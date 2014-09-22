/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package physio;

import datainterface.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 *
 * @author Lieven
 */
public class PatientAdmin {
    
    private DataInterface dataInterface = null;
    private ArrayList<Patient> patientlist = null;
    private ArrayList<Physio> physiolist = null;
    private ArrayList<ExerciseProgram> exerciseprogramlist = null;
    
    public PatientAdmin() throws DataException{
        //dataInterface = new DataInterface();
        dataInterface = DataInterface.getInstance();
        patientlist = dataInterface.readAllPatients();
        physiolist = dataInterface.readAllPhysios();
    }
    
    public ArrayList<Patient> getPatients(){
        return patientlist;
    }
    
    public ArrayList<Physio> getPhysios(){
        return physiolist;
    }
    
    public ArrayList<ExerciseProgram> getExercisePrograms(String patientnummer) throws DataException{
        Patient p = getPatient(patientnummer);
        if (p != null){
            p.setExerciseProgram(dataInterface.readExercisePrograms(p));
            exerciseprogramlist = p.getExercisePrograms();
        }
        return exerciseprogramlist;
    }
    
    private Patient getPatient(String patientnummer){
        for(Patient p: patientlist){
            if(p.getNummer().equals(patientnummer)){
                return p;
            }
        }
        return null;
    }
    
    public ExerciseProgram getExerciseProgramDetail(String patientnummer, int volgnummer) throws DataException{
        ExerciseProgram exprog = null;
        for(ExerciseProgram e: getExercisePrograms(patientnummer)){
            String ePatientnummer = e.getPatient().getNummer();
            if(ePatientnummer.equals(patientnummer) && e.getVolgnummer() == volgnummer){
                exprog = e;
                return exprog;
            }
        }
        return exprog;
    }
    
    public void addPatient(String nummer, String achternaam, String voornaam, String email) throws DataException{
        dataInterface.addPatient(nummer, achternaam, voornaam, email);
        patientlist = dataInterface.readAllPatients();
    }
    
    public void deletePatient(Patient patient) throws DataException{
        String patientnummer = patient.getNummer();
        dataInterface.deletePatient(patientnummer);
        patientlist = dataInterface.readAllPatients();
    }
    
    public void updatePatient(String nummer, String achternaam, String voornaam, String email) throws DataException{
        dataInterface.updatePatient(nummer, achternaam, voornaam, email);
        patientlist = dataInterface.readAllPatients();
    }
}
