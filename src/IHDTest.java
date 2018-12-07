import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class IHDTest {
    private static IHD ihd;

    @BeforeAll
    static void before(){
        ihd = new IHD();
    }

    @Test
    void test() {
        ihd.startSensor();
        assertEquals(3, ihd.outputToSensor, "wrong voltage to sensor");
    }
}
