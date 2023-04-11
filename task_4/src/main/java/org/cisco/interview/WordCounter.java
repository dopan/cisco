package org.cisco.interview;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public interface WordCounter {
    Map<String, Long> wordCounts(Path path) throws IOException;
}
