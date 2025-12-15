package amunition.items;

import amunition.EquipmentSlot;
import amunition.Weapon;
import java.util.Set;

public class Spear extends Weapon {
    private double spearLength;

    public Spear(String name, double weight, double price,
                 double damage, double spearLength) {
        super(name, weight, price, damage, true);
        this.spearLength = spearLength;
    }

    public double getSpearLength() {
        return spearLength;
    }

    @Override
    public Set<EquipmentSlot> getSlots() {
        return Set.of(EquipmentSlot.LEFT_HAND, EquipmentSlot.RIGHT_HAND);
    }
}