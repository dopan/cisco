package org.cisco.interview;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;

public class WordCounterImpl implements WordCounter {

    @Override
    public Map<String, Long> wordCounts(Path path) throws IOException {
        if (path == null) return null;
        return Arrays.stream(new String(Files.readAllBytes(path), StandardCharsets.UTF_8).split("\\W+"))
                .collect(Collectors.groupingBy(Function.<String>identity(), HashMap::new, counting()))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
    }
}
