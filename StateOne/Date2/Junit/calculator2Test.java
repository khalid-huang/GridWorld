import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

//指定运行器org.junit.runners.Parameteized
@RunWith(Parameterized.class)
public class calculator2Test {
	private calculator2 cal;
	//用于保存传入的参数
	private int firstValue, secondValue;
	
	//创建需要传入的参数对
	@Parameters
	public static Collection perpareData()
	{
		Object[][] objdects = new Object[][] {
			{4, 3}, {2, 3}, {5, 5},{6, 7}
		};
		return Arrays.asList(objdects);
	}

	//为测试类声明一个带有参数的公共构造函数，便于传入参数
	public calculator2Test(int first, int second)
	{
		this.firstValue = first;
		this.secondValue = second;
		cal = new calculator2(firstValue, secondValue);
	}
	//编写测试
	@Test
	public void testAdd() {
		int first = cal.getFirst();
		int second = cal.getSecond();
		assertEquals(first+second, cal.Add());
	}
}
