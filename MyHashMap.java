// Time Complexity : O(1)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

class MyHashMap {

    class Node{
        int key;
        int val;
        Node next;
        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }

    private Node [] storage;
    private int buckets;
    private int hash(int key){
        return key%buckets;
    }
    public MyHashMap() {
        this.buckets = 10000;
        this.storage = new Node[buckets];
    }

    private Node helper(Node head, int key){
        Node prev = head;
        Node curr = head.next;
        //till null or till we find key
        while(curr != null && curr.key != key){
            prev = curr;
            curr = curr.next;
        }
        return prev;
    }
    
    public void put(int key, int value) {
        int idx = hash(key);
        if(storage[idx] == null){
            storage[idx] = new Node(-1, -1);
        }
        Node prev = helper(storage[idx], key);
        if(prev.next == null){
            //fresh node
            prev.next = new Node(key, value);
        }
        else{
            //existing node
            prev.next.val = value;
        }
    }
    
    public int get(int key) {
        int idx = hash(key);
        if(storage[idx] == null) return -1;
        Node prev = helper(storage[idx], key);
        if(prev.next == null){
            return -1;
        }
        return prev.next.val;
    }
    
    public void remove(int key) {
        int idx = hash(key);
        if(storage[idx] == null) return;
        Node prev = helper(storage[idx], key);
        if(prev.next == null) return;
        Node temp = prev.next;
        prev.next = prev.next.next;
        temp.next = null;
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */