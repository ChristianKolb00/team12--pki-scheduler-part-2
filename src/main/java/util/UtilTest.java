package util;

import java.io.IOException;

import parser.Aggregator;
import parser.Course;
import parser.Room;
import parser.TimeTable;
public class UtilTest {
	public static void main(String[] args) {
	
	
		String Path = "C:\\Users\\cmlko\\eclipse-workspace\\pkiClassroom\\src\\main\\java\\csvFiles\\BIOI1191.csv";
		String Path2 = "C:\\Users\\cmlko\\eclipse-workspace\\pkiClassroom\\src\\main\\java\\csvFiles\\BMI1191.csv";
		String Path3 = "C:\\Users\\cmlko\\eclipse-workspace\\pkiClassroom\\src\\main\\java\\csvFiles\\CIST_EMIT1191.csv";
		String Path4 = "C:\\Users\\cmlko\\eclipse-workspace\\pkiClassroom\\src\\main\\java\\csvFiles\\CSCI1191.csv";
		String Path5 = "C:\\Users\\cmlko\\eclipse-workspace\\pkiClassroom\\src\\main\\java\\csvFiles\\CYBR1191.csv";
		//String Path6 = "C:\\Users\\cmlko\\eclipse-workspace\\pkiClassroom\\src\\main\\java\\csvFiles\\ISQA1191.csv";
		String Path7 = "C:\\Users\\cmlko\\eclipse-workspace\\pkiClassroom\\src\\main\\java\\csvFiles\\ITIN1191.csv";

		String[] AllFile = new String[]{Path, Path2, Path3, Path4, Path5, Path7};
	
		Aggregator tester = new Aggregator(AllFile);
		Course[] course = tester.getCourses();
		
		
		
		
		
		Utils u=new Utils();
		
		String courseTitle= "CIST 2100-001";
		String[] courseName = courseTitle.split("-");
		System.out.println(courseName[0]);
		int enrollment = 50;
		int position=0;
		for(int i=0; i<course.length;i++) {
			if(course[i].getCourseName().equals(courseName[0])) {
				if(course[i].getSection().equals(courseName[1])) {
					System.out.println(i);
					position=i;
					break;
					
				}
			}
		}
		String [] roomSameTime=u.findRoomSameTime(course[position], 30);
		for(int i=0;i<roomSameTime.length;i++) {
			System.out.println(roomSameTime[i]);
		}
		
		System.out.println("\n------ Reassign Courses with Same Time to a different Room --------\n");
		System.out.println(u.reassignRoomSameTime(course[position],"252"));
		System.out.println("---------  --------- --------  -------- ---------  -------- ---------  --------");
		
//		helper h=new helper();
//		Room [] filtered=u.findRoomsLargerThanMaxEnrollment(12);
//		String names;
//		for(int i=0; i<filtered.length; i++) {
//			names=course[i].getRoomNum();
//			names=names.substring(names.length() - 3);
//			System.out.println(filtered[i].getRoomNumber()+"--"+filtered[i].getCapacity()+"--"+course[i].getCourseTime());
//		}
		

//		TimeTable timeTable=new TimeTable();
//
//		String courseTime=course[0].getCourseTime();
//		int parseTime=course[0].parseTime(courseTime);
//		int day=course[0].getMeetingPattern();
//		int du=4;
//			
//		Course[] ttable=timeTable.checkAvailable(day,parseTime,du);
//		System.out.println("++++"+ttable[0]);
//		System.out.println("++++"+ttable[0]);
		
		
//		
		//ArrayList<Room>roomSameTime=u.findRoomSameTime(course[1], 12);
		//System.out.println(roomSameTime.get(1));
//		for(int i=0; i<roomSameTime.size(); i++) {
//			System.out.println(roomSameTime.get(i));
//		}
		
		

//		for(int i=0; i<course.length; i++) {
//			System.out.println(course[i].getCourseName()+" Room: "+
//					room[i].getRoomNumber()+"  Capacity: "+room[i].getCapacity()+
//					"  Time: "+course[i].getCourseTime());
//			System.out.println(course[i].getMeetingPattern());
//			
//		}
	}
}
