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

	public static ArrayList<player> UpdatePlayers(ArrayList<player> p) {
		ArrayList<player> list = new ArrayList<player>();
		int[] a = new int[ARRAY_SIZE_PLAYER];

		try {
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
				String org = scnr.next();
				int role = scnr.nextInt();
				int WorldTitles = scnr.nextInt();
				int MajorTitles = scnr.nextInt();
				int MinorTitles = scnr.nextInt();
				for (int i = 0; i < ARRAY_SIZE_PLAYER; i++) {
					a[i] = scnr.nextInt();
					if (i == ARRAY_SIZE_PLAYER - 1)
						scnr.nextLine();
				}
				for (player dfs : p) {
					if (name.equals(dfs.name))
						list.add(new player(name));
				}

			}
			scnr.close();
		} catch (IOException e) {
		}
		return list;
	}

	public static ArrayList<team> UpdateTeams(ArrayList<team> t) {
		ArrayList<team> list = new ArrayList<team>();

		try {
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

				for (team dfs : t) {
					if (name.equals(dfs.name))
						list.add(new team(name, major, minor, world, earnings));
				}

			}
			scnr.close();
		} catch (IOException e) {
		}
		return list;
	}

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
			System.out.println("Failed to open the file. Could not save.");
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
			System.out.println("Failed to open the file. Could not save.");
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
					if (i == ARRAY_SIZE_PLAYER - 1)
						scnr.nextLine();
				}
				list.add(new player(name, r, World, Major, Minor, a));

			}
			scnr.close();
		} catch (IOException e) {
			System.out.println("Failed to load data from the file.");
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

				list.add(new team(name, major, minor, world, earnings));

			}
			scnr.close();
		} catch (IOException e) {
			System.out.println("Failed to load data from the file.");
		}

		return list;

	}

	public static void main(String[] args) {
		main.main(args);

	}
}
