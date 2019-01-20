import java.util.List;

public class Dictionary {

    private static List<String> baseDictionary;

    public static List<String> getBaseDictionary(DictionaryReader dictionaryReader) {
        if (baseDictionary != null) {
            return  baseDictionary;
        }
        baseDictionary = dictionaryReader.getDictionary();
        return baseDictionary;
    }
}
