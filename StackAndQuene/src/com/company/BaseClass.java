package com.company;

/**
 * 给出了栈，队列，优先级队列的继承结构。
 * BaseClass 为共同抽象基类
 * Created by zqiguo on 2017/2/20.
 */
public abstract class BaseClass<T> {
    protected  T[] array;
    protected int top;
    protected int len;
    public BaseClass(){
        array = (T[]) new Object[10];
        top = -1;
        len = 0;
    }
    public abstract boolean Add(T element);
    public abstract T Remove();
    public abstract T Get();
    public abstract boolean put(T element);
    public abstract boolean makeEmpty();
    public abstract boolean isEmpty();
    public abstract boolean isFull();
    public int Length() { return len; }
}

class LocalQuene<T> extends BaseClass<T>{
    public boolean Add(T element){
        if (isFull()){
            return false;
        }else {
            len++;
            top = (top + 1) % array.length;
            array[top] = element;
            return true;
        }
    }
    public  T Remove(){
        if (isEmpty()){
            return null;
        }else {
            len--;
            return array[(top + array.length - len) % array.length];
        }
    }
    public T Get(){
        if (isEmpty()){
            return null;
        }else {
            return array[(top + array.length - len) % array.length];
        }
    }
    public boolean put(T element){
        return Add(element);
    }
    public boolean makeEmpty(){
        len = 0;
        return true;
    }
    public boolean isEmpty(){
        if (len == 0){
            return true;
        }else {
            return false;
        }
    }
    public boolean isFull(){
        if (len == array.length){
            return true;
        }else {
            return false;
        }
    }

    public static void main(String[] args){

        BaseClass<Integer> localQuene = new LocalStack<>();
        localQuene.Add(1);
        localQuene.Add(2);
        localQuene.Add(3);
        System.out.println(localQuene.Remove());
        System.out.println(localQuene.Remove());
        System.out.println(localQuene.Remove());
        System.out.println(localQuene.Remove());
        localQuene.Add(1);
        localQuene.Add(2);
        localQuene.Add(3);
        System.out.println(localQuene.Remove());
        System.out.println(localQuene.Remove());
        System.out.println(localQuene.Remove());

    }
}

abstract class LocalPriorityQuene<T> extends BaseClass<T>{

}

class LocalStack<T> extends BaseClass<T>{
    public boolean Add(T element){
        if (isFull()){
            System.out.println("The stack is Full!");
            return false;
        }else {
            array[++top] = element;
            len++;
            return true;
        }
    }
    public T Remove(){
        if (isEmpty()){
            System.out.println("The stack is Empty!");
            return null;
        }else {
            len--;
            return array[top--];
        }
    }
    public T Get(){
        if (isEmpty()){
            System.out.println("The stack is Empty!");
            return null;
        }else {
            return array[top];
        }
    }
    public boolean put(T element){
        return Add(element);
    }
    public boolean makeEmpty(){
        top = -1;
        len = 0;
        return true;
    }
    public boolean isEmpty(){
        if (top == -1){
            return true;
        }else{
            return false;
        }
    }
    public boolean isFull(){
        if (top == array.length-1){
            return true;
        }else {
            return false;
        }
    }
//    public static void main(String[] args){
//        LocalStack<Float> stack = new LocalStack<>();
//        stack.put(1f);
//        stack.put(2f);
//        System.out.println(stack.Remove());
//        System.out.println(stack.Remove());
//        System.out.println(stack.Remove());
//    }
}

