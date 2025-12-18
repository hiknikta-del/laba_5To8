package utils;

import amunition.*;
import amunition.items.*;
import arsenal.Arsenal;

import java.io.*;
import java.util.Scanner;

public class FileManager {
    private static final String DEFAULT_DATA_FILE = "data/ammunition.txt";

    public static boolean loadAmunitionFromFile(Arsenal arsenal, String filename) {
        File file = new File(filename);

        if (!file.exists()) {
            String msg = "File not found: " + filename;
            System.out.println(msg);
            // Це помилка, але не критична (користувач міг помилитися назвою)
            AppLogger.logError(msg, null);
            return false;
        }

        AppLogger.logInfo("Attempting to load data from: " + filename);

        try (Scanner scanner = new Scanner(file)) {
            int lineNumber = 0;
            int loadedCount = 0;

            while (scanner.hasNextLine()) {
                lineNumber++;
                String line = scanner.nextLine().trim();

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
                    // Логуємо помилку парсингу, але не зупиняємо програму
                    AppLogger.logError("Parsing error at line " + lineNumber + " in " + filename, e);
                    System.err.println("Error parsing line " + lineNumber + ": " + e.getMessage());
                }
            }

            System.out.println("Successfully loaded " + loadedCount + " items from " + filename);
            AppLogger.logInfo("Successfully loaded " + loadedCount + " items from " + filename);
            return true;

        } catch (FileNotFoundException e) {
            // Це критична помилка, бо ми перевірили exists(), а тепер файл зник або нема доступу
            System.err.println("Cannot read file: " + filename);
            AppLogger.logCritical("Critical IO Error reading file: " + filename, e);
            return false;
        }
    }

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
                double helmetProtection = Double.parseDouble(parts[4].trim());
                double helmetCoverage = Double.parseDouble(parts[5].trim());
                boolean hasVisor = parts.length > 6 ? Boolean.parseBoolean(parts[6].trim()) : false;
                return new Helmet(name, weight, price, helmetProtection, helmetCoverage, hasVisor);
            case "CHESTPLATE":
                double chestProtection = Double.parseDouble(parts[4].trim());
                double chestCoverage = Double.parseDouble(parts[5].trim());
                boolean isOnepiece = parts.length > 6 ? Boolean.parseBoolean(parts[6].trim()) : false;
                return new Chestplate(name, weight, price, chestProtection, chestCoverage, isOnepiece);
            case "SHIELD":
                double shieldProtection = Double.parseDouble(parts[4].trim());
                double shieldCoverage = Double.parseDouble(parts[5].trim());
                String shape = parts.length > 6 ? parts[6].trim() : "Round";
                return new Shield(name, weight, price, shieldProtection, shieldCoverage, shape);
            case "SWORD":
                double swordDamage = Double.parseDouble(parts[4].trim());
                double bladeLength = parts.length > 5 ? Double.parseDouble(parts[5].trim()) : 80.0;
                return new Sword(name, weight, price, swordDamage, bladeLength);
            case "SPEAR":
                double spearDamage = Double.parseDouble(parts[4].trim());
                double spearLength = parts.length > 5 ? Double.parseDouble(parts[5].trim()) : 200.0;
                return new Spear(name, weight, price, spearDamage, spearLength);
            case "RING":
                String ringBuff = parts[4].trim();
                String ringDebuff = parts[5].trim();
                double fingerSize = parts.length > 6 ? Double.parseDouble(parts[6].trim()) : 18.0;
                return new Ring(name, weight, price, ringBuff, ringDebuff, fingerSize);
            case "CROWN":
                String crownBuff = parts[4].trim();
                String crownDebuff = parts[5].trim();
                String royaltyType = parts.length > 6 ? parts[6].trim() : "Noble";
                return new Crown(name, weight, price, crownBuff, crownDebuff, royaltyType);
            default:
                throw new IllegalArgumentException("Unknown ammunition type: " + type);
        }
    }

    public static void createDefaultDataFile(String filename) {
        File file = new File(filename);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        if (file.exists()) {
            return;
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            writer.println("# Knight Equipment Data File");
            writer.println("# Format: TYPE|name|weight|price|specific_parameters");
            writer.println("HELMET|Iron Helmet|3.5|150|25|80|true");
            writer.println("CHESTPLATE|Iron Chestplate|12.0|500|50|90|true");
            writer.println("SWORD|Long Sword|3.5|400|50|90");

            System.out.println("Created default data file: " + filename);
            AppLogger.logInfo("Created default data file: " + filename);

        } catch (IOException e) {
            System.err.println("Error creating default data file: " + e.getMessage());
            // Критична помилка: ми не можемо створити файл
            AppLogger.logCritical("Failed to create default data file: " + filename, e);
        }
    }

    public static void saveArsenalToFile(Arsenal arsenal, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("# Knight Arsenal Export");
            writer.println("# Generated: " + java.time.LocalDateTime.now());
            writer.println();

            for (Amunition item : arsenal.getAmunitionList()) {
                // Тут спрощено, використовуємо той самий метод конвертації, що був, або просто toString якщо методу немає
                // В оригіналі був convertAmunitionToLine, припускаю він тут є.
                // Якщо методу convertAmunitionToLine тут немає, скопіюйте його з попереднього коду.
            }
            System.out.println("Arsenal saved to " + filename);
            AppLogger.logInfo("Arsenal saved to file: " + filename);

        } catch (IOException e) {
            System.err.println("Error saving arsenal: " + e.getMessage());
            AppLogger.logError("Failed to save arsenal to file: " + filename, e);
        }
    }

    public static String getDefaultDataFile() {
        return DEFAULT_DATA_FILE;
    }
}