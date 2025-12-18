package commands;

import arsenal.Arsenal;
import knight.Knight;

/**
 * @deprecated Використовуйте InteractiveEquipCommand для інтерактивного режиму
 */
@Deprecated
public class EquipKnightCommand implements Command {
    private final Arsenal arsenal;
    private final Knight knight;

    public EquipKnightCommand(Knight knight, Arsenal arsenal) {
        this.knight = knight;
        this.arsenal = arsenal;
    }

    @Override
    public void execute() {
        System.out.println("Please use 'Equip Item (Interactive)' option from the menu.");
    }

    @Override
    public String getDescription() {
        return "Equip the Knight";
    }
}