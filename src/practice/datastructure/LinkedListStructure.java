package practice.datastructure;

/**
 * Insertion − Adds an element at the beginning of the list.
 * Deletion − Deletes an element at the beginning of the list.
 * Display − Displays the complete list.
 * Search − Searches an element using the given key.
 * Delete − Deletes an element using the given key.
 */
public class LinkedListStructure {
    private int size = 0;
    private Node head;

    // insert (version 1)
    public String add(String data) {
        Node newNode = new Node();
        newNode.data = data;
        if (head == null) {
            head = newNode;
        } else {
            Node node = head;
            while (node.next != null) {
                node = node.next;
            }
            node.next = newNode;
        }
        size ++;
        return data;
    }
    // insert (version 2)
    public String add(int index, String data) {
        if (index >= size) {
            return null;
        }
        Node newNode = new Node();
        newNode.data = data;

        Node prevNode = head;
        Node nowNode = head;
        for (int i = 1 ; i <= index ; i++) {
            prevNode = nowNode;
            nowNode = nowNode.next;
        }
        size += 1;
        prevNode.next = newNode;
        newNode.next = nowNode;
        return data;
    }
    // delete
    public String remove(int index) {
        if (index > size+1) {
            return null;
        }
        Node prevNode = head;
        Node nowNode = head;
        for (int i = 1 ; i <= index ; i++) {
            prevNode = nowNode;
            nowNode = nowNode.next;
        }
        size -= 1;
        prevNode.next = nowNode.next;
        return prevNode.next.data;
    }

    // search
    public String get(int index) {
        if (index >= size) {
            return null;
        }
        Node node = head;
        for (int i = 0 ; i <= index ; i++) {
            node = node.next;
        }
        return node.data;
    }

    // display
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node node = null;
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

    private class Node {
        private String data;
        private Node next;
    }
}
