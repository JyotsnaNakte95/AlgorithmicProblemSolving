/* package codechef; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class HardestSumValue
{
    static Scanner sc = new Scanner(System.in);
    static int[][] matrix, northMatrix, southMatrix, eastMatrix, westMatrix;
    static int row, column;

    public static void main (String[] args) throws java.lang.Exception {
        int n = sc.nextInt();
        for(int i = 0; i < n; i++) {
            row = sc.nextInt();
            column = sc.nextInt();
            matrix = new int[row][column];
            northMatrix = new int[row][column];
            southMatrix = new int[row][column];
            eastMatrix = new int[row][column];
            westMatrix = new int[row][column];
            for(int k = 0; k < row; k++) {
                for(int j = 0; j < column; j++) {
                    matrix[k][j] = sc.nextInt();
                }
            }

            for(int k = 0; k < row; k++) {
                eastMatrix[k][0] = matrix[k][0];
                westMatrix[k][column - 1] = matrix[k][column - 1];
                for(int j = 1; j < column; j++) {
                    eastMatrix[k][j] = matrix[k][j] + Math.min(0, eastMatrix[k][j - 1]);
                    westMatrix[k][column - 1 - j] = matrix[k][column - 1 - j] + Math.min(0, westMatrix[k][column - 1 - j + 1]);
                }
            }

            for(int k = 0; k < column; k++) {
                southMatrix[0][k] = matrix[0][k];
                northMatrix[row - 1][k] = matrix[row - 1][k];
                for(int j = 1; j < row; j++) {
                    southMatrix[j][k] = matrix[j][k] + Math.min(0, southMatrix[j - 1][k]);
                    northMatrix[row - 1 - j][k] = matrix[row - 1 - j][k] + Math.min(0, northMatrix[row - 1 - j + 1][k]);
                }
            }

            long result = Integer.MAX_VALUE;
            for(int k = 0; k < row; k++) {
                for(int j = 0; j < column; j++) {
                    result = Math.min(result, northMatrix[k][j] + southMatrix[k][j] + eastMatrix[k][j] + westMatrix[k][j] - 3 * matrix[k][j]);
                }
            }
            System.out.println(result);
        }
        sc.close();
    }
}
