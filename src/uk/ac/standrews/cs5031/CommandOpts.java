package uk.ac.standrews.cs5031;

public class CommandOpts {

    public int maxguesses;
    public int maxhints;

    String wordsource;

    CommandOpts(String[] args) {
        maxguesses = 10;
        maxhints = 2;

        wordsource = "";

        for(int i = 0; i < args.length; ++i) {
            switch (args[i]) {
                case "--guesses":
                    maxguesses = Integer.parseInt(args[i + 1]);
                    i++;
                    break;
                case "--hints":
                    maxhints = Integer.parseInt(args[i + 1]);
                    i++;
                    break;
                default:
                    wordsource = args[i];
                    break;
            }
        }
    }
}