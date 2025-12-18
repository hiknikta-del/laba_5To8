package commands;

import knight.Knight;

public class CalculateTotalCostCommand implements Command {
    private final Knight knight;

    public CalculateTotalCostCommand(Knight knight) {
        this.knight = knight;
    }

    @Override
    public void execute() {
        if (!knight.isEquipped()) {
            System.out.println("Knight has no equipment.");
            return;
        }

        double cost = knight.calculatePrice();
        double weight = knight.calculateWeight();

        System.out.println("\n=== EQUIPMENT SUMMARY ===");
        System.out.println("Knight: " + knight.getName());
        System.out.printf("Total Weight: %.2f kg\n", weight);
        System.out.printf("Total Cost: %.2f gold\n", cost);
        System.out.println("========================\n");
    }

    @Override
    public String getDescription() {
        return "Calculate Total Cost & Weight";
    }
}