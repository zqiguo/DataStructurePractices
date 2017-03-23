package com.company;

/**
 * Created by qiguo on 2017/3/23.
 */
class DataNode{
    Data data;
    DataNode next;
    public DataNode(Data data, DataNode next){
        this.data = data;
        this.next = next;
    }
}

/**
 * this is a open hashTable realization.
 */
public class OpenHashTable {
    private int size;
    private DataNode[] hashArray;
    public OpenHashTable(){
        this.size = 5;
        this.hashArray = new DataNode[this.size];
    }

    private int hash(DataNode dataNode){
        return dataNode.data.key % 5;
    }

    /**
     * find the dataNode
     * @param dataNode
     * @return
     */
    public DataNode search(DataNode dataNode){
        if (dataNode == null){
            return null;
        }
        int pos = hash(dataNode);
        DataNode tmp = hashArray[pos];
        while (tmp != null){
            if (!tmp.data.equals(dataNode.data)){
                tmp = tmp.next;
            }else {
                return tmp;
            }
        }
        return tmp;
    }

    /**
     * insert the dataNode into hashTable
     * @param dataNode
     * @return if success, return true.
     */
    public boolean insert(DataNode dataNode){
        if (dataNode == null){
            return false;
        }
        if (search(dataNode) != null){
            return false;
        }
        int pos = hash(dataNode);
        DataNode tmp = hashArray[pos];
        if (tmp == null){
            hashArray[pos] = dataNode;
            return true;
        }
        while (tmp.next != null){
            tmp = tmp.next;
        }
        tmp.next = dataNode;
        return true;
    }

    /**
     * delete dataNode that has the same key in hashTable.
     * @param dataNode
     * @return if success, return true.
     */
    public boolean delete(DataNode dataNode){
        if (dataNode == null){
            return false;
        }
        int pos = hash(dataNode);
        DataNode tmp = hashArray[pos];
        if (tmp.data.equals(dataNode.data)){
            hashArray[pos] = null; // cannot write tmp = null !!!
        }
        while (tmp.next != null){
            if (tmp.next.data.equals(dataNode.data)){
                break;
            }else {
                tmp = tmp.next;
            }
        }
        if (tmp.next == null){
            return false;
        }else {
            tmp.next = tmp.next.next;
            return true;
        }
    }

    /**
     * print the element in hashTable.
     */
    public void print(){
        for (int i = 0; i < hashArray.length; i++) {
            DataNode tmp = hashArray[i];
            while (tmp != null){
                System.out.printf(tmp.data.value +  " ");
                tmp = tmp.next;
            }
            System.out.println();
        }
    }
    public static void main(String[] args){
        OpenHashTable openHashTable = new OpenHashTable();
        openHashTable.insert(new DataNode(new Data(1,11),null));
        openHashTable.insert(new DataNode(new Data(2,12),null));
        openHashTable.insert(new DataNode(new Data(3,13),null));
        openHashTable.insert(new DataNode(new Data(4,14),null));
        openHashTable.insert(new DataNode(new Data(5,15),null));
        openHashTable.insert(new DataNode(new Data(6,16),null));
        openHashTable.insert(new DataNode(new Data(7,17),null));
        openHashTable.insert(new DataNode(new Data(12,18),null));
        openHashTable.insert(new DataNode(new Data(103,19),null));
        openHashTable.print();
        System.out.println("*************************");
        openHashTable.delete(new DataNode(new Data(7,17),null));
        openHashTable.print();
    }
}
