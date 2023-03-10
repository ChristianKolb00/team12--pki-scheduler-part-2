package parser;

//Extending Line to be able to directly call Line properties
public class Course {
	private Line course;
	private int docIndex, rowIndex;
	private String[] diff;
	private Course parent, childOne, childTwo, childThree;
	private int aggEnroll;
	
	//Line reference
	public Course(Line line, int doc, int row) {
		course = line;
		docIndex = doc;
		rowIndex = row;
		//Copy contents of line to diff for editing
		diff = new String[38];
		System.arraycopy(line.line, 0, diff, 0, 37);
		//Logic to detect parent/child class relationships
		//aggEnroll = enrollment or sum of enrollments if parent
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