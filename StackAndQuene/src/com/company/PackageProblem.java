package com.company;

/**
 * 背包问题解法
 * 以及排列组合问题，汉诺塔问题，斐波那契数列问题等的递归解法
 * Created by zqiguo on 2017/2/16.
 */
public class PackageProblem {

    /**
     * search from first element; backtracking
     * search every possible combination, from 0 to length of weight array
     */
    public static boolean packageSolution(int sum, int[] weight, int i){
        if(i == weight.length - 1 && sum != weight[i]){
            return false;
        }else if (sum == weight[i]){
            return true;
        }
        if(sum < weight[i] || packageSolution(sum-weight[i],weight,i+1) == false ){
            return packageSolution(sum,weight,i+1);
        }else{
            return true;
        }
    }

    /**
     * search from last element
     */
    public static boolean packageSolution2(int sum, int[] weight, int n){
        if (sum < 0 || sum > 0 && n < 1){
            return false;
        }else if (sum == 0) {
            return true;
        }
        if (packageSolution2(sum-weight[n-1],weight,n-1)){
            System.out.printf(weight[n-1] + " ");
            return true;
        }else {
            return packageSolution2(sum,weight,n-1);
        }
    }

    static int count = 0;

    /**
     * print all permutation of n element
     * @param array
     * @param level
     * @param len
     * @return
     */
    public static int perm(int[] array, int level, int len){
        if (level == 0){
            for (int i:
                 array) {
                System.out.print(i + " ");
            }
            count++;
            System.out.println();
            return count;
        }
        for (int i = 0; i <= level; i++) {
            int tmp = array[level];
            array[level] = array[i];
            array[i] = tmp;
            perm(array,level-1,len);
            tmp = array[level];
            array[level] = array[i];
            array[i] = tmp;
        }
        return count;
    }

    /**
     * combination(newt,5,3); newt is int[3]
     * @param array storage one recursive result
     * @param n number of all element.
     * @param r the number to combined.
     * @return none
     */
    public static void combination(int[] array, int n, int r){
        if (r == 0){
            for (int j : array)
                System.out.printf(j +"　");
            System.out.println();
            count++;
            return;
        }
        for (int i = n - 1; i > r - 2; i--){
            array[r-1] = i+1;
            combination(array,i,r-1);

        }
    }

    public int GCD(int m, int n){
        if (m < 0) m = -m;
        if (n < 0) n = -n;
        if (m < n) return GCD(n,m);
        if (n == 0){
            return m;
        }else {
            return GCD(n, m%n);
        }
    }

    public static void hanoi(char A, char B, char C, int n){
        if (n == 1){
            System.out.println(A + " -> "  + C);
            count++;
        }else{
            hanoi(A,C,B,n-1);
            System.out.println(A + " -> " + C);
            count++;
            hanoi(B,A,C,n-1);
        }
    }
    public static long Fib(long n) {
        if (n <=1){
            return n;
        } else{
            return Fib(n-1) + Fib(n-2);
        }
    }

    public static long fib(int n){
        int[] stack = new int[1000000];
        int top = -1;
        long sum = 0;
        int tmp = 0;
        stack[++top] = n;
        while (top != -1){
            tmp = stack[top--];
            if (tmp > 1){
                stack[++top] = tmp-1;
                stack[++top] = tmp-2;
            }else {
                sum += tmp;
            }

        }
        return sum;
    }
    public static long fibIterate(int n){
        if (n <=1) return n;
        long a1 = 0;
        long a2 = 1;
        long result = 0;
        for (int i = 2; i <= n; i++) {
            result = a1 + a2;
            a1 = a2;
            a2 = result;
        }
        return result;
    }
    public static void main(String[] args){
//        int [] w = new int[]{1,2,4,8,16,32};
//        System.out.println(packageSolution2(51,w,6));
//        int [] newt = new int[3];
//        combination(newt,5,3);
//        System.out.println(count);
        char A = 'A';
        char B = 'B';
        char C = 'C';
        hanoi(A,B,C,4);
        System.out.println(count);
        System.out.println(fibIterate(50));


    }
}
