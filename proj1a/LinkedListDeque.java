public class LinkedListDeque<T> {
    public class IntNode {
        public IntNode prev;
        public T item;
        public IntNode next;

        public IntNode(IntNode p, T i, IntNode n) {
            this.prev = p;
            this.item = i;
            this.next = n;
        }
    }

    private IntNode sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new IntNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        IntNode first = new IntNode(sentinel, item, sentinel.next);
        sentinel.next.prev = first;
        sentinel.next= first;
        size += 1;
    }

    public void addLast(T item) {
        IntNode last = new IntNode(sentinel.prev, item, sentinel);
        sentinel.prev.next = last;
        sentinel.prev = last;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        IntNode p = sentinel;
        while (p.next != null) {
            System.out.print(p.next.item);
            System.out.print(" ");
            p = p.next;
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        T firstItem = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return firstItem;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        T lastItem = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;
        return lastItem;
    }

    public T get(int index) {
        if (index > size - 1) {
            return null;
        }

        IntNode p = sentinel.next;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.item;
    }

    private T getRecursiveHelper(IntNode currentNode, int index) {
        if (index == 0) {
            return currentNode.item;
        }
        return getRecursiveHelper(currentNode.next, index - 1);
    }

    /** Same as get but using recursion!! */
    public T getRecursive(int index) {
        if (index > size - 1) {
            return null;
        }

        return getRecursiveHelper(sentinel.next, index);
    }
}
