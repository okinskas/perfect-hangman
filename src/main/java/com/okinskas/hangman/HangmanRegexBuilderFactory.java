package com.okinskas.hangman;

public class HangmanRegexBuilderFactory {

    public HangmanRegexBuilder getHangmanRegexBuilder(int wordLength) {
        return new HangmanRegexBuilder(wordLength);
    }
}
