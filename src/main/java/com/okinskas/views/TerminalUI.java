package com.okinskas.views;

import com.okinskas.hangman.HangmanService;

import java.util.Scanner;

public class TerminalUI implements UI {

    private HangmanService hangman;

    public TerminalUI(HangmanService hangman) {
        this.hangman = hangman;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String word = scanner.nextLine();
            hangman.startNewGame(word);
            outputGameState();
            while (!hangman.isWordSolved()) {
                hangman.makeGuess();
                outputGameState();
            }
        }
    }

    private String getFormattedWord() {
        StringBuilder builder = new StringBuilder();
        char[] wordSequence = hangman.getHangmanSequence();
        for (char c : wordSequence) {
            builder.append(String.format("%s ", c));
        }
        return builder.toString();
    }

    private void outputGameState() {
        StringBuilder builder = new StringBuilder();
        if (hangman.isWordSolved()) {
            builder.append(getSuccessText());
        } else {
            builder.append(String.format(": %s\n", hangman.getCorrectGuesses()));
            builder.append(String.format("Correct guesses: %s\n", hangman.getCorrectGuesses()));
            builder.append(String.format("Inorrect guesses: %s\n", hangman.getIncorrectGuesses()));
        }

        builder.append(String.format("%s\n", getFormattedWord()));
        System.out.println(builder.toString());
    }

    private String getSuccessText() {
        StringBuilder builder = new StringBuilder();
        builder.append("The bot has solved the word.\n");
        int correctCount = hangman.getCorrectGuesses().size();
        int incorrectCount = hangman.getIncorrectGuesses().size();
        int totalCount = correctCount + incorrectCount;
        builder.append(String.format("Correct guess count: %s\n", correctCount));
        builder.append(String.format("Inorrect guess count: %s\n", incorrectCount));
        builder.append(String.format("Total guesses: %s\n", totalCount));
        return builder.toString();
    }
}
