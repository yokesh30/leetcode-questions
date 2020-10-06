import java.util.*;

public class Trees {
    Node root;

    static class Node {
        int data;
        Node left;
        Node right;

        public Node(int d) {
            data = d;
            left = null;
            right = null;
        }
    }

    public Node push(Node n, int data) {
        Node d = new Node(data);
        if (n == null) {
            root = d;
            return d;
        }
        if (data < n.data) {
            n.left = push(n.left, data);
        } else {
            n.right = push(n.right, data);
        }
        return n;
    }

    public Node search(Node root, int data) {
        if (root == null || root.data == data) {
            return root;
        }
        if (data < root.data) return search(root.left, data);
        return search(root.right, data);
    }

    void inOrder(Node root) {
        if (root != null) {
            inOrder(root.left);
            System.out.println(root.data);
            inOrder(root.right);
        }
    }

    void preOrder(Node root) {
        if (root != null) {
            System.out.println(root.data);
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    void postOrder(Node root) {
        if (root != null) {
            postOrder(root.left);
            postOrder(root.right);
            System.out.println(root.data);
        }
    }

    public Node delete(Node root, int d) {
        return deleteRecursively(root, d);
    }

    public Node deleteRecursively(Node root, int d) {
        if (root == null) return null;
        if (d < root.data) {
            root.left = deleteRecursively(root.left, d);
        } else if (d > root.data) {
            root.right = deleteRecursively(root.right, d);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            root.data = findMin(root.right);
            root.right = deleteRecursively(root.right, root.data);
        }
        return root;
    }

    public int findMin(Node root) {
        int min = root.data;
        while (root.left != null) {
            min = root.left.data;
            root = root.left;
        }
        return min;
    }

    void inOrderBST(Node root, ArrayList<Integer> arr) {
        if (root != null) {
            inOrderBST(root.left, arr);
            arr.add(root.data);
            inOrderBST(root.right, arr);
        }
    }

    public boolean isBinarySearchTree(Node root) {
        ArrayList<Integer> elements = new ArrayList<>();
        if (root == null) return false;
        inOrderBST(root, elements);
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer integer, Integer t1) {
                return integer - t1;
            }
        };
        return isSorted(elements, comparator);
    }

    public boolean isBST(Node n) {
        if (n == null) return true;
        Integer prev = null;
        if (!isBST(n.left)) return false;

        if (prev != null && n != null && n.data <= prev) {
            return false;
        }
        prev = n.data;
        if (!isBST(n.right)) return false;
        return true;
    }

    public boolean isSorted(ArrayList<Integer> elements, Comparator<Integer> comparator) {
        Iterator<Integer> iterator = elements.iterator();
        Integer current, prev = iterator.next();
        while (iterator.hasNext()) {
            current = iterator.next();
            if (comparator.compare(prev, current) > 0) return false;
            prev = current;
        }
        return true;
    }

    public Node commonAncestor(Node root, Node p, Node q) {
        if (root == null || p == null || q == null) return null;

        if (!checkNodes(root, p) || !checkNodes(root, q)) return null;

        return ancestorHelper(root, p, q);
    }

    public Node ancestorHelper(Node root, Node p, Node q) {
        if (root == null || root == p || root == q) return root;
        boolean pIsOnLeft = checkNodes(root.left, p);
        boolean qIsOnLeft = checkNodes(root.left, q);

        if (pIsOnLeft != qIsOnLeft) return root;

        Node childSide = pIsOnLeft ? root.left : root.right;
        return ancestorHelper(childSide, p, q);
    }

    public boolean checkNodes(Node root, Node b) {
        if (root == null) return false;
        if (root == b) return true;
        return checkNodes(root.left, b) || checkNodes(root.right, b);
    }


    public Node createMinimalBST(int[] values) {
        return createMinimalBST(values, 0, values.length - 1);
    }

    public Node createMinimalBST(int[] vals, int start, int end) {
        if (end < start) return null;
        int mid = (start + end) / 2;
        Node root = new Node(vals[mid]);
        root.left = createMinimalBST(vals, start, mid - 1);
        root.right = createMinimalBST(vals, mid + 1, end);
        return root;
    }

    public void printElementsInOrderTraversal(Node root) {
        if (root != null) {
            inOrder(root.left);
            System.out.println(root.data);
            inOrder(root.right);
        }
    }

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> levels = new ArrayList<>();
        if (root == null) return levels;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;
        while (!queue.isEmpty()) {
            levels.add(new ArrayList<>());

            int level_length = queue.size();
            for (int i = 0; i < level_length; ++i) {
                Node node = queue.remove();
                levels.get(level).add(node.data);

                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            level++;
        }
        return levels;
    }

    public List<List<Integer>> zigZagLevelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        boolean zigzag = false;
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int cnt = queue.size();
            for (int i = 0; i < cnt; i++) {
                Node node = queue.poll();
                if (zigzag) {
                    level.add(0, node.data);
                } else {
                    level.add(node.data);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            res.add(level);
            zigzag = !zigzag;
        }
        return res;
    }

    int maxValue;

    public int maxPathSum(Node root) {
        maxValue = Integer.MIN_VALUE;
        maxPathDown(root);
        return maxValue;
    }

    private int maxPathDown(Node node) {
        if (node == null) return 0;
        int left = Math.max(0, maxPathDown(node.left));
        int right = Math.max(0, maxPathDown(node.right));
        maxValue = Math.max(maxValue, left + right + node.data);
        return Math.max(left, right) + node.data;
    }

    private Node prev = null;

    public void flatten(Node root) {
        if (root == null)
            return;
        flatten(root.right);
        flatten(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }

    public static void main(String args[]) {
        Trees trees = new Trees();
        Node root = new Node(100);
        trees.root = root;
        trees.push(root, 70);
        trees.push(root, 120);
        trees.push(root, 50);
        trees.push(root, 90);
        trees.push(root, 30);
        trees.push(root, 60);
        trees.push(root, 80);
        trees.push(root, 92);
        trees.push(root, 110);
        trees.push(root, 140);

        trees.flatten(root);
//        System.out.println("In order traversal");
//        trees.inOrder(root);
//        System.out.println("Pre order traversal");
//        trees.preOrder(root);
//        System.out.println("Post order traversal");
//        trees.postOrder(root);
//
//        System.out.println("Search");
//        Node search = trees.search(root, 120);
//        System.out.println("Data is :" + search.data);
////        System.out.println("Data left is :" + search.left.data);
////        System.out.println("Data right is :" + search.right.data);
//
//        Node search1 = trees.search(root, 140);
//        System.out.println("Data is :" + search1.data);
////        System.out.println("Data left is :" + search1.left.data);
////        System.out.println("Data right is :" + search1.right.data);
//
////        Node delete = trees.delete(root, 120);
//////        System.out.println("Node deleted is: " + delete.data);
////
////        Node delete1 = trees.delete(root, 50);
//////        System.out.println("Node deleted is: " + delete1.data);
////        trees.inOrder(root);
////
////        System.out.println("Is tree BST: " + trees.isBinarySearchTree(root));
////        System.out.println("Is tree BST?: " + trees.isBST(root));
//
//        Node node = trees.commonAncestor(root, search, search1);
//        System.out.println(" Common ancestor: " + node.data);

//        int[] vals = new int[]{1, 2, 3, 4, 5, 6};
//        Node minimalBST = trees.createMinimalBST((vals));
//        trees.printElementsInOrderTraversal(minimalBST);

//        List<List<Integer>> lists = trees.zigZagLevelOrder(root);
        int i = trees.maxPathSum(root);
    }
}
