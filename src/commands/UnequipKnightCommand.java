package commands;

import knight.Knight;

/**
 * @deprecated Використовуйте InteractiveUnequipCommand для інтерактивного режиму
 */
@Deprecated
public class UnequipKnightCommand implements Command {
    private final Knight knight;

    public UnequipKnightCommand(Knight knight) {
        this.knight = knight;
    }

    @Override
    public void execute() {
        System.out.println("Please use 'Unequip Item (Interactive)' option from the menu.");
    }

    @Override
    public String getDescription() {
        return "Unequip the Knight";
    }
}