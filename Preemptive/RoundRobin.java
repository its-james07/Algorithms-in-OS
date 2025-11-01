import java.util.Scanner;

public class RoundRobin{
    static void findCT(int[] arrivalTime, int[] burstTime, int[] CT, int timeQuantum) {
        int completed = 0, currentTime = 0, n = arrivalTime.length;
        boolean[] hasCompleted = new boolean[n];
        int[] remainingTime = burstTime.clone();

        while (completed < n) {
            int index = -1;
            int minRemainingTime = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                System.out.println(remainingTime[i]);
                if(!hasCompleted[i] && arrivalTime[i] <= currentTime) {
                    if(remainingTime[i] < minRemainingTime){
                        minRemainingTime = remainingTime[i];
                        index = i;
                        System.out.println("Choosen"+index);
                    }
                }
            }
            if (index == -1) {
                currentTime++;
            } else {
                currentTime++;
                if(remainingTime[index] < timeQuantum){
                    remainingTime[index] -= remainingTime[index];
                }
                else{
                    remainingTime[index] -= timeQuantum;
                }
                if(remainingTime[index] == 0){ 
                    CT[index] = currentTime;
                    hasCompleted[index] = true;
                    completed++;
                }
            }
        }
    }

    static void findTAT(int[] TAT, int[] CT, int[] arrivalTime){
        int n = arrivalTime.length;
        for (int i = 0; i < n; i++) {
            TAT[i] = CT[i] - arrivalTime[i];
        }
    }

    static void findWT(int[] TAT, int[] WT, int[] burstTime){
     int n = burstTime.length;
        for (int i = 0; i < n; i++) {
            WT[i] = TAT[i] - burstTime[i];
        }
    }

    static void findAvg(int[] arrivalTime, int[] burstTime, int timeQuantum){
        int n = arrivalTime.length;
        int[] TAT = new int[n];
        int[] CT = new int[n];
        int[] WT = new int[n];
        int[] processes = new int[n];

        for(int i = 0; i<n; i++){
            processes[i] = i+1;
        }

        findCT(arrivalTime, burstTime, CT, timeQuantum);
        findTAT(TAT, CT, arrivalTime);
        findWT(TAT, WT, burstTime);
        int totalTAT = 0, totalWT = 0;
        for (int i = 0; i < n; i++) {
            totalTAT += TAT[i];
            totalWT += WT[i];
        }

        float avgTAT = (float) totalTAT / n;
        float avgWT = (float) totalWT / n;

        System.out.println("\nPID\tAT\tBT\tCT\tTAT\tWT");
        for (int j = 0; j < n; j++) {
            System.out.println(processes[j] + "\t" +
                               arrivalTime[j] + "\t" +
                               burstTime[j] + "\t" +
                               CT[j] + "\t" +
                               TAT[j] + "\t" +
                               WT[j]);
        }

        System.out.println("\nAverage Turn Around Time: " + avgTAT);
        System.out.println("Average Waiting Time: " + avgWT);
        
    }

    

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arrivalTime = {3, 8, 2, 7, 11};
        int[] burstTime   = {8, 6, 11, 5, 13};

        System.out.println("RoundRobin Scheduling\nEnter time Quantum");
        int timeQuantum = sc.nextInt();

        findAvg(arrivalTime, burstTime, timeQuantum);
    }

    
}
