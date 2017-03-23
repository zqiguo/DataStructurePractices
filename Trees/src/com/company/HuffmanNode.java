package com.company;

/**
 * Created by zqiguo on 2017/3/12.
 */
public class HuffmanNode implements Comparable{
    public int weight;
    public HuffmanNode left;
    public HuffmanNode right;

    public HuffmanNode(){
        weight = -1;
        left = null;
        right = null;
    }

    public HuffmanNode(int weight){
        this.weight = weight;
        left = null;
        right = null;
    }

    @Override
    public int compareTo(Object hufNode) throws ClassCastException{
        if (!(hufNode instanceof HuffmanNode)){
            throw new ClassCastException("A HuffmanNode object is expected. ");
        }
        return weight - ((HuffmanNode) hufNode).weight;
    }
}
