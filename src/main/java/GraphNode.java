import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class GraphNode {
    static class Node {
        int data;
        boolean visited;
        LinkedList<Node> neighbors;

        public Node(int d) {
            data = d;
            visited = false;
            neighbors = new LinkedList<>();
        }

        public void addEdges(Node neighbor) {
            this.neighbors.push(neighbor);
        }
    }

    public void dfsIterative(Node n) {
        Stack<Node> stack = new Stack<>();
        stack.push(n);

        while (!stack.isEmpty()) {
            Node element = stack.pop();
            if (!element.visited) {
                System.out.print(element.data + " ");
                element.visited = true;
            }
            LinkedList<Node> neighbors = element.neighbors;
            for (Node nd : neighbors) {
                if (n != null && !nd.visited) {
                    stack.push(nd);
                }
            }
        }
    }

    public void dfsRecursive(Node n) {
        if (n == null) return;
        n.visited = true;
        LinkedList<Node> neighbors = n.neighbors;
        System.out.print(n.data + " ");
        for (Node nd : neighbors) {
            if (!nd.visited)
                dfsRecursive(nd);
        }
    }

    public void bfsIterative(Node n) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(n);

        while (!queue.isEmpty()) {
            Node element = queue.remove();
            if (!element.visited) {
                System.out.print(element.data + " ");
                element.visited = true;
            }
            LinkedList<Node> neighbors = element.neighbors;
            for (Node nd : neighbors) {
                if (n != null && !nd.visited) {
                    queue.add(nd);
                }
            }
        }
    }

    public boolean hasPathBetweenNodes(Node n, Node e) {
        Stack<Node> stack = new Stack<>();
        stack.push(n);

        while (!stack.isEmpty()) {
            Node element = stack.pop();
            if (!element.visited && element.data == e.data) {
                System.out.print(element.data + " ");
                element.visited = true;
                return true;
            }
            LinkedList<Node> neighbors = element.neighbors;
            for (Node nd : neighbors) {
                if (n != null && !nd.visited) {
                    stack.push(nd);
                }
            }
        }
        return false;
    }

    public static void main(String args[]) {
        GraphNode node = new GraphNode();
        Node n = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);

        n.addEdges(n2);
        n.addEdges(n3);
//        n2.addEdges(n3);
        n2.addEdges(n4);
        n4.addEdges(n5);
        n3.addEdges(n6);

        System.out.println("Iteration");
//        node.dfsIterative(n);
//        node.dfsRecursive(n);
        System.out.println("Does nodes have path: " + node.hasPathBetweenNodes(n2, n3));
        //BFS
//        Node node40 = new Node(40);
//        Node node10 = new Node(10);
//        Node node20 = new Node(20);
//        Node node30 = new Node(30);
//        Node node60 = new Node(60);
//        Node node50 = new Node(50);
//        Node node70 = new Node(70);
//
//        node40.addEdges(node10);
//        node40.addEdges(node20);
////        node10.addEdges(node30);
//        node20.addEdges(node10);
////        node20.addEdges(node30);
////        node20.addEdges(node60);
//        node20.addEdges(node50);
//        node30.addEdges(node60);
//        node60.addEdges(node70);
//        node50.addEdges(node70);
////        node.dfsIterative(n);
//        System.out.println("Recursion print");
////        node.dfsRecursive(n);
////        node.bfsIterative(node40);
//        System.out.println("Does nodes have path: " + node.hasPathBetweenNodes(node20, node30));

    }
}
