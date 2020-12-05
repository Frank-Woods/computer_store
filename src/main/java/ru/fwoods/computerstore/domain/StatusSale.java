package ru.fwoods.computerstore.domain;

public enum StatusSale {
    PROCESSING("Обрабатывается"), SENT("Отправлено"), DELIVERED("Доставлено");

    private String text;

    StatusSale(String text) { this.text = text; }

    public String getText() {
        return text;
    }
}
