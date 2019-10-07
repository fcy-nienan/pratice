package link;

public class sortList {
    public static void main(String args[]) {

    }
//    排序一个链表
//    归并排序

    public ListNode sortList(ListNode head) {
        return null;
    }
//    归并排序？首先将数组元素分解为单独的一个个,然后在进行合并
    public static void sortListMerge(int[] array,int start,int end,int[] temp){
        if (start<end){
            int mid=(start+end)/2;
            sortListMerge(array,start,mid,temp);
            sortListMerge(array,mid+1,end,temp);

        }
    }
    public static void merge(int[] array,int start,int end,int[] temp){

    }
}
