package org.example;


import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PrintZooLog {

    private static final Path PATH =
            Paths.get("module2/src/main/resources/food_records.txt");
    private final ZooAccountingLog zooAccountingLog;
    private final List<Entry> entryList;

    public PrintZooLog(String fileName) throws URISyntaxException {

        ReadFile fileReader = new ReadFile();
        entryList = fileReader.readEntriesFromFile(fileName);
        zooAccountingLog = new ZooAccountingLog();

    }

    public void printFindAnimalMostEatLastMonth() {

        System.out.println("Животное, которое съело больше всего корма за последний месяц, " +
        zooAccountingLog.findAnimalMostEatLastMonth(entryList).toString());
    }

    public void printMonthMostDiferenseEat() {

        System.out.println("Mесяца, в которыe животные получают самое разнообразные питание: ");
        ZooAccountingLog.monthMostDiferenseEat(entryList)
                .forEach(entry -> {
                    System.out.println(entry.toString());
                });
    }

    public void printFindUnconsumedFoods() {

        System.out.println("Для каждого животного, найти продукт, которое оно ело в прошлом месяце, но не ело в этом");
        Map<AnimalClass, Set<String>> unconsumedFoods = ZooAccountingLog.findUnconsumedFoods(entryList);
        for (Map.Entry<AnimalClass, Set<String>> entry : unconsumedFoods.entrySet()) {
            System.out.println("Животное: " + entry.getKey());
            System.out.println("Еда: " + entry.getValue());
            System.out.println("----------------------------------");
        }
    }
}