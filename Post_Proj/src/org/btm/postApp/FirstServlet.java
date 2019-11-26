package org.btm.postApp;

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class FirstServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sid = req.getParameter("i");
		String name = req.getParameter("nm");
		String dept = req.getParameter("dp");
		String sperc = req.getParameter("pr");
		
		PrintWriter out  = resp.getWriter();
		out.println("<html><body bgcolor='yellow'><h1> The name of the Student is :"+name
				+" and Department is: "+dept+"</h1></body></html>");
		out.flush();
		out.close();
		Connection con = null;
		PreparedStatement pstmt = null;
		String conURL = "jdbc:mysql://localhost:3306?user=root&password=root";
		String qry = "INSERT INTO BTM.STUDENT VALUES(?,?,?,?)";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(conURL);
			pstmt = con.prepareStatement(qry);
			
			//SET VALUES TO THE PLACEHOLERS BEFORE EXECUTION//
			pstmt.setInt(1, Integer.parseInt(sid));
			pstmt.setString(2, name);
			pstmt.setString(3, dept);
			pstmt.setDouble(4, Double.parseDouble(sperc));

			//EXECUTE QUERY - Use executeUpdate() Since it supports Specifically for DML & Returns an Integer - Saying How Many Rows have been affected by the DML Command //
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally 
		{
			if(pstmt!=null)
			{
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(con!=null)
			{
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
