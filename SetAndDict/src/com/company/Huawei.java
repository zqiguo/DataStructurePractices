package com.company;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Stack;
/**
 * Created by qiguo on 2017/3/19.
 */
public class Huawei {
    public static void main(String[] args){
//        Scanner scanner = new Scanner(System.in);
//        String input = "";
//        while (scanner.hasNextLine()){
//            input = scanner.nextLine();
//            String result = "";
//            for (int i = 0; i < input.length(); i++){
//                String tmp = input.charAt(i) + "";
//                if (tmp.matches("[A-Za-z]")){
//                    result += tmp.toLowerCase();
//                }
//            }
//            System.out.println(result);
//        }
//
//        Scanner scanner = new Scanner(System.in);
//        ArrayList<String> input = new ArrayList<>();
//        System.out.println("Please input: ");
//        String consoleInput = "";
//        while (scanner.hasNextLine() && !(consoleInput=scanner.nextLine()).equals("")){
//            input.add(consoleInput);
//        }
//        int[] count = new int[5];
//        for (String str : input){
//            for (int i = 0; i < 5; i++) {
//                if (str.charAt(i) == '1'){
//                    count[i] += 1;
//                }
//            }
//        }
//        int result = count[0];
//        for(int i : count){
//            if (result > i){
//                result = i;
//            }
//        }
//        System.out.println(result);
//        scanner.close();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine().trim();
            Stack<Integer> stack = new Stack<>();
            for (char c : input.toCharArray()) {
                if (c >= '0' && c <= '9') {
                    stack.push(c - '0');
                } else if (c >= 'A' && c <= 'F') {
                    stack.push(c - 'A' + 10);
                } else {
                    int right = stack.pop();
                    int left = stack.pop();
                    switch (c) {
                        case '+':
                            stack.push(left + right);
                            break;
                        case '-':
                            stack.push(left - right);
                            break;
                        case '*':
                            stack.push(left * right);
                            break;
                        default:
                            break;
                    }
                }
            }
            System.out.println(stack.pop());


        }
    }
}
