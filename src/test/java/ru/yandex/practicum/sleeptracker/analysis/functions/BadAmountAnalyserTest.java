package ru.yandex.practicum.sleeptracker.analysis.functions;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.Tests;
import ru.yandex.practicum.sleeptracker.analysis.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.session.SleepQuality;
import ru.yandex.practicum.sleeptracker.session.SleepingSession;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BadAmountAnalyserTest {
    private static final BadAmountAnalyser ANALYSER = new BadAmountAnalyser();

    @Test
    public void NoBad() {
        List<SleepingSession> sessions = List.of(
                Tests.createWithQuality(SleepQuality.NORMAL),
                Tests.createWithQuality(SleepQuality.GOOD)
        );
        SleepAnalysisResult result = ANALYSER.apply(sessions);
        assertEquals("0", result.Value);
    }

    @Test
    public void AllBad() {
        List<SleepingSession> sessions = List.of(
                Tests.createWithQuality(SleepQuality.BAD),
                Tests.createWithQuality(SleepQuality.BAD),
                Tests.createWithQuality(SleepQuality.BAD),
                Tests.createWithQuality(SleepQuality.BAD)
        );
        SleepAnalysisResult result = ANALYSER.apply(sessions);
        assertEquals("4", result.Value);
    }
}
