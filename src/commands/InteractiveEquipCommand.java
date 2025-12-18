package commands;

import amunition.Amunition;
import arsenal.Arsenal;
import knight.Knight;

import java.util.Scanner;

public class InteractiveEquipCommand implements Command {
    private final Knight knight;
    private final Arsenal arsenal;
    private final Scanner scanner;

    public InteractiveEquipCommand(Knight knight, Arsenal arsenal, Scanner scanner) {
        this.knight = knight;
        this.arsenal = arsenal;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        if (arsenal.isEmpty()) {
            System.out.println("Arsenal is empty. Load ammunition first.");
            return;
        }

        arsenal.showAmunition();

        System.out.print("\nEnter item number to equip (0 to cancel): ");

        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());

            if (choice == 0) {
                System.out.println("Cancelled.");
                return;
            }

            Amunition item = arsenal.getAmunitionByIndex(choice);

            if (item == null) {
                System.out.println("Invalid item number.");
                return;
            }

            knight.equip(item);

        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }

    @Override
    public String getDescription() {
        return "Equip Item (Interactive)";
    }
}