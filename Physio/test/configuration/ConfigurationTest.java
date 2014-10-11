/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package configuration;

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
public class ConfigurationTest {
    
    public ConfigurationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getDatabase method, of class Configuration.
     */
    @Test
    public void testGetDatabase() {
        System.out.println("getDatabase");
        Configuration instance = new Configuration();
        String expResult = System.getProperty("user.dir") + "\\" + "HomePhysioDB.fdb";
        String result = instance.getDatabase();
        assertEquals(expResult, result);
    }
    
}
