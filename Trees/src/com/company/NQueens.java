package com.company;

/**
 * n皇后问题实际上很简单。
 * Created by zqiguo on 2017/2/28.
 */
public class NQueens {

    /**
     * count用于计数解法个数。
     */
    private static int count = 0;

    /**
     * n皇后递归函数
     * @param n  皇后个数
     * @param row  当前行号
     * @param pos  前row-1 行放置的皇后位置信息。比如（0,pos[0]），表示第0行皇后放置在第pos[0]列。
     */
    private static void nQueens(int n, int row, int[] pos){
        if (row == n){
            count++;
            return;
        }
        for (int i = 0; i < n; i++) {
            int conflictFlag = 0;
            for (int j = 0; j < row; j++) {
                if (i == pos[j] || pos[j] - j == i - row || pos[j] + j == i + row){
                    conflictFlag = 1;
                }
            }
            if (conflictFlag == 0){
                pos[row] = i;
                nQueens(n,row+1,pos);
            }
        }
    }

    /**
     * n皇后外部调用接口， 返回解法个数
     * @param n  皇后个数
     * @return 解法个数
     */
    public static int nQueens(int n){
        int[] pos = new int[n];
        nQueens(n,0,pos);
        return count;
    }

    public static void main(String[] args){
        System.out.println(nQueens(8));
    }
}
