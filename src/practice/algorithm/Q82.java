package practice.algorithm;

import java.util.Arrays;

/*
순환 정수 배열이 주어졌을 때, 합이 최대가 되는 부분 배열을 구하시오.

Input: [2, 1, -5, 4, -3, 1, -3, 4, -1]
Output: 부분 배열 [4, -1, 2, 1], 합 6

Input: [-3, 1, -3, 4, -1, 2, 1, -5, 4]
Output: 부분 배열 [4, -1, 2, 1], 합 6
 */
public class Q82 {
    public static void main(String[] args) {
        //int[] input1 = {2, 1, -5, 4, -3, 1, -3, 4, -1};
        int[] input1 = {-3, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] best = new int[input1.length];
        int bestSum = 0;
        int len = input1.length;

        for (int i = 0 ; i < len ; i++) {
            int d = i;
            int[] current = new int[input1.length];
            int idx = 0;
            int currentSum = 0;

            for (int j = 0 ; j < len -1 ; j ++) {
                System.out.println("this >> " + d + " : > " + input1[d]);

                current[idx++] = input1[d];
                currentSum = currentSum + input1[d];
                if (currentSum > bestSum) {
                    bestSum = currentSum;
                    best = Arrays.copyOf(current, idx);
                }
                d = (d + 1) == len ? 0 : d + 1;
            }
        }
        System.out.println(bestSum);
        System.out.println(Arrays.toString(best));
    }
}
