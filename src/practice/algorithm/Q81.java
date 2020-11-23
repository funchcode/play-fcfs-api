package practice.algorithm;

/*
정렬된 정수 배열이 주어졌을 때, 주어진 수가 존재하는 배열의 첫 번째 위치와 마지막 위치를 찾으시오.
배열에 찾는 수가 없다면 없다고 출력하세요.


Input:
A = [2, 5, 5, 5, 6, 6, 8, 9, 9, 9]
target = 5
Output:
첫 번째 위치는 인덱스 1
마지막 위치는 인덱스 3

Input:
A = [2, 5, 5, 5, 6, 6, 8, 9, 9, 9]
target = 4
Output:
찾는 값 없음
*/
public class Q81 {
    public static void main(String[] args) {
        int[] input = {2, 5, 5, 5, 6, 6, 8, 9, 9, 9};
        int target = 5;
        int target2 = 4;

        printFirstLastIndex(input, target);
        printFirstLastIndex(input, target2);
    }

    public static void printFirstLastIndex(int[] sorted, int target) {
        int firstIdx = -1;
        int lastIdx = -1;
        int length = sorted.length;
        for (int i = 0 ; i < length ; i++) {
            if (sorted[i] == target) {
                if (firstIdx == -1) {
                    firstIdx = i;
                }
                lastIdx = i;
            } else {
                if (lastIdx != -1) {
                    break;
                }
            }
        }
        if (firstIdx == -1 && lastIdx == -1) {
            System.out.println("찾는 값 없음");
        } else {
            System.out.println("첫 번째 위치는 인덱스 " + firstIdx);
            System.out.println("마지막 위치는 인덱스 " + lastIdx);
        }
    }
}
