package parser;

import java.util.ArrayList;

/**
 * The master controller class, that handles taking in multiple CSVs, turning each into its own Document. Creates a master list of Rooms, finds all Courses, filters Courses
 * to relevant ones, schedules those Courses, and creates an easy access list. Also contains lookup methods for Courses and Rooms.
 * @author jwillson
 *
 */
public class Aggregator {
	/**
	 * An array of all documents parsed
	 */
	private Document[] documents;
	/**
	 * An array of all Courses parsed
	 */
	private Course[] allCourses;
	/**
	 * An array of all Courses that are relevant to the application
	 */
	private Course[] courses;
	/**
	 * An array of all valid Rooms
	 */
	private Room[] rooms;
	/**
	 * An array of all valid Professors *UNIMPLEMENTED*
	 */
	private Professor[] professors;
	/**
	 * An array of all relevant Course names with a 1:1 mapping for ease of lookup and display
	 */
	private String[] coursesNames;
	
	/**
	 * Constructor of aggregator, uses an array of file paths as Strings
	 * @param paths - an array of file paths
	 */
	public Aggregator(String[] paths)
	{
		rooms = createRooms();
		documents = new Document[paths.length];
		for(int i = 0; i < paths.length; i++) {
			documents[i] = new Document(paths[i],rooms);
		}
		allCourses = findCourses();//Isolate all courses in document
		parentChildLinkage();//Link parent and child courses for allCourses
		courses = refineCourses();//Isolate all courses we can modify/care about
		scheduleCourses();
		processCourseNames();
	}
	
	
	//GETTERS
	/**
	 * Accessor for the array of relevant Courses
	 * @return all relevant Courses
	 */
	public Course[] getCourses()
	{
		return courses;
	}
	
	/**
	 * Accessor for the array of all Course names
	 * @return all relevant Course names
	 */
	public String[] getCourseNames()
	{
		return coursesNames;
	}
	
	/**
	 * Accessor for the array of all valid Rooms
	 * @return all valid Rooms
	 */
	public Room[] getRooms()
	{
		return rooms;
	}
	
	//Functional Methods
	/**
	 * Aggregator level call to output all file changes, application should be closed after execution
	 * @return true if output is successful
	 */
	public boolean finalOutput()
	{
		boolean flag = true;
		for(int i = 0; i<documents.length; i++)
		{
			flag = flag && documents[i].output();
		}
		return flag;
	}
	
	//Finders
	/**
	 * Lookup by String to find the associated relevant Course
	 * @param name - the name of the Course being looked for in XXXX YYYY-ZZZ format
	 * @return the associated relevant Course object
	 */
	public Course findCourse(String name)
	{
		for(int i = 0; i < coursesNames.length; i++)
		{
			if(name.contentEquals(coursesNames[i]))
				return courses[i];
		}
		return null;
	}
	/**
	 * Lookup by String to find the associated valid Room
	 * @param name - the number of the room
	 * @return the associated Room object
	 */
	public Room findRoomByNum(String name)
	{
		for(int i = 0; i < rooms.length; i++)
		{
			if(name.contentEquals(rooms[i].getRoomNumber()))
				return rooms[i];
		}
		return null;
	}
	
	//Helpers
	/**
	 * Creates an array of Rooms based off the defined parameters in Constants
	 * @return all valid Rooms
	 */
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
	
	/**
	 * Iterates through all Lines in all Documents, finding ones that are Courses and making an array of those
	 * @return all Courses
	 */
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
	
	/**
	 * Iterates through all Courses, finding those Courses that either have a parent or child and linking them together
	 */
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
	
	/**
	 * Iterates through all Courses, filtering to those relevant to the application
	 * @return all Courses that are scheduled in PKI
	 */
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
	
	/**
	 * Iterates through all Courses at PKI, scheduling them into the TimeTables of their Rooms
	 */
	private void scheduleCourses()
	{
		for(int i = 0; i < courses.length; i++)
		{
			try
			{
				courses[i].schedule();
			}
			catch(ScheduleException e)
			{
				System.out.println(courses[i].toString() + "\n" + e.toString());
			}
		}
	}
	
	/**
	 * Iterates through all Courses at PKI, making a list of their Course+Section name that has a 1:1 correspondence by index to the Course list
	 */
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
	
	/**
	 * Standard output format for debugging
	 */
	@Override
	public String toString()
	{
		String ret ="";
		for(int i = 0; i< documents.length; i++) {
			ret = ret.concat(documents[i].toString() + "\n\n");
		}
		return ret;
	}
	
	public Document getDocOne()
	{
		return documents[0];
	}
	
}