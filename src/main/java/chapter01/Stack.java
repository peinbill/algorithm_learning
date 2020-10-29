package chapter01;

import java.util.Iterator;

/**
 * @Classname Stack
 * @Description 下压栈（链表实现）——所有操作都在链表的头部
 * @Date 2020/10/28 16:50
 * @Created by laohuang
 */
public class Stack<Item> implements Iterable<Item>{

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

    public void push(Item item){
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


    public static void main(String[] args){
        Stack<String> s = new Stack<>();
        for(int i=0;i<10;i++){
            s.push(String.valueOf(i));
        }
        System.out.println(s.pop());
    }
}
