package com.company;

/**
 * 给出Sparse Matrix的三元组存储表示。
 * 并利用辅助数组给出转置操作。
 * Created by zqiguo on 2017/2/22.
 */
public class SparseMatrix<T>{
    private class Triple<E>{
        E value;
        int row;
        int column;
        public Triple(){

        }
        public Triple(Triple<E> triple){
            value = triple.value;
            row = triple.row;
            column = triple.column;
        }
    }
    Triple<T>[] matrix;
    int rowOfMatrix;
    int columnOfMatrix;
    int len;
    int maxLen;
    public SparseMatrix(){
        maxLen = 100;
        matrix =  new Triple[maxLen];
        len = 0;
        rowOfMatrix = 0;
        columnOfMatrix = 0;
    }

    public SparseMatrix(int row, int column){
        maxLen = 1000;
        matrix =  new Triple[maxLen];
        len = 0;
        rowOfMatrix = row;
        columnOfMatrix = column;
    }

    public boolean insertMatrix(int row, int column, T value){
        Triple<T> triple = new Triple<>();
        triple.value = value;
        triple.row = row;
        triple.column = column;
        if (len < maxLen){
            matrix[len++] = triple;
            return true;
        }else {
            System.err.println("The storage is Full!");
            return false;
        }
    }

    public void printMatrix(){
        for (int i = 0; i < len; i++){
            System.out.println("row: " + matrix[i].row + " column: " + matrix[i].column + " value: " + matrix[i].value);
        }
    }

    public SparseMatrix<T> transpose(){
        int[] rowSize = new int [columnOfMatrix]; //每列非零元素个数。
        int[] rowStart = new int[columnOfMatrix]; //每列在转置后矩阵中初始位置。
        for (int i = 0; i < len; i++) {
            rowSize[matrix[i].column]++;
        }
        rowStart[0] = 0;
        for (int i = 1; i < columnOfMatrix; i++) {
            rowStart[i] = rowStart[i-1] + rowSize[i-1];
        }

        SparseMatrix<T> newMatrix = new SparseMatrix<T>(columnOfMatrix, rowOfMatrix);
        newMatrix.len = this.len;
        for (int i = 0; i < len; i++) {
            Triple<T> triple = new Triple<>();
            triple.column = matrix[i].row;
            triple.row = matrix[i].column;
            triple.value = matrix[i].value;
            newMatrix.matrix[rowStart[matrix[i].column]++] = triple;
        }
        return newMatrix;
    }

    public static  void main(String[]  args){
        SparseMatrix<Integer> sparseMatrix = new SparseMatrix<>(20,10);
        sparseMatrix.insertMatrix(1,0,2);
        sparseMatrix.insertMatrix(1,1,3);
        sparseMatrix.insertMatrix(2,0,1);
        sparseMatrix.insertMatrix(2,2,2);
        sparseMatrix.insertMatrix(2,4,5);
        sparseMatrix.insertMatrix(3,1,8);
        sparseMatrix.insertMatrix(4,0,2);
        sparseMatrix.insertMatrix(4,7,9);
        sparseMatrix.printMatrix();
        System.out.println();
        sparseMatrix.transpose().printMatrix();

    }
}
