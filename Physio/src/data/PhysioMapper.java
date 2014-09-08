/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import physio.Patient;


/**
 *
 *
 * @author lievenp
 */
public class PhysioMapper extends JFrame {
    
    private Connection con = null;
    private String padnaam = null;
    private static final String DRIVERNAAM = "org.firebirdsql.jdbc.FBDriver";
    private String url = "jdbc:firebirdsql://localhost/" + padnaam;
    private final String GEBRUIKERSNAAM = "SYSDBA";
    private static final String WACHTWOORD = "masterkey";
    
    public PhysioMapper() throws PhysioException{
        JFileChooser chooseDB = new JFileChooser();
        int chosen = chooseDB.showDialog(this, "Open");
        String urlDB = null;
        if(chosen == JFileChooser.APPROVE_OPTION){
            File database = chooseDB.getSelectedFile();
            urlDB = database.getPath();
        }
        padnaam = urlDB;
        maakConnectie();
    }
    
    private void maakConnectie() throws PhysioException{
        try{
            DriverManager.setLogWriter(new PrintWriter(System.out));
            Class.forName("org.firebirdsql.jdbc.FBDriver"); 
            con = DriverManager.getConnection(url, GEBRUIKERSNAAM, WACHTWOORD);
        }
        catch (ClassNotFoundException e){
            throw new PhysioException("Driver niet geladen");
        }
        catch (SQLException e){
            throw new PhysioException("Verbinding maken is mislukt");
        }
        
    }
    
     public ArrayList<Patient> getAllPatients() {
        ArrayList<Patient> result = new ArrayList<>();
        return result;
    }
    
}
