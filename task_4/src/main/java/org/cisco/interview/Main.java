package org.cisco.interview;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        Path filePath = Paths.get("src", "main", "resources", "words.txt");
        Map<String, Long> result = new WordCounterImpl().wordCounts(filePath);
        result.entrySet().stream()
                .sorted(((o1, o2) -> o2.getValue().compareTo(o1.getValue())))
                .forEach(System.out::println);
    }
}