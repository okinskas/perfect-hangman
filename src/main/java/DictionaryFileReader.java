import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DictionaryFileReader implements DictionaryReader {

    public ArrayList<String> getDictionary() {
        // Relies on global property "dictionaryPath" to provide path of .txt file.
        String dictPath = ApplicationProperties.getProperties().getProperty("dictionaryPath");
        return readDictionaryFromFile(dictPath);
    }

    private ArrayList<String> readDictionaryFromFile(String filePath) {
        ArrayList<String> dictionary = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            while (bufferedReader.ready()) {
                dictionary.add(bufferedReader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return dictionary;
    }
}
