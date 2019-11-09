package tree.AVL;

public class AVLTree {
    public int getHeight(AVLNode root){
        if (root!=null){
            if (root.getLeft()==null&&root.getRight()==null){
                return 1;
            }

            int left=getHeight(root.getLeft());
            int right=getHeight(root.getRight());
            return Math.max(left,right);
        }else{
            return 0;
        }
    }
}
