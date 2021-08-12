
package Utilitaire;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import java.io.IOException;
import org.w3c.dom.*;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Parseur {
    
    private Document document = null;
	private DOMParser parser = null;

    public Parseur() {
    }
        
        public boolean chargeroute(String filePath){
		try{
			parser = new DOMParser();
			parser.parse(filePath);
			document = parser.getDocument();
		}catch(Exception ex){
                    System.out.println(ex.getMessage());
			return false;
		}
		return true;
	}
        
       public boolean routeExist(String nom_module, String nom_action,String destination,String filePath){
	
         if((nom_module!=null && nom_action!=null && destination!=null)){
           if(chargeroute(filePath)){
			NodeList nodeList = document.getElementsByTagName("route");
			int length = nodeList.getLength();
			for(int i=0; i<length; i++){
                          //  System.out.println("<route module="+nodeList.item(i).getAttributes().getNamedItem("module").getTextContent()+" action="+nodeList.item(i).getAttributes().getNamedItem("action").getTextContent()+" destination="+nodeList.item(i).getAttributes().getNamedItem("destination").getTextContent()+">");
                             
                              if(nodeList.item(i).getAttributes().getNamedItem("module").getTextContent().equals(nom_module)&&
                                     nodeList.item(i).getAttributes().getNamedItem("action").getTextContent().equals(nom_action)&&
                                      nodeList.item(i).getAttributes().getNamedItem("destination").getTextContent().equals(destination)){
                                  
                                  
                                  return true;
                              }
                       
                        }
			}
                        
		}
           
          return false;
		
	} 
       
       public static void main(String arg[]) throws SAXException, IOException{
         
           Parseur parse=new Parseur();
           String chemin="./web/Ressources/XML/routes.xml";
        System.out.println(parse.routeExist("personnel","ajouter_personnel","backend",chemin));
         
      /* try{
        
               DOMParser  parser = new DOMParser();
			parser.parse("./web/Ressources/XML/routes.xml");
		Document	document = parser.getDocument();
                System.out.println("succes parsage");
           }catch(IOException e){
               System.out.println(e.getMessage());
           }*/
      }
}
