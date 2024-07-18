package org.example;


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

    public List<Entry> readEntriesFromFile(String fileName) throws URISyntaxException {
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
            throw new RuntimeException(e);
        }

        return entries;
    }
}
