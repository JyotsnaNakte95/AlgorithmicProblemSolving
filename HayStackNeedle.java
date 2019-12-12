/**
 * This class helps find if the pattern exist in string and returns the index of string where the pattern is found.
 * @author Jyotsna Nakte
 */

import java.util.Arrays;
//Jyotsna Nakte
class HayStackNeedle {
    public int strStr(String haystack, String needle) {
        if(needle.length() == 0) return 0;

        int length = 0, index = 1;
        // KMP Algo
        int[] lps = new int[needle.length()];
        while(index < needle.length()) {
            if(needle.charAt(index) != needle.charAt(length)) {
                if(length == 0) lps[index] = 0;
                else {
                    lps[index] = 0;
                    while (length != 0) {
                        length = lps[length - 1];
                        if(needle.charAt(index) == needle.charAt(length)) {
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
        int result = 0;
        while(textIndex < haystack.length()) {
            if(haystack.charAt(textIndex) == needle.charAt(bIndex)) {
                bIndex++; textIndex++;
            }
            if(bIndex == needle.length()) {
                return textIndex - bIndex;
            }
            while(textIndex < haystack.length() && haystack.charAt(textIndex) != needle.charAt(bIndex)) {
                if(bIndex == 0) textIndex++;
                else bIndex = lps[bIndex - 1];
            }
        }


        return -1;
    }
}