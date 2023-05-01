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
		if(diff[Constants.CROSSLISTINGS].contains("See"))//Child flag
			parentC = 1;
		else if (diff[Constants.CROSSLISTINGS].contains("Also"))
			parentC = 0;
		else
			parentC = -1;
		//TODO: aggEnroll = enrollment or sum of enrollments if parent
		//Dummy setters
		aggEnroll = -1;
		aggEnrollOriginal = -1;
	}
	
	//Possibly split this
	public void setMeetingPattern(String p)
	{
		if(parentC == 1)
		{
			parent.setMeetingPattern(p);
		}
		diff[Constants.MEET_PATT]=p;
		parseDayTime();
		changed = true;
	}
	
	public void setRoom(String r)
	{
		if(parentC == 1)
		{
			parent.setRoom(room);
		}
		diff[Constants.ROOM]=r;
		parseRoomNum();
		//TODO: Also set maxroom based on new room
		//TODO: Cascad max enrollment set
		changed = true;
	}
	
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
	
	public void setMaxEnrollment(String e)
	{
		if(parentC == 1)
		{
			parent.setMaxEnrollment(e);
		}
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
		if(parentC == 1)
			return parent.getRoom();
		return room;
	}
	
	public int getEnrollment()
	{
		//TODO: Switch for aggenroll here
		return Integer.parseInt(diff[Constants.ENROLLMENT]);
	}
	
	public int getMaxEnrollment()
	{
		//TODO: Switch for aggenrollmax
		return Integer.parseInt(diff[Constants.MAX_ENROLL]);
	}
	
	public void releaseOriginal() throws ScheduleException
	{
		if(parentC == 1)
			parent.releaseOriginal();
		else
			room.release(this);
	}
	
	/*
	public void schedule() throws ScheduleException
	{
		if(parentC != 1)
			room.set(this);
	}*/
	
	public void release() throws ScheduleException
	{
		if(parentC == 1)
			parent.release();
		else
			room.restoreRelease(this);
	}
	
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
		if (parentC == 1 || roomNum.contentEquals("0"))
			return;//Child courses are never scheduled and use inherited scheduling
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
		if (diff[Constants.ROOM].contains("Online") || diff[Constants.ROOM].contains("Not"))
		{
			roomNum = "0";
			return;
		}
		String[] roomPieces = diff[Constants.ROOM].split(" ", 5);
		roomNum = roomPieces[roomPieces.length - 1];
	}
	
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
	
	private void parseOriginalDayTime()
	{
		
		if(line[Constants.MEET_PATT].contains("Not") || diff[Constants.MEET_PATT].contains("Online"))
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
		if(line[Constants.ROOM].contains("Peter") && !line[Constants.ROOM].contains("Online"))
			return "Peter Kiewit Institute";
		else
			return "Other";
	}
	
	protected int getPC()
	{
		return parentC;
	}
	
	protected String[] getPCField()
	{
		String ret = diff[Constants.CROSSLISTINGS];
		ret = ret.replace("See ", "");
		ret = ret.replace("Also ", "");
		return ret.split(",");
		
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
	public String getRoomNum() 
	{
		
		return diff[Constants.ROOM];
	}
	public String getCourseTime() 
	{
		
		return diff[Constants.MEET_PATT];
	}
	public String getCourseName() 
	{
		
		return diff[Constants.COURSE];
	}
	public String getSection() 
	{
		
		return diff[Constants.SEC_NUM];
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
	}
	

	public String toString()
	{
		String ret = "";
		for(int i = 0; i<diff.length; i++)
		{
			ret = ret.concat(diff[i]);
		}
		return ret;
	}*/
}