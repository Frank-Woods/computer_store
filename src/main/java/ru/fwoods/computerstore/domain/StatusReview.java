package ru.fwoods.computerstore.domain;

public enum StatusReview {
    PROCESSING("Обрабатывается"), CONFIRMED("Утверждён"), UNCONFIRMED("Отклонён");

    private String text;

    StatusReview(String text) { this.text = text; }

    public String getText() {
        return text;
    }
}
