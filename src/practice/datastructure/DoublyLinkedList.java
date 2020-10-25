package practice.datastructure;

/**
 * Insertion − Adds an element at the beginning of the list.
 * Deletion − Deletes an element at the beginning of the list.
 * Display − Displays the complete list.
 * Search − Searches an element using the given key.
 * Delete − Deletes an element using the given key.
 */
public class DoublyLinkedList<E> {
    private Node<E> head;
    private int size = 0;

    // display
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<E> node = null;
        do {
            if (node == null) {
                node = head;
            } else {
                node = node.next;
            }
            sb.append(node.data);
        } while (node.next != null);

        return sb.toString();
    }

    public int size() {
        return this.size;
    }

    // insert
    public E add(E data) {
        Node<E> node = new Node<>(data);
        if (head == null) {
            head = node;
            size += 1;
            return data;
        }
        Node<E> lastNode = head;
        while (lastNode.next != null) {
            lastNode = lastNode.next;
        }
        lastNode.next = node;
        node.prev = lastNode;
        size += 1;
        return data;
    }

    // insert by index
    public E add(int index, E data) {
        if (index > size) {
            return null;
        }
        Node<E> node = new Node<>(data);
        Node<E> targetNode = head;

        for (int i = 0 ; i <= index ; i++) {
            if (i != 0) {
                targetNode = targetNode.next;
            }
        }
        if (index == 0) {
            head = node;
        } else {
            Node<E> prevNode = targetNode.prev;
            prevNode.next = node;
            node.prev = prevNode;
        }
        node.next = targetNode;
        targetNode.prev = node;
        size += 1;
        return data;
    }

    // delete
    public E remove(int index) {
        if (index > size) {
            return null;
        }
        Node<E> targetNode = head;
        for (int i = 0 ; i <= index ; i++) {
            if (i != 0) {
                targetNode = targetNode.next;
            }
        }
        if (index == 0) {
            Node<E> nextNode = targetNode.next;
            head = nextNode;
        } else if (index == size - 1){
            Node<E> prevNode = targetNode.prev;
            prevNode.next = null;
        } else {
            Node<E> prevNode = targetNode.prev;
            Node<E> nextNode = targetNode.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
        }
        size -= 1;
        return targetNode.data;
    }

    // search
    public E search(int index) {
        if (index > size) {
            return null;
        }
        Node<E> targetNode = head;
        for (int i = 0 ; i <= index ; i++) {
            if (i != 0) {
                targetNode = targetNode.next;
            }
        }
        return targetNode.data;
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
