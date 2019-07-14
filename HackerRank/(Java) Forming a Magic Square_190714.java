//https://www.hackerrank.com/challenges/magic-square-forming/problem

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the formingMagicSquare function below.
    static int formingMagicSquare(int[][] s) {
        //3x3 마방진의 갯수는?
        //8 1 6
        //3 5 7
        //4 9 2
        
        int minCost = 72;

        List<List<Integer>> magicSquare = makeMagicSquare();
        for (List<Integer> square : magicSquare) {
            int cost = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    cost += Math.abs(s[i][j] - square.remove(0));
                }
            }
            if(cost < minCost) {
                minCost = cost;
            }
        }

        return minCost;
    }

    static List<List<Integer>> makeMagicSquare() {
        //가운데 5는 고정, 8개의 숫자순열
        int arr[] = {1, 2, 3, 4, 6, 7, 8, 9};
        List<List<Integer>> permList = new ArrayList<>();
        perm(permList, arr, 0);

        for (List<Integer> a : permList) {
            a.add(4, 5);
        }

        //가로, 세로, 대각선 합이 15인 행렬만 추가
        List<List<Integer>> magicSquare = new ArrayList<>();
        for (List<Integer> a : permList) {
//            if ((3 * (a.get(0) + a.get(2) + a.get(6) + a.get(8))) +
//                    (2 * (a.get(1) + a.get(3) + a.get(5) + a.get(7))) + (4 * a.get(4)) == 120) {
//                magicSquare.add(a);
//            }
            if ((a.get(0) + a.get(1) + a.get(2)) == 15 &&
                    (a.get(3) + a.get(4) + a.get(5)) == 15 &&
                    (a.get(6) + a.get(7) + a.get(8)) == 15 &&
                    (a.get(0) + a.get(3) + a.get(6)) == 15 &&
                    (a.get(1) + a.get(4) + a.get(7)) == 15 &&
                    (a.get(2) + a.get(5) + a.get(8)) == 15 &&
                    (a.get(0) + a.get(4) + a.get(8)) == 15 &&
                    (a.get(2) + a.get(4) + a.get(6)) == 15) {
                magicSquare.add(a);
            }
        }
        return magicSquare;
    }

    static void perm(List<List<Integer>> permList, int[] arr, int pivot) {
        if (pivot == arr.length) {

            List<Integer> a = new ArrayList<>();
            for (int i = 0; i < arr.length; i++) {
                a.add(arr[i]);
            }
            permList.add(a);
            return;
        }
        for (int i = pivot; i < arr.length; i++) {
            swap(arr, i, pivot);
            perm(permList, arr, pivot + 1);
            swap(arr, i, pivot);
        }
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int[][] s = new int[3][3];

        for (int i = 0; i < 3; i++) {
            String[] sRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 3; j++) {
                int sItem = Integer.parseInt(sRowItems[j]);
                s[i][j] = sItem;
            }
        }

        int result = formingMagicSquare(s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
