package chapter01;

import java.util.Iterator;

/**
 * @Classname Queue
 * @Description 列表实现——基于链表
 * @Date 2020/10/29 9:05
 * @Created by laohuang
 */
public class Queue<Item> implements Iterable<Item>{

    private Node first;
    private Node last;
    private int N;


    private class Node{
        Item item;
        Node next;
    }

    public boolean isEmpty(){
        return N==0;
    }

    public int size(){
        return N;
    }

    public void enqueue(Item item){
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;

        if(isEmpty()){
            first = last;
        }else {
            oldLast.next = last;
        }
        N++;
    }

    public Item dequeue(){
        Item item = first.item;
        first = first.next;
        if(isEmpty()){
            last = null;
        }
        N--;
        return item;

    }

    public Iterator<Item> iterator(){
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>{
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current==null;
        }

        public Item next(){
            Item item = current.item;
            current = current.next;
            return item;
        }
        public void remove(){}
    }
}
