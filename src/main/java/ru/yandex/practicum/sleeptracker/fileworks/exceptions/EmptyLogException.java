package ru.yandex.practicum.sleeptracker.fileworks.exceptions;

public class EmptyLogException extends RuntimeException {
    public EmptyLogException() {
        super("Файл лога пуст.");
    }
}
