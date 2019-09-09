package tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Logger;

public class TreeUtil {
    private static Logger logger = Logger.getLogger(TreeUtil.class.getName());
    public static void preOrder(TreeNode root){
        if (root!=null){
            System.out.print(root.getVal()+"\t");
            preOrder(root.getLeft());
            preOrder(root.getRight());
        }
    }
    public static void inOrder(TreeNode root){
        if (root!=null){
            inOrder(root.left);
            System.out.println(root.val);
            inOrder(root.right);
        }
    }
    public static void postOrder(TreeNode root){
        if (root!=null){
            postOrder(root.left);
            postOrder(root.right);
            System.out.println(root.val);
        }
    }
    public static TreeNode create(int[] array){
        if (array.length==0)return null;
        Queue<TreeNode> linkedList=new LinkedList();
        TreeNode node=new TreeNode(array[0]);
        linkedList.offer(node);
        int i=1;
        while (!linkedList.isEmpty()){
            TreeNode tmp=linkedList.poll();
            if (tmp==null)return node;
            if (i<array.length) {
                if (array[i]==-1){
                    tmp.setLeft(null);
                    i++;
                }else {
                    TreeNode left = new TreeNode(array[i]);
                    tmp.left=left;
                    linkedList.offer(left);
                    i++;
                }
            }
            if (i<array.length) {
                if (array[i]==-1){
                    tmp.setRight(null);
                    i++;
                }else {
                    TreeNode right = new TreeNode(array[i]);
                    tmp.setRight(right);
                    linkedList.offer(right);
                    i++;
                }
            }
        }
        return node;
    }
}
