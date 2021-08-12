
package Filtre;
import Beans.Employe;
import Utilitaire.Parseur;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class filtreur implements Filter {
    
  public final String ERROR_PAGE = "/Ressources/errors/error_page.jsp";
  public final String USER_SESSION="user_session";
  public final String ATTR_ACTION="attr_action";
  public final String ATTR_DESTINATION="attr_destination";
  public final String nom_action_connexion="authentification_personnel";
  public final String nom_destination_connexion="backend";
   public final String module_connexion="personnel";
   
    public filtreur() {
    }    
    
    @Override
     public void destroy() {
		
	}
  
    @Override
    public void init(FilterConfig fConfig) throws ServletException {
		
	}
       
  

    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {       
              
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        Parseur parse=new Parseur();
        
        HttpSession session=req.getSession();
       String chemin ="file:///"+req.getRealPath("/Ressources/XML/routes.xml");
        
        String nom_module = req.getParameter("module"), nom_action=req.getParameter("action"), nom_destination=req.getParameter("destination"), context = req.getContextPath();
        String url = req.getRequestURI().substring(context.length() );
        
        if (url.startsWith( "/Ressources" ) || url.startsWith( "/exemple" )) {
				chain.doFilter( req, res );
				return;
			}
	if(((url.endsWith("/index.jsp")) && nom_module == null && nom_action == null && nom_destination==null) || (chemin.length() == 1)){
				request.getServletContext().getRequestDispatcher("/Servlet_acceuil").forward(request, response);
				return;
			}
	if(((url.endsWith("/admin")) && nom_module == null && nom_action == null && nom_destination==null) || (chemin.length() == 1)){
		request.getServletContext().getRequestDispatcher("/Servlet_admin").forward(request, response);
		return;
	}
			if(parse.routeExist(nom_module,nom_action,nom_destination, chemin)){
                            
                              if(nom_destination.equals("frontend")){
                                  
                                req.setAttribute(ATTR_ACTION, nom_action);
                                req.setAttribute(ATTR_DESTINATION, nom_destination);
				req.getServletContext().getRequestDispatcher("/Servlet_"+nom_module).forward(req, res);
                                
                              }else if(nom_destination.equals("backend")){
                            	  Employe employe=(Employe)session.getAttribute(USER_SESSION);
                              if( employe != null){// on teste si l'employe est connecté
                            	  req.setAttribute(ATTR_ACTION, nom_action);
                                  req.setAttribute(ATTR_DESTINATION, nom_destination);
				req.getServletContext().getRequestDispatcher("/Servlet_"+nom_module).forward(req, res);
                                   //envoie l'utilisateur vers la page de connexion nom_action
                                 
                               }else{                               
                            	   req.setAttribute(ATTR_ACTION, nom_action_connexion);
                                   req.setAttribute(ATTR_DESTINATION, nom_destination_connexion);
				   req.getServletContext().getRequestDispatcher("/Servlet_"+module_connexion).forward(req, res);
                              }
                              }
                        } else{
				request.getServletContext().getRequestDispatcher(ERROR_PAGE).forward(req, res);
                        }
		}
    }

   

