package amunition.items;

import amunition.Armor;
import amunition.EquipmentSlot;

import java.util.Set;

public class Helmet extends Armor {
    private boolean hasVisor;

    public Helmet(String name, double weight, double price,
                  double protection, double coverage, boolean hasVisor) {
        super(name, weight, price, protection, coverage);
        this.hasVisor = hasVisor;
    }

    public boolean hasVisor() {
        return hasVisor;
    }

    @Override
    public Set<EquipmentSlot> getSlots() {
        return Set.of(EquipmentSlot.HEAD);
    }
}