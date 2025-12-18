package commands;

import knight.Knight;

public class ShowEquippedCommand implements Command {
    private final Knight knight;

    public ShowEquippedCommand(Knight knight) {
        this.knight = knight;
    }


    @Override
    public void execute() {
        knight.showEquipped();
    }

    @Override
    public String getDescription() {
        return "Show Equipped Items";
    }
}