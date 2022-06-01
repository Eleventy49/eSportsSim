package Game;

import java.util.Random;
import java.util.Scanner;

public class Name {
	String first;
	String middle;
	String last;
	public String user = "";
	
	static String[] firsts = {"Austin","AJ","George","Mike","Travis","Cam","Cameron","Dylan","James","Rob","Robert","Aaron","Nate","Nathan","Michael","Neil","RJ","Donny",
			"Adam","Kenny","Kendrick","Trevor","Daniel","Clinton","Paul","Ray","Quintin" };
	static String[] middles = {"Austin","George","Mike","Travis","Cam","Cameron","Dylan","James","Rob","Robert","Aaron","Nate","Nathan","Michael","Neil","Donny",
			"Adam","Kenny","Kendrick","Trevor","Daniel","Clinton","Paul","Ray" };
	static String[] lasts = {"Nelson","Wilson","Palmer","Hughes","Gibson","Smith","Puckett","Curtis","Callahan","Blackmore","Schuler","Miller","Rollins","Glass",
			"Ryder","Zimmerman","Kowalski","Wright","Perez","Patrick","Anderson","Williams","Young","Jennings","Jordan","Wiseman","Holt"};
	
	
	
	static char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	static Random r = new Random();
	
	public Name()
	{
		if(Math.random() > 0.50)
		{
			Random1();
		}
		else
		{
			Random2();
		}	
	}
	public void Random1()
	{
		first = getFirst();
		middle = getMiddle();
		last = getLast();	
		switch(r.nextInt(3))
		{
		case 0:
			System.out.print("1, 0: ");
			user = first + " " +  last;
			break;
		case 1:
			System.out.print("1, 1: ");
			user = middle + " " + last;
			break;
		case 2:
			System.out.print("1, 2: ");
			user = first + " " + middle + " " + last;
			break;
		default:
			System.out.print("1, default: ");
			user = first.substring(0,1) + middle.substring(0,1) + last.substring(0,1);
			break;
		}
	}
	public void Random2()
	{
		System.out.print("2: ");
		for(int i = r.nextInt(5) + 1; i > 0; i--)
		{
			user += alphabet[r.nextInt(26)];
		}
	}
	public String getFirst()
	{
		return firsts[r.nextInt(firsts.length)];
	}
	public String getMiddle()
	{
		return middles[r.nextInt(middles.length)];
	}
	public String getLast()
	{
		
		return lasts[r.nextInt(lasts.length)];
	}

}
