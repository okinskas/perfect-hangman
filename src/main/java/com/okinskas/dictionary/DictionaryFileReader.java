package com.okinskas.dictionary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DictionaryFileReader implements DictionaryReader {

    private String dictionaryPath;

    public DictionaryFileReader(String dictionaryPath) {
        this.dictionaryPath = dictionaryPath;
    }

    public List<String> getDictionary() {
        // Relies on global property "dictionaryPath" to provide path of .txt file.
        return readDictionaryFromFile();
    }

    private List<String> readDictionaryFromFile() {
        List<String> dictionary = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(dictionaryPath));
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
