/**
 * This class finds if there exists any arrangement such that demands of each robber is     * satisfied and also they are not caught.
 *
 * @author Jyotsna Nakte
 */

import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class TheIndianJob {

    /*
     * Complete the indianJob function below.
     */
    static String indianJob(int g, int[] arr) {

        // Application of set-into-two-subsets-such-that-the-difference-of-subset-sums-is-minimum

        // If max[A[i]] * Number of thief <= G
        if (g >= 10000) return "YES";
    

        // IsSumExist is array where if j sum can be acheived from i = 0 to ith Element;
        boolean[][] isSumExist = new boolean[arr.length + 1][g+1];

        int sum = 0;
        for(int i = 0; i<= arr.length; i++) {
            isSumExist[i][0] = true;
            if(i < arr.length) sum += arr[i];
        }
        int maxDiff = -1;

        for(int i = 1; i<= arr.length; i++) {
            for(int j = 0; j<= g; j++) {
                // If i is included
                if(j >= arr[i-1]) {
                    isSumExist[i][j] = isSumExist[i-1][j] || isSumExist[i-1][j - arr[i-1]];
                } else {
                    // If i is not included
                    isSumExist[i][j] = isSumExist[i-1][j];
                }
                if(isSumExist[i][j]) {
                    maxDiff = sum - j;
                }
            }
        }
        if(maxDiff != -1 && maxDiff <= g) return "YES";
        return "NO";

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(scanner.nextLine().trim());

        for (int tItr = 0; tItr < t; tItr++) {
            String[] ng = scanner.nextLine().split(" ");

            int n = Integer.parseInt(ng[0].trim());

            int g = Integer.parseInt(ng[1].trim());

            int[] arr = new int[n];

            String[] arrItems = scanner.nextLine().split(" ");

            for (int arrItr = 0; arrItr < n; arrItr++) {
                int arrItem = Integer.parseInt(arrItems[arrItr].trim());
                arr[arrItr] = arrItem;
            }

            String result = indianJob(g, arr);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
    }
}
