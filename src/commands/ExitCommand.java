package commands;

import utils.AppLogger;

public class ExitCommand implements Command {

    public ExitCommand() {
    }

    @Override
    public void execute() {
        AppLogger.logInfo("User initiated exit. Shutting down.");

        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║         Thank you for using our system!        ║");
        System.out.println("║              Goodbye, brave knight!            ║");
        System.out.println("╚════════════════════════════════════════════════╝");
        System.exit(0);
    }

    @Override
    public String getDescription() {
        return "Exit";
    }
}