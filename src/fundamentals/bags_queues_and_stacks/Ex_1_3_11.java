package fundamentals.bags_queues_and_stacks;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.List;

/**
 * Write a program EvaluatePostfix that takes a postfix expression from standard
 * input, evaluates it, and prints the value. (Piping the output of your program from
 * the previous exercise to this program gives equivalent behavior to Evaluate.
 */
public class Ex_1_3_11 {
    private static final List<Character> operators = Arrays.asList('+', '-', '*', '/');

    public static void main(String[] args) {
        String input = StdIn.readAll();
        StdOut.print(eval(input));
    }

    private static int eval(String input) {
        Stack<Integer> numsStack = new Stack<>();

        for (char c : input.toCharArray()) {
            if(Character.isDigit(c)) {
                numsStack.push(Character.getNumericValue(c));
            } else if(operators.contains(c)) {
                int left = numsStack.pop();
                int right = numsStack.pop();
                switch(c) {
                    case '*':
                        numsStack.push(left * right);
                        break;
                    case '/':
                        numsStack.push(left / right);
                        break;
                    case '+':
                        numsStack.push(left + right);
                        break;
                    case '-':
                        numsStack.push(left - right);
                        break;
                }
            } else if(c == ' ') {
            } else {
                throw new RuntimeException("Parse error!");
            }
        }
        return numsStack.pop();
    }

}
