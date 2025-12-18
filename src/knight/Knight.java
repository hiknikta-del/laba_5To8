package knight;

import amunition.Amunition;
import amunition.EquipmentSlot;
import amunition.Weapon;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Клас Knight представляє лицаря та його спорядження.
 * Управляє екіпіровкою, розрахунком характеристик та вагою/ціною.
 */
public class Knight {
    private Map<EquipmentSlot, Amunition> equipment;
    private String name;

    public Knight() {
        this("Unknown Knight");
    }

    public Knight(String name) {
        this.name = name;
        this.equipment = new HashMap<>();
    }

    /**
     * Екіпірує амуніцію на лицаря
     * @param item амуніція для екіпірування
     */
    public void equip(Amunition item) {
        if (item == null) {
            System.out.println("Cannot equip null item.");
            return;
        }

        Set<EquipmentSlot> requiredSlots = item.getSlots();

        // Перевірка чи слоти вільні
        for (EquipmentSlot slot : requiredSlots) {
            if (equipment.containsKey(slot)) {
                System.out.println("Cannot equip " + item.getName() +
                        ": " + slot.getDisplayName() + " slot is already occupied by " +
                        equipment.get(slot).getName());
                return;
            }
        }

        // Спеціальна перевірка для двуручної зброї
        if (item instanceof Weapon && ((Weapon) item).isTwoHanded()) {
            if (equipment.containsKey(EquipmentSlot.LEFT_HAND) ||
                    equipment.containsKey(EquipmentSlot.RIGHT_HAND)) {
                System.out.println("Cannot equip two-handed weapon: hands are occupied.");
                return;
            }
        }

        // Перевірка чи можна одягнути одноручну зброю якщо є двуручна
        if (item.getSlots().contains(EquipmentSlot.LEFT_HAND) ||
                item.getSlots().contains(EquipmentSlot.RIGHT_HAND)) {
            for (Amunition equipped : equipment.values()) {
                if (equipped instanceof Weapon && ((Weapon) equipped).isTwoHanded()) {
                    System.out.println("Cannot equip " + item.getName() +
                            ": Two-handed weapon is equipped.");
                    return;
                }
            }
        }

        // Екіпіруємо предмет
        for (EquipmentSlot slot : requiredSlots) {
            equipment.put(slot, item);
        }

        System.out.println("Successfully equipped: " + item.getName());
    }

    /**
     * Знімає амуніцію зі слота
     * @param slot слот для звільнення
     */
    public void unequip(EquipmentSlot slot) {
        if (slot == null) {
            System.out.println("Invalid slot.");
            return;
        }

        if (!equipment.containsKey(slot)) {
            System.out.println("Slot " + slot.getDisplayName() + " is empty.");
            return;
        }

        Amunition item = equipment.get(slot);

        // Видаляємо всі слоти що займає цей предмет
        Set<EquipmentSlot> occupiedSlots = item.getSlots();
        for (EquipmentSlot occupiedSlot : occupiedSlots) {
            equipment.remove(occupiedSlot);
        }

        System.out.println("Unequipped: " + item.getName());
    }

    /**
     * Знімає конкретний предмет
     * @param item предмет для зняття
     */
    public void unequipItem(Amunition item) {
        if (item == null) {
            System.out.println("Cannot unequip null item.");
            return;
        }

        boolean found = false;
        for (EquipmentSlot slot : item.getSlots()) {
            if (equipment.get(slot) == item) {
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println(item.getName() + " is not equipped.");
            return;
        }

        for (EquipmentSlot slot : item.getSlots()) {
            equipment.remove(slot);
        }

        System.out.println("Unequipped: " + item.getName());
    }

    /**
     * Показує все екіпіроване спорядження
     */
    public void showEquipped() {
        if (equipment.isEmpty()) {
            System.out.println("\n" + name + " has no equipment.");
            return;
        }

        System.out.println("\n=== EQUIPPED ITEMS FOR " + name.toUpperCase() + " ===");

        // Використовуємо Set для унікальних предметів (двуручна зброя займає 2 слоти)
        Map<Amunition, Set<EquipmentSlot>> uniqueItems = new HashMap<>();

        for (Map.Entry<EquipmentSlot, Amunition> entry : equipment.entrySet()) {
            Amunition item = entry.getValue();
            if (!uniqueItems.containsKey(item)) {
                uniqueItems.put(item, item.getSlots());
            }
        }

        int index = 1;
        for (Map.Entry<Amunition, Set<EquipmentSlot>> entry : uniqueItems.entrySet()) {
            Amunition item = entry.getKey();
            Set<EquipmentSlot> slots = entry.getValue();

            StringBuilder slotNames = new StringBuilder();
            for (EquipmentSlot slot : slots) {
                if (slotNames.length() > 0) {
                    slotNames.append(", ");
                }
                slotNames.append(slot.getDisplayName());
            }

            System.out.println(index + ". [" + slotNames + "] " + item.toString());
            index++;
        }

        System.out.println("\nTotal weight: " + calculateWeight() + " kg");
        System.out.println("Total cost: " + calculatePrice() + " gold");
    }

    /**
     * Розраховує загальну вартість спорядження
     * @return загальна вартість
     */
    public double calculatePrice() {
        double totalPrice = 0.0;

        // Використовуємо Set для унікальних предметів
        Set<Amunition> uniqueItems = Set.copyOf(equipment.values());

        for (Amunition item : uniqueItems) {
            totalPrice += item.getPrice();
        }

        return totalPrice;
    }

    /**
     * Розраховує загальну вагу спорядження
     * @return загальна вага
     */
    public double calculateWeight() {
        double totalWeight = 0.0;

        // Використовуємо Set для унікальних предметів
        Set<Amunition> uniqueItems = Set.copyOf(equipment.values());

        for (Amunition item : uniqueItems) {
            totalWeight += item.getWeight();
        }

        return totalWeight;
    }

    /**
     * Перевіряє чи слот зайнятий
     * @param slot слот для перевірки
     * @return true якщо зайнятий
     */
    public boolean isSlotOccupied(EquipmentSlot slot) {
        return equipment.containsKey(slot);
    }

    /**
     * Отримує предмет зі слота
     * @param slot слот
     * @return амуніція або null
     */
    public Amunition getEquippedItem(EquipmentSlot slot) {
        return equipment.get(slot);
    }

    /**
     * Знімає все спорядження
     */
    public void unequipAll() {
        if (equipment.isEmpty()) {
            System.out.println("Knight has no equipment to unequip.");
            return;
        }

        int count = Set.copyOf(equipment.values()).size();
        equipment.clear();
        System.out.println("Unequipped all items (" + count + " items).");
    }

    /**
     * Повертає карту екіпірованого спорядження
     * @return копія мапи спорядження
     */
    public Map<EquipmentSlot, Amunition> getEquipment() {
        return new HashMap<>(equipment);
    }

    /**
     * Встановлює ім'я лицаря
     * @param name нове ім'я
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Отримує ім'я лицаря
     * @return ім'я
     */
    public String getName() {
        return name;
    }

    /**
     * Перевіряє чи лицар має будь-яке спорядження
     * @return true якщо екіпірований
     */
    public boolean isEquipped() {
        return !equipment.isEmpty();
    }

    @Override
    public String toString() {
        return String.format("Knight: %s | Items: %d | Weight: %.2f kg | Cost: %.2f gold",
                name, Set.copyOf(equipment.values()).size(), calculateWeight(), calculatePrice());
    }
}