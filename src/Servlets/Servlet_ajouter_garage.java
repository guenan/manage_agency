package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Garage;
import Beans.News;
import Managers.Brigade_managers;
import Managers.Bus_managers;
import Managers.Garage_managers;
import Managers.Ligne_managers;
import Managers.Point_Arret_managers;
import Objet_Metiers.OM_garage;


public class Servlet_ajouter_garage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public final String vue_form_garage_frontend="/vue_backend/index_flotte.jsp";
    public final String vue_form_garage_backend="/vue_backend/index_flotte.jsp";
    public final String ATTR_RESULTAT_GARAGE="attr_resultat_garage";
   private String resultat_garage="";   
   private static final String ATTR_LISTE_LIGNE="ATTR_LISTE_LIGNE"; 
   private static final String ATTR_LISTE_BRIGADE="ATTR_LISTE_BRIGADE";
   private static final String ATTR_LISTE_GARAGE="ATTR_LISTE_GARAGE";
   private static final String ATTR_LISTE_BUS="ATTR_LISTE_BUS";
   private static final String ATTR_LISTE_POINT_ARRET="ATTR_LISTE_POINT_ARRET";
    public Servlet_ajouter_garage() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("action", "index_flotte");
		
		if(request.getAttribute("attr_destination").equals("frontend"))
		    this.getServletContext().getRequestDispatcher(vue_form_garage_frontend).forward(request,response);
		else if(request.getAttribute("attr_destination").equals("backend"))
			this.getServletContext().getRequestDispatcher(vue_form_garage_backend).forward(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OM_garage om_garage=new OM_garage(request);
		Garage_managers garage_managers=new Garage_managers();
		Garage garage=om_garage.make_garage_to_add();
		
		
		if(garage==null){
			
			resultat_garage="remplissez tous vos champs";
		     request.setAttribute("attr_om_garage", om_garage);
		     
		    
		}else{
			try{
				
			if(garage_managers.add_garage(garage)){		
				resultat_garage="succes d'ajout du garage";
				
				request.setAttribute("garage", garage);
			
			}else{
				resultat_garage="echec d'ajout du garage";
				
				request.setAttribute("garage", garage);
			
				
			}
			}catch(Exception e){
				request.setAttribute("error_bd", e.getMessage());
				e.printStackTrace();
			}
	}
	
		Bus_managers bm=new Bus_managers();
		Ligne_managers lm=new Ligne_managers();
		Brigade_managers brig =new Brigade_managers();
		Point_Arret_managers pam=new Point_Arret_managers();
		Garage_managers gm=new Garage_managers();
		
		request.getSession().setAttribute(ATTR_LISTE_LIGNE, lm.select_all_ligne());
		request.setAttribute(ATTR_LISTE_BRIGADE, brig.select_all_brigade());
		request.setAttribute(ATTR_LISTE_GARAGE, gm.select_all_garage());
		request.setAttribute(ATTR_LISTE_BUS, bm.select_all_bus());
		request.setAttribute(ATTR_LISTE_POINT_ARRET, pam.select_all_point_arret());
		
		request.setAttribute("action", "index_flotte");
		request.setAttribute("rel", resultat_garage);
	request.setAttribute(ATTR_RESULTAT_GARAGE, resultat_garage);
	if(request.getAttribute("attr_destination").equals("frontend"))
	    this.getServletContext().getRequestDispatcher(vue_form_garage_frontend).forward(request,response);
	else if(request.getAttribute("attr_destination").equals("backend"))
		this.getServletContext().getRequestDispatcher(vue_form_garage_backend).forward(request,response);
	
	}

}
