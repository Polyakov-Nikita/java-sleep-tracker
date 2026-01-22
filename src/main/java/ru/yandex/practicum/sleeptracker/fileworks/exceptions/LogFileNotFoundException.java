package ru.yandex.practicum.sleeptracker.fileworks.exceptions;

import java.nio.file.Path;

public class LogFileNotFoundException extends RuntimeException {
    public final Path logFile;

    public LogFileNotFoundException(Path logFile) {
        super("Файл лога не найден.");
        this.logFile = logFile;
    }
}
