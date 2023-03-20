package parser;

import java.util.ArrayList;

public class Aggregator {
	private Document[] documents;
	private Course[] courses;
	public Aggregator(String[] paths)
	{
		documents = new Document[paths.length];
		for(int i = 0; i < paths.length; i++) {
			documents[i] = new Document(paths[i]);
		}
		courses = findCourses();
	}
	
	public Course[] getCourses()
	{
		return courses;
	}
	
	public String[] getCourseNames()
	{
		ArrayList<String> names = new ArrayList<String>();
		for (int i = 0; i < courses.length; i++) {
			names.add(courses[i].getCourseSection());
		}
		String[] ret = new String[names.size()];
		names.toArray(ret);
		return ret;
	}
	
	//Iterate through all documents finding the Lines that are course flagged
	private Course[] findCourses() {
		ArrayList<Course> courses = new ArrayList<Course>();
		//Iterate through entire set of all lines
		for (int i = 0; i < documents.length; i++) {
			for(int j = 0; j<documents[i].docLines.size(); j++) {
				//If line is a valid course, add it to courses
				Line temp = documents[i].docLines.get(j);
				if (temp instanceof Course)
				{
					courses.add((Course) temp);
				}
			}
		}
		//Convert arrayList to array to return
		Course[] ret = new Course[courses.size()];
		courses.toArray(ret);
		return ret;
	}
	
	@Override
	public String toString()
	{
		String ret ="";
		for(int i = 0; i< documents.length; i++) {
			ret = ret.concat(documents[i].toString() + "\n\n");
		}
		return ret;
	}
}