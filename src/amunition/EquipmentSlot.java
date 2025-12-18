package amunition;

public enum EquipmentSlot {
    HEAD("Head"),
    CHEST("Chest"),
    LEFT_HAND("Left Hand"),
    RIGHT_HAND("Right Hand"),
    LEFT_RING("Left Ring"),
    RIGHT_RING("Right Ring");

    private final String displayName;

    EquipmentSlot(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}