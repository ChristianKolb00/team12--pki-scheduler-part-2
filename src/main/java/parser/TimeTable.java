package parser;

public class TimeTable {
	private Course[][] table;
	private Course blocker;
	public TimeTable()
	{
		table = new Course[4][95];
		//Preset table loops
		blocker = new Course(null);//Might need special handling to create a good readable blocker?
		//Use a blocker course to blackout times
		for(int h=0; h <= table.length; h++)
		{
			for(int i = 0; i < 34; i++)
				table[h][i]=blocker;
			for(int j=84; j < table[h].length; j++)
				table[h][j]=blocker;
		}
	}
	//Returns true if all slots for course and slot before and after are available
	public boolean checkAvailable(int d, int t, int du)
	{
		for (int k = t-1; k < t + du + 1; k++)
			if(table[d][k] != null)
				return false;
		return true;
	}
	
	
	//Sets a Course into the specified time slots
	//Double checks against checkAvailable, throwing ScheduleException if issue
	//Additionally release prior held time slots if applicable
	public void setTime(Course c) throws ScheduleException
	{
		int[] td = c.parseTime();
		int[] days = c.parseDay();
		//Double check for availability
		for(int i = 0; i < days.length; i++)
		{
			if(!checkAvailable(days[i],td[0],td[1]))
				throw new ScheduleException("Time/day: " + days[i] + ", " + td[0] + " " + td[1]);

		}
		//Once confirmed
		releaseTime(c);
		//Add new time
		for(int j = 0; j < days.length; j++)
			for(int k = td[0]; k < td[0] + td[1]; k++)
				table[j][k]=c;
	}
	
	private void releaseTime(Course c)
	{
		int[] td = c.parseOriginalTime();
		int[] days = c.parseOriginalDay();
		for(int j = 0; j < days.length; j++)
			for(int k = td[0]; k < td[0] + td[1]; k++)
				table[j][k]=null;
	}
}
