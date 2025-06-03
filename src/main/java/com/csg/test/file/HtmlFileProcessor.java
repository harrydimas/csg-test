package com.csg.test.file;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HtmlFileProcessor extends FileProcessor {

    public HtmlFileProcessor(String filePath) {
        super(filePath);
    }

    @Override
    public void extractText() {
        checkFile();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath), StandardCharsets.UTF_8)) {
            String content = processReader(reader);
            Document doc = Jsoup.parse(content);
            String text = doc.text();
            validateText(text);
            this.words = text.split("\\W+");
        } catch (Exception e) {
            throw new FileException("Error reading HTML file: " + e.getMessage());
        }
    }
}
