public class ArrayDeque<T> {
    private int size;
    private int nextFirst;
    private T[] items;
    private int nextLast;
    private int capacity;

    public ArrayDeque() {
        this.size = 0;
        this.nextFirst = 4;
        this.items = (T[]) new Object[8];
        this.nextLast = 5;
        this.capacity = 8;
    }

    private int plus(int index) {
        if (index == capacity - 1) {
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
        for (int i = 1; i <= size; i++) {
            arr[i] = items[(++nextFirst) % this.capacity];
        }
        this.capacity = capacity;
        nextFirst = 0;
        nextLast = size + 1;
        items = arr;
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
        while (index != nextLast - 1) {
            System.out.print(items[index] + " ");
            index = plus(index);
        }
        System.out.print(items[nextLast - 1]);
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
            return items[(nextFirst + 1 + index) % capacity];
        }
    }
}