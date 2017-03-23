package com.company;

/**
 * float栈，因为要寻找最大元素，未用泛型。
 */
public class Stack {
    private float[] A;
    class Node{
        float val;
        Node next;

        Node(){
            val = 0;
            next = null;
        }
        Node(float item){
            val = item;
            this.next = null;
        }
    }
    private Node first = new Node();

    public void initialLink(float[] array){
        Node previous = first;
        for (float i: array) {
            Node tmp = new Node(i);
            previous.next = tmp;
            previous = tmp;
        }
    }
    public void printLink(){
        Node tmp = first.next;
        while (tmp != null){
            System.out.println(tmp.val);
            tmp = tmp.next;
        }
    }

    public float maxT(Node head, float max){
        if (head == null){
            System.err.println("Null Node!");
            System.exit(-1);
        }
        if (head.next == null){
            return max;
        }else{
            return  max > head.next.val ? maxT(head.next,max) : maxT(head.next,head.next.val);
        }
    }

    public float maxT(Node head){
        if (head == null){
            System.exit(-1);
        }
        if (head.next == null){
            return head.val;
        }else{
            return maxT(head.next) > head.val ? maxT(head.next) : head.val;
        }
    }

    public int num(Node head){
        if (head == null){
            return 0;
        }else{
            int num = num(head.next);
            return num+1;
        }
    }

    public float average(Node head){
        if (head == null){
            return 0;
        }
        if(head.next == null){
            return head.val;
        }else{
            return (average(head.next) * num(head.next) + head.val)/ num(head);
        }
    }
    public float findMax(int len, float max){
        if (len == A.length-1){
            return max;
        }else{
            return (A[++len] > max) ? findMax(len, A[len]) : findMax(len,max);

        }
    }

    public float findMax(float[] array, int len){
        if (len == 1){
            return array[0];
        }else {
            float tmp = findMax(array,len-1);
            return (tmp > array[len-1]) ? tmp : array[len-1];
        }
    }

    public float sum(float[] array, int len){
        if (len == 1){
            return array[0];
        }else{
            float sum = sum(array, len-1);
            return sum + array[len-1];
        }
    }

    public float average(float[] array, int len){
        if (len == 1){
            return array[0];
        }else {
            return ((len-1) * average(array,len-1) + array[len-1])/len;
        }
    }
    public static void main(String[] args) {
	// write your code here
        Stack stack = new Stack();
        stack.A = new float[]{3,1,2,4,4,4,2,4};
        float max = stack.findMax(0, stack.A[0]);
        max = stack.findMax(stack.A, stack.A.length);
        System.out.println( max  );
        System.out.println(stack.sum(stack.A, stack.A.length));
        System.out.println(stack.average(stack.A, stack.A.length));
        stack.initialLink(stack.A);
        System.out.println(stack.maxT(stack.first.next,stack.first.next.val));
        System.out.println(stack.maxT(stack.first.next));
        System.out.println(stack.average(stack.first.next));
    }
}
