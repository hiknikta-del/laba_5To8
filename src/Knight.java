import amunition.Amunition;
import amunition.EquipmentSlot;

import java.util.HashMap;
import java.util.Map;

public class Knight {
    private Map<EquipmentSlot, Amunition> equipment;

    public Knight() {
        this.equipment = new HashMap<>();
    }

    public void equip(Amunition item) {
        System.out.println("Equip functionality - to be implemented");
    }

    public void unequip(EquipmentSlot slot) {
        System.out.println("Unequip functionality - to be implemented");
    }

    public void showEquipped() {
        System.out.println("Show equipped functionality - to be implemented");
    }

    public double calculatePrice() {
        System.out.println("Calculate price functionality - to be implemented");
        return 0.0;
    }

    public Map<EquipmentSlot, Amunition> getEquipment() {
        return equipment;
    }
}