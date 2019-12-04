package org.btm.chainApp;

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class SecondServlet extends HttpServlet
{
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
				throws ServletException, IOException
		{
			//FETCH RESULTS//
			String pname = (String)req.getAttribute("prdnm");
			String pqty = (String)req.getAttribute("prdqt");
			int qty = Integer.parseInt(pqty);
			
			double price =40000.00;
			double totalsum = (price*qty);//BUSINESS LOGIC//
			PrintWriter out = resp.getWriter();
			out.println("<html><body bgcolor='yellow'>"
					+ "<h1> Product Details are "+pname+" and total is "+totalsum+"</h1>"
							+ "</body></html>");
			out.flush();
			out.close();
			
			Connection con = null;
			PreparedStatement pstmt = null;
			String conURL = "jdbc:mysql://localhost:3306?user=root&password=root";
			String qry = "insert into btm.product values(?,?,?)";
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection(conURL);
				pstmt = con.prepareStatement(qry);
				
				//SET VALUES TO PLACEHOLDERS BEFORE EXECUTION//
				pstmt.setString(1, pname);
				pstmt.setInt(2, qty);
				pstmt.setDouble(3, totalsum);
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
