package commands;

import knight.Knight;

public class UnequipKnightCommand implements Command {
    private Knight knight;

    public UnequipKnightCommand(Knight knight) {
        this.knight = knight;
    }


    @Override
    public void execute() {
        knight.unequip(null);
    }

    @Override
    public String getDescription() {
        return "Unequip the Knight";
    }
}