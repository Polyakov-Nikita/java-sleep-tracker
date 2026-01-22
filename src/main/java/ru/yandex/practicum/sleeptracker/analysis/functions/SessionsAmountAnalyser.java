package ru.yandex.practicum.sleeptracker.analysis.functions;

import ru.yandex.practicum.sleeptracker.analysis.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.session.SleepingSession;

import java.util.List;
import java.util.function.Function;

public class SessionsAmountAnalyser implements Function<List<SleepingSession>, SleepAnalysisResult> {
    private static final String DESCRIPTION = "количество сессий сна";

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        int amount = sessions.size();
        return new SleepAnalysisResult(DESCRIPTION, Integer.toString(amount));
    }
}