
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Managers.News_managers;


public class Servlet_index extends HttpServlet {
public static final String VUE_ACCEUIL        = "/index.jsp";
public static final String ATTR_LISTE_NEWS_FRONTEND="ATTR_LISTE_NEWS_FRONTEND" ;  
    

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	News_managers nm =new News_managers();
    	
    	request.setAttribute("action", "index_news");
    	request.setAttribute(ATTR_LISTE_NEWS_FRONTEND, nm.get_list_news(6));
        
         this.getServletContext().getRequestDispatcher( VUE_ACCEUIL).forward( request, response );
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       
    }

    
  
}
