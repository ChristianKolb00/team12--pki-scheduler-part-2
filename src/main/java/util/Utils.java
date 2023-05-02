package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	
	
	
	//Reassign Courses with Same Time to a different Room 
	public String reassignRoomSameTime(String courseTitle,String newRoomNum){

		int position=getCourseIndex (courseTitle);
		
		String oldRoomNum=course[position].getRoomNum();
		int length = oldRoomNum.length();
	    oldRoomNum = oldRoomNum.substring(length - 3);
	    
		course[position].setRoom("Peter Kiewit Institute "+newRoomNum);
		
		String result=courseTitle+" Room PKI "+oldRoomNum+" was reassigned to Room PKI "+ newRoomNum+"";
		return result;
	  }
	
	
	
	// Finds a list of rooms that are open at the same time as the course passed.
	public  String[] findRoomSameTime(String courseTitle,int maxEnrollement){
		
		int position=getCourseIndex (courseTitle);
		
		Room[] filtered=findRoomsLargerThanMaxEnrollment(maxEnrollement);
		ArrayList<String> findRoomSameTime=new ArrayList<String>();
		
		for (int i=0;i<filtered.length;i++) {
			if (!course[i].getCourseTime().equals(course[position].getCourseTime())) {
				String ret="Room: "+filtered[i].getRoomNumber()+" ,Capacity: "+
			filtered[i].getCapacity()+" ,Open at "+course[position].getCourseTime()+" : Yes";
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
		        	 System.out.println("\nRoom: "+course[i].getRoomNum());
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
	    
	    String oldRoomNum1=course[position].getRoomNum();
		int length = oldRoomNum1.length();
	    oldRoomNum1 = oldRoomNum1.substring(length - 3);
	    //String oldRoomNum1 = course[position].getRoomNum();
	    String oldTime = course[position].getCourseTime();
	    // Update course information
	    course[position].setRoom("Peter Kiewit Institute " + newRoomNum);
	    //course[position].setCourseTime(newTime);
	    //course[position].setMeetingPattern(meetingPattern);
	    // Construct result message
	    String result = courseTitle + " in Room<PKI " + oldRoomNum1 + ">" + " at " + oldTime +
	                    " has been reassigned to Room<PKI " + newRoomNum + ">" +
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
			
			String classRoom1=course[position1].getRoomNum();
			String classRoom2=course[position2].getRoomNum();
			String tempRoom=classRoom1;
			
			//Swap rooms
			course[position1].setRoom(classRoom2);
			course[position2].setRoom(tempRoom);
			
			int length = classRoom1.length();
			
			String ret="Course <"+courseTitle1+"> was moved from Room<PKI "+classRoom1.substring(length - 3)+
					"> to Room<PKI "+course[position1].getRoomNum().substring(length - 3)+
					">\nCourse <"+courseTitle2+"> was moved from Room<PKI "+classRoom2.substring(length - 3)+
					"> to Room<PKI "+course[position2].getRoomNum().substring(length - 3)+">";
			
			return ret;
			
		}

		return "ERROR: Max Enrollment Exceeds Rooms Capacity";
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
	
	// Find course index/position in Course[] using courseTitle
	private int getCourseIndex (String courseTitle) {
		String[] courseName = courseTitle.split("-");
		int position=-1;
		for(int i=0; i<course.length;i++) {
			if(course[i].getCourseName().equals(courseName[0])) {
				if(course[i].getSection().equals(courseName[1])) {
					position=i;
					break;	
				}
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
