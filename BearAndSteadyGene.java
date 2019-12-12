import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class BearAndSteadyGene {

    static boolean ifOutOfBound(Map<String, Integer> geneToCount, int bound) {
        return geneToCount.entrySet()
            .stream()
            .anyMatch(entry -> entry.getValue() > bound);
    }
    static int steadyGene(String gene) {
        if(gene == "") return 0;
        String[] geneArray = gene.split("");
        int result = geneArray.length;
        Map<String, Integer> geneToCount = new HashMap<>();
        int startIndex = 0;
        int endIndex = 0;
        int limit = geneArray.length / 4;
        Arrays
            .stream(geneArray)
            .forEach(
                current -> geneToCount.put(current, geneToCount.getOrDefault(current, 0) + 1));
        if(!ifOutOfBound(geneToCount, limit)) return 0;
        while(startIndex < geneArray.length) {
            while(endIndex < geneArray.length && ifOutOfBound(geneToCount, limit)) {
                geneToCount.put(geneArray[endIndex], geneToCount.get(geneArray[endIndex]) - 1);                endIndex ++;
            }
            if(!ifOutOfBound(geneToCount, limit)) result = Math.min(result, endIndex - startIndex);
            geneToCount.put(geneArray[startIndex], geneToCount.get(geneArray[startIndex]) + 1);
            startIndex++;
        }
        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String gene = scanner.nextLine();

        int result = steadyGene(gene);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
