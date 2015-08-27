public class calculator2 {
	private int firstValue, secondValue;
	
	public calculator2(int first, int second)
	{
		this.firstValue = first;
		this.secondValue = second;
	}

	public int getFirst() 
	{
		return firstValue;
	}

	public int getSecond()
	{
		return secondValue;
	}

	public int Add() {
		return firstValue + secondValue;
	}

	public int Sub() {
		return firstValue - secondValue;
	}

	public int Mul() {
		return firstValue * secondValue;
	}

	public int Div() {
		try {
			int result = firstValue / secondValue;
			return result;
		} catch(Exception e) {
			System.out.println("div zero");
		}
		return 0;
	}
}


