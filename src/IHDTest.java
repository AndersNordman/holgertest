import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class IHDTest {
    private static IHD ihd;
    private static Sensor sensor;

    @BeforeEach
    public void beforeEach() {
    	ihd = new IHD();
        sensor = new Sensor();
        ihd.initialize(sensor);
    }
    
    // IHD_TEST_001
    @ParameterizedTest 
    @MethodSource("TEST_001_values")
    public void outputIsAverageOfInputs(int[] values) {
    	sensor.originalOutput = values[0];
    	sensor.safetyOutput = values[1];
    	ihd.execute();
    	assertEquals(values[2], ihd.getMean(), "output should be average of inputs");
    }
    
    static int[][] TEST_001_values(){
    	return new int[][] {
    			{100, 110, 105}, 
        		{150, 181, 165},
        		{ 30,  40,  35}, 
        		{190, 180, 185},
        		{200, 200, 200}, 
        		{  0,   0,   0}};
    }
    
    // IHD_TEST_002
    @ParameterizedTest
    @MethodSource("TEST_002_values")
    public void inputBounderiesHigh(int[] values) {
    	sensor.originalOutput = values[0];
    	sensor.safetyOutput = values[1];
    	ihd.execute();
    	assertEquals(values[2], ihd.getMean(), "inputs over 200 should be handled as 200");
    }
    
    static int[][] TEST_002_values(){
    	return new int[][] {
    			{190, 210, 195}, 
        		{220, 190, 195},
        		{210, 220, 200}}; 
    }
    
    // IHD_TEST_003
    @ParameterizedTest
    @MethodSource("TEST_003_values")
    public void inputBounderiesLow(int[] values) {
    	sensor.originalOutput = values[0];
    	sensor.safetyOutput = values[1];
    	ihd.execute();
    	assertEquals(values[2], ihd.getMean(), "inputs under 0 should be handled as 0");
    }
    
    static int[][] TEST_003_values(){
    	return new int[][] {
    			{ 10, -20,  5}, 
        		{-10,  20, 10},
        		{-10, -20,  0}}; 
    }
    
    // IDH_TEST_004
    @Test
    public void startSensorTest() {
        ihd.startSensor();
        assertEquals(3, ihd.outputToSensor, "wrong voltage to sensor");
    }

    // IDH_TEST_005
    @Test
    public void readSensorTest() {
        ihd.listenToSensor();
        assertEquals(0, ihd.outputToSensor, "wrong voltage to sensor");
    }
}
