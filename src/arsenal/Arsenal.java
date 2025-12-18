package arsenal;

import amunition.Amunition;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Клас Arsenal представляє арсенал амуніції.
 * Містить список всієї доступної амуніції та методи для роботи з нею.
 */
public class Arsenal {
    private List<Amunition> amunitionList;

    public Arsenal() {
        this.amunitionList = new ArrayList<>();
    }

    /**
     * Додає амуніцію до арсеналу
     * @param item амуніція для додавання
     */
    public void addAmunition(Amunition item) {
        if (item == null) {
            System.out.println("Cannot add null ammunition.");
            return;
        }
        amunitionList.add(item);
        System.out.println("Added: " + item.getName());
    }

    /**
     * Видаляє амуніцію з арсеналу
     * @param item амуніція для видалення
     * @return true якщо видалення успішне
     */
    public boolean removeAmunition(Amunition item) {
        if (item == null) {
            return false;
        }
        return amunitionList.remove(item);
    }

    /**
     * Показує всю амуніцію в арсеналі
     */
    public void showAmunition() {
        if (amunitionList.isEmpty()) {
            System.out.println("Arsenal is empty.");
            return;
        }

        System.out.println("\n=== ARSENAL AMMUNITION ===");
        System.out.println(String.format("%-4s %-30s %-12s %-12s %-15s",
                "No.", "Name", "Weight (kg)", "Price (gold)", "Type"));
        System.out.println("-".repeat(80));

        for (int i = 0; i < amunitionList.size(); i++) {
            Amunition item = amunitionList.get(i);
            System.out.println(String.format("%-4d %s", (i + 1), item.toString()));
        }
        System.out.println("Total items: " + amunitionList.size());
    }

    /**
     * Сортує амуніцію за вагою (від найлегшої до найважчої)
     */
    public void sortByWeight() {
        if (amunitionList.isEmpty()) {
            System.out.println("Arsenal is empty. Nothing to sort.");
            return;
        }

        amunitionList.sort(Comparator.comparingDouble(Amunition::getWeight));
        System.out.println("Ammunition sorted by weight (ascending).");
    }

    /**
     * Сортує амуніцію за ціною (від найдешевшої до найдорожчої)
     */
    public void sortByPrice() {
        if (amunitionList.isEmpty()) {
            System.out.println("Arsenal is empty. Nothing to sort.");
            return;
        }

        amunitionList.sort(Comparator.comparingDouble(Amunition::getPrice));
        System.out.println("Ammunition sorted by price (ascending).");
    }

    /**
     * Знаходить амуніцію за індексом
     * @param index індекс (починаючи з 1 для зручності користувача)
     * @return амуніція або null
     */
    public Amunition getAmunitionByIndex(int index) {
        if (index < 1 || index > amunitionList.size()) {
            return null;
        }
        return amunitionList.get(index - 1);
    }

    /**
     * Фільтрує амуніцію за діапазоном цін
     * @param minPrice мінімальна ціна
     * @param maxPrice максимальна ціна
     * @return список відфільтрованої амуніції
     */
    public List<Amunition> filterByPriceRange(double minPrice, double maxPrice) {
        List<Amunition> filtered = new ArrayList<>();
        for (Amunition item : amunitionList) {
            if (item.getPrice() >= minPrice && item.getPrice() <= maxPrice) {
                filtered.add(item);
            }
        }
        return filtered;
    }

    /**
     * Показує амуніцію в заданому діапазоні цін
     * @param minPrice мінімальна ціна
     * @param maxPrice максимальна ціна
     */
    public void showAmunitionInPriceRange(double minPrice, double maxPrice) {
        List<Amunition> filtered = filterByPriceRange(minPrice, maxPrice);

        if (filtered.isEmpty()) {
            System.out.println("No ammunition found in price range: " +
                    minPrice + " - " + maxPrice + " gold.");
            return;
        }

        System.out.println("\n=== AMMUNITION IN PRICE RANGE: " +
                minPrice + " - " + maxPrice + " gold ===");
        for (int i = 0; i < filtered.size(); i++) {
            System.out.println((i + 1) + ". " + filtered.get(i).toString());
        }
    }

    /**
     * Повертає список всієї амуніції
     * @return список амуніції
     */
    public List<Amunition> getAmunitionList() {
        return new ArrayList<>(amunitionList);
    }

    /**
     * Повертає кількість амуніції в арсеналі
     * @return кількість предметів
     */
    public int getSize() {
        return amunitionList.size();
    }

    /**
     * Очищує арсенал
     */
    public void clear() {
        amunitionList.clear();
        System.out.println("Arsenal cleared.");
    }

    /**
     * Перевіряє чи арсенал порожній
     * @return true якщо порожній
     */
    public boolean isEmpty() {
        return amunitionList.isEmpty();
    }
}