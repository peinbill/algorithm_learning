package chapter03;

/**
 * @Classname SequentialSearchST
 * @Description 基于无序链表的顺序查找
 * @Date 2020/11/3 10:42
 * @Created by laohuang
 */
public class SequentialSearchST<Key extends Comparable<Key>,Value> {

    private Node first;

    private class Node{
        Key key;
        Value val;
        Node next;
        public Node(Key key,Value value,Node next){
            this.key= key;
            this.val = value;
            this.next=next;
        }
    }

    public Value get(Key key){
        for(Node x=first;x!=null;x=x.next){
            if(key.equals(x.key)){
                return x.val;
            }
        }
        return null;
    }

    public void put(Key key,Value value){
        for(Node x=first;x!=null;x=x.next){
            if(key.equals(x.key)){
                x.val=value;
                return;
            }
        }

        first = new Node(key,value,first);
    }



}
