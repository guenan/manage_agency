package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Employe;
import Managers.Personnel_managers;

public class Servlet_rechercher_personnel extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String vue_rechercher_personnel_backend="/vue_backend/index_personnel.jsp";
	public final String ATTR_RESULTAT_EMPLOYE_CHERCHE="attr_resultat_employe_cherche";   

    public Servlet_rechercher_personnel() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String succes_recherche="recherche terminée";
		
		 String champ_numero_cni=request.getParameter("numero_cni");
		 Long numero_cni=Long.parseLong(champ_numero_cni);
		 Personnel_managers pm=new Personnel_managers();
		 Employe employe=new Employe();
		 employe=pm.search_employe(numero_cni);
		
		response.setContentType("text/xml");
	      response.setHeader("Cache-Control", "no-cache");
	      response.getWriter().write("<response>" +
	    		  						"<resultat>"+employe.getNumero_cni()+"</resultat>" +
	    		  						"<resultat>"+employe.getNom_employe()+"</resultat>" +
	    		  						"<resultat>"+employe.getPrenom_employe()+"</resultat>" +
	    		  						"<resultat>"+employe.getDate_naissance()+"</resultat>" +
	    		  						"<resultat>"+employe.getSexe()+"</resultat>" +
	    		  						"<resultat>"+employe.getDroit_employe().getNom_droit()+"</resultat>" +
	    		  						"<resultat>"+employe.getLoggin()+"</resultat>" +
	    		  						"<resultat>"+employe.getSalaire()+"</resultat>" +
	    		  						"<resultat>"+employe.getDate_recrutement()+"</resultat>" +
	    		  						"<resultat>"+employe.getLicencie()+"</resultat>" +
	      							 "</response>");
	      System.out.println("gtryuiiiuuu");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	 
	 
	
	}

}
