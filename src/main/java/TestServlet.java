

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public TestServlet() {
    	super();
        // TODO Auto-generated constructor stub
    }
    /**
     * this method when called will print out all a list of data in that csv file.
     * this method will specifically print out the data related to PKI Building and In-Person Classes
     */
    protected void printOutCSV(HttpServletRequest request, HttpServletResponse response, String Path) throws ServletException, IOException {
    	PrintWriter out = response.getWriter();
    	out.println("<table>");
    	String line = "";
    	try { 
    		out.println("<br> <br>");
			BufferedReader br = new BufferedReader(new FileReader(Path));
			while ((line = br.readLine())!= null) {
				String[] values = line.split(",\"");
					if(values.length>4) {
						if(values[13].contains("Does Not Meet")== false) {
							if(values[15].contains("Peter Kiewit Institute")== true||values[15].contains("Room")== true) {
								//8 is course section, 10 is course title, 13 is meet time, 
								//15 is location, 28 is enrollment, and 29 is max enrollment
								out.println( "<tr> <td>"  + values[8]+ "</td> <td>" + values[10] +"</td>");
								out.print("<td>"+ values[13] + "</td> <td>"+ values[15] +"</td>");
								out.print("<td>"+ values[28] + "</td> <td>"+ values[29] +"</td><td>"+values[36] +"</td></tr> ");
							}
						}
					}
			}	
			br.close();
			}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		out.println("</table>");
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("<html><head><title>PKI Classroom Assignment</title></head> <body>");
		String Path = "C:\\Users\\cmlko\\eclipse-workspace\\pkiClassroom\\src\\main\\java\\csvFiles\\BIOI1191.csv";
		String Path2 = "C:\\Users\\cmlko\\eclipse-workspace\\pkiClassroom\\src\\main\\java\\csvFiles\\BMI1191.csv";
		String Path3 = "C:\\Users\\cmlko\\eclipse-workspace\\pkiClassroom\\src\\main\\java\\csvFiles\\CIST_EMIT1191.csv";
		String Path4 = "C:\\Users\\cmlko\\eclipse-workspace\\pkiClassroom\\src\\main\\java\\csvFiles\\CSCI1191.csv";
		String Path5 = "C:\\Users\\cmlko\\eclipse-workspace\\pkiClassroom\\src\\main\\java\\csvFiles\\CYBR1191.csv";
		//String Path6 = "C:\\Users\\cmlko\\eclipse-workspace\\pkiClassroom\\src\\main\\java\\csvFiles\\ISQA1191.csv";
		String Path7 = "C:\\Users\\cmlko\\eclipse-workspace\\pkiClassroom\\src\\main\\java\\csvFiles\\ITIN1191.csv";
		
		String[] AllFile = new String[]{Path, Path2, Path3, Path4, Path5, /*Path6,*/ Path7};
	
		out.println("<style>");
		out.println("button { width: 300px; height: 60px; margin: 1px;}");
		out.println("table{ margin-left:auto; margin-right:auto; }");
		out.println("table, th, td { border: 1px solid black; border-collapse:collapse;}");
		out.println("td { padding:15px; text-align:left;}");
		out.println("h1 { margin-left:50%; transform: translate(-50%);}");
		out.println("</style>");
		out.println("<h1> Welcome to the PKI building's Courses!</h1>");
		
		//each printOutCSV call will print out data from the csv file path listed above
		for(int i=0; i<AllFile.length; i++) {
			printOutCSV(request, response, AllFile[i]);
		}
		out.println("</body> </html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}