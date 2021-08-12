package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Bus;
import Beans.Point_Arret;
import Managers.Brigade_managers;
import Managers.Bus_managers;
import Managers.Garage_managers;
import Managers.Ligne_managers;
import Managers.Point_Arret_managers;
import Objet_Metiers.OM_bus;
import Objet_Metiers.OM_point_arret;


public class Servlet_ajouter_point_arret extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public final String vue_form_point_arret_frontend="/vue_backend/index_flotte.jsp";
    public final String vue_form_point_arre_backend="/vue_backend/index_flotte.jsp";
    public final String ATTR_RESULTAT_POINT_ARRET="attr_resultat_point_arret";
   private String resultat_point_arret="";
   private static final String ATTR_LISTE_LIGNE="ATTR_LISTE_LIGNE"; 
   private static final String ATTR_LISTE_BRIGADE="ATTR_LISTE_BRIGADE";
   private static final String ATTR_LISTE_GARAGE="ATTR_LISTE_GARAGE";
   private static final String ATTR_LISTE_BUS="ATTR_LISTE_BUS";
   private static final String ATTR_LISTE_POINT_ARRET="ATTR_LISTE_POINT_ARRET";
    
    public Servlet_ajouter_point_arret() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("action", "index_flotte");
		
		if(request.getAttribute("attr_destination").equals("frontend"))
		    this.getServletContext().getRequestDispatcher(vue_form_point_arret_frontend).forward(request,response);
		else if(request.getAttribute("attr_destination").equals("backend"))
			this.getServletContext().getRequestDispatcher(vue_form_point_arre_backend).forward(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OM_point_arret om_point_arret=new OM_point_arret(request);
		Point_Arret_managers point_arret_managers=new Point_Arret_managers();
		Point_Arret point_arret=om_point_arret.make_point_arret();
		
		
		if(point_arret==null){
			
			resultat_point_arret="remplissez tous vos champs";
		     request.setAttribute("attr_om_point_arret", om_point_arret);
		     
		    
		}else{
			try{
				
			if(point_arret_managers.add_point_arret(point_arret)){		
				resultat_point_arret="succes d'ajout du point d'arret";
				
				request.setAttribute("point_arret", point_arret);
			
			}else{
				resultat_point_arret="echec d'ajout du point d'arret";
				
				request.setAttribute("point_arret", point_arret);
			
				
			}
			}catch(Exception e){
				request.setAttribute("error_bd", e.getMessage());
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
		request.setAttribute("rel", resultat_point_arret);
		request.setAttribute(ATTR_RESULTAT_POINT_ARRET, resultat_point_arret);
		if(request.getAttribute("attr_destination").equals("frontend"))
		    this.getServletContext().getRequestDispatcher(vue_form_point_arret_frontend).forward(request,response);
		else if(request.getAttribute("attr_destination").equals("backend"))
			this.getServletContext().getRequestDispatcher(vue_form_point_arre_backend).forward(request,response);
		
	}
	}


