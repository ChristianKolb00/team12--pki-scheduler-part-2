package util;

import java.util.ArrayList;

import parser.Aggregator;
import parser.Course;
import parser.Room;


public class Utils {
	
	
	String Path = "C:\\Users\\cmlko\\eclipse-workspace\\pkiClassroom\\src\\main\\java\\csvFiles\\BIOI1191.csv";
	String Path2 = "C:\\Users\\cmlko\\eclipse-workspace\\pkiClassroom\\src\\main\\java\\csvFiles\\BMI1191.csv";
	String Path3 = "C:\\Users\\cmlko\\eclipse-workspace\\pkiClassroom\\src\\main\\java\\csvFiles\\CIST_EMIT1191.csv";
	String Path4 = "C:\\Users\\cmlko\\eclipse-workspace\\pkiClassroom\\src\\main\\java\\csvFiles\\CSCI1191.csv";
	String Path5 = "C:\\Users\\cmlko\\eclipse-workspace\\pkiClassroom\\src\\main\\java\\csvFiles\\CYBR1191.csv";
	//String Path6 = "C:\\Users\\cmlko\\eclipse-workspace\\pkiClassroom\\src\\main\\java\\csvFiles\\ISQA1191.csv";
	String Path7 = "C:\\Users\\cmlko\\eclipse-workspace\\pkiClassroom\\src\\main\\java\\csvFiles\\ITIN1191.csv";
	
	String[] AllFile = new String[]{Path, Path2, Path3, Path4, Path5, Path7};
	//String[] AllFile = new String[]{Path};

	Aggregator tester = new Aggregator(AllFile);
	Course[] course = tester.getCourses();
	Room[] rooms = tester.getRooms();
	
	
	// Finds a list of rooms that are open at the same time as the course passed.
	public  String[] findRoomSameTime(Course c,int maxEnrollement){
		Room[] filtered=findRoomsLargerThanMaxEnrollment(maxEnrollement);
		ArrayList<String> findRoomSameTime=new ArrayList<String>();
		
		for (int i=0;i<filtered.length;i++) {
			if (!course[i].getCourseTime().equals(c.getCourseTime())) {
				String ret="Room: "+filtered[i].getRoomNumber()+" ,Capacity: "+
			filtered[i].getCapacity()+" ,Open at "+c.getCourseTime()+" : Yes";
				findRoomSameTime.add(ret);
			}	
		}
		String [] ret=new String[findRoomSameTime.size()];
		findRoomSameTime.toArray(ret);
		return ret;
	}	
	
	
	// Find a list of rooms with larger capacity than the maxEnrollment
	public  Room[] findRoomsLargerThanMaxEnrollment( int maxEnrollment) {
    	ArrayList<Room> largerRooms = new ArrayList<>();
    	
    	for (int i=0;i<rooms.length;i++) {
       		if (rooms[i].getCapacity() >=maxEnrollment) {
        		largerRooms.add(rooms[i]);
	    		}
	    	}
    	Room[] lroom=new Room[largerRooms.size()];
    	largerRooms.toArray(lroom);
		return lroom;

	}
		
	
	
	//Reassign Courses with Same Time to a different Room 
	public String reassignRoomSameTime(Course c,String roomNum){
		for (int i=0;i<course.length;i++) {
			if (!course[i].getCourseTime().equals(c.getCourseTime()) ) {
				course[i].setRoom(roomNum);
			}
			}
		String result=c.getCourseName()+" Room: "+c.getRoomNum()+" was reassigned to Room: Peter Kiewit Institute "+ roomNum;
		return result;
	  }
	
	
	
//	//Use the max enrollment to find array of list like array list of rooms that is larger than max enrollment.
//	public  ArrayList<Room> findRoomsLargerThanMaxEnrollment( int maxEnrollment) {
//    	ArrayList<Room> largerRooms = new ArrayList<>();
//    	
//    	for (int i=0;i<rooms.length;i++) {
//       		if (rooms[i].getCapacity() >=maxEnrollment) {
//        		largerRooms.add(rooms[i]);
//	    		}
//	    	}
//	    
//		return largerRooms;
//	}
	
	
	
	//Create a method to find open time slot.
//	public static int findOpenTS(boolean[] timeSlots) {
//		for(int i = 0; i < timeSlots.length; i++) {
//			if(!timeSlots[i]){
//				return i;
//			}
//		}
//		return -1; //not open timeslots
//	}
//	// Create a method to 
//	
//
//	private void swapRoom(Course class1,Course class2){
//		class1_index=findClass(class1);
//		class2_index=findClass(class2);
//		for (int i = 0; i < courses.length; i++) {
//			String class1Room=courses[class1_index].getRoom()
//			String class2Room=courses[class2_index].getRoom()
//			String tempRoom = class1Room;
//			class1Room = class2Room;
//			class2Room = tempRoom;
//			courses[class1_index].SetRoom(class1Room);
//			courses[class2_index].SetRoom(class2Room);
//			course.add(allCourses[i]);
//					}
//		}
//create a method to check how many time slot the class has taken
//	public int takenTS() {
//		int numTaken = 0;
//		for(boolean taken : timeSlots){
//			if(taken){
//				numTaken++;
//			}
//		}
//		return numTaken;
//	}
	
////The class your are swaping with should have its current enrollment less than the next class max enrollment
////Method--> if_swappable()
//	
//	
//	
//	public boolean checkTimeConf(Course cls){
//		for (Course c : course) {
//			if (c.getTime().equals(cls.getTime()) {
//				return true; // time conflict found
//			}
//		}
//			return false; // no time conflict
//	}
//	public boolean checkRoomConf(Course class){
//		for (Course c : courses) {
//			if (c.getRoom().equals(class.getRoom()) {
//				return true; // time conflict found
//			}
//		}
//		return false; // no time conflict
//	}
//	public boolean checkOverCap(Course class, Room room) {
//		return course.getEnrolledStudents() <= room.getCapacity();
//	}
	
}
