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
            pSelectPatient = con.prepareStatement("SELECT * FROM Patient");
            pSelectPhysio = con.prepareStatement("SELECT * FROM Kinesist");
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
    
    public ArrayList<Patient> getAllPatients() {
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
    
}
