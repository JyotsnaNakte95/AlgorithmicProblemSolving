/**
 * The class finds the least number of rolls to reach the 100th square
 * @author Jyotsna Nakte
 */
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class SnakeLadder {

    // Complete the quickestWayUp function below.
    static int quickestWayUp(int[][] ladders, int[][] snakes) {
        int count = 0;
        int[] fromLadder = new int[101];
        int[] fromSnakes = new int[101];
        int[] minMoves = new int[101];
        for(int i = 0; i < ladders.length; i++) 
            fromLadder[ladders[i][0]] = ladders[i][1];
        for(int i = 0; i < snakes.length; i++) 
            fromSnakes[snakes[i][0]] = snakes[i][1];
        LinkedList<Integer> queue = new LinkedList();
        queue.add(1);
        boolean finished = false;
        while(!finished && !queue.isEmpty()) {
            if(queue.peek() > 93) {
                break;
            }
            int current = queue.pop();
            for(int i  = 1; i <= 6; i++) {
                int nextPoint = fromLadder[current + i] != 0 ? fromLadder[current + i] : current + i;
                nextPoint = fromSnakes[current + i] != 0 ? fromSnakes[current + i] : nextPoint;
                if(minMoves[nextPoint] == 0) {
                    queue.add(nextPoint);
                    minMoves[nextPoint] = minMoves[current] + 1;
                }}}
        if(queue.isEmpty()) return -1;
        int remaining = queue.pop();
        if(remaining != 100) {
            minMoves[remaining] ++;
        }        
        return  minMoves[remaining];
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[][] ladders = new int[n][2];

            for (int i = 0; i < n; i++) {
                String[] laddersRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 2; j++) {
                    int laddersItem = Integer.parseInt(laddersRowItems[j]);
                    ladders[i][j] = laddersItem;
                }
            }

            int m = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[][] snakes = new int[m][2];

            for (int i = 0; i < m; i++) {
                String[] snakesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 2; j++) {
                    int snakesItem = Integer.parseInt(snakesRowItems[j]);
                    snakes[i][j] = snakesItem;
                }
            }

            int result = quickestWayUp(ladders, snakes);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
