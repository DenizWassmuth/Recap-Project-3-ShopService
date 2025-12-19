import lombok.With;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

public record Order(
        String id,
        List<Product> products,
        @With OrderStatus orderStatus,
        Instant timeStamp){

    public Order(String id, List<Product> products, OrderStatus orderStatus){

        this(id, products, orderStatus, null);
    }
}
