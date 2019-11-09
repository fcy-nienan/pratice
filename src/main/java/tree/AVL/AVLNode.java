package tree.AVL;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AVLNode {
    private int value;
    private int height;
    private AVLNode left;
    private AVLNode right;
    public AVLNode(int value){
        this.value=value;
    }
}
