package commands;

import arsenal.Arsenal;
import knight.Knight;
import amunition.items.*;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Тести для Command класів
 * Використовуємо Mockito для мокування Scanner
 */
class CommandsTest {

    private Arsenal arsenal;
    private Knight knight;
    private Sword sword;
    private Helmet helmet;
    private AutoCloseable mocks;

    @BeforeEach
    void setUp() {
        mocks = MockitoAnnotations.openMocks(this);
        arsenal = new Arsenal();
        knight = new Knight("Test Knight");

        sword = new Sword("Test Sword", 3.0, 100.0, 50.0, 80.0);
        helmet = new Helmet("Test Helmet", 2.0, 50.0, 30.0, 80.0, true);
    }

    @AfterEach
    void tearDown() throws Exception {
        mocks.close();
    }

    // ========== ShowAllAmunitionCommand ==========

    @Test
    @DisplayName("ShowAllAmunitionCommand показує амуніцію")
    void testShowAllAmunitionCommand() {
        arsenal.addAmunition(sword);
        Command command = new ShowAllAmunitionCommand(arsenal);

        assertThatCode(() -> command.execute()).doesNotThrowAnyException();
        assertThat(command.getDescription()).isEqualTo("Show all Ammunition");
    }

    // ========== SortByPriceCommand ==========

    @Test
    @DisplayName("SortByPriceCommand сортує за ціною")
    void testSortByPriceCommand() {
        arsenal.addAmunition(helmet);  // 50
        arsenal.addAmunition(sword);   // 100

        Command command = new SortByPriceCommand(arsenal);
        command.execute();

        assertThat(arsenal.getAmunitionList().get(0)).isEqualTo(helmet);
        assertThat(command.getDescription()).isEqualTo("Sort by Price");
    }

    // ========== SortByWeightCommand ==========

    @Test
    @DisplayName("SortByWeightCommand сортує за вагою")
    void testSortByWeightCommand() {
        arsenal.addAmunition(sword);   // 3.0
        arsenal.addAmunition(helmet);  // 2.0

        Command command = new SortByWeightCommand(arsenal);
        command.execute();

        assertThat(arsenal.getAmunitionList().get(0)).isEqualTo(helmet);
        assertThat(command.getDescription()).isEqualTo("Sort by Weight");
    }

    // ========== ShowEquippedCommand ==========

    @Test
    @DisplayName("ShowEquippedCommand показує екіпіроване")
    void testShowEquippedCommand() {
        knight.equip(sword);

        Command command = new ShowEquippedCommand(knight);

        assertThatCode(() -> command.execute()).doesNotThrowAnyException();
        assertThat(command.getDescription()).isEqualTo("Show Equipped Items");
    }

    // ========== CalculateTotalCostCommand ==========

    @Test
    @DisplayName("CalculateTotalCostCommand розраховує вартість")
    void testCalculateTotalCostCommand() {
        knight.equip(sword);
        knight.equip(helmet);

        Command command = new CalculateTotalCostCommand(knight);

        assertThatCode(() -> command.execute()).doesNotThrowAnyException();
        assertThat(command.getDescription()).isEqualTo("Calculate Total Cost & Weight");
    }

    @Test
    @DisplayName("CalculateTotalCostCommand для порожнього спорядження")
    void testCalculateTotalCostCommandEmpty() {
        Command command = new CalculateTotalCostCommand(knight);

        assertThatCode(() -> command.execute()).doesNotThrowAnyException();
    }

    // ========== InfoReferenceCommand ==========

    @Test
    @DisplayName("InfoReferenceCommand виконується без помилок")
    void testInfoReferenceCommand() {
        // Мокуємо System.in щоб не чекати на Enter
        InputStream originalIn = System.in;
        try {
            System.setIn(new ByteArrayInputStream("\n".getBytes()));

            Command command = new InfoReferenceCommand();

            assertThatCode(() -> command.execute()).doesNotThrowAnyException();
            assertThat(command.getDescription()).isEqualTo("Info/Reference");
        } finally {
            System.setIn(originalIn);
        }
    }

    // ========== BackToMainMenuCommand ==========

    @Test
    @DisplayName("BackToMainMenuCommand має правильний опис")
    void testBackToMainMenuCommand() {
        Command command = new BackToMainMenuCommand();

        assertThat(command.getDescription()).isEqualTo("Back to Main Menu");
        assertThatCode(() -> command.execute()).doesNotThrowAnyException();
    }

    // ========== ExitCommand ==========

    @Test
    @DisplayName("ExitCommand має правильний опис")
    void testExitCommandDescription() {
        Command command = new ExitCommand();

        assertThat(command.getDescription()).isEqualTo("Exit");
    }

    // ========== InteractiveEquipCommand ==========

    @Test
    @DisplayName("InteractiveEquipCommand - порожній арсенал")
    void testInteractiveEquipCommandEmptyArsenal() {
        Scanner scanner = new Scanner("1\n");
        Command command = new InteractiveEquipCommand(knight, arsenal, scanner);

        assertThatCode(() -> command.execute()).doesNotThrowAnyException();
        assertThat(command.getDescription()).isEqualTo("Equip Item (Interactive)");
    }

    @Test
    @DisplayName("InteractiveEquipCommand - екіпірування предмету")
    void testInteractiveEquipCommandEquipItem() {
        arsenal.addAmunition(sword);
        Scanner scanner = new Scanner("1\n");

        Command command = new InteractiveEquipCommand(knight, arsenal, scanner);
        command.execute();

        assertThat(knight.isEquipped()).isTrue();
    }

    @Test
    @DisplayName("InteractiveEquipCommand - скасування")
    void testInteractiveEquipCommandCancel() {
        arsenal.addAmunition(sword);
        Scanner scanner = new Scanner("0\n");

        Command command = new InteractiveEquipCommand(knight, arsenal, scanner);
        command.execute();

        assertThat(knight.isEquipped()).isFalse();
    }

    @Test
    @DisplayName("InteractiveEquipCommand - невалідний вибір")
    void testInteractiveEquipCommandInvalidChoice() {
        arsenal.addAmunition(sword);
        Scanner scanner = new Scanner("999\n");

        Command command = new InteractiveEquipCommand(knight, arsenal, scanner);

        assertThatCode(() -> command.execute()).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("InteractiveEquipCommand - невалідний інпут")
    void testInteractiveEquipCommandInvalidInput() {
        arsenal.addAmunition(sword);
        Scanner scanner = new Scanner("abc\n");

        Command command = new InteractiveEquipCommand(knight, arsenal, scanner);

        assertThatCode(() -> command.execute()).doesNotThrowAnyException();
    }

    // ========== InteractiveUnequipCommand ==========

    @Test
    @DisplayName("InteractiveUnequipCommand - порожнє спорядження")
    void testInteractiveUnequipCommandEmpty() {
        Scanner scanner = new Scanner("1\n");
        Command command = new InteractiveUnequipCommand(knight, scanner);

        assertThatCode(() -> command.execute()).doesNotThrowAnyException();
        assertThat(command.getDescription()).isEqualTo("Unequip Item (Interactive)");
    }

    @Test
    @DisplayName("InteractiveUnequipCommand - зняття всього")
    void testInteractiveUnequipCommandUnequipAll() {
        knight.equip(sword);
        knight.equip(helmet);
        Scanner scanner = new Scanner("2\n");

        Command command = new InteractiveUnequipCommand(knight, scanner);
        command.execute();

        assertThat(knight.isEquipped()).isFalse();
    }

    @Test
    @DisplayName("InteractiveUnequipCommand - зняття конкретного предмету")
    void testInteractiveUnequipCommandUnequipSpecific() {
        knight.equip(sword);
        Scanner scanner = new Scanner("1\n1\n");

        Command command = new InteractiveUnequipCommand(knight, scanner);
        command.execute();

        assertThat(knight.isEquipped()).isFalse();
    }

    @Test
    @DisplayName("InteractiveUnequipCommand - невалідний вибір")
    void testInteractiveUnequipCommandInvalidChoice() {
        knight.equip(sword);
        Scanner scanner = new Scanner("3\n");

        Command command = new InteractiveUnequipCommand(knight, scanner);

        assertThatCode(() -> command.execute()).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("InteractiveUnequipCommand - невалідний номер предмету")
    void testInteractiveUnequipCommandInvalidItemNumber() {
        knight.equip(sword);
        Scanner scanner = new Scanner("1\n999\n");

        Command command = new InteractiveUnequipCommand(knight, scanner);

        assertThatCode(() -> command.execute()).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("InteractiveUnequipCommand - невалідний інпут")
    void testInteractiveUnequipCommandInvalidInput() {
        knight.equip(sword);
        Scanner scanner = new Scanner("abc\n");

        Command command = new InteractiveUnequipCommand(knight, scanner);

        assertThatCode(() -> command.execute()).doesNotThrowAnyException();
    }

    // ========== SearchByPriceRangeCommand ==========

    @Test
    @DisplayName("SearchByPriceRangeCommand - порожній арсенал")
    void testSearchByPriceRangeCommandEmpty() {
        Scanner scanner = new Scanner("50\n100\n");
        Command command = new SearchByPriceRangeCommand(arsenal, scanner);

        assertThatCode(() -> command.execute()).doesNotThrowAnyException();
        assertThat(command.getDescription()).isEqualTo("Search by Price Range");
    }

    @Test
    @DisplayName("SearchByPriceRangeCommand - пошук у діапазоні")
    void testSearchByPriceRangeCommandSearch() {
        arsenal.addAmunition(sword);   // 100
        arsenal.addAmunition(helmet);  // 50
        Scanner scanner = new Scanner("40\n150\n");

        Command command = new SearchByPriceRangeCommand(arsenal, scanner);

        assertThatCode(() -> command.execute()).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("SearchByPriceRangeCommand - невалідний діапазон")
    void testSearchByPriceRangeCommandInvalidRange() {
        arsenal.addAmunition(sword);
        Scanner scanner = new Scanner("200\n100\n");

        Command command = new SearchByPriceRangeCommand(arsenal, scanner);

        assertThatCode(() -> command.execute()).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("SearchByPriceRangeCommand - негативні ціни")
    void testSearchByPriceRangeCommandNegativePrices() {
        arsenal.addAmunition(sword);
        Scanner scanner = new Scanner("-50\n100\n");

        Command command = new SearchByPriceRangeCommand(arsenal, scanner);

        assertThatCode(() -> command.execute()).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("SearchByPriceRangeCommand - невалідний інпут")
    void testSearchByPriceRangeCommandInvalidInput() {
        arsenal.addAmunition(sword);
        Scanner scanner = new Scanner("abc\n");

        Command command = new SearchByPriceRangeCommand(arsenal, scanner);

        assertThatCode(() -> command.execute()).doesNotThrowAnyException();
    }

    // ========== Deprecated Commands ==========

    @Test
    @DisplayName("EquipKnightCommand (deprecated) має правильний опис")
    void testEquipKnightCommandDeprecated() {
        Command command = new EquipKnightCommand(knight, arsenal);

        assertThat(command.getDescription()).isEqualTo("Equip the Knight");
        assertThatCode(() -> command.execute()).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("UnequipKnightCommand (deprecated) має правильний опис")
    void testUnequipKnightCommandDeprecated() {
        Command command = new UnequipKnightCommand(knight);

        assertThat(command.getDescription()).isEqualTo("Unequip the Knight");
        assertThatCode(() -> command.execute()).doesNotThrowAnyException();
    }
}