package stack;

import tree.TreeNode;
import tree.TreeUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.logging.Logger;
//输入: [1,null,2,3]
//   1
//    \
//     2
//    /
//   3
//
//输出: [1,3,2]
public class inorderTraversal {
    private static Logger logger = Logger.getLogger(inorderTraversal.class.getName());

    public static void main(String args[]) throws Exception {
        TreeNode root= TreeUtil.create(new int[]{3,1,-1,-1,2});
        TreeUtil.preOrder(root);
        root=new TreeNode(3);
        root.left=new TreeNode(1);
        root.left.right=new TreeNode(2);
        List<Integer> list=inorderTraversal(root);
        System.out.println(list);
    }
    public static List<Integer> inorderTraversal(TreeNode root) {
        if (root==null)return new ArrayList<>();
        Stack<TreeNode> stack=new Stack<>();
        List<Integer> list=new ArrayList<>();
        while (root!=null||!stack.isEmpty()){
            if (root!=null){
                stack.push(root);
                root=root.left;
            }else{
                root=stack.pop();
                list.add(root.val);
                root=root.right;
            }
        }
        return list;
    }
}
