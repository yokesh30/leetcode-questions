
public class Queues {
    Node first;
    Node last;

    static class Node {
        int data;
        Node next;

        public Node(int d) {
            data = d;
            next = null;
        }
    }

    public void push(int d) {
        Node data = new Node(d);
        if (first == null || last == null) {
            first = data;
            last = data;
            return;
        }
        last.next = data;
        last = data;
    }

    public int pop() {
        int poppedData;
        if (last == null) {
            System.out.println("Nothing to pop");
            return 0;
        }
        if (first == last) {
            poppedData = first.data;
            first = last = null;
            return poppedData;
        }
        poppedData = first.data;
        first = first.next;
        return poppedData;
    }

    public int peek() {
        if (first == null) return 0;
        return first.data;
    }

    public static void main(String args[]) {
        Queues queues = new Queues();
        queues.push(1);
        queues.push(2);
        queues.push(3);
        queues.push(4);
        System.out.println("First element is: " + queues.peek());
        System.out.println("Last element is: " + queues.last.data);
        queues.pop();
        System.out.println("First element is: " + queues.peek());
        System.out.println("Last element is: " + queues.last.data);
        queues.pop();
        System.out.println("First element is: " + queues.peek());
        System.out.println("Last element is: " + queues.last.data);
        queues.pop();
        System.out.println("First element is: " + queues.peek());
        System.out.println("Last element is: " + queues.last.data);
        queues.pop();
        System.out.println("First element is: " + queues.peek());
    }
}
