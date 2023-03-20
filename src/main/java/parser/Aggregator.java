package parser;

import java.util.ArrayList;

public class Aggregator {
	private Document[] documents;
	private Course[] allCourses, courses;
	public Aggregator(String[] paths)
	{
		documents = new Document[paths.length];
		for(int i = 0; i < paths.length; i++) {
			documents[i] = new Document(paths[i]);
		}
		allCourses = findCourses();//Isolate all courses in document
		courses = refineCourses();//Isolate all courses we can modify/care about
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
		ArrayList<Course> course = new ArrayList<Course>();
		//Iterate through entire set of all lines
		for (int i = 0; i < documents.length; i++) {
			for(int j = 0; j<documents[i].docLines.size(); j++) {
				//If line is a valid course, add it to courses
				Line temp = documents[i].docLines.get(j);
				if (temp instanceof Course)
				{
					course.add((Course) temp);
				}
			}
		}
		//Convert arrayList to array to return
		Course[] ret = new Course[course.size()];
		course.toArray(ret);
		return ret;
	}
	
	private Course[] refineCourses()
	{
		ArrayList<Course> course = new ArrayList<Course>();
		for(int i = 0; i < allCourses.length; i++) {
			if(allCourses[i].getBuilding().contentEquals("PKI"))
				course.add(allCourses[i]);
		}
		//TODO: filter by classroom to PKI only
		return courses;
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