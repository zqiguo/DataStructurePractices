package com.company;

import java.util.HashMap;

/**
 * 简易计算器，支持正整数 + - * / %。结果返回float类型。
 * 利用后缀表达式计算，先将infix转化成suffix，再计算。
 * Created by zqiguo on 2017/2/20.
 */
public class Suffix {
    private char[] symbolStack;
    private int top;
    private HashMap<Character,Integer> insidePriority;
    private HashMap<Character,Integer> outsidePriority;

    public Suffix(){
        symbolStack = new char[1000];
        top = -1;
        outsidePriority = new HashMap<>();
        insidePriority = new HashMap<>();
        outsidePriority.put('#',0);
        outsidePriority.put(')',1);
        outsidePriority.put('-',2);
        outsidePriority.put('+',2);
        outsidePriority.put('*',4);
        outsidePriority.put('/',4);
        outsidePriority.put('%',4);
        outsidePriority.put('(',6);
        insidePriority.put('#',0);
        insidePriority.put('(',1);
        insidePriority.put('-',3);
        insidePriority.put('+',3);
        insidePriority.put('*',5);
        insidePriority.put('/',5);
        insidePriority.put('%',5);
        insidePriority.put(')',6);
    }

    /**
     *
     * @param infix must start with '#' and end with '#',
     *              for example: "#1+2*3-5*(2+3)#"
     * @return suffix
     */
    public String infixToSuffix(String infix){
        if (infix == "" || infix == null){
            return "";
        }else {
            String result = "";
            if(infix.charAt(0) == '#') {
                symbolStack[++top] = '#';
            }else {
                System.err.println("Must start with '#'!");
                return null;
            }
            String tmp = "";
            try{
                for (int i = 1; i < infix.length(); i++) {
                    tmp = "" + infix.charAt(i);
                    if (tmp.matches("\\d")){
                        result += tmp;
                    }else {
                        while (top != -1){
                            if (outsidePriority.get(tmp.charAt(0)) > insidePriority.get(symbolStack[top])){
                                symbolStack[++top] = tmp.charAt(0);
                                break;
                            }else if (outsidePriority.get(tmp.charAt(0)) == insidePriority.get(symbolStack[top])){
                                top--;
                                break;
                            }else {
                                result += symbolStack[top--] + "";
                            }
                        }
                    }
                }
            }catch(Exception e){
                e.printStackTrace(System.out);
            }
            return result;
        }
    }

    public float calculate(String suffix){
        char[] sf = suffix.toCharArray();
        float[] numberStack = new float[100];
        int top = -1;
        float result = 0;
        for (int i = 0; i < sf.length; i++)
        {
            String tmp = sf[i] + "";
            if (tmp.matches("\\d")){
                numberStack[++top] = Integer.parseInt(tmp);
            }else {
                float rightOperand = numberStack[top--];
                float leftOperand = numberStack[top--];
                result = 0;
                switch (tmp){
                    case "+":
                        result = leftOperand + rightOperand;
                        numberStack[++top] = result;
                        break;
                    case "-":
                        result = leftOperand - rightOperand;
                        numberStack[++top] = result;
                        break;
                    case "*":
                        result = leftOperand * rightOperand;
                        numberStack[++top] = result;
                        break;
                    case "/":
                        result = leftOperand / rightOperand;
                        numberStack[++top] = result;
                        break;
                    case "%":
                        result = leftOperand % rightOperand;
                        numberStack[++top] = result;
                        break;
                    default:
                        break;
                }
            }
        }
        return result;
    }

    public static void main(String[] args){
        Suffix suffix = new Suffix();
        String infix ="#1+2*3-5*(2+3)#";
        System.out.println(suffix.infixToSuffix(infix));
        System.out.println(suffix.calculate(suffix.infixToSuffix(infix)));
    }
}
