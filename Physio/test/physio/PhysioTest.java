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
public class PhysioTest {
    
    private Physio p;
    static String testRiziv= "1000010";
    static String testVoornaam = "Mickey";
    static String testAchternaam = "Mouse";
    static String testEmail = "mickey@familieMouse.wd";    
    
    public PhysioTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        p = new Physio(testAchternaam, testVoornaam, testRiziv, testEmail);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getNaam method, of class Physio.
     */
    @Test
    public void testGetNaam() {
        System.out.println("Physio::getNaam");
        Physio instance = p;
        String expResult = testAchternaam;
        String result = instance.getNaam();
        assertEquals(expResult, result);
    }

    /**
     * Test of getVoornaam method, of class Physio.
     */
    @Test
    public void testGetVoornaam() {
        System.out.println("Physio::getVoornaam");
        Physio instance = p;
        String expResult = testVoornaam;
        String result = instance.getVoornaam();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRiziv method, of class Physio.
     */
    @Test
    public void testGetRiziv() {
        System.out.println("Physio::getRiziv");
        Physio instance = p;
        String expResult = testRiziv;
        String result = instance.getRiziv();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEmail method, of class Physio.
     */
    @Test
    public void testGetEmail() {
        System.out.println("Physio::getEmail");
        Physio instance = p;
        String expResult = testEmail;
        String result = instance.getEmail();
        assertEquals(expResult, result);
    }
    
}
