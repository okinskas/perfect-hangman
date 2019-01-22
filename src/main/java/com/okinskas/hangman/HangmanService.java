package com.okinskas.hangman;

import java.util.List;

public interface HangmanService {

    void startNewGame(String word);
    boolean isWordSolved();
    void makeGuess();
    List<Character> getCorrectGuesses();
    List<Character> getIncorrectGuesses();
    char[] getHangmanSequence();
}
