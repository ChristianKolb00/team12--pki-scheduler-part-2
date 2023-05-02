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
		Utils u=new Utils();
		HttpSession session = request.getSession();
		try {
			int selection =Integer.parseInt( request.getParameter("selection")) ;
			@SuppressWarnings("unchecked")
			ArrayList<String> results =(ArrayList<String>) request.getSession().getAttribute("object");
			
			String result = results.get(selection-1);
			String[] parseRoom = result.split(",");
			String[] parseRoomNumber = parseRoom[0].split(":");
			
			String courseTitle = (String) session.getAttribute("course");
			
			String feedback = u.reassignRoomSameTime(courseTitle, parseRoomNumber[1]);
			
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
