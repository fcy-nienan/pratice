package sort;

public class quickSort extends abstractSort{
    public static void main(String args[]) {
        new quickSort().execute();
    }
    public static void quickSort(int[] array,int start,int end){
        int key=array[start];
        int low=start,high=end;
        while (low<high){
            while (low<high&&array[high]>=key){
                high--;
            }
            if (array[high]<key){
                sortUtil.swap(array,low,high);
            }
            while (low<high&&array[low]<=key){
                low++;
            }
            if (array[low]>key){
                sortUtil.swap(array,low,high);
            }
        }
        if (low!=start)quickSort(array,start,low);
        if (high!=end)quickSort(array,low+1,end);
    }

    @Override
    protected void sort(int[] array) {
        quickSort(array,0,array.length-1);
    }
}
