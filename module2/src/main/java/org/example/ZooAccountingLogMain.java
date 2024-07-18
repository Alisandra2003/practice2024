package org.example;


import java.net.URISyntaxException;

public class ZooAccountingLogMain {
    private static final String fileName = "food_records.txt";
    public static void main(String[] args) throws URISyntaxException {
        PrintZooLog printZooLog = new PrintZooLog(fileName);
        printZooLog.printFindAnimalMostEatLastMonth();
        printZooLog.printMonthMostDiferenseEat();
        printZooLog.printFindUnconsumedFoods();
    }
}