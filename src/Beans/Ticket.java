
package Beans;


public class Ticket {
 
    private int id_ticket;
    private String date_impression;
    private String quantite_imprimer;

    public Ticket() {
    }

    
    
    public String getDate_impression() {
        return date_impression;
    }

    public void setDate_impression(String date_impression) {
        this.date_impression = date_impression;
    }

    public int getId_ticket() {
        return id_ticket;
    }

    public void setId_ticket(int id_ticket) {
        this.id_ticket = id_ticket;
    }

    public String getQuantite_imprimer() {
        return quantite_imprimer;
    }

    public void setQuantite_imprimer(String quantite_imprimer) {
        this.quantite_imprimer = quantite_imprimer;
    }
    
    
       
}
