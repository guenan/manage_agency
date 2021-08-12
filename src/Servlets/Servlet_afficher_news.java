package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Managers.News_managers;



public class Servlet_afficher_news extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE_NEWS_BACKEND        = "/vue_backend/index_news.jsp";
	public static final String ATTR_LISTE_NEWS_BACKEND="ATTR_LISTE_NEWS_BACKEND" ;  
	      
   
    public Servlet_afficher_news() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
              News_managers nm =new News_managers();
    	
    	request.getSession().setAttribute(ATTR_LISTE_NEWS_BACKEND, nm.get_list_news(10));
    	
           //   request.setAttribute(ATTR_LISTE_NEWS_BACKEND, nm.get_list_news(10));
         this.getServletContext().getRequestDispatcher( VUE_NEWS_BACKEND).forward( request, response );
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
