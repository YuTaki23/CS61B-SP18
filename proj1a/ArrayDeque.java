public class ArrayDeque<T> {
    final int STARTCAPACITY = 8;
    private int capacity;
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private double factor = 0.25;

    public ArrayDeque() {
        capacity = STARTCAPACITY;
        items = (T[]) new Object[capacity];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    private int minusOne(int index) {
        if (index == 0) {
            index = capacity - 1;
        } else {
            index -= 1;
        }
        return index;
    }

    private int plusOne(int index) {
        if (index == capacity - 1) {
            index = 0;
        } else {
            index += 1;
        }
        return index;
    }

    private void resizeHelper(int resizeCapacity) {
        T[] temp = items;
        int first = plusOne(nextFirst);
        int last = minusOne(nextLast);
        items = (T[]) new Object[resizeCapacity];
        nextFirst = 0;
        nextLast = 1;
        for (int i = first; i != last; i++) {
            items[nextLast] = temp[i];
            nextLast = plusOne(nextLast);
        }
        items[nextLast] = temp[last];
        nextLast = plusOne(nextLast);
    }

    private void expand() {
        resizeHelper(items.length * 2);
    }

    private void reduce() {
        resizeHelper(items.length / 2);
    }

    private void resize() {
        if (size == items.length) {
            expand();
        } else if (size < items.length && items.length > 16) {
            reduce();
        }
    }

    /**
     * Adds an item of type T to the front of the deque.
     * @param item
     */
    public void addFirst(T item) {
        resize();
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size++;
    }

    /**
     * Adds an item of type T to the back of the deque.
     * @param item
     */
    public void addLast(T item) {
        resize();
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size++;
    }

    /**
     * Returns true if deque is empty, false otherwise.
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of items in the deque.
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     */
    public void printDeque() {
        for (int i = 0; i < items.length; i++) {
            System.out.print(items[i]);
            System.out.print(" ");
        }
    }

    /**
     * Removes and returns the item at the front of the deque. If no such item exists, returns null.
     * @return
     */
    public T removeFirst() {
        resize();
        T result = items[plusOne(nextFirst)];
        nextFirst = plusOne(nextFirst);
        items[nextFirst] = null;
        size--;
        return result;
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item exists, returns null.
     * @return
     */
    public T removeLast() {
        resize();
        T result = items[minusOne(nextLast)];
        nextLast = minusOne(nextLast);
        items[nextLast] = null;
        size--;
        return result;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque!
     * @param index
     * @return
     */
    public T get(int index) {
        if (index < 0 || index > size) {
            return null;
        } else {
            return items[index];
        }
    }
}
