package amunition.items;

import amunition.EquipmentSlot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Set;

import static org.assertj.core.api.Assertions.*;

/**
 * Тести для всіх класів амуніції
 * Перевіряємо конструктори, геттери та специфічні властивості
 */
class AmunitionItemsTest {

    // ========== Sword Tests ==========

    @Test
    @DisplayName("Створення меча з параметрами")
    void testSwordCreation() {
        Sword sword = new Sword("Excalibur", 4.0, 2000.0, 85.0, 100.0);

        assertThat(sword.getName()).isEqualTo("Excalibur");
        assertThat(sword.getWeight()).isEqualTo(4.0);
        assertThat(sword.getPrice()).isEqualTo(2000.0);
        assertThat(sword.getDamage()).isEqualTo(85.0);
        assertThat(sword.getBladeLength()).isEqualTo(100.0);
        assertThat(sword.isTwoHanded()).isFalse();
    }

    @Test
    @DisplayName("Меч займає правий слот")
    void testSwordSlots() {
        Sword sword = new Sword("Test", 3.0, 100.0, 50.0, 80.0);

        Set<EquipmentSlot> slots = sword.getSlots();

        assertThat(slots).containsExactly(EquipmentSlot.RIGHT_HAND);
    }

    @Test
    @DisplayName("toString меча містить інформацію")
    void testSwordToString() {
        Sword sword = new Sword("Test Sword", 3.0, 100.0, 50.0, 80.0);

        String result = sword.toString();

        assertThat(result).contains("Test Sword");
        assertThat(result).contains("3.00");
        assertThat(result).contains("100.00");
        assertThat(result).contains("50.0");
        assertThat(result).contains("One-handed");
    }

    // ========== Spear Tests ==========

    @Test
    @DisplayName("Створення списа з параметрами")
    void testSpearCreation() {
        Spear spear = new Spear("Lance", 5.5, 450.0, 60.0, 220.0);

        assertThat(spear.getName()).isEqualTo("Lance");
        assertThat(spear.getWeight()).isEqualTo(5.5);
        assertThat(spear.getPrice()).isEqualTo(450.0);
        assertThat(spear.getDamage()).isEqualTo(60.0);
        assertThat(spear.getSpearLength()).isEqualTo(220.0);
        assertThat(spear.isTwoHanded()).isTrue();
    }

    @Test
    @DisplayName("Спис займає обидві руки")
    void testSpearSlots() {
        Spear spear = new Spear("Test", 4.0, 150.0, 40.0, 200.0);

        Set<EquipmentSlot> slots = spear.getSlots();

        assertThat(slots).containsExactlyInAnyOrder(
                EquipmentSlot.LEFT_HAND,
                EquipmentSlot.RIGHT_HAND
        );
    }

    @Test
    @DisplayName("toString списа містить Two-handed")
    void testSpearToString() {
        Spear spear = new Spear("Test Spear", 4.0, 150.0, 40.0, 200.0);

        String result = spear.toString();

        assertThat(result).contains("Test Spear");
        assertThat(result).contains("Two-handed");
    }

    // ========== Helmet Tests ==========

    @Test
    @DisplayName("Створення шолома з параметрами")
    void testHelmetCreation() {
        Helmet helmet = new Helmet("Steel Helmet", 4.2, 300.0, 35.0, 85.0, true);

        assertThat(helmet.getName()).isEqualTo("Steel Helmet");
        assertThat(helmet.getWeight()).isEqualTo(4.2);
        assertThat(helmet.getPrice()).isEqualTo(300.0);
        assertThat(helmet.getProtection()).isEqualTo(35.0);
        assertThat(helmet.getCoverage()).isEqualTo(85.0);
        assertThat(helmet.hasVisor()).isTrue();
    }

    @Test
    @DisplayName("Шолом займає слот голови")
    void testHelmetSlots() {
        Helmet helmet = new Helmet("Test", 3.5, 150.0, 25.0, 80.0, false);

        Set<EquipmentSlot> slots = helmet.getSlots();

        assertThat(slots).containsExactly(EquipmentSlot.HEAD);
    }

    @Test
    @DisplayName("toString шолома містить захист і покриття")
    void testHelmetToString() {
        Helmet helmet = new Helmet("Test Helmet", 3.5, 150.0, 25.0, 80.0, true);

        String result = helmet.toString();

        assertThat(result).contains("Test Helmet");
        assertThat(result).contains("Protection: 25.0");
        assertThat(result).contains("Coverage: 80.0");
        assertThat(result).contains("Helmet");
    }

    // ========== Chestplate Tests ==========

    @Test
    @DisplayName("Створення кіраси з параметрами")
    void testChestplateCreation() {
        Chestplate chest = new Chestplate("Steel Plate", 14.5, 1000.0, 70.0, 95.0, true);

        assertThat(chest.getName()).isEqualTo("Steel Plate");
        assertThat(chest.getWeight()).isEqualTo(14.5);
        assertThat(chest.getPrice()).isEqualTo(1000.0);
        assertThat(chest.getProtection()).isEqualTo(70.0);
        assertThat(chest.getCoverage()).isEqualTo(95.0);
        assertThat(chest.isOnepiece()).isTrue();
    }

    @Test
    @DisplayName("Кіраса займає слот грудей")
    void testChestplateSlots() {
        Chestplate chest = new Chestplate("Test", 12.0, 500.0, 50.0, 90.0, false);

        Set<EquipmentSlot> slots = chest.getSlots();

        assertThat(slots).containsExactly(EquipmentSlot.CHEST);
    }

    // ========== Shield Tests ==========

    @Test
    @DisplayName("Створення щита з параметрами")
    void testShieldCreation() {
        Shield shield = new Shield("Iron Shield", 8.0, 250.0, 35.0, 50.0, "Kite");

        assertThat(shield.getName()).isEqualTo("Iron Shield");
        assertThat(shield.getWeight()).isEqualTo(8.0);
        assertThat(shield.getPrice()).isEqualTo(250.0);
        assertThat(shield.getProtection()).isEqualTo(35.0);
        assertThat(shield.getCoverage()).isEqualTo(50.0);
        assertThat(shield.getShape()).isEqualTo("Kite");
    }

    @Test
    @DisplayName("Щит займає ліву руку")
    void testShieldSlots() {
        Shield shield = new Shield("Test", 4.0, 80.0, 15.0, 40.0, "Round");

        Set<EquipmentSlot> slots = shield.getSlots();

        assertThat(slots).containsExactly(EquipmentSlot.LEFT_HAND);
    }

    // ========== Ring Tests ==========

    @Test
    @DisplayName("Створення кільця з параметрами")
    void testRingCreation() {
        Ring ring = new Ring("Ring of Strength", 0.1, 500.0, "+10 STR", "None", 18.0);

        assertThat(ring.getName()).isEqualTo("Ring of Strength");
        assertThat(ring.getWeight()).isEqualTo(0.1);
        assertThat(ring.getPrice()).isEqualTo(500.0);
        assertThat(ring.getBuff()).isEqualTo("+10 STR");
        assertThat(ring.getDebuff()).isEqualTo("None");
        assertThat(ring.getFingerSize()).isEqualTo(18.0);
    }

    @Test
    @DisplayName("Кільце займає лівий слот кільця")
    void testRingSlots() {
        Ring ring = new Ring("Test", 0.1, 500.0, "+10 STR", "None", 18.0);

        Set<EquipmentSlot> slots = ring.getSlots();

        assertThat(slots).containsExactly(EquipmentSlot.LEFT_RING);
    }

    @Test
    @DisplayName("toString кільця містить бафи")
    void testRingToString() {
        Ring ring = new Ring("Test Ring", 0.1, 500.0, "+10 STR", "-5 AGI", 18.0);

        String result = ring.toString();

        assertThat(result).contains("Test Ring");
        assertThat(result).contains("Buff: +10 STR");
        assertThat(result).contains("Debuff: -5 AGI");
    }

    @Test
    @DisplayName("toString кільця показує None для null дебафа")
    void testRingToStringNullDebuff() {
        Ring ring = new Ring("Test", 0.1, 500.0, "+10 STR", null, 18.0);

        String result = ring.toString();

        assertThat(result).contains("Debuff: None");
    }

    // ========== Crown Tests ==========

    @Test
    @DisplayName("Створення корони з параметрами")
    void testCrownCreation() {
        Crown crown = new Crown("King's Crown", 2.0, 10000.0, "+15 CHA", "-5 AGI", "King");

        assertThat(crown.getName()).isEqualTo("King's Crown");
        assertThat(crown.getWeight()).isEqualTo(2.0);
        assertThat(crown.getPrice()).isEqualTo(10000.0);
        assertThat(crown.getBuff()).isEqualTo("+15 CHA");
        assertThat(crown.getDebuff()).isEqualTo("-5 AGI");
        assertThat(crown.getRoyaltyType()).isEqualTo("King");
    }

    @Test
    @DisplayName("Корона займає слот голови")
    void testCrownSlots() {
        Crown crown = new Crown("Test", 1.5, 3000.0, "+5 CHA", "None", "Baron");

        Set<EquipmentSlot> slots = crown.getSlots();

        assertThat(slots).containsExactly(EquipmentSlot.HEAD);
    }

    @Test
    @DisplayName("toString корони містить бафи")
    void testCrownToString() {
        Crown crown = new Crown("Test Crown", 2.0, 10000.0, "+15 CHA", "-5 AGI", "King");

        String result = crown.toString();

        assertThat(result).contains("Test Crown");
        assertThat(result).contains("Buff: +15 CHA");
        assertThat(result).contains("Debuff: -5 AGI");
    }

    // ========== EquipmentSlot Enum Tests ==========

    @Test
    @DisplayName("EquipmentSlot має правильні назви")
    void testEquipmentSlotDisplayNames() {
        assertThat(EquipmentSlot.HEAD.getDisplayName()).isEqualTo("Head");
        assertThat(EquipmentSlot.CHEST.getDisplayName()).isEqualTo("Chest");
        assertThat(EquipmentSlot.LEFT_HAND.getDisplayName()).isEqualTo("Left Hand");
        assertThat(EquipmentSlot.RIGHT_HAND.getDisplayName()).isEqualTo("Right Hand");
        assertThat(EquipmentSlot.LEFT_RING.getDisplayName()).isEqualTo("Left Ring");
        assertThat(EquipmentSlot.RIGHT_RING.getDisplayName()).isEqualTo("Right Ring");
    }

    @Test
    @DisplayName("EquipmentSlot має всі слоти")
    void testEquipmentSlotValues() {
        EquipmentSlot[] slots = EquipmentSlot.values();

        assertThat(slots).hasSize(6);
        assertThat(slots).contains(
                EquipmentSlot.HEAD,
                EquipmentSlot.CHEST,
                EquipmentSlot.LEFT_HAND,
                EquipmentSlot.RIGHT_HAND,
                EquipmentSlot.LEFT_RING,
                EquipmentSlot.RIGHT_RING
        );
    }
}