package org.btm.getApp;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class FirstServlet extends HttpServlet 
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException 
	{
		String sid = req.getParameter("i");
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PrintWriter out = resp.getWriter();
		String conURL = "jdbc:mysql://localhost:3306?user=root&password=root";
		String qry = "SELECT * FROM BTM.STUDENT WHERE ID=?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(conURL);
			pstmt = con.prepareStatement(qry);
			pstmt.setInt(1, Integer.parseInt(sid));
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				out.println("<html><body bgcolor='yellow'><center><h1>The name of the Student is:"+rs.getString(2)+" & Branch of the Student is:"+rs.getString(3)+"</h1></center></body></html>");
				out.flush();
			}
			else
			{
				out.println("<html><body bgcolor='yellow'><center><h1 style='color:red;'>No Data Found!!</h1></center></body></html>");
				out.flush();
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		finally 
		{
			out.close();
			
			if(rs!=null)
			{
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
