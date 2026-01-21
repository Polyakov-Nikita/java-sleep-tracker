package ru.yandex.practicum.sleeptracker.analysis.functions;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.analysis.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.session.SleepingSession;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.yandex.practicum.sleeptracker.Tests.create;

public class ChronotypeAnalyserTest {
    private static final ChronotypeAnalyser ANALYSER = new ChronotypeAnalyser();
    
    @Test
    public void Owl_SingleSession() {
        List<SleepingSession> sessions = List.of(
                create(1, 0, 10)
        );
        SleepAnalysisResult result = ANALYSER.apply(sessions);
        assertEquals("сова", result.Value);
    }

    @Test
    public void Owl_SingleSession_LeftBorder() {
        List<SleepingSession> sessions = List.of(
                create(1, 23, 10)
        );
        SleepAnalysisResult result = ANALYSER.apply(sessions);
        assertEquals("сова", result.Value);
    }

    @Test
    public void Owl_SingleSession_RightBorder() {
        List<SleepingSession> sessions = List.of(
                create(1, 0, 9)
        );
        SleepAnalysisResult result = ANALYSER.apply(sessions);
        assertEquals("сова", result.Value);
    }

    @Test
    public void Owl() {
        List<SleepingSession> sessions = List.of(
                create(1, 0, 11),     // OWL
                create(2, 23, 10),    // OWL
                create(3, 21, 10),    // HUMMINGBIRD
                create(4, 21, 5)      // LARK
        );
        SleepAnalysisResult result = ANALYSER.apply(sessions);
        assertEquals("сова", result.Value);
    }

    @Test
    public void Lark_SingleSession() {
        List<SleepingSession> sessions = List.of(
                create(1, 21, 6)
        );
        SleepAnalysisResult result = ANALYSER.apply(sessions);
        assertEquals("жаворонок", result.Value);
    }

    @Test
    public void Lark_SingleSession_LeftBorder() {
        List<SleepingSession> sessions = List.of(
                create(1, 22, 6)
        );
        SleepAnalysisResult result = ANALYSER.apply(sessions);
        assertEquals("жаворонок", result.Value);
    }

    @Test
    public void Lark_SingleSession_RightBorder() {
        List<SleepingSession> sessions = List.of(
                create(1, 21, 7)
        );
        SleepAnalysisResult result = ANALYSER.apply(sessions);
        assertEquals("жаворонок", result.Value);
    }

    @Test
    public void Lark() {
        List<SleepingSession> sessions = List.of(
                create(1, 21, 6),     // LARK
                create(2, 0, 9),      // OWL
                create(3, 20, 5),     // LARK
                create(3, 20, 8)      // HUMMINGBIRD
        );
        SleepAnalysisResult result = ANALYSER.apply(sessions);
        assertEquals("жаворонок", result.Value);
    }

    @Test
    public void Hummingbird_SingleSession() {
        List<SleepingSession> sessions = List.of(
                create(1, 21, 8)
        );
        SleepAnalysisResult result = ANALYSER.apply(sessions);
        assertEquals("голубь", result.Value);
    }

    @Test
    public void Hummingbird() {
        List<SleepingSession> sessions = List.of(
                create(1, 21, 8),     // HUMMINGBIRD
                create(2, 20, 6),     // LARK
                create(3, 23, 9),     // OWL
                create(3, 22, 12)     // HUMMINGBIRD
        );
        SleepAnalysisResult result = ANALYSER.apply(sessions);
        assertEquals("голубь", result.Value);
    }

    @Test
    public void Hummingbird_OwlAndLarkAreEqual() {
        List<SleepingSession> sessions = List.of(
                create(1, 21, 5),     // LARK
                create(2, 20, 6),     // LARK
                create(3, 23, 9),     // OWL
                create(3, 1, 12)      // OWL
        );
        SleepAnalysisResult result = ANALYSER.apply(sessions);
        assertEquals("голубь", result.Value);
    }

    @Test
    public void Hummingbird_AllAreEqual() {
        List<SleepingSession> sessions = List.of(
                create(1, 21, 5),     // LARK
                create(2, 20, 8),     // HUMMINGBIRD
                create(3, 1, 12)      // OWL
        );
        SleepAnalysisResult result = ANALYSER.apply(sessions);
        assertEquals("голубь", result.Value);
    }
}
