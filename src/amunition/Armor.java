package amunition;

public abstract class Armor extends Amunition {
    private double protection;
    private double coverage;

    public Armor(String name, double weight, double price,
                 double protection, double coverage) {
        super(name, weight, price);
        this.protection = protection;
        this.coverage = coverage;
    }

    public double getProtection() {
        return protection;
    }

    public double getCoverage() {
        return coverage;
    }
}