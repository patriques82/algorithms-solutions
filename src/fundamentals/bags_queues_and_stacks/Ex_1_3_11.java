package fundamentals.bags_queues_and_stacks;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.List;

/**
 * Created by patriknygren on 2017-04-09.
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
