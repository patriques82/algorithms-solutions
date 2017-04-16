package fundamentals.bags_queues_and_stacks;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.List;

/**
 * Created by patriknygren on 2017-04-09.
 */
public class Ex_1_3_10 {
    private static final List<Character> operators = Arrays.asList('+', '-', '*', '/');

    public static void main(String[] args) {
        String input = StdIn.readAll();
        StdOut.print(toPostfix(input));
    }

    private static String toPostfix(String input) {

        Stack<Character> numsStack = new Stack<>();
        Stack<Character> operatorStack = new Stack<>();

        StringBuilder builder = new StringBuilder();
        for (char c : input.toCharArray()) {
            if(operators.contains(c)) {
                operatorStack.push(c);
            } else if(c == ')') {
                char right = numsStack.pop();
                builder.append(numsStack.pop());
                builder.append(right);
                builder.append(operatorStack.pop());
            } else if(Character.isDigit(c)) {
                numsStack.push(c);
                if(numsStack.size() == 2) {
                    char right = numsStack.pop();
                    builder.append(numsStack.pop());
                    builder.append(right);
                    builder.append(operatorStack.pop());
                }
            } else if(c == '(' || c == ' ') {
            } else {
                throw new RuntimeException("Parse error!");
            }
        }
        if(!operatorStack.isEmpty()) {
            builder.append(operatorStack.pop());
        }
        return builder.toString();
    }

}
