package sort;

public class quickSort extends AbstractSort {
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
        quickSort1(array,0,array.length-1);
    }
    public static void quickSort1(int[] array,int low,int high){
        int start=low,end=high;
        int key=array[start];
        while(start<end){
            while (start<end&&array[end]>=key){
                end--;
            }
            sortUtil.swap(array,start,end);
            while(start<end&&array[start]<=key){
                start++;
            }
            sortUtil.swap(array,start,end);
        }
        if (start!=low)quickSort1(array,low,start);
        if (end!=high)quickSort1(array,start+1,high);

    }
}
