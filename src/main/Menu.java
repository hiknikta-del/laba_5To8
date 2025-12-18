package main;

import commands.Command;
import commands.BackToMainMenuCommand;
import commands.ExitCommand;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    private final Map<Integer, Command> commands = new LinkedHashMap<>();
    private final Scanner scanner;
    private final String menuName;

    public Menu(String menuName, Scanner scanner) {
        this.menuName = menuName;
        this.scanner = scanner;
    }

    public void addCommand(int key, Command command) {
        commands.put(key, command);
    }

    public void run() {
        while (true) {
            System.out.println("\n=== " + menuName + " ===");

            for (Map.Entry<Integer, Command> entry : commands.entrySet()) {
                System.out.println("Press " + entry.getKey() + " - " + entry.getValue().getDescription());
            }
            System.out.print("Your choice: ");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Поглинаємо ентер

                if (commands.containsKey(choice)) {
                    Command command = commands.get(choice);
                    command.execute();

                    // Перевірка на вихід з меню
                    if (command instanceof ExitCommand || command instanceof BackToMainMenuCommand) {
                        return;
                    }
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("Please enter a number.");
                scanner.next();
            }
        }
    }
}