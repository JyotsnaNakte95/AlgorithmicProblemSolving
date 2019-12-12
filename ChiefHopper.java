/**
 * This class helps to find minimum bot Energy to start with, in order to reach the end of  * the number line buildings.
 * @author Jyotsna Nakte
 */

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class ChiefHopper {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        long energy = 0;
        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        for(int i = n - 1; i >= 0; i--) {
            energy = (arr[i] + energy) % 2 == 0 ? (arr[i] + energy) / 2 : (arr[i] + energy + 1) / 2;
        }

        bufferedWriter.write(String.valueOf(energy));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
