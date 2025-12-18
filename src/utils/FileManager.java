package utils;

import amunition.*;
import amunition.items.*;
import arsenal.Arsenal;

import java.io.*;
import java.util.Scanner;

/**
 * Клас для роботи з файлами конфігурації та даних.
 * Завантажує початкові дані амуніції з файлу.
 */
public class FileManager {
    private static final String DEFAULT_DATA_FILE = "data/ammunition.txt";
    private static final String CONFIG_FILE = "config/app.properties";

    /**
     * Завантажує амуніцію з файлу до арсеналу
     * @param arsenal арсенал для завантаження
     * @param filename назва файлу
     * @return true якщо завантаження успішне
     */
    public static boolean loadAmunitionFromFile(Arsenal arsenal, String filename) {
        File file = new File(filename);

        if (!file.exists()) {
            System.out.println("File not found: " + filename);
            return false;
        }

        try (Scanner scanner = new Scanner(file)) {
            int lineNumber = 0;
            int loadedCount = 0;

            while (scanner.hasNextLine()) {
                lineNumber++;
                String line = scanner.nextLine().trim();

                // Пропускаємо порожні рядки та коментарі
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }

                try {
                    Amunition item = parseAmunitionLine(line);
                    if (item != null) {
                        arsenal.addAmunition(item);
                        loadedCount++;
                    }
                } catch (Exception e) {
                    System.err.println("Error parsing line " + lineNumber + ": " + e.getMessage());
                }
            }

            System.out.println("Successfully loaded " + loadedCount + " items from " + filename);
            return true;

        } catch (FileNotFoundException e) {
            System.err.println("Cannot read file: " + filename);
            return false;
        }
    }

    /**
     * Парсить рядок з файлу та створює об'єкт амуніції
     * Формат: TYPE|name|weight|price|specific_params
     */
    private static Amunition parseAmunitionLine(String line) {
        String[] parts = line.split("\\|");

        if (parts.length < 4) {
            throw new IllegalArgumentException("Invalid format: not enough parameters");
        }

        String type = parts[0].trim().toUpperCase();
        String name = parts[1].trim();
        double weight = Double.parseDouble(parts[2].trim());
        double price = Double.parseDouble(parts[3].trim());

        switch (type) {
            case "HELMET":
                if (parts.length < 6) {
                    throw new IllegalArgumentException("Helmet requires 6 parameters");
                }
                double helmetProtection = Double.parseDouble(parts[4].trim());
                double helmetCoverage = Double.parseDouble(parts[5].trim());
                boolean hasVisor = parts.length > 6 ? Boolean.parseBoolean(parts[6].trim()) : false;
                return new Helmet(name, weight, price, helmetProtection, helmetCoverage, hasVisor);

            case "CHESTPLATE":
                if (parts.length < 6) {
                    throw new IllegalArgumentException("Chestplate requires 6 parameters");
                }
                double chestProtection = Double.parseDouble(parts[4].trim());
                double chestCoverage = Double.parseDouble(parts[5].trim());
                boolean isOnepiece = parts.length > 6 ? Boolean.parseBoolean(parts[6].trim()) : false;
                return new Chestplate(name, weight, price, chestProtection, chestCoverage, isOnepiece);

            case "SHIELD":
                if (parts.length < 6) {
                    throw new IllegalArgumentException("Shield requires 6 parameters");
                }
                double shieldProtection = Double.parseDouble(parts[4].trim());
                double shieldCoverage = Double.parseDouble(parts[5].trim());
                String shape = parts.length > 6 ? parts[6].trim() : "Round";
                return new Shield(name, weight, price, shieldProtection, shieldCoverage, shape);

            case "SWORD":
                if (parts.length < 5) {
                    throw new IllegalArgumentException("Sword requires 5 parameters");
                }
                double swordDamage = Double.parseDouble(parts[4].trim());
                double bladeLength = parts.length > 5 ? Double.parseDouble(parts[5].trim()) : 80.0;
                return new Sword(name, weight, price, swordDamage, bladeLength);

            case "SPEAR":
                if (parts.length < 5) {
                    throw new IllegalArgumentException("Spear requires 5 parameters");
                }
                double spearDamage = Double.parseDouble(parts[4].trim());
                double spearLength = parts.length > 5 ? Double.parseDouble(parts[5].trim()) : 200.0;
                return new Spear(name, weight, price, spearDamage, spearLength);

            case "RING":
                if (parts.length < 6) {
                    throw new IllegalArgumentException("Ring requires 6 parameters");
                }
                String ringBuff = parts[4].trim();
                String ringDebuff = parts[5].trim();
                double fingerSize = parts.length > 6 ? Double.parseDouble(parts[6].trim()) : 18.0;
                return new Ring(name, weight, price, ringBuff, ringDebuff, fingerSize);

            case "CROWN":
                if (parts.length < 6) {
                    throw new IllegalArgumentException("Crown requires 6 parameters");
                }
                String crownBuff = parts[4].trim();
                String crownDebuff = parts[5].trim();
                String royaltyType = parts.length > 6 ? parts[6].trim() : "Noble";
                return new Crown(name, weight, price, crownBuff, crownDebuff, royaltyType);

            default:
                throw new IllegalArgumentException("Unknown ammunition type: " + type);
        }
    }

    /**
     * Створює файл з прикладами даних якщо він не існує
     * @param filename назва файлу
     */
    public static void createDefaultDataFile(String filename) {
        File file = new File(filename);

        // Створюємо директорію якщо не існує
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        if (file.exists()) {
            return; // Файл вже існує
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            writer.println("# Knight Equipment Data File");
            writer.println("# Format: TYPE|name|weight|price|specific_parameters");
            writer.println("# Types: HELMET, CHESTPLATE, SHIELD, SWORD, SPEAR, RING, CROWN");
            writer.println();

            // Шоломи
            writer.println("# Helmets: TYPE|name|weight|price|protection|coverage|hasVisor");
            writer.println("HELMET|Iron Helmet|3.5|150|25|80|true");
            writer.println("HELMET|Steel Helmet|4.2|300|35|85|true");
            writer.println("HELMET|Bronze Helmet|3.0|100|20|75|false");
            writer.println();

            // Кіраси
            writer.println("# Chestplates: TYPE|name|weight|price|protection|coverage|isOnepiece");
            writer.println("CHESTPLATE|Iron Chestplate|12.0|500|50|90|true");
            writer.println("CHESTPLATE|Steel Chestplate|14.5|1000|70|95|true");
            writer.println("CHESTPLATE|Leather Armor|6.0|200|25|70|false");
            writer.println();

            // Щити
            writer.println("# Shields: TYPE|name|weight|price|protection|coverage|shape");
            writer.println("SHIELD|Wooden Shield|4.0|80|15|40|Round");
            writer.println("SHIELD|Iron Shield|8.0|250|35|50|Kite");
            writer.println("SHIELD|Tower Shield|15.0|400|45|70|Rectangle");
            writer.println();

            // Мечі
            writer.println("# Swords: TYPE|name|weight|price|damage|bladeLength");
            writer.println("SWORD|Short Sword|2.5|200|35|60");
            writer.println("SWORD|Long Sword|3.5|400|50|90");
            writer.println("SWORD|Excalibur|4.0|2000|85|100");
            writer.println();

            // Списи
            writer.println("# Spears: TYPE|name|weight|price|damage|spearLength");
            writer.println("SPEAR|Simple Spear|4.0|150|40|180");
            writer.println("SPEAR|Pike|6.5|300|55|250");
            writer.println("SPEAR|Lance|5.5|450|60|220");
            writer.println();

            // Кільця
            writer.println("# Rings: TYPE|name|weight|price|buff|debuff|fingerSize");
            writer.println("RING|Ring of Strength|0.1|500|+10 STR|None|18");
            writer.println("RING|Ring of Speed|0.1|600|+15 AGI|-5 STR|17");
            writer.println("RING|Ring of Wisdom|0.1|700|+20 INT|-10 STR|19");
            writer.println();

            // Корони
            writer.println("# Crowns: TYPE|name|weight|price|buff|debuff|royaltyType");
            writer.println("CROWN|Baron's Crown|1.5|3000|+5 CHA|None|Baron");
            writer.println("CROWN|King's Crown|2.0|10000|+15 CHA|-5 AGI|King");
            writer.println("CROWN|Emperor's Crown|2.5|25000|+25 CHA|-10 AGI|Emperor");

            System.out.println("Created default data file: " + filename);

        } catch (IOException e) {
            System.err.println("Error creating default data file: " + e.getMessage());
        }
    }

    /**
     * Зберігає стан арсеналу у файл
     * @param arsenal арсенал для збереження
     * @param filename назва файлу
     */
    public static void saveArsenalToFile(Arsenal arsenal, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("# Knight Arsenal Export");
            writer.println("# Generated: " + java.time.LocalDateTime.now());
            writer.println();

            for (Amunition item : arsenal.getAmunitionList()) {
                writer.println(convertAmunitionToLine(item));
            }

            System.out.println("Arsenal saved to " + filename);

        } catch (IOException e) {
            System.err.println("Error saving arsenal: " + e.getMessage());
        }
    }

    /**
     * Конвертує об'єкт амуніції в рядок для збереження
     */
    private static String convertAmunitionToLine(Amunition item) {
        StringBuilder line = new StringBuilder();

        if (item instanceof Helmet) {
            Helmet h = (Helmet) item;
            line.append("HELMET|").append(h.getName()).append("|")
                    .append(h.getWeight()).append("|").append(h.getPrice()).append("|")
                    .append(h.getProtection()).append("|").append(h.getCoverage()).append("|")
                    .append(h.hasVisor());
        } else if (item instanceof Chestplate) {
            Chestplate c = (Chestplate) item;
            line.append("CHESTPLATE|").append(c.getName()).append("|")
                    .append(c.getWeight()).append("|").append(c.getPrice()).append("|")
                    .append(c.getProtection()).append("|").append(c.getCoverage()).append("|")
                    .append(c.isOnepiece());
        } else if (item instanceof Shield) {
            Shield s = (Shield) item;
            line.append("SHIELD|").append(s.getName()).append("|")
                    .append(s.getWeight()).append("|").append(s.getPrice()).append("|")
                    .append(s.getProtection()).append("|").append(s.getCoverage()).append("|")
                    .append(s.getShape());
        } else if (item instanceof Sword) {
            Sword s = (Sword) item;
            line.append("SWORD|").append(s.getName()).append("|")
                    .append(s.getWeight()).append("|").append(s.getPrice()).append("|")
                    .append(s.getDamage()).append("|").append(s.getBladeLength());
        } else if (item instanceof Spear) {
            Spear s = (Spear) item;
            line.append("SPEAR|").append(s.getName()).append("|")
                    .append(s.getWeight()).append("|").append(s.getPrice()).append("|")
                    .append(s.getDamage()).append("|").append(s.getSpearLength());
        } else if (item instanceof Ring) {
            Ring r = (Ring) item;
            line.append("RING|").append(r.getName()).append("|")
                    .append(r.getWeight()).append("|").append(r.getPrice()).append("|")
                    .append(r.getBuff()).append("|").append(r.getDebuff()).append("|")
                    .append(r.getFingerSize());
        } else if (item instanceof Crown) {
            Crown c = (Crown) item;
            line.append("CROWN|").append(c.getName()).append("|")
                    .append(c.getWeight()).append("|").append(c.getPrice()).append("|")
                    .append(c.getBuff()).append("|").append(c.getDebuff()).append("|")
                    .append(c.getRoyaltyType());
        }

        return line.toString();
    }

    /**
     * Повертає шлях до файлу за замовчуванням
     */
    public static String getDefaultDataFile() {
        return DEFAULT_DATA_FILE;
    }
}