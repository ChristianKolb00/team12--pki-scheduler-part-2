package parser;

//Extending Line to be able to directly call Line properties
public class Course extends Line{
	private String[] diff;
	private String[] web;
	private int parentC;//-1 when neither, 0 when parent, 1 when child
	private Course parent, childOne, childTwo, childThree;
	private int aggEnroll;
	private boolean changed;//Flag for when fields in course are changed to make display reprocess
	
	//Line reference
	public Course(String line) {
		//Create a standard line
		super(line);
		diff = new String[Constants.COL_COUNT];
		//Copy the line contents to the Course for possible editting
		this.revert();
		//By processing original line now, the values are locked in as immutable
		processWebOriginal();
		changed = true;
		//TODO: Logic to detect parent/child class relationships
		//TODO: aggEnroll = enrollment or sum of enrollments if parent
	}
	
	//Possibly split this
	public void setMeetingPattern(String p)
	{
		diff[Constants.MEET_PATT]=p;
		changed = true;
	}
	
	public void setRoom(String r)
	{
		diff[Constants.ROOM]=r;
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
	
	//TODO: build, base 15 timescale
	protected int[] parseTime()
	{
		int[] ret = {0,0};
		return ret;
	}
	//Same as prior but for Line values
	protected int[] parseOriginalTime()
	{
		int[] ret = {0,0};
		return ret;
	}
	
	//TODO: 1=M, 2=T, etc
	protected int[] parseDay()
	{
		int[] ret = {0,0};
		return ret;
	}
	//Same as prior but for Line values
	protected int[] parseOriginalDay()
	{
		int[] ret = {0,0};
		return ret;
	}
	
	//Accessor methods for formatted output to display on web
	public String[] getOriginalWebDisplay()
	{
		return webOriginal;
	}
	public String[] getWebDisplay()
	{
		if(changed)
			processWeb();
		return web;
	}
	
	protected String getCourseSection()
	{
		return line[Constants.COURSE] + "-" + line[Constants.SEC_NUM];
	}
	
	protected String getBuilding()
	{
		if(line[14].contains("Peter"))
			return "PKI";
		else
			return "Other";
	}
	
	public void revert()
	{
		for(int i = 0; i<line.length; i++)
		{
			diff[i] = line[i];//Since string is immutable this is a deep copy
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