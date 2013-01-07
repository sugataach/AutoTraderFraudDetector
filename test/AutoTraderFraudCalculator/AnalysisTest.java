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
public class AnalysisTest {

    /**
     * Provides test methods of analysis for calculating the risk of a listing 
     * on <code>autotrader.ca</code> of being a scam or not. 
     * 
     * <p> @TODO Exceptions
     */
    public AnalysisTest() {
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
     * Test initCalculations method of class Analysis when carAds is null.
     * 
     */
    @Test
    public void testInitCalculationsNull() {
        System.out.println("Test case for initCalculations when carAds is "
                + "null");
        CarListings carAds = null;
        Analysis instance = new Analysis();
        try {
            instance.initCalculations(carAds);
        } catch (NullPointerException npe) {
            return;
        }
        fail("Expected NullPointerException");
    }

    /**
     * Test initCalculations method of class Analysis when carAds is an empty
     * list.
     * 
     */
    @Test
    public void testInitCalculationsEmptyCarListings() {
        System.out.println("Test case for initCalculations when carAds is "
                + "empty");
        CarListings carAds = new CarListings();
        Analysis instance = new Analysis();
        try {
            instance.initCalculations(carAds);
        } catch (NullPointerException npe) {
            return;
        }
    }

    /**
     * Test of getTotalRisk method of class Analysis when carAds is null.
     */
    @Test
    public void testGetTotalRiskNull() {
        System.out.println("Test case for getTotalRisk");
        CarListings carAds = null;
        Analysis instance = new Analysis();
        try {
            instance.getTotalRisk(carAds);
        } catch (NullPointerException npe) {
            return;
        }
        fail("Expected NullPointerException");
    }

    /**
     * Test of getTotalRisk method of class Analysis when carAds is null.
     */
    @Test
    public void testGetTotalRiskEmptyCarListings() {
        System.out.println("Test case for getTotalRisk");
        CarListings carAds = new CarListings();
        Analysis instance = new Analysis();
        try {
            instance.getTotalRisk(carAds);
        } catch (NullPointerException npe) {
            return;
        }
    }

    /**
     * Test of dummyRiskCalculate method, of class Analysis.
     */
    @Test
    public void testDummyRiskCalculate() {
        System.out.println("Test case for dummyRiskCalculate");
        String price = "";
        String mileage = "";
        String phone = "";
        String listingType = "";
        int numListings = 0;
        Analysis instance = new Analysis();
        try {
            instance.dummyRiskCalculate(price, mileage, phone, listingType,
                    numListings);
        } catch (NumberFormatException nfe) {
            return;
        }
        fail("Expected NumberFormatException");
    }

    /**
     * Test of getMinPrice method, of class Analysis.
     */
    @Test
    public void testGetMinPrice() {
        System.out.println("Test case for getMinPrice");
        Analysis instance = new Analysis();
        int expResult = 0;
        int result = instance.getMinPrice();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMinPrice method of class Analysis for a specific minPrice.
     */
    @Test
    public void testGetMinPriceSpecificValue() {
        System.out.println("Test case for getMinPrice for a specific minPrice");
        Analysis instance = new Analysis();
        int expResult = 2;
        instance.setMinPrice(expResult);
        int result = instance.getMinPrice();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAvgPrice method, of class Analysis.
     */
    @Test
    public void testGetAvgPrice() {
        System.out.println("Test case for getAvgPrice");
        Analysis instance = new Analysis();
        int expResult = 0;
        int result = instance.getAvgPrice();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAvgPrice method of class Analysis for a specific avgPrice.
     */
    @Test
    public void testGetAvgPriceSpecificValue() {
        System.out.println("Test case for getAvgPrice for a specific avgPrice");
        Analysis instance = new Analysis();
        int expResult = 2;
        instance.setAvgPrice(expResult);
        int result = instance.getAvgPrice();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMaxPrice method, of class Analysis.
     */
    @Test
    public void testGetMaxPrice() {
        System.out.println("Test case for getMaxPrice");
        Analysis instance = new Analysis();
        int expResult = 0;
        int result = instance.getMaxPrice();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMaxPrice method of class Analysis for a specific maxPrice.
     */
    @Test
    public void testGetMaxPriceSpecificValue() {
        System.out.println("Test case for getMaxPrice for a specific maxPrice");
        Analysis instance = new Analysis();
        int expResult = 2;
        instance.setMaxPrice(expResult);
        int result = instance.getMaxPrice();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMinMileage method, of class Analysis.
     */
    @Test
    public void testGetMinMileage() {
        System.out.println("Test case for getMinMileage");
        Analysis instance = new Analysis();
        int expResult = 0;
        int result = instance.getMinMileage();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMinMileage method of class Analysis for a specific minMileage.
     */
    @Test
    public void testGetMinMileageSpecificValue() {
        System.out.println("Test case for getMinMileage for a specific "
                + "minMileage");
        Analysis instance = new Analysis();
        int expResult = 20000;
        instance.setMinMileage(expResult);
        int result = instance.getMinMileage();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAvgMileage method, of class Analysis.
     */
    @Test
    public void testGetAvgMileage() {
        System.out.println("Test case for getAvgMileage");
        Analysis instance = new Analysis();
        int expResult = 0;
        int result = instance.getAvgMileage();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAvgMileage method of class Analysis for a specific avgMileage.
     */
    @Test
    public void testGetAvgMileageSpecificValue() {
        System.out.println("Test case for getAvgMileage for a specific "
                + "avgMileage");
        Analysis instance = new Analysis();
        int expResult = 20000;
        instance.setAvgMileage(expResult);
        int result = instance.getAvgMileage();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMaxMileage method, of class Analysis.
     */
    @Test
    public void testGetMaxMileage() {
        System.out.println("Test case for getMaxMileage");
        Analysis instance = new Analysis();
        int expResult = 0;
        int result = instance.getMaxMileage();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMaxMileage method of class Analysis for a specific maxMileage.
     */
    @Test
    public void testGetMaxMileageSpecificValue() {
        System.out.println("Test case for getMaxMileage for a specific "
                + "maxMileage");
        Analysis instance = new Analysis();
        int expResult = 20000;
        instance.setMaxMileage(expResult);
        int result = instance.getMaxMileage();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMinPrice method, of class Analysis.
     */
    @Test
    public void testSetMinPrice() {
        System.out.println("Test case for setMinPrice");
        int minPriced = 0;
        Analysis instance = new Analysis();
        instance.setMinPrice(minPriced);
    }

    /**
     * Test of setAvgPrice method, of class Analysis.
     */
    @Test
    public void testSetAvgPrice() {
        System.out.println("Test case for setAvgPrice");
        int avgPriced = 0;
        Analysis instance = new Analysis();
        instance.setAvgPrice(avgPriced);
    }

    /**
     * Test of setMaxPrice method, of class Analysis.
     */
    @Test
    public void testSetMaxPrice() {
        System.out.println("Test case for setMaxPrice");
        int maxPriced = 0;
        Analysis instance = new Analysis();
        instance.setMaxPrice(maxPriced);
    }

    /**
     * Test of setMinMileage method, of class Analysis.
     */
    @Test
    public void testSetMinMileage() {
        System.out.println("Test case for setMinMileage");
        int minMileaged = 0;
        Analysis instance = new Analysis();
        instance.setMinMileage(minMileaged);
    }

    /**
     * Test of setAvgMileage method, of class Analysis.
     */
    @Test
    public void testSetAvgMileage() {
        System.out.println("Test case for setAvgMileage");
        int avgMileaged = 0;
        Analysis instance = new Analysis();
        instance.setAvgMileage(avgMileaged);
    }

    /**
     * Test of setMaxMileage method, of class Analysis.
     */
    @Test
    public void testSetMaxMileage() {
        System.out.println("Test case for setMaxMileage");
        int maxMileaged = 0;
        Analysis instance = new Analysis();
        instance.setMaxMileage(maxMileaged);
    }
}
