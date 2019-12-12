/**
 * The class finds whether the string is valid or not, all characters have same frequencies
 * @author Jyotsna Nakte
 */
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class SherlockValid {

    // Complete the isValid function below.
    static String isValid(String s) {       
        long firstvalue = 0, secondvalue = 0;
        int firstvaluecount = 0, secondvaluecount = 0;
        Map<Character, Integer> frequencycount = new HashMap<Character, Integer>();
        for(int i=0;i<s.length();i++){
            frequencycount.put(s.charAt(i), frequencycount.getOrDefault(s.charAt(i), 0) + 1);
        }   
            firstvalue=frequencycount.get(frequencycount.keySet().toArray()[0]);
           for (Map.Entry mapElement : frequencycount.entrySet()) { 
            int value = (int)mapElement.getValue();
            if(value!=firstvalue){
               secondvalue=value; 
            }
           }
           if(secondvalue == 0){
               return "YES";
           }
        for (Map.Entry mapElement : frequencycount.entrySet()) {
            int value = (int)mapElement.getValue();
            if(value == firstvalue) {
                firstvaluecount++;
            }
            else  if(value == secondvalue) {
                secondvaluecount++;
            }
            else{
                return "NO";
            }
        }
        if(firstvaluecount==1 && secondvaluecount >= 1 ){
            long cn= Math.abs(firstvalue - secondvalue);
            if(cn!=1 && firstvalue != 1) {
                return "NO";
            }
        }

        if(firstvaluecount > 1 && secondvaluecount > 1) {
            return "NO";
        }

        return "YES";

    }
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
       // BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scanner.nextLine();

        String result = isValid(s);
        System.out.println(result);

        scanner.close();
    }
}
