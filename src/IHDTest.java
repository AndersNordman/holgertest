import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;

public class IHDTest {
    private static IHD ihd;

    @BeforeEach
    public static void before() { 
        ihd = new IHD();
        int a = 1;
    }

    @Test
    public void startSensorTest() {
        //ihd.startSensor();
        assertEquals(3, ihd.outputToSensor, "wrong voltage to sensor");
    }

    @Test
    public void readSensorTest() {
        //ihd.readSensor();
        assertEquals(0, ihd.outputToSensor, "wrong voltage to sensor");
    }
}
