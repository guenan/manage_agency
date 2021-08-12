package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Managers.Planning_managers;


public class Servlet_afficher_planning extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE_PLANNING_FRONTEND        = "/index.jsp";
	public static final String ATTR_LISTE_PLANNING_FRONTEND="ATTR_LISTE_PLANNING_FRONTEND" ;  
	      
       
   
    public Servlet_afficher_planning() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Planning_managers pm=new Planning_managers();
		
		request.setAttribute(ATTR_LISTE_PLANNING_FRONTEND,pm.get_planning());
    	request.setAttribute("action", "afficher_planning");
        
        this.getServletContext().getRequestDispatcher( VUE_PLANNING_FRONTEND).forward( request, response );
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
