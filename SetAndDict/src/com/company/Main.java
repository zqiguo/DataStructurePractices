package com.company;
import java.util.Scanner;
import java.util.HashSet;
import java.util.ArrayList;
/**
 * Created by qiguo on 2017/3/18.
 */
public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String[] input = new String[3];
        int i = 0;
        while (scanner.hasNextLine()){
            input[i] = scanner.nextLine();
            i++;
        }
        if (input[0].equals("")){
            System.out.println(0);
        }
        String[] size = input[0].trim().split(" ");
        int minSize = Integer.parseInt(size[0]);
        int maxSize = Integer.parseInt(size[1]);
        int numOfFish = Integer.parseInt(input[1]);
        String[] fish = input[2].trim().split(" ");
        int[] fishSize = new int[fish.length];
        for (int j = 0; j < fish.length; j++) {
            fishSize[j] = Integer.parseInt(fish[j]);
        }

        if (numOfFish != fishSize.length){
            System.out.println(0);
        }

        String result = "";
        HashSet<Integer> hashSet = new HashSet<Integer>();
        for(int j : fishSize){
            for (int k = 2; k <= 10; k++) {
                hashSet.add(j * k);
                if (j % k == 0){
                    hashSet.add((j / k));
                }
            }
        }
        for (int j = minSize; j <= maxSize ; j++) {
            if (!hashSet.contains(j)){
                for (int k = 2; k <= 10; k++) {
                    hashSet.add(j * k);
                    if (j % k == 0){
                        hashSet.add((j / k));
                    }
                }
                result += (j + " ");
            }

        }
        System.out.println(result);



    }
}
