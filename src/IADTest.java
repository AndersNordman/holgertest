import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class IADTest {
	private static IAD iad;
	private static IHD ihd;
	
	@BeforeEach
	public void before() {
		iad = new IAD();
		ihd = mock(IHD.class);
	}
	
	// IAD_TEST_001
    @ParameterizedTest 
    @MethodSource("TEST_001_values")
    public void outputIsAverageOfInputs(IADTestData testData) {
    	when(ihd.getMean()).thenReturn(testData.input);
    	iad.initialize(ihd, testData.limit);
    	iad.execute();
    	assertEquals(testData.expectedOutput, iad.getData(), 
    			() -> "output should be true iff input (" + testData.input + ")" + 
    			" > limit (" + testData.limit + ")");
    }
    
    static IADTestData[] TEST_001_values(){
    	return new IADTestData[] {
    			new IADTestData( 3,  5, false),
    			new IADTestData( 5,  5, false),
    			new IADTestData(20,  5, true),
    			new IADTestData(20, 30, false),
    			new IADTestData(30, 30, false),
    			new IADTestData(50, 30, true)};
    }
    
}

class IADTestData {
	int input;
	int limit;
	boolean expectedOutput;
	
	IADTestData(int input, int limit, boolean expectedOutput){
		this.input = input;
		this.limit = limit;
		this.expectedOutput = expectedOutput;
	}
}