import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class SuffixRotation {


    private static String[] StringWithAlphabet;
    private static int[] sizeOfString;

    private static int[][] visited;
    private static int[][] shifts;
    private static final Scanner scanner = new Scanner(System.in);

    public static int dp(int character, int position) {
        int chunk = 0;
        String currentString = StringWithAlphabet[character];
        int length = StringWithAlphabet[character].length();
        if(sizeOfString[character] == 0) return  dp(character + 1, position);
        if(sizeOfString[character] == length) return chunk;
        if(visited[character][position] == 1) return shifts[character][position];
        int countOfCharacter[] = new int[length + 1];
        int index = 1;
        int start = 0, finish = length - 1;
        List<Integer> chunkStart = new ArrayList();
        List<Integer> chunkEnd = new ArrayList();
        for(char currentChar : currentString.toCharArray()) {
            if(currentChar == (char)(character + 'a')) {
                countOfCharacter[index] = countOfCharacter[index - 1] + 1;
            } else {
                countOfCharacter[index] = countOfCharacter[index - 1];
            }
            index++;
        }
        boolean finishAtEnd = false;
        if(currentString.charAt(finish) == (char)(character + 'a') ) {
            while (currentString.charAt(finish) == (char)(character + 'a') ) finish--;
            while (currentString.charAt(start)  == (char)(character + 'a') ) start ++;
            chunkStart.add(start);
            chunkEnd.add((finish + 1)%length);
            start++;
            chunk++;
            finishAtEnd = true;
            if(chunkEnd.get(0) == position) {
                chunk --;
                finishAtEnd = false;
            }
        }

        for(int i = start; i <= finish; i++) {
 
            if(currentString.charAt(i) == (char)(character + 'a') && currentString.charAt((i+length-1)%length) != (char)(character + 'a')) {
                chunkStart.add(i);
                if(i == position) chunk --;
            }
            if(currentString.charAt(i) != (char)(character + 'a') && currentString.charAt((i+length-1)%length) == (char)(character + 'a')) {
                chunkEnd.add(i);
                chunk++;
            }
        }
        int result = 100000;

        if(chunkEnd.size() <= 1) {
            visited[character][position] = 1;
            if(chunkStart.get(0) == start - 1 && chunkEnd.get(0) == ((finish + 1)%length)) {
                result = chunk + dp(character + 1, 0);
            } else {
                result = chunk + dp(character + 1, (chunkEnd.get(0) - countOfCharacter[chunkEnd.get(0) + 1])%(length-1));
            }
        } else {
            for(int i = 0; i < chunkEnd.size(); i++) {
                if((chunkEnd.get(i) != ((finish + 1)%length) && chunkStart.get(i) == position) || (i == 0 && chunkStart.get(i) == start - 1 && chunkEnd.get(i) == ((finish + 1)%length) && chunkEnd.get(i) == position)) {
                    continue;
                }
                if(i == 0 && chunkStart.get(i) == start - 1 && chunkEnd.get(i) == ((finish + 1)%length)) {
                    result = Math.min(result, chunk + dp(character + 1, 0));
                } else {
                    result = Math.min(result, chunk + dp(character + 1, (chunkEnd.get(i) - countOfCharacter[chunkEnd.get(i) + 1])%(length-1)));
                }
            }
        }
        visited[character][position] = 1;
        shifts[character][position] = result;
        return shifts[character][position];
    }
    public static void main(String[] args) {
        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s = scanner.nextLine();
            StringWithAlphabet = new String[26];
            sizeOfString = new int[26];
            visited = new int[27][s.length() + 1];
            shifts = new int[27][s.length() + 1];

            for(int i = 0; i < s.length(); i++) {
                if(s.charAt(i) == 'a') {
                    sizeOfString[0] ++;
                }
            }
            StringWithAlphabet[0] = s;
            for(int i = 1; i < 26; i++) {
                StringBuilder stringBuilder = new StringBuilder();
                for(int j = 0; j < StringWithAlphabet[i-1].length(); j++) {
                    if(StringWithAlphabet[i-1].charAt(j) == ('a' + i)) {
                        sizeOfString[i]++;
                    }
                    if(StringWithAlphabet[i-1].charAt(j) != ('a' + i - 1)) {
                        stringBuilder.append(StringWithAlphabet[i-1].charAt(j));
                    }

                }
                StringWithAlphabet[i] = stringBuilder.toString();
            }
            System.out.println(dp(0,0));
        }
        scanner.close();
    }
}
