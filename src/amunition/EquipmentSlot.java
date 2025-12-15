package amunition;

public enum EquipmentSlot {
    HEAD(null),
    CHEST(null),
    LEFT_HAND(null),
    RIGHT_HAND(null),
    LEFT_RING(null),
    RIGHT_RING(null);

    private Amunition amunition;

    EquipmentSlot(Amunition amunition) {
        this.amunition = amunition;
    }

    public Amunition getAmunition() {
        return amunition;
    }

    public void setAmunition(Amunition amunition) {
        this.amunition = amunition;
    }
}