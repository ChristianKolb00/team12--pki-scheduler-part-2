package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
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
@WebServlet("/changesPage")
public class changesPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public changesPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		Utils u = (Utils)session.getAttribute("u");
		try {
			int selection =Integer.parseInt( request.getParameter("selection")) ;
			@SuppressWarnings("unchecked")
			ArrayList<String> results =(ArrayList<String>) request.getSession().getAttribute("object");
			@SuppressWarnings("unchecked")
			ArrayList<String> results2 =(ArrayList<String>) request.getSession().getAttribute("object2");
			String result;
			String feedback;
			String courseTitle = (String) session.getAttribute("course");
			Course courseTitles = (Course) u.getAggregator().findCourse(courseTitle);
			
			if(selection > results.size()) {
				result = results2.get(selection-results.size()-1);
				String[] parser = result.split(",");
				String[] parseCourseTitle = parser[1].split(":");
				Course courseTwo = u.getAggregator().findCourse(parseCourseTitle[1].trim());
				 feedback= u.timeSwap(courseTitles, courseTwo);
			}else {
				result = results.get(selection-1);
				String[] parseRoom = result.split(",");
				String[] parseRoomNumber = parseRoom[0].split(":");
				 
				 feedback = u.reassignRoomSameTime(courseTitles, parseRoomNumber[1].trim());
			}
			
			
			
			
			
			
			
			System.out.println(feedback);
			
			if(selection == -1) {
				
			} else {
				session.setAttribute("feedback", feedback);
				session.setAttribute("selection", selection);
				session.setAttribute("result", result);
				response.sendRedirect("summary.jsp?selection="+selection+"&result="+result);
			}
		}catch(NumberFormatException e) {
			request.setAttribute("errorMessage", "Please select a different choice");
			request.getRequestDispatcher("/change.jsp").forward(request, response);
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
