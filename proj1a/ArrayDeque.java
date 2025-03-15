public class ArrayDeque<T> {
    private int size;
    private int nextFirst;
    private T[] items;
    private int nextLast;
    private static final int initCapacity = 8;
    private int capacity;

    public ArrayDeque() {
        this.size = 0;
        this.nextFirst = 4;
        this.items = (T[]) new Object[initCapacity];
        this.nextLast = 5;
    }

    private int plus(int index) {
        if (index == capacity) {
            index = 0;
        } else {
            index++;
        }
        return index;
    }

    private int minus(int index) {
        if (index == 0) {
            index = capacity - 1;
        } else {
            index--;
        }
        return index;
    }

    private void resize(int newCapacity) {
        T[] arr = (T[]) new Object[newCapacity];
        int p = nextFirst;
        for (int i = 0; i < Math.min(size, newCapacity); i++) {
            arr[i] = items[p];
            p = (p + 1) % capacity;
        }
        items = arr;
        nextFirst = 0;
        nextLast = size;
        capacity = newCapacity;
    }

    public void addFirst(T item) {
        if (size >= capacity) {
            resize(capacity * 2);
        }
        items[nextFirst] = item;
        nextFirst = minus(nextFirst);
        size++;
    }

    public void addLast(T item) {
        if (size >= capacity) {
            resize(capacity * 2);
        }
        items[nextLast] = item;
        nextLast = plus(nextLast);
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (size == 0) {
            return;
        }
        int index = nextFirst;
        index = plus(index);
        while (index != nextFirst) {
            System.out.print(items[index] + " ");
            index = plus(index);
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if (capacity >= 16 && size <= capacity / 4) {
            resize(capacity / 2);
        }
        int current = plus(nextFirst);
        nextFirst = current;
        items[nextFirst] = null;
        size--;
        return items[current];
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (capacity >= 16 && size <= capacity / 4) {
            resize(capacity / 2);
        }
        int current = minus(nextLast);
        nextLast = current;
        items[nextLast] = null;
        size--;
        return items[current];
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        } else {
            int indexFromFirst = nextFirst + 1 + index;
            if (indexFromFirst >= capacity) {
                indexFromFirst -= capacity;
            }

            return items[indexFromFirst];
        }
    }
}