package sort;

public class quickSort {
    public static void main(String args[]) {

    }
    public static void quickSort(int[] array,int start,int end){
        int key=array[start];
        int low=start,high=end;
        while (low<high){
            while (low<high&&array[low]>=key){
                low++;
            }

        }
    }
}
