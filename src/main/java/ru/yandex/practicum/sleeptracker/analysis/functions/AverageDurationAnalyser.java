package ru.yandex.practicum.sleeptracker.analysis.functions;

import ru.yandex.practicum.sleeptracker.analysis.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.session.SleepingSession;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class AverageDurationAnalyser implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        long average = (long) sessions.stream()
                .map(session -> Duration.between(session.Start, session.End))
                .mapToInt(duration -> (int) duration.toMinutes())
                .average()
                .orElse(0.0);
        return new SleepAnalysisResult("средняя продолжительность сессии", average + " мин.");
    }
}
