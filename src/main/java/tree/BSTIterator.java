package tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.logging.Logger;
//二叉搜索树的迭代器
public class BSTIterator {
    TreeNode root;
    public BSTIterator(TreeNode root) {
        this.root=root;
    }
    public int next(){
        TreeNode prev=null;
        TreeNode t=root;
        while(t!=null){
            // 到达最小节点
            while (t.left!=null){
                prev=t;
                t=t.left;
            }
//                和父节点截断
            if (prev!=null){
                prev.left=null;
//            将root节点挂在到当前节点的最右边
                TreeNode tmp=t;
                while (tmp.right!=null) {
                    tmp = tmp.right;
                }
                tmp.right = root;
                root = t.right;
            }else{
//            如果父节点没有值就不需要挂在
                root=root.right;
            }
            return t.val;
        }
        return -1;
    }
    public boolean hasNext(){
        return root!=null;
    }

    public static void main(String[] args) {
        TreeNode root=TreeUtil.create(new int[]{3,1,4,-1,2});
        BSTIterator iterator=new BSTIterator(root);
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
    }
}
