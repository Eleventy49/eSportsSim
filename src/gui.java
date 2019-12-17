import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
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
	private JButton loadPlayers;
	private JButton loadTeams;

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
		loadPlayers = new JButton("Load Players");
		loadTeams = new JButton("Load Teams");

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
		add(about, location);
		location.gridx = 1;
		location.gridy++;
		add(savePlayers, location);
		location.gridx++;
		add(saveTeams, location);
		location.gridx++;
		add(loadPlayers, location);
		location.gridx++;
		add(loadTeams, location);
		location.gridx++;

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
		loadPlayers.addActionListener(this);
		loadTeams.addActionListener(this);

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
		}

		if (((r.nextInt(5) + 1) == 5) && database.teamdatabase.size() > 1) {
			int TeamSwap1 = (r.nextInt(database.teamdatabase.size() - 1) + 1);
			int TeamSwap2 = (r.nextInt(database.teamdatabase.size() - 1) + 1);
			int temp;
			for (int i = 0; i != 1;) {
				temp = r.nextInt(5 - 1) - 1;
				if (temp != TeamSwap1) {
					temp = TeamSwap2;
					i = 1;
				}
			}
			int PlayerSwap1 = (r.nextInt(database.teamdatabase.get(TeamSwap1).roster.size() - 1) + 1);
			int PlayerSwap2 = (r.nextInt(database.teamdatabase.get(TeamSwap2).roster.size() - 1) + 1);

			//
			player player1 = database.teamdatabase.get(TeamSwap1).roster.get(PlayerSwap1);
			player player2 = database.teamdatabase.get(TeamSwap2).roster.get(PlayerSwap2);
			player1.org = database.teamdatabase.get(TeamSwap2);
			player2.org = database.teamdatabase.get(TeamSwap1);
			database.teamdatabase.get(TeamSwap1).roster.remove(PlayerSwap1);
			database.teamdatabase.get(TeamSwap2).roster.remove(PlayerSwap2);
			database.teamdatabase.get(TeamSwap1).roster.add(player2);
			database.teamdatabase.get(TeamSwap2).roster.add(player1);
			displayTeamTrade(player1, player2, database.teamdatabase.get(TeamSwap1),
					database.teamdatabase.get(TeamSwap2));
			//

		}

	}

	private team hasNameTeam(String name) {
		for (team x : database.teamdatabase) {
			if (x.name.equals(name)) {
				return x;
			}
		}
		System.out.println("Something Broke");
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
		if (buttonPressed == loadPlayers) {
			displayloadPlayers();
		}
		if (buttonPressed == loadTeams) {
			displayloadTeams();
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
		JOptionPane.showConfirmDialog(null, "" + team.name + " has signed " + player.name + " to their roster!",
				"Alert!", 2);
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
				for (team x : database.teamdatabase)
					displayTeams.add(x);

				String selector;
				ArrayList<team> teamsintournament = new ArrayList<team>();
				ArrayList<String> displaynames = new ArrayList<String>();
				for (team x : displayTeams)
					displaynames.add(x.name);
				for (int i = 0; i < 32; i++) {

					Object[] displayNamesplz = displaynames.toArray();

					selector = (String) JOptionPane.showInputDialog(null, "Select team " + i, "Tournament Teams",
							JOptionPane.PLAIN_MESSAGE, null, displayNamesplz, null);
					if (selector != null) {
						displayTeams.remove(hasNameTeam(selector));
						teamsintournament.add(hasNameTeam(selector));
					} else
						i--;
					displaynames.remove(selector);
				}
				simTournament.World(teamsintournament);

				String results = "";
				for (team x : teamsintournament) {
					if (x.positionInTournament == 1) {
						results += "Winner: " + x.name + "\t Rating: " + x.getTeamStrength();
						x.tournamentEarnings += 1666666;
						x.WorldTitlesWon += 1;
						for (player y : x.roster) {
							y.WorldTitles += 1;
						}
					}
				}
				for (team x : teamsintournament) {
					if (x.positionInTournament == 2) {
						results += "\nRunner-up: " + x.name + "\t Rating: " + x.getTeamStrength();
						x.tournamentEarnings += 1111111;
					}
				}
				for (team x : teamsintournament) {
					if (x.positionInTournament == 3) {
						results += "\nBronze: " + x.name + "\t Rating: " + x.getTeamStrength();
						x.tournamentEarnings += 370000;
					}
				}
				for (team x : teamsintournament) {
					if (x.positionInTournament == 3) {
						results += "\n4th Place: " + x.name + "\t Rating: " + x.getTeamStrength();
						x.tournamentEarnings += 123450;
					}
				}
				results += "\n5th-6th:";
				for (team x : teamsintournament) {
					if (x.positionInTournament == 4) {
						results += "\n" + x.name + "\t Rating: " + x.getTeamStrength();
						x.tournamentEarnings += 22000;
					}
				}
				results += "\n7th-8th:";
				for (team x : teamsintournament) {
					if (x.positionInTournament == 7) {
						results += "\n" + x.name + "\t Rating: " + x.getTeamStrength();
						x.tournamentEarnings += 3425;
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

			} else {
				JOptionPane.showConfirmDialog(null, "There are not enough teams to form a World tournament.", "Error!",
						2);
			}

			break;
		case 1:// major
			if (database.teamdatabase.size() >= 16
					&& database.teamdatabase.get(database.teamdatabase.size() - 1).roster.size() == 5) {
				ArrayList<team> displayTeams = database.teamdatabase;
				String selector;
				ArrayList<team> teamsintournament = new ArrayList<team>();
				ArrayList<String> displaynames = new ArrayList<String>();
				for (int i = 0; i < 16; i++) {
					for (team x : displayTeams)
						displaynames.add(x.name);
					Object[] displayNamesplz = displaynames.toArray();

					selector = (String) JOptionPane.showInputDialog(null, "Select team " + i, "Tournament Teams",
							JOptionPane.PLAIN_MESSAGE, null, displayNamesplz, null);
					teamsintournament.add(hasNameTeam(selector));
					displayTeams.remove(hasNameTeam(selector));

					teamsintournament.add(hasNameTeam(selector));
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
						}
					}
				}
				for (team x : teamsintournament) {
					if (x.positionInTournament == 2) {
						results += "\nRunner-up: " + x.name + "\t Rating: " + x.getTeamStrength();
						x.tournamentEarnings += 1111111 / 5;
					}
				}
				for (team x : teamsintournament) {
					if (x.positionInTournament == 3) {
						results += "\nBronze: " + x.name + "\t Rating: " + x.getTeamStrength();
						x.tournamentEarnings += 370000 / 5;
					}
				}
				for (team x : teamsintournament) {
					if (x.positionInTournament == 3) {
						results += "\n4th Place: " + x.name + "\t Rating: " + x.getTeamStrength();
						x.tournamentEarnings += 123450 / 5;
					}
				}
				results += "\n5th-6th:";
				for (team x : teamsintournament) {
					if (x.positionInTournament == 4) {
						results += "\n" + x.name + "\t Rating: " + x.getTeamStrength();
						x.tournamentEarnings += 22000 / 5;
					}
				}
				results += "\n7th-8th:";
				for (team x : teamsintournament) {
					if (x.positionInTournament == 7) {
						results += "\n" + x.name + "\t Rating: " + x.getTeamStrength();

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
					if (x.positionInTournament == 9) {
						results += "\n" + x.name + "\t Rating: " + x.getTeamStrength();

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
					ArrayList<team> displayTeams = database.teamdatabase;
					String selector;
					ArrayList<team> teamsintournament = new ArrayList<team>();
					ArrayList<String> displaynames = new ArrayList<String>();
					for (int i = 0; i < 8; i++) {
						for (team x : displayTeams)
							displaynames.add(x.name);
						Object[] displayNamesplz = displaynames.toArray();

						selector = (String) JOptionPane.showInputDialog(null, "Select team " + i, "Tournament Teams",
								JOptionPane.PLAIN_MESSAGE, null, displayNamesplz, null);
						teamsintournament.add(hasNameTeam(selector));
						displayTeams.remove(hasNameTeam(selector));

						teamsintournament.add(hasNameTeam(selector));
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
							}
						}
					}
					for (team x : teamsintournament) {
						if (x.positionInTournament == 2) {
							results += "\nRunner-up: " + x.name + "\t Rating: " + x.getTeamStrength();
							x.tournamentEarnings += 1111111 / 10;
						}
					}
					for (team x : teamsintournament) {
						if (x.positionInTournament == 3) {
							results += "\nBronze: " + x.name + "\t Rating: " + x.getTeamStrength();
							x.tournamentEarnings += 370000 / 10;
						}
					}
					for (team x : teamsintournament) {
						if (x.positionInTournament == 3) {
							results += "\n4th Place: " + x.name + "\t Rating: " + x.getTeamStrength();
							x.tournamentEarnings += 123450 / 10;
						}
					}
					results += "\n5th-6th:";
					for (team x : teamsintournament) {
						if (x.positionInTournament == 4) {
							results += "\n" + x.name + "\t Rating: " + x.getTeamStrength();
							x.tournamentEarnings += 22000 / 10;
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
						displayTeams.remove(hasNameTeam(selector));

						teamsintournament.add(hasNameTeam(selector));
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

							}

						}
					}
					for (team x : teamsintournament) {
						if (x.positionInTournament == 2) {
							results += "\nRunner-up: " + x.name + "\t Rating: " + x.getTeamStrength();
							x.tournamentEarnings += 1111111 / 20;
							x.positionInTournament = 0;

						}
					}
					for (team x : teamsintournament) {
						if (x.positionInTournament == 3) {
							results += "\nBronze: " + x.name + "\t Rating: " + x.getTeamStrength();
							x.tournamentEarnings += 370000 / 20;
							x.positionInTournament = 0;

						}
					}
					for (team x : teamsintournament) {
						if (x.positionInTournament == 4) {
							results += "\n4th Place: " + x.name + "\t Rating: " + x.getTeamStrength();
							x.tournamentEarnings += 123450 / 20;
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

	public void displayloadPlayers() {
		database.playerdatabase = filehandler.LoadPlayers();
		JOptionPane.showConfirmDialog(null, "The players have been loaded.", "Alert!", 2);
	}

	public void displayloadTeams() {

		for (team x : filehandler.LoadTeams())
			database.teamdatabase.add(x);

		JOptionPane.showConfirmDialog(null, "The teams have been loaded.", "Alert!", 2);
	}

}