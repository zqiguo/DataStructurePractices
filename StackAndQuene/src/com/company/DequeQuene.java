package com.company;

/**
 * 数组表示的双端队列。
 * Created by zqiguo on 2017/2/18.
 */
public class DequeQuene<T> {
    T[] array;
    int maxLen;
    int end1;
    int end2;
    public DequeQuene(){
        array = (T[]) new Object[5];
        maxLen = 5;
        end1 = 0;
        end2 = 0;
    }
    public DequeQuene(int n){
        array = (T[]) new Object[n];
        end1 = 0;
        end2 = 0;
        maxLen = n;
    }

    public boolean isEmpty(){
        if (end1 == end2)
        {
            return true;
        }else {
            return false;
        }
    }
    public boolean isFull(){
        if ((end2 + 1) % maxLen == end1){
            return true;
        }else {
            return false;
        }
    }

    /**
     *
     * @param element 要添加元素
     * @param end 添加方向，0 表示从左边插入，1表示从右边插入。
     * @return 成功与否
     */
    public boolean EnQueue(T element, int direction){
        if (isFull()){
            return false;
        }else {
            if(direction == 0){
                end1 = (end1 + maxLen - 1) % maxLen;
                array[end1] = element;
            }else {
                array[end2] = element;
                end2 = (end2 + 1) % maxLen;
            }
            return true;
        }
    }
    public T DeQueue(int direction){
        if (isEmpty()){
            return null;
        }else {
            if (direction == 0){
                T tmp = array[end1];
                end1 = (end1 + 1) % maxLen;
                return tmp;
            }else {
                end2 = (end2 + maxLen -1) % maxLen;
                T tmp = array[end2];
                return tmp;
            }
        }
    }

    public static void main(String[] args){
        DequeQuene<Integer> dequeQuene = new DequeQuene<>();
        dequeQuene.EnQueue(1,1);
        dequeQuene.EnQueue(2,1);
        dequeQuene.EnQueue(3,1);
        System.out.println(dequeQuene.DeQueue(1));
        System.out.println(dequeQuene.DeQueue(1));
        System.out.println(dequeQuene.DeQueue(1));
        System.out.println(dequeQuene.DeQueue(0));
    }
}
