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
		//Dummy setters
		aggEnroll = -1;
		aggEnrollOriginal = -1;
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
		//TODO: Also set maxroom based on new room
		//TODO: Cascad max enrollment set
		changed = true;
	}
	
	public void setRoom(Room r)
	{
		room = r;
		diff[Constants.ROOM] = r.getBuilding() + " " + r.getRoomNumber();
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
		return day;//This might need more
	}
	
	public Room getRoom()
	{
		return room;
	}
	
	public int getEnrollment()
	{
		//Switch for aggenroll here
		return Integer.parseInt(diff[Constants.ENROLLMENT]);
	}
	
	public int getMaxEnrollment()
	{
		return Integer.parseInt(diff[Constants.MAX_ENROLL]);
	}
	
	public void releaseOriginal() throws ScheduleException
	{
		room.release(this);
	}
	
	public void schedule() throws ScheduleException
	{
		room.set(this);
	}
	
	public void release() throws ScheduleException
	{
		room.restoreRelease(this);
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
		time = helper.parseTime(timePieces[0]);
		duration = helper.parseTime(timePieces[1]) - time;
		//Set day using parsed day
		day = helper.parseDays(dayPiece[0]);
		
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
		otime = helper.parseTime(timePieces[0]);
		oduration = helper.parseTime(timePieces[1]) - time;
		//Set day using parsed day
		oday = helper.parseDays(dayPiece[0]);
		
	}
	
	public int parseTime(String timeRange)
	{
		int t = 0;
		if(timeRange.contains("pm"))
				t += 12 * 4;
		//Replace 12pm with 0 for easier hour shifting
		timeRange.replace("12","0");
		String[] minHour = timeRange.split(":",2);
		//If only hours, split give just hours, if mixed earlier split reduced and still functions
		t += Integer.parseInt(minHour[0].split("am|pm")[0]) * 4;
		//If there are minutes
		if(minHour.length > 1)
		{
			//Divide minutes by 15 and round to nearest 15 minute so there is 10-20 minutes between all classes
			t += Math.round(Integer.parseInt(minHour[1].split("am|pm")[0])/15);
		}
		return t;
	}

	
	public int parseDays(String days)
	{
		switch(days)
		{
			case "T": return Constants.T;
			case "W": return Constants.W;
			case "Th": return Constants.Th;
			case "F": return Constants.F;
			case "MW": return Constants.M_W;
			case "TTh": return Constants.T_TH;
			case "Sa": return Constants.Sa;
			default: return -2;
		}
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
		if(line[Constants.ROOM].contains("Peter"))
			return "Peter Kiewit Institute";
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
	
	/*Temporarily deprecated
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
	}*/	
	
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