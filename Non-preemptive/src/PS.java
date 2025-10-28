import algorithms.*;
import java.util.Scanner;

class PS{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] arrivalTime = {3, 8, 2, 7, 11};
        int[] burstTime   = {8, 6, 11, 5, 13};
        
        System.out.println("Choose Algorithm\n1. FCFS\n2. SJF");
        int choice = sc.nextInt();

        Scheduler scheduler = null;

        switch (choice) {
            case 1:
                scheduler = new FCFS(arrivalTime, burstTime);
                break;
            case 2:
                scheduler = new ShortJF(arrivalTime, burstTime);
                break;
            default:
                System.out.println("Invalid Choice");
                break;
        }

        if (scheduler != null) {
            scheduler.findCT();
            scheduler.findAvg();
        }

        sc.close();
    }
}
