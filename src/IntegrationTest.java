import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.Mockito.*;

public class IntegrationTest {
	private static OHD ohd;
	private static Sensor[] sensors = new Sensor[5];
	private static IHD[] ihds = new IHD[5];
	private static IAD[] iads = new IAD[5];
	
	@BeforeEach
	public void before() {
		for(int i = 0; i < 5; i++) {
			sensors[i] = mock(Sensor.class);
			IHD ihd = new IHD();
			ihd.initialize(sensors[i]);
			IAD iad = new IAD();
			iad.initialize(ihd, i == 0 ? 5 : 30);
			ihds[i] = ihd;
			iads[i] = iad;
		}
		ohd = new OHD();
		ohd.initialize(iads[0], iads[1], iads[2], iads[3], iads[4]);
	}
	
	// Integration_TEST_001
	@Test
	public void test() {
		TestData[] input = new TestData[] {
			new TestData(20, 30, true), 
			new TestData(-30, 10, false),
			new TestData(20, 40, false),
			new TestData(21, 41, true),
			new TestData(220, 210, true)
		};
		boolean[] expected = new boolean[5];
		for(int i = 0; i < 5; i++) {
			expected[i] = input[i].expectedResult;
			sensors[i].originalOutput = input[i].original;
			sensors[i].safetyOutput = input[i].safety;
			ihds[i].execute();
			iads[i].execute();
		}
		ohd.execute();
		assertArrayEquals(expected, ohd.getOutput());
	}

}

class TestData {
	int original;
	int safety;
	boolean expectedResult;
	
	public TestData(int original, int safety, boolean expectedResult) {
		this.original = original;
		this.safety = safety;
		this.expectedResult = expectedResult;
	}
}
