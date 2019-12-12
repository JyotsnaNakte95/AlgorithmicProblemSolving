/**
 * The class finds the shortest distance from source to other cities/nodes.
 * @author Jyotsna Nakte
 */
import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class RustAndMuderer {

    /*
     * Complete the rustMurdered function below.
     */
    static int[] rustMurdered(int s, int n, int[][] roads) {
        int[] result = new int[n + 1];
        boolean[] visited = new boolean[n + 1];
        List[] graph = new ArrayList[n + 1];

        int count = n;
        int distance = 1;
        for(int i = 0; i <= n; i++) {
            graph[i] = new ArrayList();
        }
        for(int i = 0; i < roads.length; i++) {
            graph[roads[i][1]].add(roads[i][0]);
            graph[roads[i][0]].add(roads[i][1]);
        }
        LinkedList<Integer> queue = new LinkedList();
        queue.add(s);
        int past = s;
        while(count > 0 && !queue.isEmpty()) {
            int current = queue.pop();
            for(int i = 1; i <= n; i++) {
                if(!graph[current].contains(i) && !visited[i] && i != current) {
                    result[i] = distance;
                    visited[i] = true;
                    count --;
                    queue.add(i);
                }
            }
            if(past == current) {
                distance++;
                past = queue.getLast();
            }
        }

        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(scanner.nextLine().trim());

        for (int tItr = 0; tItr < t; tItr++) {
            String[] nm = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nm[0].trim());

            int m = Integer.parseInt(nm[1].trim());

            int[][] roads = new int[m][2];

            for (int roadsRowItr = 0; roadsRowItr < m; roadsRowItr++) {
                String[] roadsRowItems = scanner.nextLine().split(" ");

                for (int roadsColumnItr = 0; roadsColumnItr < 2; roadsColumnItr++) {
                    int roadsItem = Integer.parseInt(roadsRowItems[roadsColumnItr].trim());
                    roads[roadsRowItr][roadsColumnItr] = roadsItem;
                }
            }

            int s = Integer.parseInt(scanner.nextLine().trim());

            int[] result = rustMurdered(s, n, roads);

            for (int resultItr = 1; resultItr < result.length; resultItr++) {
                if(resultItr == s) continue;
                bufferedWriter.write(String.valueOf(result[resultItr]));

                if (resultItr != result.length - 1) {
                    bufferedWriter.write(" ");
                }
            }

            bufferedWriter.newLine();
        }

        bufferedWriter.close();
    }
}
