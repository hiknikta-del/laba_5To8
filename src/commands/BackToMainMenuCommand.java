package commands;

public class BackToMainMenuCommand implements Command {

    @Override
    public void execute() {
        // Логіка повернення обробляється в MenuInvoker
    }

    @Override
    public String getDescription() {
        return "Back to Main Menu";
    }
}