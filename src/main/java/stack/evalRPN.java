package stack;

import java.util.Stack;
import java.util.logging.Logger;

public class evalRPN {
    private static Logger logger = Logger.getLogger(evalRPN.class.getName());

    public static int evalRPN(String[] tokens) {
        Stack<String> stack=new Stack<>();
        for(String s:tokens){
            if (s.equals("+")){
                int x=Integer.parseInt(stack.pop());
                int y=Integer.parseInt(stack.pop());
                int z=x+y;
                stack.push(String.valueOf(z));
            }else if (s.equals("-")){
                int x=Integer.parseInt(stack.pop());
                int y=Integer.parseInt(stack.pop());
                int z=y-x;
                stack.push(String.valueOf(z));
            }else if (s.equals("*")){
                int x=Integer.parseInt(stack.pop());
                int y=Integer.parseInt(stack.pop());
                int z=x*y;
                stack.push(String.valueOf(z));
            }else if (s.equals("/")){
                int x=Integer.parseInt(stack.pop());
                int y=Integer.parseInt(stack.pop());
                int z=y/x;
                stack.push(String.valueOf(z));
            }else{
                stack.push(s);
            }
        }
        return Integer.parseInt(stack.pop());
    }
}
