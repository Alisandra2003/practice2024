package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URISyntaxException;

public class ZooAccountingLogMain {
    private static final Logger logger = LogManager.getLogger(ZooAccountingLogMain.class);
    private static final String fileName = "food_records.txt";
    public static void main(String[] args) throws URISyntaxException {
        logger.info("Начало работы программы.");
        PrintZooLog printZooLog = new PrintZooLog(fileName);
        printZooLog.printFindAnimalMostEatLastMonth();
        printZooLog.printMonthMostDiferenseEat();
        printZooLog.printFindUnconsumedFoods();
        logger.info("Конец работы программы.");
    }
}