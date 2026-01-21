package ru.yandex.practicum.sleeptracker.fileworks;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.fileworks.exceptions.EmptyLogException;
import ru.yandex.practicum.sleeptracker.fileworks.exceptions.LogFileNotFoundException;
import ru.yandex.practicum.sleeptracker.session.SleepQuality;
import ru.yandex.practicum.sleeptracker.session.SleepingSession;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LogLoaderTest {
    private static final Path LOG_PATH = Paths.get("src\\test\\resources\\sleep_log_test.txt");
    private static final Path LOG_PATH_SINGLE_STRING = Paths.get("src\\test\\resources\\sleep_log_test_SingleString.txt");
    private static final Path LOG_PATH_NO_STRINGS = Paths.get("src\\test\\resources\\sleep_log_test_NoStrings.txt");
    private static final Path LOG_PATH_ABSENT_FILE = Paths.get("src\\test\\resources\\sleep_log_test_AbsentFile.txt");

    private final LogLoader loader = new LogLoader();

    @Test
    public void load() {
        List<SleepingSession> sessions = loader.load(LOG_PATH);
        SleepingSession session1 = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 23, 15),
                LocalDateTime.of(2025, 10, 2, 7, 30),
                SleepQuality.GOOD
        );
        SleepingSession session2 = new SleepingSession(
                LocalDateTime.of(2025, 10, 2, 23, 50),
                LocalDateTime.of(2025, 10, 3, 6, 40),
                SleepQuality.NORMAL
        );
        SleepingSession session3 = new SleepingSession(
                LocalDateTime.of(2025, 10, 3, 14, 10),
                LocalDateTime.of(2025, 10, 3, 15, 0),
                SleepQuality.NORMAL
        );
        assertEquals(3, sessions.size());
        assertEquals(session1, sessions.get(0));
        assertEquals(session2, sessions.get(1));
        assertEquals(session3, sessions.get(2));
    }

    @Test
    public void load_SingleString() {
        List<SleepingSession> sessions = loader.load(LOG_PATH_SINGLE_STRING);
        SleepingSession session = new SleepingSession(
                LocalDateTime.of(2025, 10, 3, 23, 40),
                LocalDateTime.of(2025, 10, 4, 8, 0),
                SleepQuality.BAD
        );
        assertEquals(1, sessions.size());
        assertEquals(session, sessions.getFirst());
    }

    @Test
    public void load_NoStrings() {
        assertThrows(EmptyLogException.class, () -> loader.load(LOG_PATH_NO_STRINGS));
    }

    @Test
    public void load_NoFile() {
        assertThrows(LogFileNotFoundException.class, () -> loader.load(LOG_PATH_ABSENT_FILE));
    }
}
