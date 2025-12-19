
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

    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;
    private final IdService idService;

    public ShopService(){
        this.productRepo = new ProductRepo();
        this.orderRepo = new OrderMapRepo();
        this.idService = new IdService();
    }

    public Order addOrder(List<String> productIds) {
        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {
            try {
                Product productToOrder = productRepo.getProductById(productId);
                products.add(productToOrder);

            } catch (Exception e) {

                throw new ProductDoesNotExistException();
            }
        }

        Order newOrder = new Order(idService.generateRandomId(), products, OrderStatus.PROCESSING, Instant.now());

        return orderRepo.addOrder(newOrder);
    }

    public List<Order> getOrders() {
        return orderRepo.getOrders();
    }

    public List<Order> getOrdersByOrderStatus(OrderStatus orderStatus) {
        return orderRepo.getOrders().stream().filter(order -> order.orderStatus() == orderStatus).toList();
    }

//    public void addOrderDirectlyToOrderRepo(Order order)
//    {
//        orderRepo.addOrder(order);
//    }

    public Order updateOrder(String orderId, OrderStatus orderStatus){

        try{
            Optional<Order> optionalOrder = orderRepo.getOrderById(orderId);
            if (optionalOrder.isPresent()) {
                Order orderToUpdate = optionalOrder.get();
                orderRepo.removeOrder(orderId);
                return orderRepo.addOrder(orderToUpdate.withOrderStatus(orderStatus));
            }
        }catch (Exception e){
            throw new ProductDoesNotExistException();
        }

        throw new ProductDoesNotExistException();
    }
}
