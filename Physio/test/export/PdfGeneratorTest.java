/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package export;

import java.sql.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import physio.*;
import export.*;

/**
 *
 * @author Joris
 */
public class PdfGeneratorTest {
    private Physio ph;
    static String testPhRiziv= "1000010";
    static String testPhVoornaam = "Mickey";
    static String testPhAchternaam = "Mouse";
    static String testPhEmail = "mickey@familieMouse.wd";  
    
    private Patient p;
    static String testPNummer = "1000011";
    static String testPVoornaam = "Geest";
    static String testPAchternaam = "InDeLamp";
    static String testPEmail = "geest@lampen.wd";  
    
    private ExerciseProgram e;
    private int volgnummer = 20;
    String datumInString = "1987-08-17";
    private Date datum = null;  
    
    private String testFileName = "test\\testData\\TestGeneration.pdf";
    
    public PdfGeneratorTest() {
     
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        p = new Patient(testPNummer, testPVoornaam, testPAchternaam, testPEmail);
        ph = new Physio(testPhAchternaam, testPhVoornaam, testPhRiziv, testPhEmail);
        datum = java.sql.Date.valueOf(datumInString);  
        
        e = new ExerciseProgram(p, volgnummer, datum, ph);
    }
    
    @After
    public void tearDown() {
    }  
    
    @Test
    public void testGenerate() {
        System.out.println("PdfGeneratorTest::testGenerate");   
        PdfGenerator generator = new PdfGenerator(e, testFileName);
        generator.generate();
    }    
}
