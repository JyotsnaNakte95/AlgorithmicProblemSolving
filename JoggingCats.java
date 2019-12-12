/**
 * This class finds the maximum number of days the cats can jog  through unique
 * paths.
 *
 * @author Jyotsna Nakte
 */
import java.io.*;
import java.util.*;

public class JoggingCats {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int numberOfNode = sc.nextInt();
        int numberOfEdges = sc.nextInt();
        long path = 0;
        List[] graph = new List[numberOfNode + 1];
        Map<Integer, Integer> possiblePathTo;
        for(int i = 1; i <= numberOfNode; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        for(int i = 0; i < numberOfEdges; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            graph[from].add(to);
            graph[to].add(from);
        }
//System.out.println(graph);
        // Iterate through each Node
        for(int i = 1; i <= numberOfNode; i++) {
            List<Integer> currentEdgesForNode = graph[i];
            possiblePathTo = new HashMap<>();
            for(int j : currentEdgesForNode) {
                // We will only traverse the edges that is greater than i, as we dont want to go backward 
                if(i>=j) continue;
                List<Integer> currentEdgesForNext = graph[j];
                // Find the possible path a current node can take.
                for(Integer k : currentEdgesForNext) {
                    if(i>=k) continue;
                    possiblePathTo.put(k, possiblePathTo.getOrDefault(k, 0) + 1);
                }
            }
                    //System.out.println(possiblePathTo);

            // Add the possible path value.
            for(Map.Entry<Integer, Integer> entry : possiblePathTo.entrySet()) {
                path += entry.getValue() * (entry.getValue() - 1);
            }
        }
        
        // Total Path / 2, as it counts symmetric.
        System.out.println(path/2);
    }
}

