
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Beans.Employe;
import Managers.Personnel_managers;
import Beans.Assistant;
import Beans.Chef_Agence;
import javax.servlet.http.HttpSession;
import Beans.Droit_Acces;
import Beans.Controlleur;
import Beans.Chauffeur;
import Beans.Garagiste;

/**
 *
 * @author garrick brel
 */
public class Servlet_ajouter_personnel extends HttpServlet {
    public final String vue_ajouter_personnel_frontend = "/vue_frontend/ajouter_personnel.jsp";
    public final String vue_ajouter_personnel_backend="/vue_backend/index_personnel.jsp";
    public final String vue_connexion_backend="/vue_backend/connexion.jsp";  
  
    public final int id_chef_agence=5;
    public final int id_assistant=4;
    public final int id_controlleur=3;
    public final int id_garagiste=2;
    public final int id_chauffeur=1;
    
    
    
    
    

    
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ATTR_destination=(String) request.getAttribute("attr_destination");
        String action ="ajouter_personnel";
        request.setAttribute("action", action);
        
         if ("backend".equals(ATTR_destination)){        
            
             
             this.getServletContext().getRequestDispatcher(vue_ajouter_personnel_backend).forward(request,response);
        }
        
        if ("frontend".equals(ATTR_destination)){
            
            this.getServletContext().getRequestDispatcher(vue_ajouter_personnel_frontend).forward(request,response);
        }
    
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        String ATTR_destination=(String)request.getAttribute("ATTR_destination");
        HttpSession session=request.getSession();
        Employe employe=(Employe)session.getAttribute("user_session");
        int droit_employe=employe.getDroit_employe().getId_droit();
        
         if   (employe.isChef_agence()){
        
        
         String champ_numero_cni=request.getParameter("numero_cni");
         String champ_nom_employe=request.getParameter("nom_employe");
         String champ_prenom_employe=request.getParameter("prenom_employe");
         String champ_jour_employe=request.getParameter("jour");
         String champ_mois_employe=request.getParameter("mois");
         String champ_annee_employe=request.getParameter("annee");
         String champ_date_naissance="";
         String champ_sexe=request.getParameter("sexe");
         String champ_loggin=request.getParameter("loggin");
         String champ_password=request.getParameter("password");
         String champ_niveau_acces=request.getParameter("niveau_acces");
         String champ_licencie="non_licencie";
         String champ_salaire=request.getParameter("salaire");
         String champ_statut="off_line";
         
         
         
         
         
         long num_cni1=Integer.parseInt(champ_numero_cni);
         long salaire=Long.parseLong(champ_salaire);
         
         
         Personnel_managers register_to_bd=new Personnel_managers();
         
         String message_ajout_personnel="";
         
    /*     if(champ_jour_employe.trim().isEmpty() || champ_mois_employe.trim().isEmpty() || champ_annee_employe.trim().isEmpty() || champ_numero_cni.trim().isEmpty() || champ_nom_employe.trim().isEmpty() || champ_loggin.trim().isEmpty() || champ_password.trim().isEmpty() || champ_niveau_acces.trim().isEmpty() ){
             message="Vous n'avez pas rempli tous les champs obligatoires.";
         }
         else {*/
        	 champ_date_naissance=champ_annee_employe+"-"+champ_mois_employe+"-"+champ_jour_employe;
        	 
                if (Integer.parseInt(champ_niveau_acces)==id_assistant){
                    Assistant assistant=new Assistant();
                    
                    /*employe.setNumero_cni(num_cni1);
                    employe.setDroit_employe(droit);*/
                    
                    
                    assistant.setNumero_cni(num_cni1);
                    assistant.setDate_naissance(champ_date_naissance);
                    assistant.setLoggin(champ_loggin);
                    assistant.setNom_employe(champ_nom_employe);
                    assistant.setPassword(champ_password);
                    assistant.setPrenom_employe(champ_prenom_employe);
                    assistant.setSexe(champ_sexe);
                    assistant.setLicencie(champ_licencie);
                    assistant.getDroit_employe().setId_droit(id_assistant);
                    assistant.setSalaire(salaire);
                    assistant.setStatut(champ_statut);
         
                    //register_to_bd.add_employe(employe);//
                    boolean b = register_to_bd.add_assistant(assistant);
                    if(b){
                    	String action ="ajouter_personnel";
                        request.setAttribute("action", action);
                    message_ajout_personnel="Assistant cree avec succes";
                    request.setAttribute("assistant", assistant);
                    request.setAttribute("message", message_ajout_personnel);
                    if("backend".equals(ATTR_destination)){
         
                        this.getServletContext().getRequestDispatcher(vue_ajouter_personnel_backend).forward(request,response);
                    }
                    
                    if ("frontend".equals(ATTR_destination)){
                        this.getServletContext().getRequestDispatcher(vue_ajouter_personnel_frontend).forward(request,response);
                    }
                }
                    if (!b){
                    	String action ="ajouter_personnel";
                        request.setAttribute("action", action);
                        message_ajout_personnel="echec de la creation de l'assistant";
                        request.setAttribute("message",message_ajout_personnel);        
                        if ("backend.".equals(ATTR_destination)){
                            this.getServletContext().getRequestDispatcher(vue_ajouter_personnel_backend).forward(request,response);
                        }
                        if("frontend".equals(ATTR_destination)){
                            this.getServletContext().getRequestDispatcher(vue_ajouter_personnel_frontend);
                        }
                        }
                }
                
                                if (Integer.parseInt(champ_niveau_acces)==id_controlleur){
                                	
                    Controlleur controlleur= new Controlleur();
                    String champ_code_brigade=request.getParameter("code_brigade");
                    
                    /*employe.setNumero_cni(num_cni1);
                    employe.setDroit_employe(droit);*/
                    
                    
                    controlleur.setNumero_cni(num_cni1);
                    controlleur.setDate_naissance(champ_date_naissance);
                    controlleur.setLoggin(champ_loggin);
                    controlleur.setNom_employe(champ_nom_employe);
                    controlleur.setPassword(champ_password);
                    controlleur.setPrenom_employe(champ_prenom_employe);
                    controlleur.setSexe(champ_sexe);
                    controlleur.setLicencie(champ_licencie);
                    controlleur.getDroit_employe().setId_droit(id_controlleur);
                    controlleur.setSalaire(salaire);
                   controlleur.setStatut(champ_statut);
                    controlleur.getBrigade().setCode_brigade(champ_code_brigade);
                    //register_to_bd.add_employe(employe);//
                    boolean b= register_to_bd.add_controlleur(controlleur);
                    
                    if(b){
                    	String action ="ajouter_personnel";
                        request.setAttribute("action", action);
                    message_ajout_personnel="controlleur cree avec succes";
                    request.setAttribute("controlleur", controlleur);
                    request.setAttribute("message", message_ajout_personnel);
                    if("backend".equals(ATTR_destination)){
         
                        this.getServletContext().getRequestDispatcher(vue_ajouter_personnel_backend).forward(request,response);
                    }
                    if ("frontend".equals(ATTR_destination)){
                        this.getServletContext().getRequestDispatcher(vue_ajouter_personnel_frontend).forward(request,response);
                    }
                    }
                    if (!b){
                    	String action ="ajouter_personnel";
                        request.setAttribute("action", action);
                        message_ajout_personnel="echec de la creation du controlleur";
                        request.setAttribute("message",message_ajout_personnel);        
                        if ("backend.".equals(ATTR_destination)){
                            this.getServletContext().getRequestDispatcher(vue_ajouter_personnel_backend).forward(request,response);
                        }
                        if("frontend".equals(ATTR_destination)){
                            this.getServletContext().getRequestDispatcher(vue_ajouter_personnel_frontend);
                        }
                        }
                    
                }
                                
                                
                           if (Integer.parseInt(champ_niveau_acces)==id_chauffeur){
                    Chauffeur chauffeur = new Chauffeur();
                    String champ_immatriculation=request.getParameter("immatriculation");  
                   // String champ_code_ligne=request.getParameter("date_conduite");
                  
                    
                    chauffeur.setNumero_cni(num_cni1);
                    chauffeur.setDate_naissance(champ_date_naissance);
                 //   chauffeur.setLoggin(champ_loggin);
                    chauffeur.setNom_employe(champ_nom_employe);
                  //  chauffeur.setPassword(champ_password);
                    chauffeur.setPrenom_employe(champ_prenom_employe);
                    chauffeur.setSexe(champ_sexe);
                    chauffeur.setLicencie(champ_licencie);
                    chauffeur.getDroit_employe().setId_droit(id_chauffeur);
                    chauffeur.setSalaire(salaire);
                    chauffeur.getBus().setImmatriculation(champ_immatriculation);
                    chauffeur.setStatut(champ_statut);
                  // chauffeur.getLigne().setCode_ligne(champ_code_ligne);
         
                    //register_to_bd.add_employe(employe);//
                    boolean b=register_to_bd.add_chauffeur(chauffeur);
                    
                   if(b){ 
                	   String action ="ajouter_personnel";
                       request.setAttribute("action", action);
                       message_ajout_personnel="chauffeur cree avec succes";
                    request.setAttribute("chauffeur", chauffeur);
                    request.setAttribute("message",message_ajout_personnel);
                    if("backend".equals(ATTR_destination)){
         
                        this.getServletContext().getRequestDispatcher(vue_ajouter_personnel_backend).forward(request,response);
                    }
                    if ("frontend".equals(ATTR_destination)){
                        this.getServletContext().getRequestDispatcher(vue_ajouter_personnel_frontend).forward(request,response);
                    }
                   }
                   
                   if (!b){
                	   String action ="ajouter_personnel";
                       request.setAttribute("action", action);
                       message_ajout_personnel="echec de la creation du chauffeur";
                        request.setAttribute("message",message_ajout_personnel);        
                        if ("backend.".equals(ATTR_destination)){
                            this.getServletContext().getRequestDispatcher(vue_ajouter_personnel_backend).forward(request,response);
                        }
                        if("frontend".equals(ATTR_destination)){
                            this.getServletContext().getRequestDispatcher(vue_ajouter_personnel_frontend);
                        }
                        }
                }
                
                
                if (Integer.parseInt(champ_niveau_acces) == id_chef_agence){
                
                     Chef_Agence chef_agence= new Chef_Agence();
                     
                    
         
                    /*employe.setNumero_cni(num_cni1);
                    employe.setDroit_employe(droit);*/
                     
                     
                     
                    chef_agence.setNumero_cni(num_cni1);
                    chef_agence.setDate_naissance(champ_date_naissance);
                    chef_agence.setLoggin(champ_loggin);
                    chef_agence.setNom_employe(champ_nom_employe);
                    chef_agence.setPassword(champ_password);
                    chef_agence.setPrenom_employe(champ_prenom_employe);
                    chef_agence.setSexe(champ_sexe);
                    chef_agence.setLicencie(champ_licencie);
                    chef_agence.getDroit_employe().setId_droit(id_chef_agence);
                    chef_agence.setSalaire(salaire);
                    chef_agence.setStatut(champ_statut);
                    
                    
                    //register_to_bd.add_employe(employe);//
                    boolean b= register_to_bd.add_chef_agence(chef_agence);
         
                    if(b){
                    	String action ="ajouter_personnel";
                        request.setAttribute("action", action);
                        message_ajout_personnel="Chef d'agence cree avec succes";
                    request.setAttribute("chef_agence", chef_agence);
                    request.setAttribute("message",message_ajout_personnel);
                    
                    if("backend".equals(ATTR_destination)){
         
                        this.getServletContext().getRequestDispatcher(vue_ajouter_personnel_backend).forward(request,response);
                    }
                    if ("frontend".equals(ATTR_destination)){
                        this.getServletContext().getRequestDispatcher(vue_ajouter_personnel_frontend).forward(request,response);
                    }
                    
                }
                    
                    if (!b){
                    	String action ="ajouter_personnel";
                        request.setAttribute("action", action);
                        message_ajout_personnel="echec de la creation du chef d'agence";
                        request.setAttribute("message",message_ajout_personnel);        
                        if ("backend.".equals(ATTR_destination)){
                            this.getServletContext().getRequestDispatcher(vue_ajouter_personnel_backend).forward(request,response);
                        }
                        if("frontend".equals(ATTR_destination)){
                            this.getServletContext().getRequestDispatcher(vue_ajouter_personnel_frontend);
                        }
                        }
                    
                }
                
                
                if (Integer.parseInt(champ_niveau_acces) == id_garagiste){
                
                     Garagiste garagiste= new Garagiste();
                     String champ_code_garage=request.getParameter("code_garage");
                     String champ_poste=request.getParameter("poste");
                    
         
                    /*employe.setNumero_cni(num_cni1);
                    employe.setDroit_employe(droit);*/
                     
                     
                     
                     garagiste.setNumero_cni(num_cni1);
                     garagiste.setDate_naissance(champ_date_naissance);
                     garagiste.setLoggin(champ_loggin);
                     garagiste.setNom_employe(champ_nom_employe);
                     garagiste.setPassword(champ_password);
                     garagiste.setPrenom_employe(champ_prenom_employe);
                     garagiste.setSexe(champ_sexe);
                     garagiste.setLicencie(champ_licencie);
                     garagiste.getDroit_employe().setId_droit(id_garagiste);
                     garagiste.setSalaire(salaire);
                     garagiste.setPoste(champ_poste);
                     garagiste.getGarage().setCode_garage(champ_code_garage);
                     garagiste.setStatut(champ_statut);
                    
                    //register_to_bd.add_employe(employe);//
                    boolean b= register_to_bd.add_garagiste(garagiste);
         
                    if(b){
                    	message_ajout_personnel="garagiste cree avec succes";
                    request.setAttribute("garagiste", garagiste);
                    request.setAttribute("message",message_ajout_personnel);
                    String action ="ajouter_personnel";
                    request.setAttribute("action", action);
                    if("backend".equals(ATTR_destination)){
         
                        this.getServletContext().getRequestDispatcher(vue_ajouter_personnel_backend).forward(request,response);
                    }
                    if ("frontend".equals(ATTR_destination)){
                        this.getServletContext().getRequestDispatcher(vue_ajouter_personnel_frontend).forward(request,response);
                    }
                    
                }
                    
                    if (!b){
                    	message_ajout_personnel="echec de la creation du garagiste";
                        request.setAttribute("message",message_ajout_personnel); 
                        String action ="ajouter_personnel";
                        request.setAttribute("action", action);
                        
                        if ("backend.".equals(ATTR_destination)){
                            this.getServletContext().getRequestDispatcher(vue_ajouter_personnel_backend).forward(request,response);
                        }
                        if("frontend".equals(ATTR_destination)){
                            this.getServletContext().getRequestDispatcher(vue_ajouter_personnel_frontend);
                        }
                        }
                    
                }
                
      //   }
         
         
         }
         
         
         else{
             String message_erreur="Vous ne disposez pas des droits pour ajouter un employee";
             request.setAttribute("message_erreur",message_erreur);
             
                if(ATTR_destination=="backend"){
                    this.getServletContext().getRequestDispatcher(vue_connexion_backend).forward(request,response);
                }
                if(ATTR_destination=="frontend"){
                    this.getServletContext().getRequestDispatcher(vue_connexion_backend).forward(request,response);
                }
                }
         
        }
}
