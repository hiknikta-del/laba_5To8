package commands;

import arsenal.Arsenal;

public class SortByWeightCommand implements Command {
    private Arsenal arsenal;

    public SortByWeightCommand(Arsenal arsenal) {
        this.arsenal = arsenal;
    }


    @Override
    public void execute() {
        arsenal.sortByWeight();
        System.out.println("Sorted by weight.\n");
    }

    @Override
    public String getDescription() {
        return "Sort by Weight";
    }
}