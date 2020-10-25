package practice.datastructure;

/**
 * Insertion − Adds an element at the beginning of the list.
 * Deletion − Deletes an element at the beginning of the list.
 * Display − Displays the complete list.
 * Search − Searches an element using the given key.
 * Delete − Deletes an element using the given key.
 */
public class CircularLinkedList<E> {
    private int size = 0;
    private Node<E> tail;

    // size
    public int size() {
        return this.size;
    }

    // display
    public String display() {
        StringBuilder sb = new StringBuilder();
        Node<E> node = null;
        for (int i=0 ; i<size ; i++) {
            if (i==0) {
                node = tail.next;
            } else {
                node = node.next;
            }
            sb.append(node.data);
        }
        return sb.toString();
    }

    // insert
    public E add(E data) {
        Node<E> node = new Node<>(data);
        if (tail == null) {
            tail = node;
            tail.prev = node;
            tail.next = node;
            size += 1;
            return data;
        }
        node.next = tail.next;
        tail.next = node;
        node.prev = tail;
        tail = node;
        size += 1;
        return data;
    }

    // delete
    public E remove() {
        if (size == 0) {
            return null;
        }
        if (size == 1) {
            Node<E> head = tail.next;
            tail.next = null;
            tail.prev = null;
            tail = null;
            size -= 1;
            return head.data;
        }
        Node<E> prevNode = tail.prev;
        prevNode.next = tail.next;
        tail = prevNode;
        size -=1;
        return tail.data;
    }

    private class Node<E> {
        private Node prev;
        private Node next;
        private E data;

        Node(E data) {
            this.data = data;
        }
    }
}
