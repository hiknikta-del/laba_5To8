package commands;

import commands.Command;
import knight.Knight;

public class CalculateTotalCostCommand implements Command {
    private Knight knight;

    public CalculateTotalCostCommand(Knight knight) {
        this.knight = knight;
    }



    @Override
    public void execute() {
        double cost = knight.calculatePrice();
        System.out.printf("Total cost: %.2f gold\n", cost);
    }

    @Override
    public String getDescription() {
        return "Calculate Total Cost";
    }
}