import java.util.Scanner;
public class FirstCFS{
    static int findSeekTime(int request, int currentPosition, int[] positions){
        int seekTime = 0;
        for(int i = 0; i<request; i++){
            seekTime += Math.abs(currentPosition - positions[i]);
            currentPosition = positions[i];
        }
        return seekTime;
    }
    public static void main(String[] args){
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