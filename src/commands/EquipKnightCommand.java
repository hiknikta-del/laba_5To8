package commands;

import arsenal.Arsenal;
import knight.Knight;

public class EquipKnightCommand implements Command {
    private final Arsenal arsenal;
    private Knight knight;

    public EquipKnightCommand(Knight knight, Arsenal arsenal) {
        this.knight = knight;
        this.arsenal = arsenal; // Якщо команда використовує і арсенал теж
    }

    @Override
    public void execute() {
        knight.equip(null);
    }

    @Override
    public String getDescription() {
        return "Equip the Knight";
    }
}