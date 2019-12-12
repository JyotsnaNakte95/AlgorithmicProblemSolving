/**
 * This class helps to find expression of the numbers given using the three operations (+,*,-) to be divisible by 101.
 * @author Jyotsna Nakte
 */


import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class ArithmeticExpression {

    // Complete the arithmeticExpressions function below.
    static String arithmeticExpressions(int[] arr) {
        String result = "";
        String[] operation = {"+", "-", "*"};
        boolean done = false;
        Map<Integer, String> outputToExpression = new HashMap<>();
        outputToExpression.put(arr[0], String.valueOf(arr[0]));
        for(int i = 1; i < arr.length; i++) {
            Map<Integer, String> tempOutputToExpression = new HashMap<>();
            int currentInteger = arr[i];
            final Set<Map.Entry<Integer, String>> entry2  = outputToExpression.entrySet();
            for(int entry : outputToExpression.keySet()) {
                for(int k = 0; k < operation.length; k++) {
                    int tempOutput = entry;
                    if(operation[k].equals("+")) {
                        tempOutput = tempOutput + currentInteger;
                        tempOutputToExpression.put(tempOutput, outputToExpression.get(entry) + "+" + currentInteger);
                    }
                    if(operation[k].equals("-")) {
                        tempOutput = tempOutput - currentInteger;
                        tempOutputToExpression.put(tempOutput, outputToExpression.get(entry) + "-" + currentInteger);
                    }
                    if(operation[k].equals("*")) {
                        tempOutput = (tempOutput * currentInteger) % 101;
                        tempOutputToExpression.put(tempOutput, outputToExpression.get(entry) + "*" + currentInteger);
                    }
                    if(tempOutput % 101 == 0) {
                        result = tempOutputToExpression.get(tempOutput);
                        for(int j = i + 1; j < arr.length; j++) {
                            result += "*" + arr[j];
                        }
                        done = true;
                    }
                    if(done) break;
                }
                if(done) break;
            }
            if(done) break;
            outputToExpression = tempOutputToExpression;
        }


        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        String result = arithmeticExpressions(arr);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
