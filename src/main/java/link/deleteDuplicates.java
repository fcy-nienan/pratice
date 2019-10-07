package link;

import java.util.HashMap;
import java.util.Map;

public class deleteDuplicates {
    public static void main(String args[]) {
        ListNode root=LinkUtil.create(new int[]{1,2,3,3,4,4,5});
        root=deleteDuplicatesII(root);
        LinkUtil.disListNode(root);
    }
    //输入: 1->2->3->3->4->4->5
    //输出: 1->2->5
    public static ListNode deleteDuplicatesII(ListNode head){
        if (head==null||head.next==null)return head;
        ListNode dummy=new ListNode();
        ListNode prev=dummy,current=prev.next,next=current.next;
        int lastReduce=-999;
//        12233445  1222335 12223335    12233
        while(next!=null){
            if (current.val!=prev.val&&current.val!=next.val&&current.val!=lastReduce){
                prev=current;
                current=prev.next;
                next=current.next;
            }
        }
        return dummy.next;
    }
    //输入: 1->1->2
    //输出: 1->2
    public static ListNode deleteDuplicates(ListNode head) {
        if (head==null||head.next==null)return head;
        ListNode dummy=new ListNode(0,head);
        ListNode prev=dummy.next,current=prev.next;
        while (current!=null){
            if (prev.val==current.val){
                prev.next=current.next;
                current=current.next;
            }else{
                prev=current;
                current=current.next;
            }
        }
        return dummy.next;
    }
}
