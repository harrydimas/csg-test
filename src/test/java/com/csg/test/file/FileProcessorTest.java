package com.csg.test.file;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class FileProcessorTest {

    @Mock
    private File mockedFile;

    private FileProcessor fileProcessor;

    @TempDir
    private Path tempDir;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        fileProcessor = new FileProcessor("test.txt") {
            @Override
            public void extractText() {
            }
        };
    }

    @Test
    void testCheckFile_ExistingFile() {
        File tempFile = new File(tempDir.toFile(), "test.txt");
        try {
            tempFile.createNewFile();
            fileProcessor.filePath = tempFile.getAbsolutePath();

            assertDoesNotThrow(() -> fileProcessor.checkFile());
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    void testCheckFile_NonExistingFile() {
        fileProcessor.filePath = "404.txt";
        assertThrows(FileException.class, () -> fileProcessor.checkFile());
    }

    @Test
    void testValidateText_ValidText() {
        String validText = "Lorem ipsum";
        assertDoesNotThrow(() -> fileProcessor.validateText(validText));
    }

    @Test
    void testValidateText_EmptyText() {
        assertThrows(FileException.class, () -> fileProcessor.validateText(""));
    }

    @Test
    void testValidateText_BlankText() {
        assertThrows(FileException.class, () -> fileProcessor.validateText(" "));
    }
}
