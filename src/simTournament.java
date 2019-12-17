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
		for (int i = 8; i > 0; i--) { // first round
			probability = main.determine(teams.get((i * 2) - 2).getTeamStrength(),
					teams.get((i * 2) - 1).getTeamStrength());
			reel = Math.random() * 100;
			if (reel < probability) {
				UBR2.add(teams.get((i * 2) - 1)); // first team is winner
				LBR1.add(teams.get((i * 2) - 2)); // second team is loser
			} else {
				UBR2.add(teams.get((i * 2) - 2)); // first team is winner
				LBR1.add(teams.get((i * 2) - 1)); // second team is loser
			}

		}
		for (int i = 4; i > 0; i--) { // lowerbracket Round 1
			probability = main.determine(LBR1.get((i * 2) - 2).getTeamStrength(),
					LBR1.get((i * 2) - 1).getTeamStrength());
			reel = Math.random() * 100;
			if (reel < probability) {
				LBR2.add(LBR1.get((i * 2) - 1)); // first team is winner
				LBR1.get((i * 2) - 2).positionInTournament = 13;
				LBR1.remove(LBR1.get((i * 2) - 2)); // second team is loser
			} else {
				LBR2.add(LBR1.get((i * 2) - 2)); // first team is winner
				LBR1.get((i * 2) - 1).positionInTournament = 13;
				LBR1.remove(LBR1.get((i * 2) - 1)); // second team is loser
			}

		}
		for (int i = 4; i > 0; i--) { // upperbracket Round 2
			probability = main.determine(UBR2.get((i * 2) - 2).getTeamStrength(),
					UBR2.get((i * 2) - 1).getTeamStrength());
			reel = Math.random() * 100;
			if (reel < probability) {
				UBR3.add(UBR2.get((i * 2) - 1)); // first team is winner
				LBR4.add(UBR2.get((i * 2) - 2));

			} else {
				UBR3.add(UBR2.get((i * 2) - 2)); // first team is winner
				LBR4.add(UBR2.get((i * 2) - 1));

			}
		}
		for (int i = 4; i > 0; i--) { // lowerbracket Round 2
			probability = main.determine(LBR2.get((i * 2) - 2).getTeamStrength(),
					LBR2.get((i * 2) - 1).getTeamStrength());
			reel = Math.random() * 100;
			if (reel < probability) {
				LBR3.add(LBR2.get((i * 2) - 1)); // first team is winner
				LBR2.get((i * 2) - 2).positionInTournament = 9;
				LBR2.remove(LBR2.get((i * 2) - 2)); // second team is loser
			} else {
				LBR3.add(LBR2.get((i * 2) - 2)); // first team is winner
				LBR2.get((i * 2) - 1).positionInTournament = 9;
				LBR2.remove(LBR2.get((i * 2) - 1)); // second team is loser
			}

		}
		for (int i = 2; i > 0; i--) { // upperbracket Round 3
			probability = main.determine(UBR3.get((i * 2) - 2).getTeamStrength(),
					UBR3.get((i * 2) - 1).getTeamStrength());
			reel = Math.random() * 100;
			if (reel < probability) {
				SEMI.add(SEMI.get((i * 2) - 1)); // first team is winner
				LBR4.add(SEMI.get((i * 2) - 2));
				SEMI.remove(SEMI.get((i * 2) - 2)); // second team is loser

			} else {
				SEMI.add(SEMI.get((i * 2) - 2)); // first team is winner
				LBR4.add(SEMI.get((i * 2) - 1));
				SEMI.remove(SEMI.get((i * 2) - 1)); // second team is loser

			}

		}
		for (int i = 2; i > 0; i--) { // lowerbracket Round 3
			probability = main.determine(LBR3.get((i * 2) - 2).getTeamStrength(),
					LBR3.get((i * 2) - 1).getTeamStrength());
			reel = Math.random() * 100;
			if (reel < probability) {
				LBR4.add(LBR3.get((i * 2) - 1)); // first team is winner
				LBR3.get((i * 2) - 2).positionInTournament = 7;
				LBR3.remove(LBR3.get((i * 2) - 2)); // second team is loser
			} else {
				LBR4.add(LBR3.get((i * 2) - 2)); // first team is winner
				LBR3.get((i * 2) - 1).positionInTournament = 7;
				LBR3.remove(LBR3.get((i * 2) - 1)); // second team is loser
			}

		}
		for (int i = 2; i > 0; i--) { // lowerbracket Round 4
			probability = main.determine(LBR4.get((i * 2) - 2).getTeamStrength(),
					LBR4.get((i * 2) - 1).getTeamStrength());
			reel = Math.random() * 100;
			if (reel < probability) {
				LBR5.add(LBR4.get((i * 2) - 1)); // first team is winner
				LBR4.get((i * 2) - 2).positionInTournament = 5;
				LBR4.remove(LBR4.get((i * 2) - 2)); // second team is loser
			} else {
				LBR5.add(LBR4.get((i * 2) - 2)); // first team is winner
				LBR4.get((i * 2) - 1).positionInTournament = 5;
				LBR4.remove(LBR4.get((i * 2) - 1)); // second team is loser
			}

		}
		for (int i = 1; i > 0; i--) { // semi
			probability = main.determine(SEMI.get(((i * 2) - 2)).getTeamStrength(),
					SEMI.get((i * 2) - 1).getTeamStrength());
			reel = Math.random() * 100;
			if (reel < probability) {
				FINAL.add(SEMI.get((i * 2) - 1)); // first team is winner
				LBR6.add(SEMI.get((i * 2) - 2));

			} else {
				FINAL.add(SEMI.get((i * 2) - 2)); // first team is winner
				LBR6.add(SEMI.get((i * 2) - 1));

			}

		}
		for (int i = 1; i > 0; i--) { // lowerbracket Round 5
			probability = main.determine(LBR5.get((i * 2) - 2).getTeamStrength(),
					LBR5.get((i * 2) - 1).getTeamStrength());
			reel = Math.random() * 100;
			if (reel < probability) {
				LBR6.add(LBR5.get((i * 2) - 1)); // first team is winner
				LBR5.get((i * 2) - 2).positionInTournament = 4;
				LBR5.remove(LBR5.get((i * 2) - 2)); // second team is loser
			} else {
				LBR6.add(LBR5.get((i * 2) - 2)); // first team is winner
				LBR5.get((i * 2) - 1).positionInTournament = 4;
				LBR5.remove(LBR5.get((i * 2) - 1)); // second team is loser
			}

		}
		for (int i = 1; i > 0; i--) { // lowerbracket Round 6
			probability = main.determine(LBR6.get((0)).getTeamStrength(), LBR6.get(1).getTeamStrength());
			reel = Math.random() * 100;
			if (reel < probability) {
				FINAL.add(LBR6.get((i * 2) - 1)); // first team is winner
				LBR6.get((i * 2) - 2).positionInTournament = 3;
				LBR6.remove(LBR6.get((i * 2) - 2)); // second team is loser
			} else {
				FINAL.add(LBR6.get((i * 2) - 2)); // first team is winner
				LBR6.get((i * 2) - 1).positionInTournament = 3;
				LBR6.remove(LBR6.get((i * 2) - 1)); // second team is loser
			}

		}
		for (int i = 1; i > 0; i--) { // final
			probability = main.determine(FINAL.get((0)).getTeamStrength(), FINAL.get((1)).getTeamStrength());
			reel = Math.random() * 100;
			if (reel < probability) {
				FINAL.get(1).positionInTournament = 2;
				FINAL.get(0).positionInTournament = 1;

			} else {
				FINAL.get(0).positionInTournament = 2;
				FINAL.get(1).positionInTournament = 1;

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
		if (teams.get(31) != null) {
			for (int i = 16; i > 0; i--) { // first round
				System.out.println(i);

				probability = main.determine(teams.get((i * 2) - 2).getTeamStrength(),
						teams.get((i * 2) - 1).getTeamStrength());
				reel = Math.random() * 100;
				if (reel < probability) {
					UBR2.add(teams.get((i * 2) - 1)); // first team is winner
					LBR1.add(teams.get((i * 2) - 2)); // second team is loser
				} else {
					UBR2.add(teams.get((i * 2) - 2)); // first team is winner
					LBR1.add(teams.get((i * 2) - 1)); // second team is loser
				}

			}
			for (int i = 8; i > 0; i--) { // lowerbracket Round 1
				probability = main.determine(LBR1.get((i * 2) - 2).getTeamStrength(),
						LBR1.get((i * 2) - 1).getTeamStrength());
				reel = Math.random() * 100;
				if (reel < probability) {
					LBR2.add(LBR1.get((i * 2) - 1)); // first team is winner
					LBR1.get((i * 2) - 2).positionInTournament = 24;
					LBR1.remove(LBR1.get((i * 2) - 2)); // second team is loser
				} else {
					LBR2.add(LBR1.get((i * 2) - 2)); // first team is winner
					LBR1.get((i * 2) - 1).positionInTournament = 24;
					LBR1.remove(LBR1.get((i * 2) - 1)); // second team is loser
				}

			}
			for (int i = 8; i > 0; i--) { // upperbracket Round 2
				probability = main.determine(UBR2.get((i * 2) - 2).getTeamStrength(),
						UBR2.get((i * 2) - 1).getTeamStrength());
				reel = Math.random() * 100;
				if (reel < probability) {
					UBR3.add(UBR2.get((i * 2) - 1)); // first team is winner
					LBR2.add(UBR2.get((i * 2) - 2));
					UBR2.remove(UBR2.get((i * 2) - 2)); // second team is loser

				} else {
					UBR3.add(UBR2.get((i * 2) - 2)); // first team is winner
					LBR2.add(UBR2.get((i * 2) - 1));
					UBR2.remove(UBR2.get((i * 2) - 1)); // second team is loser

				}

			}
			for (int i = 8; i > 0; i--) { // lowerbracket Round 2
				probability = main.determine(LBR2.get((i * 2) - 2).getTeamStrength(),
						LBR2.get((i * 2) - 1).getTeamStrength());
				reel = Math.random() * 100;
				if (reel < probability) {
					LBR3.add(LBR2.get((i * 2) - 1)); // first team is winner
					LBR2.get((i * 2) - 2).positionInTournament = 17;
					LBR2.remove(LBR2.get((i * 2) - 2)); // second team is loser
				} else {
					LBR3.add(LBR2.get((i * 2) - 2)); // first team is winner
					LBR2.get((i * 2) - 1).positionInTournament = 17;
					LBR2.remove(LBR2.get((i * 2) - 1)); // second team is loser
				}

			}
			for (int i = 4; i > 0; i--) { // upperbracket Round 3
				probability = main.determine(UBR3.get((i * 2) - 2).getTeamStrength(),
						UBR3.get((i * 2) - 1).getTeamStrength());
				reel = Math.random() * 100;
				if (reel < probability) {
					UBR4.add(UBR3.get((i * 2) - 1)); // first team is winner
					LBR4.add(UBR3.get((i * 2) - 2));
					UBR4.remove(UBR3.get((i * 2) - 2)); // second team is loser

				} else {
					UBR4.add(UBR3.get((i * 2) - 2)); // first team is winner
					LBR4.add(UBR3.get((i * 2) - 1));
					UBR4.remove(UBR3.get((i * 2) - 1)); // second team is loser

				}

			}
			for (int i = 4; i > 0; i--) { // lowerbracket Round 3
				probability = main.determine(LBR3.get((i * 2) - 2).getTeamStrength(),
						LBR3.get((i * 2) - 1).getTeamStrength());
				reel = Math.random() * 100;
				if (reel < probability) {
					LBR4.add(LBR3.get((i * 2) - 1)); // first team is winner
					LBR3.get((i * 2) - 2).positionInTournament = 13;
					LBR3.remove(LBR3.get((i * 2) - 2)); // second team is loser
				} else {
					LBR4.add(LBR3.get((i * 2) - 2)); // first team is winner
					LBR3.get((i * 2) - 1).positionInTournament = 13;
					LBR3.remove(LBR3.get((i * 2) - 1)); // second team is loser
				}

			}
			for (int i = 4; i > 0; i--) { // lowerbracket Round 4
				probability = main.determine(LBR4.get((i * 2) - 2).getTeamStrength(),
						LBR4.get((i * 2) - 1).getTeamStrength());
				reel = Math.random() * 100;
				if (reel < probability) {
					LBR5.add(LBR4.get((i * 2) - 1)); // first team is winner
					LBR4.get((i * 2) - 2).positionInTournament = 9;
					LBR4.remove(LBR4.get((i * 2) - 2)); // second team is loser
				} else {
					LBR5.add(LBR4.get((i * 2) - 2)); // first team is winner
					LBR4.get((i * 2) - 1).positionInTournament = 9;
					LBR4.remove(LBR4.get((i * 2) - 1)); // second team is loser
				}

			}
			for (int i = 2; i > 0; i--) { // upperbracket Round 4
				probability = main.determine(UBR4.get((i * 2) - 2).getTeamStrength(),
						UBR4.get((i * 2) - 1).getTeamStrength());
				reel = Math.random() * 100;
				if (reel < probability) {
					SEMI.add(UBR3.get((i * 2) - 1)); // first team is winner
					LBR6.add(teams.get((i * 2) - 2));

				} else {
					SEMI.add(UBR3.get((i * 2) - 2)); // first team is winner
					LBR6.add(teams.get((i * 2) - 1));

				}

			}
			for (int i = 2; i > 0; i--) { // lowerbracket Round 5
				probability = main.determine(LBR5.get((i * 2) - 2).getTeamStrength(),
						LBR5.get((i * 2) - 1).getTeamStrength());
				reel = Math.random() * 100;
				if (reel < probability) {
					LBR6.add(LBR5.get((i * 2) - 1)); // first team is winner
					LBR5.get((i * 2) - 2).positionInTournament = 7;
					LBR5.remove(LBR5.get((i * 2) - 2)); // second team is loser
				} else {
					LBR6.add(LBR5.get((i * 2) - 2)); // first team is winner
					LBR5.get((i * 2) - 1).positionInTournament = 7;
					LBR5.remove(LBR5.get((i * 2) - 1)); // second team is loser
				}

			}
			for (int i = 2; i > 0; i--) { // lowerbracket Round 6
				probability = main.determine(LBR6.get((i * 2) - 2).getTeamStrength(),
						LBR6.get((i * 2) - 1).getTeamStrength());
				reel = Math.random() * 100;
				if (reel < probability) {
					LBR7.add(LBR6.get((i * 2) - 1)); // first team is winner
					LBR6.get((i * 2) - 2).positionInTournament = 5;
					LBR6.remove(LBR6.get((i * 2) - 2)); // second team is loser
				} else {
					LBR7.add(LBR6.get((i * 2) - 2)); // first team is winner
					LBR6.get((i * 2) - 1).positionInTournament = 5;
					LBR6.remove(LBR6.get((i * 2) - 1)); // second team is loser
				}

			}
			for (int i = 1; i > 0; i--) { // Semi
				probability = main.determine(SEMI.get((i * 2) - 2).getTeamStrength(),
						SEMI.get((i * 2) - 1).getTeamStrength());
				reel = Math.random() * 100;
				if (reel < probability) {
					FINAL.add(SEMI.get((i * 2) - 1)); // first team is winner
					LBR8.add(SEMI.get((i * 2) - 2));

				} else {
					FINAL.add(SEMI.get((i * 2) - 2)); // first team is winner
					LBR8.add(SEMI.get((i * 2) - 1));

				}

			}
			for (int i = 1; i > 0; i--) { // lowerbracket Round 7
				probability = main.determine(LBR7.get((i * 2) - 2).getTeamStrength(),
						LBR7.get((i * 2) - 1).getTeamStrength());
				reel = Math.random() * 100;
				if (reel < probability) {
					LBR8.add(LBR7.get((i * 2) - 1)); // first team is winner
					LBR7.get((i * 2) - 2).positionInTournament = 4;
					LBR7.remove(LBR7.get((i * 2) - 2)); // second team is loser
				} else {
					LBR8.add(LBR7.get((i * 2) - 2)); // first team is winner
					LBR7.get((i * 2) - 1).positionInTournament = 4;
					LBR7.remove(LBR7.get((i * 2) - 1)); // second team is loser
				}

			}
			for (int i = 1; i > 0; i--) { // lowerbracket Round 8
				probability = main.determine(LBR8.get((i * 2) - 2).getTeamStrength(),
						LBR8.get((i * 2) - 1).getTeamStrength());
				reel = Math.random() * 100;
				if (reel < probability) {
					FINAL.add(LBR8.get((i * 2) - 1)); // first team is winner
					LBR8.get((i * 2) - 2).positionInTournament = 3;
					LBR8.remove(LBR8.get((i * 2) - 2)); // second team is loser
				} else {
					FINAL.add(LBR8.get((i * 2) - 2)); // first team is winner
					LBR8.get((i * 2) - 1).positionInTournament = 3;
					LBR8.remove(LBR8.get((i * 2) - 1)); // second team is loser
				}

			}
			for (int i = 1; i > 0; i--) { // FINAL
				probability = main.determine(FINAL.get((i * 2) - 2).getTeamStrength(),
						FINAL.get((i * 2) - 1).getTeamStrength());
				reel = Math.random() * 100;
				if (reel < probability) {
					// FINAL.add(LBR1.get((i * 2)+1)); //first team is winner
					FINAL.get(1).positionInTournament = 2;
					FINAL.get(0).positionInTournament = 1;
				} else {
					// FINAL.add(LBR1.get((i * 2)+1)); //first team is winner
					FINAL.get(0).positionInTournament = 2;
					FINAL.get(1).positionInTournament = 1;
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
		for (int i = 4; i > 0; i--) { // first round
			probability = main.determine(teams.get((i * 2) - 2).getTeamStrength(),
					teams.get((i * 2) - 1).getTeamStrength());
			reel = Math.random() * 100;
			if (reel < probability) {
				UBR2.add(teams.get((i * 2) - 2)); // first team is winner
				LBR1.add(teams.get((i * 2) - 1)); // second team is loser
			} else {
				UBR2.add(teams.get((i * 2) - 1)); // first team is winner
				LBR1.add(teams.get((i * 2) - 2)); // second team is loser
			}

		}
		for (int i = 1; i > 0; i--) { // lowerbracket Round 1
			probability = main.determine(LBR1.get((i * 2) - 2).getTeamStrength(),
					LBR1.get((i * 2) - 1).getTeamStrength());
			reel = Math.random() * 100;
			if (reel < probability) {
				LBR2.add(LBR1.get((i * 2) - 1)); // first team is winner
				LBR1.get((i * 2) - 2).positionInTournament = 7;
				LBR1.remove(LBR1.get((i * 2) - 2)); // second team is loser
			} else {
				LBR2.add(LBR1.get((i * 2) - 2)); // first team is winner
				LBR1.get((i * 2) - 1).positionInTournament = 7;
				LBR1.remove(LBR1.get((i * 2) - 1)); // second team is loser
			}

		}
		for (int i = 2; i > 0; i--) { // upperbracket Round 2
			probability = main.determine(UBR2.get((i * 2) - 2).getTeamStrength(),
					UBR2.get((i * 2) - 1).getTeamStrength());
			reel = Math.random() * 100;
			if (reel < probability) {
				SEMI.add(UBR2.get((i * 2) - 1)); // first team is winner
				UBR2.add(UBR2.get((i * 2) - 2));
				UBR2.remove(UBR2.get((i * 2) - 2)); // second team is loser

			} else {
				SEMI.add(UBR2.get((i * 2) - 2)); // first team is winner
				UBR2.add(UBR2.get((i * 2) - 1));
				UBR2.remove(UBR2.get((i * 2) - 1)); // second team is loser

			}

		}
		for (int i = 2; i > 0; i--) { // lowerbracket Round 2
			probability = main.determine(LBR2.get((i * 2) - 2).getTeamStrength(),
					LBR2.get((i * 2) - 1).getTeamStrength());
			reel = Math.random() * 100;
			if (reel < probability) {
				LBR3.add(LBR2.get((i * 2) - 1)); // first team is winner
				LBR2.get((i * 2) - 2).positionInTournament = 5;
				LBR2.remove(LBR2.get((i * 2) - 2)); // second team is loser
			} else {
				LBR3.add(LBR2.get((i * 2) - 2)); // first team is winner
				LBR2.get((i * 2) - 1).positionInTournament = 5;
				LBR2.remove(LBR2.get((i * 2) - 1)); // second team is loser
			}

		}
		for (int i = 1; i > 0; i--) { // semi
			probability = main.determine(SEMI.get((i * 2) - 2).getTeamStrength(),
					SEMI.get((i * 2) - 1).getTeamStrength());
			reel = Math.random() * 100;
			if (reel < probability) {
				FINAL.add(SEMI.get((i * 2) - 1)); // first team is winner
				LBR4.add(SEMI.get((i * 2) - 2));
				SEMI.remove(SEMI.get((i * 2) - 2)); // second team is loser

			} else {
				FINAL.add(SEMI.get((i * 2) - 2)); // first team is winner
				LBR4.add(SEMI.get((i * 2) - 1));
				SEMI.remove(SEMI.get((i * 2) - 1)); // second team is loser

			}

		}
		for (int i = 1; i > 0; i--) { // lowerbracket Round 3
			probability = main.determine(LBR3.get((i * 2) - 2).getTeamStrength(),
					LBR3.get((i * 2) - 1).getTeamStrength());
			reel = Math.random() * 100;
			if (reel < probability) {
				LBR4.add(LBR3.get((i * 2) - 1)); // first team is winner
				LBR3.get((i * 2) - 2).positionInTournament = 4;
				LBR3.remove(LBR3.get((i * 2) - 2)); // second team is loser
			}
			if (reel < probability) {
				LBR4.add(LBR3.get((i * 2) - 2)); // first team is winner
				LBR3.get((i * 2) - 1).positionInTournament = 4;
				LBR3.remove(LBR3.get((i * 2) - 1)); // second team is loser
			}

		}
		for (int i = 1; i > 0; i--) { // lowerbracket Round 4
			probability = main.determine(LBR4.get((i * 2) - 2).getTeamStrength(),
					LBR4.get((i * 2) - 1).getTeamStrength());
			reel = Math.random() * 100;
			if (reel < probability) {
				FINAL.add(LBR4.get((i * 2) - 1)); // first team is winner
				LBR4.get((i * 2) - 2).positionInTournament = 3;
				LBR4.remove(LBR4.get((i * 2) - 1)); // second team is loser

			} else {
				FINAL.add(LBR4.get((i * 2) - 2)); // first team is winner
				LBR4.get((i * 2) - 1).positionInTournament = 3;
				LBR4.remove(LBR4.get((i * 2) - 2)); // second team is loser

			}

		}
		for (int i = 1; i > 0; i--) { // final
			probability = main.determine(SEMI.get((i * 2) - 2).getTeamStrength(),
					SEMI.get((i * 2) - 1).getTeamStrength());
			reel = Math.random() * 100;
			if (reel < probability) {
				FINAL.get(0).positionInTournament = 1;
				FINAL.get(1).positionInTournament = 2;

			} else {
				FINAL.get(1).positionInTournament = 1;
				FINAL.get(0).positionInTournament = 2;

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
		for (int i = 2; i > 0; i--) { // first round
			probability = main.determine(teams.get(((i * 2) - 2)).getTeamStrength(),
					teams.get((i * 2) - 1).getTeamStrength());
			reel = Math.random() * 100;
			if (reel < probability) {
				SEMI.add(teams.get((i * 2) - 1)); // first team is winner
				LBR1.add(teams.get((i * 2) - 2)); // second team is loser
			} else {
				SEMI.add(teams.get((i * 2) - 2)); // first team is winner
				LBR1.add(teams.get((i * 2) - 1)); // second team is loser
			}

		}
		for (int i = 1; i > 0; i--) { // semi
			probability = main.determine(SEMI.get((i * 2) - 2).getTeamStrength(),
					SEMI.get((i * 2) - 1).getTeamStrength());
			reel = Math.random() * 100;
			if (reel < probability) {
				FINAL.add(SEMI.get((i * 2) - 1)); // first team is winner
				LBR2.add(SEMI.get((i * 2) - 2));

			} else {
				FINAL.add(SEMI.get((i * 2) - 2)); // first team is winner
				LBR2.add(SEMI.get((i * 2) - 1));

			}

		}
		for (int i = 1; i > 0; i--) { // lowerbracket Round 1
			probability = main.determine(LBR1.get((i * 2) - 2).getTeamStrength(),
					LBR1.get((i * 2) - 1).getTeamStrength());
			reel = Math.random() * 100;
			if (reel < probability) {
				LBR2.add(LBR1.get((i * 2) - 1)); // first team is winner
				LBR1.get((i * 2) - 2).positionInTournament = 4;
				System.out.println("Someone Placed 4th");
				LBR1.remove(LBR1.get((i * 2) - 2)); // second team is loser
			} else {
				LBR2.add(LBR1.get((i * 2) - 2)); // first team is winner
				LBR1.get((i * 2) - 1).positionInTournament = 4;
				System.out.println("Someone Placed 4th");
				LBR1.remove(LBR1.get((i * 2) - 1)); // second team is loser
			}

		}
		for (int i = 1; i > 0; i--) { // lowerbracket Round2
			probability = main.determine(LBR2.get((i * 2) - 2).getTeamStrength(),
					LBR2.get((i * 2) - 1).getTeamStrength());
			reel = Math.random() * 100;
			if (reel < probability) {
				FINAL.add(LBR2.get((i * 2) - 1)); // first team is winner
				LBR2.get((i * 2) - 2).positionInTournament = 3;
				System.out.println("Someone Placed 3rd");
				LBR2.remove(LBR2.get((i * 2) - 2)); // second team is loser

			} else {
				FINAL.add(LBR1.get((i * 2) - 2)); // first team is winner
				LBR2.get((i * 2) - 1).positionInTournament = 3;
				System.out.println("Someone Placed 3rd");
				LBR2.remove(LBR2.get((i * 2) - 1)); // second team is loser

			}

		}
		for (int i = 1; i > 0; i--) { // final
			probability = main.determine(FINAL.get((i * 2) - 2).getTeamStrength(),
					FINAL.get((i * 2) - 1).getTeamStrength());
			reel = Math.random() * 100;
			if (reel < probability) {
				FINAL.get(0).positionInTournament = 1;
				System.out.println("Someone Placed 1st");
				FINAL.get(1).positionInTournament = 2;
				System.out.println("Someone Placed 2nd");

			} else {
				FINAL.get(0).positionInTournament = 2;
				System.out.println("Someone Placed 2nd");
				FINAL.get(1).positionInTournament = 1;
				System.out.println("Someone Placed 1st");

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
