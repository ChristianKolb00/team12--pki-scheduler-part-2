package util;

import java.util.ArrayList;

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

	/*
	 * Params: c is course and max enrollment is the wanted enrollment to expand
	 * Return: A string array of string contain room, capacity, and time
	 */
	
	public  String[] findRoomSameTime(Course c,int maxEnrollement){
		
		Room[] filtered=findRoomsLargerThanMaxEnrollment(c,maxEnrollement);
		ArrayList<String> findRoomSameTime=new ArrayList<String>();
		
		for (int i=0;i<filtered.length;i++) {
			if (!course[i].getCourseMeeting().equals(c.getCourseMeeting())) {
				String ret="Room: "+filtered[i].getRoomNumber()+", Max Capacity: "+
			filtered[i].getCapacity()+", Open at "+c.getCourseMeeting()+" : Yes";
				findRoomSameTime.add(ret);
			}	
		}
		String [] ret=new String[findRoomSameTime.size()];
		findRoomSameTime.toArray(ret);
		return ret;
	}		

	/*
	 * param: maxEnrollment is the maxEnrollment given to find larger rooms
	 * return a Room array based on maxEnrollment,
	 * maxEnrollment return based on three divisions, maxEnrollment+7 < 30 or 40 or greater than 40
	 * for example: if maxEnrollment is 10-23, then it return room arrays of room size between 10-29
	 * if maxEnrollment is 24-38, then it return room arrays of room size between 24-44
	 * if maxEnrollment is 39-60, then it return room arrays of room size between 39-60
	 */
	public  Room[] findRoomsLargerThanMaxEnrollment(Course cc, int maxEnrollment) {
    	ArrayList<Room> largerRooms = new ArrayList<>();
    	
    	for (int i=0;i<rooms.length;i++) {
    		if(maxEnrollment + 7 <= 30) {
    			if (rooms[i].getCapacity() >=maxEnrollment && rooms[i].getCapacity()< 30) {
    				if(cc.getMeetingPattern()==5) {
    					if(rooms[i].checkAvailable(0, 
        						cc.getMeetingPattern(), cc.getMeetingDuration()).length ==0) {
    						if(rooms[i].checkAvailable(2, 
            						cc.getMeetingPattern(), cc.getMeetingDuration()).length ==0) {
            					largerRooms.add(rooms[i]);
            				}
        				}
    				}
    				else if(cc.getMeetingPattern()==6) {
    					if(rooms[i].checkAvailable(1, 
        						cc.getMeetingPattern(), cc.getMeetingDuration()).length ==0) {
    						if(rooms[i].checkAvailable(3, 
            						cc.getMeetingPattern(), cc.getMeetingDuration()).length ==0) {
            					largerRooms.add(rooms[i]);
            				}
        				}
    				}
    				else {
    					if(rooms[i].checkAvailable(cc.getMeetingPattern(), 
        						cc.getMeetingPattern(), cc.getMeetingDuration()).length ==0) {
        					largerRooms.add(rooms[i]);
        				}
    				}
    				
            		
    	    	}
    		}
    		else if(maxEnrollment + 7 <= 45) {
    			if(cc.getMeetingPattern()==5) {
					if(rooms[i].checkAvailable(0, 
    						cc.getMeetingPattern(), cc.getMeetingDuration()).length ==0) {
						if(rooms[i].checkAvailable(2, 
        						cc.getMeetingPattern(), cc.getMeetingDuration()).length ==0) {
        					largerRooms.add(rooms[i]);
        				}
    				}
				}
				else if(cc.getMeetingPattern()==6) {
					if(rooms[i].checkAvailable(1, 
    						cc.getMeetingPattern(), cc.getMeetingDuration()).length ==0) {
						if(rooms[i].checkAvailable(3, 
        						cc.getMeetingPattern(), cc.getMeetingDuration()).length ==0) {
        					largerRooms.add(rooms[i]);
        				}
    				}
				}
				else {
					if(rooms[i].checkAvailable(cc.getMeetingPattern(), 
    						cc.getMeetingPattern(), cc.getMeetingDuration()).length ==0) {
    					largerRooms.add(rooms[i]);
    				}
				}
				
    		}else {
    			if(cc.getMeetingPattern()==5) {
					if(rooms[i].checkAvailable(0, 
    						cc.getMeetingPattern(), cc.getMeetingDuration()).length ==0) {
						if(rooms[i].checkAvailable(2, 
        						cc.getMeetingPattern(), cc.getMeetingDuration()).length ==0) {
        					largerRooms.add(rooms[i]);
        				}
    				}
				}
				else if(cc.getMeetingPattern()==6) {
					if(rooms[i].checkAvailable(1, 
    						cc.getMeetingPattern(), cc.getMeetingDuration()).length ==0) {
						if(rooms[i].checkAvailable(3, 
        						cc.getMeetingPattern(), cc.getMeetingDuration()).length ==0) {
        					largerRooms.add(rooms[i]);
        				}
    				}
				}
				else {
					if(rooms[i].checkAvailable(cc.getMeetingPattern(), 
    						cc.getMeetingPattern(), cc.getMeetingDuration()).length ==0) {
    					largerRooms.add(rooms[i]);
    				}
				}
				
    		}
    	}
    	Room[] lroom=new Room[largerRooms.size()];
    	largerRooms.toArray(lroom);
		
    	return lroom;
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
	    				if(course[i].getMeetingDuration() == c.getMeetingDuration()
	    						&&course[i]!=c) {
		    				diffTimeArray.add(course[i]);
		    			}
	    			}
	    		}
		}
		return diffTimeArray;
	}
	/*
	 * Params: a and b are two courses to be swapped
	 * This method return a string showing what two classrooms and time are swapped
	 * When swapping, two courses' time and room are swapped
	 */
	public String timeSwap(Course a, Course b) {
		
		try {
			String output;
			
			if(a.getMeetingDuration() == b.getMeetingDuration()) {
				
				a.release();
				b.release();
				
				String temp = a.getCourseMeeting();
				a.setCourseMeeting(b.getCourseMeeting());
				b.setCourseMeeting(temp);
				
				Room temp2 = a.getRoom();
				a.setRoom(b.getRoom());
				b.setRoom(temp2);
				
				a.schedule();
				b.schedule();
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
	/*
	 * Param: a and b are two courses
	 * Return: a string that tell what two rooms are swapped
	 */
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
				
				a.schedule();
				b.schedule();
				
				
				
			}else {
				output= "Unable to swap becuase class time different in length";
			}
			
			return output;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	//Reassign Courses with Same Time to a different Room 
		public String reassignRoomSameTime(Course courseTitle, String newRoomNum){
			try {
				String oldRoomNum=courseTitle.getRoom().getRoomNumber();
				
				int length = oldRoomNum.length();
				oldRoomNum = oldRoomNum.substring(length - 3);//TODO: this doesnt work as there are 3 and 4 character long rooms
				courseTitle.release();
				courseTitle.setRoom(A.findRoomByNum(oldRoomNum));
				courseTitle.setCourseMeeting(courseTitle.getCourseMeeting());
				courseTitle.schedule();
				String result=courseTitle.getCourseSection()+", Room PKI "+oldRoomNum
						+" was reassigned to Room PKI "+ newRoomNum+", Same Time";
				return result;
			}catch(Exception e) {
				e.printStackTrace();
			}
			return null;
		  }
		
		
}
