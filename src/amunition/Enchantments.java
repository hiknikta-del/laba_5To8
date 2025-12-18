package amunition;

public abstract class Enchantments extends Amunition {
    private String buff;
    private String debuff;

    public Enchantments(String name, double weight, double price,
                        String buff, String debuff) {
        super(name, weight, price);
        this.buff = buff;
        this.debuff = debuff;
    }

    public String getBuff() {
        return buff;
    }

    public String getDebuff() {
        return debuff;
    }

    @Override
    public String toString() {
        return String.format("%s | Buff: %s | Debuff: %s | %s",
                super.toString(),
                buff != null ? buff : "None",
                debuff != null ? debuff : "None",
                getClass().getSimpleName());
    }
}