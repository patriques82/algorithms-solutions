package fundamentals.bags_queues_and_stacks;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.List;

/**
 * Created by patriknygren on 2017-04-09.
 */
public class Ex_1_3_9 {

    public static void main(String[] args) {
        String input = "1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )";
        InfixExpressionComplement infixExpressionComp = new InfixExpressionComplement(input);
        StdOut.print(infixExpressionComp.toString()); // ( ( 1 + 2 ) * ( ( 3 - 4 ) * ( 5 - 6 ) )
    }

    static class InfixExpressionComplement {
        private static final List<Character> operators = Arrays.asList('+', '-', '*', '/');
        private final Expression tree;

        public InfixExpressionComplement(String input) {
            this.tree = parse(input);
        }

        private Expression parse(String input) {
            Stack<Expression> expressions = new Stack<>();
            Stack<Character> operatorStack = new Stack<>();
            Stack<Integer> numsStack = new Stack<>();

            for(char c : input.toCharArray()) {
                if(operators.contains(c)) {
                    operatorStack.push(c);
                } else if(Character.isDigit(c)) {
                    numsStack.push(Character.getNumericValue(c));
                } else if(c == ')') {
                    if(operatorStack.isEmpty()) {
                        throw new RuntimeException("Parse error!");
                    } else if(numsStack.size() >= 2) {
                        int right = numsStack.pop();
                        char op = operatorStack.pop();
                        int left = numsStack.pop();
                        expressions.push(new Expression(left, op, right));
                    } else if(expressions.size() >= 2) {
                        Expression right = expressions.pop();
                        char op = operatorStack.pop();
                        Expression left = expressions.pop();
                        expressions.push(new Expression(left, op, right));
                    } else {
                        throw new RuntimeException("Parse error!");
                    }
                }
            }
            return expressions.pop();
        }

        @Override
        public String toString() {
            return tree.toString();
        }

    }

    static class Expression {
        private Expression left;
        private Expression right;
        private char op;

        Expression() {
        }

        public Expression(int left, char op, int right) {
            this.left = new Literal(left);
            this.op = op;
            this.right = new Literal(right);

        }

        public Expression(Expression left, char op, Expression right) {
            this.left = left;
            this.op = op;
            this.right = right;
        }

        @Override
        public String toString() {
            return " (" + left.toString() + " " + op + " " + right.toString() + ") ";
        }

    }

    static class Literal extends Expression {
        private final int numeric;

        public Literal(int numeric) {
            this.numeric = numeric;
        }

        @Override
        public String toString() {
            return String.valueOf(numeric);
        }

    }
}
