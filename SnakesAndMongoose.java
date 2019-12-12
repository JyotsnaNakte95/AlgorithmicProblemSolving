/**
 * This class finds the minimum number of fences the mongoose needs for snakes
 * to not communicate.
 * @author Jyotsna Nakte
 */
import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Codechef
{
    static Scanner sc = new Scanner(System.in);
    static char[][] matrix;
    static int row;

    public static void main (String[] args) throws java.lang.Exception {
        int n = sc.nextInt();
        for(int i = 0; i < n; i++) {
            row = sc.nextInt();
            matrix = new char[row][2];
            String firstLine = sc.next();
            String secondLine = sc.next();
            int fence = 1;
            int row1 = 0;
            int row2 = 0;
            boolean row1Exist = false;
            boolean row2Exist = false;
// checks if snakes present in both rows
            for(int k = 0; k < firstLine.length(); k++) {
                matrix[k][0] = firstLine.charAt(k);
                matrix[k][1] = secondLine.charAt(k);
                if(matrix[k][0] == '*') {
                    row1++;
                    row1Exist = true;
                }
                if(matrix[k][1] == '*') {
                    row2++;
                    row2Exist = true;
                }
            }
      // loop checks for snakes present in columns      
            if(row1Exist && row2Exist) {
                row1 = 0; row2 = 0;
                for (int k = 0; k < firstLine.length(); k++) {
                    if (matrix[k][0] == '*') {
                        row1++;
                    }
                    if (matrix[k][1] == '*') {
                        row2++;
                    }
                    if (row1 == 2 || row2 == 2) {
                        fence++;
                        row1 = 0;
                        row2 = 0;
                        k--;
                    }
                }
            } else {
                fence = Math.max(row1, row2) - 1;
            }
            if(fence < 0) fence = 0;
            System.out.println(fence);
        }
        sc.close();
    }
}
