import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdServiceTest {

    @Test
    void generateRandomId() {
        IdService idService = new IdService();

        String randomId = idService.generateRandomId();
        boolean expected = true;

        boolean actual = !randomId.isEmpty();

        assertEquals(expected, actual);
        //assertNotNull(idService.generateRandomId());
    }
}