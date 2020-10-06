import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class StackUsingLinkedLists {
    Node root;

    List<Node> tempStack;

    public StackUsingLinkedLists() {
        tempStack = new ArrayList<>();
    }

    static class Node {
        int d;
        Node next;

        public Node(int data) {
            this.d = data;
            this.next = null;
        }
    }

    public void push(int d) {
        Node n = new Node(d);
        tempStack.add(n);
        if (root == null) root = n;

        Node temp = root;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = n;
    }

    public Node pop() {
        Node prevToTop = getPrevToTop();
        if (prevToTop == null) return null;

        Node top = prevToTop.next;
        prevToTop.next = null;
        return top;
    }

    public Node getPrevToTop() {
        if (tempStack.isEmpty()) return null;
        return tempStack.get(tempStack.size() - 2);
    }

    public void printElements() {
        Node n = this.root;
        if (n == null) {
            System.out.println("Empty");
            return;
        }
        while (n != null) {
            System.out.println(n.d);
            n = n.next;
        }
    }

    public static void main(String args[]) {
        StackUsingLinkedLists stackUsingLinkedLists = new StackUsingLinkedLists();
        Node root = new Node(5);
        stackUsingLinkedLists.root = root;
        stackUsingLinkedLists.push(10);
        stackUsingLinkedLists.push(15);
        stackUsingLinkedLists.push(20);

        stackUsingLinkedLists.printElements();
        stackUsingLinkedLists.pop();
        stackUsingLinkedLists.printElements();
    }
}
