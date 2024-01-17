package Stack;

import java.util.EmptyStackException;

public class MainPostfix {

    public static void main(String[] args) {
        PostfixExpression cal = new PostfixExpression();
        String op = " 23*73/+";
        System.out.println(cal.postfixCal(op));
    }
}

class PostfixExpression {

    LinkedStack linkedStack = new LinkedStack();

    public int postfixCal(String str) {
        int res = 0;
        char[] arr = str.trim().toCharArray();
        for (char c : arr) {
            if (c >= '0' && c <= '9') {
                linkedStack.push(Character.getNumericValue(c));
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                int temp1 = linkedStack.pop();
                int temp2 = linkedStack.pop();
                linkedStack.push(applyOperator(c, temp1, temp2));
            }
        }
        res = linkedStack.pop();
        return res;
    }

    int applyOperator(char operator, int operand1, int operand2) {
        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                if (operand1 != 0) {
                    return operand2 / operand1;
                } else {
                    throw new ArithmeticException("Cannot divide by zero");
                }
            default:
                break;
        }
        return 0;
    }

    class Node {

        public int info;
        public Node next;

        public Node(int x, Node p) {
            info = x;
            next = p;
        }

        public Node(int x) {
            this(x, null);
        }
    }

    class LinkedStack {

        protected Node head;

        public LinkedStack() {
            head = null;
        }

        public boolean isEmpty() {
            return (head == null);
        }

        public void push(int x) {
            head = new Node(x, head);
        }

        int top() throws EmptyStackException {
            if (isEmpty()) {
                throw new EmptyStackException();
            }
            return (head.info);
        }

        public int pop() throws EmptyStackException {
            if (isEmpty()) {
                throw new EmptyStackException();
            }
            int x = head.info;
            head = head.next;
            return (x);
        }
    }
}
