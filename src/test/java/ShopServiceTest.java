import org.junit.jupiter.api.Test;

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
        Order expected = new Order("-1", List.of(new Product("1", "Apfel")), OrderStatus.PROCESSING, actual.timeStamp());
        assertEquals(expected.products(), actual.products());
        assertNotNull(expected.id());
    }

    @Test
    void addOrderTest_whenInvalidProductId_throwsProductDoesNotExistException() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("2", "3");

        assertThrows(Exception.class, () -> shopService.addOrder(productsIds));
    }

    @Test
    void getOrdersByOrderStatus_passesWhenOrderStatusEqualsFirstOrderListsElementOrderStatus() {

        // Given
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1");
        shopService.addOrder(productsIds);

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

        assertThrows(NullPointerException.class, () -> shopService.updateOrder("1", OrderStatus.IN_DELIVERY));
    }

    @Test
    void updateOrder_doesNotThrowException(){
        ShopService shopService = new ShopService();
        Order newOrder = shopService.addOrder(List.of("1"));
        String orderId = newOrder.id();

        Order expectedUpdatedOrder = newOrder.withOrderStatus(OrderStatus.IN_DELIVERY);
        Order actualUpdatedOrder = shopService.updateOrder(orderId, OrderStatus.IN_DELIVERY);

        assertDoesNotThrow(() -> shopService.updateOrder(orderId, OrderStatus.IN_DELIVERY));
        assertEquals(expectedUpdatedOrder, actualUpdatedOrder);
    }
}
