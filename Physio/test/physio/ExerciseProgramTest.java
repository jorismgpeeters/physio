/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package physio;

import java.sql.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Joris
 */
public class ExerciseProgramTest {
    
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
    
    
    public ExerciseProgramTest() {
     
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

    /**
     * Test of getPatient method, of class ExerciseProgram.
     */
    @Test
    public void testGetPatient() {
        System.out.println("ExerciseProgram::getPatient");
        ExerciseProgram instance = e;
        Patient expResult = p;
        Patient result = instance.getPatient();
        assertEquals(p.getAchternaam(), result.getAchternaam());
    }

    /**
     * Test of getVolgnummer method, of class ExerciseProgram.
     */
    @Test
    public void testGetVolgnummer() {
        System.out.println("ExerciseProgram::getVolgnummer");
        ExerciseProgram instance = e;
        int expResult = volgnummer;
        int result = instance.getVolgnummer();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDatum method, of class ExerciseProgram.
     */
    @Test
    public void testGetDatum() {
        System.out.println("ExerciseProgram::getDatum");
        ExerciseProgram instance = e;
        Date expResult = datum;
        Date result = instance.getDatum();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPhysio method, of class ExerciseProgram.
     */
    @Test
    public void testGetPhysio() {
        System.out.println("ExerciseProgram::getPhysio");
        ExerciseProgram instance = e;
        Physio expResult = ph;
        Physio result = instance.getPhysio();
        assertEquals(expResult.getVoornaam(), result.getVoornaam());
    }
    
}
