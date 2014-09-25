/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package physio;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Lieven
 */
public class ExerciseProgram {
    
    private Patient patient = null;
    private int volgnummer = 0;
    private Date datum = null;
    private Physio physio = null;
    private ArrayList<Exercise> exercises = null;
    
    public ExerciseProgram(Patient patient, int volgnummer, Date datum, Physio physio){
        this.patient = patient;
        this.volgnummer = volgnummer;
        this.datum = datum;
        this.physio = physio;
    }
    
    public Patient getPatient(){
        return patient;
    }
    
    public int getVolgnummer(){
        return volgnummer;
    }
    
    public Date getDatum(){
        return datum;
    }
    
    public Physio getPhysio(){
        return physio;
    }
}
