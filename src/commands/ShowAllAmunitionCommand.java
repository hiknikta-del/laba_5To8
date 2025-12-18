package commands;

import arsenal.Arsenal;

public class ShowAllAmunitionCommand implements Command {
    private final Arsenal arsenal;

    public ShowAllAmunitionCommand(Arsenal arsenal) {
        this.arsenal = arsenal;
    }

    @Override
    public void execute() {
        System.out.println();
        arsenal.showAmunition();
        System.out.println();
    }

    @Override
    public String getDescription() {
        return "Show all Ammunition";
    }
}