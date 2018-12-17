import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.mockito.Mockito.*;

public class OHDTest {
	private static OHD ohd;
	private static IAD[] iads;
	
	@BeforeEach
	public void before() {
		ohd = new OHD();
		iads = new IAD[5];
		for(int i = 0; i < 5; i++) {
			iads[i] = mock(IAD.class);
		}
		ohd.initialize(iads[0], iads[1], iads[2], iads[3], iads[4]);
	}
	
	// ODH_TEST_001
	@ParameterizedTest
	@MethodSource("testData")
	public void test(boolean[] input) {
		for(int i = 0; i < 5; i++) {
			when(iads[i].getData()).thenReturn(input[i]);
		}
		ohd.execute();
		assertArrayEquals(input, ohd.getOutput(), "input should be put in array");
	}	
	
	static boolean[][] testData() {
		boolean[][] data = new boolean[64][];
		for(int i = 0; i < 64; i++) {
			data[i] = new boolean[] {
					(i &  1) == 0,
					(i &  2) == 0,
					(i &  4) == 0,
					(i &  8) == 0,
					(i & 16) == 0
			};
		}
		return data;
	}
}
