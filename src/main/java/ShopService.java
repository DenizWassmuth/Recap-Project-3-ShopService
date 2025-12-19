import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;
@Data
@Builder
@RequiredArgsConstructor
public class ShopService {

    private ProductRepo productRepo;
    private OrderRepo orderRepo;

    public Order addOrder(List<String> productIds) {
        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {

            try {
                Product productToOrder = productRepo.getProductById(productId);
                //if (productToOrder == null) {
                //    throw new ProductDoesNotExistException();
                //   System.out.println("Product mit der Id: " + productId + " konnte nicht bestellt werden!");
                //    return null;
                //     }

                products.add(productToOrder);

            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        }

        Order newOrder = new Order(UUID.randomUUID().toString(), products, OrderStatus.PROCESSING, Instant.now());

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

        throw new NullPointerException("Order with id " + orderId + " not found");
    }
}
