/**
 * This class finds how many maximum-length palindromes present in the given string.
 * @author Jyotsna Nakte
 */
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class MaximumPalindrome {

    static String u;
    static long[] fact;
    static long[] inv;
    static int[][] sizeToCountOfWord;
    static int mod = 1000000007;
    public static long modularInverse(long den, long y) {
        if (y == 0) return 1; 
        long p = modularInverse(den, y / 2) % mod; 
        if (y % 2 == 0) return (p * p) % mod; 
        else return (den * ((p * p) % mod)) % mod; 
    }
    /*
     * Complete the 'initialize' function below.
     *
     * The function accepts STRING s as parameter.
     */
    public static void initialize(String s) {
        u = s;
        fact = new long[s.length()];
        inv = new long[s.length()];
        sizeToCountOfWord = new int[s.length()][26];

        fact[0] = 1;
        inv[0] = modularInverse(fact[0], mod - 2);
        sizeToCountOfWord[0][s.charAt(0) - 'a'] += 1;

        for(int i = 1; i < s.length(); i++) {
            fact[i] = (fact[i-1] * i) % mod;
            
            for(int j = 0; j < 26; j++) {
                sizeToCountOfWord[i][j] = sizeToCountOfWord[i - 1][j];
            }
            sizeToCountOfWord[i][s.charAt(i) - 'a'] += 1;
        }
        inv[s.length() - 1] = modularInverse(fact[s.length() - 1], mod - 2);
        for(int i = s.length() - 2; i >= 0; i--) {
            inv[i] = inv[i+1]*(i+1)%mod;
        }
    }
    public static int answerQuery(int l, int r) {
        if( r == l ){
            return 1;
        }
        int k = 0; 
        int num = 0; 
        long den = 1; 
        boolean allUnique = true; 
        int countUnique = 0; 
        for(int j = 0; j < 26; j++) {
            int currentCount = sizeToCountOfWord[r - 1][j];
            if(l > 1)  currentCount -= sizeToCountOfWord[l - 2][j];
            if(currentCount > 1) {
                allUnique = false;
            }
            if(currentCount == 1) {
                countUnique += 1;
            }
            if (currentCount % 2 != 0) {
                currentCount--;
                k++; 
            } 
            currentCount = currentCount / 2;
            num = num + currentCount;  
            den = (den * inv[currentCount]) % mod;
        } 
        if(allUnique) return countUnique;
        long ans = fact[num]; 
        k = Math.max(k, 1);
        return (int) (((ans * den) % mod) * k % mod);
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        Result.initialize(s);

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        for (int qItr = 0; qItr < q; qItr++) {
            String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            int l = Integer.parseInt(firstMultipleInput[0]);

            int r = Integer.parseInt(firstMultipleInput[1]);

            int result = Result.answerQuery(l, r);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedReader.close();
        bufferedWriter.close();
    }
}
