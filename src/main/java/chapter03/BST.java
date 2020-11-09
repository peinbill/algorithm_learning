package chapter03;

/**
 * @Classname BST
 * @Description TODO
 * @Date 2020/11/3 15:39
 * @Created by laohuang
 */
public class BST<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Key key;
        private Value value;
        private Node left, right;
        private int N;

        public Node(Key key, Value value, int N) {
            this.key = key;
            this.value = value;
            this.N = N;
        }
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

    public Value get(Key key) {
        return get(root, key);
    }

    public Value get(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(x.left, key);
        } else if (cmp > 0) {
            return get(x.right, key);
        } else {
            return x.value;
        }
    }


    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    public Node put(Node x, Key key, Value value) {
        if (x == null) {
            return new Node(key, value, 1);
        }

        int cmp = key.compareTo(x.key);

        if (cmp < 0) {
            x.left = put(x.left, key, value);
        } else if (cmp > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }

        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Key min(){
        return min(root).key;
    }

    public Node min(Node x){
        if(x.left==null){
            return x;
        }
        return min(x.left);
    }

    public Key floor(Key key){
        Node x =floor(root,key);
        if(x==null){
            return null;
        }
        return x.key;
    }

    // 找出小于等于该键的最大键
    private Node floor(Node x,Key key){
        if(x==null){
            return null;
        }
        int cmp = key.compareTo(x.key);
        if(cmp==0){
            return x;
        }
        if(cmp<0){
            return floor(x.left,key);
        }

        Node t = floor(x.right,key);
        if(t!=null){
            return t;
        }else {
            return x;
        }
    }

    public Key select(int k){
        return select(root,k).key;
    }

    private Node select(Node x,int k){
        if(x==null){
            return null;
        }
        int t = size(x.left);
        if(t>k){
            return select(x.left,k);
        }
        else if (t<k){
            return select(x.right,k-t-1);
        }else {
            return x;
        }
    }

    public int rank(Key key){
        return rank(key,root);
    }

    private int rank(Key key,Node x){
        // 返回x为根节点中小于x.key的数量
        if(x==null){
            return 0;
        }
        int cmp = key.compareTo(x.key);
        if(cmp<0){
            return rank(key,x.left);
        }else if(cmp>0){
            return 1+size(x.left)+rank(key,x.right);
        }else {
            return size(x.left);
        }
    }

    public void deleteMin(){
        root = deleteMin(root);
    }

    private Node deleteMin(Node x){
        if(x.left==null){
            return x.right;
        }
        x.left = deleteMin(x.left);
        x.N = size(x.left)+size(x.right)+1;
        return x;
    }

    public void delete(Key key){
        root = delete(root,key);
    }

    private Node delete(Node x,Key key){
        if(x==null){
            return null;
        }

        int cmp = key.compareTo(x.key);

        if(cmp<0){
            x.left = delete(x.left,key);
        }else if(cmp>0){
            x.right = delete(x.right,key);
        }else {
            if(x.right==null){
                return x.left;
            }
            if(x.left==null){
                return x.right;
            }
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left)+size(x.right)+1;
        return x;

    }
}
