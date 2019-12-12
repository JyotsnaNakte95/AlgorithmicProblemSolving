/**
 * This class helps to find if a string is rotated from left to rightmost corner, to check if the string and rotated string are same.
 * @author Jyotsna Nakte
 */

import java.util.Arrays;
// Jyotsna Nakte
class Rotate {
    public boolean rotateString(String A, String B) {
        if(A.length() != B.length()) return false;
        if(A.length() == 0) return true;
        final String text = A + A;
        int length = 0, index = 1;
        // KMP Algo
        int[] lps = new int[A.length()];
        while(index < B.length()) {
            if(B.charAt(index) != B.charAt(length)) {
                if(length == 0) lps[index] = 0;
                else {
                    lps[index] = 0;
                    while (length != 0) {
                        length = lps[length - 1];
                        if(B.charAt(index) == B.charAt(length)) {
                            lps[index] = ++length;
                            break;
                        }
                    }
                }
            } else {
                lps[index] = ++length;
            }
            index ++;
        }
        int textIndex = 0, bIndex = 0;
        String combineA = A + A;
        while(textIndex < combineA.length()) {
            if(combineA.charAt(textIndex) == B.charAt(bIndex)) {
                bIndex++; textIndex++;
            }
            if(bIndex == B.length()) return true;
            while(textIndex < combineA.length() && combineA.charAt(textIndex) != B.charAt(bIndex)) {
                if(bIndex == 0) textIndex++;
                else bIndex = lps[bIndex - 1];
            }
        }


        return false;
    }
}