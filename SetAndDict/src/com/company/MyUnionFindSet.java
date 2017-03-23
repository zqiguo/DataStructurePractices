package com.company;

/**
 * UnionFindSet is a set to tackle only Union and Find operations in set.
 * Created by qiguo on 2017/3/21.
 */
public class MyUnionFindSet {
    private int[] array;
    private int storageSize;
    public MyUnionFindSet(){
        this.storageSize = 10;
        this.array = new int[this.storageSize];
        for (int i = 0; i < this.storageSize; i++) {
            array[i] = -1;
        }
    }

    /**
     * merge two element into one set
     * @param s1 element s1
     * @param s2 element s2
     * @return union set element num
     */
    public int union(int s1, int s2){
        int set1 = find(s1);
        int set2 = find(s2);
        if (set1 == set2){
            return -1;
        }
        if (array[set1] <= array[set2]){
            array[set1] += array[set2];
            array[set2] = set1;
            return array[set1];
        }else {
            array[set2] += array[set1];
            array[set1] = set2;
            return array[set2];
        }
    }

    /**
     * find the set that element belongs to
     * @param element
     * @return set number
     */
    public int find(int element){
        int tmp = element;
        while (array[tmp] > 0){
            tmp = array[tmp];
        }
        return tmp;
    }

    /**
     * collapse the branch while find, to optimize the structure of the tree.
     * @param element
     * @return the set number that element belongs to
     */
    public int collapsingFind(int element){
        int tmp = element;
        while (array[tmp] > 0){
            tmp = array[tmp];
        }
        while (array[element] > 0){
            int parent = array[element];
            array[element] = tmp;
            element = parent;
        }
        return tmp;
    }

    public void print(){
        for (int element: array) {
            System.out.printf(element + " ");
        }
        System.out.println();
    }
    public static void main(String[] args){
        MyUnionFindSet myUnionFindSet = new MyUnionFindSet();
        myUnionFindSet.union(1,2);
        myUnionFindSet.union(2,3);
        myUnionFindSet.union(1,5);
        myUnionFindSet.union(4,6);
        myUnionFindSet.union(7,9);
        myUnionFindSet.union(1,6);
        myUnionFindSet.union(1,6);
        myUnionFindSet.print();
        myUnionFindSet.collapsingFind(6);
        myUnionFindSet.print();
        System.out.println(myUnionFindSet.find(6));
        System.out.println(myUnionFindSet.find(1));
    }


}
