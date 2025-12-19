import org.junit.jupiter.api.Test;

import java.lang.classfile.ClassFile;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {

    @Test
    void addOrderTest() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1");

        //WHEN
        Order actual = shopService.addOrder(productsIds);

        //THEN
        Order expected = new Order("-1", List.of(new Product("1", "Apfel")), OrderStatus.PROCESSING);
        assertEquals(expected.products(), actual.products());
        assertNotNull(expected.id());
    }

    @Test
    void addOrderTest_whenInvalidProductId_throwsProductDoesNotExistException() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1", "2");

        //WHEN
        //Order actual = shopService.addOrder(productsIds);

        //THEN
        //assertNull(actual);

        assertThrows(ProductDoesNotExistException.class, () -> shopService.addOrder(productsIds));
    }

    @Test
    void getOrdersByOrderStatus_passesWhenOrderStatusEqualsFirstOrderListsElementOrderStatus() {

        // Given
        Order order1 = new Order("1", List.of(new Product("1", "Apfel")), OrderStatus.PROCESSING);
        Order order2 = new Order("1", List.of(new Product("1", "Birne")), OrderStatus.PROCESSING);

        ShopService shopService = new ShopService();
        shopService.addOrderDirectlyToOrderRepo(order1);
        shopService.addOrderDirectlyToOrderRepo(order2);

        // When
        OrderStatus expectedStatus = OrderStatus.PROCESSING;

        List<Order> actualOrder = shopService.getOrdersByOrderStatus(expectedStatus);
        OrderStatus actualStatus = actualOrder.getFirst().orderStatus();

        // Then
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    void updateOrder_throwsOrderDoesNotExistException() {

        ShopService shopService = new ShopService();

        assertThrows(ProductDoesNotExistException.class, () -> shopService.updateOrder("1", OrderStatus.IN_DELIVERY));
    }

    @Test
    void updateOrder_doesNotThrowException(){
        ShopService shopService = new ShopService();

        Order newOrder = new Order("1", List.of(new Product("1", "Birne")), OrderStatus.PROCESSING);
        shopService.addOrderDirectlyToOrderRepo(newOrder);

        Order expectedUpdatedOrder = newOrder.withOrderStatus(OrderStatus.IN_DELIVERY);
        Order actualUpdatedOrder = shopService.updateOrder("1", OrderStatus.IN_DELIVERY);

        assertDoesNotThrow(() -> shopService.updateOrder("1", OrderStatus.IN_DELIVERY));
        assertEquals(expectedUpdatedOrder, actualUpdatedOrder);
    }
}
