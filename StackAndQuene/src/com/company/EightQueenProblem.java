package com.company;

/**
 * 八皇后问题解法
 * 同时还给出了N皇后问题的解法
 * @author Jinggong Zhang
 * @version 1.0
 */
public class EightQueenProblem {
    //count the solution number
    public static int count = 0;
    /**
     * Each recursion, scan one line and find the proper location.
     * If find, go to deeper recursion and line; or, go back to last recursion.
     * The key is what to store in recursion and how to judge conflict.
     * @param n is row information, initialized by 0
     * @param place is array preserving context information, which length is 8
     * @return none
     */

    public static void placeRow(int[] place, int n){
        if (n == 8){
            for (int j = 0; j < 8; j++) {
                System.out.print(j + ","+ place[j] + " ");
            }
            count++;
            System.out.println(count);
            return;
        }
        for (int i = 0; i < 8; i++) {
            int flag = 0;
            for (int j = 0; j < n; j++) {
                if (place[j] == i || place[j]+j == i+n || place[j]-j == i-n){
                    flag = 1;
                }
            }
            if (flag == 0){
                place[n] = i;
                placeRow(place,n+1);
            }
        }
    }

    public static int NQueens(int[] array, int line){
        if (line == array.length){
            ++count;
        }else {
            for(int i = 0 ; i < array.length; i++){
                int conflict = 0;
                for (int j = 0; j < line; j++){
                    if (array[j] == i || array[j] - j == i - line || array[j] + j == i + line){
                        conflict = 1;
                    }
                }
                if (conflict == 0){
                    array[line] = i;
                    NQueens(array,line+1);
                }
            }
        }
        return count;
    }

    public static int NQueens(int n, boolean[] column, boolean[] d1, boolean[] d2, int row){
        if (row == n) count++;
        for (int i = 0; i < n; i++){
            int n1 = row + i;
            int n2 = row - i + n;
            if (column[i] || d1[n1] || d2[n2]) {
                continue;
            }
            column[i] = true;
            d1[n1] = true;
            d2[n2] = true;
            NQueens(n,column,d1,d2,row+1);
            column[i] = false;
            d1[n1] = false;
            d2[n2] =false;
        }
        return count;
    }
    public static int totalNQueens(int n) {
        boolean[] column = new boolean[n];
        boolean[] d1 = new boolean[2*n];
        boolean[] d2 = new boolean[2*n];

        return NQueens(n,column,d1,d2,0);
    }
    public static void main(String[] args){

        System.out.println(totalNQueens(2));
    }
}
