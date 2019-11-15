package tree.AVL;

import java.util.logging.Logger;

public class AVLDemo {
    private static Logger logger = Logger.getLogger(AVLDemo.class.getName());

    public static void main(String args[]) throws Exception {
        AVLTree tree=new AVLTree();
        for (int i=0;i<3;i++){
            tree.insert1(i);
        }
        System.out.println(tree);
    }
}
