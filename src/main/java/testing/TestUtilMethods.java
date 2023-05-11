package testing;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import parser.Aggregator;
import parser.Course;
import parser.Room;
import util.Utils;

public class TestUtilMethods {
	String pathName = "D:\\WorkSpaces\\PkiClassroom\\team12--pki-scheduler-part-2\\src\\main\\java\\csvFiles\\";
	String Path = pathName+ "BIOI1191.csv";
	String Path2 = pathName+ "BMI1191.csv";
	String Path3 = pathName+ "CIST_EMIT1191.csv";
	String Path4 = pathName+ " CSCI1191.csv";
	String Path5 = pathName+ "CYBR1191.csv";
	String Path6 = pathName+ "ISQA1191.csv";
	String Path7 = pathName+ "ITIN1191.csv";
	
	String[] AllFile = new String[]{Path, Path2, Path3, Path4, Path5, Path6, Path7};
	Aggregator tester = new Aggregator(AllFile);
	Utils u = new Utils(tester);
	Course[] cc = tester.getCourses();
	
	@Test
	public void testGetAggregator() {
		Assert.assertEquals(tester,u.getAggregator());
		
	}
	@Test
	public void testFindRoomSameTime() {
		//not used much since most rooms are occupied
		String[] output1= u.findRoomSameTime(cc[0], 30);
		Assert.assertEquals(0, output1.length);
	}
	@Test
	public void testFindRoomsLargerThanMaxEnrollment() {
		//not used much since most rooms are occupied
		Room[] output1= u.findRoomsLargerThanMaxEnrollment(cc[0], 30);
		Assert.assertEquals(0, output1.length);
	}
	@Test
	public void testFindCoursesSwap() {
		ArrayList<Course> output1= u.findCoursesSwap(cc[0], 30);
		Assert.assertNotEquals(0, output1.size());
	}
	@Test
	public void testTimeSwap() {
		String output1= u.timeSwap(cc[1], cc[2]);
		Assert.assertNotEquals(null, output1);
		String output2= u.timeSwap(cc[2], cc[1]);
		Assert.assertNotEquals(output1, output2);
	}
	@Test
	public void testAssignRoomSameTime() {
		//not used much since most rooms are occupied
		String output1= u.reassignRoomSameTime(cc[1],"260");
		System.out.println(output1);
		Assert.assertNotEquals(null, output1);
	}
}
