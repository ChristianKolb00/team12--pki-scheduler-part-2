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
		try {
			int selection =Integer.parseInt( request.getParameter("selection")) ;
			String[] results =(String[]) request.getSession().getAttribute("object");
			String result = results[selection-1];
			System.out.println(selection);
			if(selection == -1) {
				
			} else {
				HttpSession session = request.getSession();
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
