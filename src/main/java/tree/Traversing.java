package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.logging.Logger;

public class Traversing {
    private static Logger logger = Logger.getLogger(Traversing.class.getName());

//    前序遍历
    public static List<Integer> preOrderStack(TreeNode root){
        List<Integer> list=new ArrayList<>();
        Stack<TreeNode> stack=new Stack<>();
        stack.push(root);
        while (!stack.empty()){
            TreeNode node=stack.pop();
            list.add(node.val);
            if (node.right!=null){
                stack.push(node.right);
            }
            if (node.left!=null){
                stack.push(node.left);
            }
        }
        return list;
    }
//    中序遍历
    public static List<Integer> inOrderStack(TreeNode root){
        List<Integer> list=new ArrayList<>();
        if (root==null)return list;
        Stack<TreeNode> stack=new Stack<>();
        stack.push(root);
        while (!stack.empty()){
            TreeNode node=stack.peek();
            if (node.right!=null){
                stack.push(node.right);
            }
            if (node.left!=null){
                stack.push(node.left);
            }
            if (node.left==null){
                list.add(node.val);
                stack.pop();
            }
        }
        return list;
    }
    public static void postOrderStack(TreeNode root){

    }
}
