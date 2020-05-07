package link;

public class LinkUtil {
    public static void main(String args[]) {

    }
    public static void disListNode(ListNode head){
        ListNode tmp=head;
        while (tmp!=null){
            System.out.print(tmp.val+"-->");
            tmp=tmp.next;
        }
        System.out.print("null\r\n");
    }

    public static int length(ListNode head){
        ListNode p=head;
        int len=0;
        while (p!=null){
            len++;
            p=p.next;
        }
        return len;
    }
    public static ListNode create(int[] array){
        ListNode virtual=new ListNode();
        ListNode p=virtual;
        for(int a:array){
            ListNode node=new ListNode(a);
            p.next=node;
            p=p.next;
        }
        return virtual.next;
    }
    public static ListNode create(int len){
        ListNode head=new ListNode(0);
        ListNode p=head;
        for(int i=1;i<=len;i++){
            p.next=new ListNode(i);
            p=p.next;
        }
        return head.next;
    }
}
