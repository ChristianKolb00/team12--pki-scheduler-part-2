package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		int selection =Integer.parseInt( request.getParameter("selection")) ;
		String example = "Open room___Time change: 3pm-4:15pm to 6pm-7:15pm___PKI 250 to PKI 265";
		System.out.println(selection);
		if(selection == 0) {
			request.setAttribute("errorMessage", "Please select a different choice");
			request.getRequestDispatcher("/change.jsp").forward(request, response);
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("selection", selection);
			session.setAttribute("result", example);
			response.sendRedirect("summary.jsp?selection="+selection+"&result="+example);
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
