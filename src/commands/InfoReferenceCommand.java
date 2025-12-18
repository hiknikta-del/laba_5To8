package commands;

public class InfoReferenceCommand implements Command {

    @Override
    public void execute() {
        System.out.println("\n=== KNIGHT EQUIPMENT SYSTEM ===");
        System.out.println("This program manages knight equipment and arsenal.");
        System.out.println("You can equip knights, sort ammunition, and calculate costs.");
        System.out.println("================================\n");
    }

    @Override
    public String getDescription() {
        return "Info/Reference";
    }
}
