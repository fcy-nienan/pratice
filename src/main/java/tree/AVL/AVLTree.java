package tree.AVL;

public class AVLTree {
    private AVLNode root;
    public int getHeight(AVLNode root){
        if (root!=null){
            if (root.getLeft()==null&&root.getRight()==null){
                return 1;
            }
            int left=getHeight(root.getLeft());
            int right=getHeight(root.getRight());
            return Math.max(left,right)+1;
        }else{
            return 0;
        }
    }
    public void query(int value){

    }
    public void delete(AVLNode node){

    }
    public AVLNode LL(AVLNode node){
        System.out.println("LL");
        AVLNode tmp=node.getRight();
        tmp.setRight(tmp.getLeft());
        tmp.setLeft(tmp);
        node.setHeight(Math.max(getHeight(node.getLeft()),getHeight(node.getRight()))+1);
        tmp.setHeight(Math.max(getHeight(tmp.getLeft()),getHeight(tmp.getRight()))+1);
        return tmp;
    }
    public AVLNode RR(AVLNode node){
        System.out.println("RR");
        AVLNode tmp=node.getLeft();
        node.setLeft(tmp.getRight());
        tmp.setRight(node);
        node.setHeight(Math.max(getHeight(node.getLeft()),getHeight(node.getRight()))+1);
        tmp.setHeight(Math.max(getHeight(tmp.getLeft()),getHeight(tmp.getRight()))+1);
        return tmp;
    }
    public AVLNode LR(AVLNode node){
        node.setLeft(LL(node.getLeft()));
        return RR(node);
    }
    public AVLNode RL(AVLNode node){
        node.setRight(RR(node.getRight()));
        return LL(node);
    }
    public int min(){
        AVLNode node=root;
        while (node.getLeft()!=null){
            node=node.getLeft();
        }
        return node.getValue();
    }
    public int max(){
        AVLNode node=root;
        while (node.getRight()!=null){
            node=node.getRight();
        }
        return node.getValue();
    }
    public AVLNode search(int value){
        AVLNode node=root;
        while (node!=null){
            if (value>node.getValue()){
                node=node.getLeft();
            }else if (value<node.getValue()){
                node=node.getRight();
            }else{
                return node;
            }
        }
        return null;
    }
    public void insert(int value){
        root=insert(value,root);
    }
    private AVLNode insert(int value,AVLNode node){
        if (node==null){
            node=new AVLNode(value);
            node.setHeight(1);
            return node;
        }
        if (value>node.getValue()){
            node.setRight(insert(value,node.getRight()));
            int lh=getHeight(node.getLeft());
            int rh=getHeight(node.getRight());
            int balance=Math.abs(lh-rh);
            System.out.println(balance);
            if (balance>1) {
                if (value < node.getRight().getValue()) {
                    node = RL(node);
                } else {
                    node = LL(node);
                }
            }
        }else if (value<node.getValue()){
            node.setLeft(insert(value,node.getLeft()));
            int lh=getHeight(node.getLeft());
            int rh=getHeight(node.getRight());
            int balance=Math.abs(lh-rh);
            System.out.println(balance);
            if (balance>1) {
                if (value > node.getLeft().getValue()) {
                    node = LR(node);
                } else {
                    node = RR(node);
                }
            }
        }else{
            return node;
        }
        int h=(node.getLeft()==null?0:node.getLeft().getHeight())+(node.getRight()==null?0:node.getRight().getHeight());
        node.setHeight(h+1);
        System.out.println(node);
//        int h=Math.max(getHeight(node.getLeft()),getHeight(node.getRight()))+1;
//        System.out.println(value+"height:"+h);
//        node.setHeight(h);
        return node;
    }
    public String toString(){
        return root.toString();
    }
}
