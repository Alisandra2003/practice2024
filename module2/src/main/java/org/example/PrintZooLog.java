package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PrintZooLog {
    private static final Logger logger = LogManager.getLogger(PrintZooLog.class);
    private static final Path PATH =
            Paths.get("module2/src/main/resources/food_records.txt");
    private final ZooAccountingLog zooAccountingLog;
    private final List<Entry> entryList;

    public PrintZooLog(String fileName) throws URISyntaxException {
        logger.info("Создание printZooLog.");
        ReadFile fileReader = new ReadFile();
        entryList = fileReader.readEntriesFromFile(fileName);
        zooAccountingLog = new ZooAccountingLog();
        logger.info("ZooLog создан.");
    }

    public void printFindAnimalMostEatLastMonth() {
        logger.info("Вызов printFindAnimalMostEatLastMonth.");
        System.out.println("Животное, которое съело больше всего корма за последний месяц, " +
        zooAccountingLog.findAnimalMostEatLastMonth(entryList).toString());
    }

    public void printMonthMostDiferenseEat() {
        logger.info("Вызов printMonthMostDiferenseEat.");
        System.out.println("Mесяца, в которыe животные получают самое разнообразные питание: ");
        ZooAccountingLog.monthMostDiferenseEat(entryList)
                .forEach(entry -> {
                    System.out.println(entry.toString());
                });
    }

    public void printFindUnconsumedFoods() {
        logger.info("Вызов printFindUnconsumedFoods.");
        System.out.println("Для каждого животного, найти продукт, которое оно ело в прошлом месяце, но не ело в этом");
        Map<AnimalClass, Set<String>> unconsumedFoods = ZooAccountingLog.findUnconsumedFoods(entryList);
        for (Map.Entry<AnimalClass, Set<String>> entry : unconsumedFoods.entrySet()) {
            System.out.println("Животное: " + entry.getKey());
            System.out.println("Еда: " + entry.getValue());
            System.out.println("----------------------------------");
        }
    }
}