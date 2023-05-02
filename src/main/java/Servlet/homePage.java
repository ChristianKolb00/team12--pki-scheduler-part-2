package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

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
		System.out.println(courseName[0]+"-"+courseName[1]);
		int enrollment = Integer.parseInt(field);
		String[] roomSameTimeArray=u.findRoomSameTime(course, enrollment);
		ArrayList<String> roomSameTime = new ArrayList<String>(
				Arrays.asList(roomSameTimeArray));
		
		int position=0;
		for(int i=0; i<courses.length;i++) {
			if(courses[i].getCourseName().equals(courseName[0])) {
				if(courses[i].getSection().equals(courseName[1])) {
					position=i;
					break;
					
				}
			}
		}
		String courseTime = courses[position].getCourseTime();
		String roomNum = courses[position].getRoomNum();

		/*
		if(roomSameTime.size() <5) {
			for(int i=0; i<5; i++) {
				if(courseDay != i) {
					ArrayList<String> differentDay= u.findDiffRoomDiffTime(i,enrollment);
					roomSameTime.addAll(differentDay);
				}
				
			}
			
		}*/
		
		HttpSession session = request.getSession();
		session.setAttribute("course", course);
		session.setAttribute("field", field);
		session.setAttribute("object", roomSameTime);
		session.setAttribute("courseTime", courseTime);
		session.setAttribute("roomNum", roomNum);
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
