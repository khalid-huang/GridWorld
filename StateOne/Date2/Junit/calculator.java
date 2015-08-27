public class calculator {
	private calculator() {}

	public static int Add(int firstValue, int secondValue) {
		return firstValue + secondValue;
	}

	public static int Sub(int firstValue, int secondValue) {
		return firstValue - secondValue;
	}

	public static int Mul(int firstValue, int secondValue) {
		return firstValue * secondValue;
	}

	public static int Div(int firstValue, int secondValue) {
		try {
			int result = firstValue / secondValue;
			return result;
		} catch(Exception e) {
			System.out.println("div zero");
		}
		return 0;
	}
}


