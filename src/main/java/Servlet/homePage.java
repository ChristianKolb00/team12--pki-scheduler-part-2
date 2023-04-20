package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import parser.Aggregator;
import parser.Course;
import util.Utils;

/**
 * Servlet implementation class change
 */
@WebServlet("/homePage")
public class homePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public homePage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String field = request.getParameter("selection");
		String course =  request.getParameter("course");
		ArrayList<String> object = new ArrayList<>();
		
		String Path = "C:\\Users\\cmlko\\eclipse-workspace\\pkiClassroom\\src\\main\\java\\csvFiles\\BIOI1191.csv";
		String Path2 = "C:\\Users\\cmlko\\eclipse-workspace\\pkiClassroom\\src\\main\\java\\csvFiles\\BMI1191.csv";
		String Path3 = "C:\\Users\\cmlko\\eclipse-workspace\\pkiClassroom\\src\\main\\java\\csvFiles\\CIST_EMIT1191.csv";
		String Path4 = "C:\\Users\\cmlko\\eclipse-workspace\\pkiClassroom\\src\\main\\java\\csvFiles\\CSCI1191.csv";
		String Path5 = "C:\\Users\\cmlko\\eclipse-workspace\\pkiClassroom\\src\\main\\java\\csvFiles\\CYBR1191.csv";
		//String Path6 = "C:\\Users\\cmlko\\eclipse-workspace\\pkiClassroom\\src\\main\\java\\csvFiles\\ISQA1191.csv";
		String Path7 = "C:\\Users\\cmlko\\eclipse-workspace\\pkiClassroom\\src\\main\\java\\csvFiles\\ITIN1191.csv";

		String[] AllFile = new String[]{Path, Path2, Path3, Path4, Path5, Path7};
	
		Aggregator tester = new Aggregator(AllFile);
		Course[] courses = tester.getCourses();
		Utils u=new Utils();
		
		String courseTitle= course;
		String[] courseName = courseTitle.split("-");
		System.out.println(courseName[0]);
		
		int enrollment = Integer.parseInt(field);
		int position=0;
		for(int i=0; i<courses.length;i++) {
			if(courses[i].getCourseName().equals(courseName[0])) {
				if(courses[i].getSection().equals(courseName[1])) {
					System.out.println(i);
					position=i;
					break;
					
				}
			}
		}
		String[] roomSameTime=u.findRoomSameTime(courses[position], enrollment);
		
		
		/*System.out.println("\n------ Reassign Courses with Same Time to a different Room --------\n");
		System.out.println(u.reassignRoomSameTime(courses[position],"252"));
		System.out.println("---------  --------- --------  -------- ---------  -------- ---------  --------");
		*/
		HttpSession session = request.getSession();
		session.setAttribute("course", course);
		session.setAttribute("field", field);
		session.setAttribute("object", roomSameTime);
		response.sendRedirect("change.jsp?course="+course+"&field="+field);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
