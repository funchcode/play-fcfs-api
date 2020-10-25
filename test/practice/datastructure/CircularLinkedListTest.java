package practice.datastructure;

public class CircularLinkedListTest {

    public static void main(String[] args) {
//        addTest();
        removeTest();
    }

    public static void addTest() {
        CircularLinkedList<String> linkedList = new CircularLinkedList<>();
        linkedList.add("a");
        linkedList.add("b");
        linkedList.add("c");
        linkedList.add("d"); // abcd

        System.out.println(linkedList.display());
        System.out.println(linkedList.size()); // 4
    }

    public static void removeTest() {
        CircularLinkedList<String> linkedList = new CircularLinkedList<>();
        linkedList.add("a");
        linkedList.add("b");
        linkedList.add("c");
        linkedList.add("d"); // abcd
        linkedList.add("e");
        linkedList.add("f"); // abcdef
        System.out.println(linkedList.display());
        linkedList.remove(); // abcde
        System.out.println(linkedList.display());
        linkedList.remove(); // abcd
        System.out.println(linkedList.display());
        linkedList.remove(); // abc
        System.out.println(linkedList.display());
        linkedList.remove(); // ab
        System.out.println(linkedList.display());
        linkedList.remove(); // a
        System.out.println(linkedList.display());
        linkedList.remove();
        System.out.println(linkedList.display());
    }

}
