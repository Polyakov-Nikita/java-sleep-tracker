package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.analysis.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.analysis.functions.*;
import ru.yandex.practicum.sleeptracker.fileworks.LogLoader;
import ru.yandex.practicum.sleeptracker.session.SleepingSession;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SleepTrackerApp {
    private static final List<Function<List<SleepingSession>, SleepAnalysisResult>> FUNCTIONS = new ArrayList<>(List.of(
            new SessionsAmountAnalyser(),
            new MinDurationAnalyser(),
            new MaxDurationAnalyser(),
            new AverageDurationAnalyser(),
            new BadAmountAnalyser(),
            new SleeplessNightsAnalyser(),
            new ChronotypeAnalyser()
    ));

    private static List<SleepingSession> sessions;

    public static void main(String[] args) {
        printMessage("начало работы.");
        if (tryLoadLog(args[0])) {
            printResults(analyse());
        }
        printMessage("завершение работы.");
    }

    private static void printMessage(String message) {
        System.out.printf("\u001B[32mSleepTrackerApp\u001B[0m: %s%n", message);
    }

    private static boolean tryLoadLog(String logPath) {
        try {
            printMessage("загрузка лога сна...");
            sessions = new LogLoader().load(Paths.get(logPath));
            printMessage("лог файл загружен.");
            return true;
        } catch (RuntimeException e) {
            printError(e);
            printMessage("лог файл не загружен.");
            return false;
        }
    }

    private static void printError(RuntimeException exception) {
        printMessage(String.format("\u001B[31mОШИБКА\u001B[0m: %s", exception.getMessage()));
    }

    private static List<SleepAnalysisResult> analyse() {
        printMessage("запуск анализирующих функций...");
        return FUNCTIONS.stream()
                .map(function -> function.apply(sessions))
                .toList();
    }

    private static void printResults(List<SleepAnalysisResult> results) {
        printMessage("вывод:");
        int resultsCount = results.size();
        for (int i = 0; i < resultsCount; i++) {
            printResult(results.get(i), i + 1);
        }
    }

    private static void printResult(SleepAnalysisResult result, int order) {
        System.out.printf("%3d. | %-50s | %10s%n", order, result.description, result.value);
    }
}