package com.project.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class FileUtils {
    public static String readFileAsString(String filePath) throws IOException {
        InputStream is = FileUtils.class.getClassLoader().getResourceAsStream(filePath);
        if (is == null) {
            throw new IOException("File not found: " + filePath);
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining(System.lineSeparator()));
        }
    }
}
