/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AutoTraderFraudCalculator;

import java.util.TreeMap;
import java.util.Map;
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
public class ListingTest {
    
    /**
     * Provides test methods for class Listing.
     */
    public ListingTest() {
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
     * Test of setTitle method, of class Listing.
     */
    @Test
    public void testSetTitle() {
        System.out.println("Test case for setTitle");
        String adTitle = "";
        Listing instance = new Listing();
        instance.setTitle(adTitle);
        String expResult = adTitle;
        String result = instance.getTitle();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTitle method, of class Listing.
     */
    @Test
    public void testSetTitleCarMake() {
        System.out.println("Test case for setTitle for a specific CarMake");
        String adTitle = "Acura";
        Listing instance = new Listing();
        instance.setTitle(adTitle);
        String expResult = adTitle;
        String result = instance.getTitle();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of setUrl method, of class Listing.
     */
    @Test
    public void testSetUrl() {
        System.out.println("Test case for setUrl");
        String adUrl = "";
        Listing instance = new Listing();
        instance.setUrl(adUrl);
        String expResult = adUrl;
        String result = instance.getUrl();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of setUrl method, of class Listing.
     */
    @Test
    public void testSetUrlWebPage() {
        System.out.println("Second test case for setUrl");
        String adUrl = "www.autotrader.ca";
        Listing instance = new Listing();
        instance.setUrl(adUrl);
        String expResult = adUrl;
        String result = instance.getUrl();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSpecification method, of class Listing.
     */
    @Test
    public void testSetSpecification() {
        System.out.println("Test case for setSpecification");
        String adSpecType = "";
        String adSpecValue = "";
        Listing instance = new Listing();
        instance.setSpecification(adSpecType, adSpecValue);
        Map<String, String> expResult =
                new TreeMap<String, String>(instance.getSpecifications());

        Map<String, String> result = new TreeMap<String, String>();
        result.put("", "");
        assertEquals(expResult, result);
    }

    /**
     * Test of getTitle method, of class Listing.
     */
    @Test
    public void testGetTitle() {
        System.out.println("Test case for getTitle");
        Listing instance = new Listing();
        String expResult = "";
        String result = instance.getTitle();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getTitle method, of class Listing.
     */
    @Test
    public void testGetTitleMake() {
        System.out.println("Second test case for getTitle");
        Listing instance = new Listing();
        instance.setTitle("Acura");
        String expResult = "Acura";
        String result = instance.getTitle();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getUrl method, of class Listing.
     */
    @Test
    public void testGetUrl() {
        System.out.println("Test case for getUrl");
        Listing instance = new Listing();
        instance.setUrl("");
        String expResult = "";
        String result = instance.getUrl();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getUrl method, of class Listing.
     */
    @Test
    public void testGetUrlWeb() {
        System.out.println("Third test case for getUrl");
        Listing instance = new Listing();
        instance.setUrl("www.autotrader.ca");
        String expResult = "www.autotrader.ca";
        String result = instance.getUrl();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSpecs method, of class Listing.
     */
    @Test
    public void testGetSpecifications() {
        System.out.println("Test case for getSpecifications");
        Listing instance = new Listing();
        instance.setSpecification("", "");
        Map expResult = instance.getSpecifications();
        Map result = instance.getSpecifications();
        assertEquals(expResult, result);
    }


    /**
     * Test of getSpecificationValue method, of class Listing.
     */
    @Test
    public void testGetSpecificationValue() {
        System.out.println("Test case for getSpecificationValue");
        String adSpecType = "";
        Listing instance = new Listing();
        String expResult = null;
        String result = instance.getSpecificationValue(adSpecType);
        assertEquals(expResult, result);
    }

    /**
     * Test of containsSpecificationType method, of class Listing.
     */
    @Test
    public void testContainsSpecificationType() {
        System.out.println("Test case for containsSpecificationType");
        String adSpecType = "";
        Listing instance = new Listing();
        boolean expResult = false;
        boolean result = instance.containsSpecificationType(adSpecType);
        assertEquals(expResult, result);
    }

    /**
     * Test of getSpecificationSize method, of class Listing.
     */
    @Test
    public void testGetSpecificationSize() {
        System.out.println("Test case for getSpecificationSize");
        Listing instance = new Listing();
        int expResult = 0;
        int result = instance.getSpecificationSize();
        assertEquals(expResult, result);
    }

    /**
     * Test of toArray method, of class Listing.
     */
    @Test
    public void testToArray() {
        System.out.println("Test case for toArray");
        Listing instance = new Listing();
        Map<String, String> specifications = new TreeMap<String, String>();
        Object[] expResult = specifications.entrySet().toArray();
        Object[] result = instance.toArray();
        assertEquals(expResult, result);
    }
}
