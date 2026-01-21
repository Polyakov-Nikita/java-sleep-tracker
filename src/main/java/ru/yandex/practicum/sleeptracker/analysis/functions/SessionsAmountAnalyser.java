package ru.yandex.practicum.sleeptracker.analysis.functions;

import ru.yandex.practicum.sleeptracker.analysis.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.session.SleepingSession;

import java.util.List;
import java.util.function.Function;

public class SessionsAmountAnalyser implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        int amount = sessions.size();
        return new SleepAnalysisResult("количество сессий сна", Integer.toString(amount));
    }
}