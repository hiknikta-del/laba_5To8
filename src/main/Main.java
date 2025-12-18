package main;

import arsenal.Arsenal;
import commands.*;
import knight.Knight;
import utils.FileManager;

import java.util.Scanner;

/**
 * Головний клас програми для управління спорядженням лицаря.
 * Реалізує консольне меню з використанням паттерну Command.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Привітання
        printWelcomeBanner();

        // Створення основних об'єктів
        Knight knight = new Knight("Sir Lancelot");
        Arsenal arsenal = new Arsenal();

        // Пропонуємо завантажити дані при старті
        System.out.println("Would you like to load default ammunition data? (y/n)");
        String response = scanner.nextLine().trim().toLowerCase();

        if (response.equals("y") || response.equals("yes")) {
            String defaultFile = FileManager.getDefaultDataFile();
            FileManager.createDefaultDataFile(defaultFile);
            FileManager.loadAmunitionFromFile(arsenal, defaultFile);
        }

        // === Меню управління лицарем та арсеналом ===
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

        // === Головне меню ===
        Menu mainMenu = new Menu("MAIN MENU", scanner);

        mainMenu.addCommand(1, new OpenKnightManagerCommand(knightMenu));
        mainMenu.addCommand(2, new InfoReferenceCommand());
        mainMenu.addCommand(3, new ExitCommand());

        // Запуск програми
        mainMenu.run();
    }

    /**
     * Виводить банер привітання
     */
    private static void printWelcomeBanner() {
        System.out.println("╔════════════════════════════════════════════════╗");
        System.out.println("║    KNIGHT EQUIPMENT MANAGEMENT SYSTEM v2.0     ║");
        System.out.println("║           Equip your knight for battle         ║");
        System.out.println("╚════════════════════════════════════════════════╝");
        System.out.println();
    }
}