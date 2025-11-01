import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

public class RoundRobin {
    static void findCT(int[] arrivalTime, int[] burstTime, int[] CT, int timeQuantum) {
        int completed = 0, currentTime = 0, n = arrivalTime.length;
        boolean[] inQueue = new boolean[n];
        int[] remainingTime = burstTime.clone();
        Queue<Integer> queue = new LinkedList<>();

        while (completed < n) {
            for (int i = 0; i < n; i++)
                if (!inQueue[i] && arrivalTime[i] <= currentTime) {
                    queue.add(i);
                    inQueue[i] = true;
                }
            if (queue.isEmpty()) {
                int nextArrival = Integer.MAX_VALUE;
                for (int i = 0; i < n; i++)
                    if (!inQueue[i]) { nextArrival = Math.min(nextArrival, arrivalTime[i]);}
                currentTime = nextArrival;
                continue;
            }
            int i = queue.poll();
            int executionTime = Math.min(timeQuantum, remainingTime[i]);
            remainingTime[i] -= executionTime;
            currentTime += executionTime;
            for (int j = 0; j < n; j++)
                if (!inQueue[j] && arrivalTime[j] <= currentTime) {
                    queue.add(j);
                    inQueue[j] = true;
                }

            if (remainingTime[i] == 0) {
                CT[i] = currentTime;
                completed++;
            } else {
                queue.add(i);
            }
        }
    }
    static void findTAT(int[] TAT, int[] CT, int[] arrivalTime) {
        int n = arrivalTime.length;
        for (int i = 0; i < n; i++) TAT[i] = CT[i] - arrivalTime[i];
    }
    static void findWT(int[] TAT, int[] WT, int[] burstTime) {
        int n = burstTime.length;
        for (int i = 0; i < n; i++) WT[i] = TAT[i] - burstTime[i];
    }
    static void findAvg(int[] arrivalTime, int[] burstTime, int timeQuantum) {
        int n = arrivalTime.length;
        int[] TAT = new int[n], CT = new int[n], WT = new int[n], processes = new int[n];

        for (int i = 0; i < n; i++){processes[i] = i + 1;}

        findCT(arrivalTime, burstTime, CT, timeQuantum);
        findTAT(TAT, CT, arrivalTime);
        findWT(TAT, WT, burstTime);

        int totalTAT = 0, totalWT = 0;
        System.out.println("\nPID\tAT\tBT\tCT\tTAT\tWT");
        for (int i = 0; i < n; i++) {
            totalTAT += TAT[i];
            totalWT += WT[i];
            System.out.println(processes[i] + "\t" + arrivalTime[i] + "\t" + burstTime[i] + "\t" +
                    CT[i] + "\t" + TAT[i] + "\t" + WT[i]);
        }
        System.out.println("\nAverage Turn Around Time: " + (float) totalTAT / n);
        System.out.println("Average Waiting Time: " + (float) totalWT / n);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arrivalTime = {3, 8, 2, 7, 11};
        int[] burstTime = {8, 6, 11, 5, 13};
        System.out.println("RoundRobin Scheduling\nEnter time Quantum:");
        int timeQuantum = sc.nextInt();
        findAvg(arrivalTime, burstTime, timeQuantum);
        sc.close();
    }
}
