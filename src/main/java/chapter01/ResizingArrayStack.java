package chapter01;


import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.Iterator;

/**
 * @Classname ResizingArrayStack
 * @Description 动态扩容和手动实现迭代
 * @Date 2020/10/28 10:52
 * @Created by laohuang
 */
public class ResizingArrayStack<Item> implements Iterable<Item>{
    private Item[] a;
    private int N;

    public ResizingArrayStack(int cap) {
        a = (Item[]) new Object[cap];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }


    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    // 动态扩容
    public void push(Item item){
        if(N==a.length){
            resize(2*a.length);
        }
        a[N++]=item;
    }

    // 半满状态
    public Item pop(){
        Item item = a[--N];
        // 对象游离
        a[N]=null;
        if(N>0&&N==a.length/4){
            resize(a.length/2);
        }
        return item;
    }

    public Iterator<Item> iterator(){
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item>{
        private int i=N;
        public boolean hasNext(){
            return i>0;
        }
        public Item next(){
            return a[--i];
        }
        public void remove(){}
    }
}
