import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class ShopService {

private ProductRepo productRepo;
private OrderRepo orderRepo;

    public ShopService(ProductRepo productRepo, OrderMapRepo orderMapRepo) {
        this.productRepo = productRepo;
        this.orderRepo = orderMapRepo;
    }

    public Order addOrder(List<String> productIds) {
        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {
            Product productToOrder = productRepo.getProductById(productId);
            if (productToOrder == null) {

                throw new ProductDoesNotExistException("Product does not exist");
                //System.out.println("Product mit der Id: " + productId + " konnte nicht bestellt werden!");
                //return null;
            }
            products.add(productToOrder);
        }

        Order newOrder = new Order(UUID.randomUUID().toString(), products, OrderStatus.PROCESSING, LocalDateTime.now());

        return orderRepo.addOrder(newOrder);
    }

    public List<Order> getOrders() {
        return orderRepo.getOrders();
    }

    public List<Order> getOrdersByOrderStatus(OrderStatus orderStatus) {
        return orderRepo.getOrders().stream().filter(order -> order.orderStatus() == orderStatus).toList();
    }

    public void addOrderDirectlyToOrderRepo(Order order)
    {
        orderRepo.addOrder(order);
    }

    public Order updateOrder(String orderId, OrderStatus orderStatus){

        Optional<Order> optionalOrder = orderRepo.getOrderById(orderId);
        if (optionalOrder.isPresent()) {
            Order orderToUpdate = optionalOrder.get();
            orderRepo.removeOrder(orderId);
            return orderRepo.addOrder(orderToUpdate.withOrderStatus(orderStatus));
        }

        throw new ProductDoesNotExistException();
    }
}
