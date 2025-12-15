package amunition.items;

import amunition.EquipmentSlot;
import amunition.Weapon;
import java.util.Set;

public class Sword extends Weapon {
    private double bladeLength;

    public Sword(String name, double weight, double price,
                 double damage, double bladeLength) {
        super(name, weight, price, damage, false);
        this.bladeLength = bladeLength;
    }

    public double getBladeLength() {
        return bladeLength;
    }

    @Override
    public Set<EquipmentSlot> getSlots() {
        return Set.of(EquipmentSlot.RIGHT_HAND);
    }
}