/**
 * The class finds the largest possible subset of intervals such that no three intervals in the subset share a common point..
 * @author Jyotsna Nakte
 */
import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class IntervalSelection {

    static class Node {
        int start;
        int end;
        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    /*
     * Complete the intervalSelection function below.
     */
    static int intervalSelection(int[][] intervals) {

        Node firstInterval = new Node(0,0);
        Node secondInterval = new Node(0,0);
        int output = 0;
        Arrays.sort(intervals, Comparator.comparingInt((int[] a) -> a[1]));
        for(int i = 0; i < intervals.length; i++) {
            Node currentInterval = new Node(intervals[i][0], intervals[i][1]);
            if(currentInterval.start > firstInterval.end) {
                output++;
                firstInterval = currentInterval;
                continue;
            }
            if(currentInterval.start > secondInterval.end) {
                output++;
                // Check the end
                if(currentInterval.end > firstInterval.end) {
                    secondInterval = firstInterval;
                    firstInterval = currentInterval;
                } else {
                    secondInterval = currentInterval;
                }
                continue;
            }
        }
        return output;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int s = Integer.parseInt(scanner.nextLine().trim());

        for (int sItr = 0; sItr < s; sItr++) {
            int n = Integer.parseInt(scanner.nextLine().trim());

            int[][] intervals = new int[n][2];

            for (int intervalsRowItr = 0; intervalsRowItr < n; intervalsRowItr++) {
                String[] intervalsRowItems = scanner.nextLine().split(" ");

                for (int intervalsColumnItr = 0; intervalsColumnItr < 2; intervalsColumnItr++) {
                    int intervalsItem = Integer.parseInt(intervalsRowItems[intervalsColumnItr].trim());
                    intervals[intervalsRowItr][intervalsColumnItr] = intervalsItem;
                }
            }

            int result = intervalSelection(intervals);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
    }
}
