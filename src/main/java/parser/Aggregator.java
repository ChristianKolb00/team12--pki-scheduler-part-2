package parser;

import java.util.ArrayList;

public class Aggregator {
	private Document[] documents;
	private Course[] allCourses, courses;
	private Room[] rooms;
	private Professor[] professors;
	private String[] coursesNames;
	public Aggregator(String[] paths)
	{
		documents = new Document[paths.length];
		for(int i = 0; i < paths.length; i++) {
			documents[i] = new Document(paths[i]);
		}
		allCourses = findCourses();//Isolate all courses in document
		parentChildLinkage();//Link parent and child courses for allCourses
		courses = refineCourses();//Isolate all courses we can modify/care about
		rooms = createRooms();
		scheduleCourses();
		processCourseNames();
	}
	
	public Course[] getCourses()
	{
		return courses;
	}
	public String[] getCourseNames()
	{
		return coursesNames;
	}
	
	public Course findCourse(String name)
	{
		for(int i = 0; i < coursesNames.length; i++)
		{
			if(name.contentEquals(coursesNames[i]))
				return courses[i];
		}
		return null;
	}
	
	
	private void processCourseNames()
	{
		ArrayList<String> names = new ArrayList<String>();
		for (int i = 0; i < courses.length; i++) {
			names.add(( courses[i]).getCourseSection());
		}
		String[] ret = new String[names.size()];
		names.toArray(ret);
		coursesNames = ret;
	}
	
	public Room[] getRooms()
	{
		return rooms;
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
	
	private void parentChildLinkage()
	{
		for(int i = 0; i < allCourses.length; i++)
		{
			if(allCourses[i].getPC() == 0)
			{
				String[] children = allCourses[i].getPCField();
				for(int k = 0; k < children.length; k++)
				{
					for(int j = 0; j < allCourses.length; j++)
					{
						if(children[k].equalsIgnoreCase(allCourses[j].getCourseSection()))
						{
							allCourses[i].setPC(k+1,allCourses[j]);
							allCourses[j].setPC(0,allCourses[i]);
							break;
						}
					}
				}
			}
		}
	}
	
	private Course[] refineCourses()
	{
		ArrayList<Course> course = new ArrayList<Course>();
		for(int i = 0; i < allCourses.length; i++) {
			if(allCourses[i].getBuilding().contentEquals("Peter Kiewit Institute") && allCourses[i].getMeetingPattern() >= 0)
			{
				course.add(allCourses[i]);
			}
		}
		Course[] ret = new Course[course.size()];
		course.toArray(ret);
		return ret;
	}
	
	private Room[] createRooms()
	{
		ArrayList<Room> room = new ArrayList<Room>();
		for(int i = 0; i < Constants.pki.length; i++)
		{
			room.add(new Room(Constants.pki[i]));
		}
		Room[] ret = new Room[room.size()];
		room.toArray(ret);
		return ret;
	}
	
	private void scheduleCourses()
	{
		for(int i = 0; i < courses.length; i++)
		{
			try
			{
				courses[i].schedule(rooms);
			}
			catch(ScheduleException e)
			{
				System.out.println(courses[i].toString() + "\n" + e.toString());
			}
		}
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