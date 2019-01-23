package com.okinskas.application;

public enum PropertyKeys {
    DICTIONARY_FILE_PATH ("dictionaryFilePath");

    private final String path;

    PropertyKeys(final String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return path;
    }
}
