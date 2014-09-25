/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package physio;

import java.sql.Blob;
import javax.swing.ImageIcon;

/**
 *
 * @author Lieven
 */
public class Exercise {
    
    private int ID = 0;
    private String naam = null;
    private String beginhouding = null;
    private String instructie = null;
    private ImageIcon afbeelding = null;
    private String categorie = null;
    private int level = 0;
    
    public Exercise(int ID, String naam, String beginhouding, String instructie, ImageIcon afbeelding){
        this.ID = ID;
        this.naam = naam;
        this.beginhouding = beginhouding;
        this.instructie = instructie;
        this.afbeelding = afbeelding;
    }
    
    public String getNaam(){
        return naam;
    }
    
    public String getBeginhouding(){
        return beginhouding;
    }
    
    public String getInstructie(){
        return instructie;
    }
    
    public ImageIcon getAfbeelding(){
        return afbeelding;
    }
}
