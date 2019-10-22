package tree;

import link.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.logging.Logger;

public class Traversing {
    private static Logger logger = Logger.getLogger(Traversing.class.getName());

    public static List<Integer> preOrderStack1(TreeNode root){
        List<Integer> list=new ArrayList<>();

        return list;
    }













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
    public static List<Integer> inOrderStackII(TreeNode root){
        List<Integer> list=new ArrayList<>();
        Stack<TreeNode> stack=new Stack<>();
        TreeNode node=root;
        while (node!=null||!stack.empty()){
            if (node!=null){
                stack.push(node);
                node=node.left;
            }else{
                TreeNode n=stack.pop();
                list.add(n.val);
                node=node.right;
            }

        }
        return list;
    }
//    中序遍历
    public static List<Integer> inOrderStack(TreeNode root){
        List<Integer> list=new ArrayList<>();
        if (root==null)return list;
        Stack<TreeNode> stack=new Stack<>();
        TreeNode node=root;
        while (node!=null){
            stack.push(node);
        }
        while (!stack.empty()){
            TreeNode n=stack.pop();
            System.out.println(n.val);
            n=n.right;
            while (n!=null){
                stack.push(n);
                n=n.left;
            }
        }
        return list;
    }
    /*
    * 1 3   2 7 8 6 9 5
    *                   1
    *           2               3
    *
    *       5       7
    *             6    8
    *           9
    *
    *
    *
    * */
    public static List<Integer> postOrderStack(TreeNode root){
        List<Integer> list=new ArrayList<>();
        Stack<TreeNode> stack=new Stack<>();
        TreeNode node=root,prev=null;
        while (null!=node||!stack.empty()){
            if (node!=null){
                stack.push(node);
                if (node.right!=null){
                    prev=node;
                    node=node.right;
                }else if (node.left!=null){
                    prev=node;
                    node=node.left;
                }else{
                    node=prev.left;
                }
            }
        }
        return list;
    }
}
