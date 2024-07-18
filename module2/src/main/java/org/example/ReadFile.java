package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ReadFile {
    private static final Logger logger = LogManager.getLogger(ReadFile.class);

    public List<Entry> readEntriesFromFile(String fileName) throws URISyntaxException {
        logger.info("Начало чтения записей из файла.");
        List<Entry> entries;
        URI uri = ClassLoader.getSystemResource(fileName).toURI();
        try(Stream<String> streamFromFiles = Files.lines(Path.of(uri))) {
            entries = streamFromFiles
                    .map(line -> {
                        var parts = line.split(";");
                        LocalDate date = LocalDate.parse(parts[0]);
                        AnimalClass animalClass = AnimalClass.fromString(parts[1]);
                        List<FoodList> food = Arrays.stream(parts[2].split(",")).map(FoodList::fromString).toList();
                        double foodWeight = Double.parseDouble(parts[3]);
                        return new Entry(date, animalClass, food, foodWeight);
                    })
                    .toList();
        } catch (IOException e) {
            logger.error("Ошибка при чтении файла!\n" + e.getMessage());
            throw new RuntimeException(e);
        }
        logger.info("Записи прочитаны.");
        return entries;
    }
}
