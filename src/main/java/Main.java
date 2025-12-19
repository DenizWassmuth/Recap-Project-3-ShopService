import java.time.LocalDateTime;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Order newOrder1 = new Order("1", List.of(new Product("1", "Apfel")), OrderStatus.PROCESSING, LocalDateTime.now());
        System.out.println(newOrder1.timeStamp());

        Order newOrder2 = new Order("1", List.of(new Product("1", "Apfel")), OrderStatus.PROCESSING);
        System.out.println(newOrder2.timeStamp());

        if (newOrder1.equals(newOrder2)) {
            System.out.println("Orders are equal");
        }
    }
}
