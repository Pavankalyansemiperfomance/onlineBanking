package bankingServices;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;
@WebServlet("/LogIn")
public class LogIn extends HttpServlet
{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String id=req.getParameter("us");
		String pass=req.getParameter("pas");
		String url="jdbc:mysql://localhost:3306?user=root&password=12345";
		String query="select * from pavan1999.banking where username=?";
		PrintWriter writer=resp.getWriter();
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection connection=DriverManager.getConnection(url);
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setString(1,id);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				RequestDispatcher rd=req.getRequestDispatcher("Services.html");
				rd.include(req, resp);
				HttpSession session=req.getSession();
				session.setMaxInactiveInterval(10);
			}
			else
			{
				RequestDispatcher rd1=req.getRequestDispatcher("LogInPage1.html");
				rd1.include(req, resp);
				writer.print("<h1 style='color:red'>Please ENter Correct LogIn Crediantials....</h1>");
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
