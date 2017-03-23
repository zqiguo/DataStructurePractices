package com.company;

/**
 * Created by zqiguo on 2017/3/11.
 */
public class Heap {
    private int heapSize;
    private int currentSize;
    private HuffmanNode[] heapArray;

    public int getCurrentSize(){
        return currentSize;
    }
    /**
     * siftDown algorithm when build a heap from an array
     * if you know the storage of a complete binary tree well, it's easy.
     * The point is how to reduce the move of array element.
     * @param pos int,  the position to start siftDown
     */
    private void siftDown(int pos){
        HuffmanNode tmp = heapArray[pos];
        while (pos * 2 + 1 < currentSize){
            int minIndex = pos * 2 + 1;
            if (minIndex + 1 < currentSize){
                minIndex = heapArray[minIndex].compareTo(heapArray[minIndex+1]) <= 0 ? minIndex : minIndex+1;
            }
            if (heapArray[minIndex].compareTo(tmp) < 0){
                heapArray[pos] = heapArray[minIndex];
                pos = minIndex;
            }else {
                break;
            }
        }
        heapArray[pos] = tmp;
    }

    /**
     * return the top of the heap
     * @return the top of the heap
     */
    public HuffmanNode getTop(){
        return heapArray[0];
    }

    public void printHeap(){
        for (int i = 0; i < currentSize; i++)
        {
            System.out.printf(heapArray[i] + " ");
        }
        System.out.println();
    }
    public Heap(){
        heapSize = 5;
        currentSize = 0;
        heapArray = new HuffmanNode[heapSize];
    }

    /**
     * build a heap from an array.
     * @param array
     */
    public Heap(HuffmanNode[] array){
        currentSize = array.length;
        if (heapSize < currentSize){
            heapArray = new HuffmanNode[currentSize];
            heapSize = currentSize;
        }
        for (int i = 0; i < currentSize; i++) {
            heapArray[i] = array[i];
        }
        int lastParent = (currentSize - 2) / 2;
        while (lastParent >= 0){
            siftDown(lastParent--);
        }
    }

    /**
     * insert an element to heap. auto resize the heapArray, if full.
     * @param element
     * @return
     */
    public boolean insert(HuffmanNode element){
        if (currentSize == heapSize){
            HuffmanNode[] tmp = new HuffmanNode[heapSize + 10];
            for (int i = 0; i < currentSize; i++) {
                tmp[i] = heapArray[i];
            }
            heapArray = tmp;
            heapSize = tmp.length;
        }
        heapArray[currentSize] = element;
        siftUp(currentSize);
        currentSize++;
        return true;
    }

    /**
     * delete the top of the heap
     * @return the top of heap
     */
    public HuffmanNode delete(){
        if (currentSize == 0){
            System.out.println("The heap is empty!");
            return null;
        }else {
            HuffmanNode tmp = heapArray[0];
            heapArray[0] = heapArray[currentSize-1];
            currentSize--;
            siftDown(0);
            return tmp;
        }
    }

    /**
     * siftup adjust, used by insert.
     * @param start
     */
    private void siftUp(int start){
        HuffmanNode tmp = heapArray[start];
        while (start > 0){
            int parentIndex = (start-1) / 2;
            if (heapArray[parentIndex].compareTo(tmp) > 0){
                heapArray[start] = heapArray[parentIndex];
                start = parentIndex;
            }else {
                break;
            }
        }
        heapArray[start] = tmp;
    }
//    public class Node {
//        int value;
//        Node left;
//        Node right;
//    }
//
//    /**
//     * build a tree from heap array, use a queue to realize.
//     * @return
//     */
//    public Node buildTreeFromHeap(){
//        if (currentSize == 0){
//            return null;
//        }
//        Node root = new Node();
//        root.value = heapArray[0];
//        Node[] queue = new Node[20];
//        int head = 0;
//        int tail = 0;
//        queue[tail++%queue.length] = root;
//        for (int i = 0; i <= (currentSize-2)/2; i++){
//            Node left = new Node();
//            left.value = heapArray[2*i + 1];
//            queue[tail++%queue.length] = left;
//            Node right = null;
//            if (2*i + 2 < currentSize){
//                right = new Node();
//                right.value = heapArray[2*i + 2];
//                queue[tail++%queue.length] = right;
//            }
//            if (head != tail){
//                queue[head%queue.length].left = left;
//                queue[head++%queue.length].right = right;
//            }
//        }
//        return root;
//    }
//
//    public Node buildTreeFromHeapRecursion(int n){
//        Node tmp = null;
//        if (n < currentSize){
//            tmp = new Node();
//            tmp.value = heapArray[n];
//            tmp.left = buildTreeFromHeapRecursion(2*n+1);
//            tmp.right = buildTreeFromHeapRecursion(2*n+2);
//        }
//        return tmp;
//    }
//    public static  void main(String[] args){
//        int[] array = new int[]{7,6,5,4,3,2,1};
//        Heap heap = new Heap(array);
//        heap.printHeap();
//        heap.insert(0);
//        heap.printHeap();
//        heap.delete();
//        heap.printHeap();
//        heap.delete();
//        heap.printHeap();
//        heap.delete();
//        heap.printHeap();
//    }
}
