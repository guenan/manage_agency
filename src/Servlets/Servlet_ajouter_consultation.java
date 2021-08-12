package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Consultation;
import Managers.Consultation_managers;
import Objet_Metiers.OM_consultation;



public class Servlet_ajouter_consultation extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public final String vue_form_consultation_frontend="/vue_backend/ajouter_consultation.jsp";
    public final String vue_form_consultation_backend="/vue_backend/ajouter_consultation.jsp";
    public final String ATTR_RESULTAT_CONSULTATION="attr_resultat_consultation";
   private String resultat_consultation="";
      
    
    public Servlet_ajouter_consultation() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getAttribute("attr_destination").equals("frontend"))
		    this.getServletContext().getRequestDispatcher(vue_form_consultation_frontend).forward(request,response);
		else if(request.getAttribute("attr_destination").equals("backend"))
			this.getServletContext().getRequestDispatcher(vue_form_consultation_backend).forward(request,response);
	
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		OM_consultation om_consultation=new OM_consultation(request);
		Consultation_managers consultation_managers=new Consultation_managers();
		Consultation consultation=om_consultation.make_consultation_to_add();
		
		
		if(consultation==null){
			
			resultat_consultation="remplissez tous vos champs";
		     request.setAttribute("attr_om_consultation", om_consultation);
		     
		    
		}else{
			try{
				
			if(consultation_managers.add_bus(consultation)){		
				resultat_consultation="succes d'ajout de la consultation";
				
				request.setAttribute("consultation", consultation);
			
			}else{
				resultat_consultation="echec d'ajout la consultation";
				
				request.setAttribute("consultation", consultation);
			
				
			}
			}catch(Exception e){
				request.setAttribute("error_bd", e.getMessage());
			}
		}
		
		request.setAttribute(ATTR_RESULTAT_CONSULTATION, resultat_consultation);
		if(request.getAttribute("attr_destination").equals("frontend"))
		    this.getServletContext().getRequestDispatcher(vue_form_consultation_frontend).forward(request,response);
		else if(request.getAttribute("attr_destination").equals("backend"))
			this.getServletContext().getRequestDispatcher(vue_form_consultation_backend).forward(request,response);
	
		
	}

}
