package util;

import java.io.IOException;

import parser.Aggregator;
import parser.Course;
import parser.Room;
import parser.TimeTable;
public class UtilTest {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		
		Utils u=new Utils();
	 	System.out.println("\n------------ Find Rooms that are open based on current course time and capacity");
		String courseTitle= "CIST 2100-001";
		Course course = helper.parseCourseTitle(courseTitle);
		
		int enrollment = 50;
		
		String [] roomSameTime=u.findRoomSameTime(course, enrollment);
		for(int i=0;i<roomSameTime.length;i++) {
			System.out.println(roomSameTime[i]);
		}
		
		System.out.println("\n------------ Reassign Courses with Same Time to a different Room -----------");
		String reassignFeedback=u.reassignRoomSameTime("CIST 2100-001","252");
		System.out.println(reassignFeedback);
		
		System.out.println("\n------------ Swap two classes --------- --------  --------  ----------------");
		String swapFeedback=u.roomSwap("BIOI 1000-001", "BIOI 4970-001");
		//String swapFeedback=u.roomSwap("CIST 3000-001", "CIST 1400-004");
		System.out.println(swapFeedback);
		
		System.out.println("\n\n------------ Find Empty Rooms --------- --------  --------  ---------------");
		u.findDiffRoomDiffTime(0,40);
		
		System.out.println("\n------------ Reassign Different Time And Day And Room--------------");
		String reassignFeedback1=u.reassignDiffTimeDiffRoom("CIST 2100-001","276", "MW 8:15am-10:15am");
		System.out.println(reassignFeedback1);
		

		
	}
}
