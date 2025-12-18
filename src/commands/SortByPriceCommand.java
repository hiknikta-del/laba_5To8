package commands;

import arsenal.Arsenal;

public class SortByPriceCommand implements Command {
    private Arsenal arsenal;

    public SortByPriceCommand(Arsenal arsenal) {
        this.arsenal = arsenal;
    }



    @Override
    public void execute() {
        arsenal.sortByPrice();
        System.out.println("Sorted by price.\n");
    }

    @Override
    public String getDescription() {
        return "Sort by Price";
    }
}