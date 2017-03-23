package com.company;

public class StringPatternMatch {
    /**
     * naive match algorithm
     * @param source
     * @param pattern
     * @return
     */
    public static int naiveMatch(String source, String pattern){
        int stop = source.length() - pattern.length();
        if (stop < 0){
            return -1;
        }else {
            for (int i = 0; i <= stop ; i++) {
                int j = 0;
                for (; j < pattern.length(); j++) {
                    if (source.charAt(i+j) != pattern.charAt(j)){
                        break;
                    }
                }
                if (j == pattern.length()){
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * next数组的递归写法。
     * @param pattern 待匹配字符串
     * @param number  字符串在第number位匹配失败
     * @param next   特征数组
     * @return 字符串在第number位匹配失败时，应匹配的下一个元素索引。
     */
    private static int next(String pattern, int number, int[] next){
        if (number == 0){
            next[0] = -1;
        }else {
            int tmp = next(pattern,number-1, next);
            while (tmp != -1){
                if (pattern.charAt(number-1) == pattern.charAt(tmp)){
                    next[number] = tmp + 1;
                    break;
                } else{
                    tmp = next(pattern, tmp, next);
                }
            }
            if (tmp == -1)
                next[number] = tmp + 1;
        }
        return next[number];
    }

    /**
     * next数组的非递归写法
     * @param pattern   待匹配字符串
     * @param next next数组
     */
    private static void next(String pattern, int[] next){
        int len = pattern.length();
        next[0] = -1;
        for (int i = 1; i < len; i++)
        {
            int tmp = next[i-1];
            while (tmp != -1) {
                if (pattern.charAt(i-1) == pattern.charAt(tmp)) {
                    next[i] = tmp + 1;
                    break;
                }else {
                    tmp = next[tmp];
                }
            }
            if (tmp == -1){
                next[i] = tmp + 1;
            }

        }
    }

    /**
     * kmp match algorithm
     * @param source
     * @param pattern
     * @return
     */
    public static int kmpMatch(String source, String pattern)
    {
        int[] next = new int[pattern.length()];
        next(pattern,next);
        int i = 0;
        int j = 0;
        while (j < pattern.length() && i < source.length()) {
            if (j == -1 || source.charAt(i) == pattern.charAt(j)){
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j == pattern.length()){
            return i - j;
        }else {
            return -1;
        }
    }
    public static void main(String[] args) {
	// write your code here
        String source = "01234567821245";
        String pattern = "012";
        System.out.println(naiveMatch(source,pattern));
        System.out.println(kmpMatch(source,pattern));
        String pattern1 = "abc  aabbabcabaacbacba";
        int[] next = new int[pattern1.length()];
        next(pattern1,pattern1.length()-1,next);
        for (int i:
             next) {
            System.out.print(i + " ");
        }
        System.out.println();
        next(pattern1,next);
        for (int i:  next) {
            System.out.print(i + " ");
        }
    }
}
