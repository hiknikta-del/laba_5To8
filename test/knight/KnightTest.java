package knight;

import amunition.Amunition;
import amunition.EquipmentSlot;
import amunition.items.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

/**
 * Тести для класу Knight
 * Перевіряємо екіпірування, розрахунки та управління спорядженням
 */
class KnightTest {

    private Knight knight;
    private Sword sword;
    private Helmet helmet;
    private Shield shield;
    private Spear spear;
    private Ring ring;

    @BeforeEach
    void setUp() {
        knight = new Knight("Test Knight");

        // Створюємо тестові предмети
        sword = new Sword("Test Sword", 3.0, 100.0, 50.0, 80.0);
        helmet = new Helmet("Test Helmet", 2.0, 50.0, 30.0, 80.0, true);
        shield = new Shield("Test Shield", 5.0, 80.0, 40.0, 60.0, "Round");
        spear = new Spear("Test Spear", 4.0, 150.0, 60.0, 200.0);
        ring = new Ring("Test Ring", 0.1, 500.0, "+10 STR", "None", 18.0);
    }

    @Test
    @DisplayName("Конструктор створює лицаря з іменем")
    void testConstructorWithName() {
        assertThat(knight.getName()).isEqualTo("Test Knight");
        assertThat(knight.isEquipped()).isFalse();
    }

    @Test
    @DisplayName("Конструктор за замовчуванням")
    void testDefaultConstructor() {
        Knight defaultKnight = new Knight();
        assertThat(defaultKnight.getName()).isEqualTo("Unknown Knight");
    }

    @Test
    @DisplayName("Екіпірування одноручного меча успішне")
    void testEquipSword() {
        knight.equip(sword);

        assertThat(knight.isEquipped()).isTrue();
        assertThat(knight.isSlotOccupied(EquipmentSlot.RIGHT_HAND)).isTrue();
        assertThat(knight.getEquippedItem(EquipmentSlot.RIGHT_HAND)).isEqualTo(sword);
    }

    @Test
    @DisplayName("Екіпірування двуручної зброї займає обидві руки")
    void testEquipTwoHandedWeapon() {
        knight.equip(spear);

        assertThat(knight.isSlotOccupied(EquipmentSlot.LEFT_HAND)).isTrue();
        assertThat(knight.isSlotOccupied(EquipmentSlot.RIGHT_HAND)).isTrue();
        assertThat(knight.getEquippedItem(EquipmentSlot.LEFT_HAND)).isEqualTo(spear);
        assertThat(knight.getEquippedItem(EquipmentSlot.RIGHT_HAND)).isEqualTo(spear);
    }

    @Test
    @DisplayName("Неможливо екіпірувати null")
    void testEquipNull() {
        knight.equip(null);
        assertThat(knight.isEquipped()).isFalse();
    }

    @Test
    @DisplayName("Неможливо екіпірувати предмет у зайнятий слот")
    void testCannotEquipToOccupiedSlot() {
        Sword sword1 = new Sword("Sword 1", 3.0, 100.0, 50.0, 80.0);
        Sword sword2 = new Sword("Sword 2", 3.5, 150.0, 60.0, 90.0);

        knight.equip(sword1);
        knight.equip(sword2); // Має не вдатися

        assertThat(knight.getEquippedItem(EquipmentSlot.RIGHT_HAND)).isEqualTo(sword1);
    }

    @Test
    @DisplayName("Неможливо екіпірувати одноручну зброю коли є двуручна")
    void testCannotEquipOneHandedWhenTwoHandedEquipped() {
        knight.equip(spear); // Двуручна
        knight.equip(sword);  // Має не вдатися

        assertThat(knight.getEquippedItem(EquipmentSlot.RIGHT_HAND)).isEqualTo(spear);
    }

    @Test
    @DisplayName("Неможливо екіпірувати двуручну зброю коли руки зайняті")
    void testCannotEquipTwoHandedWhenHandsOccupied() {
        knight.equip(sword);
        knight.equip(shield);
        knight.equip(spear); // Має не вдатися

        assertThat(knight.getEquippedItem(EquipmentSlot.RIGHT_HAND)).isEqualTo(sword);
        assertThat(knight.getEquippedItem(EquipmentSlot.LEFT_HAND)).isEqualTo(shield);
    }

    @Test
    @DisplayName("Зняття предмету зі слота")
    void testUnequip() {
        knight.equip(sword);
        knight.unequip(EquipmentSlot.RIGHT_HAND);

        assertThat(knight.isEquipped()).isFalse();
        assertThat(knight.isSlotOccupied(EquipmentSlot.RIGHT_HAND)).isFalse();
    }

    @Test
    @DisplayName("Зняття двуручної зброї звільняє обидві руки")
    void testUnequipTwoHandedWeapon() {
        knight.equip(spear);
        knight.unequip(EquipmentSlot.RIGHT_HAND);

        assertThat(knight.isSlotOccupied(EquipmentSlot.LEFT_HAND)).isFalse();
        assertThat(knight.isSlotOccupied(EquipmentSlot.RIGHT_HAND)).isFalse();
    }

    @Test
    @DisplayName("Зняття з порожнього слота")
    void testUnequipEmptySlot() {
        knight.unequip(EquipmentSlot.HEAD);
        assertThat(knight.isEquipped()).isFalse();
    }

    @Test
    @DisplayName("Зняття null слота")
    void testUnequipNullSlot() {
        knight.unequip(null);
        assertThat(knight.isEquipped()).isFalse();
    }

    @Test
    @DisplayName("Зняття конкретного предмету")
    void testUnequipItem() {
        knight.equip(sword);
        knight.unequipItem(sword);

        assertThat(knight.isEquipped()).isFalse();
    }

    @Test
    @DisplayName("Зняття null предмету")
    void testUnequipNullItem() {
        knight.equip(sword);
        knight.unequipItem(null);

        assertThat(knight.isEquipped()).isTrue();
    }

    @Test
    @DisplayName("Зняття неекіпірованого предмету")
    void testUnequipNotEquippedItem() {
        knight.equip(sword);
        Sword anotherSword = new Sword("Another", 3.0, 100.0, 50.0, 80.0);
        knight.unequipItem(anotherSword);

        assertThat(knight.isEquipped()).isTrue();
    }

    @Test
    @DisplayName("Зняття всього спорядження")
    void testUnequipAll() {
        knight.equip(sword);
        knight.equip(helmet);
        knight.equip(shield);

        knight.unequipAll();

        assertThat(knight.isEquipped()).isFalse();
    }

    @Test
    @DisplayName("Зняття всього коли нічого не екіпіровано")
    void testUnequipAllWhenEmpty() {
        knight.unequipAll();
        assertThat(knight.isEquipped()).isFalse();
    }

    @Test
    @DisplayName("Розрахунок загальної вартості")
    void testCalculatePrice() {
        knight.equip(sword);   // 100
        knight.equip(helmet);  // 50
        knight.equip(shield);  // 80

        assertThat(knight.calculatePrice()).isEqualTo(230.0);
    }

    @Test
    @DisplayName("Розрахунок вартості порожнього спорядження")
    void testCalculatePriceEmpty() {
        assertThat(knight.calculatePrice()).isEqualTo(0.0);
    }

    @Test
    @DisplayName("Розрахунок загальної ваги")
    void testCalculateWeight() {
        knight.equip(sword);   // 3.0
        knight.equip(helmet);  // 2.0
        knight.equip(shield);  // 5.0

        assertThat(knight.calculateWeight()).isEqualTo(10.0);
    }

    @Test
    @DisplayName("Розрахунок ваги порожнього спорядження")
    void testCalculateWeightEmpty() {
        assertThat(knight.calculateWeight()).isEqualTo(0.0);
    }

    @Test
    @DisplayName("Двуручна зброя рахується один раз")
    void testTwoHandedWeaponCountedOnce() {
        knight.equip(spear); // 150 gold, 4.0 kg

        assertThat(knight.calculatePrice()).isEqualTo(150.0);
        assertThat(knight.calculateWeight()).isEqualTo(4.0);
    }

    @Test
    @DisplayName("Перевірка зайнятості слота")
    void testIsSlotOccupied() {
        assertThat(knight.isSlotOccupied(EquipmentSlot.HEAD)).isFalse();

        knight.equip(helmet);

        assertThat(knight.isSlotOccupied(EquipmentSlot.HEAD)).isTrue();
    }

    @Test
    @DisplayName("Отримання екіпірованого предмету")
    void testGetEquippedItem() {
        knight.equip(sword);

        assertThat(knight.getEquippedItem(EquipmentSlot.RIGHT_HAND)).isEqualTo(sword);
        assertThat(knight.getEquippedItem(EquipmentSlot.LEFT_HAND)).isNull();
    }

    @Test
    @DisplayName("Отримання мапи спорядження")
    void testGetEquipment() {
        knight.equip(sword);
        knight.equip(helmet);

        Map<EquipmentSlot, Amunition> equipment = knight.getEquipment();

        assertThat(equipment).hasSize(2);
        assertThat(equipment).containsKeys(EquipmentSlot.RIGHT_HAND, EquipmentSlot.HEAD);
    }

    @Test
    @DisplayName("Зміна імені лицаря")
    void testSetName() {
        knight.setName("Sir Lancelot");
        assertThat(knight.getName()).isEqualTo("Sir Lancelot");
    }

    @Test
    @DisplayName("Перевірка чи екіпірований")
    void testIsEquipped() {
        assertThat(knight.isEquipped()).isFalse();

        knight.equip(sword);

        assertThat(knight.isEquipped()).isTrue();
    }

    @Test
    @DisplayName("toString містить інформацію про лицаря")
    void testToString() {
        knight.equip(sword);
        knight.equip(helmet);

        String result = knight.toString();

        assertThat(result).contains("Test Knight");
        assertThat(result).contains("Items: 2");
        assertThat(result).contains("Weight:");
        assertThat(result).contains("Cost:");
    }

    @Test
    @DisplayName("showEquipped не падає для порожнього спорядження")
    void testShowEquippedEmpty() {
        assertThatCode(() -> knight.showEquipped()).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("showEquipped не падає для екіпірованого спорядження")
    void testShowEquippedWithItems() {
        knight.equip(sword);
        knight.equip(helmet);

        assertThatCode(() -> knight.showEquipped()).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Повний цикл: екіпірування та зняття")
    void testFullCycle() {
        // Екіпіруємо
        knight.equip(sword);
        knight.equip(helmet);
        knight.equip(shield);
        knight.equip(ring);

        assertThat(knight.isEquipped()).isTrue();
        assertThat(knight.calculatePrice()).isGreaterThan(0);

        // Знімаємо частину
        knight.unequipItem(sword);
        assertThat(knight.isSlotOccupied(EquipmentSlot.RIGHT_HAND)).isFalse();

        // Знімаємо все
        knight.unequipAll();
        assertThat(knight.isEquipped()).isFalse();
        assertThat(knight.calculatePrice()).isEqualTo(0.0);
    }
}