package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;

@Component
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class CsvFileConfig {

    @Value("${file.csv.location}")
    private String csvFilePath;

    public Stream<String> getFileStreamLines() throws IOException {
        return Files.lines(new File(csvFilePath).toPath());
    }

}
