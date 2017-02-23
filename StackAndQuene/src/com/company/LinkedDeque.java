package com.company;

/**
 * 链表表示的双端队列
 * Created by zqiguo on 2017/2/18.
 */
public class LinkedDeque<T> {
    class Node<E>{
        E value;
        Node<E> next;
    }
    Node<T> head;
    Node<T> rear;
    public LinkedDeque(){
        head = null;
        rear = null;
    }
    public boolean isEmpty(){
        if (head == null){
            return true;
        }else {
            return false;
        }
    }

    /**
     *
     * @param element
     * @param flag 0 头插， 1尾插
     */
    public void enDeque(T element, int flag){
        Node<T> tmp = new Node<>();
        tmp.value = element;
        if (isEmpty()){
            head = tmp;
            rear = tmp;
        }else {
            if (flag == 0){
                tmp.next = head;
                head = tmp;
            }else {
                rear.next = tmp;
                rear = tmp;
            }
        }
    }
    public T dlDeque(){
        if (isEmpty()){
            return null;
        }else {
            T tmp = head.value;
            head = head.next;
            return tmp;
        }
    }

    public static void main(String[] args){
        LinkedDeque<Double> linkedDeque = new LinkedDeque<>();
        linkedDeque.enDeque(1.0,1);
        linkedDeque.enDeque(2.0,2);
        linkedDeque.enDeque(3.0,3);
        System.out.println(linkedDeque.dlDeque());
        System.out.println(linkedDeque.dlDeque());
        System.out.println(linkedDeque.dlDeque());
        System.out.println(linkedDeque.dlDeque());
        linkedDeque.enDeque(1.0,0);
        linkedDeque.enDeque(2.0,0);
        linkedDeque.enDeque(3.0,3);
        System.out.println(linkedDeque.dlDeque());
        System.out.println(linkedDeque.dlDeque());
        System.out.println(linkedDeque.dlDeque());
        System.out.println(linkedDeque.dlDeque());
    }
}
