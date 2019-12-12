/**
 * This class finds the the number of ways to make the first move such that under optimal   * play, the first player always wins.
 *
 * @author Jyotsna Nakte
 */

import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class ChocolateInBox {

    /*
     * Complete the chocolateInBox function below.
     */
    static int chocolateInBox(int[] arr) {
        // Ref : https://en.wikipedia.org/wiki/Nim
        int finalXor = arr[0];
        for(int currentStoneInHeap = 1; currentStoneInHeap < arr.length; currentStoneInHeap++) {
            finalXor ^= arr[currentStoneInHeap];
        }
        if(finalXor == 0) return 0;

        int result = 0;
        for(int currentStoneInHeap = 0; currentStoneInHeap < arr.length; currentStoneInHeap++) {
            if((finalXor ^ arr[currentStoneInHeap]) < arr[currentStoneInHeap]) {
                result ++;
            }
        }
        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int arrCount = Integer.parseInt(scanner.nextLine().trim());

        int[] arr = new int[arrCount];

        String[] arrItems = scanner.nextLine().split(" ");

        for (int arrItr = 0; arrItr < arrCount; arrItr++) {
            int arrItem = Integer.parseInt(arrItems[arrItr].trim());
            arr[arrItr] = arrItem;
        }

        int result = chocolateInBox(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();
    }
}
