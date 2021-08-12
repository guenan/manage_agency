package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Employe;
import Managers.News_managers;
import Managers.Personnel_managers;
import Managers.Rapport_managers;


public class Servlet_admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static  String VUE_ADMIN       = "/vue_backend/connexion.jsp";
	public final String USER_SESSION="user_session";
    
    public Servlet_admin() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		 Employe employe=(Employe)request.getSession().getAttribute(USER_SESSION);
	        
	        if(employe == null){
	        	VUE_ADMIN="/vue_backend/connexion.jsp";
	        	this.getServletContext().getRequestDispatcher( VUE_ADMIN).forward( request, response );
	        }else{
	        	VUE_ADMIN="/vue_backend/index.jsp";
	        	this.getServletContext().getRequestDispatcher( VUE_ADMIN).forward( request, response );
	        }
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
