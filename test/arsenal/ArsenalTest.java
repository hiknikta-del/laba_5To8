package arsenal;

import amunition.Amunition;
import amunition.items.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * Тести для класу Arsenal
 * Перевіряємо додавання, видалення, сортування та фільтрацію амуніції
 */
class ArsenalTest {

    private Arsenal arsenal;
    private Sword cheapSword;
    private Sword expensiveSword;
    private Helmet lightHelmet;
    private Shield heavyShield;

    @BeforeEach
    void setUp() {
        arsenal = new Arsenal();

        // Створюємо тестові предмети з різними характеристиками
        cheapSword = new Sword("Cheap Sword", 2.0, 50.0, 30.0, 60.0);
        expensiveSword = new Sword("Expensive Sword", 5.0, 500.0, 80.0, 100.0);
        lightHelmet = new Helmet("Light Helmet", 1.0, 100.0, 20.0, 70.0, false);
        heavyShield = new Shield("Heavy Shield", 10.0, 200.0, 50.0, 80.0, "Tower");
    }

    @Test
    @DisplayName("Новий арсенал порожній")
    void testNewArsenalIsEmpty() {
        assertThat(arsenal.isEmpty()).isTrue();
        assertThat(arsenal.getSize()).isZero();
    }

    @Test
    @DisplayName("Додавання амуніції")
    void testAddAmunition() {
        arsenal.addAmunition(cheapSword);

        assertThat(arsenal.isEmpty()).isFalse();
        assertThat(arsenal.getSize()).isEqualTo(1);
    }

    @Test
    @DisplayName("Додавання null не змінює арсенал")
    void testAddNullAmunition() {
        arsenal.addAmunition(null);

        assertThat(arsenal.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Додавання кількох предметів")
    void testAddMultipleItems() {
        arsenal.addAmunition(cheapSword);
        arsenal.addAmunition(expensiveSword);
        arsenal.addAmunition(lightHelmet);

        assertThat(arsenal.getSize()).isEqualTo(3);
    }

    @Test
    @DisplayName("Видалення амуніції")
    void testRemoveAmunition() {
        arsenal.addAmunition(cheapSword);
        arsenal.addAmunition(expensiveSword);

        boolean removed = arsenal.removeAmunition(cheapSword);

        assertThat(removed).isTrue();
        assertThat(arsenal.getSize()).isEqualTo(1);
    }

    @Test
    @DisplayName("Видалення неіснуючого предмету")
    void testRemoveNonExistentAmunition() {
        arsenal.addAmunition(cheapSword);

        boolean removed = arsenal.removeAmunition(expensiveSword);

        assertThat(removed).isFalse();
        assertThat(arsenal.getSize()).isEqualTo(1);
    }

    @Test
    @DisplayName("Видалення null повертає false")
    void testRemoveNull() {
        arsenal.addAmunition(cheapSword);

        boolean removed = arsenal.removeAmunition(null);

        assertThat(removed).isFalse();
    }

    @Test
    @DisplayName("Сортування за вагою")
    void testSortByWeight() {
        arsenal.addAmunition(heavyShield);  // 10.0 kg
        arsenal.addAmunition(expensiveSword); // 5.0 kg
        arsenal.addAmunition(lightHelmet);   // 1.0 kg
        arsenal.addAmunition(cheapSword);    // 2.0 kg

        arsenal.sortByWeight();

        List<Amunition> items = arsenal.getAmunitionList();
        assertThat(items.get(0).getWeight()).isEqualTo(1.0);  // Light Helmet
        assertThat(items.get(1).getWeight()).isEqualTo(2.0);  // Cheap Sword
        assertThat(items.get(2).getWeight()).isEqualTo(5.0);  // Expensive Sword
        assertThat(items.get(3).getWeight()).isEqualTo(10.0); // Heavy Shield
    }

    @Test
    @DisplayName("Сортування порожнього арсеналу не падає")
    void testSortEmptyArsenal() {
        assertThatCode(() -> arsenal.sortByWeight()).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Сортування за ціною")
    void testSortByPrice() {
        arsenal.addAmunition(expensiveSword); // 500
        arsenal.addAmunition(heavyShield);    // 200
        arsenal.addAmunition(lightHelmet);    // 100
        arsenal.addAmunition(cheapSword);     // 50

        arsenal.sortByPrice();

        List<Amunition> items = arsenal.getAmunitionList();
        assertThat(items.get(0).getPrice()).isEqualTo(50.0);   // Cheap Sword
        assertThat(items.get(1).getPrice()).isEqualTo(100.0);  // Light Helmet
        assertThat(items.get(2).getPrice()).isEqualTo(200.0);  // Heavy Shield
        assertThat(items.get(3).getPrice()).isEqualTo(500.0);  // Expensive Sword
    }

    @Test
    @DisplayName("Фільтрація за діапазоном цін")
    void testFilterByPriceRange() {
        arsenal.addAmunition(cheapSword);     // 50
        arsenal.addAmunition(lightHelmet);    // 100
        arsenal.addAmunition(heavyShield);    // 200
        arsenal.addAmunition(expensiveSword); // 500

        List<Amunition> filtered = arsenal.filterByPriceRange(80, 250);

        assertThat(filtered).hasSize(2);
        assertThat(filtered).extracting(Amunition::getPrice)
                .containsExactlyInAnyOrder(100.0, 200.0);
    }

    @Test
    @DisplayName("Фільтрація порожнього арсеналу")
    void testFilterEmptyArsenal() {
        List<Amunition> filtered = arsenal.filterByPriceRange(0, 1000);

        assertThat(filtered).isEmpty();
    }

    @Test
    @DisplayName("Фільтрація без результатів")
    void testFilterNoResults() {
        arsenal.addAmunition(cheapSword);

        List<Amunition> filtered = arsenal.filterByPriceRange(1000, 2000);

        assertThat(filtered).isEmpty();
    }

    @Test
    @DisplayName("Отримання амуніції за індексом")
    void testGetAmunitionByIndex() {
        arsenal.addAmunition(cheapSword);
        arsenal.addAmunition(expensiveSword);

        Amunition first = arsenal.getAmunitionByIndex(1);
        Amunition second = arsenal.getAmunitionByIndex(2);

        assertThat(first).isEqualTo(cheapSword);
        assertThat(second).isEqualTo(expensiveSword);
    }

    @Test
    @DisplayName("Отримання за невалідним індексом")
    void testGetAmunitionByInvalidIndex() {
        arsenal.addAmunition(cheapSword);

        Amunition result1 = arsenal.getAmunitionByIndex(0);
        Amunition result2 = arsenal.getAmunitionByIndex(10);

        assertThat(result1).isNull();
        assertThat(result2).isNull();
    }

    @Test
    @DisplayName("Очищення арсеналу")
    void testClear() {
        arsenal.addAmunition(cheapSword);
        arsenal.addAmunition(expensiveSword);

        arsenal.clear();

        assertThat(arsenal.isEmpty()).isTrue();
        assertThat(arsenal.getSize()).isZero();
    }

    @Test
    @DisplayName("Отримання списку амуніції")
    void testGetAmunitionList() {
        arsenal.addAmunition(cheapSword);
        arsenal.addAmunition(expensiveSword);

        List<Amunition> list = arsenal.getAmunitionList();

        assertThat(list).hasSize(2);
        assertThat(list).containsExactly(cheapSword, expensiveSword);
    }

    @Test
    @DisplayName("Список амуніції є копією (не впливає на оригінал)")
    void testGetAmunitionListIsCopy() {
        arsenal.addAmunition(cheapSword);

        List<Amunition> list = arsenal.getAmunitionList();
        list.clear();

        assertThat(arsenal.getSize()).isEqualTo(1); // Оригінал не змінився
    }

    @Test
    @DisplayName("showAmunition не падає для порожнього арсеналу")
    void testShowAmunitionEmpty() {
        assertThatCode(() -> arsenal.showAmunition()).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("showAmunition не падає з предметами")
    void testShowAmunitionWithItems() {
        arsenal.addAmunition(cheapSword);
        arsenal.addAmunition(expensiveSword);

        assertThatCode(() -> arsenal.showAmunition()).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("showAmunitionInPriceRange для порожнього результату")
    void testShowAmunitionInPriceRangeEmpty() {
        arsenal.addAmunition(cheapSword);

        assertThatCode(() ->
                arsenal.showAmunitionInPriceRange(1000, 2000)
        ).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("showAmunitionInPriceRange з результатами")
    void testShowAmunitionInPriceRangeWithResults() {
        arsenal.addAmunition(cheapSword);
        arsenal.addAmunition(lightHelmet);

        assertThatCode(() ->
                arsenal.showAmunitionInPriceRange(50, 150)
        ).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Перевірка isEmpty")
    void testIsEmpty() {
        assertThat(arsenal.isEmpty()).isTrue();

        arsenal.addAmunition(cheapSword);
        assertThat(arsenal.isEmpty()).isFalse();

        arsenal.clear();
        assertThat(arsenal.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Повний цикл роботи з арсеналом")
    void testFullWorkflow() {
        // Додаємо предмети
        arsenal.addAmunition(cheapSword);
        arsenal.addAmunition(expensiveSword);
        arsenal.addAmunition(lightHelmet);
        arsenal.addAmunition(heavyShield);

        assertThat(arsenal.getSize()).isEqualTo(4);

        // Сортуємо
        arsenal.sortByPrice();
        assertThat(arsenal.getAmunitionList().get(0)).isEqualTo(cheapSword);

        // Фільтруємо
        List<Amunition> expensive = arsenal.filterByPriceRange(200, 600);
        assertThat(expensive).hasSize(2);

        // Видаляємо
        arsenal.removeAmunition(cheapSword);
        assertThat(arsenal.getSize()).isEqualTo(3);

        // Очищуємо
        arsenal.clear();
        assertThat(arsenal.isEmpty()).isTrue();
    }
}