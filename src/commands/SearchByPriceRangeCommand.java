package commands;

import arsenal.Arsenal;

import java.util.Scanner;

public class SearchByPriceRangeCommand implements Command {
    private final Arsenal arsenal;
    private final Scanner scanner;

    public SearchByPriceRangeCommand(Arsenal arsenal, Scanner scanner) {
        this.arsenal = arsenal;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        if (arsenal.isEmpty()) {
            System.out.println("Arsenal is empty.");
            return;
        }

        try {
            System.out.print("Enter minimum price: ");
            double minPrice = Double.parseDouble(scanner.nextLine().trim());

            System.out.print("Enter maximum price: ");
            double maxPrice = Double.parseDouble(scanner.nextLine().trim());

            if (minPrice < 0 || maxPrice < 0 || minPrice > maxPrice) {
                System.out.println("Invalid price range.");
                return;
            }

            arsenal.showAmunitionInPriceRange(minPrice, maxPrice);

        } catch (NumberFormatException e) {
            System.out.println("Invalid number format.");
        }
    }

    @Override
    public String getDescription() {
        return "Search by Price Range";
    }
}