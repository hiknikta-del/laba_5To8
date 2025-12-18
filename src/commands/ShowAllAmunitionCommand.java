package commands;

import arsenal.Arsenal;

public class ShowAllAmunitionCommand implements Command {
    private Arsenal arsenal;

    public ShowAllAmunitionCommand(Arsenal arsenal) {
        this.arsenal = arsenal;
    }


    @Override
    public void execute() {
        System.out.println("\n=== ALL AMMUNITION ===");
        arsenal.showAmunition();
    }

    @Override
    public String getDescription() {
        return "Show all Ammunition";
    }
}