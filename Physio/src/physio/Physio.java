/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package physio;

/**
 *
 * @author Joris, Lieven
 */
public class Physio {

    private String naam = null;
    private String voornaam = null;
    private String riziv = null;
    private String emailadres = null;
    
    /**
     * Constructor met gegevens van de deelnemende kinesist
     */
    public Physio(String naam, String voornaam, String riziv, String email){
        this.naam = naam;
        this.voornaam = voornaam;
        this.riziv = riziv;
        this.emailadres = email;
    }
    
    public String getNaam()
    {
        return naam;
    }
    
    public String getVoornaam(){
        return voornaam;
    }
    
    public String getRiziv(){
        return riziv;
    }
    
    public String getEmail(){
        return emailadres;
    }
}
