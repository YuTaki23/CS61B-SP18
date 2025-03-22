package synthesizer;
import java.util.Iterator;

public interface BoundedQueue<T> extends Iterable<T> {
    /**
      * @return 返回buffer的size
     */
    int capacity();

    /**
     * @return 返回当前buffer内有多少个items
     */
    int fillCount();

    /**
     * @param x 将x添加到最后
     */
    void enqueue(T x);

    /**
     * @return 删除并返回第一个item
     */
    T dequeue();

    /**
     * @return 返回但不要删除第一个item
     */
    T peek();

    /**
     * @return 若当前buffer为空则返回true，否则返回false
     */
    default boolean isEmpty() {
        return this.fillCount() == 0;
    }

    /**
     * @return 若当前buffer全满返回true，否则返回false
     */
    default boolean isFull() {
        return this.capacity() == this.fillCount();
    }
}
