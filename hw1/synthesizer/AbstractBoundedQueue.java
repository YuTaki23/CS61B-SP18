package synthesizer;

public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T>{
    protected int fillCount;
    protected int capacity;

    public int capacity() {
        return capacity;
    }

    public int fillCount() {
        return fillCount;
    }

    @Override
    public boolean isEmpty() {
        return fillCount == 0;
    }

    @Override
    public boolean isFull() {
        return capacity == fillCount;
    }

    public abstract void enqueue(T x);

    public abstract T dequeue();

    public abstract T peek();

}
