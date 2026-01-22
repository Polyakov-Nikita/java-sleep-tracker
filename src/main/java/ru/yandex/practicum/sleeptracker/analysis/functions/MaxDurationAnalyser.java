package ru.yandex.practicum.sleeptracker.analysis.functions;

import ru.yandex.practicum.sleeptracker.analysis.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.session.SleepingSession;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class MaxDurationAnalyser implements Function<List<SleepingSession>, SleepAnalysisResult> {
    private static final String DESCRIPTION = "максимальная продолжительность сессии";

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        long max = sessions.stream()
                .map(session -> Duration.between(session.start, session.end))
                .max(Duration::compareTo)
                .orElse(Duration.ofMinutes(0))
                .toMinutes();
        return new SleepAnalysisResult(DESCRIPTION, max + " мин.");
    }
}
