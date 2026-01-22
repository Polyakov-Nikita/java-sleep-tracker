package ru.yandex.practicum.sleeptracker.analysis.functions;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.analysis.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.session.SleepingSession;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.yandex.practicum.sleeptracker.utils.UtilityTest.createWithDuration;

public class AverageDurationAnalyserTest {
    private static final AverageDurationAnalyser ANALYSER = new AverageDurationAnalyser();

    @Test
    public void equalDurations() {
        List<SleepingSession> sessions = List.of(
                createWithDuration(90),
                createWithDuration(90),
                createWithDuration(90)
        );
        SleepAnalysisResult result = new AverageDurationAnalyser().apply(sessions);
        assertEquals("90 мин.", result.value);
    }

    @Test
    public void roundDown() {
        List<SleepingSession> sessions = List.of(
                createWithDuration(90),
                createWithDuration(45)
        );
        SleepAnalysisResult result = ANALYSER.apply(sessions);
        assertEquals("67 мин.", result.value);
    }
}

