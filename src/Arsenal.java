import amunition.Amunition;
import java.util.ArrayList;
import java.util.List;

public class Arsenal {
    private List<Amunition> amunition;

    public Arsenal() {
        this.amunition = new ArrayList<>();
    }

    public void loadAmunition() {
        System.out.println("Load ammunition functionality - to be implemented");
    }

    public void showAmunition() {
        System.out.println("Show ammunition functionality - to be implemented");
    }

    public void sortByWeight() {
        System.out.println("Sort by weight functionality - to be implemented");
    }

    public void sortByPrice() {
        System.out.println("Sort by price functionality - to be implemented");
    }

    public List<Amunition> getAmunition() {
        return amunition;
    }
}