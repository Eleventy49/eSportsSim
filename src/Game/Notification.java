package Game;

public class Notification {
	String txt = "";
	static int id = 0;
	int uniqueid;
	
	public Notification(String text)
	{
		txt = text;
		uniqueid = id;
		id++;
	}
	
	public String toString()
	{
		return txt + "\t" + uniqueid + "\t" + id; 
	}
	
	public static void resetID()
	{
		id = 0;
	}
	
}
