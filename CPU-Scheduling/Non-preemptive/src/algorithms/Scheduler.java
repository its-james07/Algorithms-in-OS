package algorithms;

public abstract class Scheduler {
    int n, count = 0;
    int[] burstTime, arrivalTime, TAT, WT, CT;
    String[] processes;

    public Scheduler(int[] arrivalTime, int[] burstTime) {
        this.n = arrivalTime.length;
        this.arrivalTime = arrivalTime.clone();
        this.burstTime = burstTime.clone();
        this.processes = new String[n];

        for (int i = 0; i < n; i++) {
            processes[i] = "P"+count;
            count++;
        }

        CT = new int[n];
        TAT = new int[n];
        WT = new int[n];
    }

    public abstract void findCT();

    public void findTAT() {
        for (int i = 0; i < n; i++) {
            TAT[i] = CT[i] - arrivalTime[i];
        }
    }

    public void findWT() {
        for (int i = 0; i < n; i++) {
            WT[i] = TAT[i] - burstTime[i];
        }
    }

    public void findAvg() {
        findTAT();
        findWT();

        int totalTAT = 0, totalWT = 0;
        for (int i = 0; i < n; i++) {
            totalTAT += TAT[i];
            totalWT += WT[i];
        }

        float avgTAT = (float) totalTAT / n;
        float avgWT = (float) totalWT / n;

        System.out.println("\nPID\tAT\tBT");
        for (int j = 0; j < n; j++) {
            System.out.println(processes[j] + "\t" + arrivalTime[j] + "\t" +
                               burstTime[j]);
        }

        System.out.println("\nAverage Turn Around Time: " + avgTAT);
        System.out.println("Average Waiting Time: " + avgWT);
    }
}
