package ru.yandex.practicum.sleeptracker.session;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class SleepingSession {
    public final LocalDateTime start;
    public final LocalDateTime end;
    public final SleepQuality quality;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    public SleepingSession(LocalDateTime start, LocalDateTime end, SleepQuality quality) {
        this.start = start;
        this.end = end;
        this.quality = quality;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SleepingSession that = (SleepingSession) o;
        return Objects.equals(start, that.start) && Objects.equals(end, that.end) && quality == that.quality;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end, quality);
    }

    @Override
    public String toString() {
        return start.format(formatter) +
                " - " + end.format(formatter) +
                ", " + quality;
    }
}
