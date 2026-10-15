package com.botmod;

import java.util.ArrayList;
import java.util.List;

public class BotManager {
    private static String botName = "BotDefault";
    private static boolean isRunning = false;
    private static final List<String> consoleLogs = new ArrayList<>();

    public static void log(String message) {
        String entry = "[" + java.time.LocalTime.now().withNano(0) + "] " + message;
        consoleLogs.add(entry);
        if (consoleLogs.size() > 100) { consoleLogs.remove(0); }
    }

    public static List<String> getLogs() { return consoleLogs; }
    public static String getBotName() { return botName; }
    public static void setBotName(String name) { botName = name; }
    public static boolean isRunning() { return isRunning; }

    public static void startBot() {
        if (!isRunning) {
            isRunning = true;
            log("Uruchamianie bota o nicku: " + botName + "...");
            log("Bot podłączył się do sesji/serwera.");
        } else {
            log("Bot jest już uruchomiony!");
        }
    }

    public static void stopBot() {
        if (isRunning) {
            isRunning = false;
            log("Zatrzymano bota.");
        } else {
            log("Bot nie jest uruchomiony.");
        }
    }
}
