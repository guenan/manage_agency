package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Brigade;
import Beans.Bus;
import Managers.Brigade_managers;
import Managers.Bus_managers;
import Managers.Garage_managers;
import Managers.Ligne_managers;
import Managers.Point_Arret_managers;
import Objet_Metiers.OM_brigade;
import Objet_Metiers.OM_bus;



public class Servlet_ajouter_brigade extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 public final String vue_form_brigade_frontend="/vue_frontend/index_flotte.jsp";
	    public final String vue_form_brigade_backend="/vue_backend/index_flotte.jsp";
	    public final String ATTR_RESULTAT_BRIGADE="attr_resultat_brigade";
	   private String resultat_brigade="";
	   private static final String ATTR_LISTE_LIGNE="ATTR_LISTE_LIGNE"; 
	    private static final String ATTR_LISTE_BRIGADE="ATTR_LISTE_BRIGADE";
	    private static final String ATTR_LISTE_GARAGE="ATTR_LISTE_GARAGE";
	    private static final String ATTR_LISTE_BUS="ATTR_LISTE_BUS";
	    private static final String ATTR_LISTE_POINT_ARRET="ATTR_LISTE_POINT_ARRET";
   
    public Servlet_ajouter_brigade() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("action", "ajouter_brigade");
		
		if(request.getAttribute("attr_destination").equals("frontend"))
		    this.getServletContext().getRequestDispatcher(vue_form_brigade_frontend).forward(request,response);
		else if(request.getAttribute("attr_destination").equals("backend"))
			this.getServletContext().getRequestDispatcher(vue_form_brigade_backend).forward(request,response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OM_brigade om_brigade=new OM_brigade(request);
		Brigade_managers brigade_managers=new Brigade_managers();
		Brigade brigade=om_brigade.make_brigade();
		
		if(brigade==null){
			resultat_brigade ="remplissez correctement vos champs";
		     request.setAttribute("attr_om_brigade", om_brigade);
		     request.setAttribute(ATTR_RESULTAT_BRIGADE, resultat_brigade);
			//this.getServletContext().getRequestDispatcher("/Ressources/errors/error_remplissage.jsp").forward(request,response);
			
		     
		    
		}else{
			
			try{
				//request.setAttribute("brigade", brigade);
				
			if(brigade_managers.add_brigade(brigade)){		
				resultat_brigade ="succes d'ajout de la brigade";				
				
			}else{
				resultat_brigade ="echec d'ajout de la brigade";				
				
			}
			}catch(Exception e){
				
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
		request.setAttribute(ATTR_RESULTAT_BRIGADE, resultat_brigade);
		request.setAttribute("rel", resultat_brigade);
		if(request.getAttribute("attr_destination").equals("frontend"))
		    this.getServletContext().getRequestDispatcher(vue_form_brigade_frontend).forward(request,response);
		else if(request.getAttribute("attr_destination").equals("backend"))
			this.getServletContext().getRequestDispatcher(vue_form_brigade_backend).forward(request,response);
		
		
	}
	}


