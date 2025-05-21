package com.csg.test.file;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public abstract class FileProcessor {

    private static final Logger log = Logger.getLogger(FileProcessor.class.getName());

    protected String[] words;
    protected String filePath;

    protected FileProcessor(String filePath) {
        this.filePath = filePath;
    }

    public abstract void extractText();

    protected void checkFile() {
        log.info("checkFile = " + filePath);
        File file = new File(filePath);
        if (!file.exists()) throw new FileException("file not exists.");
    }

    protected void validateText(String text) {
        log.info("validateText = " + text);
        if (text.isBlank()) throw new FileException("text content is empty");
    }

    public long countUpperCase() {
        return Arrays.stream(words).filter(word -> word.matches("[A-Z][a-zA-Z]*")).count();
    }

    public List<String> getAllWordsMoreThan5() {
        return Arrays.stream(words).filter(word -> word.length() > 5).toList();
    }

}
