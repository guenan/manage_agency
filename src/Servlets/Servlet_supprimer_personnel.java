package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Managers.Personnel_managers;

public class Servlet_supprimer_personnel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	public final String vue_supprimer_personnel_backend="/vue_backend/index_personnel.jsp";  
	
	
       public Servlet_supprimer_personnel() {
        super();
            }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int succes_delete=0;
		int number_to_delete=Integer.parseInt(request.getParameter("number"));
		Personnel_managers pm=new Personnel_managers();
		
	
		
		for(int i=0;i<number_to_delete;i++){
		   int cni=Integer.parseInt(request.getParameter("param"+i));
		  if(	pm.delete_employe(cni) != 1){
			succes_delete=1;
		   }
		}
		
		 response.setContentType("text/xml");
	      response.setHeader("Cache-Control", "no-cache");
	      response.getWriter().write("<response>" +
	      								"<statut>"+succes_delete+"</statut>" +	      								
	      							 "</response>");
		
		
	
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
