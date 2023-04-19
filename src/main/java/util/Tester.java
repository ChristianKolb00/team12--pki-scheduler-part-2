package util;

import parser.Aggregator;
import parser.Course;
import parser.Room;

public class Tester {
	public static void main(String[] args) {
		String Path = "C:\\Users\\jcwga\\git\\team12--pki-scheduler-part-2\\src\\main\\java\\csvFiles\\BIOI1191.csv";
		String Path2 = "C:\\Users\\jcwga\\git\\team12--pki-scheduler-part-2\\src\\main\\java\\csvFiles\\BMI1191.csv";
		String Path3 = "C:\\Users\\jcwga\\git\\team12--pki-scheduler-part-2\\src\\main\\java\\csvFiles\\CIST_EMIT1191.csv";
		String Path4 = "C:\\Users\\jcwga\\git\\team12--pki-scheduler-part-2\\src\\main\\java\\csvFiles\\CSCI1191.csv";
		String Path5 = "C:\\Users\\jcwga\\git\\team12--pki-scheduler-part-2\\src\\main\\java\\csvFiles\\CYBR1191.csv";
		String Path6 = "C:\\Users\\jcwga\\git\\team12--pki-scheduler-part-2\\src\\main\\java\\csvFiles\\ISQA1191.csv";
		String Path7 = "C:\\Users\\jcwga\\git\\team12--pki-scheduler-part-2\\src\\main\\java\\csvFiles\\ITIN1191.csv";
		String PathTemp = "C:\\Users\\jcwga\\git\\team12--pki-scheduler-part-2\\src\\main\\java\\csvFiles\\CSCI1191.csv";
		
		String[] AllFile = new String[]{Path,Path2,Path3,Path4,Path5,Path6,Path7};
		Aggregator tester = new Aggregator(AllFile);
		//for(int i=0; i<allCourses.length; i++) {
			//System.out.println(allCourses[i] +"");
		//}
		System.out.println(helper.parseTime("8:00pm"));
		System.out.println(helper.parseTime("12:00am"));
		System.out.println(helper.parseTime("12:15am"));
		System.out.println(helper.parseTime("12:00pm"));
		System.out.println(helper.parseTime("12:15pm"));
		
		
	}
	
}