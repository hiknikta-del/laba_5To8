package commands;

public class ExitCommand implements Command {

    public ExitCommand() {
        // Не потрібен scanner - він закривається в Main
    }

    @Override
    public void execute() {
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