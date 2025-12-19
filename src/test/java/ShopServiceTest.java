import org.junit.jupiter.api.Test;

import java.lang.classfile.ClassFile;
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

        List<Order> actual = shopService.getOrdersByOrderStatus(expectedStatus);
        OrderStatus actualOrderStatus = actual.getFirst().orderStatus();

        // Then
        assertEquals(expectedStatus, actualOrderStatus);
    }

    @Test
    void updateOrder_expectNull(){

        ShopService shopService = new ShopService();
        Order actual = shopService.updateOrder("1", OrderStatus.IN_DELIVERY);

        Order expected = null;

        assertEquals(expected, actual);
        assertNull(actual);
    }
}
