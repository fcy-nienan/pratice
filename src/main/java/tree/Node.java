package tree;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public  class Node {
    public int value;
    public Node left;
    public Node right;
    public Node(int value){
        this.value=value;
    }
}
