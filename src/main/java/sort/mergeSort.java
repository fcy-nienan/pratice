package sort;
//归并排序
public class mergeSort extends abstractSort{
    public static void main(String args[]) {
        new mergeSort().execute();
    }

    @Override
    protected void sort(int[] array) {//需要注意的地方,这个是len-1
        mergeSort(array,0,array.length-1,new int[array.length]);
    }
    public static void mergeSort(int[] array,int start,int end,int[] tmp){
        if (start<end){
            int mid=(start+end)/2;
            mergeSort(array,start,mid,tmp);
            mergeSort(array,mid+1,end,tmp);
            merge(array,start,mid,end,tmp);
        }
    }
    public static void merge(int[] array,int start,int mid,int end,int[] tmp){
//        这里的j需要mid+1
        int t=0,i=start,j=mid+1;
        while (i<=mid&&j<=end){
            if (array[i]<array[j]){
                tmp[t++]=array[i++];
            }else{
                tmp[t++]=array[j++];
            }
        }
//        将两个数组中剩余的元素挪到临时数组
        while (i<=mid){
            tmp[t++]=array[i++];
        }
        while (j<=end){
            tmp[t++]=array[j++];
        }
//      将排好序的元素复制到原数组
        t=0;
        while (start<=end){
            array[start++]=tmp[t++];
        }
    }
}
