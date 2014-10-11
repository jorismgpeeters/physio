/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package configuration;
import java.io.*;

/**
 *
 * @author Joris
 */
public class Configuration {
    private String configFile = "physio.cfg";
    
    private String dataBaseKey = "dataBase";
    
    public String getDatabase() {
        return System.getProperty("user.dir") + "\\" + getArgument(dataBaseKey);        
    }
    
    private String getArgument(String key)
    {
        try {
            File fin = new File(System.getProperty("user.dir") + "\\" + configFile);

            // Construct BufferedReader from FileReader
            BufferedReader br = new BufferedReader(new FileReader(fin));
            
            String line = null;
            while ((line = br.readLine()) != null) {
                if (line.startsWith(key))
                {
                    String argument = line.substring(line.indexOf(key)+key.length() + 1);
                    return argument.trim();
                }
            }

            br.close();       

            return null;
        }
        catch (IOException e){
            return null;
        }
    }
    
    
}
