public class HighRR{
    static void findCT(int[] processes, int[] arrivalTime, int[] burstTime, int[] CT) {
        int completed = 0, currentTime = 0, n = arrivalTime.length;
        boolean[] hasCompleted = new boolean[n];

        while (completed < n) {
            int index = -1;
            double maxRatio = -1;

            for (int i = 0; i < n; i++) {
                if (!hasCompleted[i] && arrivalTime[i] <= currentTime) {
                    int waitingTime = currentTime - arrivalTime[i];
                    double responseRatio = (double)(waitingTime+burstTime[i]) / burstTime[i];

                    if(responseRatio > maxRatio){
                        maxRatio = responseRatio;
                        index = i;
                    }
                }
            }

            if (index == -1) {
                currentTime++;
            } else {
                currentTime += burstTime[index];
                CT[index] = currentTime;
                hasCompleted[index] = true;
                completed++;
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

    static void findAvg(int[] arrivalTime, int[] burstTime){
        int n = arrivalTime.length;
        int[] TAT = new int[n];
        int[] CT = new int[n];
        int[] WT = new int[n];
        int[] processes = new int[n];

        for(int i = 0; i<n; i++){
            processes[i] = i+1;
        }

        findCT(processes, arrivalTime, burstTime, CT);
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
        int[] arrivalTime = {3, 8, 2, 7, 11};
        int[] burstTime   = {8, 6, 11, 5, 13};

        findAvg(arrivalTime, burstTime);
    }

    
}
