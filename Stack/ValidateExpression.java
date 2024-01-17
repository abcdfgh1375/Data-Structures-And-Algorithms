
package Stack;

import java.util.Stack;

public class ValidateExpression {
    public static void main(String[] args) {
        ValidateExpression check = new ValidateExpression();
        String str = "({[ ])}";
        String str2 = "( )(( )){([( )])}";
        System.out.println(check.checkValidateExpression(str));
        System.out.println(check.checkValidateExpression(str2));
    }
    
    Node head;
    ValidateExpression(){
        head = null;
    }
    public boolean checkValidateExpression(String str){
        Stack<Character> stack = new Stack<>();
        for(char c : str.toCharArray()){
            if(c == '{'||c == '('||c == '['){
                stack.push(c); 
            } else if (c == '}'||c == ')'||c == ']') {
                if (stack.isEmpty() || !isValidPair(stack.pop(),c)) {
                    return false; 
        } 
    }
    } return true;
}
    public boolean isValidPair(char open, char close){
        return (open == '{' && close == '}') ||
               (open == '(' && close == ')') ||
               (open == '[' && close == ']');
    }
}
