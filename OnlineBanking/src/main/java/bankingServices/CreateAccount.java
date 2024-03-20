package bankingServices;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

@WebServlet("/CreateAccount")
public class CreateAccount extends HttpServlet
{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String url="jdbc:mysql://localhost:3306?user=root&password=12345";
		String query="insert into pavan1999.banking(name,accno,username,password,mobile,accbal,ifsc)values(?,?,?,?,?,?,?)";
		
		String name=req.getParameter("nam");
		String acn=req.getParameter("acc");
		String user=req.getParameter("user");
		String pass=req.getParameter("pass");
		String mbl=req.getParameter("mb");
		String bal=req.getParameter("bal");
		String ifsc=req.getParameter("ifsc");
		PrintWriter writer=resp.getWriter();
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection connection=DriverManager.getConnection(url);
			PreparedStatement stmt=connection.prepareStatement(query);
			stmt.setString(1, name);
			stmt.setString(2, acn);
			stmt.setString(3, user);
			stmt.setString(4, pass);
			stmt.setString(5, mbl);
			stmt.setString(6,bal);
			stmt.setString(7, ifsc);
			if(name.isEmpty()||acn.isEmpty()||user.isEmpty()||pass.isEmpty()||mbl.isEmpty()||bal.isEmpty()||ifsc.isEmpty())
			{
				System.out.println("Enter All Credientials");
				RequestDispatcher rd=req.getRequestDispatcher("CreateAccount.html");
				rd.include(req, resp);
				writer.println("Please Enter All the Details....");
			}
			else
			{
				stmt.executeUpdate();
				System.out.println("Account Created...");
				RequestDispatcher rs=req.getRequestDispatcher("LogInPage1.html");
				rs.include(req, resp);
				writer.println("Account Created Successfully..");
				writer.println("LogIn With Your Details....");
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}

















