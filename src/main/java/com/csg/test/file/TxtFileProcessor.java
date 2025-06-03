package com.csg.test.file;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TxtFileProcessor extends FileProcessor {

    public TxtFileProcessor(String filePath) {
        super(filePath);
    }

    @Override
    public void extractText() {
        checkFile();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath), StandardCharsets.UTF_8)) {
            String content = processReader(reader);
            validateText(content);
            this.words = content.split("\\W+");
        } catch (Exception e) {
            throw new FileException("Error reading file: " + e.getMessage());
        }
    }
}
