import java.util.HashSet;

public class LinkedListLearn {
    Node head;

    static class Node {
        int data;
        Node next;

        Node(int d) {
            data = d;
            next = null;
        }
    }

    public void printElements() {
        Node n = head;
        while (n != null) {
            System.out.print(n.data + " ");
            n = n.next;
        }
    }

    public void addFirst(int data) {
        Node n = new Node(data);
        Node currentHead = head;
        head = n;
        head.next = currentHead;
        return;
    }

    public void addLast(int data) {
        Node node = new Node(data);
        if (head == null) {
            head = node;
            return;
        }

        Node n = head;
        while (n.next != null) {
            n = n.next;
        }
        n.next = node;
        return;
    }

    public void addAfter(Node afterNode, int data) {
        Node node = new Node(data);
        Node currentNextNode;
        if (afterNode != null) {
            currentNextNode = afterNode.next;
            afterNode.next = node;
            node.next = currentNextNode;
        }
        System.out.println("Didnt find the node to be inserted after");
        return;
    }

    public void removeFirstNode() {
        if (head == null) {
            System.out.println("Head is empty. Nothing to remove");
            return;
        }
        head = head.next;
    }

    public void removeLastNode() {
        if (head == null) {
            System.out.println("Head is empty. Nothing to remove");
            return;
        }

        if (head.next == null) {
            System.out.println("Head is the only node. Nothing to remove");
            return;
        }

        Node currentNode = head;
        Node nextNode = head;
        while (nextNode.next != null) {
            currentNode = nextNode;
            nextNode = nextNode.next;
        }
        currentNode.next = null;
    }

    public void removeDuplicatesFromSortedList() {
        if (head == null) return;
        if (head.next == null) return;

        Node n = head;
        Node nextNode;
        while (n != null) {
            nextNode = n.next;
            if (nextNode == null) return;
            if (n.data == nextNode.data) {
                n.next = nextNode.next;
            } else {
                n = n.next;
            }
        }
        return;
    }

    public void removeDuplicatesFromUnSortedList() {
        if (head == null) return;
        if (head.next == null) return;

        HashSet<Integer> hs = new HashSet<>();
        hs.add(head.data);
        Node currentNode = head;
        while (currentNode.next != null) {
            if (hs.contains(currentNode.next.data)) {
                currentNode.next = currentNode.next.next;
            } else {
                hs.add(currentNode.next.data);
                currentNode = currentNode.next;
            }
        }
        return;
    }

    public void removeAllDuplicatesFromSortedList() {
        if (head == null) return;
        if (head.next == null) return;

        Node currentNode = head;
        Node newHeadNode = null;
        Node previousNode = null;
        while (currentNode != null) {
            if (currentNode.next != null && currentNode.data == currentNode.next.data) {
                if (previousNode != null) {
                    previousNode.next = currentNode.next.next;
                }
                currentNode = currentNode.next.next;
            } else {
                if (newHeadNode == null) {
                    newHeadNode = currentNode;
                    head = newHeadNode;
                } else {
                    previousNode = currentNode;
                    currentNode = currentNode.next != null ? currentNode.next : null;
                }
            }
        }
        if (newHeadNode == null) head = null;
        return;
    }

    public void removeEverynthNode() {
        if (head == null) return;
        if (head.next == null) return;

        int i = 1;
        final int NthNode = 2;
        Node currentNode = head;
        while (currentNode.next != null) {
            i++;
            if (i % NthNode == 0) {
                currentNode.next = currentNode.next.next;
            } else {
                currentNode = currentNode.next;
            }
        }
    }

    public Node removeKthToLast(int k) {
        if (head == null) return null;
        Node p1 = head;
        Node p2 = head;

        for (int i = 0; i < k; i++) {
            if (p1 == null) return null;
            p1 = p1.next;
        }

        while (p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2;
    }

    public Node addTwoNumbers(Node l1, Node l2) {
        Node dummyHead = new Node(0);
        Node p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.data : 0;
            int y = (q != null) ? q.data : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new Node(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new Node(carry);
        }
        return dummyHead.next;
    }

    public Node mergeSortedLists(Node l1, Node l2) {
        if (l1 == null && l2 == null) return null;
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        Node head = new Node(0);
        Node curr = head;
        while (l1 != null && l2 != null) {
            if (l1.data < l2.data) {
                curr.next = new Node(l1.data);
                curr = curr.next;
                l1 = l1.next;
            } else if (l2.data < l1.data) {
                curr.next = new Node(l2.data);
                curr = curr.next;
                l2 = l2.next;
            } else {
                curr.next = new Node(l1.data);
                curr = curr.next;
                curr.next = new Node(l2.data);
                curr = curr.next;
                l1 = l1.next;
                l2 = l2.next;
            }
        }
        if (l1 != null) {
            curr.next = l1;
        }
        if (l2 != null) {
            curr.next = l2;
        }
        return head.next;
    }

    public static void main(String args[]) {
        LinkedListLearn linkedList = new LinkedListLearn();
        linkedList.head = new Node(10);
//        Node n1 = new Node(10);
//        Node n2 = new Node(20);
//        Node n3 = new Node(30);
//        Node n4 = new Node(30);
//        Node n5 = new Node(30);
//        linkedList.head.next = n1;
//        n1.next = n2;
//        n2.next = n3;
//        n3.next = n4;
//        n4.next = n5;
        //linkedList.printElements();

        //Inserting a node as first
        int first = 5;
//        linkedList.addFirst(first);
        //linkedList.printElements();

        //Inserting a node as last
        int last = 80;
//        linkedList.addLast(last);
//        linkedList.printElements();
//
//        //Inserting a node in between nodes
        int data = 25;
//        linkedList.addAfter(n2, data);
        //linkedList.printElements();

        //Trying to insert a node after an invalid node
        Node noNode = new Node(100);
        //linkedList.addAfter(noNode, data);
        //linkedList.printElements();

        //Remove first node
//        linkedList.removeFirstNode();
        //linkedList.printElements();

        //Remove last node
        //linkedList.removeLastNode();
        //linkedList.printElements();

        //Remove duplicate nodes
//        linkedList.removeDuplicatesFromSortedList();
//        linkedList.printElements();

        Node n1 = new Node(10);
        Node n2 = new Node(20);
        Node n3 = new Node(20);
        Node n4 = new Node(30);
        Node n5 = new Node(30);
        Node n6 = new Node(40);
        Node n7 = new Node(50);
        Node n8 = new Node(50);
        Node n9 = new Node(60);
        linkedList.head.next = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        n7.next = n8;
        n8.next = n9;
        linkedList.printElements();
//        linkedList.removeDuplicatesFromUnSortedList();
//        linkedList.printElements();

//        linkedList.removeAllDuplicatesFromSortedList();
//        linkedList.printElements();

        //linkedList.removeEverynthNode();
        Node kthToLast = linkedList.removeKthToLast(4);
        System.out.println("Kth node to be removed is: " + kthToLast.data);
//        linkedList.printElements();
        Node n11 = new Node(2);
        Node n21 = new Node(3);
        Node n32 = new Node(4);
        LinkedListLearn linkedList1 = new LinkedListLearn();
        linkedList1.head = n11;
        n11.next = n21;
        n21.next = n32;

        Node n111 = new Node(2);
        Node n211 = new Node(4);
        Node n321 = new Node(7);
        LinkedListLearn linkedList2 = new LinkedListLearn();
        linkedList2.head = n111;
        n111.next = n211;
        n211.next = n321;

        Node node = linkedList.addTwoNumberss(n11, n111);
        Node node1 = linkedList.addTwoNumbers(n11, n111);
        linkedList.mergeSortedLists(n11, n111);


    }

    public Node addTwoNumberss(Node l1, Node l2) {
        if (l1 == null || l2 == null) return null;

        int carry = 0;
        Node dummy = new Node(-1);
        Node n = dummy;
        while (l1 != null && l2 != null) {
            int sum = carry + l1.data + l2.data;
            if (sum > 9) carry = sum / 10;
            n.next = new Node(sum % 10);
            n = n.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        if (carry > 0) {
            n.next = new Node(carry);
        }
        return dummy.next;

    }
    //1->2->3
    //4->5->6
    //5->7->9
}

