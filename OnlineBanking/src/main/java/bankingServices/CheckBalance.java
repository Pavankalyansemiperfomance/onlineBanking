package bankingServices;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/CheckBalance")
public class CheckBalance extends HttpServlet
{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String url="jdbc:mysql://localhost:3306?user=root&password=12345";
		String query="select * from pavan1999.banking where accno=?";
		String acn=req.getParameter("ac");
		PrintWriter writer=resp.getWriter();
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setString(1, acn);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				String bal=rs.getString("accbal");
				writer.println("<h1>Available Balance :</h1>"+bal);
			}
			else
			{
				RequestDispatcher rd=req.getRequestDispatcher("ViewBalance.html");
				rd.include(req, resp);
				writer.println("Please Enter Correct Account Number..");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
