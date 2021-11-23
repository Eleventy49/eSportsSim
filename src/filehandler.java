import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class filehandler {
	final static int ARRAY_SIZE_PLAYER = 18;
	static boolean loadedPlayers = false;
	static boolean loadedTeams = false;

	public static void SavePlayers(ArrayList<player> p) {
		try {
			FileWriter fw = new FileWriter("players.csv", false);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter w = new PrintWriter(bw);
			for (player a : p) {
				w.print(a.save());
			}
			w.close();

		} catch (IOException e) {
			System.out.println("Failed to open the Players file. Could not save.");
		}
	}

	public static void SaveTeams(ArrayList<team> t) {
		try {
			FileWriter fw = new FileWriter("teams.csv", false);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter w = new PrintWriter(bw);

			for (team a : t) {
				w.print(a.save());
			}
			w.close();

		} catch (IOException e) {
			System.out.println("Failed to open the Teams file. Could not save.");
		}
	}

	public static ArrayList<player> LoadPlayers() {
		ArrayList<player> list = new ArrayList<player>();
		int[] a = new int[ARRAY_SIZE_PLAYER];

		try {
			// File f = new
			// File("C:/Users/"+System.getProperty("user.name")+"/Documents/Valkyrie");
			// f.mkdirs();
			File f2 = new File("players.csv");
			f2.createNewFile();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			FileInputStream file = new FileInputStream("players.csv");

			Scanner scnr = new Scanner(file);
			scnr.useDelimiter("[,\r\n]+");
			while (scnr.hasNext()) {
				String name = scnr.next();
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
				loadedPlayers = true;
				list.add(new player(name, r, World, Major, Minor, a, earn));
				scnr.nextLine();

			}
			scnr.close();
		} catch (IOException e) {
			System.out.println("Failed to load data from the Players file.");
		}

		return list;

	}

	public static ArrayList<team> LoadTeams() {
		ArrayList<team> list = new ArrayList<team>();
		// int[] a = new int[ARRAY_SIZE_PLAYER];

		try {
			// File f = new
			// File("C:/Users/"+System.getProperty("user.name")+"/Documents/Valkyrie");
			// f.mkdirs();
			File f2 = new File("teams.csv");
			f2.createNewFile();

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			FileInputStream file = new FileInputStream("teams.csv");

			Scanner scnr = new Scanner(file);
			scnr.useDelimiter("[,\r\n]+");
			while (scnr.hasNext()) {
				String name = scnr.next();
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
				String a = scnr.next();
				list.add(new team(name, major, minor, world, earnings, d, p1, p2, p3, p4, p5, a, r1, r2, r3, r4, r5));

				loadedTeams = true;
			}
			scnr.close();
		} catch (IOException e) {
			System.out.println("Failed to load data from the Teams file.");
		}

		return list;

	}

	public static void main(String[] args) {
		main.main(args);

	}
}
