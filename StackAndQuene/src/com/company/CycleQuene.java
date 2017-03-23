package com.company;

import java.util.Objects;

/**
 * Created by zqiguo on 2017/2/18.
 * 循环队列，用数组实现。根据元素个数自动扩充和缩减数组长度。
 * 根据rear 和 length 来判断满空。
 */
public class CycleQuene<T> {
      T[] array;
      int rear;
      int length;
      int maxElement;
      public CycleQuene(){
          array = (T[])new Object[20];
          rear = 0;
          length = 0;
          maxElement = array.length;
      }
      public CycleQuene(int n){
          array = (T[])new Object[n];
          rear = 0;
          length = 0;
          maxElement = array.length;
      }
      public boolean isEmpty(){
          if (length == 0){
              return true;
          }else
              return false;
      }
      public boolean isFull(){
          if (length == array.length){
              return true;
          }else {
              return false;
          }
      }
      public boolean EnQueue(T element){
          if (!isFull()){
              array[rear] = element;
              rear = (rear+1) % array.length;
              length++;
              return true;
          }else {
              T[] tmp = (T[])new Object[2*length];
              for (int i = 0; i < length; i++ ){
                  tmp[i] = array[(rear - length + i + array.length) % array.length];
              }
              array = tmp;
              array[length] = element;
              maxElement = array.length;
              rear = (length+1) % array.length;
              length++;
              return false;
          }
      }
      public T DlQueue(){
          if (!isEmpty()){
              T a;
              a = array[(rear + array.length - length) % array.length];
              length--;
              if (length < array.length / 4){
                  T[] tmp = (T[])new Object[array.length / 2];
                  for (int i = 0; i < length; i++){
                      tmp[0] = array[(rear - length + i + array.length) % array.length];
                  }
                  rear = length;
                  array = tmp;
                  maxElement = array.length;
              }
              return a;
          }else {

              return null;
          }
      }
      public static void main(String[] args){
          CycleQuene<Integer> cq = new CycleQuene<Integer>(2);
          cq.EnQueue(1);
          cq.EnQueue(2);
          cq.EnQueue(3);
          cq.EnQueue(4);
          cq.EnQueue(5);
          System.out.println(cq.maxElement);
          System.out.println(cq.DlQueue());
          System.out.println(cq.DlQueue());
          System.out.println(cq.DlQueue());
          System.out.println(cq.DlQueue());
          System.out.println(cq.maxElement);
          cq.EnQueue(6);
          cq.EnQueue(7);
          cq.EnQueue(4);
          cq.EnQueue(5);
          System.out.println(cq.DlQueue());
          System.out.println(cq.DlQueue());
          System.out.println(cq.DlQueue());
          System.out.println(cq.DlQueue());


    }


}
