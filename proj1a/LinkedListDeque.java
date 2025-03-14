public class LinkedListDeque<T> {
    private class IntNode {
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

    // creates an empty LinkedListDeque
    public LinkedListDeque() {
        sentinel = new IntNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        sentinel.next = new IntNode(sentinel, item, sentinel.next);
        //sentinel.next = sentinel.next.prev;
        sentinel.next.next.prev = sentinel.next;
        size++;
    }

    public void addLast(T item) {
        sentinel.prev = new IntNode(sentinel.prev, item, sentinel);
        //sentinel.prev = sentinel.prev.next;
        sentinel.prev.prev.next = sentinel.prev;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        IntNode currentNode = sentinel.next;
        for (int i = 0; i < size; i++) {
            System.out.print(currentNode.item + " ");
            currentNode = currentNode.next;
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            IntNode currentNode = sentinel.next;
            sentinel.next.next.prev = sentinel;
            sentinel.next = sentinel.next.next;
            size--;
            return currentNode.item;
        }
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            IntNode currentNode = sentinel.prev;
            sentinel.prev.prev.next = sentinel;
            sentinel.prev = sentinel.prev.prev;
            size--;
            return currentNode.item;
        }
    }

    public T get(int index) {
        if (index > size) {
            return null;
        } else {
            IntNode currentNode = sentinel.next;
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.next;
            }
            return currentNode.item;
        }
    }

    private T getRecursive(int index, IntNode temp) {
        if (index == 0) {
            return temp.item;
        } else {
            return getRecursive(index - 1, temp.next);
        }
    }

    public T getRecursive(int index) {
        IntNode currentNode = sentinel.next;
        if (index >= size) {
            return null;
        } else {
            return getRecursive(index, currentNode);
        }
    }
}