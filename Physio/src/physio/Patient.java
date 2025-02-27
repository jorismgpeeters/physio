/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package physio;

import java.util.ArrayList;

/**
 *
 * @author Joris, Lieven
 */
public class Patient {
    private String nummer;
    private String voornaam;
    private String achternaam;
    private String emailadres;
    private ArrayList<ExerciseProgram> exerciseprograms = null;
    
    /**
     * Constructor
     * @param nummer - patient nummer
     * @param voornaam - voornaam vd patient
     * @param achternaam - achternaam vd patient
     * @param emailadres - emailadres vd patient
     */
    public Patient(String nummer, String voornaam, String achternaam, String emailadres){
        this.nummer = nummer;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.emailadres = emailadres;
    }    
    
    public String getNummer() {
        return nummer;
    }
    
    public String getVoornaam() {
        return voornaam;
    } 
    
    public String getAchternaam() {
        return achternaam;
    }
    
    public String getEmailadres() {
        return emailadres;
    }
    
    public void setExerciseProgram(ArrayList<ExerciseProgram> exerciseprograms){
        this.exerciseprograms = exerciseprograms;
    }
    
    public ArrayList<ExerciseProgram> getExercisePrograms(){
        return exerciseprograms;
    }

}
