package ru.yandex.practicum.sleeptracker.analysis.functions;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.analysis.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.session.SleepingSession;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.yandex.practicum.sleeptracker.Tests.createWithDuration;

public class MinDurationAnalyserTest {
    private static final MinDurationAnalyser ANALYSER = new MinDurationAnalyser();

    @Test
    public void singleDuration() {
        List<SleepingSession> sessions = List.of(
                createWithDuration(90)
        );
        SleepAnalysisResult result = ANALYSER.apply(sessions);
        assertEquals("90 мин.", result.Value);
    }

    @Test
    public void equalDurations() {
        List<SleepingSession> sessions = List.of(
                createWithDuration(90),
                createWithDuration(90),
                createWithDuration(90)
        );
        SleepAnalysisResult result = ANALYSER.apply(sessions);
        assertEquals("90 мин.", result.Value);
    }
}
