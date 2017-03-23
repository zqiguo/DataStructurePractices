package com.company;

/**
 * Created by qiguo on 2017/3/20.
 */
class Data implements Comparable<Data>{
    int key;
    int value;
    public Data(){}
    public Data(int key, int value){
        this.key = key;
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Data){
            return this.key == ((Data) obj).key;
        }else {
            return false;
        }
    }

    @Override
    public int compareTo(Data data) {
        return this.key - data.key;
    }
}

/**
 * closed hashTable: stored by cycle array.
 * it is just an example.
 * Note that closed hashTable is not a good implement of hashTable, comparing to open hashTable.
 */
public class MyHashSet {
    private int setSize;
    private int elementNum;
    private String[] setInfo;
    private Data[] hashSet;
    public MyHashSet(){
        this.setSize = 6;  // the size is small, because it is easy to test :)
        this.elementNum = 0;
        this.hashSet = new Data[this.setSize];
        this.setInfo = new String[this.setSize]; //"empty", "deleted" or "active"
        for (int i = 0; i < setInfo.length; i++) {
            setInfo[i] = "empty";
        }
    }
    private int hash(int key){
        return key % 5;
    }

    /**
     * Find the storage position of element
     * This is a private element. because it cannot directly reflect the real position of element.
     * @param element
     * @return position
     */
    private int searchPos(Data element){
        int pos = this.hash(element.key);
        int j = pos;
        do {
            if (setInfo[j].equals("empty") || setInfo[j].equals("active") && hashSet[j].key == element.key){
                break;
            }
            j = (j + 1) % this.setSize;
        } while (j != pos);

        return j;
    }

    /**
     * Find the position of element.if find, return position; else, return -1;
     * @param element
     * @return
     */
    public int search(Data element){
        int pos = this.searchPos(element);
        if (setInfo[pos].equals("active") && hashSet[pos].key == element.key){
            return pos;
        }else {
            return -1;
        }
    }

    /**
     * insert a new element into hashSet
     * @param element
     * @return
     */
    public boolean insert(Data element){
        if (this.elementNum == this.setSize){
            System.err.println("The Set is FULL!");
            return false;
        }
        int pos = searchPos(element);
        if (!setInfo[pos].equals("active")){
            setInfo[pos] = "active";
            hashSet[pos] = element;
            this.elementNum++;
            return true;
        }else {
            if (hashSet[pos].key == element.key){
                System.err.println("Already have!");
                return false;
            }else {
                System.err.println("The Set is logically full!");
                return false;
            }
        }
    }

    /**
     * delete the element of hashSet
     * @param element
     * @return if delete successfully , return true; else, false.
     */
    public boolean delete(Data element){
        if (this.elementNum == 0){
            System.err.println("The Set is Empty!");
            return false;
        }
        int pos = searchPos(element);
        if (setInfo[pos].equals("active") && hashSet[pos].key == element.key){
            setInfo[pos] = "deleted";
            this.elementNum--;
            return true;
        }else {
            System.err.println("Not have!");
            return false;
        }
    }


    public static void main(String[] args){
        MyHashSet hashSet = new MyHashSet();

        hashSet.insert(new Data(1,11));
        hashSet.insert(new Data(13,12));
        hashSet.insert(new Data(3,13));
        hashSet.insert(new Data(4,14));
        hashSet.insert(new Data(16,15));
        hashSet.insert(new Data(5,16));
        for (Data d : hashSet.hashSet){
            System.out.println(d.value);
        }
        System.out.println(hashSet.elementNum);
        hashSet.delete(new Data(5,16));
        hashSet.insert(new Data(15,17));
        for (Data d : hashSet.hashSet){
            System.out.println(d.value);
        }
    }
}
