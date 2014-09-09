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
import javax.swing.filechooser.FileNameExtensionFilter;
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
    private String url = "jdbc:firebirdsql://localhost/";
    private final String GEBRUIKERSNAAM = "SYSDBA";
    private static final String WACHTWOORD = "masterkey";
    
    public PhysioMapper() throws PhysioException{
        JFileChooser chooseDB = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Firebird databasefile", "fdb");
        chooseDB.setFileFilter(filter);
        int chosen = chooseDB.showDialog(this, "Selecteer de database");
        if(chosen == JFileChooser.APPROVE_OPTION){
            File database = chooseDB.getSelectedFile();
            padnaam = database.getPath();
        }
        url = url + padnaam;
        System.out.println("padnaam is: " + padnaam);
        System.out.println("URL is: " + url);
        maakConnectie();
        PreparedStatement p = null;
        try{
            p = con.prepareStatement("SELECT * FROM Oefening");
        }
        catch (SQLException e){
            throw new PhysioException("prepStatement mislukt");
        }
        String naam = null;
        String begin = null;
        String instructie = null;
        try{
            ResultSet res = p.executeQuery();
            while(res.next()){
                naam = res.getString(2);
                begin = res.getString(3);
                instructie = res.getString(4);
            }
        }
        catch(SQLException e){
            throw new PhysioException("inlezen data mislukt");
        }
        System.out.println("naam: " + naam + ", beginhouding: " + begin + ", instructie: " + instructie);
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
