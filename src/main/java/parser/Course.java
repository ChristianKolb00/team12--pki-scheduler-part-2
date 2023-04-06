package parser;
import util.helper;
//Extending Line to be able to directly call Line properties
public class Course extends Line{
	private String[] diff;
	
	private int parentC;//-1 when neither, 0 when parent, 1 when child
	protected int day,time,duration;
	protected String roomNum;//
	private Room room;
	private Course parent, childOne, childTwo, childThree;
	private int aggEnroll;
	private boolean changed;//Flag for when fields in course are changed to make display reprocess
	helper u = new helper();
	
	public Course()
	{
		super();
	}
	
	//Line reference
	public Course(String line) {
		//Create a standard line
		super(line);
		diff = new String[Constants.COL_COUNT];
		//Copy the line contents to the Course for possible editting
		this.revert();
		//By processing original line now, the values are locked in as immutable
		changed = false;
		parseOriginalDayTime();
		parseDayTime();
		parseRoomNum();
		//TODO: Logic to detect parent/child class relationships
		//TODO: aggEnroll = enrollment or sum of enrollments if parent
	}
	
	//Possibly split this
	public void setMeetingPattern(String p)
	{
		diff[Constants.MEET_PATT]=p;
		parseDayTime();
		changed = true;
	}
	
	public void setRoom(String r)
	{
		diff[Constants.ROOM]=r;
		parseRoomNum();
		//Also set maxroom based on new room
		//Cascad max enrollment set
		changed = true;
	}
	
	public void setMaxEnrollment(String e)
	{
		diff[Constants.MAX_ENROLL]=e;
		//Update aggEnroll
		changed = true;
	}
	
	public int getMeetingPattern()
	{
		return day;
	}
	
	protected int[] getDays()
	{
		if(this.day <= 4)
			return new int[] {day};
		else if (day == 5)
			return new int[] {Constants.M, Constants.W};
		else if (day == 6)
			return new int[] {Constants.T, Constants.T_TH};
		else
			return new int[] {};
	}
	
	protected int[] getOriginalDays()
	{
		if(this.oday <= 4)
			return new int[] {day};
		else if (oday == 5)
			return new int[] {Constants.M, Constants.W};
		else if (oday == 6)
			return new int[] {Constants.T, Constants.T_TH};
		else
			return new int[] {};
	}
	
	
	public void schedule(Room[] rooms) throws ScheduleException
	{
		boolean found = false;
		//Find room
		for (int i = 0; i < rooms.length; i++)
		{
			if(rooms[i].getRoomNumber().equals(roomNum))
			{
				room = rooms[i];
				found = true;
				break;
			}
		}
		if (!found)
			throw new ScheduleException("Room not found!");
		//Attempt to set using parsed values on room object
		else
		{
			room.set(this);
			
		}
	}
	
	private void parseRoomNum()
	{
		if (diff[Constants.ROOM].contains("Online|Announced"))
		{
			roomNum = "0";
			return;
		}
		String[] roomPieces = diff[Constants.ROOM].split(" ", 5);
		roomNum = roomPieces[roomPieces.length - 1];
	}
	
	private void parseDayTime()
	{
		if(diff[Constants.MEET_PATT].contains("Not"))
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
		time = u.parseTime(timePieces[0]);
		duration = u.parseTime(timePieces[1]) - time;
		//Set day using parsed day
		day = u.parseDays(dayPiece[0]);
		
	}
	
	private void parseOriginalDayTime()
	{
		
		if(line[Constants.MEET_PATT].contains("Not"))
		{
			otime = -1;
			oduration = -1;
			oday = -1;
			return;
		}
		//Split off day into [0]
		String[] dayPiece = line[Constants.MEET_PATT].split(" ", 2);
		//Split off time into [0] and [1]
		String[] timePieces = dayPiece[1].split("-",2);
		//Set time and duration using parsed time
		otime = u.parseTime(timePieces[0]);
		oduration = u.parseTime(timePieces[1]) - time;
		//Set day using parsed day
		oday = u.parseDays(dayPiece[0]);
		
	}
	
	
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
	
	protected String getCourseSection()
	{
		return line[Constants.COURSE] + "-" + line[Constants.SEC_NUM];
	}
	
	protected String getBuilding()
	{
		if(line[15].contains("Peter"))
			return "PKI";
		else
			return "Other";
	}
	
	public void revert()
	{
		//Only revert if something has changed
		if (changed = true)
		{
			for(int i = 0; i<line.length; i++)
			{
				diff[i] = line[i];//Since string is immutable this is a deep copy
			}
		}
		changed = true;
	}
	protected void processWebOriginal()
	{
		//Decide what data to put into webOriginal
		String[] webOriginal = {line[Constants.COURSE] + "-" + line[Constants.SEC_NUM], line[Constants.SEC_TYPE], line[Constants.MEET_PATT], 
				line[Constants.INSTRUCTOR], line[Constants.ROOM], line[Constants.ENROLLMENT],line[Constants.MAX_ENROLL], String.valueOf(aggEnrollOriginal)};
	}
	private void processWeb() {
		//Decide what to put in display
		String[] web = {diff[Constants.COURSE] + "-" + diff[Constants.SEC_NUM], diff[Constants.SEC_TYPE], diff[Constants.MEET_PATT], 
				diff[Constants.INSTRUCTOR], diff[Constants.ROOM], diff[Constants.ENROLLMENT], diff[Constants.MAX_ENROLL], String.valueOf(aggEnroll)};
		parseDayTime();
		parseRoomNum();
		changed = false;
	}	
	
	//Getter via line
	//Setter via diff
	//Apply will apply diff to line
	//Discard will reclone line over diff
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