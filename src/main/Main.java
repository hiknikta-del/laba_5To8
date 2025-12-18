package main;

import arsenal.Arsenal;
import commands.*;
import knight.Knight;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Створюємо "справжні" об'єкти (поки що вони пусті всередині, це ОК для Лаб 2)
        Knight knight = new Knight();
        Arsenal arsenal = new Arsenal();

        // --- Меню управління лицарем ---
        Menu knightMenu = new Menu("KNIGHT MANAGEMENT", scanner);

        // 2. Передаємо arsenal та knight у конструктори команд
        knightMenu.addCommand(1, new ShowAllAmunitionCommand(arsenal));
        knightMenu.addCommand(2, new SortByPriceCommand(arsenal));
        knightMenu.addCommand(3, new SortByWeightCommand(arsenal));
        knightMenu.addCommand(4, new EquipKnightCommand(knight, arsenal)); // Можливо тут потрібні обидва?
        knightMenu.addCommand(5, new UnequipKnightCommand(knight));
        knightMenu.addCommand(6, new ShowEquippedCommand(knight));
        knightMenu.addCommand(7, new CalculateTotalCostCommand(knight));
        knightMenu.addCommand(8, new BackToMainMenuCommand());

        // --- Головне меню ---
        Menu mainMenu = new Menu("MAIN MENU", scanner);

        mainMenu.addCommand(1, new OpenKnightManagerCommand(knightMenu));
        mainMenu.addCommand(2, new InfoReferenceCommand());
        mainMenu.addCommand(3, new ExitCommand());

        mainMenu.run();
    }
}