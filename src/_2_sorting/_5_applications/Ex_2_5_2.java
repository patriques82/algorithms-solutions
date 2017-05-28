package _2_sorting._5_applications;

import edu.princeton.cs.algs4.StdOut;
import _2_sorting._3_quicksort.Quick;

import java.util.*;

/**
 Write a program that reads a list of words from standard input and prints all twoword
 compound words in the list. For example, if after, thought, and afterthought
 are in the list, then afterthought is a compound word.
 */
public class Ex_2_5_2 {

    public static void main(String[] args) {
        String[] list = {"hand", "a", "blueberry", "handsome", "knowledge", "there", "berry", "aknowledge", "some", "know", "blue"};
        String[] compounds = compounds(list);
        for(String comp : compounds) {
            StdOut.println(comp); // acknowledge, blueberry, handsome
        }
    }

    private static String[] compounds(String[] list) {
        String[] listOfWords = Arrays.copyOf(list, list.length);
        List<Integer> indexes = new ArrayList<>();
        Quick.sort(listOfWords);
        for (int i = 0; i < listOfWords.length-1; i++) {
            String first = listOfWords[i];
            for (int j = i+1; j < listOfWords.length; j++) {
                String wholeWord = listOfWords[j];
                if (wholeWord.startsWith(first)) {
                    for (String second : listOfWords) {
                        String rest = wholeWord.substring(first.length(), wholeWord.length());
                        if (rest.equals(second)) {
                            indexes.add(j);
                        }
                    }
                }
            }
        }
        String[] compounds = new String[indexes.size()];
        for (int i = 0; i < compounds.length; i++) {
            compounds[i] = listOfWords[indexes.get(i)];
        }
        return compounds;
    }
}
