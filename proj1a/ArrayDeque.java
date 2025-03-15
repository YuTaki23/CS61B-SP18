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
        T[] a = (T[]) new Object[newCapacity];

        int currentFirst = plus(nextFirst);
        int currentLast = minus(nextLast);

        if (currentFirst < currentLast) {
            int length = currentLast - currentFirst + 1;
            System.arraycopy(items, currentFirst, a, 0, length);
            nextFirst = newCapacity - 1;
            nextLast = length;
        } else {
            int firstRightCount = capacity - currentFirst;
            int firstLeftCount = capacity - firstRightCount;
            System.arraycopy(items, currentFirst, a, 0, firstRightCount);
            System.arraycopy(items, 0, a, firstRightCount, firstLeftCount);

            nextFirst = newCapacity - 1;
            nextLast = capacity;
        }

        capacity = newCapacity;
        items = a;
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
        int index = plus(nextFirst);
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
            return items[(nextFirst + 1 + index) % capacity];
        }
    }

//    public static void main(String[] args) {
//        ArrayDeque<Integer> a = new ArrayDeque<>();
//        a.addFirst(1);
//        a.addFirst(2);
//        a.addFirst(3);
//        a.addFirst(4);
//        a.addFirst(5);
//        a.addFirst(6);
//        a.addFirst(7);
//        a.addFirst(8);
//        a.addFirst(9);
//        a.addFirst(1);
//        a.addFirst(2);
//        a.addFirst(3);
//        a.addFirst(4);
//        a.addFirst(5);
//        a.addFirst(6);
//        a.addFirst(7);
//        a.addFirst(8);
//        a.addFirst(9);
//        a.printDeque();
//        System.out.println("--------------------------");
//        a.removeFirst();
//        a.removeFirst();
//        a.removeFirst();
//        a.removeFirst();
//        a.removeFirst();
//        a.removeFirst();
//        a.removeFirst();
//        a.removeFirst();
//        a.removeFirst();
//        a.removeFirst();
//        a.removeFirst();
//        a.removeFirst();
//        a.printDeque();
//        System.out.println(a.get(10));
//    }
}