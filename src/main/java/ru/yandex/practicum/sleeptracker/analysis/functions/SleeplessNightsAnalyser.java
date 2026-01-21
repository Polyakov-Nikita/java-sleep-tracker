package ru.yandex.practicum.sleeptracker.analysis.functions;

import ru.yandex.practicum.sleeptracker.analysis.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.session.SleepingSession;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

public class SleeplessNightsAnalyser implements Function<List<SleepingSession>, SleepAnalysisResult> {
    private static final LocalTime DAYS_BORDER = LocalTime.of(12, 0);
    private static final LocalTime SLEEP_TIME_RIGHT = LocalTime.of(6, 0);

    private LocalDate firstDay;
    private boolean[] daysInfo;

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        createDaysInfo(sessions);
        sessions.forEach(this::fillInfo);
        int amount = analyseDaysInfo();
        return new SleepAnalysisResult("количество бессонных ночей", Integer.toString(amount));
    }

    private void createDaysInfo(List<SleepingSession> sessions) {
        firstDay = getDay(sessions.getFirst());
        LocalDate lastDay = getDay(sessions.getLast());
        int daysCount = Period.between(firstDay, lastDay.plusDays(1)).getDays();
        daysInfo = new boolean[daysCount];
    }

    private LocalDate getDay(SleepingSession session) {
        if (session.start.toLocalTime().isBefore(DAYS_BORDER)) {
            return session.start.toLocalDate().minusDays(1);
        }
        return session.start.toLocalDate();
    }

    private void fillInfo(SleepingSession session) {
        int dayIndex = Period.between(firstDay, getDay(session)).getDays();
        daysInfo[dayIndex] |= inSleepTimeBounds(session);
    }

    private boolean inSleepTimeBounds(SleepingSession session) {
        if (session.start.toLocalDate().isBefore(session.end.toLocalDate())) {
            return true;
        }
        LocalTime start = session.start.toLocalTime();
        return start.isBefore(SLEEP_TIME_RIGHT) || start.equals(SLEEP_TIME_RIGHT);
    }

    private int analyseDaysInfo() {
        return (int) IntStream.range(0, daysInfo.length)
                .filter(index -> !daysInfo[index])
                .count();
    }
}
