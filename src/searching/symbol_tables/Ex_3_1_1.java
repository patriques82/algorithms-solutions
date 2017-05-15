package searching.symbol_tables;

import edu.princeton.cs.algs4.BinarySearchST;
import edu.princeton.cs.algs4.StdIn;

/**
 * Write a client that creates a symbol table mapping letter grades to numerical
 * scores, as in the table below, then reads from standard input a list of letter grades and
 * computes and prints the GPA (the average of the numbers corresponding to the grades).
 * <p>
 * A+   A    A-   B+   B    B-   C+   C    C-   D    F
 * 4.33 4.00 3.67 3.33 3.00 2.67 2.33 2.00 1.67 1.00 0.00
 */
public class Ex_3_1_1 {

    public static void main(String[] args) {
        BinarySearchST<Grade, Double> st = new BinarySearchST<>(11);

        st.put(new Grade('A', Sign.plus), 4.33);
        st.put(new Grade('A', Sign.neutral), 4.00);
        st.put(new Grade('A', Sign.minus), 3.67);
        st.put(new Grade('B', Sign.plus), 3.33);
        st.put(new Grade('B', Sign.neutral), 3.00);
        st.put(new Grade('B', Sign.minus), 2.67);
        st.put(new Grade('C', Sign.plus), 2.33);
        st.put(new Grade('C', Sign.neutral), 2.00);
        st.put(new Grade('C', Sign.minus), 1.67);
        st.put(new Grade('D', Sign.neutral), 1.00);
        st.put(new Grade('F', Sign.neutral), 0.00);

        String line;
        double sum = 0;
        int nr = 0;
        while ((line = StdIn.readLine()) != null) {
            if(line.isEmpty()) break;
            sum += st.get(new Grade(line));
            nr++;
        }
        System.out.println("GPA: " + sum/nr);

    }

    private static class Grade implements Comparable<Grade> {

        private char letter;
        private Sign sign;

        Grade(String grade) {
            char l = grade.charAt(0);
            if (l >= 'A' && l < 'G') {
                this.letter = l;
                if (l == 'D' || l == 'F' || grade.length() == 1) {
                    this.sign = Sign.neutral;
                } else {
                    char s = grade.charAt(1);
                    if (s == '-')
                        this.sign = Sign.minus;
                    else if (s == '+')
                        this.sign = Sign.plus;
                    else
                        throw new IllegalArgumentException("Unknown sign");
                }
            } else {
                throw new IllegalArgumentException("Unknown grade letter");
            }
        }

        Grade(char letter, Sign sign) {
            this.letter = letter;
            this.sign = sign;
        }

        @Override
        public int compareTo(Grade other) {
            boolean smallerLetter = this.letter < other.letter;
            boolean largerLetter = this.letter > other.letter;
            return smallerLetter ? -1 : (largerLetter ? 1 : this.sign.compareTo(other.sign));
        }
    }

    private enum Sign {
        minus, neutral, plus
    }

}
