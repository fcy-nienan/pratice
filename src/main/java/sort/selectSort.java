package sort;
//选择排序
//时间复杂度o(n*n) 空间复杂度o(1)  稳定性:是
public class selectSort extends abstractSort{
    public static void main(String args[]) {
        new selectSort().execute();
    }

    @Override
    protected void sort(int[] array) {
        selectSort2(array);
    }
    public static void selectSort(int[] array){
        for(int i=0;i<array.length-1;i++){
            int min=array[i];
            int k=i;
            for(int j=i+1;j<array.length;j++){
                if (min<array[j]){
                    min=array[j];
                    k=j;
                }
            }
            array[k]=array[i];
            array[i]=min;
        }
    }
    public static void selectSort2(int[] array){
        for(int i=0;i<array.length-1;i++){
            int minIndex=i;
            for(int j=i+1;j<array.length;j++){
                if (array[j]<array[minIndex]){
                    minIndex=j;
                }
            }
            int tmp=array[i];
            array[i]=array[minIndex];
            array[minIndex]=tmp;
        }
    }
}
