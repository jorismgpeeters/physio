/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package physio;

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
public class PatientTest {
    
    private Patient p;
    static String testNummer = "1000010";
    static String testVoornaam = "Mickey";
    static String testAchternaam = "Mouse";
    static String testEmail = "mickey@familieMouse.wd";
    
    public PatientTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        p = new Patient(testNummer, testVoornaam, testAchternaam, testEmail);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getNummer method, of class Patient.
     */
    @Test
    public void testGetNummer() {
        System.out.println("Patient::getNummer");
        Patient instance = p;
        String expResult = testNummer;
        String result = instance.getNummer();
        assertEquals(expResult, result);
    }

    /**
     * Test of getVoornaam method, of class Patient.
     */
    @Test
    public void testGetVoornaam() {
        System.out.println("Patient::getVoornaam");
        Patient instance =  p;
        String expResult = testVoornaam;
        String result = instance.getVoornaam();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAchternaam method, of class Patient.
     */
    @Test
    public void testGetAchternaam() {
        System.out.println("Patient::getAchternaam");
        Patient instance = p;
        String expResult = testAchternaam;
        String result = instance.getAchternaam();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEmailadres method, of class Patient.
     */
    @Test
    public void testGetEmailadres() {
        System.out.println("Patient::getEmailadres");
        Patient instance = p;
        String expResult = testEmail;
        String result = instance.getEmailadres();
        assertEquals(expResult, result);
    }
    
}
