package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.session.SleepQuality;
import ru.yandex.practicum.sleeptracker.session.SleepingSession;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Tests {
    private static final LocalDate FIRST_DAY = LocalDate.of(2026, 1, 21);

    public static SleepingSession create() {
        return new SleepingSession(LocalDateTime.MIN, LocalDateTime.MIN, SleepQuality.NORMAL);
    }

    public static SleepingSession createWithDuration(int minutes) {
        return new SleepingSession(LocalDateTime.MIN, LocalDateTime.MIN.plusMinutes(minutes), SleepQuality.NORMAL);
    }

    public static SleepingSession createWithQuality(SleepQuality quality) {
        return new SleepingSession(LocalDateTime.MIN, LocalDateTime.MIN, quality);
    }

    public static SleepingSession create(int dayNumber, int startHour, int endHour) {
        LocalDate date = FIRST_DAY.plusDays(dayNumber - 1);
        LocalTime startTime = LocalTime.of(startHour, 0);
        LocalTime endTime = LocalTime.of(endHour, 0);
        LocalDateTime start = LocalDateTime.of(date, startTime);
        if (endHour < startHour) {
            date = date.plusDays(1);
        }
        LocalDateTime end = LocalDateTime.of(date, endTime);
        return new SleepingSession(start, end, SleepQuality.NORMAL);
    }

    public static SleepingSession create(LocalDate date, int startHour, int endHour) {
        LocalTime startTime = LocalTime.of(startHour, 0);
        LocalTime endTime = LocalTime.of(endHour, 0);
        LocalDateTime start = LocalDateTime.of(date, startTime);
        if (endHour < startHour) {
            date = date.plusDays(1);
        }
        LocalDateTime end = LocalDateTime.of(date, endTime);
        return new SleepingSession(start, end, SleepQuality.NORMAL);
    }
}
