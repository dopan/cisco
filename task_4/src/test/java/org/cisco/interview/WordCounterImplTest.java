package org.cisco.interview;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class WordCounterImplTest {

    @Test
    void edgeCase() {
        Exception exception = assertThrows(NoSuchFileException.class, () -> {
            new WordCounterImpl().wordCounts(Paths.get("aaa"));
        });
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains("aaa"));
    }

    @Test
    void testWordCounter() throws IOException {
        Path filePath = Paths.get("src", "test", "resources", "testWords1.txt");
        Map<String, Long> result = new WordCounterImpl().wordCounts(filePath);
        assertEquals(5, result.size());
        assertEquals(5, result.get("c"));
        assertEquals(2, result.get("a"));
        assertEquals(1, result.get("b"));
        assertEquals(1, result.get("d"));
        assertEquals(1, result.get("d_"));
    }

    @Test
    void testWordCounterNoSpaces() throws IOException {
        Path filePath = Paths.get("src", "test", "resources", "testWords2.txt");
        Map<String, Long> result = new WordCounterImpl().wordCounts(filePath);
        assertEquals(5, result.size());
        assertEquals(2, result.get("c"));
        assertEquals(3, result.get("a"));
        assertEquals(2, result.get("b"));
        assertEquals(1, result.get("ab"));
        assertEquals(1, result.get("bc"));
    }
}
