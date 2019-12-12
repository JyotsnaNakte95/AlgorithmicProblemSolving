/**
 * The class finds the maximum number of people superman can save in one single drop
 * @author Jyotsna Nakte
 */


import java.io.*;
import java.util.*;

public class SupermanDiwali {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int buildings = sc.nextInt();
        int height = sc.nextInt();
        int loss = sc.nextInt();
        int[][] numberOfPeople = new int[buildings][height+1];
        for(int i = 0; i < buildings; i++) {
            int numberOfValue = sc.nextInt();
            for(int j = 0; j < numberOfValue; j++) {
                int currentFloor = sc.nextInt();
                numberOfPeople[i][currentFloor]++;
            }
        }

        int[][] peopleSave = new int[buildings][height+1];
        int[] maxPerHeight = new int[height + 1];
        for(int i = 1; i <= height;  i++) {
            for(int j = 0; j < buildings; j++) {
                // If Loss is greater that means we cannot jump, OR  peopleSave on last floor is higher than if we are jumped from other building
                // then we take the last best result plus the current number of people in the building.
                if(loss > i || (loss <= i && maxPerHeight[i-loss] <= peopleSave[j][i-1])) {
                    peopleSave[j][i] = peopleSave[j][i-1] + numberOfPeople[j][i];
                } else {
                    // Else Add Max People saved if jumped from other building plus the current building
                    peopleSave[j][i] =  maxPerHeight[i-loss] + numberOfPeople[j][i];
                    // IF max selected is from the current floor, then we add all the people in between.
                    if(maxPerHeight[i-loss] == peopleSave[j][i-loss]) {
                        for(int k = i-1; k > i-loss; k--) {
                            peopleSave[j][i] += numberOfPeople[j][k];
                        }
                    }
                }
            maxPerHeight[i] = Math.max(peopleSave[j][i], maxPerHeight[i]); 
            }
        }
        System.out.println(maxPerHeight[height]);
    }
}

