package com.csg.test.file;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.nio.file.Files;
import java.nio.file.Paths;

public class HtmlFileProcessor extends FileProcessor {

    public HtmlFileProcessor(String filePath) {
        super(filePath);
    }

    @Override
    public void extractText() {
        checkFile();
        try {
            String html = Files.readString(Paths.get(filePath));
            Document doc = Jsoup.parse(html);
            String text = doc.text();
            validateText(text);
            words = text.split("\\W+");
        } catch (Exception e) {
            throw new FileException(e.getMessage());
        }
    }
}
