
import java.util.List;

public class Main {

    public static void main(String[] args) {

//        Order newOrder1 = new Order("1", List.of(new Product("1", "Apfel")), OrderStatus.PROCESSING, Instant.now());
//        System.out.println(newOrder1.timeStamp());
//
//        Order newOrder2 = new Order("1", List.of(new Product("1", "Apfel")), OrderStatus.PROCESSING);
//        System.out.println(newOrder2.timeStamp());
//
//        if (newOrder1.equals(newOrder2)) {
//            System.out.println("Orders are equal");
//        }

        ProductRepo productRepo = new ProductRepo();
        productRepo.addProduct(new Product("2", "Birne"));
        productRepo.addProduct(new Product("3", "Banane"));
        productRepo.addProduct(new Product("4", "Orange"));
        productRepo.addProduct(new Product("5", "Tomate"));

        OrderMapRepo orderMapRepo = new OrderMapRepo();

        ShopService shopService = ShopService.builder()
                .productRepo(productRepo)
                .orderRepo(orderMapRepo)
                .build();

        List<String> firstOrder = List.of("1", "2", "3", "4");
        List<String> secondOrder = List.of("2", "4", "3", "5");
        List<String> thirdOrder = List.of("5", "1", "3", "2");
        shopService.addOrder(firstOrder);
        shopService.addOrder(secondOrder);
        shopService.addOrder(thirdOrder);


    }
}
