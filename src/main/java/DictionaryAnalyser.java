import java.util.*;
import java.util.stream.Collectors;

public class DictionaryAnalyser {

    private List<String> dictionary;
    private HashMap<Character, Long> characterCountMap;
    private List<String> latestRegexAsList; // Make this more safe

    // Need to remove this dictionaryReader dependency sequence
    public DictionaryAnalyser(DictionaryReader dictionaryReader) {
        this.dictionary = Dictionary.getBaseDictionary(dictionaryReader);
        characterCountMap = new HashMap<>();
        latestRegexAsList = List.of("*"); // Maybe change to Java 8
        setCharacterCounts();
    }

    public void filterByRegex(HangmanRegexBuilder regexBuilder) {
        this.dictionary = dictionary
                .stream()
                .filter(word -> word.matches(regexBuilder.buildRegex()))
                .collect(Collectors.toList());
        latestRegexAsList = regexBuilder.getRegexAsList();
        setCharacterCounts();
    }

    public Map<Character, Long> getCharacterCounts() {
        return characterCountMap;
    }

    // This logic used regex as String, needs to be updated to use regex as List<String>
    private void setCharacterCounts() {
        characterCountMap.clear();
        boolean forceCount = latestRegexAsList.get(0).equals("*");
        for (String word : dictionary) {
            char[] chars = word.toCharArray();
            // Avoid repeated characters because it should not influence decision.
            HashSet<Character> charactersSeen = new HashSet<>();
            for (int i = 0; i < chars.length; i++) {
                if ((forceCount || latestRegexAsList.get(i).contains("[")) // real char not found yet
                        && !charactersSeen.contains(chars[i])) {
                    characterCountMap.compute(chars[i], (character, count) -> count == null ? 1L : count + 1);
                    charactersSeen.add(chars[i]);
                }
            }
        }
    }
}
