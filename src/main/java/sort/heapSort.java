package sort;

import java.util.Arrays;

public class heapSort extends abstractSort{

    public static void main(String args[]) {
        new heapSort().execute();
    }

    @Override
    protected void sort(int[] array) {
        selectSort(array);
    }
    private static void heapSort1(int[] array){
        for (int i=array.length/2-1;i>=0;i--){
            adjustHeap(array,i,array.length);
        }
        for (int i=1;i<array.length;i++){
            sortUtil.swap(array,0,array.length-i);
            adjustHeap(array,0,array.length);
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
    public static void bubbleSort(int[] array){
        for (int i=0;i<array.length-1;i++){
            for (int j=1;j<array.length-1;j++){
                if (array[j]<array[j-1]){
                    sortUtil.swap(array,j,j-1);
                }
            }
        }
    }
    public static void selectSort(int[] array){
        for (int i=0;i<array.length-1;i++){
            int minIndex=i;
            for (int j=i+1;j<array.length;j++){
                if (array[j]<array[minIndex]){
                    minIndex=j;
                }
            }
            array[i]=array[minIndex];
        }
    }
    public static void insertSort(int[] array){
        for (int i=1;i<array.length;i++){
            int value=array[i];
            int j=i-1;
            while (j>=0&&value<array[j]){
                array[j+1]=array[j];
                j--;
            }
            array[j+1]=value;
        }
    }
    public static void heapSort(int[] array){
        for (int i=array.length/2;i>=0;i--){
            adjustHeap1(array,array.length,i);
        }
        for (int i=0;i<array.length;i++){
            int value=array[array.length-i];

        }
    }
//    这个函数是干啥的?将当前节点调整为符合大小堆特性
    private static void adjustHeap1(int[] array,int len,int current){
        for (int i=current*2+1;i<array.length;current=current*2+1){
            if (i+1<len&&array[i]<array[i+1]){
                i++;
            }
            if (array[current]>array[i]){
                sortUtil.swap(array,i,current);
                current=i;
            }else{
                break;
            }
        }
    }
}
