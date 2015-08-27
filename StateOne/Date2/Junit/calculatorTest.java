import static org.junit.Assert.*;
import org.junit.Test;
public class  calculatorTest {
	@Test
	public void testAdd() {
		System.out.println("AddTest");
		assertEquals(6, calculator.Add(4, 2));
	}

	@Test
	public void testSub() {
		System.out.println("SubTest");
		assertEquals(2, calculator.Sub(4, 2));
	}

	@Test
	public void testMul() {
		System.out.println("MulTest");
		assertEquals(8, calculator.Mul(4, 2));
	}

	@Test
	public void testDiv() {
		System.out.println("DivTest");
		assertEquals(2, calculator.Div(4, 2));
	}
}
