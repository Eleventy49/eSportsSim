package Game;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileSystemView;

import Game.TournamentScheduler;
import Game.Timer;

public class Filehandler {
	final static int ARRAY_SIZE_PLAYER = 18;
	static boolean loadedPlayers = false;
	static boolean loadedTeams = false;

	//Save the currect database of players to the file.
	public static void Save(ArrayList<Player> p,float s, float s2,ArrayList<Team> t,ArrayList<Tournament> collection) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView());
		j.setDialogTitle("Save");
		int r = j.showSaveDialog(null);
		if (r == JFileChooser.APPROVE_OPTION)
		{
			String x = j.getSelectedFile().getAbsolutePath();
			if(x.contains(".csv"))
				x = x.substring(0,x.lastIndexOf(".csv"));
			else
				x += ".csv";
			try {
				FileWriter fw = new FileWriter(x, false);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter w = new PrintWriter(bw);
				for (Player a : p) {
					w.print(a.save());
				}
				w.print("®,\n");
				w.print(s + ",");
				w.print(Application.ConsoleOutput + ",");
				w.print(s2 + ",");
				w.print(Timer.week + ",");
				w.print(Timer.month + ",");
				w.print(Timer.year + ",");
				w.print("\n®,\n");
				for (Team a : t) {
					w.print(a.save());
				}
				w.print("®,\n");
				for (Tournament a : collection) {
					w.print(a.save());
				}
				w.close();

			} catch (IOException e) {
				System.out.println("Failed to open the Players file. Could not save.");
			} catch (NullPointerException e) {
				Application.WarningQuery = true;
				Application.WarningMessage = "There is no game to save";
			}
		}
		
		
	}
	

	//Load the players file into the database
	public static void Load() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView());
		j.setDialogTitle("Open");
		int rml = j.showOpenDialog(null);
		if (rml == JFileChooser.APPROVE_OPTION)
		{
			String x = j.getSelectedFile().getAbsolutePath();
		//ArrayList<Tournament> listo = new ArrayList<Tournament>();
		ArrayList<Team> ii = new ArrayList<Team>();
		ArrayList<Player> list = new ArrayList<Player>();
		ArrayList<Team> listt = new ArrayList<Team>();
		String betterTemp = "";
		float songVolume = 0;
		float effectsVolume = 0;
		boolean console = false;
		int week = 0;
		int month = 0;
		int year = 0;
		boolean quickFunctionTournament = false;
		int[] a = new int[ARRAY_SIZE_PLAYER];


		try {
			FileInputStream file = new FileInputStream(x);

			Scanner scnr = new Scanner(file);
			scnr.useDelimiter("[,\r\n]+");
			while (scnr.hasNext()) {
				String name = scnr.next();
				if(name.equals("®"))
					break;
				int r = scnr.nextInt();
				int World = scnr.nextInt();
				int Major = scnr.nextInt();
				int Minor = scnr.nextInt();
				for (int i = 0; i < ARRAY_SIZE_PLAYER; i++) {
					a[i] = scnr.nextInt();
					if (i == ARRAY_SIZE_PLAYER - 1) {
					}

				}
				int earn = scnr.nextInt();
				boolean c = scnr.nextBoolean();
				boolean mac = scnr.nextBoolean();
				boolean mic = scnr.nextBoolean();
				boolean bp = scnr.nextBoolean();
				boolean wp = scnr.nextBoolean();
				loadedPlayers = true;
				list.add(new Player(name, r, World, Major, Minor, a, earn, c, mac, mic,bp,wp));
				scnr.nextLine();

			}
			Database.playerdatabase = list;
				songVolume = scnr.nextFloat();
				console = scnr.nextBoolean();
				effectsVolume = scnr.nextFloat();
				week = scnr.nextInt();
				month = scnr.nextInt();
				year = scnr.nextInt();
		
				OptionsMenu.setVolumeSong(songVolume);
				Application.ConsoleOutput = console;
				OptionsMenu.setVolumeEffect(effectsVolume);
				Timer.setWeek(week);
				Timer.setMonth(month);
				Timer.setYear(year);
				scnr.next();
				while (scnr.hasNext()) {
					String name = scnr.next();
					if (name.equals("®"))
						break;
					int major = scnr.nextInt();
					int minor = scnr.nextInt();
					int world = scnr.nextInt();
					int earnings = scnr.nextInt();

					String p1 = scnr.next();
					int r1 = scnr.nextInt();
					String p2 = scnr.next();
					int r2 = scnr.nextInt();
					String p3 = scnr.next();
					int r3 = scnr.nextInt();
					String p4 = scnr.next();
					int r4 = scnr.nextInt();
					String p5 = scnr.next();
					int r5 = scnr.nextInt();

					double d = scnr.nextDouble();
					String a1 = scnr.next();
					boolean c = scnr.nextBoolean();
					boolean mac = scnr.nextBoolean();
					boolean mic = scnr.nextBoolean();
					int dollars = scnr.nextInt();
					int cents = scnr.nextInt();
					Cash b = new Cash(dollars, cents);
					ArrayList<Staff> temp = new ArrayList<Staff>();
					for(int i = scnr.nextInt(); i > 0; i--)
					{
						String temp1 = scnr.next();
						Staff.StaffTypes temp2;
						if(temp1.equals("PerformanceTrainer"))
							temp2 = Staff.StaffTypes.PerformanceTrainer;
						else if(temp1.equals("TeamCoach"))
							temp2 = Staff.StaffTypes.TeamCoach;
						else temp2 = Staff.StaffTypes.FinancialManager;
						
						int level = scnr.nextInt();
						
						temp.add(new Staff(temp2,level));
						
					}
					listt.add(new Team(name, major, minor, world, earnings, d, p1, p2, p3, p4, p5, a1, r1, r2, r3, r4, r5, c, mac,mic, b));
					listt.get(listt.size() - 1).staff.addAll(temp);
					listt.get(listt.size() - 1).updateStaff();

					loadedTeams = true;
				}
				Database.teamdatabase = listt;
				System.out.println(Database.teamdatabase.size() + " TEAMDATA");
				while (scnr.hasNext()) {
					try {
					int monthb = scnr.nextInt();
					int yearb = scnr.nextInt();
					String temp = scnr.next();
					if(temp.equals("World"))
					{
						int t = scnr.nextInt();
						for(int i = 0; i < t; i++) {
							betterTemp = scnr.next();
							ii.add(Database.teamHasName(betterTemp));
						}
						
						TournamentScheduler.collection.add(new Tournament(Tournament.FORMAT.World, ii, monthb, yearb));
						scnr.nextLine();
						ii.clear();
					}
					if(temp.equals("Major"))
					{
						int t = scnr.nextInt();
						for(int i = 0; i < t; i++) {
							if(scnr.hasNext()) {
							betterTemp = scnr.next();
							ii.add(Database.teamHasName(betterTemp));
							}
						}
						
						TournamentScheduler.collection.add(new Tournament(Tournament.FORMAT.Major, ii, monthb, yearb));
						scnr.nextLine();
						ii.clear();
					}
					if(temp.equals("Minor4"))
					{
						int t = scnr.nextInt();
						for(int i = 0; i < t; i++) {
							betterTemp = scnr.next();
							ii.add(Database.teamHasName(betterTemp));
							
						}
						
						TournamentScheduler.collection.add(new Tournament(Tournament.FORMAT.Minor4, ii, monthb, yearb));
						scnr.nextLine();
						ii.clear();
					}
					if(temp.equals("Minor8"))
					{
						int t = scnr.nextInt();
						
						for(int i = 0; i < t; i++) {
							betterTemp = scnr.next();
							System.out.println("BetterTemp is: " + betterTemp);
							ii.add(Database.teamHasName(betterTemp));
							System.out.println("BetterTemp is: " + betterTemp);
						}
						
						TournamentScheduler.collection.add(new Tournament(Tournament.FORMAT.Minor8, ii, monthb, yearb));
						scnr.nextLine();
						ii.clear();
					}
					}
					catch(NoSuchElementException e)
					{
						break;
					}
						
						
						
					}
			//TournamentScheduler.collection = listo;
			
			
			
			
			
			
			scnr.close();
		} catch (IOException e) {
			System.out.println("Failed to load data from the Players file.");
		}

	}


	
}
	public static void newManagerGame() {
		
		ArrayList<Tournament> listo = new ArrayList<Tournament>();
		ArrayList<Team> ii = new ArrayList<Team>();
		ArrayList<Player> list = new ArrayList<Player>();
		ArrayList<Team> listt = new ArrayList<Team>();
		String betterTemp = "";
		float songVolume = 0;
		float effectsVolume = 0;
		boolean console = false;
		int week = 0;
		int month = 0;
		int year = 0;
		boolean quickFunctionTournament = false;
		int[] a = new int[ARRAY_SIZE_PLAYER];


		try {
			FileInputStream file = new FileInputStream("spectatorgame.csv");

			Scanner scnr = new Scanner(file);
			scnr.useDelimiter("[,\r\n]+");
			while (scnr.hasNext()) {
				String name = scnr.next();
				if(name.equals("®"))
					break;
				int r = scnr.nextInt();
				int World = scnr.nextInt();
				int Major = scnr.nextInt();
				int Minor = scnr.nextInt();
				for (int i = 0; i < ARRAY_SIZE_PLAYER; i++) {
					a[i] = scnr.nextInt();
					if (i == ARRAY_SIZE_PLAYER - 1) {
					}

				}
				int earn = scnr.nextInt();
				boolean c = scnr.nextBoolean();
				boolean mac = scnr.nextBoolean();
				boolean mic = scnr.nextBoolean();
				boolean bp = scnr.nextBoolean();
				boolean wp = scnr.nextBoolean();
				loadedPlayers = true;
				list.add(new Player(name, r, World, Major, Minor, a, earn, c, mac, mic,bp,wp));
				scnr.nextLine();

			}
			Database.playerdatabase = list;
				songVolume = scnr.nextFloat();
				console = scnr.nextBoolean();
				effectsVolume = scnr.nextFloat();
				week = scnr.nextInt();
				month = scnr.nextInt();
				year = scnr.nextInt();
		
				OptionsMenu.setVolumeSong(songVolume);
				Application.ConsoleOutput = console;
				OptionsMenu.setVolumeEffect(effectsVolume);
				Timer.setWeek(week);
				Timer.setMonth(month);
				Timer.setYear(year);
				System.out.println("You got into the load block");
				scnr.next();
				while (scnr.hasNext()) {
					String name = scnr.next();
					if (name.equals("®"))
						break;
					int major = scnr.nextInt();
					int minor = scnr.nextInt();
					int world = scnr.nextInt();
					int earnings = scnr.nextInt();

					String p1 = scnr.next();
					int r1 = scnr.nextInt();
					String p2 = scnr.next();
					int r2 = scnr.nextInt();
					String p3 = scnr.next();
					int r3 = scnr.nextInt();
					String p4 = scnr.next();
					int r4 = scnr.nextInt();
					String p5 = scnr.next();
					int r5 = scnr.nextInt();

					double d = scnr.nextDouble();
					String a1 = scnr.next();
					boolean c = scnr.nextBoolean();
					boolean mac = scnr.nextBoolean();
					boolean mic = scnr.nextBoolean();
					int dollars = scnr.nextInt();
					int cents = scnr.nextInt();
					Cash b = new Cash(dollars, cents);
					ArrayList<Staff> temp = new ArrayList<Staff>();
					for(int i = scnr.nextInt(); i > 0; i--)
					{
						String temp1 = scnr.next();
						Staff.StaffTypes temp2;
						if(temp1.equals("PerformanceTrainer"))
							temp2 = Staff.StaffTypes.PerformanceTrainer;
						else if(temp1.equals("TeamCoach"))
							temp2 = Staff.StaffTypes.TeamCoach;
						else temp2 = Staff.StaffTypes.FinancialManager;
						
						int level = scnr.nextInt();
						
						temp.add(new Staff(temp2,level));
						
					}
					listt.add(new Team(name, major, minor, world, earnings, d, p1, p2, p3, p4, p5, a1, r1, r2, r3, r4, r5, c, mac, mic, b));
					listt.get(listt.size() - 1).staff.addAll(temp);
					listt.get(listt.size() - 1).updateStaff();

					loadedTeams = true;
				}
				Database.teamdatabase = listt;
				while (scnr.hasNext()) {
					try {
					int monthb = scnr.nextInt();
					int yearb = scnr.nextInt();
					String temp = scnr.next();
					if(temp.equals("World"))
					{
						int t = scnr.nextInt();
						for(int i = 0; i < t; i++) {
							betterTemp = scnr.next();
							ii.add(Database.teamHasName(betterTemp));
						}
						
						TournamentScheduler.collection.add(new Tournament(Tournament.FORMAT.World, ii, monthb, yearb));
						scnr.nextLine();
						ii.clear();
					}
					if(temp.equals("Major"))
					{
						int t = scnr.nextInt();
						for(int i = 0; i < t; i++) {
							if(scnr.hasNext()) {
							betterTemp = scnr.next();
							ii.add(Database.teamHasName(betterTemp));
							}
						}
						
						TournamentScheduler.collection.add(new Tournament(Tournament.FORMAT.Major, ii, monthb, yearb));
						scnr.nextLine();
						ii.clear();
					}
					if(temp.equals("Minor4"))
					{
						int t = scnr.nextInt();
						for(int i = 0; i < t; i++) {
							betterTemp = scnr.next();
							ii.add(Database.teamHasName(betterTemp));
							
						}
						
						TournamentScheduler.collection.add(new Tournament(Tournament.FORMAT.Minor4, ii, monthb, yearb));
						scnr.nextLine();
						ii.clear();
					}
					if(temp.equals("Minor8"))
					{
						int t = scnr.nextInt();
						
						for(int i = 0; i < t; i++) {
							betterTemp = scnr.next();
							System.out.println("BetterTemp is: " + betterTemp);
							ii.add(Database.teamHasName(betterTemp));
							System.out.println("BetterTemp is: " + betterTemp);
						}
						
						TournamentScheduler.collection.add(new Tournament(Tournament.FORMAT.Minor8, ii, monthb, yearb));
						scnr.nextLine();
						ii.clear();
					}
					}
					catch(NoSuchElementException e)
					{
						break;
					}
						
						
						
					}
			TournamentScheduler.collection = listo;
			
			
			
			
			
			
			scnr.close();
	
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void newSpectatorGame() {
		
		ArrayList<Tournament> listo = new ArrayList<Tournament>();
		ArrayList<Team> ii = new ArrayList<Team>();
		ArrayList<Player> list = new ArrayList<Player>();
		ArrayList<Team> listt = new ArrayList<Team>();
		String betterTemp = "";
		float songVolume = 0;
		float effectsVolume = 0;
		boolean console = false;
		int week = 0;
		int month = 0;
		int year = 0;
		boolean quickFunctionTournament = false;
		int[] a = new int[ARRAY_SIZE_PLAYER];


		try {
			FileInputStream file = new FileInputStream("spectatorgame.csv");

			Scanner scnr = new Scanner(file);
			scnr.useDelimiter("[,\r\n]+");
			while (scnr.hasNext()) {
				String name = scnr.next();
				if(name.equals("®"))
					break;
				int r = scnr.nextInt();
				int World = scnr.nextInt();
				int Major = scnr.nextInt();
				int Minor = scnr.nextInt();
				for (int i = 0; i < ARRAY_SIZE_PLAYER; i++) {
					a[i] = scnr.nextInt();
					if (i == ARRAY_SIZE_PLAYER - 1) {
					}

				}
				int earn = scnr.nextInt();
				boolean c = scnr.nextBoolean();
				boolean mac = scnr.nextBoolean();
				boolean mic = scnr.nextBoolean();
				boolean bp = scnr.nextBoolean();
				boolean wp = scnr.nextBoolean();
				loadedPlayers = true;
				list.add(new Player(name, r, World, Major, Minor, a, earn, c, mac,mic,bp,wp));
				scnr.nextLine();

			}
			Database.playerdatabase = list;
				songVolume = scnr.nextFloat();
				console = scnr.nextBoolean();
				effectsVolume = scnr.nextFloat();
				week = scnr.nextInt();
				month = scnr.nextInt();
				year = scnr.nextInt();
		
				OptionsMenu.setVolumeSong(songVolume);
				Application.ConsoleOutput = console;
				OptionsMenu.setVolumeEffect(effectsVolume);
				Timer.setWeek(week);
				Timer.setMonth(month);
				Timer.setYear(year);
				System.out.println("You got into the load block");
				scnr.next();
				while (scnr.hasNext()) {
					String name = scnr.next();
					if (name.equals("®"))
						break;
					int major = scnr.nextInt();
					int minor = scnr.nextInt();
					int world = scnr.nextInt();
					int earnings = scnr.nextInt();

					String p1 = scnr.next();
					int r1 = scnr.nextInt();
					String p2 = scnr.next();
					int r2 = scnr.nextInt();
					String p3 = scnr.next();
					int r3 = scnr.nextInt();
					String p4 = scnr.next();
					int r4 = scnr.nextInt();
					String p5 = scnr.next();
					int r5 = scnr.nextInt();

					double d = scnr.nextDouble();
					String a1 = scnr.next();
					boolean c = scnr.nextBoolean();
					boolean mac = scnr.nextBoolean();
					boolean mic = scnr.nextBoolean();
					int dollars = scnr.nextInt();
					int cents = scnr.nextInt();
					Cash b = new Cash(dollars, cents);
					ArrayList<Staff> temp = new ArrayList<Staff>();
					for(int i = scnr.nextInt(); i > 0; i--)
					{
						String temp1 = scnr.next();
						Staff.StaffTypes temp2;
						if(temp1.equals("PerformanceTrainer"))
							temp2 = Staff.StaffTypes.PerformanceTrainer;
						else if(temp1.equals("TeamCoach"))
							temp2 = Staff.StaffTypes.TeamCoach;
						else temp2 = Staff.StaffTypes.FinancialManager;
						
						int level = scnr.nextInt();
						
						temp.add(new Staff(temp2,level));
						
					}
					listt.add(new Team(name, major, minor, world, earnings, d, p1, p2, p3, p4, p5, a1, r1, r2, r3, r4, r5, c, mac, mic, b));
					listt.get(listt.size() - 1).staff.addAll(temp);
					listt.get(listt.size() - 1).updateStaff();

					loadedTeams = true;
				}
				Database.teamdatabase = listt;
				while (scnr.hasNext()) {
					try {
					int monthb = scnr.nextInt();
					int yearb = scnr.nextInt();
					String temp = scnr.next();
					if(temp.equals("World"))
					{
						int t = scnr.nextInt();
						for(int i = 0; i < t; i++) {
							betterTemp = scnr.next();
							ii.add(Database.teamHasName(betterTemp));
						}
						
						TournamentScheduler.collection.add(new Tournament(Tournament.FORMAT.World, ii, monthb, yearb));
						scnr.nextLine();
						ii.clear();
					}
					if(temp.equals("Major"))
					{
						int t = scnr.nextInt();
						for(int i = 0; i < t; i++) {
							if(scnr.hasNext()) {
							betterTemp = scnr.next();
							ii.add(Database.teamHasName(betterTemp));
							}
						}
						
						TournamentScheduler.collection.add(new Tournament(Tournament.FORMAT.Major, ii, monthb, yearb));
						scnr.nextLine();
						ii.clear();
					}
					if(temp.equals("Minor4"))
					{
						int t = scnr.nextInt();
						for(int i = 0; i < t; i++) {
							betterTemp = scnr.next();
							ii.add(Database.teamHasName(betterTemp));
							
						}
						
						TournamentScheduler.collection.add(new Tournament(Tournament.FORMAT.Minor4, ii, monthb, yearb));
						scnr.nextLine();
						ii.clear();
					}
					if(temp.equals("Minor8"))
					{
						int t = scnr.nextInt();
						
						for(int i = 0; i < t; i++) {
							betterTemp = scnr.next();
							System.out.println("BetterTemp is: " + betterTemp);
							ii.add(Database.teamHasName(betterTemp));
							System.out.println("BetterTemp is: " + betterTemp);
						}
						
						TournamentScheduler.collection.add(new Tournament(Tournament.FORMAT.Minor8, ii, monthb, yearb));
						scnr.nextLine();
						ii.clear();
					}
					}
					catch(NoSuchElementException e)
					{
						break;
					}
						
						
						
					}
			TournamentScheduler.collection = listo;
			
			
			
			
			
			
			scnr.close();
	
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	

	}

