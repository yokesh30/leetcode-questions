import java.util.HashMap;

public class LRUCache {
    private final HashMap<Integer, ListNode> cachedMap = new HashMap<>();
    private final DLL cachedList = new DLL();
    private final int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!cachedMap.containsKey(key)) {
            return -1;
        }

        ListNode targetNode = cachedMap.get(key);
        cachedList.promote(targetNode);

        return targetNode.val;
    }

    public void set(int key, int value) {
        ListNode targetNode;

        if (cachedMap.containsKey(key)) {
            targetNode = cachedMap.get(key);
            targetNode.val = value;
            cachedList.promote(targetNode);
            return;
        }

        if (cachedMap.size() == capacity) {
            ListNode node = cachedList.removeLast();
            cachedMap.remove(node.key);
        }

        targetNode = new ListNode(key, value);
        cachedList.addFirst(targetNode);
        cachedMap.put(targetNode.key, targetNode);
    }

    public static void main(String args[]) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.set(1, 1);
        lruCache.set(2, 2);
        lruCache.get(1);
        lruCache.set(3, 3);
        lruCache.get(2);

    }
}

class ListNode {
    int key;
    int val;
    ListNode prev;
    ListNode next;

    public ListNode(int k, int v) {
        key = k;
        val = v;
    }
}

class DLL {
    private ListNode head = null;
    private ListNode tail = null;

    public void addFirst(ListNode node) {
        if (head == null) {
            head = node;
            tail = node;
            return;
        }

        head.prev = node;
        node.next = head;
        node.prev = null;
        head = node;
    }

    public ListNode removeLast() {
        ListNode node = tail;

        if (tail.prev != null) {
            tail.prev.next = null;
            tail = tail.prev;
        } else {
            head = null;
            tail = null;
        }

        return node;
    }

    public void promote(ListNode node) {
        if (node.prev == null) {
            return;
        }

        node.prev.next = node.next;
        if (node.next == null) {
            tail = node.prev;
        } else {
            node.next.prev = node.prev;
        }

        head.prev = node;
        node.next = head;
        node.prev = null;
        head = node;
    }
}
