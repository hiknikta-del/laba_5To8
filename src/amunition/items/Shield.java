package amunition.items;

import amunition.Armor;
import amunition.EquipmentSlot;

import java.util.Set;

public class Shield extends Armor {
    private String shape;

    public Shield(String name, double weight, double price,
                  double protection, double coverage, String shape) {
        super(name, weight, price, protection, coverage);
        this.shape = shape;
    }

    public String getShape() {
        return shape;
    }

    @Override
    public Set<EquipmentSlot> getSlots() {
        return Set.of(EquipmentSlot.LEFT_HAND);
    }
}
