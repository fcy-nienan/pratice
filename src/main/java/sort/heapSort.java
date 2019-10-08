package sort;

import java.util.Arrays;

public class heapSort extends abstractSort{

    public static void main(String args[]) {
        new heapSort().execute();
    }

    @Override
    protected void sort(int[] array) {
        for(int i=array.length/2-1;i>=0;i--){
            adjustHeap(array,i,array.length);
        }
        for (int i=1;i<array.length;i++){
            int tmp=array[0];
            array[0]=array[array.length-i];
            array[array.length-i]=tmp;
            adjustHeap(array,0,array.length-i);
        }
    }
    public static void adjustHeap(int[] array,int current,int len){
        for(int i=current*2+1;i<len;i=current*2+1){
            if (i+1<len&&array[i]>array[i+1]){
                i=i+1;
            }
            if (array[i]<array[current]){
                int tmp=array[current];
                array[current]=array[i];
                array[i]=tmp;
                current=i;
            }else{
                break;
            }
        }
    }
}
