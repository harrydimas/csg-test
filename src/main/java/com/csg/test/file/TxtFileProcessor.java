package com.csg.test.file;

import java.nio.file.Files;
import java.nio.file.Paths;

public class TxtFileProcessor extends FileProcessor {

    public TxtFileProcessor(String filePath) {
        super(filePath);
    }

    @Override
    public void extractText() {
        checkFile();
        try {
            String text = Files.readString(Paths.get(filePath));
            validateText(text);
            words = text.split("\\W+");
        } catch (Exception e) {
            throw new FileException(e.getMessage());
        }
    }
}
