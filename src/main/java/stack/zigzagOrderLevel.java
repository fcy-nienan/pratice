package stack;

import tree.TreeNode;
import tree.TreeUtil;

import java.util.*;
import java.util.logging.Logger;

public class zigzagOrderLevel {
    public static void main(String[] args) {
        TreeNode root= TreeUtil.create(new int[]{3,9,20,-1,-1,7});
        dfs(root);
        List<List<Integer>> list=zigzagLevelOrder(root);
        for(List<Integer> t:list){
            for(int k:t){
                System.out.println(k);
            }
        }
    }
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> lists=new ArrayList<>();
        if (root==null)return lists;
        Stack<TreeNode> stack=new Stack<>();
        stack.push(root);
        boolean face=false;
        while (!stack.isEmpty()){
            TreeNode node;
            List<Integer> listSingle=new ArrayList<>();
            List<TreeNode> tmpList=new ArrayList<>();
            while (!stack.empty()&&(node=stack.pop())!=null){
                listSingle.add(node.val);
                if (face) {
                    if (node.right != null) {
                        tmpList.add(node.right);
                    }
                    if (node.left != null) {
                        tmpList.add(node.left);
                    }
                }else{
                    if (node.left!=null){
                        tmpList.add(node.left);
                    }
                    if (node.right!=null){
                        tmpList.add(node.right);
                    }
                }
            }
            face=!face;
            lists.add(listSingle);
            for(TreeNode n:tmpList){
                stack.push(n);
            }
        }
        return lists;
    }
    public static void dfs(TreeNode root){
        System.out.println(root);
        if (root.left!=null){
            dfs(root.left);
        }
        if (root.right!=null){
            dfs(root.right);
        }
    }
    public static void bfs(TreeNode root){
        if (root!=null){
            System.out.println(root.val);
        }
    }
}
