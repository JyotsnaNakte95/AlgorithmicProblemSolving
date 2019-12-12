/**
 * This class finds the set of substrings of the given string arranges in lexicographical   
 *  order, concatenates them and returns the Kth character.
 *
 * @author Jyotsna Nakte
 */

import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class AshtonAndString {
    /*
     * Complete the ashtonString function below.
     */
    static char ashtonString(String s, int k) {
        char result = 'a';
        boolean found = false;
        int size = 0;
        // Going lexicographically
        for(char currentChar = 'a'; currentChar <= 'z'; currentChar++) {
            List<String> setOfString = new ArrayList<>();
            //find set of substrings
            for(int i = 0; i < s.length(); i++) {
                char currentCharInS = s.charAt(i);
                if(currentCharInS == currentChar) {
                    setOfString.add(s.substring(i));
                }
            }
            if(setOfString.isEmpty()) continue;
            // sorting for lexicographic order
            Collections.sort(setOfString);
            for(int i = 0; i < setOfString.size(); i++) {
                String currentString = setOfString.get(i);
                for(int currentLength = 1; currentLength <= currentString.length(); currentLength++) {
                    String prevString = "";
                    boolean foundSubString = false;
                    //finds the strings to concatenate
                    for (int j = 0; j < i; j++) {
                        prevString = setOfString.get(j);
                        if(prevString.length() < currentLength || currentString.length() < currentLength) continue;
                        if (prevString.substring(0, currentLength).equals(currentString.substring(0, currentLength))) {
                            foundSubString = true;
                            break;
                        }
                    }
                    if(foundSubString) continue;
                    // keeping track of size
                    size += currentLength;
                    //formed the k size string finds the k and returns result
                    if (size >= k) {
                        found = true;
                        result = currentString.charAt(currentLength - (size - k) - 1);
                        break;
                    }
                }
                if(found) break;
            }
            if(found) break;
        }

        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(scanner.nextLine().trim());

        for (int tItr = 0; tItr < t; tItr++) {
            String s = scanner.nextLine();

            int k = Integer.parseInt(scanner.nextLine().trim());

            char res = ashtonString(s, k);

            bufferedWriter.write(String.valueOf(res));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
    }
}

