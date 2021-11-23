package Game;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

//The full collection of teams and players. Created automatically in the FileHandler.
public class Database {
	public static ArrayList<Player> playerdatabase = new ArrayList<Player>();
	public static ArrayList<Team> teamdatabase = new ArrayList<Team>();
	
	public static int getPlayerPosition(int j, Player p)
	{
		ArrayList<Player> temp = new ArrayList<Player>();
		for(Player p1 : playerdatabase)
			temp.add(p1);
		if(j == -1)
			temp = sortPlayersGeneral(temp);
		else
			temp =sortPlayersByStat(temp, j);
		
		return temp.indexOf(p) + 1;
		
	}
	public static ArrayList<Player> sortPlayersGeneral(ArrayList<Player> t)
	{	
		
		Player T1;
		Player T2;
		Player temp1;
		Player temp2;
		int zebra = 0;
		boolean ITellYouToKeepGoing = true;
		while(ITellYouToKeepGoing)
		{
			for(int i = 0; i < t.size() - 1; i++)
			{ 
				if(t.get(i).getStatsTotal() < t.get(i+1).getStatsTotal())
				{zebra++;
					
					temp1 = t.get(i);
					temp2 = t.get(i+1);
					t.remove(temp1);
					t.remove(temp2);
					t.add(i, temp2);
					t.add(i+1,temp1);
					
				}
			}
			if(zebra == 0)
			{
				ITellYouToKeepGoing =  false;
			}
			zebra = 0;
		
			
		}
	/*	for(Team x: teamdatabase)
		{
			System.out.println(x.getTeamStrength());
		}*/
		return t;
	
	}
	public static ArrayList<Player> sortPlayersByStat(ArrayList<Player> t,int j)
	{	
		
		Player T1;
		Player T2;
		Player temp1;
		Player temp2;
		int zebra = 0;
		boolean ITellYouToKeepGoing = true;
		while(ITellYouToKeepGoing)
		{
			for(int i = 0; i < t.size() - 1; i++)
			{ 
				if(t.get(i).stats[j] < t.get(i+1).stats[j])
				{zebra++;
					
					temp1 = t.get(i);
					temp2 = t.get(i+1);
					t.remove(temp1);
					t.remove(temp2);
					t.add(i, temp2);
					t.add(i+1,temp1);
					
				}
			}
			if(zebra == 0)
			{
				ITellYouToKeepGoing =  false;
			}
			zebra = 0;
		
			
		}
	
		return t;
	
	}
	
	
	
	public static ArrayList<Team> sortTeamsBySkill()
	{
		
		
		Team T1;
		Team T2;
		Team temp1;
		Team temp2;
		int zebra = 0;
		boolean ITellYouToKeepGoing = true;
		while(ITellYouToKeepGoing)
		{
			for(int i = 0; i < teamdatabase.size() - 1; i++)
			{ 
				if(teamdatabase.get(i).getTeamStrength() < teamdatabase.get(i+1).getTeamStrength())
				{zebra++;
					
					temp1 = teamdatabase.get(i);
					temp2 = teamdatabase.get(i+1);
					teamdatabase.remove(temp1);
					teamdatabase.remove(temp2);
					teamdatabase.add(i, temp2);
					teamdatabase.add(i+1,temp1);
					
				}
			}
			if(zebra == 0)
			{
				ITellYouToKeepGoing =  false;
			}
			zebra = 0;
		
			System.out.println("We did something");
		}
	
		return teamdatabase;
	}
	public static ArrayList<Team> getStrongBois(int n)
	{
		ArrayList<Team> goingfrom = sortTeamsBySkill();
		ArrayList<Team> returnyboi = new ArrayList<Team>();
		if(n == 32)
		for(int i = 0; i < 32; i++)
			returnyboi.add(goingfrom.get(i));
		else if(n == 8)
			for(int i = 0; i < 8; i++)
				returnyboi.add(goingfrom.get(i));
		else if(n == 16)
			for(int i = 0; i < 16; i++)
				returnyboi.add(goingfrom.get(i));
		return returnyboi;
			
	}
	public static ArrayList<Team> getWeakBois(int n)
	{
		Team temp = null;
		Random r = new Random();
		
		ArrayList<Team> goingfrom = sortTeamsBySkill();
		System.out.println("goingfrom" + goingfrom.size() );
		ArrayList<Team> returnyboi = new ArrayList<Team>();
		for(int i = 0; i < n; ++i)
		{
			temp = goingfrom.get(r.nextInt(15) + 16);
					
			if(returnyboi.contains(temp))
			{
				i--;
				returnyboi.remove(temp);
			}
			else returnyboi.add(temp);
			if(i == n -1 && returnyboi.size() != n)
				i--;
			
			
		}
		return returnyboi;
			
	}
	
	public static ArrayList<Team> getRandomTeams(int n)
	{
		Team temp = null;
		Random r = new Random();
		
		ArrayList<Team> goingfrom = sortTeamsBySkill();
		System.out.println("goingfrom" + goingfrom.size() );
		ArrayList<Team> returnyboi = new ArrayList<Team>();
		for(int i = 0; i < n; ++i)
		{
			temp = goingfrom.get(r.nextInt(goingfrom.size()));
					
			if(returnyboi.contains(temp))
			{
				i--;
				returnyboi.remove(temp);
			}
			else returnyboi.add(temp);
			if(i == n -1 && returnyboi.size() != n)
				i--;
			
			
		}
		return returnyboi;
			
	}
	public static Team getRandomBoi(ArrayList<Team> participating) {
		ArrayList<Team> temp = new ArrayList<Team>();
		for(Team x: teamdatabase)
		{
			temp.add(x);
		}
		for(Team x: participating)
		{
			temp.remove(x);
		}
		Random r = new Random();
	//	System.out.println(temp.size());
	//	System.out.println(teamdatabase.size());
		return temp.get(r.nextInt(temp.size()));
		
		
		
	}
	public static Team teamHasName(String betterTemp) {
		for(Team x: teamdatabase) {
			if (x.name.equals(betterTemp))
			return x;	
		}
		return null;
		
	}
	public static Player playerHasName(String string) {
		for(Player x: playerdatabase) {
			if (x.name.equals(string))
			return x;	
		}
		return null;
		
		
		
		
		
		
		
	}
}
