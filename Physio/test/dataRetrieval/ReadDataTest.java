/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dataRetrieval;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import datainterface.*;
import java.util.ArrayList;
import physio.Physio;
import physio.Patient;

/**
 *
 * @author Joris
 */
public class ReadDataTest {
    public ReadDataTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        String testDB = System.getProperty("user.dir") + "\\test\\testData\\TestPhysioDB.fdb";
        DataInterface.setPreferredDB(testDB);
    }    
    
    /**
     * Test if all Physios can be read from the database.
     */
    @Test
    public void testReadAllPhysios() {
        System.out.println("ReadDataTest::readAllPhysios");
        ArrayList<Physio> physios = DataInterface.getInstance().readAllPhysios();
        assertEquals(2, physios.size());
        assertEquals("Lieven", physios.get(0).getVoornaam());
        assertEquals("Lieselotte", physios.get(1).getVoornaam());
    }    
    
    /**
     * Test if all Patients can be read from the database.
     */
    @Test
    public void testReadAllPatients() {
        System.out.println("ReadDataTest::readAllPatients");
        ArrayList<Patient> patients = DataInterface.getInstance().readAllPatients();
        assertEquals(4, patients.size());
        assertEquals("Duck", patients.get(0).getAchternaam());
        assertEquals("Daffy", patients.get(0).getVoornaam());
        assertEquals("Duck", patients.get(1).getAchternaam());
        assertEquals("Daisy", patients.get(1).getVoornaam());        
    }        
    
    
}
