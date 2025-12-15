package amunition.items;

import amunition.Enchantments;
import amunition.EquipmentSlot;

import java.util.Set;

public class Ring extends Enchantments {
    private double fingerSize;

    public Ring(String name, double weight, double price,
                String buff, String debuff, double fingerSize) {
        super(name, weight, price, buff, debuff);
        this.fingerSize = fingerSize;
    }

    public double getFingerSize() {
        return fingerSize;
    }

    @Override
    public Set<EquipmentSlot> getSlots() {
        return Set.of(EquipmentSlot.LEFT_RING);
    }
}