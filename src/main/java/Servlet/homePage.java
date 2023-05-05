package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
		HttpSession session = request.getSession();
		
		String selection = request.getParameter("selection");
		String course =  request.getParameter("course");
		Utils u = (Utils)session.getAttribute("u");
		
		Aggregator tester = u.getAggregator();
		Course courseTitle = tester.findCourse(course);
		int enrollment = Integer.parseInt(selection);
		
		//Find Open Rooms
		String[] roomSameTimeArray=u.findRoomSameTime(courseTitle, enrollment);
		ArrayList<String> roomSameTime = new ArrayList<String>(
				Arrays.asList(roomSameTimeArray));
		
		//Find Swappable Classes
		ArrayList<Course> OpenCourse = u.findCoursesSwap(courseTitle, enrollment);
		ArrayList<String> formatCourse = new ArrayList<String>();
		for( int i=0; i<OpenCourse.size();i++) {
			String formatString = "Room: "+ OpenCourse.get(i).getRoom().getRoomNumber() + 
					", Course: "+ OpenCourse.get(i).getCourseSection() +
					", Max Capacity: "+OpenCourse.get(i).getMaxEnrollment() + 
					", Capacity: "+OpenCourse.get(i).getEnrollment() + 
					", Open at " + OpenCourse.get(i).getCourseMeeting() + ":Yes";
			formatCourse.add(formatString);
		}
		
		
		
		String courseTime = courseTitle.getCourseMeeting();
		String roomNum = courseTitle.getRoom().getRoomNumber();
		int maxEnrollment=courseTitle.getMaxEnrollment();
		int enroll = courseTitle.getEnrollment();
		
		session.setAttribute("course", course);
		session.setAttribute("MaxEnrollWanted", enrollment);
		session.setAttribute("enroll", enroll);
		session.setAttribute("object2", formatCourse); //array of swappable classes
		session.setAttribute("object", roomSameTime); //array of open classes
		session.setAttribute("courseTime", courseTime);
		session.setAttribute("MaxEnroll", maxEnrollment);
		session.setAttribute("roomNum", roomNum);
		response.sendRedirect("change.jsp?course="+course+"&enrollment="+enrollment);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
