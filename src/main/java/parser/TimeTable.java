package parser;

public class TimeTable {
	//2d array representing [day][time in 15s]
	private Course[][] table;
	private Course blocker;
	public TimeTable()
	{
		table = new Course[Constants.NUM_DAYS][Constants.NUM_FIFTEENS];
		blocker = new Course();//Might need special handling to create a good readable blocker?
		//Use a blocker course to blackout invalid times
		for(int h=0; h <= table.length - 1; h++)
		{
			for(int i = 0; i < 33; i++)
			{
				table[h][i]=blocker;
			}
			for(int j=85; j < table[h].length - 1; j++)
				table[h][j]=blocker;
		}
	}
	
	public Course getCourseAt(int d, int t)
	{
		return table[d][t];
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
		int t = c.time;
		int d = c.duration;
		int[] days = c.getDays();
		//Double check for availability
		for(int i = 0; i < days.length; i++)
		{
			if(!checkAvailable(days[i],t,d))
				throw new ScheduleException("Time/day: " + days[i] + ", " + t + " " + d);
		}
		//Add new time
		for(int j = 0; j < days.length; j++)
			for(int k = t; k < t + d; k++)
				table[days[j]][k]=c;
	}
	
	//Unsets a courses original time
	public void release(Course c) throws ScheduleException
	{
		int t = c.otime;
		int d = c.oduration;
		int[] days = c.getOriginalDays();
		for(int j = 0; j < days.length; j++)
		{
			for(int k = t; k < t + d; k++)
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
		int t = c.time;
		int d = c.duration;
		int[] days = c.getDays();
		for(int j = 0; j < days.length; j++)
		{
			for(int k = t; k < t + d; k++)
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
		int t = c.otime;
		int d = c.oduration;
		int[] days = c.getOriginalDays();
		//No extra check needed because this is only ever called after releaseTime(c)
		for(int j = 0; j < days.length; j++)
		{
			for(int k = t; k < t + d; k++)
			{
				if(table[days[j]][k] != null)
					throw new ScheduleException("Improper restore order");
				table[days[j]][k]=c;
			}
		}
	}
}
