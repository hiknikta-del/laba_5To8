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

    @Override
    public String toString() {
        return String.format("%s | Protection: %.1f | Coverage: %.1f%% | %s",
                super.toString(),
                protection,
                coverage,
                getClass().getSimpleName());
    }
}