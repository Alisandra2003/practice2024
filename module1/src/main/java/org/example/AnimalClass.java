package org.example;

public enum AnimalClass {
    COW("Корова"),
    BEAR("Медведь"),
    SNAKE("Змея"),
    GORRILA("Горилла");

    private final String className;

    private AnimalClass(String className) {
        this.className = className;
    }

    public static AnimalClass  fromString(String value) {
        if (value != null) {
            for (AnimalClass animalClass : AnimalClass.values()) {
                if (value.equalsIgnoreCase(animalClass.className)) {
                    return animalClass;
                }
            }
        }
        throw new IllegalArgumentException("No such value");
    }

    public String getClassName() {
        return className;
    }
}
