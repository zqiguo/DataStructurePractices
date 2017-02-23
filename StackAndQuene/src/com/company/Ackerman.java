package com.company;

/**
 * ackerman函数的递归与非递归实现
 * Created by zqiguo on 2017/2/14.
 */

public class Ackerman {

    public static int akm(int m, int n){
        if (m == 0){
            return n+1;
        }
        if(m != 0 && n == 0){
            return akm(m-1,1);
        }

        return akm(m-1,akm(m,n-1));


    }
    public static int akmNonStack(int m, int n){
        int[] sm = new int[1000];
        int[] sn = new int[1000];
        int top = -1;
        sm[++top] = m;
        sn[top] = n;
        int i, j, k;
        while (true){
            i = sm[top];
            j = sn[top];
            top--;
            if (i == 0){
                k = j+1;
                if (top == -1){
                    return k;
                }else {
                    sn[top]=k;
                }
            }else if (j == 0){
                sm[++top] = i - 1;
                sn[top] = 1;
            }else {
                sm[++top] = i - 1;
                sm[++top] = i;
                sn[top] = j - 1;
            }
        }

    }
    public static void main(String[] args){
        System.out.println(akm(3,10));
        System.out.println(akmNonStack(3,10));
    }
}
