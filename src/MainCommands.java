import java.util.Scanner;

public class MainCommands {
    private Knight knight;
    private Arsenal arsenal;
    private Scanner scanner;

    public MainCommands() {
        this.knight = new Knight();
        this.arsenal = new Arsenal();
        this.scanner = new Scanner(System.in);
    }

    public void consoleMenu() {
        int choice;

        while (true) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("Press 1 - Knight and Arsenal Management");
            System.out.println("Press 2 - Info/Reference");
            System.out.println("Press 3 - Exit");
            System.out.print("Your choice: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    knightManagementMenu();
                    break;
                case 2:
                    infoReference();
                    break;
                case 3:
                    exit();
                    break;
                default:
                    System.out.println("Wrong input");
            }
        }
    }

    private void knightManagementMenu() {
        int choice;

        while (true) {
            System.out.println("\n=== KNIGHT & ARSENAL MANAGEMENT ===");
            System.out.println("Press 1 - Show all Ammunition");
            System.out.println("Press 2 - Sort by Price");
            System.out.println("Press 3 - Sort by Weight");
            System.out.println("Press 4 - Equip the Knight");
            System.out.println("Press 5 - Unequip the Knight");
            System.out.println("Press 6 - Show Equipped Items");
            System.out.println("Press 7 - Calculate Total Cost");
            System.out.println("Press 8 - Back to Main Menu");
            System.out.print("Your choice: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    showAllAmunition();
                    break;
                case 2:
                    sortByPrice();
                    break;
                case 3:
                    sortByWeight();
                    break;
                case 4:
                    equipKnight();
                    break;
                case 5:
                    unequipKnight();
                    break;
                case 6:
                    showEquipped();
                    break;
                case 7:
                    calculateTotalCost();
                    break;
                case 8:
                    return;
                default:
                    System.out.println("Wrong input");
            }
        }
    }

    private void showAllAmunition() {
        System.out.println("\n=== ALL AMMUNITION ===");
        arsenal.showAmunition();
    }

    private void sortByPrice() {
        arsenal.sortByPrice();
        System.out.println("Sorted by price.\n");
    }

    private void sortByWeight() {
        arsenal.sortByWeight();
        System.out.println("Sorted by weight.\n");
    }

    private void equipKnight() {
        knight.equip(null);
    }

    private void unequipKnight() {
        knight.unequip(null);
    }

    private void showEquipped() {
        knight.showEquipped();
    }

    private void calculateTotalCost() {
        double cost = knight.calculatePrice();
        System.out.printf("Total cost: %.2f gold\n", cost);
    }

    public void infoReference() {
        System.out.println("\n=== KNIGHT EQUIPMENT SYSTEM ===");
        System.out.println("This program manages knight equipment and arsenal.");
        System.out.println("You can equip knights, sort ammunition, and calculate costs.");
        System.out.println("================================\n");
    }

    public void exit() {
        System.out.println("Exiting program...");
        scanner.close();
        System.exit(0);
    }
}