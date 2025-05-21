package com.csg.test;

import com.csg.test.file.FileException;
import com.csg.test.file.FileProcessor;
import com.csg.test.file.FileProcessorFactory;

import java.util.logging.Logger;

public class Main {

    private static final Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        for (String filePath : args) {
            try {
                FileProcessor parser = FileProcessorFactory.createProcessor(filePath);
                parser.extractText();

                System.out.println("File: " + filePath);
                System.out.println("Capitalized Words: " + parser.countUpperCase());
                System.out.println("Words more than 5 chars: " + parser.getAllWordsMoreThan5());
            } catch (FileException fe) {
                log.severe(fe.getMessage());
            }
        }
    }
}
