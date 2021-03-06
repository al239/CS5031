package uk.ac.standrews.cs5031;

import java.lang.reflect.MalformedParametersException;
import java.util.ArrayList;
import java.util.Scanner;

// The game state
public class GameState {
    public String word; // letters
    public int g;
    public int wrong;
    public int h;

    ArrayList<Character> got;
    ArrayList<Character> not;

    public Scanner sc = new Scanner(System.in).useDelimiter("\n");

    public GameState(String target, int g, int h) {
        //check variables
        if(g < 1 || h < 0) {
            throw new MalformedParametersException();
        }

        this.word = target;
        not = new ArrayList<Character>();
        got = new ArrayList<Character>();

        for(int i = 0; i < target.length(); ++i) {
            if (!not.contains(Character.toLowerCase(target.charAt(i))))
                not.add(Character.toLowerCase(target.charAt(i)));
        }
        //System.out.println(missing);

        this.g = 0; // guesses made
        wrong = g; // guesses remaining
        this.h = h;
    }

    void showWord(String word) {
        for (int i = 0; i < word.length(); ++i) {
            if (got.contains(word.charAt(i))) {
                System.out.print(word.charAt(i));
            } else {
                System.out.print("-");
            }
        }
        System.out.println("");
        // System.out.println(missing);
    }

    boolean guessLetter() {


        System.out.print("Guess a letter or word (? for a hint): ");

        String str = sc.next().toLowerCase();

        return guessLetter(str);
    }

    boolean guessLetter(String str) {
        int i;
        char letter;

        if (str.length() > 1) {
            if (str.toLowerCase().equals(word.toLowerCase())) {
                not.clear();
                return true;
            } else return false;
        }

        letter = str.charAt(0);

        if (letter == '?') {
            hint();
            return false;
        }

        for(i = 0; i < not.size(); ++i) { // Loop over the not got
            if (not.get(i) == letter) {
                not.remove(i);
                got.add(letter);
                g++;
                return true;
            }
        }

        g++; // One more guess
        wrong--;
        return false;
    }


    boolean won() {
        return not.size() == 0;
    }

    boolean lost() {
        return not.size() > 0 && wrong == 0;
    }

    void hint() {
        if (h == 0) {
            System.out.println("No more hints allowed");
        }
        else {
            //fixed bug hint
            System.out.print("Try: ");
            System.out.println(not.get((int)(Math.random()*not.size())));
            h--;
        }

    }
}