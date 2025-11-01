package algorithms;

public class FCFS extends Scheduler {

    public FCFS(int[] arrivalTime, int[] burstTime) {
        super(arrivalTime, burstTime);
    }

    @Override
    public void findCT() {
        // Sort based on arrival time (Bubble Sort)
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arrivalTime[j] > arrivalTime[j + 1]) {
                    int temp;

                    temp = arrivalTime[j];
                    arrivalTime[j] = arrivalTime[j + 1];
                    arrivalTime[j + 1] = temp;

                    temp = burstTime[j];
                    burstTime[j] = burstTime[j + 1];
                    burstTime[j + 1] = temp;
                }
            }
        }

        int currentTime = 0;
        for (int i = 0; i < n; i++) {
            if (arrivalTime[i] > currentTime) {
                currentTime = arrivalTime[i];
            }
            currentTime += burstTime[i];
            CT[i] = currentTime;
        }
    }
}
