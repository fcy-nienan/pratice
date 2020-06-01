package sort;

import java.util.Arrays;

public class quickSort extends AbstractSort {
    @Override
    protected void sort(int[] array) {
        quickSort2(array,0,array.length-1);
    }
    public static void main(String args[]) {
        int[] arr1=new int[]{2,7,1,9,4,4,2};
//        int[] arr=new int[]{2,7,1,9,4,4,3};
        quickSort2(arr1,0,arr1.length-1);
        System.out.println(Arrays.toString(arr1));
//        new quickSort().arrayLen(10000).sortCount(1000).execute();
    }
    public static void quickSort(int[] array,int start,int end){
        int key=array[start];
        int low=start,high=end;
        while (low<high){
            while (low<high&&array[high]>=key){//为什么需要等于?   2 .....  2
                high--;
            }
            while (low<high&&array[low]<key){//需要等于吗?   2 1
                low++;
            }
            sortUtil.swap(array,low,high);
        }
        if (low!=start)quickSort(array,start,low);
        if (high!=end)quickSort(array,low+1,end);
    }
    private static void quickSort2(int[] array,int start,int end){
        int low=start,high=end;
        int key=array[low];
        while (low<high){
            while (low<high&&array[high]>=key){
                high--;
            }
            while (low<high&&array[low]<key){
                low++;
            }
            sortUtil.swap(array,low,high);
        }
        if (start!=low)quickSort2(array,start,low);
        if (end!=high)quickSort2(array,low,end);
    }
}
