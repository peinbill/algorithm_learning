package chapter03;


import chapter01.Queue;

/**
 * @Classname RedBlackBST
 * @Description 红黑树
 * @Date 2020/11/5 16:40
 * @Created by laohuang
 */
public class RedBlackBST<Key extends Comparable<Key>,Value>{
    private static final boolean RED = true;
    private static final boolean BLACK =false;
    private Node root;
    private class Node{
        Key key;
        Value value;
        Node left,right;
        int N;
        boolean color;
        Node(Key key,Value value,int N,boolean color){
            this.key = key;
            this.value=value;
            this.N=N;
            this.color = color;
        }
    }

    public boolean isEmpty() {
        return root == null;
    }
    
    public int size() {
        return size(root);
    }

    public int size(Node x) {
        if (x == null) {
            return 0;
        } else {
            return x.N;
        }
    }

    private boolean isRed(Node x){
        if(x==null){
            return false;
        }
        return x.color==RED;
    }


    Node rotateLeft(Node h){
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color=RED;
        x.N = h.N;
        h.N = 1+size(h.left)+size(h.right);
        return x;
    }

    Node rotateRight(Node h){
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1+size(h.left)+size(h.right);
        return x;
    }
//    // 如果只考虑插入的话，用这行就行
//    void flipColors(Node h){
//        h.color = RED;
//        h.left.color = BLACK;
//        h.right.color = BLACK;
//    }

    // flip the colors of a node and its two children
    private void flipColors(Node h) {
        // h must have opposite color of its two children
        // assert (h != null) && (h.left != null) && (h.right != null);
        // assert (!isRed(h) &&  isRed(h.left) &&  isRed(h.right))
        //    || (isRed(h)  && !isRed(h.left) && !isRed(h.right));
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    public void put(Key key,Value value){
        root = put(root,key,value);
        root.color = BLACK;
    }

    public Node put(Node h,Key key,Value value){
        if(h==null){
            return new Node(key,value,1,RED);
        }

        int cmp = key.compareTo(h.key);
        if(cmp<0){
            h.left = put(h.left,key,value);
        }else if(cmp>0){
            h.right = put(h.right,key,value);
        }else {
            h.value = value;
        }

        if(isRed(h.right)&&!isRed(h.left)){
            h = rotateLeft(h);
        }
        if(isRed(h.left)&&isRed(h.left.left)){
            h = rotateRight(h);
        }
        if(isRed(h.left)&&isRed(h.right)){
            flipColors(h);
        }
        h.N = size(h.left)+size(h.right)+1;
        return h;
    }


    // restore red-black tree invariant
    private Node balance(Node h) {
        // assert (h != null);

        if (isRed(h.right)) {
            h = rotateLeft(h);
        }
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }
        if (isRed(h.left) && isRed(h.right)){
            flipColors(h);
        }

        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }


    // Assuming that h is red and both h.left and h.left.left
    // are black, make h.left or one of its children red.
    private Node moveRedLeft(Node h) {//这个函数是用来处理2节点的

        flipColors(h);//直接保证了删除不是2结点 这里有可能把h
        if (isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

    // Assuming that h is red and both h.right and h.right.left
    // are black, make h.right or one of its children red.
    private Node moveRedRight(Node h) {
        // assert (h != null);
        // assert isRed(h) && !isRed(h.right) && !isRed(h.right.left);
        flipColors(h);
        if (isRed(h.left.left)) {
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }


    public void deleteMin() {

        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = deleteMin(root);
        if (!isEmpty()) root.color = BLACK;
        // assert check();
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        return get(root, key);
    }

    // value associated with the given key in subtree rooted at x; null if no such key
    private Value get(Node x, Key key) {
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if      (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else              return x.value;
        }
        return null;
    }


    // delete the key-value pair with the minimum key rooted at h
    private Node deleteMin(Node h) {
        if (h.left == null)
            return null;
        // 保证了不删除2结点
        if (!isRed(h.left) && !isRed(h.left.left)){
            h = moveRedLeft(h);
        }
        h.left = deleteMin(h.left);
        return balance(h);
    }






    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (!contains(key)) return;

        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = delete(root, key);
        if (!isEmpty()) root.color = BLACK;
        // assert check();
    }

    // delete the key-value pair with the given key rooted at h
    private Node delete(Node h, Key key) {
        // assert get(h, key) != null;

        if (key.compareTo(h.key) < 0)  {
            // 保证查找路上都不是2结点
            if (!isRed(h.left) && !isRed(h.left.left)){
                h = moveRedLeft(h);

            }
            h.left = delete(h.left, key);
        }
        else {
            if (isRed(h.left))
                h = rotateRight(h);
            if (key.compareTo(h.key) == 0 && (h.right == null)){
                // 叶子结点
                return null;
            }
            if (!isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);
            if (key.compareTo(h.key) == 0) {
                Node x = min(h.right);
                h.key = x.key;
                h.value = x.value;
                // h.val = get(h.right, min(h.right).key);
                // h.key = min(h.right).key;
                h.right = deleteMin(h.right);
            }
            else h.right = delete(h.right, key);
        }
        return balance(h);
    }


    public Key min() {
        return min(root).key;
    }

    // the smallest key in subtree rooted at x; null if no such key
    private Node min(Node x) {
        // assert x != null;
        if (x.left == null) return x;
        else                return min(x.left);
    }

    public Iterable<Key> keys() {
        if (isEmpty()) return new Queue<Key>();
        return keys(min(), max());
    }

    public Key max() {
        return max(root).key;
    }

    // the largest key in the subtree rooted at x; null if no such key
    private Node max(Node x) {
        // assert x != null;
        if (x.right == null) return x;
        else                 return max(x.right);
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");

        Queue<Key> queue = new Queue<Key>();
        // if (isEmpty() || lo.compareTo(hi) > 0) return queue;
        keys(root, queue, lo, hi);
        return queue;
    }

    // add the keys between lo and hi in the subtree rooted at x
    // to the queue
    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
        if (cmphi > 0) keys(x.right, queue, lo, hi);
    }
}
