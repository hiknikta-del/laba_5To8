package arsenal;

import amunition.Amunition;

import java.util.ArrayList;
import java.util.List;

public class Arsenal {
    // Список всієї доступної амуніції
    private List<Amunition> amunitionList;

    public Arsenal() {
        this.amunitionList = new ArrayList<>();
    }

    // Заглушка для показу всього списку
    public void showAmunition() {
        System.out.println("[Arsenal] showAmunition() called - logic to be implemented in Lab 3");
    }

    // Заглушка для сортування за вагою
    public void sortByWeight() {
        System.out.println("[Arsenal] sortByWeight() called - logic to be implemented in Lab 3");
    }

    // Заглушка для сортування за ціною
    public void sortByPrice() {
        System.out.println("[Arsenal] sortByPrice() called - logic to be implemented in Lab 3");
    }

    // Метод для додавання (знадобиться для тестів або ініціалізації)
    public void addAmunition(Amunition item) {
        amunitionList.add(item);
    }

    public List<Amunition> getAmunitionList() {
        return amunitionList;
    }
}