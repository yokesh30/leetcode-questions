public class DoublyLinkedList {

    Node head;

    static class Node {
        String data;
        Node prev;
        Node next;

        public Node(String d) {
            data = d;
            next = null;
        }
    }

    public void printElements() {
        if (head == null) return;
        if (head.next == null) {
            System.out.print("Head is: " + head.data + " ");
            return;
        }

        Node node = head;
        Node last = null;
        while (node != null) {
            last = node;
            System.out.print(node.data + " ");
            node = node.next;
        }

//        //Reverse order
//        while (last != null) {
//            System.out.print(last.data + " ");
//            last = last.prev;
//        }
    }

    public void addFirst(String d) {
        Node node = new Node(d);
        if (head == null) {
            head = node;
            return;
        }

        node.next = head;
        node.prev = null;
        head.prev = node;
        head = node;
        return;
    }

    public void addAfterNode(Node afterNode, String d) {
        Node node = new Node(d);

        if (afterNode == null) return;
        if (head == null) head = node;
        Node nextNode = afterNode.next;
        afterNode.next = node;
        node.prev = afterNode;
        node.next = nextNode;
        if (nextNode != null) {
            nextNode.prev = node;
        }
        return;
    }

    public void addLast(String d) {
        Node node = new Node(d);
        if (head == null) head = node;

        Node n = head;
        while (n.next != null) {
            n = n.next;
        }
        n.next = node;
        node.prev = n;
        return;
    }

    public boolean isPalindrome() {
        if (head == null) return false;

        Node n1 = head;
        while (n1.next != null) {
            n1 = n1.next;
        }
        Node n2 = n1;
        n1 = head;

        while (n1.next != null && n2.prev != null) {
            if (!n1.data.equals(n2.data)) {
                return false;
            }
            n1 = n1.next;
            n2 = n2.prev;
        }
        return true;
    }

    public static void main(String args[]) {
        DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
//        Node n = new Node(10);
//        n.prev = null;
//        doublyLinkedList.head = n;
//
//        Node n1 = new Node(20);
//        n1.prev = n;
//        n.next = n1;
//
//        doublyLinkedList.addFirst(4);
//
//        doublyLinkedList.addAfterNode(n1, 25);
//        doublyLinkedList.addAfterNode(n, 15);
//        doublyLinkedList.addLast(30);

        Node n = new Node("a");
        n.prev = null;
        doublyLinkedList.head = n;

        doublyLinkedList.addLast("c");
        doublyLinkedList.addLast("d");
        doublyLinkedList.addLast("d");
        doublyLinkedList.addLast("c");
        doublyLinkedList.addLast("a");
        doublyLinkedList.printElements();
        System.out.println("Is palindrome: " + doublyLinkedList.isPalindrome());
    }
}
