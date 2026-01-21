package ru.yandex.practicum.sleeptracker.analysis.functions;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.analysis.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.session.SleepingSession;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.yandex.practicum.sleeptracker.Tests.create;

public class SleeplessNightsAnalyserTest {
    private static final SleeplessNightsAnalyser ANALYSER = new SleeplessNightsAnalyser();

    @Test
    public void singleNight_Normal() {
        List<SleepingSession> sessions = List.of(
                create(1, 23, 5)
        );
        SleepAnalysisResult result = ANALYSER.apply(sessions);
        assertEquals("0", result.Value);
    }

    @Test
    public void singleNight_Normal_LeftBorder() {
        List<SleepingSession> sessions = List.of(
                create(1, 20, 0)
        );
        SleepAnalysisResult result = ANALYSER.apply(sessions);
        assertEquals("0", result.Value);
    }

    @Test
    public void singleNight_Normal_RightBorder() {
        List<SleepingSession> sessions = List.of(
                create(1, 6, 13)
        );
        SleepAnalysisResult result = ANALYSER.apply(sessions);
        assertEquals("0", result.Value);
    }

    @Test
    public void singleNight_Normal_BothBorders() {
        List<SleepingSession> sessions = List.of(
                create(1, 0, 6)
        );
        SleepAnalysisResult result = ANALYSER.apply(sessions);
        assertEquals("0", result.Value);
    }

    @Test
    public void singleNight_Normal_NextDayAwake() {
        List<SleepingSession> sessions = List.of(
                create(1, 23, 7)
        );
        SleepAnalysisResult result = ANALYSER.apply(sessions);
        assertEquals("0", result.Value);
    }

    @Test
    public void singleNight_Normal_NextMonthAwake() {
        List<SleepingSession> sessions = List.of(
                create(LocalDate.of(2026, 1, 31), 23, 7)
        );
        SleepAnalysisResult result = ANALYSER.apply(sessions);
        assertEquals("0", result.Value);
    }

    @Test
    public void singleNight_Sleepless() {
        List<SleepingSession> sessions = List.of(
                create(1, 7, 20)
        );
        SleepAnalysisResult result = ANALYSER.apply(sessions);
        assertEquals("1", result.Value);
    }

    @Test
    public void allNormal() {
        List<SleepingSession> sessions = List.of(
                create(1, 21, 5),
                create(2, 0, 6),
                create(3, 1, 15)
        );
        SleepAnalysisResult result = ANALYSER.apply(sessions);
        assertEquals("0", result.Value);
    }

    @Test
    public void normalAfterSleepless() {
        List<SleepingSession> sessions = List.of(
                create(1, 15, 20),
                create(1, 21, 1)
        );
        SleepAnalysisResult result = ANALYSER.apply(sessions);
        assertEquals("0", result.Value);
    }

    @Test
    public void allSleepless_AfterNoon() {
        List<SleepingSession> sessions = List.of(
                create(1, 16, 23),
                create(2, 14, 22),
                create(3, 13, 19)
        );
        SleepAnalysisResult result = ANALYSER.apply(sessions);
        assertEquals("3", result.Value);
    }

    @Test
    public void allSleepless_BeforeNoon() {
        List<SleepingSession> sessions = List.of(
                create(1, 7, 11),
                create(2, 8, 10),
                create(3, 9, 11)
        );
        SleepAnalysisResult result = ANALYSER.apply(sessions);
        assertEquals("3", result.Value);
    }

    @Test
    public void allSleepless_TwoSessions_OneNight() {
        List<SleepingSession> sessions = List.of(
                create(1, 16, 20),
                create(2, 8, 10)
        );
        SleepAnalysisResult result = ANALYSER.apply(sessions);
        assertEquals("1", result.Value);
    }

    @Test
    public void allSleepless_ThreeSessions_TwoNights() {
        List<SleepingSession> sessions = List.of(
                create(1, 16, 20),
                create(2, 8, 10),
                create(3, 7, 11)
        );
        SleepAnalysisResult result = ANALYSER.apply(sessions);
        assertEquals("2", result.Value);
    }

    @Test
    public void allSleepless_MultipleSession_OneDayWithoutSessions() {
        List<SleepingSession> sessions = List.of(
                create(1, 16, 20),
                create(3, 16, 20)
        );
        SleepAnalysisResult result = ANALYSER.apply(sessions);
        assertEquals("3", result.Value);
    }

    @Test
    public void allSleepless_MultipleSession_MultipleDaysWithoutSessions() {
        List<SleepingSession> sessions = List.of(
                create(1, 16, 20),
                create(5, 16, 20)
        );
        SleepAnalysisResult result = ANALYSER.apply(sessions);
        assertEquals("5", result.Value);
    }
}
