package algorithms;

public class ShortJF extends Scheduler {

    public ShortJF(int[] arrivalTime, int[] burstTime) {
        super(arrivalTime, burstTime);
    }

    @Override
    public void findCT() {
        int completed = 0, currentTime = 0;
        boolean[] hasCompleted = new boolean[n];

        while (completed < n) {
            int index = -1;
            int minBurst = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                if (!hasCompleted[i] && arrivalTime[i] <= currentTime) {
                    if (burstTime[i] < minBurst) {
                        minBurst = burstTime[i];
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

    
}
