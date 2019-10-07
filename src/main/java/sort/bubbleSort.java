package sort;

import java.util.Random;
//冒泡排序    时间复杂度o(n*n)   空间复杂度o(1)
//每次循环找出一个最小或者最大的数一直上浮或者下降到一端
//是一种稳定性的排序
public class bubbleSort extends abstractSort{
    public static void main(String args[]) {
        new bubbleSort()
                .sortCount(10000)
                .arrayLen(1000)
                .execute();
    }

    @Override
    protected void sort(int[] array) {
        bubbleSort2(array);
    }

    public static void bubbleSort2(int[] array){
        for(int i=0;i<array.length-1;i++){
            for(int j=0;j<array.length-1-i;j++){
                if (array[j]<array[j+1]){
                    int tmp=array[j];
                    array[j]=array[j+1];
                    array[j+1]=tmp;
                }
            }
        }
    }
    public static void bubbleSort(int[] array){
        for(int i=0;i<array.length;i++){
            for(int j=0;j<array.length-1;j++){
                if (array[j]>array[j+1]){
                    int tmp=array[j];
                    array[j]=array[j+1];
                    array[j+1]=tmp;
                }
            }
        }
    }
}
