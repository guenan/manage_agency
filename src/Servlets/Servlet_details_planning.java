package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import Beans.Details;
import java.util.Map;
import java.util.HashMap;
import Beans.Point_Arret;
import Beans.Bus;
import Managers.Bus_managers;
import java.sql.SQLException;



public class Servlet_details_planning extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String vue_details_planning_frontend="/index.jsp"; 
	public static final String vue_details_planning_backend        = "/vue_backend/index_planning.jsp";
	public static final String ATTR_LISTE_PLANNING_BACKEND="ATTR_LISTE_PLANNING_FRONTEND" ;
	public static final String ATTR_LISTE_HORAIRE="ATTR_LISTE_HORAIRE" ;
	public static final String ATTR_LISTE_POINT_ARRET="attr_liste_point_arret_backend";
	      
       
   
    public Servlet_details_planning() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom_action="action";
		String valeur_action="details_planning";
		Bus bus =new Bus();
	
		ArrayList<Details> details_heure=new ArrayList<Details>();
		String immatriculation=request.getParameter("immatriculation");
		Bus_managers bm=new Bus_managers();
		ArrayList<String> liste_code_point_arret =new ArrayList <String>();
		ArrayList <Point_Arret> liste_point_arret=new ArrayList<Point_Arret>();
		ArrayList<String> liste_horaire =new ArrayList<String>();
	    liste_point_arret=bm.get_bus_point_arret(immatriculation);
	   					
		request.setAttribute(ATTR_LISTE_POINT_ARRET,liste_point_arret);
		
		for (int i=0; i<liste_point_arret.size();i++){
			String code_point_arret=liste_point_arret.get(i).getCode_point_arret();
			liste_code_point_arret.add(code_point_arret);
			}
		for (int j=0; j<liste_code_point_arret.size();j++){
			String code_point_arret=liste_code_point_arret.get(j);
			liste_horaire=bm.get_horaire_bus_point_arret(immatriculation, code_point_arret);
			Details details=new Details();
			details.setCode_point_point_arret(code_point_arret);
			details.setListe_horaire(liste_horaire);
			details_heure.add(details);
							
			}
			
		
		
		for (int i=0; i<details_heure.size();i++){
			System.out.println(details_heure.get(i).getCode_point_arret());
			System.out.println(details_heure.get(i).getListe_horaire());
			
		}
		
		
		request.setAttribute(ATTR_LISTE_HORAIRE,details_heure);
		
		
		
	      
		
		request.setAttribute(nom_action, valeur_action);
		if(request.getAttribute("attr_destination").equals("frontend"))
		    this.getServletContext().getRequestDispatcher(vue_details_planning_frontend).forward(request,response);
		else if(request.getAttribute("attr_destination").equals("backend"))
			this.getServletContext().getRequestDispatcher(vue_details_planning_backend).forward(request,response);

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
