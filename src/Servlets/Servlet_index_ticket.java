package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Bus;
import Beans.Ligne;
import Managers.Bus_managers;
import Managers.Ligne_managers;


public class Servlet_index_ticket extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ATTR_LISTE_LIGNE="ATTR_LISTE_LIGNE";
	private static final String TAILLE="TAILLE";
	
	public final String vue_form_flotte_frontend="/vue_frontend/Imprimante.jsp";
   public final String vue_form_flotte_backend="/exemple/Imprimante.jsp";  
     
       
    
   
    public Servlet_index_ticket() {
        super();
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Ligne_managers lm=new Ligne_managers();
		ArrayList<Ligne> list_ligne=lm.select_all_ligne();
		request.setAttribute(ATTR_LISTE_LIGNE,list_ligne );
		request.setAttribute(TAILLE,list_ligne.size());
		 
		
		if(request.getAttribute("attr_destination").equals("frontend"))
		    this.getServletContext().getRequestDispatcher(vue_form_flotte_frontend).forward(request,response);
		else if(request.getAttribute("attr_destination").equals("backend"))
			this.getServletContext().getRequestDispatcher(vue_form_flotte_backend).forward(request,response);
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
