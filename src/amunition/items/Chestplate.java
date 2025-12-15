package amunition.items;

import amunition.Armor;
import amunition.EquipmentSlot;

import java.util.Set;

public class Chestplate extends Armor {
    private boolean isOnepiece;

    public Chestplate(String name, double weight, double price,
                      double protection, double coverage, boolean isOnepiece) {
        super(name, weight, price, protection, coverage);
        this.isOnepiece = isOnepiece;
    }

    public boolean isOnepiece() {
        return isOnepiece;
    }

    @Override
    public Set<EquipmentSlot> getSlots() {
        return Set.of(EquipmentSlot.CHEST);
    }
}