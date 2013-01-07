/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AutoTraderFraudCalculator;

import java.util.ArrayList;
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
public class AutoTraderFraudCalculatorTest {

    /**
     * Provides test methods for class AutoTraderFraudCalculator.
     */
    public AutoTraderFraudCalculatorTest() {
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
     * Test of setupPageReader method, of class AutoTraderFraudCalculator.
     */
    @Test
    public void testSetupPageReader() {
        System.out.println("Test case for setupPageReader");
        String url = "www.autotrader.ca";
        AutoTraderFraudCalculator instance = new AutoTraderFraudCalculator();
        instance.setupPageReader(url);
    }
    
    /**
     * Test of setupPageReader method, of class AutoTraderFraudCalculator.
     */
    @Test
    public void testSetupPageReaderEmpty() {
        System.out.println("Test case for setupPageReaderEmpty");
        String url = "";
        AutoTraderFraudCalculator instance = new AutoTraderFraudCalculator();
        instance.setupPageReader(url);
    }
    

    /**
     * Test of getCarMakes method, of class AutoTraderFraudCalculator.
     */
    @Test
    public void testGetCarMakes() {
        System.out.println("Test case for getCarMakes");
        AutoTraderFraudCalculator instance = new AutoTraderFraudCalculator();
        String[] carMakes = {"Acura", "Audi", "BMW", "Buick", "CADILLAC", 
            "Chevrolet", "Chrysler", "Dodge", "Ford", "GMC", "Honda", "Hummer", 
            "Hyundai", "Infiniti", "Jaguar", "Jeep", "Kia", "Land Rover", 
            "Lexus", "Lincoln", "Mazda", "Mercedes-Benz", "MINI", "Mitsubishi", 
            "Nissan", "Pontiac", "Porsche", "Saab", "Saturn", "smart", "Subaru", 
            "Suzuki", "Toyota", "Volkswagen", "Volvo"};
        ArrayList expResult = new ArrayList();
        for (int i = 0; i < carMakes.length; i++) {
            expResult.add(carMakes[i]);
        }
        ArrayList result = instance.getCarMakes();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCarModels method, of class AutoTraderFraudCalculator.
     */
    @Test
    public void testGetCarModels() {
        System.out.println("Test case for getCarModels");
        String carMake = "Acura";
        AutoTraderFraudCalculator instance = null;   
        try {
            instance.getCarModels(carMake);
        } catch (NullPointerException npe) {
            return;
        }
        fail("Expected NullPointerException");
    }

    /**
     * Test of downloadCarListings method, of class AutoTraderFraudCalculator.
     */
    @Test
    public void testDownloadCarListings() {
        System.out.println("Test case for downloadCarListings");
        String carMake = "";
        String carModel = "";
        AutoTraderFraudCalculator instance = new AutoTraderFraudCalculator();
        try {
            instance.downloadCarListings(carMake, carModel);
        } catch (Exception e) {
            return;
        } 
    }
    
    /**
     * Test of getCarSpecifications method, of class AutoTraderFraudCalculator.
     * 
     * @throws Exception 
     */
    @Test
    public void testGetCarSpecifications() throws Exception {
        System.out.println("Test case for getCarSpecifications");
        CarListings carAds = null;
        String carMake = "";
        String carModel = "";
        AutoTraderFraudCalculator instance = new AutoTraderFraudCalculator();
        try {
            instance.getCarSpecifications(carAds, carMake, carModel);
        } catch (NullPointerException npe) {
            return;
        }

        fail("Expected NullPointerException");
    }

    /**
     * Test of writeToXml method, of class AutoTraderFraudCalculator.
     * 
     * @throws Exception 
     */
    @Test
    public void testWriteToXml() throws Exception {
        System.out.println("Test case for writeToXml");
        CarListings carAds = null;
        String carMake = "";
        String carModel = "";
        AutoTraderFraudCalculator instance = new AutoTraderFraudCalculator();
        try {
            instance.writeToXml(carAds, carMake, carModel);
        } catch (NullPointerException npe) {
            return;
        }
        fail("Expected NullPointerException");
    }

    /**
     * Test of getMinPrice method, of class AutoTraderFraudCalculator.
     */
    @Test
    public void testGetMinPrice() {
        System.out.println("Test case for getMinPrice");
        AutoTraderFraudCalculator instance = new AutoTraderFraudCalculator();
        int expResult = 0;
        int result = instance.getMinPrice();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getMinPrice method, of class AutoTraderFraudCalculator.
     */
    @Test
    public void testGetMinPriceSpecificValue() {
        System.out.println("Test case for getMinPrice");
        AutoTraderFraudCalculator instance = new AutoTraderFraudCalculator();
        int expResult = 20000;
        instance.setMinPrice(expResult);
        int result = instance.getMinPrice();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAvgPrice method, of class AutoTraderFraudCalculator.
     */
    @Test
    public void testGetAvgPrice() {
        System.out.println("Test case for getAvgPrice");
        AutoTraderFraudCalculator instance = new AutoTraderFraudCalculator();
        int expResult = 0;
        int result = instance.getAvgPrice();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getAvgPrice method, of class AutoTraderFraudCalculator.
     */
    @Test
    public void testGetAvgPriceSpecificValue() {
        System.out.println("Test case for getAvgPrice");
        AutoTraderFraudCalculator instance = new AutoTraderFraudCalculator();
        int expResult = 20000;
        instance.setAvgPrice(expResult);
        int result = instance.getAvgPrice();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMaxPrice method, of class AutoTraderFraudCalculator.
     */
    @Test
    public void testGetMaxPrice() {
        System.out.println("Test case for getMaxPrice");
        AutoTraderFraudCalculator instance = new AutoTraderFraudCalculator();
        int expResult = 0;
        int result = instance.getMaxPrice();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getMaxPrice method, of class AutoTraderFraudCalculator.
     */
    @Test
    public void testGetMaxPriceSpecificValue() {
        System.out.println("Test case for getMaxPrice");
        AutoTraderFraudCalculator instance = new AutoTraderFraudCalculator();
        int expResult = 20000;
        instance.setMaxPrice(expResult);
        int result = instance.getMaxPrice();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMinMileage method, of class AutoTraderFraudCalculator.
     */
    @Test
    public void testGetMinMileage() {
        System.out.println("Test case for getMinMileage");
        AutoTraderFraudCalculator instance = new AutoTraderFraudCalculator();
        int expResult = 0;
        int result = instance.getMinMileage();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getMinMileage method, of class AutoTraderFraudCalculator.
     */
    @Test
    public void testGetMinMileageSpecificValue() {
        System.out.println("Test case for getMinMileage");
        AutoTraderFraudCalculator instance = new AutoTraderFraudCalculator();
        int expResult = 20000;
        instance.setMinMileage(expResult);
        int result = instance.getMinMileage();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAvgMileage method, of class AutoTraderFraudCalculator.
     */
    @Test
    public void testGetAvgMileage() {
        System.out.println("Test case for getAvgMileage");
        AutoTraderFraudCalculator instance = new AutoTraderFraudCalculator();
        int expResult = 0;
        int result = instance.getAvgMileage();
        assertEquals(expResult, result);
    }
    
    
    /**
     * Test of getAvgMileage method, of class AutoTraderFraudCalculator.
     */
    @Test
    public void testGetAvgMileageSpecificValue() {
        System.out.println("Test case for getAvgMileage");
        AutoTraderFraudCalculator instance = new AutoTraderFraudCalculator();
        int expResult = 20000;
        instance.setAvgMileage(expResult);
        int result = instance.getAvgMileage();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMaxMileage method, of class AutoTraderFraudCalculator.
     */
    @Test
    public void testGetMaxMileage() {
        System.out.println("Test case for getMaxMileage");
        AutoTraderFraudCalculator instance = new AutoTraderFraudCalculator();
        int expResult = 0;
        int result = instance.getMaxMileage();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getMaxMileage method, of class AutoTraderFraudCalculator.
     */
    @Test
    public void testGetMaxMileageSpecificValue() {
        System.out.println("Test case for getMaxMileage");
        AutoTraderFraudCalculator instance = new AutoTraderFraudCalculator();
        int expResult = 20000;
        instance.setMaxMileage(expResult);
        int result = instance.getMaxMileage();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMinPrice method, of class AutoTraderFraudCalculator.
     */
    @Test
    public void testSetMinPrice() {
        System.out.println("Test case for setMinPrice");
        int minPriced = 0;
        AutoTraderFraudCalculator instance = new AutoTraderFraudCalculator();
        instance.setMinPrice(minPriced);
    }

    /**
     * Test of setAvgPrice method, of class AutoTraderFraudCalculator.
     */
    @Test
    public void testSetAvgPrice() {
        System.out.println("Test case for setAvgPrice");
        int avgPriced = 0;
        AutoTraderFraudCalculator instance = new AutoTraderFraudCalculator();
        instance.setAvgPrice(avgPriced);
    }

    /**
     * Test of setMaxPrice method, of class AutoTraderFraudCalculator.
     */
    @Test
    public void testSetMaxPrice() {
        System.out.println("Test case for setMaxPrice");
        int maxPriced = 0;
        AutoTraderFraudCalculator instance = new AutoTraderFraudCalculator();
        instance.setMaxPrice(maxPriced);
    }

    /**
     * Test of setMinMileage method, of class AutoTraderFraudCalculator.
     */
    @Test
    public void testSetMinMileage() {
        System.out.println("Test case for setMinMileage");
        int minMileaged = 0;
        AutoTraderFraudCalculator instance = new AutoTraderFraudCalculator();
        instance.setMinMileage(minMileaged);
    }

    /**
     * Test of setAvgMileage method, of class AutoTraderFraudCalculator.
     */
    @Test
    public void testSetAvgMileage() {
        System.out.println("Test case for setAvgMileage");
        int avgMileaged = 0;
        AutoTraderFraudCalculator instance = new AutoTraderFraudCalculator();
        instance.setAvgMileage(avgMileaged);
    }

    /**
     * Test of setMaxMileage method, of class AutoTraderFraudCalculator.
     */
    @Test
    public void testSetMaxMileage() {
        System.out.println("Test case for setMaxMileage");
        int maxMileaged = 0;
        AutoTraderFraudCalculator instance = new AutoTraderFraudCalculator();
        instance.setMaxMileage(maxMileaged);
    }

    /**
     * Test of getNumListings method, of class AutoTraderFraudCalculator.
     */
    @Test
    public void testGetNumListings() {
        System.out.println("Test case for getNumListings");
        String model = "";
        AutoTraderFraudCalculator instance = new AutoTraderFraudCalculator();
        try {
            instance.getNumListings(model);
        } catch (NullPointerException npe) {
            return;
        }
    }

    /**
     * Test of getCurrentLoadingValue method, of class AutoTraderFraudCalculator.
     */
    @Test
    public void testGetCurrentLoadingValue() {
        System.out.println("Test case for getCurrentLoadingValue");
        AutoTraderFraudCalculator instance = new AutoTraderFraudCalculator();
        double expResult = 0.0;
        double result = instance.getCurrentLoadingValue();
        assertEquals(expResult, result, 0.0);
    }
}
