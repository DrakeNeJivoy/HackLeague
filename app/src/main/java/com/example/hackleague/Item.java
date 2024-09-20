package com.example.hackleague;

public class Item {
    private String name;

    // Конструктор
    public Item(String name) {
        this.name = name;
    }

    // Геттер для имени
    public String getName() {
        return name;
    }

    // Сеттер для имени (если нужно изменять имя)
    public void setName(String name) {
        this.name = name;
    }
}
