package com.okinskas.hangman;

import com.okinskas.dictionary.DictionaryAnalyser;

import java.util.ArrayList;
import java.util.List;

public class Hangman implements HangmanService {

    private String word;
    private DictionaryAnalyser analyser;
    private HangmanRegexBuilder regexBuilder;
    private HangmanRegexBuilderFactory regexBuilderFactory;
    private boolean wordSolved = false;
    private List<Character> correctGuesses = new ArrayList<>();
    private List<Character> incorrectGuesses = new ArrayList<>();

    public Hangman(DictionaryAnalyser analyser, HangmanRegexBuilderFactory regexBuilderFactory) {
        this.analyser = analyser;
        this.regexBuilderFactory = regexBuilderFactory;
    }

    public void startNewGame(String word) {
        this.word = word;
        wordSolved = false;
        correctGuesses = new ArrayList<>();
        incorrectGuesses = new ArrayList<>();
        regexBuilder = regexBuilderFactory.getHangmanRegexBuilder(word.length());
        analyser.reset();
        analyser.filterByRegex(regexBuilder);
    }

    public boolean isWordSolved() {
        return wordSolved;
    }

    public void makeGuess() {
        Character guess = analyser.getNextGuess();
        if (word.contains(guess.toString())) {
            correctGuesses.add(guess);
            List<Integer> positions = findAllPositionsOfCharacter(guess);
            regexBuilder.addFoundCharacter(guess, positions);
            wordSolved = regexBuilder.isConstantRegex();
        } else {
            incorrectGuesses.add(guess);
            regexBuilder.excludeCharacter(guess);
        }
        analyser.filterByRegex(regexBuilder);
    }

    public List<Character> getCorrectGuesses() {
        return correctGuesses;
    }

    public List<Character> getIncorrectGuesses() {
        return incorrectGuesses;
    }

    public char[] getHangmanSequence() {
        List<String> regex = regexBuilder.getRegexAsList();
        char[] stateOfWord = new char[regex.size()];

        for (int i = 0; i < stateOfWord.length; i++) {
            if (regex.get(i).length() > 1) {
                stateOfWord[i] = '_';
            } else {
                stateOfWord[i] = regex.get(i).charAt(0);
            }
        }
        return stateOfWord;
    }

    private List<Integer> findAllPositionsOfCharacter(Character c) {
        List<Integer> characterPositions = new ArrayList<>();
        String sub = word;
        int trackedPosition = 0;
        for (;;) {
            int position = sub.indexOf(c);
            if (position < 0) {
                break;
            }
            sub = sub.substring(position + 1);
            trackedPosition += position;
            characterPositions.add(trackedPosition);
            trackedPosition += 1;
        }
        return characterPositions;
    }
}
