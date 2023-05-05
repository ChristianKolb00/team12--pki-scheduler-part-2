package util;

import java.util.ArrayList;
import java.util.List;

import parser.Aggregator;
import parser.Constants;
import parser.Course;
import parser.Room;


public class Utils {
	private Aggregator A;
	private Course[] course;
	private Room[] rooms;
	public Utils(Aggregator A) {
		this.A = A;
		course = A.getCourses();
		rooms = A.getRooms();
	}
	public Aggregator getAggregator() {
		return A;
	}

	//Reassign Courses with Same Time to a different Room 
	public String reassignRoomSameTime(Course courseTitle, String newRoomNum){
		
		String oldRoomNum=courseTitle.getRoom().getRoomNumber();
		
		int length = oldRoomNum.length();
	    oldRoomNum = oldRoomNum.substring(length - 3);
	    
		courseTitle.setRoom("Peter Kiewit Institute "+newRoomNum);
		
		String result=courseTitle+" Room PKI "+oldRoomNum+" was reassigned to Room PKI "+ newRoomNum+"";
		return result;
	  }
	
	
	
	// Finds a list of rooms that are open at the same time as the course passed.
	public  String[] findRoomSameTime(Course c,int maxEnrollement){
		
		Room[] filtered=findRoomsLargerThanMaxEnrollment(maxEnrollement);
		ArrayList<String> findRoomSameTime=new ArrayList<String>();
		
		for (int i=0;i<filtered.length;i++) {
			if (!course[i].getCourseMeeting().equals(c.getCourseMeeting())) {
				String ret="Room: "+filtered[i].getRoomNumber()+" ,Capacity: "+
			filtered[i].getCapacity()+" ,Open at "+c.getCourseMeeting()+" : Yes";
				findRoomSameTime.add(ret);
			}	
		}
		String [] ret=new String[findRoomSameTime.size()];
		findRoomSameTime.toArray(ret);
		return ret;
	}		
	/*
	 * param: c is the course to find other course to swap
	 * param: enrollment is the max enrollment wanted to expand
	 * return: a list of Courses that satisfy these three cases:
	 * 1. duration is same 2. day of week swap available, 3.the enrollment swap available,
	 */
	public ArrayList<Course> findCoursesSwap (Course c, int enrollment){
		ArrayList<Course> diffTimeArray=new ArrayList<Course>();
		int rand;
		int current;
	    	for(int i=0; i<course.length; i++) {
	    		//check if enrollment swap available
	    		if(course[i].getMaxEnrollment()>= enrollment 
	    				&& c.getMaxEnrollment()>=course[i].getEnrollment()) {
	    			//check if day of week swap available
	    			if(course[i].getMeetingPattern()<4) { rand= 0;}
	    			else { rand =1;}
	    			if(c.getMeetingPattern()<4) { current = 0;}
	    			else { current = 1;}
	    			if(rand == current) {
	    				if(course[i].getMeetingDuration() == c.getMeetingDuration()) {
		    				diffTimeArray.add(course[i]);
		    			}
	    			}
	    		}
		}
		return diffTimeArray;
	}
	public String timeSwap(Course a, Course b) {
		
		try {
			String output;
			
			if(a.getMeetingDuration() == b.getMeetingDuration()) {
				
				a.release();
				b.release();
				
				String temp = a.getCourseMeeting();
				a.setMeetingPattern(b.getCourseMeeting());
				b.setMeetingPattern(temp);
				
				Room temp2 = a.getRoom();
				a.setRoom(b.getRoom());
				b.setRoom(temp2);
				
				a.schedule(rooms);
				b.schedule(rooms);
				output = a.getCourseSection() +", Time: "+a.getCourseMeeting() + ", "
						+ "Room: PKI "+ a.getRoom().getRoomNumber() + " <br><br> "
						+ b.getCourseSection()+": Time: "+b.getCourseMeeting() + ", "
								+ "Room: PKI "+ b.getRoom().getRoomNumber();
						
			}else {
				output= "Unable to swap";
			}
			
			return output;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	public String roomSwap(Course a, Course b) {
		
		try {
			String output;
			
			if(a.getMeetingDuration() == b.getMeetingDuration()) {
				
				output = a.getCourseSection() +": "+a.getRoom().getRoomNumber() + " and "
						+b.getCourseSection()+": "+b.getRoom().getRoomNumber();
				
				a.release();
				b.release();
				
				Room temp = a.getRoom();
				a.setRoom(b.getRoom());
				b.setRoom(temp);
				
				a.schedule(rooms);
				b.schedule(rooms);
				
				
				
			}else {
				output= "Unable to swap becuase class time different in length";
			}
			
			return output;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	// Swap two classes
	public  String roomSwap(String courseTitle1,String courseTitle2){

		// Find index of each course
		int position1=getCourseIndex (courseTitle1);
		int position2=getCourseIndex (courseTitle2);
		if (position1<0 || position2<0 ) {
			return "ERROR: course title does not exist"; 
		}
		
		// Capacity of each course
		int courseTitle1Capacity=course[position1].getRoom().getCapacity();
		int courseTitle2Capacity=course[position2].getRoom().getCapacity();
		
		// Max enrollment of each course
		int courseTitle1MaxEnrollment = course[position1].getMaxEnrollment();
		int courseTitle2MaxEnrollment = course[position2].getMaxEnrollment();
		
		
		if (courseTitle1MaxEnrollment<=courseTitle2Capacity && courseTitle2MaxEnrollment<=courseTitle1Capacity) {
			
			String classRoom1=course[position1].getRoom().getRoomNumber();
			String classRoom2=course[position2].getRoom().getRoomNumber();
			String tempRoom=classRoom1;
			
			//Swap rooms
			course[position1].setRoom(classRoom2);
			course[position2].setRoom(tempRoom);
			
			int length = classRoom1.length();
			
			String ret="Course <"+courseTitle1+"> was moved from Room<PKI "+classRoom1.substring(length - 3)+
					"> to Room<PKI "+course[position1].getRoom().getRoomNumber().substring(length - 3)+
					">\nCourse <"+courseTitle2+"> was moved from Room<PKI "+classRoom2.substring(length - 3)+
					"> to Room<PKI "+course[position2].getRoom().getRoomNumber().substring(length - 3)+">";
			
			return ret;
			
		}

		return "ERROR: Max Enrollment Exceeds Rooms Capacity";
	}
	
	
	/*
	 * param: maxEnrollment is the maxEnrollment given to find larger rooms
	 * return a Room array based on maxEnrollment,
	 * maxEnrollment return based on three divisions, maxEnrollment+7 < 30 or 40 or greater than 40
	 * for example: if maxEnrollment is 10-23, then it return room arrays of room size between 10-29
	 * if maxEnrollment is 24-38, then it return room arrays of room size between 24-44
	 * if maxEnrollment is 39-60, then it return room arrays of room size between 39-60
	 */
	public  Room[] findRoomsLargerThanMaxEnrollment( int maxEnrollment) {
    	ArrayList<Room> largerRooms = new ArrayList<>();
    	
    	for (int i=0;i<rooms.length;i++) {
    		if(maxEnrollment + 7 <= 30) {
    			if (rooms[i].getCapacity() >=maxEnrollment && rooms[i].getCapacity()< 30) {
            		largerRooms.add(rooms[i]);
    	    	}
    		}
    		else if(maxEnrollment + 7 <= 45) {
    			if (rooms[i].getCapacity() >=maxEnrollment && rooms[i].getCapacity()< 45) {
            		largerRooms.add(rooms[i]);
    	    	}
    		}else {
    			if (rooms[i].getCapacity() >=maxEnrollment) {
            		largerRooms.add(rooms[i]);
    	    	}
    		}
    	}
    	Room[] lroom=new Room[largerRooms.size()];
    	largerRooms.toArray(lroom);
		
    	return lroom;
	}
	
	// Find course index/position in Course[] using courseTitle
	private int getCourseIndex (String courseTitle) {
		int position=-1;
		for(int i=0; i<course.length;i++) {
			if(course[i].getCourseSection().equals(courseTitle)) {
				position=i;
				break;	
			}
		}
		return position;
	}
	

	private String getTimeFromSlot(int timeSlot) {
	    int hour = (timeSlot / 4);
	    int minute = (timeSlot % 4) * 15;
	    String ampm = (hour < 12) ? "am" : "pm";
	    if (hour == 0) {
	        hour = 12;
	    } else if (hour > 12) {
	        hour -= 12;
	    }
	    return String.format("%d:%02d%s", hour, minute, ampm);
	}
	private static String[] getRanges(List<Integer> numbers) {
	        if (numbers == null || numbers.isEmpty()) {
	            return new String[0];
	        }

	        List<String> ranges = new ArrayList<>();
	        int start = numbers.get(0);
	        int end = numbers.get(0);

	        for (int i = 1; i < numbers.size(); i++) {
	            int current = numbers.get(i);
	            if (current == end + 1) {
	                end = current;
	            } else {
	                ranges.add(buildRangeString(start, end));
	                start = end = current;
	            }
	        }
	        ranges.add(buildRangeString(start, end));
	        return ranges.toArray(new String[0]);
	    }

	    private static String buildRangeString(int start, int end) {
	        if (start == end) {
	            return String.valueOf(start);
	        } else {
	            return start + "-" + end;
	        }
	    }	
}
