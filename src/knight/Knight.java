package knight;

import amunition.Amunition;
import amunition.EquipmentSlot;

import java.util.HashMap;
import java.util.Map;

public class Knight {
    // Зберігаємо спорядження: Слот -> Амуніція
    private Map<EquipmentSlot, Amunition> equipment;

    public Knight() {
        this.equipment = new HashMap<>();
    }

    // Заглушка для одягання
    public void equip(Amunition item) {
        System.out.println("[Knight] equip() called - logic to be implemented in Lab 3");
    }

    // Заглушка для знімання
    public void unequip(EquipmentSlot slot) {
        System.out.println("[Knight] unequip() called - logic to be implemented in Lab 3");
    }

    // Заглушка для показу одягненого
    public void showEquipped() {
        System.out.println("[Knight] showEquipped() called - logic to be implemented in Lab 3");
    }

    // Заглушка для підрахунку ціни (повертає 0.0, щоб не було помилки)
    public double calculatePrice() {
        System.out.println("[Knight] calculatePrice() called - logic to be implemented in Lab 3");
        return 0.0;
    }

    // Геттер (може знадобитися пізніше)
    public Map<EquipmentSlot, Amunition> getEquipment() {
        return equipment;
    }
}