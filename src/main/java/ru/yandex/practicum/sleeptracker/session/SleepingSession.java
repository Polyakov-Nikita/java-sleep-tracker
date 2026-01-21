package ru.yandex.practicum.sleeptracker.session;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class SleepingSession {
    public final LocalDateTime Start;
    public final LocalDateTime End;
    public final SleepQuality Quality;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    public SleepingSession(LocalDateTime start, LocalDateTime end, SleepQuality quality) {
        Start = start;
        End = end;
        Quality = quality;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SleepingSession that = (SleepingSession) o;
        return Objects.equals(Start, that.Start) && Objects.equals(End, that.End) && Quality == that.Quality;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Start, End, Quality);
    }

    @Override
    public String toString() {
        return Start.format(formatter) +
                " - " + End.format(formatter) +
                ", " + Quality;
    }
}
