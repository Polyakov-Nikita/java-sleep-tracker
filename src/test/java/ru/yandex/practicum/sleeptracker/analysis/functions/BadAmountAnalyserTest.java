package ru.yandex.practicum.sleeptracker.analysis.functions;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.analysis.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.session.SleepQuality;
import ru.yandex.practicum.sleeptracker.session.SleepingSession;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.yandex.practicum.sleeptracker.utils.UtilityTest.createWithQuality;

public class BadAmountAnalyserTest {
    private static final BadAmountAnalyser ANALYSER = new BadAmountAnalyser();

    @Test
    public void noBad() {
        List<SleepingSession> sessions = List.of(
                createWithQuality(SleepQuality.NORMAL),
                createWithQuality(SleepQuality.GOOD)
        );
        SleepAnalysisResult result = ANALYSER.apply(sessions);
        assertEquals("0", result.value);
    }

    @Test
    public void allBad() {
        List<SleepingSession> sessions = List.of(
                createWithQuality(SleepQuality.BAD),
                createWithQuality(SleepQuality.BAD),
                createWithQuality(SleepQuality.BAD),
                createWithQuality(SleepQuality.BAD)
        );
        SleepAnalysisResult result = ANALYSER.apply(sessions);
        assertEquals("4", result.value);
    }
}
