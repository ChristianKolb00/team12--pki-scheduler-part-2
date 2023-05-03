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
		String field = request.getParameter("selection");
		String course =  request.getParameter("course");
		
		HttpSession session = request.getSession();
		Utils u = (Utils)session.getAttribute("u");
		Aggregator tester = u.getAggregator();
		Course courseTitle = tester.findCourse(course);
		int enrollment = Integer.parseInt(field);
		
		String[] roomSameTimeArray=u.findRoomSameTime(courseTitle, enrollment);
		
		ArrayList<String> roomSameTime = new ArrayList<String>(
				Arrays.asList(roomSameTimeArray));
		
		String courseTime = courseTitle.getCourseMeeting();
		String roomNum = courseTitle.getRoom().getRoomNumber();
		int courseDay = courseTitle.getMeetingPattern();
		if(roomSameTime.size() <5) {
			for(int i=0; i<5; i++) {
				if(courseDay != i) {
					ArrayList<String> differentDay= u.findDiffRoomDiffTime(i,enrollment);
					roomSameTime.addAll(differentDay);
				}
				
			}
			
		}
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
