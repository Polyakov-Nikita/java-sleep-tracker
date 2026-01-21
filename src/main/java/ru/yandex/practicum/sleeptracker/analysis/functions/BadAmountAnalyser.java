package ru.yandex.practicum.sleeptracker.analysis.functions;

import ru.yandex.practicum.sleeptracker.analysis.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.session.SleepQuality;
import ru.yandex.practicum.sleeptracker.session.SleepingSession;

import java.util.List;
import java.util.function.Function;

public class BadAmountAnalyser implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        int amount = sessions.stream()
                .filter(session -> session.Quality == SleepQuality.BAD)
                .toList().size();
        return new SleepAnalysisResult("количество сессий с плохим качеством сна", Integer.toString(amount));
    }
}
