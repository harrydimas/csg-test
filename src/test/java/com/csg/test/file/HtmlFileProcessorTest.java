package com.csg.test.file;

import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class HtmlFileProcessorTest {

    @Mock
    private Document mockedDocument;

    private HtmlFileProcessor htmlFileProcessor;

    @TempDir
    private Path tempDir;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        File tempFile = new File(tempDir.toFile(), "test.html");
        try {
            tempFile.createNewFile();
            htmlFileProcessor = new HtmlFileProcessor(tempFile.getAbsolutePath());
        } catch (Exception e) {
            fail("Failed to create test file: " + e.getMessage());
        }
    }

    @Test
    void testExtractText_ValidHtml() {
        String testHtml = "<html><body><p>Lorem Ipsum</strong></p></body></html>";
        try {
            Files.writeString(Paths.get(htmlFileProcessor.filePath), testHtml);
            htmlFileProcessor.extractText();

            assertEquals(2, htmlFileProcessor.words.length);
            assertTrue(Arrays.asList(htmlFileProcessor.words).containsAll(
                Arrays.asList("Lorem", "Ipsum")));
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    void testExtractText_InvalidHtml() {
        String invalidHtml = "<html><body><p>";
        try {
            Files.writeString(Paths.get(htmlFileProcessor.filePath), invalidHtml);
            assertThrows(FileException.class, () -> htmlFileProcessor.extractText());
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    void testCountUpperCase_WithHtmlTags() {
        String testHtml = "<html><body><p>Hello World Lorem ipsum dolor sit amet</p></body></html>";
        try {
            Files.writeString(Paths.get(htmlFileProcessor.filePath), testHtml);
            htmlFileProcessor.extractText();

            assertEquals(3, htmlFileProcessor.countUpperCase());
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }
}
