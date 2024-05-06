

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class login extends HttpServlet {
	Connection con;
	public void init(ServletConfig config) throws ServletException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "Hemant123");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch(Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=response.getWriter();
        try {
            String s1=request.getParameter("username");
            String s2=request.getParameter("password");
           
            PreparedStatement stmt = con.prepareStatement("select * from  uinfo where username=? and password=?"); 
            
            stmt.setString(1,s1);
            stmt.setString(2,s2);
            ResultSet rs=stmt.executeQuery();
            
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

            if(rs.next()) 
             {
            pw.println("<div class=\"title\"><span style=\"color: rgb(6, 255, 22);\">Login Success</span></div>");
            }else {
                pw.println("<div class=\"title\"><span style=\"color: rgb(202, 22, 22);\">invalid password</span></div>");
            }
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



           
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}

}
