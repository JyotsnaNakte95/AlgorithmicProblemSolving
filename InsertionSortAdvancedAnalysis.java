/**
 * This class finds the number of shifts insertion sort takes to sort an array.
 *
 * @author Jyotsna Nakte
 */

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class InsertionSortAdvancedAnalysis {
    static long count = 0;

    static void merge(int[] arr, int left, int right, int mid) {
        int[] leftTemp = new int[mid - left + 1];
        int[] rightTemp = new int [right - mid];
        int leftIndex = 0, rightIndex = 0, k = left;
        for(int i = 0; i < leftTemp.length; i++) {
            leftTemp[i] = arr[i + left];
        }
        for(int i = 0; i < rightTemp.length; i++) {
            rightTemp[i] = arr[i + mid + 1];
        }
        int countForLeft = 0;
        while(leftIndex < leftTemp.length && rightIndex < rightTemp.length) {
            if(leftTemp[leftIndex] <= rightTemp[rightIndex]) {
                arr[k++] = leftTemp[leftIndex++];
                countForLeft ++;
            } else {
                count += leftTemp.length - countForLeft;
                arr[k++] = rightTemp[rightIndex++];
            }
        }

        while(leftIndex < leftTemp.length) {
            arr[k++] = leftTemp[leftIndex++];
        }
        while(rightIndex < rightTemp.length) {
            arr[k++] = rightTemp[rightIndex++];
        }
    }

    static int sort(int[] arr, int left, int right) {
        if(right <= left) return 0;
        int mid = (left + right) / 2;
        sort(arr, left, mid);
        sort(arr, mid + 1, right);
        merge(arr, left, right, mid);
        return 0;
    }

    // Complete the insertionSort function below.
    static long insertionSort(int[] arr) {
        count = 0;
        sort(arr, 0, arr.length - 1);
        return count;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] arr = new int[n];

            String[] arrItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int arrItem = Integer.parseInt(arrItems[i]);
                arr[i] = arrItem;
            }

            long result = insertionSort(arr);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
