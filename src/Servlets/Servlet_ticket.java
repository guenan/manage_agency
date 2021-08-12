package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class Servlet_ticket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Servlet_ticket() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 String ATTR_action=(String) request.getAttribute("attr_action");
	        String ATTR_destination=(String) request.getAttribute("attr_destination");
	       request.setAttribute("ATTR_destination", ATTR_destination);
	       request.setAttribute("ATTR_action", ATTR_action);
	       this.getServletContext().getRequestDispatcher("/Servlet_"+ATTR_action).forward(request,response);
	       
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 String ATTR_action=(String) request.getAttribute("attr_action");
	        String ATTR_destination=(String) request.getAttribute("attr_destination");
	       request.setAttribute("ATTR_destination", ATTR_destination);
	       request.setAttribute("ATTR_action", ATTR_action);
	       this.getServletContext().getRequestDispatcher("/Servlet_"+ATTR_action).forward(request,response);
	       
		
	}
	
	

}
