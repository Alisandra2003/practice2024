package org.example;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Entry {
    // дата;класс корабля;подданство;сколько золота было получено;
    // сколько бочек рома было получено;был ли взят корабль на абордаж
    private final LocalDate date;
    private final AnimalClass animalClass;
    private final List<FoodList> foodList;
    private final double foodWeight;

    public Entry(LocalDate date, AnimalClass animalClass,
                 List<FoodList> foodList, double foodWeight) {
        this.date = date;
        this.animalClass = animalClass;
        this.foodList = foodList;
        this.foodWeight = foodWeight;
    }

    public LocalDate getDate() {
        return date;
    }
    public AnimalClass getAnimalClass() {
        return animalClass;
    }
    public List<FoodList> getFoodList() {
        return foodList;
    }
    public double getFoodWeight() {
        return foodWeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry entry = (Entry) o;
        return  Objects.equals(date, entry.date) && Objects.equals(animalClass, entry.animalClass)
                && Objects.equals(foodList, entry.foodList) && Objects.equals(foodWeight, entry.foodWeight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, animalClass, foodList, foodWeight);
    }

    @Override
    public String toString() {
        return "Entry{" +
                "date=" + date +
                ", animalClass=" + animalClass +
                ", foodList=" + foodList +
                ", foodWeight=" + foodWeight +
                '}';
    }
}
