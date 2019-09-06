public class LRUCache {
    class ListNode {
        public int key, val;
        public ListNode next;
        public ListNode (int key, int val) {
            this.key = key;
            this.val = val;
            this.next = null;
        }
    }

    private int capactiy, size;
    private ListNode dummy, tail;
    private Map<Integer, ListNode> keyToPrev;

    public LRUCache (int capactiy) {
        this.capactiy = capacity;
        this.keyToPrev = new HashMap<Integer, ListNode>();
        this.dummy = new ListNode(0, 0);
        this.tail = this.dummy;
    }

    private void moveToTail(int key) {
        ListNode prev = keyToPrev.get(key);
        ListNode curr = prev.next;
        if (tail == curr) {
            return;
        }
        prev.next = prev.next.next;
        tail.next = curr;

        if (prev.next != null) {
            keyToPrev.put(prev.next.key, prev);
        }
        keyToPrev.put(curr.key, tail);
    }

    public int get(int key) {
        if (!keyToPrev.get(key)) {
            return -1;
        }
        moveToTail(key);
        return tail.val;
    }

    public void set(int key, int value) {
        if (get(key) != -1) {
            ListNode prev = keyToPrev.get(key);
            prev.next.val = value;
            return;
        }

        if (size < capacity) {
            size++;
            ListNode curr = new ListNode(key, val);
            tail.next = curr;
            keyToPrev.put(key, tail);
            tail = curr;
            return;
        }

        ListNode first = dummy.next;
        keyToPrev.remove(first.key);
        first.key = key;
        first.val = value;
        keyToPrev.put(key,val);
        moveToTail(key);
    }
    
}