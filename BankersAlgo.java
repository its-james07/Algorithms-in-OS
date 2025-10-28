import java.util.Scanner;
import java.util.Arrays;
public class BankersAlgo{
    static int i, j,k;
    static int P = 5;
    static int R = 3;
    static void calculateNeed(int need[][], int maxNeed[][], int allocation[][]){
        for (i = 0; i < P; i++) {
        for (j = 0; j < R; j++) {
            need[i][j] = maxNeed[i][j] - allocation[i][j];
        }
    }
    }
    static void checkState(int[] processes, int[] available, int[][] maxNeed, int[][] allocation){
        int[][] need = new int[P][R];
        calculateNeed(need, maxNeed, allocation);

        boolean[] finish = new boolean[P];
        int[] safeSeq = new int[P];
        int count = 0;

        while(count < P){
            boolean found = false;
            for(i = 0; i<P; i++){
            if(!finish[i]){
                    boolean canaAllocate = true;
                    for(j=0; j<R; j++){
                        if(need[i][j] > available[j]){
                            canaAllocate = false;
                            break;
                        }
                    }
            if(canaAllocate){
                    for(k = 0; k<R; k++){
                        available[k] += allocation[i][k];
                        }
                        safeSeq[count++] = i;
                        finish[i] = true; 
                        found = true; 
                    }
                }
            }
            
            if(!found){
                System.out.println("System is not in safe state");
                return;
            }
        }
        System.out.println("The system is in safe state. Safe sequence is as follow: ");
        for(i = 0; i<P; i++){
            System.out.print("P"+safeSeq[i]);
            if(i != P-1){
                System.out.print("->");
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] processes = {0, 1, 2, 3, 4};
        int[] available = {3, 3, 2};
        // int[][] maxNeed = new int[P][R];
        // int[][] allocation = new int[P][R];

        int[][] maxNeed = {
                            {7,5,3},
                            {3,2,2},
                            {9,0,2},
                            {4,2,2},
                            {5,3,3}
        };

        int[][] allocation = {
                            {0,1,0},
                            {2,0,0},
                            {3,0,2},
                            {2,1,1},
                            {0,0,2}
        };
        
        // System.out.println("Enter available resources");
        // for(i = 0; i<R; i++){
        //     available[i] = sc.nextInt();
        // }

        // System.out.println("Enter Maximum need for each processes: ");
        // for(i = 0; i<P; i++){
        //     System.out.println("For P["+i+"]");
        //     for(j = 0; j<R; j++){
        //         maxNeed[i][j] = sc.nextInt();
        //     }
        // }

        // System.out.println("Enter Allocated Resources for each Processes: ");
        // for(i = 0; i<P; i++){
        //     System.out.println("For P["+i+"]");
        //     for(j = 0; j<R; j++){
        //         allocation[i][j] = sc.nextInt();
        //     }
        // }
        sc.close();
        checkState(processes, available, maxNeed, allocation);
    }
}

