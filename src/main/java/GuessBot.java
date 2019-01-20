import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GuessBot {

    private String word;
    private DictionaryAnalyser analyser;
    private HangmanRegexBuilder regexBuilder;
    private boolean wordSolved;
    private int correctGuessCount;
    private int incorrectGuessCount;

    public GuessBot(String word, DictionaryReader dictionaryReader) {
        this.word = word;
        wordSolved = false;
        correctGuessCount = 0;
        incorrectGuessCount = 0;
        analyser = new DictionaryAnalyser(dictionaryReader);
        regexBuilder = new HangmanRegexBuilder(word.length());
        analyser.filterByRegex(regexBuilder);
    }

    public Character calculateNextLetter() {
        Map<Character, Long> charCounts = analyser.getCharacterCounts();
        Character nextGuess = charCounts
                .entrySet()
                .stream()
                .max((prev, current) -> {
            int prevMax = prev.getValue().intValue();
            int currentMax = current.getValue().intValue();
            return prevMax > currentMax ? 1 : -1;
        }).get().getKey();
        return nextGuess;
    }

    public boolean wordContainsCharacter(Character c) {
        return word.contains(c.toString());
    }

    public void makeGuess() {
        Character guess = calculateNextLetter();
        if (wordContainsCharacter(guess)) {
            List<Integer> positions = findAllPositionsOfCharacter(guess);
            regexBuilder.addFoundCharacter(guess, positions);
            correctGuessCount++;
            wordSolved = regexBuilder.isConstantRegex();
        } else {
            regexBuilder.excludeCharacter(guess);
            incorrectGuessCount++;
        }
        analyser.filterByRegex(regexBuilder);
    }

    public boolean isWordSolved() {
        return wordSolved;
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
