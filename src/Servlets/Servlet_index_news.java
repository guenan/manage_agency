package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Managers.News_managers;



public class Servlet_index_news extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ATTR_LISTE_NEWS_BACKEND="ATTR_LISTE_NEWS_BACKEND" ;
	
	public final String vue_form_news_frontend="/vue_backend/index_news.jsp";
    public final String vue_form_news_backend="/vue_backend/index_news.jsp";   
   
   
    public Servlet_index_news() {
        super();
        
        }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("action", "index_news");
		 News_managers nm =new News_managers();
	    	
	    	request.setAttribute(ATTR_LISTE_NEWS_BACKEND, nm.get_list_news(10));
	    	
	           //   request.setAttribute(ATTR_LISTE_NEWS_BACKEND, nm.get_list_news(10));
	         
		
		if(request.getAttribute("attr_destination").equals("frontend"))
		    this.getServletContext().getRequestDispatcher(vue_form_news_frontend).forward(request,response);
		else if(request.getAttribute("attr_destination").equals("backend"))
			this.getServletContext().getRequestDispatcher(vue_form_news_backend).forward(request,response);
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
