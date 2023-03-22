package parser;

public class TimeTable {
	//2d array representing [day][time in 15s]
	private Course[][] table;
	private Course blocker;
	public TimeTable()
	{
		table = new Course[Constants.NUM_DAYS][Constants.NUM_FIFTEENS];
		blocker = new Course(null);//Might need special handling to create a good readable blocker?
		//Use a blocker course to blackout invalid times
		for(int h=0; h <= table.length; h++)
		{
			for(int i = 0; i < 33; i++)
				table[h][i]=blocker;
			for(int j=85; j < table[h].length; j++)
				table[h][j]=blocker;
		}
	}
	
	//Returns true if all slots for course and slot before and after are available on specified day
	public boolean checkAvailable(int d, int t, int du)
	{
		for (int k = t-1; k < t + du + 1; k++)
			if(table[d][k] != null)
				return false;
		return true;
	}
	
	
	//Sets a Course into the specified time slots
	//Double checks against checkAvailable, throwing ScheduleException if issue
	public void set(Course c) throws ScheduleException
	{
		int[] td = c.parseTime();
		int[] days = c.parseDay();
		//Double check for availability
		for(int i = 0; i < days.length; i++)
		{
			if(!checkAvailable(days[i],td[0],td[1]))
				throw new ScheduleException("Time/day: " + days[i] + ", " + td[0] + " " + td[1]);
		}
		//Add new time
		for(int j = 0; j < days.length; j++)
			for(int k = td[0]; k < td[0] + td[1]; k++)
				table[days[j]][k]=c;
	}
	
	//Unsets a courses original time
	public void release(Course c) throws ScheduleException
	{
		int[] td = c.parseOriginalTime();
		int[] days = c.parseOriginalDay();
		for(int j = 0; j < days.length; j++)
		{
			for(int k = td[0]; k < td[0] + td[1]; k++)
			{
				//Check to make sure its not already descheduled
				if(table[days[j]][k] == c)
					table[days[j]][k]=null;
				else
					throw new ScheduleException("Course already descheduled");
			}
		}
	}
	
	
	//After analysis is completed, this will release the slots the Course was changed too
	public void restoreRelease(Course c) throws ScheduleException
	{
		int[] td = c.parseTime();
		int[] days = c.parseDay();
		for(int j = 0; j < days.length; j++)
		{
			for(int k = td[0]; k < td[0] + td[1]; k++)
			{
				//Check to make sure its not already descheduled
				if(table[days[j]][k] == c)
					table[days[j]][k]=null;
				else
					throw new ScheduleException("Course already descheduled");
			}
		}
	}
	
	//After analysis is completed, AND restoreRelease, this will set the Course's original time slots
	public void restore(Course c) throws ScheduleException
	{
		int[] td = c.parseOriginalTime();
		int[] days = c.parseOriginalDay();
		//No extra check needed because this is only ever called after releaseTime(c)
		for(int j = 0; j < days.length; j++)
		{
			for(int k = td[0]; k < td[0] + td[1]; k++)
			{
				if(table[days[j]][k] != null)
					throw new ScheduleException("Improper restore order");
				table[days[j]][k]=c;
			}
		}
		c.revert();
	}
}
