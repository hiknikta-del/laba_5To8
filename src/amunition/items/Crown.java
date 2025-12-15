package amunition.items;

import amunition.Enchantments;
import amunition.EquipmentSlot;

import java.util.Set;

public class Crown extends Enchantments {
    private String royaltyType;

    public Crown(String name, double weight, double price,
                 String buff, String debuff, String royaltyType) {
        super(name, weight, price, buff, debuff);
        this.royaltyType = royaltyType;
    }

    public String getRoyaltyType() {
        return royaltyType;
    }

    @Override
    public Set<EquipmentSlot> getSlots() {
        return Set.of(EquipmentSlot.HEAD);
    }
}