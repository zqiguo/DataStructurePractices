package com.company;

/**
 * Created by qiguo on 2017/3/21.
 */
abstract class BaseSort{
    public abstract int[] sort(int[] array);
    public static void printResult(int[] array){
        for (int i: array) {
            System.out.printf(i + " ");
        }
        System.out.println();
    }
}
class BubbleSort extends BaseSort{
    public int[] sort(int[] array){
        for (int i = 1; i < array.length; i++) {
            int change = 0;
            for (int j = array.length-1; j >= i; j--) {
                if (array[j] < array[j-1]){
                    int tmp = array[j];
                    array[j] = array[j-1];
                    array[j-1] = tmp;
                    change = 1;
                }
            }
            if (change == 0){
                break;
            }
        }
        return array;
    }
}
class InsertSort extends BaseSort{
    @Override
    public int[] sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int j = i;
            int tmp = array[i];
            while (j > 0){
                if (tmp < array[j-1]){
                    array[j] = array[j-1];
                    j--;
                }else {
                    break;
                }
            }
            array[j] = tmp;
        }
        return array;
    }
}

class BinarySearchSort extends BaseSort{
    public int binarySearch(int[] array, int key){
        int left = 0;
        int right = array.length-1;
        while (left <= right){
            int mid = (left + right)/2;
            if (key > array[mid]){
                left = mid + 1;
            }else if (key < array[mid]){
                right = mid - 1;
            }else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * Note that: it is stable. Because when duplication happens, It always inserts behind the last one.
     * @param array
     * @return
     */
    @Override
    public int[] sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int left = 0;
            int right = i-1;
            while (left <= right){
                int mid = (left + right) / 2;
                if (array[mid] > array[i]){
                    right = mid - 1;
                }else {
                    left = mid + 1;
                }
            }
            int tmp = array[i];
            int j = i;
            while (j > left){
                array[j] = array[j-1];
                j--;
            }
            array[j] = tmp;
        }
        return array;
    }
}


public class MySort{
    public static void main(String[] args){
        int[] array = new int[]{0,1,2,1,0,-2,5,9,10,0,1,4,6,7,3,2,1,-1};
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.printResult(bubbleSort.sort(array.clone()));

        InsertSort insertSort = new InsertSort();
        insertSort.printResult(insertSort.sort(array.clone()));

        BinarySearchSort binarySearchSort = new BinarySearchSort();
        System.out.println(array.length);
        binarySearchSort.printResult(binarySearchSort.sort(array.clone()));
    }
}


