package util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import parser.Aggregator;
import parser.Course;
import parser.Room;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/UtilTest")
public class UtilTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public UtilTest() {
    	super();
        // TODO Auto-generated constructor stub
    }

   
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("<html><head><title>PKI Classroom Assignment</title></head> <body>");
		String Path = "/Users/ghafar/Documents/PKI-Proj/2019files/BIOI1191.csv";
		String Path2 = "/Users/ghafar/Documents/PKI-Proj/2019files/BMI1191.csv";
		String Path3 = "/Users/ghafar/Documents/PKI-Proj/2019files/CIST_EMIT1191.csv";
		String Path4 = "/Users/ghafar/Documents/PKI-Proj/2019files/CSCI1191.csv";
		String Path5 = "/Users/ghafar/Documents/PKI-Proj/2019files/CYBR1191.csv";
		String Path6 = "/Users/ghafar/Documents/PKI-Proj/2019files/ISQA1191.csv";
		String Path7 = "/Users/ghafar/Documents/PKI-Proj/2019files/ITIN1191.csv";
		
		String[] AllFile = new String[]{Path, Path2, Path3, Path4, Path5, Path7};
	
		Aggregator tester = new Aggregator(AllFile);
		Course[] course = tester.getCourses();

		
		Utils u=new Utils();
		
		String [] roomSameTime=u.findRoomSameTime(course, 12);
		for(int i=0;i<roomSameTime.length;i++) {
			System.out.println(roomSameTime[i]);
		}
		
		System.out.println("\n------ Reassign Courses with Same Time to a different Room --------\n");
		System.out.println(u.reassignRoomSameTime(course,"252"));
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
