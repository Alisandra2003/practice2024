package org.example;

public enum FoodList {
    CHEESE("сыр"),
    MEAT("мясо"),
    PASTA("паста"),
    CUCUMBER("огурец"),
    MILK("молоко");

    private final String foodListName;

    private FoodList(String foodListName) {
        this.foodListName = foodListName;
    }

    public static FoodList  fromString(String value) {
        if (value != null) {
            for (FoodList foodList : FoodList.values()) {
                if (value.equalsIgnoreCase(foodList.foodListName)) {
                    return foodList;
                }
            }
        }
        throw new IllegalArgumentException("No such value");
    }

    public String getFoodListName() {
        return foodListName;
    }
}
