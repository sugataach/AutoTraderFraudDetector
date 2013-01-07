/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AutoTraderFraudCalculator;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 
 */
public class CarListingsTest {
    
    /**
     * Provides test methods for class CarListings.
     */
    public CarListingsTest() {
    }

    /**
     * Initialize the test method for all the methods within the class Analysis.
     * 
     * @throws Exception 
     */
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    /**
     * Finish up the test method for all methods within the class Analysis.
     * 
     * @throws Exception 
     */
    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    /**
     * Initialize methods.
     */
    @Before
    public void setUp() {
    }
    
    /**
     * Finish up methods.
     */
    @After
    public void tearDown() {
    }

    /**
     * Test of pushAdvertisement method, of class CarListings.
     */
    @Test
    public void testPushAdvertisement() {
        System.out.println("Test case for pushAdvertisement");
        Listing carAdvertisement = null;
        CarListings instance = new CarListings();
        instance.pushAdvertisement(carAdvertisement);
    }

    /**
     * Test of getAdvertisement method, of class CarListings.
     */
    @Test
    public void testGetAdvertisement() {
        System.out.println("Test case for getAdvertisement");
        int index = 0;
        CarListings instance = null;     
        try {
            instance.getAdvertisement(index);
        } catch (NullPointerException npe) {
            return;
        }
        fail("Expected NullPointerException");
    }

    /**
     * Test of getSize method, of class CarListings.
     */
    @Test
    public void testGetSize() {
        System.out.println("Test case for getSize");
        CarListings instance = new CarListings();
        int expResult = 0;
        int result = instance.getSize();
        assertEquals(expResult, result);
    }
}
