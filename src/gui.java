import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.*;

/**
 * Handles the visual response of the Roulette Program
 * 
 * @author Eleventy49
 *
 */

public class gui extends JFrame implements ActionListener {

	private JButton addPlayer;
	private JButton addTeam;
	private JButton viewPlayer;
	private JButton viewTeam;
	private JButton about;
	private JButton GameTick;
	private JButton SimChampionship;
	private JButton simRosterswapping;
	private JButton savePlayers;
	private JButton saveTeams;
	private JButton topTenPlayers;
	private JButton topTenTeams;
	static boolean newWindow = true;

	private JButton load;
	static boolean ITellYouTo = true;

	/**
	 * Constructor for the GUI
	 */
	public gui() {

		setLayout(new GridBagLayout());
		GridBagConstraints location = new GridBagConstraints();
		location = new GridBagConstraints();
		location.gridx = 1;
		location.gridy = 3;
		location.insets.bottom = 20;
		location.insets.top = 20;
		add(new JLabel("ESports Sim"), location);

		addPlayer = new JButton("Add Player");
		addTeam = new JButton("Add Team");
		viewPlayer = new JButton("View Player");
		viewTeam = new JButton("View Team");
		about = new JButton("About");
		GameTick = new JButton("GameTick");
		SimChampionship = new JButton("Sim Championship");
		simRosterswapping = new JButton("Sim Roster Swaps");
		savePlayers = new JButton("Save Players");
		saveTeams = new JButton("Save Teams");
		load = new JButton("Load");
		topTenPlayers = new JButton("Top Ten Players");
		topTenTeams = new JButton("Top Ten Teams");

		location.anchor = GridBagConstraints.LINE_START;
		location.insets = new Insets(5, 5, 5, 5);
		location.gridx = 1;
		location.gridy = 4;
		add(addPlayer, location);
		location.gridx++;
		add(addTeam, location);
		location.gridx++;
		add(viewPlayer, location);
		location.gridx++;
		add(viewTeam, location);
		location.anchor = GridBagConstraints.LINE_START;
		location.gridx = 1;
		location.gridy++;
		add(SimChampionship, location);
		location.gridx++;
		add(simRosterswapping, location);
		location.gridx++;
		add(GameTick, location);
		location.gridx++;
		add(load, location);
		location.gridx = 1;
		location.gridy++;
		add(savePlayers, location);
		location.gridx++;
		add(saveTeams, location);
		location.gridx++;
		add(topTenPlayers, location);
		location.gridx++;
		add(topTenTeams, location);
		// location.gridy++;
		// location.gridx = 1;
		// add(about, location);

		addPlayer.addActionListener(this);
		addTeam.addActionListener(this);
		viewPlayer.addActionListener(this);
		viewTeam.addActionListener(this);
		about.addActionListener(this);
		GameTick.addActionListener(this);
		SimChampionship.addActionListener(this);
		simRosterswapping.addActionListener(this);
		savePlayers.addActionListener(this);
		saveTeams.addActionListener(this);
		load.addActionListener(this);
		topTenTeams.addActionListener(this);
		topTenPlayers.addActionListener(this);

	}

	private player hasNamePlayer(String name) {
		for (player x : database.playerdatabase) {
			if (x.name.equals(name)) {
				return x;
			}
		}
		return null;
	}

	public void tick() { // THE PLAYERS BEING TRADED SHOULD NOT BE PURELY RANDOM
		Random r = new Random();

		for (team x : database.teamdatabase) {
			if (x.roster.size() != 5) {
				for (player y : database.playerdatabase)
					if ((y.org == null) && (x.roster.size() != 5)) {
						y.org = x;
						x.roster.add(y);
						displayPlayerSigning(x, y);
					}

			}
		//	System.out.println(x.roster.size());
			if (x.roster.size() == 5) {
				x.giveRoles();
			}

		}

		if (((r.nextInt(5) + 1) == 5) && database.teamdatabase.size() > 1) {
			int TeamSwap1 = (r.nextInt(database.teamdatabase.size() - 1));

			int TeamSwap2 = (r.nextInt(database.teamdatabase.size() - 1));

			if (TeamSwap2 == TeamSwap1)
				if (TeamSwap2 == database.teamdatabase.size() - 1)
					TeamSwap2 -= 1;
				else if (TeamSwap2 == 0)
					TeamSwap2 += 1;
				else
					TeamSwap2 -= 1;
			if (TeamSwap2 == TeamSwap1)
				System.out.println("You are an idiot");
			int temp;
			for (int i = 0; i != 1;) {
				temp = r.nextInt(5 - 1) - 1;
				if (temp != TeamSwap1) {
					temp = TeamSwap2;
					i = 1;
				}
			}
			int PlayerSwap1 = (r.nextInt(database.teamdatabase.get(TeamSwap1).roster.size() - 1)) + 1;
			int PlayerSwap2 = (r.nextInt(database.teamdatabase.get(TeamSwap2).roster.size() - 1)) + 1;

			//
			player player1 = database.teamdatabase.get(TeamSwap1).roster.get(PlayerSwap1);
			player player2 = database.teamdatabase.get(TeamSwap2).roster.get(PlayerSwap2);
			player1.org = database.teamdatabase.get(TeamSwap2);
			player2.org = database.teamdatabase.get(TeamSwap1);
			database.teamdatabase.get(TeamSwap1).roster.remove(player1);
			database.teamdatabase.get(TeamSwap2).roster.remove(player2);
			database.teamdatabase.get(TeamSwap1).roster.add(player2);
			database.teamdatabase.get(TeamSwap2).roster.add(player1);
			displayTeamTrade(player1, player2, database.teamdatabase.get(TeamSwap1),
					database.teamdatabase.get(TeamSwap2));
			//

		}
		for (player x : database.playerdatabase)
			x.update();
	}

	private team hasNameTeam(String name) {
		for (team x : database.teamdatabase) {
			if (x.name.equals(name)) {
				return x;
			}
		}
		System.out.println("Something Broke in hasNameTeam");
		return null;
	}

	public static void main(String[] args) {
		main.main(args);

	}

	public void actionPerformed(ActionEvent e) {

		JComponent buttonPressed = (JComponent) e.getSource();
		if (buttonPressed == addPlayer) {
			displayAddPlayer();

		}
		if (buttonPressed == addTeam) {
			displayAddTeam();
		}

		if (buttonPressed == viewPlayer) {
			displayViewPlayer();
		}

		if (buttonPressed == viewTeam) {
			displayViewTeam();
		}
		if (buttonPressed == SimChampionship) {
			displaySimChampionship();
		}
		if (buttonPressed == simRosterswapping) {
			displaySimRosterswapping();
		}
		if (buttonPressed == about) {
			displayAbout();
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {

					team sec = secondaryGui.main(database.teamdatabase);

					System.out.println(cache.b);
				}
			});

		}
		if (buttonPressed == GameTick) {
			displayGameTick();
		}
		if (buttonPressed == savePlayers) {
			displaysavePlayers();
		}
		if (buttonPressed == saveTeams) {
			displaysaveTeams();
		}
		if (buttonPressed == load) {
			displayload();
		}
		if (buttonPressed == topTenPlayers) {
			displayTopTenPlayers();
		}
		if (buttonPressed == topTenTeams) {
			displayTopTenTeams();
		}
	}

	private Object[] displayNames() {
		ArrayList<String> subnames = new ArrayList<String>();
		for (player a : database.playerdatabase)
			subnames.add(a.name);

		return subnames.toArray();
	}

	private Object[] displayTeams() {
		ArrayList<String> subnames = new ArrayList<String>();
		for (team a : database.teamdatabase)
			subnames.add(a.name);

		return subnames.toArray();
	}

	private void displayAddPlayer() {
		String nameboi = JOptionPane.showInputDialog(addPlayer, "Enter new player's name.", null);
		player temp = new player(nameboi);
		tick();
	}

	private void displayAddTeam() {
		String nameboi = JOptionPane.showInputDialog(addPlayer, "Enter new team's name.", null);
		team temp = new team(nameboi);
		tick();
	}

	private void displayViewTeam() {
		if (database.teamdatabase.size() == 0)
			JOptionPane.showConfirmDialog(null, "There are no teams to view!", "Error!", 2);
		else {
			String selection = (String) JOptionPane.showInputDialog(null, "Select team", "Select team",
					JOptionPane.PLAIN_MESSAGE, null, displayTeams(), null);

			JOptionPane.showConfirmDialog(null, hasNameTeam(selection).toString(), "Player Data", 2);
			tick();
		}
	}

	private void displayViewPlayer() {
		if (database.playerdatabase.size() == 0)
			JOptionPane.showConfirmDialog(null, "There are no players to view!", "Error!", 2);
		else {
			String selection = (String) JOptionPane.showInputDialog(null, "Select player", "Select player",
					JOptionPane.PLAIN_MESSAGE, null, displayNames(), null);
			JOptionPane.showConfirmDialog(null, hasNamePlayer(selection).toString(), "Player Data", 2);
			tick();
		}
	}

	public void displayPlayerSigning(team team, player player) {
		// JOptionPane.showConfirmDialog(null, "" + team.name + " has signed " +
		// player.name + " to their roster!",
		// "Alert!", 2);
		tick();
	}

	private void displaySimChampionship() // SHOULD BE MADE TO NOT BE PURELY RANDOM IN THE FUTURE
	{
		Object[] options1 = { "World Title", "Major Title", "Minor Title" };
		int result = JOptionPane.showOptionDialog(null, "What is the status of this Championship?", "New Championship!",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options1, null);
		switch (result) {
		case 0:// world
			if (database.teamdatabase.size() >= 32
					&& database.teamdatabase.get(database.teamdatabase.size() - 1).roster.size() == 5) {
				ArrayList<team> displayTeams = new ArrayList<team>();
				for (int i = 0; i < database.teamdatabase.size(); i++)
					if (database.teamdatabase.get(i) != null)
						displayTeams.add(database.teamdatabase.get(i));

				String selector;
				ArrayList<team> teamsintournament = new ArrayList<team>();
				ArrayList<String> displaynames = new ArrayList<String>();
				// int z = 0;
				for (team x : displayTeams) {
					if (!displaynames.contains(x.name))
						displaynames.add(x.name);
				}
				if(database.teamdatabase.size() != 32) {
				for (int i = 0; i < 32; i++) {

					Object[] displayNamesplz = displaynames.toArray();

					selector = (String) JOptionPane.showInputDialog(null, "Select team " + (i + 1), "Tournament Teams",
							JOptionPane.PLAIN_MESSAGE, null, displayNamesplz, null);
					if (selector != null) {
						displayTeams.remove(hasNameTeam(selector));
						teamsintournament.add(hasNameTeam(selector));
					} else
						i--;
					displaynames.remove(selector);
				}
				simTournament.World(teamsintournament);
				}
				else { 
					teamsintournament = database.teamdatabase;
					simTournament.World(teamsintournament);
					
				}

				String results = "";
				for (team x : teamsintournament) {
					if (x.positionInTournament == 1) {
						results += "Winner: " + x.name + "\t Rating: " + x.getTeamStrength();
						x.tournamentEarnings += 1666666;
						x.WorldTitlesWon += 1;
						for (player y : x.roster) {
							y.WorldTitles += 1;
							y.earnings += 1666666 / 5;
						}
					}
				}
				for (team x : teamsintournament) {
					if (x.positionInTournament == 2) {
						results += "\nRunner-up: " + x.name + "\t Rating: " + x.getTeamStrength();
						x.tournamentEarnings += 1111111;
						for (player y : x.roster)
							y.earnings += 1111111 / 5;

					}
				}
				for (team x : teamsintournament) {
					if (x.positionInTournament == 3) {
						results += "\nBronze: " + x.name + "\t Rating: " + x.getTeamStrength();
						x.tournamentEarnings += 370000;
						for (player y : x.roster)
							y.earnings += 370000 / 5;
					}
				}
				for (team x : teamsintournament) {
					if (x.positionInTournament == 4) {
						results += "\n4th Place: " + x.name + "\t Rating: " + x.getTeamStrength();
						x.tournamentEarnings += 123450;
						for (player y : x.roster)
							y.earnings += 123450 / 5;
					}
				}
				results += "\n5th-6th:";
				for (team x : teamsintournament) {
					if (x.positionInTournament == 5) {
						results += "\n" + x.name + "\t Rating: " + x.getTeamStrength();
						x.tournamentEarnings += 22000;
						for (player y : x.roster)
							y.earnings += 22000 / 5;
					}
				}
				results += "\n7th-8th:";
				for (team x : teamsintournament) {
					if (x.positionInTournament == 7) {
						results += "\n" + x.name + "\t Rating: " + x.getTeamStrength();
						x.tournamentEarnings += 3425;
						for (player y : x.roster)
							y.earnings += 3425 / 5;
					}
				}
				results += "\n9th-12th:";
				for (team x : teamsintournament) {
					if (x.positionInTournament == 9) {
						results += "\n" + x.name + "\t Rating: " + x.getTeamStrength();

					}
				}
				results += "\n13th-16th:";
				for (team x : teamsintournament) {
					if (x.positionInTournament == 13) {
						results += "\n" + x.name + "\t Rating: " + x.getTeamStrength();

					}
				}
				results += "\n17th-24th:";
				for (team x : teamsintournament) {
					if (x.positionInTournament == 17) {
						results += "\n" + x.name + "\t Rating: " + x.getTeamStrength();

					}
				}
				results += "\n25th-32nd:";
				for (team x : teamsintournament) {
					if (x.positionInTournament == 25) {
						results += "\n" + x.name + "\t Rating: " + x.getTeamStrength();

					}
				}
				JOptionPane.showConfirmDialog(null, results, "Tournament Results!", 2);
				for (team x : database.teamdatabase) {
					if (Math.abs(x.dynasty - 0) > 0.01)
						x.dynasty -= 0.15;
					if (x.dynasty < 0)
						x.dynasty = 0;

				}

			} else {
				JOptionPane.showConfirmDialog(null, "There are not enough teams to form a World tournament.", "Error!",
						2);
			}

			break;
		case 1:// major
			if (database.teamdatabase.size() >= 16
					&& database.teamdatabase.get(database.teamdatabase.size() - 1).roster.size() == 5) {
				ArrayList<team> displayTeams = new ArrayList<team>();
				for (team x : database.teamdatabase)
					displayTeams.add(x);
				String selector;
				ArrayList<team> teamsintournament = new ArrayList<team>();
				ArrayList<String> displaynames = new ArrayList<String>();
				for (int i = 0; i < 16; i++) {
					for (team x : displayTeams)
						displaynames.add(x.name);
					Object[] displayNamesplz = displaynames.toArray();

					selector = (String) JOptionPane.showInputDialog(null, "Select team " + i, "Tournament Teams",
							JOptionPane.PLAIN_MESSAGE, null, displayNamesplz, null);
					if (selector != null) {
						displayTeams.remove(hasNameTeam(selector));
						teamsintournament.add(hasNameTeam(selector));
					} else
						i--;
					displaynames.remove(selector);
					displayTeams.remove(hasNameTeam(selector));

					displaynames.clear();
				}
				simTournament.Major(teamsintournament);

				String results = "";
				for (team x : teamsintournament) {
					if (x.positionInTournament == 1) {
						results += "Winner: " + x.name + "\t Rating: " + x.getTeamStrength();
						x.tournamentEarnings += 1666666 / 5;
						x.MajorTitlesWon += 1;
						for (player y : x.roster) {
							y.MajorTitles += 1;
							y.earnings += (1666666 / 5) / 5;

						}
						x.positionInTournament = 0;
					}
				}
				for (team x : teamsintournament) {
					if (x.positionInTournament == 2) {
						results += "\nRunner-up: " + x.name + "\t Rating: " + x.getTeamStrength();
						x.tournamentEarnings += 1111111 / 5;
						for (player y : x.roster)
							y.earnings += (1111111 / 5) / 5;
						x.positionInTournament = 0;
					}
				}
				for (team x : teamsintournament) {
					if (x.positionInTournament == 3) {
						results += "\nBronze: " + x.name + "\t Rating: " + x.getTeamStrength();
						x.tournamentEarnings += 370000 / 5;
						for (player y : x.roster)
							y.earnings += (370000 / 5) / 5;
						x.positionInTournament = 0;
					}
				}
				for (team x : teamsintournament) {
					if (x.positionInTournament == 4) {
						results += "\n4th Place: " + x.name + "\t Rating: " + x.getTeamStrength();
						x.tournamentEarnings += 123450 / 5;
						for (player y : x.roster)
							y.earnings += (123450 / 5) / 5;
						x.positionInTournament = 0;
					}
				}
				results += "\n5th-6th:";
				for (team x : teamsintournament) {
					if (x.positionInTournament == 5) {
						results += "\n" + x.name + "\t Rating: " + x.getTeamStrength();
						x.tournamentEarnings += 22000 / 5;
						for (player y : x.roster)
							y.earnings += (22000 / 5) / 5;
						x.positionInTournament = 0;
					}
				}
				results += "\n7th-8th:";
				for (team x : teamsintournament) {
					if (x.positionInTournament == 7) {
						results += "\n" + x.name + "\t Rating: " + x.getTeamStrength();
						x.positionInTournament = 0;

					}
				}
				results += "\n9th-12th:";
				for (team x : teamsintournament) {
					if (x.positionInTournament == 9) {
						results += "\n" + x.name + "\t Rating: " + x.getTeamStrength();
						x.positionInTournament = 0;
					}
				}
				results += "\n13th-16th:";
				for (team x : teamsintournament) {
					if (x.positionInTournament == 13) {
						results += "\n" + x.name + "\t Rating: " + x.getTeamStrength();
						x.positionInTournament = 0;

					}
				}

				JOptionPane.showConfirmDialog(null, results, "Tournament Results!", 2);

			} else {
				JOptionPane.showConfirmDialog(null, "There are not enough teams to form a Major tournament.", "Error!",
						2);
			}

			break;

		default:// minor
			Object[] options = { "8 Team", "4 Team" };

			int resulta = JOptionPane.showOptionDialog(null, "Which size Minor tournament would you like to hold?",
					"Minor Tournament", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
					options[1]);// default button title
			if (resulta == 0) {
				if (database.teamdatabase.size() >= 8
						&& database.teamdatabase.get(database.teamdatabase.size() - 1).roster.size() == 5) {
					ArrayList<team> displayTeams = new ArrayList<team>();
					for (team x : database.teamdatabase)
						displayTeams.add(x);
					String selector;
					ArrayList<team> teamsintournament = new ArrayList<team>();
					ArrayList<String> displaynames = new ArrayList<String>();
					for (int i = 0; i < 8; i++) {
						for (team x : displayTeams)
							displaynames.add(x.name);
						Object[] displayNamesplz = displaynames.toArray();

						selector = (String) JOptionPane.showInputDialog(null, "Select team " + i, "Tournament Teams",
								JOptionPane.PLAIN_MESSAGE, null, displayNamesplz, null);
						if (selector != null) {
							displayTeams.remove(hasNameTeam(selector));
							teamsintournament.add(hasNameTeam(selector));
						} else
							i--;
						displaynames.remove(selector);
						// displayTeams.remove(hasNameTeam(selector));
						System.out.println("We've been here");

						displaynames.clear();
					}

					simTournament.Minor8(teamsintournament);

					String results = "";
					for (team x : teamsintournament) {
						if (x.positionInTournament == 1) {
							results += "Winner: " + x.name + "\t Rating: " + x.getTeamStrength();
							x.tournamentEarnings += 1666666 / 10;
							x.MinorTitlesWon += 1;
							for (player y : x.roster) {
								y.MinorTitles += 1;
								y.earnings += (1666666 / 10) / 5;
							}
						}
					}
					for (team x : teamsintournament) {
						if (x.positionInTournament == 2) {
							results += "\nRunner-up: " + x.name + "\t Rating: " + x.getTeamStrength();
							x.tournamentEarnings += 1111111 / 10;
							for (player y : x.roster)
								y.earnings += (1111111 / 10) / 5;
						}
					}
					for (team x : teamsintournament) {
						if (x.positionInTournament == 3) {
							results += "\nBronze: " + x.name + "\t Rating: " + x.getTeamStrength();
							x.tournamentEarnings += 370000 / 10;
							for (player y : x.roster)
								y.earnings += (370000 / 10) / 5;
						}
					}
					for (team x : teamsintournament) {
						if (x.positionInTournament == 4) {
							results += "\n4th Place: " + x.name + "\t Rating: " + x.getTeamStrength();
							x.tournamentEarnings += 123450 / 10;
							for (player y : x.roster)
								y.earnings += (123450 / 10) / 5;
						}
					}
					results += "\n5th-6th:";
					for (team x : teamsintournament) {
						if (x.positionInTournament == 5) {
							results += "\n" + x.name + "\t Rating: " + x.getTeamStrength();
							x.tournamentEarnings += 22000 / 10;
							for (player y : x.roster)
								y.earnings += (22000 / 10) / 5;
						}
					}
					results += "\n7th-8th:";
					for (team x : teamsintournament) {
						if (x.positionInTournament == 7) {
							results += "\n" + x.name + "\t Rating: " + x.getTeamStrength();
						}
					}
					JOptionPane.showConfirmDialog(null, results, "Tournament Results!", 2);

				} else {
					JOptionPane.showConfirmDialog(null, "There are not enough teams to form a Minor(8) tournament.",
							"Error!", 2);
				}
			} else {
				if (database.teamdatabase.size() >= 4
						&& database.teamdatabase.get(database.teamdatabase.size() - 1).roster.size() == 5) {
					ArrayList<team> displayTeams = new ArrayList<team>();
					for (team x : database.teamdatabase)
						displayTeams.add(x);
					String selector;
					ArrayList<team> teamsintournament = new ArrayList<team>();
					ArrayList<String> displaynames = new ArrayList<String>();
					for (int i = 0; i < 4; i++) {
						for (team x : displayTeams)
							displaynames.add(x.name);
						Object[] displayNamesplz = displaynames.toArray();

						selector = (String) JOptionPane.showInputDialog(null, "Select team " + i, "Tournament Teams",
								JOptionPane.PLAIN_MESSAGE, null, displayNamesplz, null);
						if (selector != null) {
							displayTeams.remove(hasNameTeam(selector));
							teamsintournament.add(hasNameTeam(selector));
						} else
							i--;
						displaynames.remove(selector);
						// displayTeams.remove(hasNameTeam(selector));
						System.out.println("We've been here");

						displaynames.clear();
					}

					simTournament.Minor4(teamsintournament);

					String results = "";
					for (team x : teamsintournament) {
						if (x.positionInTournament == 1) {
							results += "Winner: " + x.name + "\t Rating: " + x.getTeamStrength();
							x.positionInTournament = 0;
							x.tournamentEarnings += 1666666 / 20;
							x.MinorTitlesWon += 1;
							for (player y : x.roster) {
								y.MinorTitles += 1;
								y.earnings += (1666666 / 20) / 5;

							}

						}
					}
					for (team x : teamsintournament) {
						if (x.positionInTournament == 2) {
							results += "\nRunner-up: " + x.name + "\t Rating: " + x.getTeamStrength();
							x.tournamentEarnings += 1111111 / 20;
							for (player y : x.roster)
								y.earnings += (1111111 / 20) / 5;
							x.positionInTournament = 0;

						}
					}
					for (team x : teamsintournament) {
						if (x.positionInTournament == 3) {
							results += "\nBronze: " + x.name + "\t Rating: " + x.getTeamStrength();
							x.tournamentEarnings += 370000 / 20;
							for (player y : x.roster)
								y.earnings += (370000 / 20) / 5;
							x.positionInTournament = 0;

						}
					}
					for (team x : teamsintournament) {
						if (x.positionInTournament == 4) {
							results += "\n4th Place: " + x.name + "\t Rating: " + x.getTeamStrength();
							x.tournamentEarnings += 123450 / 20;
							for (player y : x.roster)
								y.earnings += (123450 / 20) / 5;
							x.positionInTournament = 0;

						}
					}
					JOptionPane.showConfirmDialog(null, results, "Tournament Results!", 2);

				} else {
					JOptionPane.showConfirmDialog(null, "There are not enough teams to form a Minor(4) tournament.",
							"Error!", 2);

				}

				break;

			}

		}

	}

	private void displaySimRosterswapping() // SHOULD BE MADE TO NOT BE PURELY RANDOM IN THE FUTURE
	{
		Random r = new Random();
		int swaps = (int) (Math.random() * database.teamdatabase.size());
		String output = "";
		for (int i = 0; i < swaps; i++) {
			int TeamSwap1 = (r.nextInt(database.teamdatabase.size() - 1)) + 1;

			int TeamSwap2 = (r.nextInt(database.teamdatabase.size() - 1)) + 1;

			if (TeamSwap2 == TeamSwap1)
				if (TeamSwap2 == database.teamdatabase.size() - 1)
					TeamSwap2 -= 1;
				else if (TeamSwap2 == 0)
					TeamSwap2 += 1;
				else
					TeamSwap2 -= 1;
			if (TeamSwap2 == TeamSwap1)
				System.out.println("You are an idiot");
			int temp;
			for (int n = 0; n != 1;) {
				temp = r.nextInt(5 - 1) - 1;
				if (temp != TeamSwap1) {
					temp = TeamSwap2;
					n = 1;
				}
			}
			int PlayerSwap1 = (r.nextInt(database.teamdatabase.get(TeamSwap1).roster.size() - 1)) + 1;
			int PlayerSwap2 = (r.nextInt(database.teamdatabase.get(TeamSwap2).roster.size() - 1)) + 1;

			//
			player player1 = database.teamdatabase.get(TeamSwap1).roster.get(PlayerSwap1);
			player player2 = database.teamdatabase.get(TeamSwap2).roster.get(PlayerSwap2);
			player1.org = database.teamdatabase.get(TeamSwap2);
			player2.org = database.teamdatabase.get(TeamSwap1);
			database.teamdatabase.get(TeamSwap1).roster.remove(player1);
			database.teamdatabase.get(TeamSwap2).roster.remove(player2);
			database.teamdatabase.get(TeamSwap1).roster.add(player2);
			database.teamdatabase.get(TeamSwap2).roster.add(player1);
			output += database.teamdatabase.get(TeamSwap1).name + " has traded " + player1.name + " to "
					+ database.teamdatabase.get(TeamSwap2).name + " in exchange for " + player2.name + "\n";

			// displayTeamTrade(player1, player2, database.teamdatabase.get(TeamSwap1),
			// database.teamdatabase.get(TeamSwap2));

		}
		JOptionPane.showConfirmDialog(null, output, "Alert!", 2);
	}

	private void displayAbout() {

	}

	private void displayGameTick() {
		JOptionPane.showConfirmDialog(null, "A tick will be simulated", "Tick!", 2);
		tick();
	}

	public void displayTeamTrade(player a, player b, team c, team d) {

		JOptionPane.showConfirmDialog(null,
				"" + c.name + " has traded " + a.name + " to " + d.name + " in return for " + b.name, "Alert!", 2);

	}

	public void displaysavePlayers() {
		filehandler.SavePlayers(database.playerdatabase);
		JOptionPane.showConfirmDialog(null, "The players have been saved.", "Alert!", 2);
	}

	public void displaysaveTeams() {
		filehandler.SaveTeams(database.teamdatabase);
		JOptionPane.showConfirmDialog(null, "The teams have been saved.", "Alert!", 2);
	}

	public void displayload() {
		database.playerdatabase = filehandler.LoadPlayers();
		database.teamdatabase = filehandler.LoadTeams();
		// JOptionPane.showConfirmDialog(null, "The files have been loaded.", "Alert!",
		// 2);
	}

	public void displayTopTenPlayers() {
		double statCounterD = 0;
		int statCounter = 0;
		player tdd = null;
		ArrayList<player> temp = new ArrayList<player>();
		String[] j = { "Skill Rating", "Tournament Wins", "Earnings" };
		Object[] options1 = j;
		int result = JOptionPane.showOptionDialog(null, "Sort by?", "Top Ten Players", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options1, null);
		for (player x : database.playerdatabase)
			temp.add(x);
		String output = "";
		switch (result) {
		case 0: // Skill Rating
			for (int i = 0; i < 10; i++) {
				for (player x : temp)
					if (x.getStatsTotal() > statCounter) {
						tdd = x;
						statCounter = tdd.getStatsTotal();
					}

				output += "" + tdd.getFullName() + " (" + tdd.getStatsTotal() + ")\n";
				temp.remove(tdd);
				statCounter = 0;
			}
			break;
		case 1: // tournament wins
			for (int i = 0; i < 10; i++) {
				System.out.println(i);
				for (player x : temp)
					if (x.getTournamentWins() > statCounter) {
						tdd = x;
						statCounter = tdd.getTournamentWins();
					}
				System.out.println(tdd.name);
				output += "" + tdd.getFullName() + " (" + tdd.getTournamentWins() + ")\n";
				temp.remove(tdd);
				statCounter = 0;
			}
			break;
		case 2: // Earnings
			NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);

			for (int i = 0; i < 10; i++) {
				for (player x : temp)
					if (x.earnings > statCounter) {
						tdd = x;
						statCounter = x.earnings;
					}
				String s = n.format(tdd.earnings);
				output += "" + tdd.getFullName() + " (" + s + ")\n";
				temp.remove(tdd);
				statCounter = 0;
			}
			break;
		}

		JOptionPane.showConfirmDialog(null, output, "Top Ten Teams", 2);
	}

	public void displayTopTenTeams() {
		double statCounter = 0;
		team tdd = null;
		ArrayList<team> temp = new ArrayList<team>();
		String[] j = { "Skill Rating", "Dynasty Factor", "Earnings" };
		Object[] options1 = j;
		int result = JOptionPane.showOptionDialog(null, "Sort by?", "Top Ten Teams", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options1, null);
		for (team x : database.teamdatabase)
			temp.add(x);
		String output = "";
		switch (result) {
		case 0: // Skill Rating
			for (int i = 0; i < 10; i++) {
				for (team x : temp)
					if (x.getTeamStrength() > statCounter) {
						tdd = x;
						statCounter = tdd.getTeamStrength();
					}
				try {
					if (tdd.equals(null))
						tdd = temp.get(0);
				} catch (NullPointerException e) {
					tdd = temp.get(0);
				}

				output += "" + tdd.name + " (" + tdd.getTeamStrength() + ")\n";
				temp.remove(tdd);
				statCounter = 0;
				tdd = null;
			}
			break;
		case 1: // Dynasty Factor
			for (int i = 0; i < 10; i++) {
				for (team x : temp)
					if (x.dynasty > statCounter) {
						tdd = x;
						statCounter = tdd.dynasty;
					}
				try {
					if (tdd.equals(null))
						tdd = temp.get(0);
				} catch (NullPointerException e) {
					tdd = temp.get(0);
				}

				output += "" + tdd.name + " (" + tdd.dynasty + ")\n";
				temp.remove(tdd);
				statCounter = 0;
				tdd = null;
			}
			break;
		case 2: // Earnings
			NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);

			for (int i = 0; i < 10; i++) {
				for (team x : temp)
					if (x.tournamentEarnings > statCounter) {
						tdd = x;
						statCounter = tdd.tournamentEarnings;
					}
				try {
					if (tdd.equals(null))
						tdd = temp.get(0);
				} catch (NullPointerException e) {
					tdd = temp.get(0);
				}
				String s = n.format(tdd.tournamentEarnings);
				output += "" + tdd.name + " (" + s + ")\n";
				temp.remove(tdd);
				statCounter = 0;
				tdd = null;
			}
			break;
		}

		JOptionPane.showConfirmDialog(null, output, "Top Ten Teams", 2);
	}

}