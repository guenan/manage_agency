package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Bus;
import Beans.Ligne;
import Managers.Brigade_managers;
import Managers.Bus_managers;
import Managers.Garage_managers;
import Managers.Ligne_managers;
import Managers.Point_Arret_managers;
import Objet_Metiers.OM_bus;
import Objet_Metiers.OM_ligne;


@WebServlet("/Servlet_ajouter_ligne")
public class Servlet_ajouter_ligne extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public final String vue_form_ligne_frontend="/vue_backend/index_flotte.jsp";
    public final String vue_form_ligne_backend="/vue_backend/index_flotte.jsp";
    public final String ATTR_RESULTAT_LIGNE="attr_resultat_ligne";
   private String resultat_ligne="";
   private static final String ATTR_LISTE_LIGNE="ATTR_LISTE_LIGNE"; 
   private static final String ATTR_LISTE_BRIGADE="ATTR_LISTE_BRIGADE";
   private static final String ATTR_LISTE_GARAGE="ATTR_LISTE_GARAGE";
   private static final String ATTR_LISTE_BUS="ATTR_LISTE_BUS";
   private static final String ATTR_LISTE_POINT_ARRET="ATTR_LISTE_POINT_ARRET";
    
    public Servlet_ajouter_ligne() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("action", "ajouter_ligne");
		
		if(request.getAttribute("attr_destination").equals("frontend"))
		    this.getServletContext().getRequestDispatcher(vue_form_ligne_frontend).forward(request,response);
		else if(request.getAttribute("attr_destination").equals("backend"))
			this.getServletContext().getRequestDispatcher(vue_form_ligne_backend).forward(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		OM_ligne om_ligne=new OM_ligne(request);
		Ligne_managers ligne_managers=new Ligne_managers();
		Ligne ligne=om_ligne.make_ligne();
		
		
		if(ligne==null){
			
			resultat_ligne="remplissez tous vos champs";
		     request.setAttribute("attr_om_ligne", om_ligne);
		     
		    
		}else{
			try{
				
			if(ligne_managers.add_ligne(ligne)){		
				resultat_ligne="succes d'ajout de la ligne";
				
				request.setAttribute("ligne", ligne);
			
			}else{
				resultat_ligne="echec d'ajout du bus";
				
				request.setAttribute("ligne", ligne);
			
				
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
		request.setAttribute("rel", resultat_ligne);
		request.setAttribute(ATTR_RESULTAT_LIGNE, resultat_ligne);
		if(request.getAttribute("attr_destination").equals("frontend"))
		    this.getServletContext().getRequestDispatcher(vue_form_ligne_frontend).forward(request,response);
		else if(request.getAttribute("attr_destination").equals("backend"))
			this.getServletContext().getRequestDispatcher(vue_form_ligne_backend).forward(request,response);
		
	}

	}


