package org.btm.uiApp;

import java.io.*;

import javax.servlet.*;

public class FirstServlet extends GenericServlet
{

	@Override
	public void service(ServletRequest req, ServletResponse resp) 
			throws ServletException, IOException 
	{

		String name = req.getParameter("nm");
		String place = req.getParameter("pl");
		PrintWriter out = resp.getWriter();
		out.println("<html>"
				+ "<body bgcolor='yellow'>"
				+ "<h1> DABBA FELLOW "+name+" "+place+"</h1>"
						+ "</body></html>");
		out.flush();
		out.close();
	}

}
 