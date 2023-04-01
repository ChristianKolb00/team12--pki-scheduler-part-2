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
		String[] allCourses = tester.getCourseInfo();
		for(int i=0; i<allCourses.length;i++) {
			//split to get each individual element from courseAt
			/* 0 is course & section, 1 is section type, 2 is meeting pattern,3 is instructor
			 * 4 is room, 5 is current enrolled, and 6 is max enrolled
			 * print out each element from the string array
			 * */
			String[] courseAt = allCourses[i].split(",");
			for(int j=0; j<courseAt.length; j++) {
				if(j<courseAt.length-1) {
					System.out.print(courseAt[j]+", ");
				}else {
					System.out.print("and "+courseAt[j]);
				}
			}
			System.out.println("");
			String[] courseMeet = courseAt[2].split(" ");
			
			//get the day of week, USEFUL METHOD!!
			String DOW = getDayOfWeek(courseMeet, courseAt);
			System.out.println(DOW);
			//converting meeting time into time slot , USEFUL METHOD!!
			int[] timeSlot = getTimeSlot(courseMeet);
			System.out.println(timeSlot[0]+ " --> "+timeSlot[1]);
			System.out.println("");
			
			
	}
			
		//column needed to access & update are Meeting Pattern, Room, Inst. Method, 
		//Enrollment, and Maximum Enrollment. 
		
		//column needed to access to display are course & course section#, room, 
		//Enrollment, and Max Enrollment.
	
		//Potential need of making getCourseXXXX(enrollment, maxEnrollment, Room...) to work 
		//before working on analysis methods in util.
	}
	public static String getDayOfWeek(String[] courseMeet, String[] courseAt) {
		String result="";
		switch(courseMeet[0]) {
		case "TThF": result=courseAt[0]+" meet on Tuesday, Thursday, and Friday"; break;
		case "TTh":result=courseAt[0]+" meet on Tuesday and Thursday"; break;
		case "MW": result=courseAt[0]+" meet on Monday and Wednesday"; break;
		case "M": result=courseAt[0]+" meet on Monday"; break;
		case "T": result=courseAt[0]+" meet on Tuesday"; break;
		case "W": result=courseAt[0]+" meet on Wednesday"; break;
		case "Th": result=courseAt[0]+" meet on Thursday"; break;
		case "F": result=courseAt[0]+" meet on Friday"; break;
		case "Sa": result=courseAt[0]+" meet on Saturday"; break;
		default: result="Abnormal scedule???"; break;
		}
		return result;
	}
	
	public static int[] getTimeSlot(String[] courseMeet) {
		int start=0, end=0; //start time and end time for meeting
		String[] meetTime = courseMeet[1].split("-"); //split meet time into start and end
		if(meetTime[0].contains("pm")) { 
			start+=(12*4); //pm for start
		}
		if(meetTime[1].contains("pm")) { 
			end+=(12*4);	//pm for end
		}
		//start only
		String[] hourMinStart =  meetTime[0].split(":");
		if(hourMinStart.length==1) {
			//System.out.println("no minutes for start");
		}else if(hourMinStart[1].contains("15")) {
			start+=1;
		}else if(hourMinStart[1].contains("30")) {
			start+=2;
		}else {
			start+=3; //else minute at 45
		}
		//end only
		String[] hourMinEnd =  meetTime[1].split(":");
		if(hourMinEnd.length==1) {
			//System.out.println("no minutes for end");
		}else if(hourMinEnd[1].contains("10")) {
			end+=1;
		}else if(hourMinEnd[1].contains("15")) {
			end+=1;
		}else if(hourMinEnd[1].contains("20")) {
			end+=2;
		}else if(hourMinEnd[1].contains("30")) {
			end+=2;
		}else {
			end+=3; //else minute at 40, 45, or 50
		}
		
		String hourOnly[] = hourMinStart[0].split("am|pm");
		if(hourOnly.length ==1) {
			int hour = Integer.parseInt(hourOnly[0]);
			start+=(hour*4);
		}
		String hourOnly2[] = hourMinEnd[0].split("am|pm");
		if(hourOnly2.length ==1) {
			int hour2 = Integer.parseInt(hourOnly2[0]);
			end+=(hour2*4);
		}
		int[] timeslot = {start,end};
		return timeslot;
	}
}