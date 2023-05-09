package parser;
import java.util.ArrayList;

/**
 * The main function of this object is to create a timetable for M-Sa from 0:00 to 11:45pm using 15min segment, or 96 segment day time. Using this system, courses are scheduled into
 * derivative object types to make sure there is no conflict in those derivatives(Rooms and Professors).
 * @author jwillson
 *
 */
public class TimeTable {
	/**
	 * 2d array representing all 96 segment day time making up M-Sa
	 */
	private Course[][] table;
	/**
	 * A specially created Course used to block blackout times
	 */
	private Course blocker;
	
	/**
	 * Initializes the table by creating, and then applying blackout rules
	 */
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
	
	//GETTER
	/**
	 * Retrieves the Course object at the specified day and time in 96 segment day time
	 * @param d - the day
	 * @param t - the time
	 * @return the Course at specified day and time
	 */
	public Course getCourseAt(int d, int t)
	{
		return table[d][t];
	}
	
	
	//Functional Methods
	/**
	 * Schedules a course at its specified time and days. Uses checkAvailable to ensure it is a valid scheduling.
	 * @param c - the Course to be scheduled
	 * @throws ScheduleException - if the needed time slots are not available, then an exception is thrown with details
	 */
	public void set(Course c) throws ScheduleException
	{
		int t = c.time;
		int d = c.duration;
		int[] days = c.getDays();
		//Double check for availability
		for(int i = 0; i < days.length; i++)
		{
			if(checkAvailable(days[i],t,d).length != 0)
				throw new ScheduleException("Time/day: " + days[i] + ", " + t + " " + d);
		}
		//Add new time
		for(int j = 0; j < days.length; j++)
			for(int k = t; k < t + d; k++)
				table[days[j]][k]=c;
	}	
	
	/**
	 * Deschedules a course at its specified time and days. Double checks to make sure the course is not already descheduled.
	 * @param c - the Course to be descheduled
	 * @throws ScheduleException - if the Course is not currently scheduled, then an exception is thrown
	 */
	public void release(Course c) throws ScheduleException
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
	
	/**
	 * A special variant of schedule, this version uses the immutable Line details to reschedule this Course at its original day and time.
	 * @param c - the Course to be rescheduled
	 * @throws ScheduleException - if the Course is attempted to be rescheduled, before itself and blockers are descheduled, then an exception is thrown
	 */
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
	
	/**
	 * Converts this TimeTable's full schedule into a time column day row csv.
	 * @return a csv ready String of the entire table's descriptors
	 */
	protected String output()
	{
		String ret = "";
		String[] days = new String[] {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
		for (int i = 0; i < table.length; i++)
		{
			ret = ret.concat(days[i] + ",");//First column day
			for (int j = 0; j < table[i].length; j++)
			{
				if(table[i][j] == blocker)//Blackout time check
					ret = ret.concat("Unavailable,");
				else if(table[i][j] == null)//Available check
					ret = ret.concat("Available,");
				else//Course check
					ret = ret.concat(table[i][j].getCourseSection() + ",");
			}
			ret = ret.concat("\n");//Next line for next day
		}
		return ret;
	}
	
	//Helpers
	/**
	 * Used to checking if time slots are available. The length of the returned array can be used to easily define some cases:
	 * length = 0 means the slot is free and scheduling can occur, 1 means a single swap could be possible but needs to be checked in other direction
	 * and 2+ is a case not relevant in current implementation
	 * @param d - day
	 * @param t - time
	 * @param du - duration
	 * @return an array of all Courses that are occupying the checked time slots
	 */
	public Course[] checkAvailable(int d, int t, int du)
	{
		ArrayList<Course> courses = new ArrayList<Course>();
		for (int k = t-1; k < t + du + 1; k++) 
		{
			if(table[d][k] != null && !courses.contains(table[d][k]))
				courses.add(table[d][k]);
		}
		Course[] ret = new Course[courses.size()];
		courses.toArray(ret);
		return ret;
	}
}
