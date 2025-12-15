package amunition;

import java.util.Set;

public abstract class Amunition {
    private double weight;
    private double price;
    private String name;

    public Amunition(String name, double weight, double price) {
        this.name = name;
        this.price = price;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getWeight() {
        return weight;
    }

    public abstract Set<EquipmentSlot> getSlots();

    @Override
    public String toString() {
        return String.format("%s (Weight: %.2f kg, Price: %.2f gold)",
                name, weight, price);
    }
}