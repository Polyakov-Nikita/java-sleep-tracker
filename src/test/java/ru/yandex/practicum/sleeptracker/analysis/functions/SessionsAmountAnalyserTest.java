package ru.yandex.practicum.sleeptracker.analysis.functions;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.analysis.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.session.SleepingSession;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.yandex.practicum.sleeptracker.Tests.create;

public class SessionsAmountAnalyserTest {
    private static final SessionsAmountAnalyser ANALYSER = new SessionsAmountAnalyser();

    @Test
    public void singleSession() {
        List<SleepingSession> sessions = List.of(
                create()
        );
        SleepAnalysisResult result = ANALYSER.apply(sessions);
        assertEquals("1", result.Value);
    }

    @Test
    public void multipleSessions() {
        List<SleepingSession> sessions = List.of(
                create(),
                create(),
                create()
        );
        SleepAnalysisResult result = ANALYSER.apply(sessions);
        assertEquals("3", result.Value);
    }
}
