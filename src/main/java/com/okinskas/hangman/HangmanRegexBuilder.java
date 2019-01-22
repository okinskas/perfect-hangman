package com.okinskas.hangman;

import java.util.ArrayList;
import java.util.List;

public class HangmanRegexBuilder {

    private List<String> regex;
    private List<Character> excluded;
    private int wordLen;

    public HangmanRegexBuilder(int wordLen) {
        this.wordLen = wordLen;
        excluded = new ArrayList<>();
        generateInitialRegex();
    }

    public String buildRegex() {
        StringBuilder builder = new StringBuilder();
        for (String characterRegex : regex) {
            builder.append(characterRegex);
        }
        return builder.toString();
    }

    public List<String> getRegexAsList() {
        return regex;
    }

    public void excludeCharacter(Character c) {
        excluded.add(c);
        updateRegexAsList();
    }

    public void addFoundCharacter(Character c, List<Integer> positions) {
        for (Integer position : positions) {
            regex.set(position, c.toString());
        }
        excludeCharacter(c);
    }

    public boolean isConstantRegex() {
        for (String character : regex) {
            if (character.contains("[")) {
                return false;
            }
        }
        return true;
    }

    private void generateInitialRegex() {
        regex = new ArrayList<>();
        for (int i = 0; i < wordLen; i++) {
            regex.add(getUnknownCharacterSpace());
        }
    }

    private void updateRegexAsList() {
        for (int i = 0; i < regex.size(); i++) {
            if (regex.get(i).contains("[a-z")) {
                regex.set(i, getUnknownCharacterSpace());
            }
        }
    }

    private String getUnknownCharacterSpace() {
        StringBuilder builder = new StringBuilder();
        builder.append("[a-z");
        if (!excluded.isEmpty()) {
            builder.append("&&[^");
            for (Character c : excluded) {
                builder.append(c);
            }
            builder.append("]");
        }
        builder.append("]");
        return builder.toString();
    }
}
