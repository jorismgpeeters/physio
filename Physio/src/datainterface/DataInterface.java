package datainterface;

import java.awt.Image;
import java.io.File;
import java.io.InputStream;
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
    private PreparedStatement pAddPhysio = null;
    private PreparedStatement pDeletePhysio = null;
    private PreparedStatement pUpdatePhysio = null;
    private PreparedStatement pAddExProg = null;
    private PreparedStatement pDeleteExProg = null;
    private PreparedStatement pGetMaxVolgnummerExProg = null;
    private PreparedStatement pGetMaxIDExProg = null;
    private PreparedStatement pSelectExercise = null;
    private PreparedStatement pSelectZone = null;
    private PreparedStatement pSelectType = null;
    private PreparedStatement pSelectExerciseFilterZone = null;
    private PreparedStatement pSelectExerciseFilterType = null;
    private PreparedStatement pSelectExerciseFilterZoneType = null;
    
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
            pSelectPhysio = con.prepareStatement("SELECT * FROM Kinesist ORDER BY Naam, Voornaam");
            pSelectExProg = con.prepareStatement("SELECT * FROM Oefenschema join Kinesist on Oefenschema.Kinesist = Kinesist.riziv WHERE Patient = ? ORDER BY Volgnummer");
            pAddPatient = con.prepareStatement("INSERT INTO Patient VALUES (?, ?, ?, ?)");
            pDeletePatient = con.prepareStatement("DELETE FROM Patient WHERE Patientnummer = ?");
            pUpdatePatient = con.prepareStatement("UPDATE Patient SET Naam = ?, Voornaam = ?, Emailadres = ? WHERE Patientnummer = ?");
            pAddPhysio = con.prepareStatement("INSERT INTO Kinesist VALUES (?, ?, ?, ?)");
            pDeletePhysio = con.prepareStatement("DELETE FROM Kinesist WHERE Riziv = ?");
            pUpdatePhysio = con.prepareStatement("UPDATE Kinesist SET Naam = ?, Voornaam = ?, Emailadres = ? WHERE Riziv = ?");
            pAddExProg = con.prepareStatement("INSERT INTO Oefenschema VALUES (?, ?, ?, ?, ?)");
            pDeleteExProg = con.prepareStatement("DELETE FROM Oefenschema WHERE Patient = ? AND Volgnummer = ?");
            pGetMaxVolgnummerExProg = con.prepareStatement("SELECT max(Volgnummer) FROM Oefenschema WHERE Patient = ?");
            pGetMaxIDExProg = con.prepareStatement("SELECT max(ID) FROM Oefenschema");
            pSelectExercise = con.prepareStatement("SELECT * FROM Oefening");
            pSelectZone = con.prepareStatement("SELECT * FROM Locatie");
            pSelectType = con.prepareStatement("SELECT * FROM Type");
            pSelectExerciseFilterZone = con.prepareStatement("SELECT * FROM Oefening join OefeningLocatie on Oefening.ID = OefeningLocatie.Oefening WHERE OefeningLocatie.Locatie = ?");
            pSelectExerciseFilterType = con.prepareStatement("SELECT * FROM Oefening join OefeningType on Oefening.ID = OefeningType.Oefening WHERE OefeningType.Type = ?");
            pSelectExerciseFilterZoneType = con.prepareStatement("SELECT * FROM Oefening join OefeningLocatie on Oefening.ID = OefeningLocatie.Oefening join OefeningType on Oefening.ID = OefeningType.Oefening WHERE OefeningLocatie.Locatie = ? AND OefeningType.Type = ?");
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
            pDeletePatient.setInt(1, Integer.parseInt(patientnummer));
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
    
    public void addPhysio(String riziv, String achternaam, String voornaam, String email) throws DataException{
        try{
            pAddPhysio.setString(1, riziv);
            pAddPhysio.setString(2, achternaam);
            pAddPhysio.setString(3, voornaam);
            pAddPhysio.setString(4, email);
            pAddPhysio.executeUpdate();
        }
        catch(SQLException e){
            throw new DataException("Fout bij toevoegen kinesist in database");
        }
    }
    
    public void updatePhysio(String riziv, String achternaam, String voornaam, String email) throws DataException{
        try{
           pUpdatePhysio.setString(1, achternaam);
           pUpdatePhysio.setString(2, voornaam);
           pUpdatePhysio.setString(3, email);
           pUpdatePhysio.setString(4, riziv);
           pUpdatePhysio.executeUpdate();
        }
        catch(SQLException e){
            throw new DataException("De aangebrachte wijzigingen kunnen niet uitgevoerd worden");
        }
    }
    
    public void deletePhysio(String riziv) throws DataException{
        try{
            pDeletePhysio.setString(1, riziv);
            pDeletePhysio.executeUpdate();
        }
        catch(SQLException e){
            throw new DataException("Fout bij het verwijderen van deze kinesist");
        }
    }
    
    public void addExProg(String patientnummer, java.sql.Date datum, String rizivkine) throws DataException {
        try{
            int ID = getNewID();
            pAddExProg.setInt(1, ID);
            pAddExProg.setInt(2, Integer.parseInt(patientnummer));
            pAddExProg.setInt(3, getNextVolgnummer(patientnummer));
            pAddExProg.setDate(4, datum);
            pAddExProg.setString(5, rizivkine);
            pAddExProg.executeUpdate();
        }
        catch(SQLException e){
            throw new DataException("Fout bij toevoegen oefenschema in database");
        }  
    }
    
    public void deleteExProg(String patientnummer, int volgnummer) throws DataException{
        try{
            pDeleteExProg.setInt(1, Integer.parseInt(patientnummer));
            pDeleteExProg.setInt(2, volgnummer);
            pDeleteExProg.executeUpdate();
        }
        catch(SQLException e){
            throw new DataException("Fout bij het verwijderen van dit oefenschema");
        }
    }
    
    public ArrayList<Exercise> readAllExercises() throws DataException{
        ArrayList<Exercise> exercises = new ArrayList<>();
        try{
            ResultSet res = pSelectExercise.executeQuery();
            while(res.next()){
                int ID = res.getInt("Id");
                String naam = res.getString("Naam");
                String beginhouding = res.getString("Beginhouding");
                String instructie = res.getString("Instructie");
                Blob afbeeldingDB = res.getBlob("Afbeelding");
                ImageIcon afbeelding = null;
                if(afbeeldingDB != null){
                    byte[] afbeeldingByte = afbeeldingDB.getBytes(1L, (int)afbeeldingDB.length());
                    afbeelding = new ImageIcon(afbeeldingByte);
                    Image image = afbeelding.getImage();
                    Image newimg = image.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
                    afbeelding = new ImageIcon(newimg);
                }
                Exercise exercise = new Exercise(ID, naam, beginhouding, instructie, afbeelding);
                exercises.add(exercise); 
            }
            return exercises;
        }
        catch(SQLException e){
            throw new DataException("Fout bij het opvragen van de oefeningen");
        }
    }
    
    //public ArrayList<Exercise> readFilteredExercises(String locatie, String type) throws DataException{
    //    ArrayList<Exercise> exercises = new ArrayList<>();
        
    //}
    
    public ArrayList<String> readAllZones() throws DataException{
        ArrayList<String> zones = new ArrayList<>();
        try{
            ResultSet res = pSelectZone.executeQuery();
            while(res.next()){
                String zone = res.getString("Naam");
                zones.add(zone);
            }
            return zones;
        }
        catch(SQLException e){
            throw new DataException("Fout bij het opvragen van de lichaamszones");
        }
    }
    
    public ArrayList<String> readAllTypes() throws DataException{
        ArrayList<String> types = new ArrayList<>();
        try{
            ResultSet res = pSelectType.executeQuery();
            while(res.next()){
                String type = res.getString("Naam");
                types.add(type);
            }
            return types;
        }
        catch(SQLException e){
            throw new DataException("Fout bij het opvragen van de oefeningtypes");
        }
    }
    
    private int getNewID() throws DataException{
        try{
            int ID = 1;
            ResultSet res = pGetMaxIDExProg.executeQuery();
            while(res.next()){
                ID = res.getInt("Max") + 1;
            }
            res.close();
            return ID;
        }
        catch(SQLException e){
            throw new DataException("Nieuw ID kan niet bepaald worden");
        }
    }
    
    private int getNextVolgnummer(String patientnummer) throws DataException {
        int maxvolgnummer = 0;
        try{
            pGetMaxVolgnummerExProg.setString(1, patientnummer);
            ResultSet res = pGetMaxVolgnummerExProg.executeQuery();
            while(res.next()){
                maxvolgnummer = res.getInt("Max") + 1;
            }
            return maxvolgnummer;
            
        }
        catch(SQLException e){
            throw new DataException("");
        }
    }
}
