package commands;

public class ExitCommand implements Command {
    private java.util.Scanner scanner;

    public ExitCommand() {
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println("Exiting program...");
        scanner.close();
        System.exit(0);
    }

    @Override
    public String getDescription() {
        return "Exit";
    }
}