package commands;

import arsenal.Arsenal;
import utils.FileManager;

import java.util.Scanner;

public class LoadFromFileCommand implements Command {
    private final Arsenal arsenal;
    private final Scanner scanner;

    public LoadFromFileCommand(Arsenal arsenal, Scanner scanner) {
        this.arsenal = arsenal;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("Enter filename (or press Enter for default 'data/ammunition.txt'): ");
        String filename = scanner.nextLine().trim();

        if (filename.isEmpty()) {
            filename = FileManager.getDefaultDataFile();
        }

        boolean success = FileManager.loadAmunitionFromFile(arsenal, filename);

        if (!success) {
            System.out.println("\nWould you like to create a default data file? (y/n)");
            String response = scanner.nextLine().trim().toLowerCase();
            if (response.equals("y") || response.equals("yes")) {
                FileManager.createDefaultDataFile(filename);
                FileManager.loadAmunitionFromFile(arsenal, filename);
            }
        }
    }

    @Override
    public String getDescription() {
        return "Load from File";
    }
}