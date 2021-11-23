import java.util.*;
import java.io.*;
import javax.swing.*;
import java.io.IOException;
import java.awt.*;
import javax.swing.plaf.metal.*;

public class simTournament {

	public static void Major(ArrayList<team> teams) {
		double probability;
		double reel;
		ArrayList<team> LBR1 = new ArrayList<team>();
		ArrayList<team> LBR2 = new ArrayList<team>();
		ArrayList<team> LBR3 = new ArrayList<team>();
		ArrayList<team> LBR4 = new ArrayList<team>();
		ArrayList<team> LBR5 = new ArrayList<team>();
		ArrayList<team> LBR6 = new ArrayList<team>();
		ArrayList<team> UBR2 = new ArrayList<team>();
		ArrayList<team> UBR3 = new ArrayList<team>();
		ArrayList<team> SEMI = new ArrayList<team>();
		ArrayList<team> FINAL = new ArrayList<team>();
		team T1;
		team T2;
		for (int i = 8; i > 0; i--) { // first round
			T1 = teams.get((i * 2) - 1);
			T2 = teams.get((i * 2) - 2);
			probability = main.determine(T1, T2);
			reel = Math.random() * 100;
			if (reel < probability) {
				UBR2.add(T1); // first team is winner
				System.out.println(T1.name + " beat " + T2.name + " in Round 1");
				LBR1.add(T2); // second team is loser
			} else {
				UBR2.add(T2); // first team is winner
				System.out.println(T2.name + " beat " + T1.name + " in Round 1");
				LBR1.add(T1); // second team is loser
			}

		}
		for (int i = 4; i > 0; i--) { // lowerbracket Round 1
			T1 = LBR1.get((i * 2) - 1);
			T2 = LBR1.get((i * 2) - 2);
			probability = main.determine(T1, T2);
			reel = Math.random() * 100;
			if (reel < probability) {
				LBR2.add(T1); // first team is winner
				System.out.println(T1.name + " beat " + T2.name + " in LBR1");
				T2.positionInTournament = 13;
				LBR1.remove(T2); // second team is loser
			} else {
				LBR2.add(T2); // first team is winner
				T1.positionInTournament = 13;
				System.out.println(T2.name + " beat " + T1.name + " in LBR1");
				LBR1.remove(T1); // second team is loser
			}

		}
		for (int i = 4; i > 0; i--) { // upperbracket Round 2
			T1 = UBR2.get((i * 2) - 1);
			T2 = UBR2.get((i * 2) - 2);
			probability = main.determine(T1, T2);
			reel = Math.random() * 100;
			if (reel < probability) {
				UBR3.add(T1); // first team is winner
				LBR2.add(T2);
				System.out.println(T1.name + " beat " + T2.name + " in UBR2");

			} else {
				UBR3.add(T2); // first team is winner
				LBR2.add(T1);
				System.out.println(T2.name + " beat " + T1.name + " in UBR2");

			}
		}
		for (int i = 4; i > 0; i--) { // lowerbracket Round 2
			T1 = LBR2.get((i * 2) - 1);
			T2 = LBR2.get((i * 2) - 2);
			probability = main.determine(T1, T2);
			reel = Math.random() * 100;
			if (reel < probability) {
				LBR3.add(T1); // first team is winner
				T2.positionInTournament = 9;
				System.out.println(T1.name + " beat " + T2.name + " in LBR2");
				LBR2.remove(T2); // second team is loser
			} else {
				LBR3.add(T2); // first team is winner
				T1.positionInTournament = 9;
				System.out.println(T2.name + " beat " + T1.name + " in LBR2");
				LBR2.remove(T1); // second team is loser
			}

		}
		for (int i = 2; i > 0; i--) { // upperbracket Round 3
			T1 = UBR3.get((i * 2) - 1);
			T2 = UBR3.get((i * 2) - 2);
			probability = main.determine(T1, T2);
			reel = Math.random() * 100;
			if (reel < probability) {
				SEMI.add(T1); // first team is winner
				LBR4.add(T2);
				System.out.println(T1.name + " beat " + T2.name + " in UBR3");
				SEMI.remove(T2); // second team is loser

			} else {
				SEMI.add(T2); // first team is winner
				LBR4.add(T1);
				System.out.println(T2.name + " beat " + T1.name + " in UBR3");
				SEMI.remove(T1); // second team is loser

			}

		}
		for (int i = 2; i > 0; i--) { // lowerbracket Round 3
			T1 = LBR3.get((i * 2) - 1);
			T2 = LBR3.get((i * 2) - 2);
			probability = main.determine(T1, T1);
			reel = Math.random() * 100;
			if (reel < probability) {
				LBR4.add(T1); // first team is winner
				T2.positionInTournament = 7;
				System.out.println(T1.name + " beat " + T2.name + " in LBR3");
				LBR3.remove(T2); // second team is loser
			} else {
				LBR4.add(T2); // first team is winner
				T1.positionInTournament = 7;
				System.out.println(T2.name + " beat " + T1.name + " in LBR3");
				LBR3.remove(T1); // second team is loser
			}

		}
		for (int i = 2; i > 0; i--) { // lowerbracket Round 4
			T1 = LBR4.get((i * 2) - 1);
			T2 = LBR4.get((i * 2) - 2);
			probability = main.determine(T1, T1);
			reel = Math.random() * 100;
			if (reel < probability) {
				LBR5.add(T1); // first team is winner
				T2.positionInTournament = 5;
				System.out.println(T1.name + " beat " + T2.name + " in LBR4");
				LBR4.remove(T2); // second team is loser
			} else {
				LBR5.add(T2); // first team is winner
				T1.positionInTournament = 5;
				System.out.println(T2.name + " beat " + T1.name + " in LBR4");
				LBR4.remove(T1); // second team is loser
			}

		}
		for (int i = 1; i > 0; i--) { // semi
			T1 = SEMI.get((i * 2) - 1);
			T2 = SEMI.get((i * 2) - 2);
			probability = main.determine(T1, T2);
			reel = Math.random() * 100;
			if (reel < probability) {
				FINAL.add(T1); // first team is winner
				System.out.println(T1.name + " beat " + T2.name + " in SEMI");
				LBR6.add(T2);

			} else {
				FINAL.add(T2); // first team is winner
				LBR6.add(T1);
				System.out.println(T2.name + " beat " + T1.name + " in SEMI");

			}

		}
		for (int i = 1; i > 0; i--) { // lowerbracket Round 5
			T1 = LBR5.get((i * 2) - 1);
			T2 = LBR5.get((i * 2) - 2);
			probability = main.determine(T1, T2);
			reel = Math.random() * 100;
			if (reel < probability) {
				LBR6.add(T1); // first team is winner
				T2.positionInTournament = 4;
				System.out.println(T1.name + " beat " + T2.name + " in LBR5");
				LBR5.remove(T2); // second team is loser
			} else {
				LBR6.add(T2); // first team is winner
				T1.positionInTournament = 4;
				System.out.println(T2.name + " beat " + T1.name + " in LBR5");
				LBR5.remove(T1); // second team is loser
			}

		}
		for (int i = 1; i > 0; i--) { // lowerbracket Round 6
			T1 = LBR6.get((i * 2) - 1);
			T2 = LBR6.get((i * 2) - 2);
			probability = main.determine(T1, T2);
			reel = Math.random() * 100;
			if (reel < probability) {
				FINAL.add(T1); // first team is winner
				T2.positionInTournament = 3;
				System.out.println(T1.name + " beat " + T2.name + " in LBR6");
				LBR6.remove(T2); // second team is loser
			} else {
				FINAL.add(T2); // first team is winner
				T1.positionInTournament = 3;
				System.out.println(T2.name + " beat " + T1.name + " in LBR6");
				LBR6.remove(T1); // second team is loser
			}

		}
		for (int i = 1; i > 0; i--) { // final
			T1 = FINAL.get((i * 2) - 1);
			T2 = FINAL.get((i * 2) - 2);
			probability = main.determine(T1, T2);
			reel = Math.random() * 100;
			if (reel < probability) {
				T1.positionInTournament = 1;
				T2.positionInTournament = 2;
				System.out.println(T1.name + " beat " + T2.name + " in FINAL");

			} else {
				T1.positionInTournament = 2;
				System.out.println(T2.name + " beat " + T1.name + " in FINAL");
				T2.positionInTournament = 1;

			}

		}

		LBR1.clear();
		LBR2.clear();
		LBR3.clear();
		LBR4.clear();
		LBR5.clear();
		LBR6.clear();

		UBR2.clear();
		UBR3.clear();

		SEMI.clear();
		FINAL.clear();
	}

	public static void World(ArrayList<team> teams) {
		double probability;
		double reel;
		ArrayList<team> LBR1 = new ArrayList<team>();
		ArrayList<team> LBR2 = new ArrayList<team>();
		ArrayList<team> LBR3 = new ArrayList<team>();
		ArrayList<team> LBR4 = new ArrayList<team>();
		ArrayList<team> LBR5 = new ArrayList<team>();
		ArrayList<team> LBR6 = new ArrayList<team>();
		ArrayList<team> LBR7 = new ArrayList<team>();
		ArrayList<team> LBR8 = new ArrayList<team>();
		ArrayList<team> UBR2 = new ArrayList<team>();
		ArrayList<team> UBR3 = new ArrayList<team>();
		ArrayList<team> UBR4 = new ArrayList<team>();
		ArrayList<team> SEMI = new ArrayList<team>();
		ArrayList<team> FINAL = new ArrayList<team>();
		team T1;
		team T2;
		if (teams.get(31) != null) {
			for (int i = 16; i > 0; i--) { // first round
				T1 = teams.get((i * 2) - 1);
				T2 = teams.get((i * 2) - 2);
				// System.out.println(i);

				probability = main.determine(T1, T2);
				reel = Math.random() * 100;
				if (reel < probability) {
					UBR2.add(T1); // first team is winner
					LBR1.add(T2); // second team is loser
				} else {
					UBR2.add(T1); // first team is winner
					LBR1.add(T2); // second team is loser
				}

			}
			for (int i = 8; i > 0; i--) { // lowerbracket Round 1
				T1 = LBR1.get((i * 2) - 1);
				T2 = LBR1.get((i * 2) - 2);
				probability = main.determine(T1, T2);
				reel = Math.random() * 100;
				if (reel < probability) {
					LBR2.add(T1); // first team is winner
					T2.positionInTournament = 25;
					LBR1.remove(T2); // second team is loser
				} else {
					LBR2.add(T2); // first team is winner
					T1.positionInTournament = 25;
					LBR1.remove(T1); // second team is loser
				}

			}
			for (int i = 8; i > 0; i--) { // upperbracket Round 2
				T1 = UBR2.get((i * 2) - 1);
				T2 = UBR2.get((i * 2) - 2);
				probability = main.determine(T1, T2);
				reel = Math.random() * 100;
				if (reel < probability) {
					UBR3.add(T1); // first team is winner
					LBR2.add(T2);
					UBR2.remove(T2); // second team is loser

				} else {
					UBR3.add(T2); // first team is winner
					LBR2.add(T1);
					UBR2.remove(T1); // second team is loser

				}

			}
			for (int i = 8; i > 0; i--) { // lowerbracket Round 2
				T1 = LBR2.get((i * 2) - 1);
				T2 = LBR2.get((i * 2) - 2);
				probability = main.determine(T1, T2);
				reel = Math.random() * 100;
				if (reel < probability) {
					LBR3.add(T1); // first team is winner
					T2.positionInTournament = 17;
					LBR2.remove(T2); // second team is loser
				} else {
					LBR3.add(T2); // first team is winner
					T1.positionInTournament = 17;
					LBR2.remove(T1); // second team is loser
				}

			}
			for (int i = 4; i > 0; i--) { // upperbracket Round 3
				T1 = UBR3.get((i * 2) - 1);
				T2 = UBR3.get((i * 2) - 2);
				probability = main.determine(T1, T2);
				reel = Math.random() * 100;
				if (reel < probability) {
					UBR4.add(T1); // first team is winner
					LBR4.add(T2);
					UBR4.remove(T2); // second team is loser

				} else {
					UBR4.add(T2); // first team is winner
					LBR4.add(T1);
					UBR4.remove(T1); // second team is loser

				}

			}
			for (int i = 4; i > 0; i--) { // lowerbracket Round 3
				T1 = LBR3.get((i * 2) - 1);
				T2 = LBR3.get((i * 2) - 2);
				probability = main.determine(T1, T2);
				reel = Math.random() * 100;
				if (reel < probability) {
					LBR4.add(T1); // first team is winner
					T2.positionInTournament = 13;
					LBR3.remove(T2); // second team is loser
				} else {
					LBR4.add(T2); // first team is winner
					T1.positionInTournament = 13;
					LBR3.remove(T1); // second team is loser
				}

			}
			for (int i = 4; i > 0; i--) { // lowerbracket Round 4
				T1 = LBR4.get((i * 2) - 1);
				T2 = LBR4.get((i * 2) - 2);
				probability = main.determine(T1, T2);
				reel = Math.random() * 100;
				if (reel < probability) {
					LBR5.add(T1); // first team is winner
					T2.positionInTournament = 9;
					LBR4.remove(T2); // second team is loser
				} else {
					LBR5.add(T2); // first team is winner
					T1.positionInTournament = 9;
					LBR4.remove(T1); // second team is loser
				}

			}
			for (int i = 2; i > 0; i--) { // upperbracket Round 4
				T1 = UBR4.get((i * 2) - 1);
				T2 = UBR4.get((i * 2) - 2);
				probability = main.determine(T1, T2);
				reel = Math.random() * 100;
				if (reel < probability) {
					SEMI.add(T1); // first team is winner
					LBR6.add(T2);

				} else {
					SEMI.add(T2); // first team is winner
					LBR6.add(T1);

				}

			}
			for (int i = 2; i > 0; i--) { // lowerbracket Round 5
				T1 = LBR5.get((i * 2) - 1);
				T2 = LBR5.get((i * 2) - 2);
				probability = main.determine(T1, T2);
				// System.out.println("[LBR5] The chances of " + T1.name + " beating " + T2.name
				// + " are " + probability);
				reel = Math.random() * 100;
				if (reel < probability) {
					LBR6.add(T1); // first team is winner
					T2.positionInTournament = 7;
					// System.out.println("[LBR5] " + T2.name + " has placed 7-8.");
					LBR5.remove(T2); // second team is loser
				} else {
					LBR6.add(T2); // first team is winner
					T1.positionInTournament = 7;
					// System.out.println("[LBR5] " + T1.name + " has placed 7-8.");
					LBR5.remove(T1); // second team is loser
				}

			}
			for (int i = 2; i > 0; i--) { // lowerbracket Round 6
				T1 = LBR6.get((i * 2) - 1);
				T2 = LBR6.get((i * 2) - 2);
				probability = main.determine(T1, T2);
				// System.out.println("[LBR6] The chances of " + T1.name + " beating " + T2.name
				// + " are " + probability);
				reel = Math.random() * 100;
				if (reel < probability) {
					LBR7.add(T1); // first team is winner
					T2.positionInTournament = 5;
					// System.out.println("[LBR6] " + T2.name + " has placed 5-6.");
					LBR6.remove(T2); // second team is loser
				} else {
					LBR7.add(T2); // first team is winner
					T1.positionInTournament = 5;
					// System.out.println("[LBR6] " + T1.name + " has placed 5-6.");
					LBR6.remove(T1); // second team is loser
				}

			}
			for (int i = 1; i > 0; i--) { // Semi
				T1 = SEMI.get((i * 2) - 1);
				T2 = SEMI.get((i * 2) - 2);
				probability = main.determine(T1, T2);
				// System.out.println("[SEMI] The chances of " + T1.name + " beating " + T2.name
				// + " are " + probability);
				reel = Math.random() * 100;
				if (reel < probability) {
					FINAL.add(T1); // first team is winner
					LBR8.add(T2);

				} else {
					FINAL.add(T2); // first team is winner
					LBR8.add(T1);

				}

			}
			for (int i = 1; i > 0; i--) { // lowerbracket Round 7
				T1 = LBR7.get(0);
				T2 = LBR7.get(1);
				probability = main.determine(T1, T2);
				// System.out.println("[LBR7] The chances of " + T1.name + " beating " + T2.name
				// + " are " + probability);
				reel = Math.random() * 100;
				if (reel < probability) {
					LBR8.add(T1); // first team is winner
					T2.positionInTournament = 4;
					// System.out.println("[LBR7] " + T2.name + " has placed 4th.");
					LBR7.remove(T2); // second team is loser
				} else {
					LBR8.add(T2); // first team is winner
					T1.positionInTournament = 4;
					// System.out.println("[LBR7] " + T1.name + " has placed 4th.");
					LBR7.remove(T1); // second team is loser
				}

			}
			for (int i = 1; i > 0; i--) { // lowerbracket Round 8
				T1 = LBR8.get((i * 2) - 1);
				T2 = LBR8.get((i * 2) - 2);
				probability = main.determine(T1, T2);
				// System.out.println("[LBR8] The chances of " + T1.name + " beating " + T2.name
				// + " are " + probability);
				reel = Math.random() * 100;
				if (reel < probability) {
					FINAL.add(T1); // first team is winner
					T2.positionInTournament = 3;
					// System.out.println("[LBR8] " + T2.name + " has placed 3rd.");
					LBR8.remove(T2); // second team is loser
				} else {
					FINAL.add(T2); // first team is winner
					T1.positionInTournament = 3;
					// System.out.println("[LBR8] " + T1.name + " has placed 3rd.");
					LBR8.remove(T1); // second team is loser
				}

			}
			for (int i = 1; i > 0; i--) { // FINAL
				T1 = FINAL.get((i * 2) - 1);
				T2 = FINAL.get((i * 2) - 2);
				probability = main.determine(T1, T2);
				// System.out.println("[FINAL] The chances of " + T1.name + " beating " +
				// T2.name + " are " + probability);
				reel = Math.random() * 100;
				if (reel < probability) {
					// FINAL.add(LBR1.get((i * 2)+1)); //first team is winner
					T2.positionInTournament = 2;
					T2.dynasty += 0.5;
					// System.out.println("[FINAL] " + T2.name + " has placed 2nd.");
					T1.positionInTournament = 1;
					T1.dynasty += 1;
					// System.out.println("[FINAL] " + T1.name + " has placed 1st.");
				} else {
					// FINAL.add(LBR1.get((i * 2)+1)); //first team is winner
					T1.positionInTournament = 2;
					T1.dynasty += 0.5;
					// System.out.println("[FINAL] " + T1.name + " has placed 2nd.");
					T2.positionInTournament = 1;
					T2.dynasty += 1;
					// System.out.println("[FINAL] " + T2.name + " has placed 1st.");
				}

			}

			LBR1.clear();
			LBR2.clear();
			LBR3.clear();
			LBR4.clear();
			LBR5.clear();
			LBR6.clear();
			LBR7.clear();
			LBR8.clear();
			UBR2.clear();
			UBR3.clear();
			UBR4.clear();
			SEMI.clear();

			FINAL.clear();
		}
	}

	public static void Minor8(ArrayList<team> teams) {
		double probability;
		double reel;
		ArrayList<team> LBR1 = new ArrayList<team>();
		ArrayList<team> LBR2 = new ArrayList<team>();
		ArrayList<team> LBR3 = new ArrayList<team>();
		ArrayList<team> LBR4 = new ArrayList<team>();
		ArrayList<team> UBR2 = new ArrayList<team>();
		ArrayList<team> SEMI = new ArrayList<team>();
		ArrayList<team> FINAL = new ArrayList<team>();
		team T1;
		team T2;
		System.out.println(teams.size());
		System.out.println("There are " + teams.size() + " teams in ROUND 1");
		for (team x : teams)
			System.out.println("[ROUND 1] " + x.name);

		for (int i = 4; i > 0; i--) { // first round
			T1 = teams.get((i * 2) - 1);
			T2 = teams.get((i * 2) - 2);
			probability = main.determine(T1, T2);
			reel = Math.random() * 100;
			if (reel < probability) {
				UBR2.add(T1); // first team is winner
				System.out.println(T1.name + " won first round against " + T2.name);
				LBR1.add(T2); // second team is loser
				System.out.println(T2.name + " lost first round against " + T1.name);
			} else {
				UBR2.add(T2); // first team is winner
				System.out.println(T2.name + " won first round against " + T1.name);
				LBR1.add(T1); // second team is loser
				System.out.println(T1.name + " lost first round against " + T2.name);
			}

		}
		for (int i = 2; i > 0; i--) { // lowerbracket Round 1
			System.out.println("There are " + LBR1.size() + " teams in LBR1");
			for (team x : LBR1)
				System.out.println("[LBR1] " + x.name);
			T1 = LBR1.get((i * 2) - 1);
			T2 = LBR1.get((i * 2) - 2);
			probability = main.determine(T1, T2);
			reel = Math.random() * 100;
			if (reel < probability) {
				LBR2.add(T1); // first team is winner
				T2.positionInTournament = 7;
				System.out.println(T2.name + " placed 7th");
				LBR1.remove(T2); // second team is loser
			} else {
				LBR2.add(T2); // first team is winner
				T1.positionInTournament = 7;
				System.out.println(T1.name + " placed 7th");
				LBR1.remove(T1); // second team is loser
			}

		}
		for (int i = 2; i > 0; i--) { // upperbracket Round 2
			System.out.println("There are " + UBR2.size() + " teams in UBR2");
			for (team x : UBR2)
				System.out.println("[UBR2] " + x.name);
			T1 = UBR2.get((i * 2) - 1);
			T2 = UBR2.get((i * 2) - 2);
			probability = main.determine(T1, T2);
			reel = Math.random() * 100;
			if (reel < probability) {
				SEMI.add(T1); // first team is winner
				LBR2.add(T2);
				SEMI.remove(T2); // second team is loser

			} else {
				SEMI.add(T2); // first team is winner
				LBR2.add(T1);
				SEMI.remove(T1); // second team is loser

			}

		}
		for (int i = 2; i > 0; i--) { // lowerbracket Round 2
			System.out.println("There are " + LBR2.size() + " teams in LBR2");
			for (team x : LBR2)
				System.out.println("[LBR2] " + x.name);
			T1 = LBR2.get((i * 2) - 1);
			T2 = LBR2.get((i * 2) - 2);
			probability = main.determine(T1, T2);
			reel = Math.random() * 100;
			if (reel < probability) {
				LBR3.add(T1); // first team is winner
				T2.positionInTournament = 5;
				System.out.println(T2.name + " placed 5th");
				LBR2.remove(T2); // second team is loser
			} else {
				LBR3.add(T2); // first team is winner
				T1.positionInTournament = 5;
				System.out.println(T1.name + " placed 5th");
				LBR2.remove(T1); // second team is loser
			}

		}
		for (int i = 1; i > 0; i--) { // semi
			System.out.println("There are " + SEMI.size() + " teams in SEMI");
			for (team x : SEMI)
				System.out.println("[SEMI] " + x.name);
			T1 = SEMI.get((i * 2) - 1);
			T2 = SEMI.get((i * 2) - 2);
			probability = main.determine(T1, T2);
			reel = Math.random() * 100;
			if (reel < probability) {
				FINAL.add(T1); // first team is winner
				LBR4.add(T2);
				System.out.println(T2.name + " has been sent to the lower bracket");
				SEMI.remove(T2); // second team is loser

			} else {
				FINAL.add(T2); // first team is winner
				LBR4.add(T1);
				System.out.println(T1.name + " has been sent to the lower bracket");
				SEMI.remove(T1); // second team is loser

			}

		}
		for (int i = 1; i > 0; i--) { // lowerbracket Round 3
			System.out.println("There are " + LBR3.size() + " teams in LBR3");
			for (team x : LBR3)
				System.out.println("[LBR3] " + x.name);
			T1 = LBR3.get((i * 2) - 1);
			System.out.println(T1.name);
			T2 = LBR3.get((i * 2) - 2);
			System.out.println(T2.name);
			probability = main.determine(T1, T2);
			reel = Math.random() * 100;
			if (reel < probability) {
				LBR4.add(T1); // first team is winner
				System.out.println(T1.name + " has moved foward to LBR4");
				T2.positionInTournament = 4;
				System.out.println(T2.name + " placed 4th");
				LBR3.remove(T2); // second team is loser
			} else {
				LBR4.add(T2); // first team is winner
				System.out.println(T2.name + " has moved foward to LBR4");
				T1.positionInTournament = 4;

				System.out.println(T1.name + " placed 4th");
				LBR3.remove(T1); // second team is loser
			}

		}
		for (int i = 1; i > 0; i--) { // lowerbracket Round 4
			System.out.println("There are " + LBR4.size() + " teams in LBR4");
			for (team x : LBR4)
				System.out.println("[LBR4] " + x.name);
			T1 = LBR4.get((i * 2) - 1);
			T2 = LBR4.get((i * 2) - 2);
			probability = main.determine(T1, T2);
			reel = Math.random() * 100;
			if (reel < probability) {
				FINAL.add(T1); // first team is winner
				T2.positionInTournament = 3;
				System.out.println(T2.name + " placed 3rd");
				LBR4.remove(T2); // second team is loser

			} else {
				FINAL.add(T2); // first team is winner
				T1.positionInTournament = 3;
				System.out.println(T1.name + " placed 3rd");
				LBR4.remove(T1); // second team is loser

			}

		}
		for (int i = 1; i > 0; i--) { // final
			System.out.println("There are " + FINAL.size() + " teams in FINAL");
			for (team x : FINAL)
				System.out.println("[FINAL] " + x.name);
			T1 = FINAL.get((i * 2) - 1);
			T2 = FINAL.get((i * 2) - 2);
			probability = main.determine(T1, T2);
			reel = Math.random() * 100;
			if (reel < probability) {
				T1.positionInTournament = 1;
				System.out.println(T1.name + " placed 1st");

				T2.positionInTournament = 2;
				System.out.println(T2.name + " placed 2nd");

			} else {
				T2.positionInTournament = 1;
				System.out.println(T2.name + " placed 1st");

				T1.positionInTournament = 2;
				System.out.println(T1.name + " placed 2nd");

			}

		}

		LBR1.clear();
		LBR2.clear();
		LBR3.clear();
		LBR4.clear();
		UBR2.clear();
		SEMI.clear();
		FINAL.clear();

	}

	public static void Minor4(ArrayList<team> teams) {
		double probability;
		double reel;
		ArrayList<team> LBR1 = new ArrayList<team>();
		ArrayList<team> LBR2 = new ArrayList<team>();
		ArrayList<team> LBR3 = new ArrayList<team>();
		ArrayList<team> LBR4 = new ArrayList<team>();
		ArrayList<team> UBR2 = new ArrayList<team>();
		ArrayList<team> SEMI = new ArrayList<team>();
		ArrayList<team> FINAL = new ArrayList<team>();
		team T1;
		team T2;
		for (int i = 2; i > 0; i--) { // first round
			T1 = teams.get((i * 2) - 1);
			T2 = teams.get((i * 2) - 2);
			probability = main.determine(T1, T2);
			reel = Math.random() * 100;
			// System.out.println("[ROUND 1] Probability for " + T1.name + " to beat " +
			// T2.name + " is: " + probability);
			// System.out.println("[ROUND 1] The number generated was: " + reel);
			if (reel < probability) {
				SEMI.add(T1); // first team is winner
				LBR1.add(T2); // second team is loser
			} else {
				SEMI.add(T2); // first team is winner
				LBR1.add(T1); // second team is loser
			}

		}
		for (int i = 1; i > 0; i--) { // semi
			T1 = SEMI.get((i * 2) - 1);
			T2 = SEMI.get((i * 2) - 2);
			probability = main.determine(T1, T2);
			reel = Math.random() * 100;
			// System.out.println("[SEMI] Probability for " + T1.name + " to beat " +
			// T2.name + " is: " + probability);
			// System.out.println("[SEMI] The number generated was: " + reel);
			if (reel < probability) {
				FINAL.add(T1); // first team is winner
				LBR2.add(T2);

			} else {
				FINAL.add(T2); // first team is winner
				LBR2.add(T1);

			}

		}
		for (int i = 1; i > 0; i--) { // lowerbracket Round 1
			T1 = LBR1.get((i * 2) - 1);
			T2 = LBR1.get((i * 2) - 2);
			probability = main.determine(T1, T2);
			reel = Math.random() * 100;
			// System.out.println("[LBR1] Probability for " + T1.name + " to beat " +
			// T2.name + " is: " + probability);
			// System.out.println("[LBR1] The number generated was: " + reel);
			if (reel < probability) {
				LBR2.add(T1); // first team is winner
				T2.positionInTournament = 4;
				// System.out.println(T2.name + " placed 4th");
				LBR1.remove(T2); // second team is loser
			} else {
				LBR2.add(T2); // first team is winner
				T1.positionInTournament = 4;
				// System.out.println(T1.name + " placed 4th");
				LBR1.remove(T1); // second team is loser
			}

		}
		for (int i = 1; i > 0; i--) { // lowerbracket Round2
			T1 = LBR2.get((i * 2) - 1);
			T2 = LBR2.get((i * 2) - 2);
			probability = main.determine(T1, T2);
			reel = Math.random() * 100;
			// System.out.println("[LBR2] Probability for " + T1.name + " to beat " +
			// T2.name + " is: " + probability);
			// System.out.println("[LBR2] The number generated was: " + reel);
			if (reel < probability) {
				FINAL.add(T1); // first team is winner
				T2.positionInTournament = 3;
				// System.out.println(T2.name + " placed 3rd");
				LBR2.remove(T2); // second team is loser

			} else {
				FINAL.add(T2); // first team is winner
				T1.positionInTournament = 3;
				// System.out.println(T1.name + " placed 3rd");
				LBR2.remove(T1); // second team is loser

			}

		}
		for (int i = 1; i > 0; i--) { // final
			T1 = FINAL.get((i * 2) - 1);
			T2 = FINAL.get((i * 2) - 2);
			probability = main.determine(T1, T2);

			reel = Math.random() * 100;

			// System.out.println("[FINAL] Probability for " + T1.name + " to beat " +
			// T2.name + " is: " + probability);
			// System.out.println("[FINAL] The number generated was: " + reel);
			if (reel < probability) {
				T2.positionInTournament = 2;
				// System.out.println(T2.name + " placed 2nd");
				T1.positionInTournament = 1;
				// System.out.println(T1.name + " placed 1st");

			} else {
				T1.positionInTournament = 2;
				// System.out.println(T1.name + " placed 2nd");
				T2.positionInTournament = 1;
				// System.out.println(T2.name + " placed 1st");

			}

		}

		LBR1.clear();
		LBR2.clear();
		LBR3.clear();
		LBR4.clear();

		UBR2.clear();

		SEMI.clear();
		FINAL.clear();
	}
}
