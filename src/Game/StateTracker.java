package Game;

import java.util.ArrayList;

import Game.Application.STATE;

public class StateTracker {
	
	public static void render() {
		if (Application.State == STATE.ManagerMode) {
			//////// Image zone

			// g.drawImage(background, 0, 0, getWidth(), getHeight(), this);

			// p.render(g);
			// Render the Text in the main menu
		} else if (Application.State == STATE.Menu) {
			Application.getMenu().render(Application.g); // Render the menu
		}
		// Draw Buttons

		if (Application.State == STATE.SpectatorModeAddPlayer) {
			AddPlayer.AddPlayer(Application.g);
		}
		if (Application.State == STATE.Options) {
			OptionsMenu.render();
		}
		if (Application.State == STATE.SpectatorModeAddTeam) {
			AddTeam.Team(Application.getGame().getGraphics());
		}
		if (Application.State == STATE.Tournaments) {

			BracketHandler.setGraphics(Application.getGame().getGraphics());
			Bracket32.clearTournament();
			Bracket16.clearTournament();
			Application.runTournament = true;

		}
		if ((Application.State == STATE.TournamentsWorldTournament)) {

			if (Application.runTournament) {
				Bracket32.clearTournament();
				BracketHandler.setGraphics(Application.getGame().getGraphics());
				SimTournament.World(Database.teamdatabase);
				Application.runTournament = false;
				Bracket32.printBracket();
			} else {
				Bracket32.load();
				Bracket32.printBracket();
			}

		}

		if ((Application.State == STATE.TournamentsMajorTournament)) {

			if (Application.runTournament) {
				Application.runTournament = false;
				Bracket16.clearTournament();
				BracketHandler.setGraphics(Application.getGame().getGraphics());
				ArrayList<Team> temp = Database.getStrongBois(16);
				SimTournament.Major(temp);
				Bracket16.load();
				Bracket16.printBracket();
			} else {
				Bracket16.load();
				Bracket16.printBracket();
			}

		}
		if ((Application.State == STATE.TournamentsMinor4Tournament)) {

			if (Application.runTournament) {
				Bracket4.clearTournament();
				BracketHandler.setGraphics(Application.getGame().getGraphics());
				SimTournament.Minor4(Database.getWeakBois(4));
				Application.runTournament = false;
				Bracket4.printBracket();
			} else {
				Bracket4.load();
				Bracket4.printBracket();
			}

		}

		if ((Application.State == STATE.TournamentsMinor8Tournament)) {

			if (Application.runTournament) {
				Bracket8.clearTournament();
				BracketHandler.setGraphics(Application.g);
				SimTournament.Minor8(Database.getWeakBois(8));
				Application.runTournament = false;
				Bracket8.printBracket();
			} else {
				Bracket8.load();
				Bracket8.printBracket();
			}

		}
		if (Application.State == STATE.SpectatorModeViewPlayer) {
			PlayerViewer.defaultdisplay();
		}
		if (Application.State == STATE.SpectatorModeViewPlayerB) {
			PlayerViewer.render(Application.trackingPlayer);
		}

		if (Application.State == STATE.SpectatorModeViewTeam) {
			TeamViewer.defaultdisplay();
		}
		if (Application.State == STATE.SpectatorModeViewTeamB) {
			TeamViewer.render(Application.ourversionofi);
		}

		if (Application.State == STATE.SpectatorModeViewTop10Team) {
			TeamTenTeams.render();
			TeamTenTeams.clear();
		}
		if (Application.State == STATE.SpectatorModeViewTop10Player) {
			TeamTenPlayers.render();
			TeamTenPlayers.clear();
		}
	}

}
