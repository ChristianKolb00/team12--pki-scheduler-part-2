package parser;
import util.helper;
/**
 * Course is a subclass of Line. It utilizes Line's immutable nature to always keep a clean revert state, but allows editting and more complex usage and parsing for
 * Line's that are actually Courses and not just headers/fillers in the CSV.
 * @author jwillson
 *
 */
public class Course extends Line{
	/**
	 * An array that is the split row of a csv
	 */
	private String[] diff;
	/**
	 * The courses current day pattern, time, and duration
	 */
	protected int day,time,duration;
	/**
	 * The room object this course is scheduled in.
	 */
	private Room room;
	/**
	 * Flag boolean for when Course diverges from Line. Can be used to find the pieces for a changelog.
	 */
	protected boolean changed;
	
	//Likely being moved to new child class if time
	private int parentC;//-1 when neither, 0 when parent, 1 when child
	private Course parent, childOne, childTwo, childThree;
	private int aggEnroll, aggEnrollOriginal;
	
	/**
	 * Default constructor, only used for fully null Blocker in TimeTable
	 */
	public Course()
	{
		super();
	}
	
	/**
	 * Initializes the immutable Line, creates a local copy of the pieces, and then parses it apart into the relevant information for modifying.
	 * @param line - a row from Document as a String
	 * @param rooms - an array of all valid Rooms
	 */
	public Course(String line, Room[] rooms) {
		//Create a standard line
		super(line);
		diff = new String[Constants.COL_COUNT];
		//Copy the line contents to the Course for possible editting
		this.revert();
		//By processing original line now, the values are locked in as immutable
		changed = false;
		parseOriginalDayTime();
		parseDayTime();
		parseRoom(rooms);
		if(diff[Constants.CROSSLISTINGS].contains("See"))//Child flag
			parentC = 1;
		else if (diff[Constants.CROSSLISTINGS].contains("Also"))//Parent flag
			parentC = 0;
		else//Normal flag
			parentC = -1;
		//TODO: aggEnroll = enrollment or sum of enrollments if parent
		//Dummy setters
		aggEnroll = -1;
		aggEnrollOriginal = -1;
	}
	
	
	//SETTERS
	/**
	 * Sets the meeting pattern for this course
	 * @param patt - a new meeting pattern in String form
	 */
	public void setCourseMeeting(String patt)
	{
		if(parentC == 1)
		{
			parent.setCourseMeeting(patt);
		}
		diff[Constants.MEET_PATT]=patt;
		parseDayTime();
		changed = true;
	}

	/**
	 * Sets the room for this course
	 * @param r - a room object that this course is in
	 */
	public void setRoom(Room r)
	{
		if(parentC == 1)
		{
			parent.setRoom(r);
		}
		room = r;
		diff[Constants.ROOM] = r.getBuilding() + " " + r.getRoomNumber();
		changed = true;
	}
	
	/**
	 * Sets the max enrollment for this course
	 * @param m - a String that is a number that is the max enrollment
	 */
	public void setMaxEnrollment(String m)
	{
		if(parentC == 1)
		{
			parent.setMaxEnrollment(m);
		}
		diff[Constants.MAX_ENROLL]=m;
		//Update aggEnroll
		changed = true;
	}
	
	
	//GETTERS
	/**
	 * Accessor for the Course Section listing to be displayed on front end and compared for equality
	 * @return the Course and Section number of this Course
	 */
	public String getCourseSection()
	{
		return line[Constants.COURSE] + "-" + line[Constants.SEC_NUM];
	}
	
	/**
	 * Accessor for the Building that this Course is scheduled in
	 * @return what building this Courses room is in
	 */
	protected String getBuilding()
	{
		if(line[Constants.ROOM].contains("Peter") && !line[Constants.ROOM].contains("Online"))
			return "Peter Kiewit Institute";
		else
			return "Other";
	}
	
	/**
	 * Accessor for the day pattern that this Course is scheduled on
	 * @return a pattern, can be split into an array of days by a helper
	 */
	public int getMeetingPattern()
	{
		return day;
	}
	
	/**
	 * Accessor for the time that this Course is scheduled at
	 * @return the start time of this course in 96 segment day time
	 */
	public int getMeetingTime()
	{
		return time;
	}
	
	/**
	 * Accessor for the duration of this Course
	 * @return the duration of this course in 96 segment day time
	 */
	public int getMeetingDuration()
	{
		return duration;
	}
	
	/**
	 * Accessor for the Meeting Pattern full String
	 * @return the contents of the column in the CSV representing the meeting pattern
	 */
	public String getCourseMeeting() 
	{
		return diff[Constants.MEET_PATT];
	}
	
	/**
	 * Accessor for the Room this Course is currently in
	 * @return the Room the course is in
	 */
	public Room getRoom()
	{
		if(parentC == 1)
			return parent.getRoom();
		return room;
	}
	
	/**
	 * Accessor for the current enrollment of this Course
	 * @return the current enrollment
	 */
	public int getEnrollment()
	{
		//TODO: Switch for aggenroll here,offload to child class
		return Integer.parseInt(diff[Constants.ENROLLMENT]);
	}
	
	/**
	 * Accessor for the max enrollment of this Course
	 * @return the current max enrollment
	 */
	public int getMaxEnrollment()
	{
		//TODO: Switch for aggenrollmax, offload to child class
		return Integer.parseInt(diff[Constants.MAX_ENROLL]);
	}
	
	//Functional Methods
	/**
	 * Schedules this Course in its assigned Room based on its meeting pattern
	 * @throws ScheduleException - when this course does not have a room, it cannot be scheduled
	 */
	public void schedule() throws ScheduleException
	{
		if (parentC == 1)
			return;//Child courses are never scheduled and use inherited scheduling
		if(room != null) {
			room.set(this);
		}
		else {
			throw new ScheduleException("Room not found!/Online Course");
		}
		
	}
	
	/**
	 * Releases the currently assigned schedule slot for this course
	 * @throws ScheduleException - when this course is not currently scheduled, it cannot be descheduled
	 */
	public void release() throws ScheduleException
	{
		if(parentC == 1)
			parent.release();
		else
			room.release(this);
	}
	
	/**
	 * Reverts all values of this Course to their default values form the CSV using the Line parent
	 */
	public void revert()
	{
		if(parentC == 1)
			parent.revert();
		//Only revert if something has changed
		if (changed = true)
		{
			for(int i = 0; i<line.length; i++)
			{
				diff[i] = line[i];//Since string is immutable this is a deep copy
			}
		}
		changed = false;
	}
	
	
	//Helper Methods
	/**
	 * Converts the day pattern into an array of days
	 * @return an array containing the numeric value of days this course is on
	 */
	protected int[] getDays()
	{
		if(this.day <= 4)
			return new int[] {day};
		else if (day == 5)
			return new int[] {Constants.M, Constants.W};
		else if (day == 6)
			return new int[] {Constants.T, Constants.Th};
		else
			return new int[] {};
	}
	
	/**
	 * Finds the Room object associated with the String that is the room column in the CSV	
	 * @param rooms - an array of all valid Rooms
	 */
	private void parseRoom(Room[] rooms)
	{
		if (diff[Constants.ROOM].contains("Online") || diff[Constants.ROOM].contains("Not"))
		{
			room = null;
			return;
		}
		String[] roomPieces = diff[Constants.ROOM].split(" ", 5);
		for(int i = 0; i<rooms.length; i++)
		{
			if(rooms[i].getRoomNumber().equalsIgnoreCase(roomPieces[roomPieces.length - 1]))
			{
				room = rooms[i];
				break;
			}
		}
	}
	
	/**
	 * Converts the Meeting Pattern field from a String to a multipiece int representation of the meeting pattern
	 */
	private void parseDayTime()
	{
		if(diff[Constants.MEET_PATT].contains("Not") || diff[Constants.MEET_PATT].contains("Online"))
		{
			time = -1;
			duration = -1;
			day = -1;
			return;
		}
		//Split off day into [0]
		String[] dayPiece = diff[Constants.MEET_PATT].split(" ", 2);
		//Split off time into [0] and [1]
		String[] timePieces = dayPiece[1].split("-",2);
		//Set time and duration using parsed time
		time = helper.parseTime(timePieces[0]);
		duration = helper.parseTime(timePieces[1]) - time;
		//Set day using parsed day
		day = helper.parseDays(dayPiece[0]);
		
	}
	
/* POSSIBLY DEFUNCT
	//Accessor methods for formatted output to display on web
	public String[] getOriginalWebDisplay()
	{
		String[] ret = {line[Constants.COURSE] + "-" + line[Constants.SEC_NUM], line[Constants.SEC_TYPE], line[Constants.MEET_PATT], 
				line[Constants.INSTRUCTOR], line[Constants.ROOM], line[Constants.ENROLLMENT],line[Constants.MAX_ENROLL], String.valueOf(aggEnrollOriginal)};
		return ret;
	}
	public String[] getWebDisplay()
	{
		String[] ret = {diff[Constants.COURSE] + "-" + diff[Constants.SEC_NUM], diff[Constants.SEC_TYPE], diff[Constants.MEET_PATT], 
				diff[Constants.INSTRUCTOR], diff[Constants.ROOM], diff[Constants.ENROLLMENT], diff[Constants.MAX_ENROLL], String.valueOf(aggEnroll)};
		return ret;
	}
	*/
	
	//SPECIAL PIECES FOR PARENT/CHILD move to child class
	protected int getPC()
	{
		return parentC;
	}
	
	protected String[] getPCField()
	{
		String ret = diff[Constants.CROSSLISTINGS];
		ret = ret.replace("See", "");
		ret = ret.replace("Also", "");
		String temp[] = ret.split(",");
		for (int i = 0; i < temp.length; i++)
			temp[i] = temp[i].substring(1);//Remove space at start
		return temp;
		
	}
	
	protected void setPC(int a, Course b)
	{
		switch(a)
		{
			case 0: parent = b;
			break;
			case 1: childOne=b;
			break;
			case 2: childTwo=b;
			break;
			case 3: childThree=b;
			break;
			default: System.out.println("Something is wrong");
		}
	}
	
	/**
	 * Converts the String array of a row back into CSV format as a single String
	 * @return a csv formatted row
	 */
	protected String output() {
		String ret = "";
		for (int i =0; i<diff.length; i++)
		{
			if(diff[i].contentEquals(""))
				ret = ret.concat(",");
			else
				ret = ret.concat("\"" + diff[i] + "\",");
		}
		return ret.concat("\n");
	}
	
	/**
	 * Standard output format for debugging
	 */
	public String toString()
	{
		String ret = "";
		for(int i = 0; i<diff.length; i++)
		{
			ret = ret.concat(diff[i]);
		}
		return ret;
	}
}