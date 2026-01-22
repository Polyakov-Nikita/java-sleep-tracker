package ru.yandex.practicum.sleeptracker.analysis.functions;

import ru.yandex.practicum.sleeptracker.analysis.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.session.SleepingSession;

import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;

public class ChronotypeAnalyser implements Function<List<SleepingSession>, SleepAnalysisResult> {
    private static final String DESCRIPTION = "хронотип";
    private static final LocalTime OWL_LEFT = LocalTime.of(23, 0);
    private static final LocalTime OWL_RIGHT = LocalTime.of(9, 0);
    private static final LocalTime LARK_LEFT = LocalTime.of(22, 0);
    private static final LocalTime LARK_RIGHT = LocalTime.of(7, 0);

    private int owlCount;
    private int lark;
    private int hummingbirdCount;

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        initialize();
        sessions.forEach(this::analyseSession);
        String chronotype = analyseCounters();
        return new SleepAnalysisResult(DESCRIPTION, chronotype);
    }

    private void initialize() {
        owlCount = 0;
        lark = 0;
        hummingbirdCount = 0;
    }

    private void analyseSession(SleepingSession session) {
        if (isOwlSession(session)) {
            owlCount++;
        } else if (isLarkSession(session)) {
            lark++;
        } else {
            hummingbirdCount++;
        }
    }

    private boolean isOwlSession(SleepingSession session) {
        LocalTime start = session.start.toLocalTime();
        LocalTime end = session.end.toLocalTime();
        return !(start.isAfter(end) && start.isBefore(OWL_LEFT))
                && (end.isAfter(OWL_RIGHT) || end.equals(OWL_RIGHT));
    }

    private boolean isLarkSession(SleepingSession session) {
        LocalTime start = session.start.toLocalTime();
        LocalTime end = session.end.toLocalTime();
        return (start.isBefore(LARK_LEFT) || start.equals(LARK_LEFT))
                && (end.isBefore(LARK_RIGHT) || end.equals(LARK_RIGHT));
    }

    private String analyseCounters() {
        if (owlCount > lark && owlCount > hummingbirdCount) {
            return "сова";
        } else if (lark > owlCount && lark > hummingbirdCount) {
            return "жаворонок";
        } else {
            return "голубь";
        }
    }
}
