package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class ZooAccountingLog {
    private static final Logger logger = LogManager.getLogger(ZooAccountingLog.class);

    // Животное, которое съело больше всего корма за последний месяц
    public Optional<AnimalClass> findAnimalMostEatLastMonth(List<Entry> entryList) {
        logger.info("Поиск животного, которое съело больше всего корма за последний месяц.");

        LocalDate lastMonth = LocalDate.now().minusMonths(1);

        Map<AnimalClass, Double> fatAnimal =
                entryList.stream()
                        .filter(entry -> entry.getDate().isAfter(lastMonth))
                        .collect(Collectors.groupingBy(Entry::getAnimalClass, Collectors.summingDouble(Entry::getFoodWeight)));

        return fatAnimal.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
    }

    //  Найти месяц, в который животные получают самое разнообразные питание
    public static List<Month> monthMostDiferenseEat(List<Entry> entryLists) {
        logger.info(" Поиск месяца, в который животные получают самое разнообразные питание");
        Map<Month, Set<FoodList>> monthWithEat = new HashMap<>();
        for(Month month : Month.values()) {
            monthWithEat.put(month, new HashSet<>());
        }
        entryLists.forEach(entryList -> entryList.getFoodList().forEach(foodlist -> monthWithEat.get(entryList.getDate().getMonth())
                .add(foodlist)));
        int maxCountEat = monthWithEat.values().stream()
                .map(Set::size)
                .max(Comparator.naturalOrder())
                .orElseThrow();
        return monthWithEat.entrySet().stream()
                .filter(month -> month.getValue().size() == maxCountEat)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
    // Для каждого животного, найти продукт, которое оно ело в прошлом месяце, но не ело в этом
    public static Map<AnimalClass, Set<String>> findUnconsumedFoods(List<Entry> entries) {
        LocalDate today = LocalDate.now();
        LocalDate firstDayOfCurrentMonth = today.withDayOfMonth(1);
        LocalDate firstDayOfPreviousMonth = firstDayOfCurrentMonth.minusMonths(1);

        Map<Boolean, List<Entry>> partitionedEntries = entries.stream()
                .collect(Collectors.partitioningBy(e -> e.getDate().isBefore(firstDayOfCurrentMonth) && !e.getDate().isBefore(firstDayOfPreviousMonth)));

        List<Entry> previousMonthEntries = partitionedEntries.get(true);
        List<Entry> currentMonthEntries = partitionedEntries.get(false);

        Map<AnimalClass, List<FoodList>> previousMonthFoodLists = previousMonthEntries.stream()
                .collect(Collectors.groupingBy(Entry::getAnimalClass, Collectors.flatMapping(entry -> entry.getFoodList().stream(), Collectors.toList())));

        Map<AnimalClass, List<FoodList>> currentMonthFoodLists = currentMonthEntries.stream()
                .collect(Collectors.groupingBy(Entry::getAnimalClass, Collectors.flatMapping(entry -> entry.getFoodList().stream(), Collectors.toList())));

        Map<AnimalClass, Set<String>> unconsumedFoods = new HashMap<>();
        for (AnimalClass animalClass : previousMonthFoodLists.keySet()) {
            Set<String> previousMonthFoods = previousMonthFoodLists.get(animalClass).stream().map(FoodList::getFoodListName).collect(Collectors.toSet());
            Set<String> currentMonthFoods = currentMonthFoodLists.getOrDefault(animalClass, Collections.emptyList()).stream().map(FoodList::getFoodListName).collect(Collectors.toSet());

            previousMonthFoods.removeAll(currentMonthFoods);
            unconsumedFoods.put(animalClass, previousMonthFoods);
        }

        return unconsumedFoods;
    }
}
