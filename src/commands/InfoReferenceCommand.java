package commands;

public class InfoReferenceCommand implements Command {

    @Override
    public void execute() {
        System.out.println("\n╔════════════════════════════════════════════════════════════════════╗");
        System.out.println("║             KNIGHT EQUIPMENT SYSTEM - REFERENCE                    ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("ABOUT:");
        System.out.println("  This program helps you manage a knight's equipment and arsenal.");
        System.out.println("  You can equip knights with various items, calculate costs,");
        System.out.println("  and manage your ammunition inventory.");
        System.out.println();
        System.out.println("FEATURES:");
        System.out.println("  • Load ammunition from files");
        System.out.println("  • Sort by price or weight");
        System.out.println("  • Search by price range");
        System.out.println("  • Equip/unequip knight interactively");
        System.out.println("  • Calculate total equipment cost and weight");
        System.out.println("  • Save arsenal to file");
        System.out.println();
        System.out.println("EQUIPMENT TYPES:");
        System.out.println("  ⚔  Weapons: Sword (one-handed), Spear (two-handed)");
        System.out.println("  🛡  Armor: Helmet, Chestplate, Shield");
        System.out.println("  ✨  Enchantments: Ring, Crown");
        System.out.println();
        System.out.println("EQUIPMENT SLOTS:");
        System.out.println("  • Head: Helmet or Crown");
        System.out.println("  • Chest: Chestplate");
        System.out.println("  • Left Hand: Shield or Spear (if two-handed)");
        System.out.println("  • Right Hand: Sword or Spear (if two-handed)");
        System.out.println("  • Rings: Left Ring, Right Ring");
        System.out.println();
        System.out.println("FILE FORMAT:");
        System.out.println("  TYPE|name|weight|price|specific_parameters");
        System.out.println("  Example: SWORD|Excalibur|4.0|2000|85|100");
        System.out.println();
        System.out.println("TIPS:");
        System.out.println("  • Load default data on first run to get example items");
        System.out.println("  • Two-handed weapons occupy both hands");
        System.out.println("  • Sort before searching for better organization");
        System.out.println("  • Save your custom arsenal for future use");
        System.out.println();
        System.out.println("Press Enter to continue...");

        try {
            System.in.read();
        } catch (Exception e) {
            // Ignore
        }
    }

    @Override
    public String getDescription() {
        return "Info/Reference";
    }
}