package com.company;

/**
 * Created by zqiguo on 2017/2/18.
 * 用循环链表实现队列。
 */
public class MyQuene<T> {
    class Node<E>{
        E value;
        Node<E> next;
    }

    Node<T> rear;
    public MyQuene(){
        rear = new Node<>();
        rear.next = rear;
    }
    public boolean insert(T element){
        Node<T> tmp = new Node<>();
        tmp.value = element;
        if (rear.next == rear.next.next){
            rear = rear.next;
        }
        tmp.next = rear.next;
        rear.next = tmp;
        rear = tmp;
        return true;
    }
    public T delete(){
        if (rear.next == rear.next.next){
            rear = rear.next;
            return null;
        }else {
            Node<T> tmp;
            tmp = rear.next.next;
            rear.next.next = rear.next.next.next;
            return tmp.value;
        }
    }
    public static void main(String[] args){
        MyQuene<Integer> myQuene = new MyQuene<>();
        myQuene.insert(1);
        myQuene.insert(2);
        System.out.println(myQuene.delete());
        System.out.println(myQuene.delete());
        myQuene.insert(3);
        System.out.println(myQuene.delete());
        System.out.println(myQuene.delete());
        myQuene.insert(4);
        System.out.println(myQuene.delete());
    }


}
