package com.company;

/**
 * Created by zqiguo on 2017/2/26.
 */
public class GeneralizedList<T> {
    private class GNode<E>{
        int type;
        E value;
        int referenceNum;
        GNode<E> nextGList;
        GNode<E> next;
    }

}
