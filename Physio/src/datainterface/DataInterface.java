package datainterface;

import java.io.File;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import physio.*;

/**
 * Class responsible for communication with the database
 * @author Joris, Lieven
 */
 public class DataInterface extends JFrame {
    
    private Connection con = null;
    private String pathname = null;
    private String url = "jdbc:firebirdsql://localhost/";
    private static final String USERNAME = "SYSDBA";
    private static final String PASSWORD = "masterkey";
    private PreparedStatement pSelectPatient = null;
    private PreparedStatement pSelectPhysio = null;
    private PreparedStatement pSelectExProg = null;
    
    /**
     * Establishes the connection to the specific database and initialises the 
     * PreparedStatement-objects
     */
    public DataInterface(){
        try{
            establishConnection();
            initialisePreparedStatements();
        }
        catch (DataException e){}
    }
    
  
    private void establishConnection() throws DataException{
        JFileChooser chooseDB = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Firebird database", "fdb");
        chooseDB.setFileFilter(filter);
        int chosen = chooseDB.showDialog(this, "Select database");
        if(chosen == JFileChooser.APPROVE_OPTION){
            File database = chooseDB.getSelectedFile();
            pathname = database.getPath();
        }
        url = url + pathname;
        try{
            DriverManager.setLogWriter(new PrintWriter(System.out));
            Class.forName("org.firebirdsql.jdbc.FBDriver"); 
            con = DriverManager.getConnection(url, USERNAME, PASSWORD);
        }
        catch (ClassNotFoundException e){
            throw new DataException("Driver not found.");
        }
        catch (SQLException e){
            throw new DataException("Connection-attempt failed.");
        }
    }
    
    
    private void initialisePreparedStatements() throws DataException{
        try{
            pSelectPatient = con.prepareStatement("SELECT * FROM Patient ORDER BY Naam");
            pSelectPhysio = con.prepareStatement("SELECT * FROM Kinesist ORDER BY Naam");
            pSelectExProg = con.prepareStatement("SELECT * FROM Oefenschema join Kinesist on Oefenschema.Kinesist = Kinesist.riziv WHERE Patient = ? ORDER BY Volgnummer");
        }
        catch (SQLException e){
            throw new DataException("Error in creating the SQL-statements");
        }
    }
    
    /**
     * Closes the connection to the database, if there exists a connection.
     */
    public void closeConnection(){
        if(con != null){
            try{
               con.close();
            }
            catch (SQLException e){}
        }  
    }
    
    public ArrayList<Patient> readAllPatients() {
        ArrayList<Patient> result = new ArrayList<>();
        try{
            ResultSet res = pSelectPatient.executeQuery();
            while(res.next()){
                String nummer = "" + res.getInt(1);
                String naam = res.getString(2);
                String voornaam = res.getString(3);
                String email = res.getString(4);
                Patient pt = new Patient(nummer, naam, voornaam, email);
                result.add(pt);
            }
        }
        catch (SQLException e){  
        }
        finally{
            return result;
        }
    }
    
     public ArrayList<Physio> readAllPhysios() {
        ArrayList<Physio> result = new ArrayList<>();
        try{
            ResultSet res = pSelectPhysio.executeQuery();
            while(res.next()){
                String naam = res.getString(2);
                String voornaam = res.getString(3);
                String riziv = res.getString(1);
                String email = res.getString(4);
                Physio physio = new Physio(naam, voornaam, riziv, email);
                result.add(physio);
            }
        }
        catch (SQLException e){  
        }
        finally{
            return result;
        }
    }
     
    public ArrayList<ExerciseProgram> readExercisePrograms(Patient patient) throws DataException{
        String patientnummer = patient.getNummer();
        ArrayList<ExerciseProgram> exerciseprograms = new ArrayList<>();
        try{
            pSelectExProg.setString(1, patientnummer);
            ResultSet res2 = pSelectExProg.executeQuery();
            while(res2.next()){
               int volgnummer = res2.getInt("Volgnummer");
               java.sql.Date datum = res2.getDate("Datum");
               String riziv = res2.getString("Kinesist");
               String naam = res2.getString("Naam");
               String voornaam = res2.getString("Voornaam");
               String email = res2.getString("Emailadres");
               Physio physio = new Physio(naam, voornaam, riziv, email);
               ExerciseProgram exprog = new ExerciseProgram(patient, volgnummer, datum, physio);
               exerciseprograms.add(exprog);
            }            
        }
        catch(SQLException e){
            throw new DataException("Fout bij inlezen oefenschema-overzicht");
        }
        return exerciseprograms;
    }
  
}
