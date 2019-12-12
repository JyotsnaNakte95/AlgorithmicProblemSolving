/**
 * The class finds shortest path between all nodes
 * @author Jyotsna Nakte
 */

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class FloydCity {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[] roadNodesEdges = scanner.nextLine().split(" ");
        int roadNodes = Integer.parseInt(roadNodesEdges[0].trim());
        int roadEdges = Integer.parseInt(roadNodesEdges[1].trim());

        int[] roadFrom = new int[roadEdges];
        int[] roadTo = new int[roadEdges];
        int[] roadWeight = new int[roadEdges];

        for (int i = 0; i < roadEdges; i++) {
            String[] roadFromToWeight = scanner.nextLine().split(" ");
            roadFrom[i] = Integer.parseInt(roadFromToWeight[0].trim());
            roadTo[i] = Integer.parseInt(roadFromToWeight[1].trim());
            roadWeight[i] = Integer.parseInt(roadFromToWeight[2].trim());
        }

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");


        long[][] distance = new long[roadNodes + 1][roadNodes + 1];

        for(int i = 1; i <= roadNodes; i++) {
            for(int j = 1; j <= roadNodes; j++) {
                if(i != j)
                    distance[i][j] = Integer.MAX_VALUE;
            }
        }

        for(int i = 0; i < roadFrom.length; i++) {
            distance[roadTo[i]][roadFrom[i]] = roadWeight[i];
        }

        for(int i = 1; i <= roadNodes; i++) {
            for(int j = 1; j <= roadNodes; j++) {
                for(int k = 1; k <= roadNodes; k++) {
                    distance[j][k] = Math.min(distance[j][i] + distance[i][k], distance[j][k]);
                }
            }
        }

        for (int qItr = 0; qItr < q; qItr++) {
            String[] xy = scanner.nextLine().split(" ");

            int x = Integer.parseInt(xy[0]);

            int y = Integer.parseInt(xy[1]);

            if(distance[y][x] == Integer.MAX_VALUE) distance[y][x] = -1;
                System.out.println(distance[y][x]);
        }

        scanner.close();
    }
}
