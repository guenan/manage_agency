
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Droit_Acces;
import Beans.Employe;
import Managers.News_managers;
import Managers.Personnel_managers;
import Managers.Rapport_managers;

import javax.servlet.http.HttpSession;


public class Servlet_authentification_personnel extends HttpServlet {

    private static  String vue_authentification_employe_frontend="/vue_frontend/connexion.jsp";
    private static  String vue_authentification_employe_backend="/vue_backend/connexion.jsp";
    
    private static  String vue_index_employe_frontend="/vue_frontend/index.jsp";
    private static  String vue_index_employe_backend="/vue_backend/index.jsp";
    
    public static  String ATTR_LISTE_RAPPORT       = "ATTR_LISTE_RAPPORT";
	public static  String ATTR_LISTE_SUGGESTION       = "ATTR_LISTE_SUGGESTION";
	public static  String ATTR_LISTE_CONNECTE       = "ATTR_LISTE_CONNECTE";
	public static  String STATUT_LECTURE       = "no_read";
	public static  String STATUT_EN_LIGNE       = "on_line";
    
	 public final String USER_SESSION="user_session";
   
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ATTR_destination=(String) request.getAttribute("attr_destination");
        
        Employe employe=(Employe)request.getSession().getAttribute(USER_SESSION);
        
        if(employe != null){
        	vue_index_employe_backend="/vue_backend/index.jsp";
        	vue_index_employe_frontend="/vue_frontend/index.jsp";
        }
        
        
         if ("backend".equals(ATTR_destination)){        
            this.getServletContext().getRequestDispatcher(vue_authentification_employe_backend).forward(request,response);
        }
        
        if ("frontend".equals(ATTR_destination)){
            this.getServletContext().getRequestDispatcher(vue_authentification_employe_frontend).forward(request,response);
        }
        
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session=request.getSession();
        
        String champ_loggin=request.getParameter("loggin");
        String champ_password=request.getParameter("password");
        
        String message_remplissage_champ="";
        String message="";
        
        String ATTR_destination=(String) request.getAttribute("attr_destination");
        Droit_Acces dr=new Droit_Acces();
        
        Employe employe =new Employe();
        Employe employe1=null;
        Employe employe2=null;
        
        String test="";
        
        request.setAttribute("a", "qsfdgqsfq");
        
        Rapport_managers rm=new Rapport_managers();
		News_managers nm= new News_managers();
		Personnel_managers pm =new Personnel_managers();
		News_managers nma=new News_managers();
		
		
		
		
        
        if (champ_loggin.trim().isEmpty() || champ_password.trim().isEmpty() ){
            message_remplissage_champ="Echec de l'authentification; vous n'avez pas rempli tous les champs.";
        }
        else{
           
           
        
            
            employe.setLoggin(champ_loggin);
            employe.setPassword(champ_password);
            
           
            
            Personnel_managers test_with_bd =new Personnel_managers();
            
            employe1  = test_with_bd.authentify_employe(employe);
            
            if (employe1 != null){
                employe2 = test_with_bd.send_employe_to_session(employe1);
                
                request.getSession().setAttribute(ATTR_LISTE_CONNECTE, pm.select_all_connecte(STATUT_EN_LIGNE));
        		request.getSession().setAttribute(ATTR_LISTE_SUGGESTION, nm.get_list_suggestion(STATUT_LECTURE));
        		request.getSession().setAttribute(ATTR_LISTE_RAPPORT, rm.get_list_raport(STATUT_LECTURE));
        		//request.getSession().setAttribute(ATTR_LISTE_RAPPORT, nma.get_list_news(3));
        		
                message="Bienvenu(e) "+employe2.getNom_employe();
                session.setAttribute("user_session", employe2);
                session.setAttribute("message",message);
                request.setAttribute("message_remplissage_champ",message_remplissage_champ);
                request.setAttribute("test",test);
                
               
        		//request.getSession().setAttribute("a","qsgdq");
        	//	this.getServletContext().getRequestDispatcher( VUE_ADMIN).forward( request, response );
               
                
                if ("backend".equals(ATTR_destination)){        
                    this.getServletContext().getRequestDispatcher(vue_index_employe_backend).forward(request,response);
                }
                
                if ("frontend".equals(ATTR_destination)){
                    this.getServletContext().getRequestDispatcher(vue_index_employe_frontend).forward(request,response);
                }
                
        }
            else{
                message="loggin ou password incorret";
                request.setAttribute("message",message);
                
                if ("backend".equals(ATTR_destination)){        
                    this.getServletContext().getRequestDispatcher(vue_authentification_employe_backend).forward(request,response);
                }
                
                if ("frontend".equals(ATTR_destination)){
                    this.getServletContext().getRequestDispatcher(vue_authentification_employe_frontend).forward(request,response);
                }
            }
            
        }
       
      
       
    }

   
}
