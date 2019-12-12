/**
 * The class finds the total number of times the client receives a notification.
 * @author Jyotsna Nakte
 */
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class FraudulentNotification {

    static int binarySearchInsortedList(List<Integer> list, int number){
        int indexValue= Integer.MAX_VALUE;
        int low=0, high=list.size()-1, mid=0;
        while (low <= high) {
        mid = (low + high) / 2;
        if (list.get(mid) < number) {
            low = mid + 1;
        } else if (list.get(mid) > number) {
            high = mid - 1;
        } else if (list.get(mid) == number) {
            indexValue = mid;
            break;
        }
    }
    if(indexValue == Integer.MAX_VALUE){
        if(mid%2==0){
        indexValue=-(mid+2);
    }
    else{
        indexValue=-(mid+1);
    }
    }
    return indexValue;
    }


    static double[] findMedian(int[] expenditure, int d){
        int size = expenditure.length - d + 1;
        double[] res=new double[size];
        List<Integer> mediancalclist = new ArrayList<Integer>();
        for(int i=0;i<d;i++){
            mediancalclist.add(expenditure[i]);
        }
        Collections.sort(mediancalclist);
        if(d%2==0) {
            res[0] = (double)(mediancalclist.get(d/2-1)+mediancalclist.get(d/2))/2;
        } else {
            res[0] = mediancalclist.get(d/2);
        }
        for(int i=0;i<expenditure.length-d;i++){
            int Rightindex= binarySearchInsortedList(mediancalclist,expenditure[i+d]);
            if(Rightindex < 0) {
                Rightindex = -(Rightindex+1);
            }
            mediancalclist.add(Rightindex,expenditure[i+d]);
            int leftIndex=binarySearchInsortedList(mediancalclist,expenditure[i]);
            mediancalclist.remove(leftIndex);
            if(d%2==0) {
                res[i+1] = (double)(mediancalclist.get(d/2-1)+mediancalclist.get(d/2))/2;
            } else {
                res[i+1] = mediancalclist.get(d/2);
            }
        }

        return res;
    }

    static int activityNotifications(int[] expenditure, int d) {
        int notifications=0;
        double[] mediansOfArray= findMedian(expenditure, d);
        System.out.println(Arrays.toString(mediansOfArray));
        int i=d;
        int j=0;
        while(i<expenditure.length){
            if(expenditure[i]>=2*mediansOfArray[j]){
                    notifications=notifications+1;
            }
            i=i+1;
            j=j+1;
        }  
        return notifications;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nd = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nd[0]);

        int d = Integer.parseInt(nd[1]);

        int[] expenditure = new int[n];

        String[] expenditureItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int expenditureItem = Integer.parseInt(expenditureItems[i]);
            expenditure[i] = expenditureItem;
        }

        int result = activityNotifications(expenditure, d);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
