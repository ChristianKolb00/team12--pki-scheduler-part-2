package parser;

public class ParserTester {
	public static void main(String[] args) {
		String Path = "C:\\Users\\jcwga\\git\\team12--pki-scheduler-part-2\\src\\main\\java\\csvFiles\\BIOI1191.csv";
		String Path2 = "C:\\Users\\jcwga\\git\\team12--pki-scheduler-part-2\\src\\main\\java\\csvFiles\\BMI1191.csv";
		String Path3 = "C:\\Users\\jcwga\\git\\team12--pki-scheduler-part-2\\src\\main\\java\\csvFiles\\CIST_EMIT1191.csv";
		String Path4 = "C:\\Users\\jcwga\\git\\team12--pki-scheduler-part-2\\src\\main\\java\\csvFiles\\CSCI1191.csv";
		String Path5 = "C:\\Users\\jcwga\\git\\team12--pki-scheduler-part-2\\src\\main\\java\\csvFiles\\CYBR1191.csv";
		String Path6 = "C:\\Users\\jcwga\\git\\team12--pki-scheduler-part-2\\src\\main\\java\\csvFiles\\ISQA1191.csv";
		String Path7 = "C:\\Users\\jcwga\\git\\team12--pki-scheduler-part-2\\src\\main\\java\\csvFiles\\ITIN1191.csv";
		
		String[] AllFile = new String[]{Path, Path2, Path3, Path4, Path5, /*Path6,*/ Path7};
		Aggregator tester = new Aggregator(AllFile);
		String[] allCourses = tester.getCourseNames();
		Course[] saniCourses = tester.getCourses();
		/*System.out.println("Sani length:" + saniCourses.length);
		System.out.println(saniCourses[113].diff[Constants.CROSSLISTINGS]);
		System.out.println(saniCourses[113].getPC());
		System.out.println(saniCourses[81].getCourseSection());
		System.out.println(saniCourses[81].getPC());
		System.out.println(saniCourses[113].getPCField()[0]);
		System.out.println(saniCourses[113].getPCField()[0].equalsIgnoreCase(saniCourses[81].getCourseSection()));
		System.out.println(saniCourses[81].getRoom().getRoomNumber());
		System.out.println(" CSCI 8366-001".contentEquals(" CSCI 8366-001"));
		System.out.println(saniCourses[113].parent.getCourseSection());*/
		for(int i=0;i<saniCourses.length;i++) {
			
			System.out.println(i);
			System.out.println("Course:" + saniCourses[i].getCourseSection());
			System.out.println(saniCourses[i].toString());
			System.out.println(saniCourses[i].getRoom().getRoomNumber());
		}
		try
		{
			saniCourses[1].release();
			saniCourses[2].release();
			Room temp1 = saniCourses[1].getRoom();
			String temp2 = saniCourses[1].getCourseMeeting();
			saniCourses[1].setRoom(saniCourses[2].getRoom());
			saniCourses[1].setCourseMeeting(saniCourses[2].getCourseMeeting());
			saniCourses[2].setRoom(temp1);
			saniCourses[2].setCourseMeeting(temp2);
			saniCourses[1].schedule();
			saniCourses[2].schedule();
			System.out.println("Rescheduled");
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		try
		{
			saniCourses[1].release();
			saniCourses[2].release();
			saniCourses[1].revert();
			saniCourses[2].revert();
			saniCourses[1].schedule();
			saniCourses[2].schedule();
			System.out.println("Reverted");
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
	}
	
}