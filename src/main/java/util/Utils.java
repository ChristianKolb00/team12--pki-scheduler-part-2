package util;

import java.util.ArrayList;
import java.util.List;

import parser.Aggregator;
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
	public String reassignRoomSameTime(String courseTitle,String newRoomNum){

		int position=getCourseIndex (courseTitle);
		
		String oldRoomNum=course[position].getRoom().getRoomNumber();
		int length = oldRoomNum.length();
	    oldRoomNum = oldRoomNum.substring(length - 3);
	    
		course[position].setRoom("Peter Kiewit Institute "+newRoomNum);
		
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
	
	// Finds a list of rooms that are open at a different time at a different room.
	// It should work as MW and TTH instead of each of single day
	//
	public ArrayList<String> findDiffRoomDiffTime(int Day,int maxEnrollement){
		ArrayList<String> timeArray= new ArrayList<>();
		ArrayList<Integer> timeArr = new ArrayList<Integer>();
		 for(int i=0;i<course.length ;i++) {
			 if(course[i].getRoom()!=null && course[i].getRoom().getCapacity()>=maxEnrollement ) {
		        	 
		            for (int timeSlot = 32; timeSlot < 88; timeSlot++) {
		                if (course[i].getRoom().getCourseAt(Day, timeSlot) == null) {
		                	if(timeSlot==33) {timeArr.add(timeSlot-1);};		         
		                	timeArr.add(timeSlot);              
		                }
		            }
		   		 String[] ranges = getRanges(timeArr);
				 
				 timeArray=new ArrayList<String>();
				 
				 for (String range : ranges) {
					    String[] times = range.split("-");
					    if(times.length>1) {
					    String startTime = getTimeFromSlot(Integer.parseInt(times[0]));
					    String endTime = getTimeFromSlot(Integer.parseInt(times[1]));
					    String timeRange = startTime + " - " + endTime;	
					    
					    timeArray.add(timeRange);	
					    }
					    //System.out.println(timeRange);
					}
				 	System.out.println(timeArray);
		            timeArr.clear();		
		            /*I need the format to be like this:
		             * 
		             * "Room: "+course[i].getRoom().getRoomNumber()+
		             * ",Capacity: "+ course[i].getMaxEnrollment()+
		             * ",Open at "+course[i].getMeetingPattern()+" "+ timeRange + " : Yes"
					 */
		        }
		    }
		 return timeArray;
		}	

	
//	PKI274 Open on MW at [8am-1:15pm,3pm-5:15pm,8:30pm-10pm]
//	PKI276 Open on MW at [8am-1:15,3pm-5:15pm,8:30pm-10pm]
//	PKI278 Open on MW at [8am-1:15,3pm-5:15pm,8:30pm-10pm]
	
//	reassignDiffRoomSameTime(courseTitle,newRoomNum);
//	reassignDiffRoomDiffTime(courseTitle, MW 8am, Room 274);
	
	//Reassign Different Room Different Time
	//reassign that work on everything
	public String reassignDiffTimeDiffRoom(String courseTitle, String newRoomNum, String newTime) {
	    int position = getCourseIndex(courseTitle);
	    
	    String oldRoomNum1=course[position].getRoom().getRoomNumber();
		int length = oldRoomNum1.length();
	    oldRoomNum1 = oldRoomNum1.substring(length - 3);
	    //String oldRoomNum1 = course[position].getRoomNum();
	    int oldTime = course[position].getMeetingTime();
	    // Update course information
	    course[position].setRoom("Peter Kiewit Institute " + newRoomNum);
	    //course[position].setCourseTime(newTime);
	    //course[position].setMeetingPattern(meetingPattern);
	    // Construct result message
	    String result = courseTitle + " in Room PKI " + oldRoomNum1 +  " at " + oldTime +
	                    " has been reassigned to Room PKI " + newRoomNum + 
	                    " at " + newTime + ".";
	    return result;
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
	
	//Reassign Courses with Same Time to a different Room 
	public String reassignRoomSameTime(Course c,String roomNum){
		for (int i=0;i<course.length;i++) {
			if (!course[i].getCourseMeeting().equals(c.getCourseMeeting()) ) {
				course[i].setRoom(roomNum);
			}
			}
		String result=c.getCourseMeeting()+" Room: "+c.getRoom().getRoomNumber()+" was reassigned to Room: Peter Kiewit Institute "+ roomNum;
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
