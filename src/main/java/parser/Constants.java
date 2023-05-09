package parser;

import java.sql.Timestamp;

public final class Constants {
	
	public Constants()
	{
	}
	
	protected static final String PATH = "C:\\Users\\cmlko\\eclipse-workspace\\pkiClassroom\\src\\main\\java\\csvFiles\\";
	
	private static final Timestamp openTime = new Timestamp(System.currentTimeMillis());
	protected static final String backupTime = openTime.getMonth() + "_" + openTime.getDate() + "_" + openTime.getHours() + "_" + openTime.getMinutes();
	
	protected static final int NUM_DAYS = 5;//M,T,W,Th,F (Sa is ignored)
	protected static final int NUM_FIFTEENS = 96;//0:00-0:15, 0:15-0:30,...,23:45-0:00
	
	//Static meeting pattern values
	protected static final int Sa = -1;
	protected static final int M = 0;
	protected static final int T = 1;
	protected static final int W = 2;
	protected static final int Th = 3;
	protected static final int F = 4;
	protected static final int M_W = 5;
	protected static final int T_TH= 6;	
	
	//Indices for CSV format in array
	protected static final int COL_COUNT = 38;
	protected static final int FILLER_TITLE = 0;//Col 0 unused by any Course line
	protected static final int CLSS = 1;
	protected static final int SIS = 2;
	protected static final int TERM = 3;
	protected static final int TERM_CODE = 4;
	protected static final int DEPT_CODE = 5;
	protected static final int SUBJ_CODE = 6;
	protected static final int CATA_NUM = 7;
	protected static final int COURSE = 8;
	protected static final int SEC_NUM = 9;
	protected static final int COUR_TITLE = 10;
	protected static final int SEC_TYPE = 11;
	protected static final int TITLE_TOPIC = 12;
	protected static final int MEET_PATT = 13;
	protected static final int INSTRUCTOR = 14;
	protected static final int ROOM = 15;
	protected static final int STATUS = 16;
	protected static final int SESSION = 17;
	protected static final int CAMPUS = 18;
	protected static final int INST_METH = 19;
	protected static final int INTEG_PART = 20;
	protected static final int SCHED_PRINT = 21;
	protected static final int CONSENT = 22;
	protected static final int CRED_MIN = 23;
	protected static final int CRED_HRS = 24;
	protected static final int GRADE_MODE = 25;
	protected static final int ATTRIBUTES = 26;
	protected static final int ROOM_ATTR = 27;
	protected static final int ENROLLMENT = 28;
	protected static final int MAX_ENROLL = 29;
	protected static final int PRIOR_ENROLL = 30;
	protected static final int PROJ_ENROLL = 31;
	protected static final int WAIT_CAP = 32;
	protected static final int ROOM_CAP_REQ = 33;
	protected static final int CROSSLISTINGS = 34;
	protected static final int LINK = 35;
	protected static final int COMMENTS = 36;
	protected static final int NOTES = 37;
	//(
	//Static creators for Rooms
	//Number, Seats, Computers
	protected static Object[][] pki = new Object[][] {{"108",12,1},{"150",16,1},{"153",42,1},{"155",40,1},{"157",24,1},{"160",42,1},{"161",30,1},{"164",56,1},{"250",16,1},{"252",58,1},
		{"256",40,1},{"259",16,1},{"260",60,60},{"261",24,1},{"263",50,1},{"269",30,1},{"270",16,1},{"274",32,1},{"276",40,35},{"278",40,35},{"279",30,1},{"350",16,1},
		{"361",40,40},{"391A",10,1}};
}
