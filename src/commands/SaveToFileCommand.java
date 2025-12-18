package commands;

import arsenal.Arsenal;
import utils.FileManager;

import java.util.Scanner;

public class SaveToFileCommand implements Command {
    private final Arsenal arsenal;
    private final Scanner scanner;

    public SaveToFileCommand(Arsenal arsenal, Scanner scanner) {
        this.arsenal = arsenal;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        if (arsenal.isEmpty()) {
            System.out.println("Arsenal is empty. Nothing to save.");
            return;
        }

        System.out.print("Enter filename to save (e.g., 'export/my_arsenal.txt'): ");
        String filename = scanner.nextLine().trim();

        if (filename.isEmpty()) {
            System.out.println("Filename cannot be empty.");
            return;
        }

        FileManager.saveArsenalToFile(arsenal, filename);
    }

    @Override
    public String getDescription() {
        return "Save Arsenal to File";
    }
}