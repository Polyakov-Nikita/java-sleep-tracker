package ru.yandex.practicum.sleeptracker.analysis.functions;

import ru.yandex.practicum.sleeptracker.analysis.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.session.SleepQuality;
import ru.yandex.practicum.sleeptracker.session.SleepingSession;

import java.util.List;
import java.util.function.Function;

public class BadAmountAnalyser implements Function<List<SleepingSession>, SleepAnalysisResult> {
    private static final String DESCRIPTION = "количество сессий с плохим качеством сна";

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        long amount = sessions.stream().filter(session -> session.quality == SleepQuality.BAD).count();
        return new SleepAnalysisResult(DESCRIPTION, Long.toString(amount));
    }
}
