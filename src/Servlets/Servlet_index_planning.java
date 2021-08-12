package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Managers.Planning_managers;
import Managers.Recette_managers;



public class Servlet_index_planning extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
public static final String ATTR_LISTE_PLANNING="ATTR_LISTE_PLANNING" ;
	
	public final String vue_form_recette_frontend="/vue_backend/index_planning.jsp";
    public final String vue_form_recette_backend="/vue_backend/index_planning.jsp";     
    
    
    public Servlet_index_planning() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("action", "index_planning");
		 Planning_managers pm =new Planning_managers();
		 String yesterday_date="";
	    	
	    	request.setAttribute(ATTR_LISTE_PLANNING, pm.get_planning());
	    	
	           //   request.setAttribute(ATTR_LISTE_NEWS_BACKEND, nm.get_list_news(10));
	         
		
		if(request.getAttribute("attr_destination").equals("frontend"))
		    this.getServletContext().getRequestDispatcher(vue_form_recette_frontend).forward(request,response);
		else if(request.getAttribute("attr_destination").equals("backend"))
			this.getServletContext().getRequestDispatcher(vue_form_recette_backend).forward(request,response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
