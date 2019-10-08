package sort;

import java.util.logging.Logger;

public class bucketSort extends abstractSort {
    private static Logger logger = Logger.getLogger(bucketSort.class.getName());

    public static void main(String args[]) throws Exception {
        new bucketSort().execute();
    }

    @Override
    protected void sort(int[] array) {
        bucketSort(array,getArrayLen());
    }
    public static void bucketSort(int[] array,int max){
        int[] data=new int[max];
        for(int i=0;i<array.length;i++){
            data[array[i]]++;
        }
        int index=0;
        for(int i=0;i<max;i++){
            int k=data[i];
            for(int j=0;j<k;j++) {
                array[index++] = i;
            }
        }
    }
}
