package practice.datastructure;

public class DoublyLinkedListTest {

    public static void main(String[] args) {
//        addTest();
//        addByIndexTest();
//        removeTest();
        searchTest();
    }

    public static void addTest() {
        DoublyLinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.add("a");
        linkedList.add("b");
        linkedList.add("c");
        linkedList.add("d");
        linkedList.add("e");
        linkedList.add("f");
        System.out.println(linkedList.toString());
    }

    public static void addByIndexTest() {
        DoublyLinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.add("a");
        linkedList.add("a");
        linkedList.add("a");
        linkedList.add("a"); // index : 3, size : 4
        System.out.println(linkedList.toString());
        linkedList.add(0, "z"); // zaaaa
        System.out.println(linkedList.toString());
        linkedList.add(0, "B"); // Bzaaaa
        System.out.println(linkedList.toString());
        linkedList.add(3, "k"); // Bzakaaa
        System.out.println(linkedList.toString());
    }

    public static void removeTest() {
        DoublyLinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.add("a");
        linkedList.add("b");
        linkedList.add("c");
        linkedList.add("d");
        linkedList.add("e"); // abcde
        linkedList.remove(0); // bcde
        System.out.println(linkedList.size());
        linkedList.remove(3); // bcd
        linkedList.remove(0); // cd
        System.out.println(linkedList.toString());
    }

    public static void searchTest() {
        DoublyLinkedList<String> linkedList = new DoublyLinkedList<>();
        linkedList.add("k");
        linkedList.add("o");
        linkedList.add("r");
        linkedList.add("e");
        linkedList.add("a");

        System.out.println(linkedList.search(2)); //r
        System.out.println(linkedList.search(4)); //a
        System.out.println(linkedList.search(0)); //k
    }
}
