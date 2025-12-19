
import java.util.UUID;

public class IdService {
    String generateRandomId() {
        return UUID.randomUUID().toString();
    }
}
