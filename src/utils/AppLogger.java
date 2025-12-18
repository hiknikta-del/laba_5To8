package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AppLogger {
    private static final String LOG_FILE = "application_log.txt";
    private static final String ADMIN_EMAIL = "mykola.shandro.oi.2024@lpnu.ua";

    // Формат дати для логів
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Логування звичайної дії (INFO)
     */
    public static void logInfo(String message) {
        writeLog("INFO", message);
    }

    /**
     * Логування помилки (ERROR) - це записується у файл
     */
    public static void logError(String message, Exception e) {
        String errorDetails = message + (e != null ? " | Exception: " + e.getMessage() : "");
        writeLog("ERROR", errorDetails);
    }

    /**
     * Логування критичної помилки (CRITICAL) - файл + email
     */
    public static void logCritical(String message, Exception e) {
        String errorDetails = message + (e != null ? " | Trace: " + e.toString() : "");
        writeLog("CRITICAL", errorDetails);
        sendEmail(errorDetails);
    }

    // Приватний метод для запису у файл
    private static void writeLog(String level, String message) {
        String timestamp = dtf.format(LocalDateTime.now());
        String logEntry = String.format("[%s] [%s] %s", timestamp, level, message);

        // Вивід у консоль (опціонально, щоб бачити відразу)
        // System.out.println(logEntry);

        // Запис у файл (append = true, щоб додавати, а не перезаписувати)
        try (FileWriter fw = new FileWriter(LOG_FILE, true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(logEntry);
        } catch (IOException ex) {
            System.err.println("Logger Failed: " + ex.getMessage());
        }
    }

    // Метод імітації відправки пошти (спрощено для студентського проекту)
    private static void sendEmail(String errorBody) {
        System.out.println("\n==================================================");
        System.out.println("!!! SENDING EMAIL ALERT !!!");
        System.out.println("TO: " + ADMIN_EMAIL);
        System.out.println("SUBJECT: CRITICAL ERROR IN KNIGHT SYSTEM");
        System.out.println("BODY: " + errorBody);
        System.out.println("STATUS: Sent successfully (Simulated)");
        System.out.println("==================================================\n");
    }
}
