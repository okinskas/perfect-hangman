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

public class Application implements Runnable {

    public void run() {

        // Load properties
        Properties properties = ApplicationProperties.getProperties();

        // Compose
        DictionaryReader reader = new DictionaryFileReader(
                properties.getProperty(
                        PropertyKeys.DICTIONARY_FILE_PATH.toString()
                )
        );

        HangmanService hangman = new Hangman(
                new DictionaryAnalyser(reader),
                new HangmanRegexBuilderFactory()
        );

        // Run
        UI app = new TerminalUI(hangman);
        app.launch();

    }

    public static void main(String[] args) {

        Thread gameThread = new Thread(new Application());
        gameThread.start();
    }
}
