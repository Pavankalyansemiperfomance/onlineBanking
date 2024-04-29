package bankingServices;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Withdraw")
public class Withdraw extends HttpServlet
{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url="jdbc:mysql://localhost:3306?user=root&password=12345";
		String query="update pavan1999.banking set accbal=accbal-? where accno=?";
		String acc=req.getParameter("acc");
		String bala=req.getParameter("bal");
		PrintWriter writer=resp.getWriter();
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setString(1, bala);
			ps.setString(2, acc);
			int res=ps.executeUpdate();
			if(res>0)
			{
				writer.print("<h1>Money Debited Successfully..</h1>");
				writer.println("<br></br><input type='button' value='Go To Services' onclick='myFunction()'></input> <script >\r\n"
						+ "		function myFunction()\r\n"
						+ "		{\r\n"
						+ "			window.location.href=\"Services.html\";\r\n"
						+ "		}\r\n"
						+ "	</script>");
				writer.println("<br></br><input type='button' value='LogOut' onclick='myFunction()'></input> <script >\r\n"
						+ "		function myFunction()\r\n"
						+ "		{\r\n"
						+ "			window.location.href=\"Homepage.html\";\r\n"
						+ "		}\r\n"
						+ "	</script>");
			}
			else
			{
				RequestDispatcher rd=req.getRequestDispatcher("Withdraw.html");
				rd.forward(req, resp);
				writer.println("Please Enter Correct Account Number...");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
