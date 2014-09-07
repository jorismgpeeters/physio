/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package datainterface;

import physio.*;
import java.util.ArrayList;

/**
 *
 * @author Joris
 */
public class DataInterface {
    
    public ArrayList<Patient> getAllPatients() {
        ArrayList<Patient> result = new ArrayList<>();
        
        // Mock data - to be replaced by actual db connection.
        
        Patient patientA = new Patient("1000001", "Donald", "Duck", "donald@familieDuck.wd");
        Patient patientB = new Patient("1000002", "Daisy", "Duck", "daisy@familieDuck.wd");
        result.add(patientA);
        result.add(patientB);
        
        return result;
    }
    
}
