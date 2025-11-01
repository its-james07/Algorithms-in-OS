import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;
public class ShortSTF{
    static int findSeekTime(int request, int currentPosition, int[] positions){
        int seekTime = 0, completed = 0; 
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for(int r : positions){
            if(r < currentPosition){ maxHeap.add(r); }
            else { minHeap.add(r); }
            }
        while(completed < request){
            int next;
            Integer topofMax = maxHeap.peek();
            Integer topofMin = minHeap.peek();
            if(topofMax == null){
                next = minHeap.poll();
            }
            else if(topofMin == null){ next = maxHeap.poll(); }
            else{
            if(Math.abs(currentPosition - topofMax) < Math.abs(currentPosition - topofMin)){
                next = maxHeap.poll();
            }
            else{
                next = minHeap.poll();
            }
            }
            seekTime += Math.abs(currentPosition - next);
            currentPosition = next;
            completed++;
        }
        return seekTime;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of Requests");
        int request = sc.nextInt();
        int[] positions = new int[request];
        System.out.println("Enter Requested Track Positions");
        for(int i = 0; i<request; i++){
            positions[i] = sc.nextInt();
        }
        System.out.println("Enter Current Position of Track");
        int currentPosition = sc.nextInt();
        int result = findSeekTime(request, currentPosition, positions);
        System.out.println("Seek Time : "+result);
        sc.close();
    }
}