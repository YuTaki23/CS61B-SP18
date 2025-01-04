package synthesizer;

import java.util.Iterator;

public interface BoundedQueue<T> extends Iterable<T>{
    /**
     * @return size of the buffer
     */
    int capacity();

    /**
     * @return number of items currently in the buffer
     */
    int fillCount();

    /**
     * add item x to the end
     */
    void enqueue(T x);

    /**
     * @return delete and return item from the front
     */
    T dequeue();

    /**
     * @return (but do not to delete) item from the front
     */
    T peek();

    /**
     * @return is the buffer empty (fillCount equals zero?)
     */
    default boolean isEmpty() {
        return fillCount() == 0;
    }

    /**
     * @return is the buffer full (fillCount is same as capacity)
     */
    default boolean isFull() {
        return fillCount() == capacity();
    }
}
