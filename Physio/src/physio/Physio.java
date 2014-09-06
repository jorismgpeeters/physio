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

    private String kinesist = null;
    
    /**
     * Constructor met naam van de deelnemende kinesist
     * @param kinesist - de deelnemende kinesist
     */
    public Physio(String kinesist){
        this.kinesist = kinesist;
    }
    
    public String getKinesist()
    {
        return kinesist;
    }
    
    
}
