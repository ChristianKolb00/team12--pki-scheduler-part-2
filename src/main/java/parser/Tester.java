package parser;

public class Tester {
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
		
		
		//column needed to access & update are Meeting Pattern, Room, Inst. Method, 
		//Enrollment, and Maximum Enrollment. 
		
		//column needed to access to display are course & course section#, room, 
		//Enrollment, and Max Enrollment.
	
		//Potential need of making getCourseXXXX(enrollment, maxEnrollment, Room...) to work 
		//before working on analysis methods in util.
		
		
	}
}