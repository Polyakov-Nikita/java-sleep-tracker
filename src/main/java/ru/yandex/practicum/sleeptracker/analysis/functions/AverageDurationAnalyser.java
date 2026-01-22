package ru.yandex.practicum.sleeptracker.analysis.functions;

import ru.yandex.practicum.sleeptracker.analysis.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.session.SleepingSession;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class AverageDurationAnalyser implements Function<List<SleepingSession>, SleepAnalysisResult> {
    private static final String DESCRIPTION = "средняя продолжительность сессии";

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        long average = (long) sessions.stream()
                .map(session -> Duration.between(session.start, session.end))
                .mapToInt(duration -> (int) duration.toMinutes())
                .average()
                .orElse(0.0);
        return new SleepAnalysisResult(DESCRIPTION, average + " мин.");
    }
}
