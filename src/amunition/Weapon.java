package amunition;

public abstract class Weapon extends Amunition {
    private double damage;
    private boolean isTwoHanded;

    public Weapon(String name, double weight, double price,
                  double damage, boolean isTwoHanded) {
        super(name, weight, price);
        this.damage = damage;
        this.isTwoHanded = isTwoHanded;
    }

    public double getDamage() {
        return damage;
    }

    public boolean isTwoHanded() {
        return isTwoHanded;
    }

    @Override
    public String toString() {
        return String.format("%s | Damage: %.1f | %s | %s",
                super.toString(),
                damage,
                isTwoHanded ? "Two-handed" : "One-handed",
                getClass().getSimpleName());
    }
}