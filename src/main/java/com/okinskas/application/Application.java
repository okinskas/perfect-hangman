package com.okinskas.application;

import com.okinskas.dictionary.DictionaryFileReader;
import com.okinskas.dictionary.DictionaryReader;
import com.okinskas.dictionary.DictionaryAnalyser;
import com.okinskas.hangman.Hangman;
import com.okinskas.hangman.HangmanRegexBuilderFactory;
import com.okinskas.hangman.HangmanService;
import com.okinskas.views.TerminalUI;
import com.okinskas.views.UI;

import java.util.Properties;

public class Application {

    private Application() {

        // Load properties
        Properties properties = ApplicationProperties.getProperties();

        // Compose com.okinskas.application
        DictionaryReader reader = new DictionaryFileReader(properties.getProperty("dictionaryPath"));

        HangmanService hangman = new Hangman(
                new DictionaryAnalyser(reader),
                new HangmanRegexBuilderFactory()
        );

        // Run com.okinskas.application
        UI app = new TerminalUI(hangman);
        app.run();
    }

    public static void main(String[] args) {
        new Application();
    }
}