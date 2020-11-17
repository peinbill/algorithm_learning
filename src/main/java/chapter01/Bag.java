package chapter01;

import java.util.Iterator;

/**
 * @Classname Bag
 * @Description 直接使用Stack的代码改一下名称即可
 * @Date 2020/10/29 9:20
 * @Created by laohuang
 */
public class Bag<Item> implements Iterable<Item>{
    private Node first;//栈顶
    private int N;

    private class Node{
        Item item;
        Node next;
    }

    public int size(){
        return N;
    }

    public boolean isEmpty(){
        return first==null;
    }

    public void add(Item item){
        Node oldFirst = first;
        first = new Node();
        first.item= item;
        first.next = oldFirst;
        N++;
    }



    public Item pop(){
        N--;
        Item item = first.item;
        first=first.next;
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
