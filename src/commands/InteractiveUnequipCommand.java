package commands;

import amunition.Amunition;
import amunition.EquipmentSlot;
import knight.Knight;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class InteractiveUnequipCommand implements Command {
    private final Knight knight;
    private final Scanner scanner;

    public InteractiveUnequipCommand(Knight knight, Scanner scanner) {
        this.knight = knight;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        if (!knight.isEquipped()) {
            System.out.println("Knight has no equipment to unequip.");
            return;
        }

        knight.showEquipped();

        System.out.println("\nOptions:");
        System.out.println("1. Unequip specific item");
        System.out.println("2. Unequip all items");
        System.out.print("Your choice: ");

        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());

            if (choice == 1) {
                unequipSpecificItem();
            } else if (choice == 2) {
                knight.unequipAll();
            } else {
                System.out.println("Invalid choice.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
        }
    }

    private void unequipSpecificItem() {
        Map<EquipmentSlot, Amunition> equipment = knight.getEquipment();
        List<Amunition> uniqueItems = new ArrayList<>(Set.copyOf(equipment.values()));

        System.out.println("\nSelect item to unequip:");
        for (int i = 0; i < uniqueItems.size(); i++) {
            System.out.println((i + 1) + ". " + uniqueItems.get(i).getName());
        }

        System.out.print("Enter number: ");

        try {
            int itemChoice = Integer.parseInt(scanner.nextLine().trim());

            if (itemChoice < 1 || itemChoice > uniqueItems.size()) {
                System.out.println("Invalid item number.");
                return;
            }

            Amunition itemToUnequip = uniqueItems.get(itemChoice - 1);
            knight.unequipItem(itemToUnequip);

        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
        }
    }

    @Override
    public String getDescription() {
        return "Unequip Item (Interactive)";
    }
}