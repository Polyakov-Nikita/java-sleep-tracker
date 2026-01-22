package ru.yandex.practicum.sleeptracker.fileworks;

import ru.yandex.practicum.sleeptracker.fileworks.exceptions.EmptyLogException;
import ru.yandex.practicum.sleeptracker.fileworks.exceptions.LogFileNotFoundException;
import ru.yandex.practicum.sleeptracker.session.SleepQuality;
import ru.yandex.practicum.sleeptracker.session.SleepingSession;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

public class LogLoader {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

    public List<SleepingSession> load(Path logPath) {
        return loadStrings(logPath).stream()
                .map(this::stringSessionMapper)
                .toList();
    }

    private List<String> loadStrings(Path logFile) {
        checkFileExistence(logFile);
        try (Stream<String> lines = Files.lines(logFile)) {
            List<String> strings = lines.toList();
            checkEmptiness(strings);
            return strings;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void checkFileExistence(Path file) {
        if (!Files.exists(file)) {
            throw new LogFileNotFoundException(file);
        }
    }

    private void checkEmptiness(List<String> strings) {
        if (strings.isEmpty()) {
            throw new EmptyLogException();
        }
    }

    private SleepingSession stringSessionMapper(String string) {
        String[] substrings = string.split(";");
        LocalDateTime start = LocalDateTime.parse(substrings[0], FORMATTER);
        LocalDateTime end = LocalDateTime.parse(substrings[1], FORMATTER);
        SleepQuality quality = SleepQuality.valueOf(substrings[2]);
        return new SleepingSession(start, end, quality);
    }
}
