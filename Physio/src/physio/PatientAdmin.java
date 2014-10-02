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
    private ArrayList<Exercise> exerciselist = null;
    private ArrayList<String> zonelist = null;
    private ArrayList<String> typelist = null;
    
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
    
    public ArrayList<Exercise> getExercises() throws DataException{
        exerciselist = dataInterface.readAllExercises();
        return exerciselist;
    }
    
    public ArrayList<String> getZones() throws DataException{
        zonelist = dataInterface.readAllZones();
        return zonelist;
    }
    
    public ArrayList<String> getTypes() throws DataException{
        typelist = dataInterface.readAllTypes();
        return typelist;
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
    
     public void addPhysio(String riziv, String achternaam, String voornaam, String email) throws DataException{
        dataInterface.addPhysio(riziv, achternaam, voornaam, email);
        physiolist = dataInterface.readAllPhysios();
    }
     
    public void updatePhysio(String riziv, String achternaam, String voornaam, String email) throws DataException{
        dataInterface.updatePhysio(riziv, achternaam, voornaam, email);
        physiolist = dataInterface.readAllPhysios();
    }
    
    public void deletePhysio(Physio physio) throws DataException{
        String riziv = physio.getRiziv();
        dataInterface.deletePhysio(riziv);
        physiolist = dataInterface.readAllPhysios();
    }
    
    public void addExProg(String patientnummer, java.sql.Date datum, String rizivkine) throws DataException{
        dataInterface.addExProg(patientnummer, datum, rizivkine);
    }
    
    public void deleteExProg(String patientnummer, int volgnummer) throws DataException{
        dataInterface.deleteExProg(patientnummer, volgnummer);
    }
}
