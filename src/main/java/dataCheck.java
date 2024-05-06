

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class dataCheck
 */
public class dataCheck extends HttpServlet {
	Connection con;
	public void init(ServletConfig config) throws ServletException {
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		 con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
		"system", "Hemant123");
		}catch(Exception e) 
		{
			System.err.println(e);
		}
	}
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			PrintWriter pw=response.getWriter();
			String s1=request.getParameter("name");
			String s2=request.getParameter("lmame");
			String s3=request.getParameter("username");
			String s4=request.getParameter("password");
			PreparedStatement stmt=con.prepareStatement("insert into uinfo values(?,?,?,?)");
			stmt.setString(1, s1);
			stmt.setString(2, s2);
			stmt.setString(3, s3);
			stmt.setString(4, s4);
			stmt.executeUpdate();
			
			pw.println("<!DOCTYPE html>");
			pw.println("<html lang=\"en\">");
			pw.println("<head>");
			pw.println("<meta charset=\"UTF-8\">");
			pw.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
			pw.println("<title>Document</title>");
			pw.println("<link rel=\"stylesheet\" href=\"style.css\">");
			pw.println("</head>");
			pw.println("<body>");
			pw.println("<div class=\"container\">");
			pw.println("<div class=\"wrapper\">");
			pw.println("<div class=\"title\"><span>Registration Successfully</span></div>");
			pw.println("<form action=\"loginCheck\" method=\"post\">");
			pw.println("<div class=\"row button\">");
			pw.println("<a href=\"loginpage.html\"><input type=\"button\" value=\"Login\"></a>");
			pw.println("</div>");
			pw.println("<a href=\"#\"><div class=\"signup-link\">Social Media</div></a>");
			pw.println("</form>");
			pw.println("</div>");
			pw.println("</div>");
			pw.println("</body>");
			pw.println("</html>");
			
	}catch(Exception e) {
		System.err.println(e);
	}
	}

}
