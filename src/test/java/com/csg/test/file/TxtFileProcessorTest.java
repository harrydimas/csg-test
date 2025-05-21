package com.csg.test.file;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class TxtFileProcessorTest {

    private TxtFileProcessor txtFileProcessor;

    @TempDir
    private Path tempDir;

    @BeforeEach
    void setUp() {
        File tempFile = new File(tempDir.toFile(), "test.txt");
        try {
            tempFile.createNewFile();
            txtFileProcessor = new TxtFileProcessor(tempFile.getAbsolutePath());
        } catch (Exception e) {
            fail("Failed to create test file: " + e.getMessage());
        }
    }

    @Test
    void testExtractText_ValidText() {
        String testContent = "Lorem ipsum dolor sit amet";
        try {
            Files.writeString(Paths.get(txtFileProcessor.filePath), testContent);
            txtFileProcessor.extractText();

            assertEquals(5, txtFileProcessor.words.length);
            assertTrue(Arrays.asList(txtFileProcessor.words).containsAll(
                Arrays.asList("Lorem", "ipsum", "dolor", "sit", "amet")));
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    void testCountUpperCase() {
        String testContent = "Lorem ipsum Dolor sit Amet";
        try {
            Files.writeString(Paths.get(txtFileProcessor.filePath), testContent);
            txtFileProcessor.extractText();

            assertEquals(3, txtFileProcessor.countUpperCase());
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    void testGetAllWordsMoreThan5() {
        String testContent = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua";
        try {
            Files.writeString(Paths.get(txtFileProcessor.filePath), testContent);
            txtFileProcessor.extractText();

            assertEquals(8, txtFileProcessor.getAllWordsMoreThan5().size());
            assertTrue(txtFileProcessor.getAllWordsMoreThan5().containsAll(
                Arrays.asList("consectetur", "adipiscing", "eiusmod", "tempor", "incididunt", "labore", "aliqua")));
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }
}
