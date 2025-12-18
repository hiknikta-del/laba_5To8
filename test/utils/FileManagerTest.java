package utils;

import arsenal.Arsenal;
import amunition.Amunition;
import amunition.items.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * Тести для класу FileManager
 * Перевіряємо завантаження, збереження та обробку файлів
 */
class FileManagerTest {

    @TempDir
    Path tempDir;

    private Arsenal arsenal;
    private String testFilePath;

    @BeforeEach
    void setUp() {
        arsenal = new Arsenal();
        testFilePath = tempDir.resolve("test_ammo.txt").toString();
    }

    @Test
    @DisplayName("Завантаження з неіснуючого файлу повертає false")
    void testLoadFromNonExistentFile() {
        boolean result = FileManager.loadAmunitionFromFile(arsenal, "nonexistent.txt");

        assertThat(result).isFalse();
        assertThat(arsenal.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Завантаження меча з файлу")
    void testLoadSwordFromFile() throws IOException {
        createTestFile(testFilePath, "SWORD|Excalibur|4.0|2000.0|85.0|100.0");

        boolean result = FileManager.loadAmunitionFromFile(arsenal, testFilePath);

        assertThat(result).isTrue();
        assertThat(arsenal.getSize()).isEqualTo(1);

        Amunition item = arsenal.getAmunitionList().get(0);
        assertThat(item).isInstanceOf(Sword.class);
        assertThat(item.getName()).isEqualTo("Excalibur");
        assertThat(item.getWeight()).isEqualTo(4.0);
        assertThat(item.getPrice()).isEqualTo(2000.0);
    }

    @Test
    @DisplayName("Завантаження списа з файлу")
    void testLoadSpearFromFile() throws IOException {
        createTestFile(testFilePath, "SPEAR|Lance|5.5|450.0|60.0|220.0");

        FileManager.loadAmunitionFromFile(arsenal, testFilePath);

        assertThat(arsenal.getSize()).isEqualTo(1);
        Amunition item = arsenal.getAmunitionList().get(0);
        assertThat(item).isInstanceOf(Spear.class);

        Spear spear = (Spear) item;
        assertThat(spear.getSpearLength()).isEqualTo(220.0);
    }

    @Test
    @DisplayName("Завантаження шолома з файлу")
    void testLoadHelmetFromFile() throws IOException {
        createTestFile(testFilePath, "HELMET|Steel Helmet|4.2|300.0|35.0|85.0|true");

        FileManager.loadAmunitionFromFile(arsenal, testFilePath);

        assertThat(arsenal.getSize()).isEqualTo(1);
        Amunition item = arsenal.getAmunitionList().get(0);
        assertThat(item).isInstanceOf(Helmet.class);

        Helmet helmet = (Helmet) item;
        assertThat(helmet.hasVisor()).isTrue();
    }

    @Test
    @DisplayName("Завантаження кіраси з файлу")
    void testLoadChestplateFromFile() throws IOException {
        createTestFile(testFilePath, "CHESTPLATE|Steel Plate|14.5|1000.0|70.0|95.0|true");

        FileManager.loadAmunitionFromFile(arsenal, testFilePath);

        assertThat(arsenal.getSize()).isEqualTo(1);
        Amunition item = arsenal.getAmunitionList().get(0);
        assertThat(item).isInstanceOf(Chestplate.class);

        Chestplate chest = (Chestplate) item;
        assertThat(chest.isOnepiece()).isTrue();
    }

    @Test
    @DisplayName("Завантаження щита з файлу")
    void testLoadShieldFromFile() throws IOException {
        createTestFile(testFilePath, "SHIELD|Iron Shield|8.0|250.0|35.0|50.0|Kite");

        FileManager.loadAmunitionFromFile(arsenal, testFilePath);

        assertThat(arsenal.getSize()).isEqualTo(1);
        Amunition item = arsenal.getAmunitionList().get(0);
        assertThat(item).isInstanceOf(Shield.class);

        Shield shield = (Shield) item;
        assertThat(shield.getShape()).isEqualTo("Kite");
    }

    @Test
    @DisplayName("Завантаження кільця з файлу")
    void testLoadRingFromFile() throws IOException {
        createTestFile(testFilePath, "RING|Ring of Power|0.1|500.0|+10 STR|None|18.0");

        FileManager.loadAmunitionFromFile(arsenal, testFilePath);

        assertThat(arsenal.getSize()).isEqualTo(1);
        Amunition item = arsenal.getAmunitionList().get(0);
        assertThat(item).isInstanceOf(Ring.class);

        Ring ring = (Ring) item;
        assertThat(ring.getBuff()).isEqualTo("+10 STR");
    }

    @Test
    @DisplayName("Завантаження корони з файлу")
    void testLoadCrownFromFile() throws IOException {
        createTestFile(testFilePath, "CROWN|King's Crown|2.0|10000.0|+15 CHA|-5 AGI|King");

        FileManager.loadAmunitionFromFile(arsenal, testFilePath);

        assertThat(arsenal.getSize()).isEqualTo(1);
        Amunition item = arsenal.getAmunitionList().get(0);
        assertThat(item).isInstanceOf(Crown.class);

        Crown crown = (Crown) item;
        assertThat(crown.getRoyaltyType()).isEqualTo("King");
    }

    @Test
    @DisplayName("Завантаження кількох предметів")
    void testLoadMultipleItems() throws IOException {
        String content = String.join("\n",
                "SWORD|Sword1|3.0|100.0|50.0|80.0",
                "HELMET|Helmet1|2.0|50.0|30.0|80.0|false",
                "SHIELD|Shield1|5.0|80.0|40.0|60.0|Round"
        );
        createTestFile(testFilePath, content);

        FileManager.loadAmunitionFromFile(arsenal, testFilePath);

        assertThat(arsenal.getSize()).isEqualTo(3);
    }

    @Test
    @DisplayName("Ігнорування порожніх рядків")
    void testIgnoreEmptyLines() throws IOException {
        String content = String.join("\n",
                "SWORD|Sword1|3.0|100.0|50.0|80.0",
                "",
                "HELMET|Helmet1|2.0|50.0|30.0|80.0|false"
        );
        createTestFile(testFilePath, content);

        FileManager.loadAmunitionFromFile(arsenal, testFilePath);

        assertThat(arsenal.getSize()).isEqualTo(2);
    }

    @Test
    @DisplayName("Ігнорування коментарів")
    void testIgnoreComments() throws IOException {
        String content = String.join("\n",
                "# This is a comment",
                "SWORD|Sword1|3.0|100.0|50.0|80.0",
                "# Another comment",
                "HELMET|Helmet1|2.0|50.0|30.0|80.0|false"
        );
        createTestFile(testFilePath, content);

        FileManager.loadAmunitionFromFile(arsenal, testFilePath);

        assertThat(arsenal.getSize()).isEqualTo(2);
    }

    @Test
    @DisplayName("Обробка невалідного формату")
    void testInvalidFormat() throws IOException {
        createTestFile(testFilePath, "INVALID|DATA");

        boolean result = FileManager.loadAmunitionFromFile(arsenal, testFilePath);

        // Завантаження успішне, але предмет не додано через помилку парсингу
        assertThat(result).isTrue();
        assertThat(arsenal.getSize()).isZero();
    }

    @Test
    @DisplayName("Обробка невідомого типу")
    void testUnknownType() throws IOException {
        createTestFile(testFilePath, "UNKNOWN|Test|1.0|100.0|param1|param2");

        FileManager.loadAmunitionFromFile(arsenal, testFilePath);

        assertThat(arsenal.getSize()).isZero();
    }

    @Test
    @DisplayName("Збереження арсеналу у файл")
    void testSaveArsenalToFile() throws IOException {
        arsenal.addAmunition(new Sword("Sword", 3.0, 100.0, 50.0, 80.0));
        arsenal.addAmunition(new Helmet("Helmet", 2.0, 50.0, 30.0, 80.0, true));

        String saveFilePath = tempDir.resolve("save.txt").toString();
        FileManager.saveArsenalToFile(arsenal, saveFilePath);

        File savedFile = new File(saveFilePath);
        assertThat(savedFile).exists();

        // Перевіряємо що можна завантажити назад
        Arsenal loadedArsenal = new Arsenal();
        FileManager.loadAmunitionFromFile(loadedArsenal, saveFilePath);

        assertThat(loadedArsenal.getSize()).isEqualTo(2);
    }

    @Test
    @DisplayName("Створення файлу за замовчуванням")
    void testCreateDefaultDataFile() {
        String defaultFile = tempDir.resolve("default.txt").toString();

        FileManager.createDefaultDataFile(defaultFile);

        File file = new File(defaultFile);
        assertThat(file).exists();

        // Перевіряємо що можна завантажити
        Arsenal testArsenal = new Arsenal();
        FileManager.loadAmunitionFromFile(testArsenal, defaultFile);

        assertThat(testArsenal.getSize()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Не створює файл якщо вже існує")
    void testDoNotOverwriteExistingFile() throws IOException {
        String existingFile = tempDir.resolve("existing.txt").toString();
        createTestFile(existingFile, "ORIGINAL CONTENT");

        FileManager.createDefaultDataFile(existingFile);

        // Файл не має бути перезаписаний
        Arsenal testArsenal = new Arsenal();
        FileManager.loadAmunitionFromFile(testArsenal, existingFile);

        assertThat(testArsenal.getSize()).isZero(); // Бо це не валідний формат
    }

    @Test
    @DisplayName("getDefaultDataFile повертає правильний шлях")
    void testGetDefaultDataFile() {
        String path = FileManager.getDefaultDataFile();

        assertThat(path).isEqualTo("data/ammunition.txt");
    }

    @Test
    @DisplayName("Збереження та завантаження повного циклу")
    void testFullSaveLoadCycle() {
        // Додаємо різні типи амуніції
        arsenal.addAmunition(new Sword("Excalibur", 4.0, 2000.0, 85.0, 100.0));
        arsenal.addAmunition(new Spear("Lance", 5.5, 450.0, 60.0, 220.0));
        arsenal.addAmunition(new Helmet("Steel Helm", 4.2, 300.0, 35.0, 85.0, true));
        arsenal.addAmunition(new Chestplate("Plate", 14.5, 1000.0, 70.0, 95.0, true));
        arsenal.addAmunition(new Shield("Tower", 15.0, 400.0, 45.0, 70.0, "Rectangle"));
        arsenal.addAmunition(new Ring("Power", 0.1, 500.0, "+10 STR", "None", 18.0));
        arsenal.addAmunition(new Crown("Royal", 2.0, 10000.0, "+15 CHA", "-5 AGI", "King"));

        String saveFile = tempDir.resolve("full_cycle.txt").toString();
        FileManager.saveArsenalToFile(arsenal, saveFile);

        // Завантажуємо в новий арсенал
        Arsenal loadedArsenal = new Arsenal();
        boolean loaded = FileManager.loadAmunitionFromFile(loadedArsenal, saveFile);

        assertThat(loaded).isTrue();
        assertThat(loadedArsenal.getSize()).isEqualTo(7);

        // Перевіряємо типи
        List<Amunition> items = loadedArsenal.getAmunitionList();
        assertThat(items).hasAtLeastOneElementOfType(Sword.class);
        assertThat(items).hasAtLeastOneElementOfType(Spear.class);
        assertThat(items).hasAtLeastOneElementOfType(Helmet.class);
        assertThat(items).hasAtLeastOneElementOfType(Chestplate.class);
        assertThat(items).hasAtLeastOneElementOfType(Shield.class);
        assertThat(items).hasAtLeastOneElementOfType(Ring.class);
        assertThat(items).hasAtLeastOneElementOfType(Crown.class);
    }

    // ========== Helper Methods ==========

    private void createTestFile(String path, String content) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path))) {
            writer.println(content);
        }
    }
}