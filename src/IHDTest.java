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
    void startSensorTest() {
        ihd.startSensor();
        assertEquals(3, ihd.outputToSensor, "wrong voltage to sensor");
    }

    @Test
    void readSensorTest() {
        ihd.readSensor();
        assertEquals(0, ihd.outputToSensor, "wrong voltage to sensor");
    }
}
