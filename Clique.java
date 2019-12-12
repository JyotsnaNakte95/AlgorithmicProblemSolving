/**
 * This class finds the minimum size of the largest clique in any graph with n nodes and m edges.
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

class Clique {

    /*
     * Complete the 'clique' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER m
     */

    // Refering https://en.wikipedia.org/wiki/Tur%C3%A1n_graph ( Turán_graph )
    public static int clique(int n, int m) {
        // Clique cannot be greater than the Number of Nodes in the graph
        // So setting Clique as Number of node + 1
        int low = 0, high = n + 1;
        int currentClique = 0;
        //Binary tree to find number of Clique.
        while(low < high - 1) {
            currentClique = (low + high) / 2;
            // Applying the formula
            int modNodeClique =  n%currentClique;

            // First Part
            int result = (n * (n-1));

            // Ceil
            int ceilDivision = (n + currentClique - 1) / currentClique;
            result -= (modNodeClique * ceilDivision * (ceilDivision - 1));

            // Floor
            int floorDivision = n / currentClique;
            result -= ((currentClique - modNodeClique) * floorDivision * (floorDivision - 1));
            result = result / 2;
            
            if(result < m) {
                low = currentClique;
            } else {
                high = currentClique;
            }
        }
        return high;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        for (int tItr = 0; tItr < t; tItr++) {
            String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            int n = Integer.parseInt(firstMultipleInput[0]);

            int m = Integer.parseInt(firstMultipleInput[1]);

            int result = Result.clique(n, m);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedReader.close();
        bufferedWriter.close();
    }
}
