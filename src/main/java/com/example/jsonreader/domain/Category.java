package com.example.jsonreader.domain;

public enum Category {
    WIRED(1, "wired-headphones"), WIRELESS(2, "wireless-headphones"), WATCHES(3,"smart-watches");

    private int value;

    private String name;


    Category(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static Category ofValue(final int value) {
        for (Category category : Category.values()) {
            if (category.getValue() == value) {
                return category;
            }
        }

        return null;
    }

    public static Category ofName(final String name) {
        for (Category category : Category.values()) {
            if (category.getName().equalsIgnoreCase(name)) {
                return category;
            }
        }

        return null;
    }


    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}