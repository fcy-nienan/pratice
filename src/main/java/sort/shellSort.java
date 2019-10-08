package sort;

public class shellSort extends abstractSort{
    public static void main(String args[]) {
        new shellSort().execute();
    }

    @Override
    protected void sort(int[] array) {
        shell(array);
    }
    public static void insertSortSingle(int[] array,int d,int i){
        int j=i-d;
        int value=array[i];
        while (j>=0&&value<array[j]){
            array[j]=array[j-d];
            j-=d;
        }
        array[j+d]=value;
    }
    public static void shell(int[] array){
        for(int gap=array.length/2;gap>0;gap=gap/2){
            for (int j=0;j<gap;j++){
                insertSortSingle(array,gap,j);
            }
        }
    }
    public static void shellSort(int[] array){
        for (int gap=array.length/2;gap>0;gap=gap/2){
            int j=0;

        }
    }
    public static void insertSort(int[] array){
        for(int i=1;i<array.length;i++){
            int j=i-1;
            int value=array[i];
            while (j>=0&&value<array[j]){
                array[j+1]=array[j];
                j--;
            }
            array[j+1]=value;
        }
    }
    public static void selectSort(int[] array){
        for(int i=0;i<array.length-1;i++){
            int minIndex=i;
            for(int j=i+1;j<array.length;j++){
                if (array[minIndex]>array[j]){
                    minIndex=j;
                }
            }
            sortUtil.swap(array,minIndex,i);

        }
    }
    public static void mergeSort(int[] array,int start,int end){
        if (start<end){
            int mid=(start+end)/2;
            mergeSort(array,start,mid);
            mergeSort(array,mid+1,end);
            merge(array,start,mid,end,new int[0]);
        }
    }
    public static void merge(int[] array,int start,int mid,int end,int[] temp){
        int i=start,j=mid+1,t=0;
        while (i<=mid&&j<=end){
            if (array[i]<array[j]){
                temp[t++]=array[i++];
            }else{
                temp[t++]=array[j++];
            }
        }
        while (i<=mid){
            temp[t++]=array[i++];
        }
        while (j<=end){
            temp[t++]=array[j++];
        }
        t=0;
        while (start<=end){
            array[start++]=temp[t++];
        }
    }
}
