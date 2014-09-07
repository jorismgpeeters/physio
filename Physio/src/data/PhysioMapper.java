/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

import java.io.*;
import java.sql.*;


/**
 *
 *
 * @author lievenp
 */
public class PhysioMapper {
    
    private Connection con = null;
    
    public PhysioMapper() throws PhysioException{
        maakConnectie();
    }
    
    private void maakConnectie() throws PhysioException{
        try{
            DriverManager.setLogWriter(new PrintWriter(System.out));
            Class.forName("org.firebirdsql.jdbc.FBDriver"); 
            con = DriverManager.getConnection(DBConst.URL, DBConst.GEBRUIKERSNAAM, DBConst.WACHTWOORD);
        }
        catch (ClassNotFoundException e){
            throw new PhysioException("Driver niet geladen");
        }
        catch (SQLException e){
            throw new PhysioException("Verbinding maken is mislukt");
        }
        
    }
    
}
