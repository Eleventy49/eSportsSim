package Game;

public class Staff {
	public static enum StaffTypes {PerformanceTrainer, TeamCoach, FinancialManager;}
	public StaffTypes type = null;
	private Team isOwnedBy;
	private int level = 0;
	public Staff(int n, Team t)
	{
		switch(n) {
		case 1:
			type = StaffTypes.PerformanceTrainer;
			PerformanceTrainer();
			break;	
		case 2:
			type = StaffTypes.TeamCoach;
			TeamCoach();
			break;	
		case 3:
			type = StaffTypes.FinancialManager;
			FinancialManager();
			break;	
		}		
	}
	public Staff(StaffTypes s, int level)
	{
		switch(s)
		{
		case PerformanceTrainer:
			type = StaffTypes.PerformanceTrainer;
			PerformanceTrainer();
			break;	
		case TeamCoach:
			type = StaffTypes.TeamCoach;
			TeamCoach();
			break;	
		case FinancialManager:
			type = StaffTypes.FinancialManager;
			FinancialManager();
			break;
			
		
		}	
		this.level = level;
	}
	public void PerformanceTrainer()
	{
	}
	public void TeamCoach()
	{
	}
	public void FinancialManager()
	{
	}
	public String save() {
		return type + "," + level + ",";
	}
	public Team getIsOwnedBy() {
		return isOwnedBy;
	}
	public void setIsOwnedBy(Team isOwnedBy) {
		this.isOwnedBy = isOwnedBy;
	}
	public void tick(int fmanagertracker) {
		double fmanBonus = 1 + (fmanagertracker / 10.0); 
		switch(type)
		{
		case PerformanceTrainer:
			
			break;
		case TeamCoach:
			
			break;
		case FinancialManager:
		//	System.out.println(fmanBonus);
			
			
			break;
		}
		
	}	
}
