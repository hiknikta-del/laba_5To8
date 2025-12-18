package commands;

import main.Menu; // <--- Важливо: імпорт Menu з іншого пакету

public class OpenKnightManagerCommand implements Command {
    private final Menu knightMenu;

    // Конструктор має бути ТІЛЬКИ ОДИН
    public OpenKnightManagerCommand(Menu knightMenu) {
        this.knightMenu = knightMenu;
    }

    @Override
    public void execute() {
        knightMenu.run();
    }

    @Override
    public String getDescription() {
        return "Knight and Arsenal Management";
    }
}