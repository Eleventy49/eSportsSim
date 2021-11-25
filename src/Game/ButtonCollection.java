package Game;

import java.util.ArrayList;
import java.util.Collection;

import Game.buttons.*;


public class ButtonCollection {

	ArrayList<Object> collection = new ArrayList<Object>();
	
	static AddTeamAbb AddTeamAbb = new AddTeamAbb();
	static AddTeamName AddTeamName = new AddTeamName();
	static CornerBack CornerBack = new CornerBack();
	static CornerMenu CornerMenu = new CornerMenu();
	static MainMenuPlayButton MainMenuPlayButton = new MainMenuPlayButton();
	static DecreaseGameSpeed DecreaseGameSpeed = new DecreaseGameSpeed ();
	static IncreaseGameSpeed IncreaseGameSpeed = new IncreaseGameSpeed ();
	static MainMenuCredits MainMenuCredits = new MainMenuCredits ();
	static MainMenuExit MainMenuExit = new MainMenuExit();
	static MainMenuLoad MainMenuLoad = new MainMenuLoad ();
	static MainMenuOptions MainMenuOptions = new MainMenuOptions ();
	static MainMenuSave MainMenuSave = new MainMenuSave();
	static MainMenuTutorial MainMenuTutorial = new  MainMenuTutorial();
	static ManagerModeAdministration ManagerModeAdministration = new ManagerModeAdministration ();
	static ManagerModeHome ManagerModeHome = new ManagerModeHome ();
	static ManagerModePlayers ManagerModePlayers = new ManagerModePlayers ();
	static ManagerModeQuickFunction1 ManagerModeQuickFunction1 = new ManagerModeQuickFunction1 ();
	static ManagerModeQuickFunction2 ManagerModeQuickFunction2 = new  ManagerModeQuickFunction2();
	static ManagerModeTeams ManagerModeTeams = new  ManagerModeTeams();
	static ManagerModeTournaments ManagerModeTournaments = new ManagerModeTournaments ();
	static NextSong NextSong = new  NextSong();
	static NotificationDismissSlot1 NotificationDismissSlot1 = new  NotificationDismissSlot1();
	static NotificationDismissSlot10 NotificationDismissSlot10 = new NotificationDismissSlot10 ();
	static NotificationDismissSlot11 NotificationDismissSlot11 = new  NotificationDismissSlot11();
	static NotificationDismissSlot12 NotificationDismissSlot12 = new  NotificationDismissSlot12();
	static NotificationDismissSlot13 NotificationDismissSlot13 = new  NotificationDismissSlot13();
	static NotificationDismissSlot14 NotificationDismissSlot14 = new  NotificationDismissSlot14();
	static NotificationDismissSlot2 NotificationDismissSlot2 = new  NotificationDismissSlot2();
	static NotificationDismissSlot3 NotificationDismissSlot3 = new NotificationDismissSlot3 ();
	static NotificationDismissSlot4 NotificationDismissSlot4 = new  NotificationDismissSlot4();
	static NotificationDismissSlot5 NotificationDismissSlot5 = new  NotificationDismissSlot5();
	static NotificationDismissSlot6 NotificationDismissSlot6 = new  NotificationDismissSlot6();
	static NotificationDismissSlot7 NotificationDismissSlot7 = new  NotificationDismissSlot7();
	static NotificationDismissSlot8 NotificationDismissSlot8 = new  NotificationDismissSlot8();
	static NotificationDismissSlot9 NotificationDismissSlot9 = new NotificationDismissSlot9();
	static OptionsConsoleEnable OptionsConsoleEnable = new  OptionsConsoleEnable();
	static PlayersAddPlayer PlayersAddPlayer = new  PlayersAddPlayer();
	static PlayersPageDown PlayersPageDown = new  PlayersPageDown();
	static PlayersPageUp PlayersPageUp = new PlayersPageUp ();
	static PlayersTop10 PlayersTop10 = new PlayersTop10 ();
	static PlayersTop10Mode1 PlayersTop10Mode1 = new PlayersTop10Mode1 ();
	static PlayersTop10Mode2 PlayersTop10Mode2= new  PlayersTop10Mode2();
	static PlayersTop10Mode3 PlayersTop10Mode3 = new  PlayersTop10Mode3();
	static PlayersViewPlayer PlayersViewPlayer = new  PlayersViewPlayer();
	static PlayPause PlayPause = new PlayPause ();
	static PlayPauseGameSpeed PlayPauseGameSpeed = new  PlayPauseGameSpeed();
	static PreviousSong PreviousSong = new  PreviousSong();
	static SelectionManagerMode SelectionManagerMode = new  SelectionManagerMode();
	static SelectionSpectatorMode SelectionSpectatorMode = new SelectionSpectatorMode ();
	static SpectatorModeAdministration SpectatorModeAdministration = new  SpectatorModeAdministration();
	static SpectatorModeHome SpectatorModeHome = new  SpectatorModeHome();
	static SpectatorModePlayers SpectatorModePlayers = new  SpectatorModePlayers();
	static SpectatorModeQuickFunction1 SpectatorModeQuickFunction1 = new  SpectatorModeQuickFunction1();
	static SpectatorModeQuickFunction2 SpectatorModeQuickFunction2 = new  SpectatorModeQuickFunction2();
	static SpectatorModeTeams SpectatorModeTeams = new  SpectatorModeTeams();
	static SpectatorModeTournaments SpectatorModeTournaments = new  SpectatorModeTournaments();
	static TeamsAddTeam TeamsAddTeam = new  TeamsAddTeam();
	static TeamsForceTrade TeamsForceTrade = new TeamsForceTrade ();
	static TeamsViewTeam TeamsViewTeam = new  TeamsViewTeam();
	static TeamsViewTop10 TeamsViewTop10 = new  TeamsViewTop10();
	static TopTenTeamsMode1 TopTenTeamsMode1 = new TopTenTeamsMode1 ();
	static TopTenTeamsMode2 TopTenTeamsMode2 = new TopTenTeamsMode2 ();
	static TopTenTeamsMode3 TopTenTeamsMode3 = new  TopTenTeamsMode3();
	static TopTenTeamsMode4 TopTenTeamsMode4 = new  TopTenTeamsMode4();
	static TournamentsMajor TournamentsMajor = new TournamentsMajor ();
	static TournamentsMinor4 TournamentsMinor4 = new  TournamentsMinor4();
	static  TournamentsMinor8 TournamentsMinor8 = new TournamentsMinor8 ();
	static TournamentsWorld TournamentsWorld = new TournamentsWorld  ();
	static ViewPlayersSlot1 ViewPlayersSlot1 = new ViewPlayersSlot1 ();
	static ViewPlayersSlot10 ViewPlayersSlot10 = new ViewPlayersSlot10 ();
	static ViewPlayersSlot2 ViewPlayersSlot2 = new  ViewPlayersSlot2();
	static ViewPlayersSlot3 ViewPlayersSlot3 = new ViewPlayersSlot3();
	static ViewPlayersSlot4 ViewPlayersSlot4 = new ViewPlayersSlot4 ();
	static ViewPlayersSlot5 ViewPlayersSlot5 = new  ViewPlayersSlot5();
	static ViewPlayersSlot6 ViewPlayersSlot6 = new  ViewPlayersSlot6();
	static ViewPlayersSlot7 ViewPlayersSlot7 = new ViewPlayersSlot7 ();
	static ViewPlayersSlot8 ViewPlayersSlot8 = new ViewPlayersSlot8 ();
	static ViewPlayersSlot9 ViewPlayersSlot9 = new ViewPlayersSlot9 ();
	static ViewTeamPageDown ViewTeamsPageDown = new ViewTeamPageDown ();
	static ViewTeamPageUp ViewTeamsPageUp = new ViewTeamPageUp ();
	static ViewTeamsSlot1 ViewTeamsSlot1 = new ViewTeamsSlot1 ();
	static ViewTeamsSlot10 ViewTeamsSlot10 = new  ViewTeamsSlot10();
	static ViewTeamsSlot2 ViewTeamsSlot2 = new ViewTeamsSlot2();
	static ViewTeamsSlot3 ViewTeamsSlot3 = new ViewTeamsSlot3();
	static ViewTeamsSlot4 ViewTeamsSlot4 = new ViewTeamsSlot4();
	static ViewTeamsSlot5 ViewTeamsSlot5 = new ViewTeamsSlot5();
	static ViewTeamsSlot6 ViewTeamsSlot6 = new ViewTeamsSlot6();
	static ViewTeamsSlot7 ViewTeamsSlot7 = new ViewTeamsSlot7();
	static ViewTeamsSlot8 ViewTeamsSlot8 = new ViewTeamsSlot8();
	static ViewTeamsSlot9 ViewTeamsSlot9 = new ViewTeamsSlot9();
	static SLIDERTEST SLIDERTEST = new SLIDERTEST();
	
	
	
	public ButtonCollection()
	{
		collection.add(SLIDERTEST);
		collection.add(MainMenuPlayButton);
		collection.add(AddTeamAbb );
		collection.add(AddTeamName);
		collection.add( CornerBack);
		collection.add(CornerMenu );
		collection.add(DecreaseGameSpeed );
		collection.add(IncreaseGameSpeed );
		collection.add(MainMenuCredits );
		collection.add(MainMenuExit );
		collection.add(MainMenuLoad );
		collection.add(MainMenuOptions );
		collection.add(MainMenuSave );
		collection.add(MainMenuTutorial );
		collection.add(ManagerModeAdministration );
		collection.add(ManagerModeHome );
		collection.add( ManagerModePlayers);
		collection.add(ManagerModeQuickFunction1 );
		collection.add(ManagerModeQuickFunction2 );
		collection.add(ManagerModeTeams );
		collection.add( ManagerModeTournaments);
		collection.add( NextSong);
		collection.add(NotificationDismissSlot1 );
		collection.add( NotificationDismissSlot10);
		collection.add( NotificationDismissSlot11);
		collection.add( NotificationDismissSlot12);
		collection.add( NotificationDismissSlot13);
		collection.add( NotificationDismissSlot14);
		collection.add(NotificationDismissSlot2 );
		collection.add( NotificationDismissSlot3);
		collection.add( NotificationDismissSlot4);
		collection.add( NotificationDismissSlot5);
		collection.add(NotificationDismissSlot6 );
		collection.add( NotificationDismissSlot7);
		collection.add(NotificationDismissSlot8 );
		collection.add(NotificationDismissSlot9 );
		collection.add(OptionsConsoleEnable );
		collection.add( PlayersAddPlayer);
		collection.add(PlayersPageDown );
		collection.add(PlayersPageUp );
		collection.add( PlayersTop10);
		collection.add( PlayersTop10Mode1);
		collection.add(PlayersTop10Mode2 );
		collection.add( PlayersTop10Mode3);
		collection.add( PlayPause);
		collection.add(PlayPauseGameSpeed );
		collection.add(PreviousSong );
		collection.add(PlayersViewPlayer);
		collection.add( SelectionManagerMode);
		collection.add(SelectionSpectatorMode );
		collection.add(SpectatorModeAdministration );
		collection.add(SpectatorModeHome );
		collection.add( SpectatorModePlayers);
		collection.add(SpectatorModeQuickFunction1 );
		collection.add(SpectatorModeQuickFunction2 );
		collection.add(SpectatorModeTeams);
		collection.add( SpectatorModeTournaments);
		collection.add(TeamsAddTeam );
		collection.add(TeamsForceTrade );
		collection.add( TeamsViewTeam);
		collection.add( TeamsViewTop10);
		collection.add(TopTenTeamsMode1 );
		collection.add(TopTenTeamsMode2 );
		collection.add(TopTenTeamsMode3 );
		collection.add(TopTenTeamsMode4 );
		collection.add(TournamentsMajor );
		collection.add( TournamentsMinor4);
		collection.add(TournamentsMinor8 );
		collection.add(TournamentsWorld );
		collection.add(ViewPlayersSlot1 );
		collection.add( ViewPlayersSlot10);
		collection.add(ViewPlayersSlot2 );
		collection.add(ViewPlayersSlot3 );
		collection.add(ViewPlayersSlot4 );
		collection.add(ViewPlayersSlot5 );
		collection.add(ViewPlayersSlot6 );
		collection.add(ViewPlayersSlot7 );
		collection.add(ViewPlayersSlot8 );
		collection.add(ViewPlayersSlot9 );
		collection.add(ViewTeamsPageDown );
		collection.add(ViewTeamsPageUp );
		collection.add(ViewTeamsSlot1 );
		collection.add(ViewTeamsSlot10 );
		collection.add(ViewTeamsSlot2);
		collection.add(ViewTeamsSlot3);
		collection.add(ViewTeamsSlot4);
		collection.add(ViewTeamsSlot5);
		collection.add(ViewTeamsSlot6);
		collection.add(ViewTeamsSlot7);
		collection.add(ViewTeamsSlot8);
		collection.add(ViewTeamsSlot9);
		
		
		

		
	}
	
	public ArrayList<Object> getCollection() {
		return collection;
	}
	
	

}
