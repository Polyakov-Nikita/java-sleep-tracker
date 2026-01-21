package ru.yandex.practicum.sleeptracker.fileworks.exceptions;

import java.nio.file.Path;

public class LogFileNotFoundException extends RuntimeException {
    public final Path LogFile;

    public LogFileNotFoundException(Path logFile) {
        super("Файл лога не найден.");
        LogFile = logFile;
    }
}
