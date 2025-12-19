import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdServiceTest {

    @Test
    void generateRandomId() {
        IdService idService = new IdService();
        assertNull(idService.generateRandomId());
    }
}