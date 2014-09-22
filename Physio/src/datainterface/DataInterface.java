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
    
     
    private static DataInterface instance = null;
    private Connection con = null;
    private static String pathname = null;
    private String url = "jdbc:firebirdsql://localhost/";
    private static final String USERNAME = "SYSDBA";
    private static final String PASSWORD = "masterkey";
    private PreparedStatement pSelectPatient = null;
    private PreparedStatement pSelectPhysio = null;
    private PreparedStatement pSelectExProg = null;
    private PreparedStatement pAddPatient = null;
    private PreparedStatement pDeletePatient = null;
    private PreparedStatement pUpdatePatient = null;
    
    /**
     * Establishes the connection to the specific database and initialises the 
     * PreparedStatement-objects
     */
    protected DataInterface(){
        try{
            establishConnection();
            initialisePreparedStatements();
        }
        catch (DataException e){}
    }
    
   public static DataInterface getInstance() {
      if(instance == null) {
         instance = new DataInterface();
      }
      return instance;
   }    
   
   public static void setPreferredDB(String path)
   {
       pathname = path;
   }
   
   private String getPathFromDialog()
   {
        JFileChooser chooseDB = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Firebird database", "fdb");
        chooseDB.setFileFilter(filter);
        int chosen = chooseDB.showDialog(this, "Select database");
        if(chosen == JFileChooser.APPROVE_OPTION){
            File database = chooseDB.getSelectedFile();
            return database.getPath();
        } else 
            return null;
   }
    
  
    private void establishConnection() throws DataException{
        
        if (pathname == null)
        {
            pathname = getPathFromDialog();
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
            pSelectPatient = con.prepareStatement("SELECT * FROM Patient ORDER BY Naam, Voornaam");
            pSelectPhysio = con.prepareStatement("SELECT * FROM Kinesist ORDER BY Naam");
            pSelectExProg = con.prepareStatement("SELECT * FROM Oefenschema join Kinesist on Oefenschema.Kinesist = Kinesist.riziv WHERE Patient = ? ORDER BY Volgnummer");
            pAddPatient = con.prepareStatement("INSERT INTO Patient VALUES (?, ?, ?, ?)");
            pDeletePatient = con.prepareStatement("DELETE FROM Patient WHERE Patientnummer = ?");
            pUpdatePatient = con.prepareStatement("UPDATE Patient SET Naam = ?, Voornaam = ?, Emailadres = ? WHERE Patientnummer = ?");
            
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
                Patient pt = new Patient(nummer, voornaam, naam, email);
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
            ResultSet res = pSelectExProg.executeQuery();
            while(res.next()){
               int volgnummer = res.getInt("Volgnummer");
               java.sql.Date datum = res.getDate("Datum");
               String riziv = res.getString("Kinesist");
               String naam = res.getString("Naam");
               String voornaam = res.getString("Voornaam");
               String email = res.getString("Emailadres");
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
    
    public void addPatient(String nummer, String achternaam, String voornaam, String email) throws DataException{
        try{
            pAddPatient.setString(1, nummer);
            pAddPatient.setString(2, achternaam);
            pAddPatient.setString(3, voornaam);
            pAddPatient.setString(4, email);
            pAddPatient.executeUpdate();
        }
        catch(SQLException e){
            throw new DataException("Fout bij toevoegen patient in database");
        }
    }
    
    public void deletePatient(String patientnummer) throws DataException{
        try{
            pDeletePatient.setString(1, patientnummer);
            pDeletePatient.executeUpdate();
        }
        catch(SQLException e){
            throw new DataException("Fout bij het verwijderen van deze patiënt");
        }
    }
    
    public void updatePatient(String patientnummer, String achternaam, String voornaam, String email) throws DataException{
        try{
           pUpdatePatient.setString(1, achternaam);
           pUpdatePatient.setString(2, voornaam);
           pUpdatePatient.setString(3, email);
           pUpdatePatient.setString(4, patientnummer);
           pUpdatePatient.executeUpdate();
        }
        catch(SQLException e){
            throw new DataException("De aangebrachte wijzigingen kunnen niet uitgevoerd worden");
        }
    }
}
