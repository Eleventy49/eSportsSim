package Game;

public class Cash {
	private int dollarAmount = 0;
	private int centsAmount = 0;
	
	public Cash(int d, int c)
	{
		dollarAmount = d;
		centsAmount = c;
	}
	
	public float getAmount()
	{
		return dollarAmount + (centsAmount / 100);
	}
	
	public String toString()
	{
		return "$" + getAmount();
		
	}
	
	public String save() {
		return dollarAmount + "," + centsAmount;
	}
}
