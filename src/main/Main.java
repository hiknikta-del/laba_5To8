package main;

import arsenal.Arsenal;
import commands.*;
import knight.Knight;
import utils.AppLogger;
import utils.FileManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // ЛОГЕР: Початок роботи
        AppLogger.logInfo("Application started. User: Mykola Shandro");

        Scanner scanner = new Scanner(System.in);
        printWelcomeBanner();

        // ================================================================
        // === ТЕСТ КРИТИЧНОЇ ПОМИЛКИ (ДЛЯ ДЕМОНСТРАЦІЇ ЛОГЕРА) ===
        // ================================================================
        System.out.println("Performing startup system checks...");
        try {
            // Навмисно створюємо критичну ситуацію
            throw new RuntimeException("Secure connection to database server failed (Simulated)");
        } catch (RuntimeException e) {
            // Цей метод запише в файл ТА викличе "надсилання пошти" в термінал
            AppLogger.logCritical("Startup Check Failed", e);
        }
        System.out.println("System recovered. Continuing in offline mode...\n");
        // ================================================================


        // Далі йде звичайний код програми...
        Knight knight = new Knight("Sir Lancelot");
        Arsenal arsenal = new Arsenal();

        System.out.println("Would you like to load default ammunition data? (y/n)");
        String response = scanner.nextLine().trim().toLowerCase();

        if (response.equals("y") || response.equals("yes")) {
            String defaultFile = FileManager.getDefaultDataFile();
            FileManager.createDefaultDataFile(defaultFile);
            FileManager.loadAmunitionFromFile(arsenal, defaultFile);
        }

        Menu knightMenu = new Menu("KNIGHT & ARSENAL MANAGEMENT", scanner);
        knightMenu.addCommand(1, new ShowAllAmunitionCommand(arsenal));
        knightMenu.addCommand(2, new SortByPriceCommand(arsenal));
        knightMenu.addCommand(3, new SortByWeightCommand(arsenal));
        knightMenu.addCommand(4, new SearchByPriceRangeCommand(arsenal, scanner));
        knightMenu.addCommand(5, new InteractiveEquipCommand(knight, arsenal, scanner));
        knightMenu.addCommand(6, new InteractiveUnequipCommand(knight, scanner));
        knightMenu.addCommand(7, new ShowEquippedCommand(knight));
        knightMenu.addCommand(8, new CalculateTotalCostCommand(knight));
        knightMenu.addCommand(9, new LoadFromFileCommand(arsenal, scanner));
        knightMenu.addCommand(10, new SaveToFileCommand(arsenal, scanner));
        knightMenu.addCommand(0, new BackToMainMenuCommand());

        Menu mainMenu = new Menu("MAIN MENU", scanner);
        mainMenu.addCommand(1, new OpenKnightManagerCommand(knightMenu));
        mainMenu.addCommand(2, new InfoReferenceCommand());
        mainMenu.addCommand(3, new ExitCommand());

        mainMenu.run();
    }

    private static void printWelcomeBanner() {
        System.out.println("╔════════════════════════════════════════════════╗");
        System.out.println("║    KNIGHT EQUIPMENT MANAGEMENT SYSTEM v2.0     ║");
        System.out.println("║            Equip your knight for battle        ║");
        System.out.println("╚════════════════════════════════════════════════╝");
        System.out.println();
    }
}