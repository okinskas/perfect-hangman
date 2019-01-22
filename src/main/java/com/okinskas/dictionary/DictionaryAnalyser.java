package com.okinskas.dictionary;

import com.okinskas.hangman.HangmanRegexBuilder;

import java.util.*;
import java.util.stream.Collectors;

public class DictionaryAnalyser {

    private List<String> baseDictionary;
    private List<String> dictionary;
    private HashMap<Character, Long> characterCountMap;
    private List<String> latestRegexAsList;

    // Need to remove this dictionaryReader dependency sequence
    public DictionaryAnalyser(DictionaryReader dictionaryReader) {
        baseDictionary = dictionaryReader.getDictionary();
        reset();
    }

    public void filterByRegex(HangmanRegexBuilder regexBuilder) {
        this.dictionary = dictionary
                .stream()
                .filter(word -> word.matches(regexBuilder.buildRegex()))
                .collect(Collectors.toList());
        latestRegexAsList = regexBuilder.getRegexAsList();
        setCharacterCounts();
    }

    public Character getNextGuess() {
        return characterCountMap
                .entrySet()
                .stream()
                .max((prev, current) -> {
                    int prevMax = prev.getValue().intValue();
                    int currentMax = current.getValue().intValue();
                    return prevMax > currentMax ? 1 : -1;
                }).get().getKey();
    }

    public void reset() {
        dictionary = baseDictionary;
        characterCountMap = new HashMap<>();
        latestRegexAsList = null;
    }

    private void setCharacterCounts() {
        characterCountMap.clear();
        for (String word : dictionary) {
            char[] chars = word.toCharArray();
            // Avoid repeated characters because it should not influence decision.
            HashSet<Character> charactersSeen = new HashSet<>();
            for (int i = 0; i < chars.length; i++) {
                if ((latestRegexAsList.get(i).contains("[")) // real char not found yet
                        && !charactersSeen.contains(chars[i])) {
                    characterCountMap.compute(chars[i], (character, count) -> count == null ? 1L : count + 1);
                    charactersSeen.add(chars[i]);
                }
            }
        }
    }
}
