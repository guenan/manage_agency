package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import Beans.Recette;
import Managers.Recette_managers;
import java.util.ArrayList;


public class Servlet_rechercher_recette extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public final String vue_recherche_recette="/vue_backend/index_recette.jsp";
	public final String ATTR_LISTE_RECHERCHE_RECETTE="ATTR_LISTE_RECHERCHE_RECETTE";
       
   
    public Servlet_rechercher_recette() {
        super();
        }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("action", "recherche_recette");
		this.getServletContext().getRequestDispatcher(vue_recherche_recette).forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String champ_jour=request.getParameter("jour");
	String champ_mois=request.getParameter("mois");
	String champ_annee=request.getParameter("annee");
	String champ_date_recrutement=champ_annee+"-"+champ_mois+"-"+champ_jour;
	Timestamp date_enregistrement=null;
	ArrayList<Recette> liste_recette=new ArrayList<Recette>();
	Recette recette=new Recette();
	Recette_managers rm=new Recette_managers();
	liste_recette=rm.search_recette(date_enregistrement.valueOf(champ_date_recrutement));
	
	
	request.setAttribute("action", "recherche_recette");
	request.setAttribute(ATTR_LISTE_RECHERCHE_RECETTE, liste_recette);
	this.getServletContext().getRequestDispatcher(vue_recherche_recette).forward(request,response);
	
	
	}

}
