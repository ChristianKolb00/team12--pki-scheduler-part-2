package util;

import java.io.IOException;
import java.util.ArrayList;

import parser.Aggregator;
import parser.Course;
import parser.Room;
import parser.TimeTable;
public class UtilTest {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String Path = "C:\\Users\\cmlko\\eclipse-workspace\\pkiClassroom\\src\\main\\java\\csvFiles\\BIOI1191.csv";
		String Path2 = "C:\\Users\\cmlko\\eclipse-workspace\\pkiClassroom\\src\\main\\java\\csvFiles\\BMI1191.csv";
		String Path3 = "C:\\Users\\cmlko\\eclipse-workspace\\pkiClassroom\\src\\main\\java\\csvFiles\\CIST_EMIT1191.csv";
		String Path4 = "C:\\Users\\cmlko\\eclipse-workspace\\pkiClassroom\\src\\main\\java\\csvFiles\\CSCI1191.csv";
		String Path5 = "C:\\Users\\cmlko\\eclipse-workspace\\pkiClassroom\\src\\main\\java\\csvFiles\\CYBR1191.csv";
		String Path6 = "C:\\Users\\cmlko\\eclipse-workspace\\pkiClassroom\\src\\main\\java\\csvFiles\\ISQA1191.csv";
		String Path7 = "C:\\Users\\cmlko\\eclipse-workspace\\pkiClassroom\\src\\main\\java\\csvFiles\\ITIN1191.csv";
		
		String[] AllFile = new String[]{Path, Path2, Path3, Path4, Path5, Path6, Path7};
		Aggregator tester = new Aggregator(AllFile);
		
		Utils u=new Utils(tester);
	 	/*System.out.println("\n------------ Find Rooms that are open based on current course time and capacity");
		String courseTitle= "CIST 2100-001";
		Course course = tester.findCourse(courseTitle);
		
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
		*/
		
		ArrayList<Course> OpenCourse = u.findCoursesSwap(tester.getCourses()[22], 20);
		System.out.println(tester.getCourses()[8].getEnrollment()
				+ ", "+tester.getCourses()[8].getMaxEnrollment());
		ArrayList<String> formatCourse = new ArrayList<String>();
		for( int i=0; i<OpenCourse.size();i++) {
			String formatString = "Room: "+ OpenCourse.get(i).getRoom().getRoomNumber() + 
					", Course: "+ OpenCourse.get(i).getCourseSection() +
					", MaxCapacity: "+OpenCourse.get(i).getMaxEnrollment() + 
					", Capacity: "+OpenCourse.get(i).getEnrollment() + 
					", Open at " + OpenCourse.get(i).getCourseMeeting() + ":Yes";
			formatCourse.add(formatString);
		}
		for(int j=0; j<formatCourse.size(); j++) {
			System.out.println(formatCourse.get(j));
		}
		
		
		String timeSwapping = u.timeSwap(tester.getCourses()[1], tester.getCourses()[2]);
		System.out.println(timeSwapping);
		String timeSwapping2 = u.timeSwap(tester.getCourses()[2], tester.getCourses()[1]);
		System.out.println(timeSwapping2);
		
		
		
		
	}
}
