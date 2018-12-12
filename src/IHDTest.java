import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;

class IHDTest {
    private static IHD ihd;

    @BeforeEach
    static void before() { 
        ihd = new IHD();
        int a = 1;
    }

    @Test
    void startSensorTest() {
        //ihd.startSensor();
        assertEquals(3, ihd.outputToSensor, "wrong voltage to sensor");
    }

    @Test
    void readSensorTest() {
        //ihd.readSensor();
        assertEquals(0, ihd.outputToSensor, "wrong voltage to sensor");
    }
}
