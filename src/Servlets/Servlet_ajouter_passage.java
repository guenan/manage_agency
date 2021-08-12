package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Garage;
import Beans.Passage;
import Managers.Garage_managers;
import Managers.Passage_managers;
import Objet_Metiers.OM_garage;
import Objet_Metiers.OM_passage;



public class Servlet_ajouter_passage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public final String vue_form_passage_frontend="/vue_backend/ajouter_passage.jsp";
    public final String vue_form_passage_backend="/vue_backend/ajouter_passage.jsp";
    public final String ATTR_RESULTAT_PASSAGE="attr_resultat_epassag";
   private String resultat_passage="";   
     
    
    public Servlet_ajouter_passage() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getAttribute("attr_destination").equals("frontend"))
		    this.getServletContext().getRequestDispatcher(vue_form_passage_frontend).forward(request,response);
		else if(request.getAttribute("attr_destination").equals("backend"))
		this.getServletContext().getRequestDispatcher(vue_form_passage_backend).forward(request,response);
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		OM_passage om_passage=new OM_passage(request);
		Passage_managers passage_managers=new Passage_managers();
		Passage passage=om_passage.make_passage();
		
		
		if(passage==null){
			
			resultat_passage="remplissez tous vos champs";
		     request.setAttribute("attr_om_passage", om_passage);
		     
		    
		}else{
			try{
				
			if(passage_managers.add_passage(passage)){		
				resultat_passage="succes d'ajout du passage";
				
				request.setAttribute("passage", passage);
			
			}else{
				resultat_passage="echec d'ajout du passage";
				
				request.setAttribute("passage", passage);
			
				
			}
			}catch(Exception e){
				request.setAttribute("error_bd", e.getMessage());
				e.printStackTrace();
			}
	}
	
	
	request.setAttribute(ATTR_RESULTAT_PASSAGE, resultat_passage);
	if(request.getAttribute("attr_destination").equals("frontend"))
	    this.getServletContext().getRequestDispatcher(vue_form_passage_frontend).forward(request,response);
	else if(request.getAttribute("attr_destination").equals("backend"))
		this.getServletContext().getRequestDispatcher(vue_form_passage_backend).forward(request,response);
	
	}

	}


