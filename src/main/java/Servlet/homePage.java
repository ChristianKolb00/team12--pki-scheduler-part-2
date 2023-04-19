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
		
		object.add("Course 1111___Time change: 3pm-4:15pm to 6pm-7:15pm___PKI 250 to PKI 260");
		object.add("Course 2222___Time change: 3pm-4:15pm to 10pm-11:15pm___PKI 250 to PKI 263");
		object.add("Open room___Time change: 3pm-4:15pm to 6pm-7:15pm___PKI 250 to PKI 265");
		
		HttpSession session = request.getSession();
		session.setAttribute("course", course);
		session.setAttribute("field", field);
		session.setAttribute("object", object);
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
