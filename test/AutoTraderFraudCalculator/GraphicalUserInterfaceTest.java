/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AutoTraderFraudCalculator;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kevin
 */
public class GraphicalUserInterfaceTest {
    
    /**
     * Provides test methods for class GraphicalUserInterface.
     */
    public GraphicalUserInterfaceTest() {
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
     * Test of setupInitializeObjects method, of class GraphicalUserInterface.
     */
    @Test
    public void testSetupInitializeObjects() {
        System.out.println("setupInitializeObjects");
        GraphicalUserInterface.setupInitializeObjects();
    }
    
    /**
     * Test of setupComboBoxCarMakes method, of class GraphicalUserInterface.
     */
    @Test
    public void testSetupComboBoxCarMakes() {
        System.out.println("Test case for setupComboBoxCarMakes");
        GraphicalUserInterface.setupComboBoxCarMakes();
    }

    /**
     * Test of setupButtonCarModels method, of class GraphicalUserInterface.
     */
    @Test
    public void testSetupButtonCarModels() {
        System.out.println("Test case for setupButtonCarModels");
        try {
            GraphicalUserInterface.setupButtonCarModels();
        } catch (NullPointerException npe) {
            return;
        }
        fail("Expected NullPointerException");
    }

    /**
     * Test of setupComboBoxCarModels method, of class GraphicalUserInterface.
     */
    @Test
    public void testSetupComboBoxCarModels() {
        System.out.println("Test case for setupComboBoxCarModels");
        try {
            GraphicalUserInterface.setupComboBoxCarModels();
        } catch (NullPointerException npe) {
            return;
        }
        fail("Expected NullPointerException");
    }

    /**
     * Test of setupButtonCarListings method, of class GraphicalUserInterface.
     */
    @Test
    public void testSetupButtonCarListings() {
        System.out.println("Test case for setupButtonCarListings");
        try {
            GraphicalUserInterface.setupButtonCarListings();
        } catch (NullPointerException npe) {
            return;
        }
        fail("Expected NullPointerException");
    }

    /**
     * Test of setupButtonReset method, of class GraphicalUserInterface.
     */
    @Test
    public void testSetupButtonReset() {
        System.out.println("Test case for setupButtonReset");
        try {
            GraphicalUserInterface.setupButtonReset();
        } catch (NullPointerException npe) {
            return;
        }
        fail("Expected NullPointerException");
    }

    /**
     * Test of setupProgressBar method, of class GraphicalUserInterface.
     */
    @Test
    public void testSetupProgressBar() {
        System.out.println("Test case for setupProgressBar");
        try {
            GraphicalUserInterface.setupProgressBar();
        } catch (NullPointerException npe) {
            return;
        }
        fail("Expected NullPointerException");
    }

    /**
     * Test of setupDummyPanelObjects method, of class GraphicalUserInterface.
     */
    @Test
    public void testSetupDummyPanelObjects() {
        System.out.println("Test case for setupDummyPanelObjects");
        try {
            GraphicalUserInterface.setupDummyPanelObjects();
        } catch (NullPointerException npe) {
            return;
        }
        fail("Expected NullPointerException");
    }

    /**
     * Test of setupVehicleListSpecifications method, of class 
     * GraphicalUserInterface.
     */
    @Test
    public void testSetupVehicleListSpecifications() {
        System.out.println("Test case for setupVehicleListSpecifications");
        try {
            GraphicalUserInterface.setupVehicleListSpecifications();
        } catch (NullPointerException npe) {
            return;
        }
        fail("Expected NullPointerException");
    }

    /**
     * Test of setupLinkToWebButton method, of class GraphicalUserInterface.
     */
    @Test
    public void testSetupLinkToWebButton() {
        System.out.println("Test case for setupLinkToWebButton");
        try {
            GraphicalUserInterface.setupLinkToWebButton();
        } catch (NullPointerException npe) {
            return;
        }
        fail("Expected NullPointerException");
    }

    /**
     * Test of setupRiskExplanationButton method, of class 
     * GraphicalUserInterface.
     */
    @Test
    public void testSetupRiskExplanationButton() {
        System.out.println("Test case for setupRiskExplanationButton");
        try {
            GraphicalUserInterface.setupRiskExplanationButton();
        } catch (NullPointerException npe) {
            return;
        }
        fail("Expected NullPointerException");
    }

    /**
     * Test of setupRiskLists method, of class GraphicalUserInterface.
     */
    @Test
    public void testSetupRiskLists() {
        System.out.println("Test case for setupRiskLists");
        try {
            GraphicalUserInterface.setupRiskLists();
        } catch (NullPointerException npe) {
            return;
        }
        fail("Expected NullPointerException");
    }

    /**
     * Test of setupRiskButtons method, of class GraphicalUserInterface.
     */
    @Test
    public void testSetupRiskButtons() {
        System.out.println("Test case for setupRiskButtons");
        try {
            GraphicalUserInterface.setupRiskButtons();
        } catch (NullPointerException npe) {
            return;
        }
        fail("Expected NullPointerException");
    }

    /**
     * Test of setupGraphUtility method, of class GraphicalUserInterface.
     */
    @Test
    public void testSetupGraphUtility() {
        System.out.println("Test case for setupGraphUtility");
        try {
            GraphicalUserInterface.setupGraphUtility();
        } catch (NullPointerException npe) {
            return;
        }
        fail("Expected NullPointerException");
    }

    /**
     * Test of setLabels method, of class GraphicalUserInterface.
     */
    @Test
    public void testSetLabels() {
        System.out.println("Test case for setLabels");
        try {
            GraphicalUserInterface.setLabels();
        } catch (NullPointerException npe) {
            return;
        }
        fail("Expected NullPointerException");
    }

    /**
     * Test of makeVisibleAndComputeValues method, of class 
     * GraphicalUserInterface.
     */
    @Test
    public void testMakeVisibleAndComputeValues() {
        System.out.println("Test case for makeVisibleAndComputeValues");
        try {
            GraphicalUserInterface.makeVisibleAndComputeValues();
        } catch (NullPointerException npe) {
            return;
        }
        fail("Expected NullPointerException");
    }

    /**
     * Test of makeInvisible method, of class GraphicalUserInterface.
     */
    @Test
    public void testMakeInvisible() {
        System.out.println("Test case for makeInvisible");
        try {
            GraphicalUserInterface.makeInvisible();
        } catch (NullPointerException npe) {
            return;
        }
        fail("Expected NullPointerException");
    }

    /**
     * Test of getCarModels method, of class GraphicalUserInterface.
     */
    @Test
    public void testGetCarModels() {
        System.out.println("Test case for getCarModels");
        GraphicalUserInterface instance = null;
        try {
            instance.getCarModels();
        } catch (NullPointerException npe) {
            return;
        }
        fail("Expected NullPointerException");
    }

    /**
     * Test of getCarListings method, of class GraphicalUserInterface.
     */
    @Test
    public void testGetCarListings() {
        System.out.println("Test case for getCarListings");
        GraphicalUserInterface instance = null;
        try {
            instance.getCarListings();
        } catch (NullPointerException npe) {
            return;
        }
        fail("Expected NullPointerException");
    }

    /**
     * Test of downloadInformation method, of class GraphicalUserInterface.
     */
    @Test
    public void testDownloadInformation() {
        System.out.println("Test case for downloadInformation");
        GraphicalUserInterface instance = null;
        try {
            instance.downloadInformation();
        } catch (NullPointerException npe) {
            return;
        }
        fail("Expected NullPointerException");
    }

    /**
     * Test of displayListings method, of class GraphicalUserInterface.
     */
    @Test
    public void testDisplayListings() {
        System.out.println("Test case for displayListings");
        GraphicalUserInterface instance = null;
        String title = "";
        try {
            GraphicalUserInterface.displayListings(title);
        } catch (NullPointerException npe) {
            return;
        }
        fail("Expected NullPointerException");
    }

    /**
     * Test of listsReset method, of class GraphicalUserInterface.
     */
    @Test
    public void testListsReset() {
        System.out.println("Test case for listsReset");
        try {
            GraphicalUserInterface.setupDummyPanelObjects();
        } catch (NullPointerException npe) {
            return;
        }
        fail("Expected NullPointerException");
    }

    /**
     * Test of linkToCarListing method, of class GraphicalUserInterface.
     */
    @Test
    public void testLinkToCarListing() {
        System.out.println("Test case for linkToCarListing");
        try {
            GraphicalUserInterface.setupDummyPanelObjects();
        } catch (NullPointerException npe) {
            return;
        }
        fail("Expected NullPointerException");
    }

    /**
     * Test of getVeryLow method, of class GraphicalUserInterface.
     */
    @Test
    public void testGetVeryLow() {
        System.out.println("Test case for getVeryLow");
        try {
            GraphicalUserInterface.setupDummyPanelObjects();
        } catch (NullPointerException npe) {
            return;
        }
        fail("Expected NullPointerException");
    }

    /**
     * Test of getLow method, of class GraphicalUserInterface.
     */
    @Test
    public void testGetLow() {
        System.out.println("Test case for getLow");
        try {
            GraphicalUserInterface.setupDummyPanelObjects();
        } catch (NullPointerException npe) {
            return;
        }
        fail("Expected NullPointerException");
    }

    /**
     * Test of getMedium method, of class GraphicalUserInterface.
     */
    @Test
    public void testGetMedium() {
        System.out.println("Test case for getMedium");
        try {
            GraphicalUserInterface.setupDummyPanelObjects();
        } catch (NullPointerException npe) {
            return;
        }
        fail("Expected NullPointerException");
    }

    /**
     * Test of getHigh method, of class GraphicalUserInterface.
     */
    @Test
    public void testGetHigh() {
        System.out.println("Test case for getHigh");
        try {
            GraphicalUserInterface.setupDummyPanelObjects();
        } catch (NullPointerException npe) {
            return;
        }
        fail("Expected NullPointerException");
    }

    /**
     * Test of getVeryHigh method, of class GraphicalUserInterface.
     */
    @Test
    public void testGetVeryHigh() {
        System.out.println("Test case for getVeryHigh");
        try {
            GraphicalUserInterface.setupDummyPanelObjects();
        } catch (NullPointerException npe) {
            return;
        }
        fail("Expected NullPointerException");
    }

    /**
     * Test of getReset method, of class GraphicalUserInterface.
     */
    @Test
    public void testGetReset() {
        System.out.println("Test case for getReset");
        try {
            GraphicalUserInterface.setupDummyPanelObjects();
        } catch (NullPointerException npe) {
            return;
        }
        fail("Expected NullPointerException");
    }

    /**
     * Test of dummyAdd method, of class GraphicalUserInterface.
     */
    @Test
    public void testDummyAdd() {
        System.out.println("Test case for dummyAdd");
        try {
            GraphicalUserInterface.setupDummyPanelObjects();
        } catch (NullPointerException npe) {
            return;
        }
        fail("Expected NullPointerException");
    }

    /**
     * Test of actionPerformed method, of class GraphicalUserInterface.
     */
    @Test
    public void testActionPerformed() {
        System.out.println("Test case for actionPerformed");
        ActionEvent e = null;
        GraphicalUserInterface instance = null;
        try {
            instance.actionPerformed(e);
        } catch (NullPointerException npe) {
            return;
        }
        fail("Expected NullPointerException");
    }

    /**
     * Test of propertyChange method, of class GraphicalUserInterface.
     */
    @Test
    public void testPropertyChange() {
        System.out.println("Test case for propertyChange");
        PropertyChangeEvent evt = null;
        GraphicalUserInterface instance = null;
        
        try {
            instance.propertyChange(evt);
        } catch (NullPointerException npe) {
            return;
        }
        fail("Expected NullPointerException");
    }


    /**
     * Test of populateListings method, of class GraphicalUserInterface.
     */
    @Test
    public void testPopulateListings() {
        System.out.println("Test case for populateListings");
        int risk = 0;
        String title = "";
        String url = "";
        GraphicalUserInterface.populateListings(risk, title, url);
    }

    /**
     * Test of updatePriceAndMileageStats method, of class 
     * GraphicalUserInterface.
     */
    @Test
    public void testUpdatePriceAndMileageStats_0args() {
        System.out.println("Test case for updatePriceAndMileageStats");
        GraphicalUserInterface.updatePriceAndMileageStats();
    }

    /**
     * Test of updatePriceAndMileageStats method, of class 
     * GraphicalUserInterface.
     */
    @Test
    public void testUpdatePriceAndMileageStats_String_String() {
        System.out.println("Test case for updatePriceAndMileageStats");
        String price = null;
        String mileage = null;       
        try {
            GraphicalUserInterface.updatePriceAndMileageStats(price, mileage);
        } catch (NullPointerException npe) {
            return;
        }      
        fail("Expected NullPointerException");
    }

    /**
     * Test of setupDummyListing method, of class GraphicalUserInterface.
     */
    @Test
    public void testSetupDummyListing() {
        System.out.println("Test case for setupDummyListing");
        try {
            GraphicalUserInterface.setupDummyListing();
        } catch (NullPointerException npe) {
            return;
        }        
        fail("Expected NullPointerException");
    }

    /**
     * Test of populateDummyListing method, of class GraphicalUserInterface.
     */
    @Test
    public void testPopulateDummyListing() {
        System.out.println("Test case for populateDummyListing");
        int risk = 0;     
        try {
            GraphicalUserInterface.populateDummyListing(risk);
        } catch (NullPointerException npe) {
            return;
        }       
        fail("Expected NullPointerException");
    }
 
    /**
     * Test of main method, of class GraphicalUserInterface.
     */
    @Test
    public void testMain() {
        System.out.println("Test case for main");
        String[] args = null;
        GraphicalUserInterface.main(args);
    }
}
