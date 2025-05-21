package com.csg.test.file;

public class FileProcessorFactory {

    public static FileProcessor createProcessor(String fileName) {
        if (fileName.endsWith(".txt")) {
            return new TxtFileProcessor(fileName);
        } else if (fileName.endsWith(".html")) {
            return new HtmlFileProcessor(fileName);
        } else {
            throw new IllegalArgumentException("Unsupported file type: " + fileName);
        }
    }
}
