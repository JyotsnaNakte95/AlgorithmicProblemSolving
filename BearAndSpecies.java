/**
 * This class finds the number of ways the bears can be placed in the cells so
 * they do not fight amongst them.
 * @author Jyotsna Nakte
 */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class BearAndSpecies
{
    static Scanner sc = new Scanner(System.in);
    static char[][] graph;
    static boolean[][] visited;
    static int sizeOfGraph;
    static int countG, countB, countP, countQ, totalCount;

    public static boolean ifOutOfBound(int x, int y) {
        return x == sizeOfGraph || x == -1 || y == sizeOfGraph || y == -1 || graph[x][y] == '.' || visited[x][y] ;
    }

    public static void dfs(int x, int y) {
        totalCount ++;
        visited[x][y] = true;

        if(graph[x][y] == 'B') countB++;
        if(graph[x][y] == 'G') countG++;
        if(graph[x][y] == 'P') countP++;
        if(graph[x][y] == '?') countQ++;

        if(!ifOutOfBound(x + 1, y)) {
            dfs(x+1, y);
        }
        if(!ifOutOfBound(x - 1, y)) {
            dfs(x-1, y);
        }
        if(!ifOutOfBound(x, y - 1)) {
            dfs(x, y-1);
        }
        if(!ifOutOfBound(x, y + 1)) {
            dfs(x, y+1);
        }

    }

    public static int returnCount(int j, int k) {
        countG = 0;
        countB = 0;
        countP = 0;
        countQ = 0;
        totalCount = 0;
        dfs(j, k);
        if(totalCount == 1) {
            if(countQ > 0) return 3;
            else return 1;
        }
        if(countG > 0 || (countB > 0 && countP > 0)) return 0;
        if(countQ > 0 && !(countB > 0 || countP > 0)) return 2;
        return 1;
    }

    public static void main (String[] args) throws java.lang.Exception
    {
        int n = sc.nextInt();
        for(int i = 0; i < n; i++) {
            sizeOfGraph = sc.nextInt();
            graph = new char[sizeOfGraph][sizeOfGraph];
            visited = new boolean[sizeOfGraph][sizeOfGraph];
            for(int j = 0; j < sizeOfGraph; j++) {
                String currentInput = sc.next();
                char[] currentInputArray = currentInput.toCharArray();
                for(int k = 0; k < currentInputArray.length; k++) {
                    graph[j][k] = currentInputArray[k];
                }
            }
            long result = 1;
            for(int j = 0; j < sizeOfGraph; j++) {
                for(int k = 0; k < sizeOfGraph; k++) {
                    if(!ifOutOfBound(j,k)) {
                        int temp = returnCount(j,k);
                        result = (result * temp) % 1000000007;
                        if(result == 0) break;
                    }
                }
            }
            System.out.println(result);
        }
        sc.close();
    }
}
