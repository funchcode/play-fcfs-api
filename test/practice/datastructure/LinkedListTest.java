package practice.datastructure;

public class LinkedListTest {

    public static void main(String[] args) {
        addTest();
    }

    public static void addTest() {
        LinkedList listStructure = new LinkedList();
        listStructure.add("1");
        listStructure.add("2");
        listStructure.add("3");
        listStructure.add("4");
        listStructure.add("5");
        listStructure.add("6");
        listStructure.add("7");
        listStructure.add("8");
        listStructure.add("9");
        listStructure.add(1, "2");
        listStructure.add(1, "2");
        listStructure.add(1, "2");
        listStructure.add(1, "2");

        System.out.println(listStructure.toString());
        System.out.println(listStructure.size());
        System.out.println(listStructure.get(10));
        System.out.println(listStructure.remove(10));
        System.out.println(listStructure.toString());
        System.out.println(listStructure.size());
    }

    public static void deleteTest() {
    }
}
