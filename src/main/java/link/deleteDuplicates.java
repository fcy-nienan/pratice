package link;
//输入: 1->2->3->3->4->4->5
//输出: 1->2->5
public class deleteDuplicates {
    public static void main(String args[]) {
        ListNode root=LinkUtil.create(new int[]{1,1,1,2,3,3,3,4,4,5});
        root=deleteDuplicatesII(root);
        LinkUtil.disListNode(root);
    }
    public static ListNode deleteDuplicatesII(ListNode head){
        if (head==null||head.next==null)return head;
        ListNode prev=new ListNode(),current=head;
        while (current!=null){
            int val=current.val;
            while (current.val==val){
                current=current.next;
            }   
        }
        return prev.next;
    }
    public static ListNode deleteDuplicates(ListNode head) {
        if (head==null||head.next==null)return head;
        ListNode prev=head,current=prev.next;
        while (current!=null){
            if (prev.val==current.val){
                prev.next=current.next;
                current=current.next;
            }else{
                prev=current;
                current=current.next;
            }
        }
        return head;
    }
}
