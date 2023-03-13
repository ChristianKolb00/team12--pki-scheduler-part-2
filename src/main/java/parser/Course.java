package parser;

//Extending Line to be able to directly call Line properties
public class Course extends Line{
	private String[] diff;
	private String[] web;
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
		//TODO: Initialize web/webOriginal to size
		super.processWeb();
		changed = true;
		//TODO: Logic to detect parent/child class relationships
		//TODO: aggEnroll = enrollment or sum of enrollments if parent
	}
	
	//Accessor methods for formatted output to display on web
	public String[] getOriginalWebDisplay()
	{
		return this.webOriginal;
	}
	public String[] getWebDisplay()
	{
		if(changed)
			processWeb();
		return this.web;
	}
	
	@Override
	private void processWeb() {
		//Decide what to put in display
		web = diff;
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