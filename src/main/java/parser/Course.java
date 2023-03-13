package parser;

//Extending Line to be able to directly call Line properties
public class Course extends Line{
	private String[] diff;
	private String[] web;
	private int parent;//-1 when neither, 0 when parent, 1 when child
	private Course parent, childOne, childTwo, childThree;
	private int aggEnroll;
	private boolean changed;//Flag for when fields in course are changed to make display reprocess
	
	//Line reference
	public Course(String line) {
		//Create a standard line
		super(line);
		diff = new String[38];
		//Copy the line contents to the Course for possible editting
		System.arraycopy(this.line, 0, diff, 0, 37);
		//By processing original line now, the values are locked in as immutable
		processWebOriginal();
		changed = true;
		//TODO: Logic to detect parent/child class relationships
		//TODO: aggEnroll = enrollment or sum of enrollments if parent
	}
	
	public void setMeetingPattern(String p)
	{
		diff[13]=p;
	}
	
	public void setRoom(String r)
	{
		diff[15]=r;
		//Cascad max enrollment set
	}
	
	public void setEnrollment(String e)
	{
		diff[28]=e;
		//Update aggEnroll
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
	
	protected void processWebOriginal()
	{
		//Decide what data to put into webOriginal
		String[] webOriginal = {line[1], line[8] + "-" + line[9], line[11], line[13], line[14], line[15], line[19], line[28],line[29], String.valueOf(aggEnrollOriginal)};
	}
	private void processWeb() {
		//Decide what to put in display
		String[] web = {diff[1], diff[8] + "-" + diff[9], diff[11], diff[13], diff[14], diff[15], diff[19], diff[28], diff[29], String.valueOf(aggEnroll)};
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