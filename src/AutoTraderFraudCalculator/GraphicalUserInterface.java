package AutoTraderFraudCalculator;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;
import java.util.*;
import java.io.*;
import java.net.*;

/**
 * A graphical user interface for the Auto Trader Fraud Calculator application.
 * <p>
 * Allows a user to choose a make and model and list all the car advertisements
 * of that model. The GUI organizes the advertisements into:
 *     <li> Very Low Risk </li>
 *     <li> Low Risk </li>
 *     <li> Medium Risk </li>
 *     <li> High Risk </li>
 *     <li> Very High Risk </li>
 * <p>
 * Also, the user is able to select an advertisement and have a quick view of
 * the car specifications (including the risk value as an integer) and open
 * the original advertisement from <code>autotrader.ca</code> in the default
 * desktop browser.
 */
public class GraphicalUserInterface extends JApplet 
    implements ActionListener, PropertyChangeListener {
    
    /**
     * An instance of the fraud calculator.
     */
    private static AutoTraderFraudCalculator autoTrader;
    
    /**
     * An instance of the analysis class which is used to analyze the car
     * advertisements.
     */
    private static Analysis analyzer;
    
    /**
     * A container to retrieve specifications of the advertisements. It uses a 
     * URL as a unique identifier.
     */
    private static Map<String, ArrayList<String>> urlMap;
    
    /**
     * Contains the price and mileage of all the car advertisements.
     */
    private static List<Integer> priceAndMileage;

    /**
     * Contains all the different GUI components.
     */
    private static JFrame frame;
    
    /**
     * Contains the components that implement the usage of dummy car
     * advertisements.
     */
    private static JPanel dummyPanel;
    
    /**
     * Dummy title field in the dummy panel.
     */
    private static JTextField dummyTitle;
    
    /**
     * Dummy price field in the dummy panel.
     */
    private static JTextField dummyPrice;
    
    /**
     * Dummy mileage field in the dummy panel.
     */
    private static JTextField dummyMileage;
    
    /**
     * Contains the graph representation. UI needs to be updated for this to
     * be updated.
     */
    private static JLabel graph;
    
    /**
     * Contains the text: "Please select a car make:".
     */
    private static JLabel label1;
    
    /**
     * Contains the text: "Please select a car model:".
     */
    private static JLabel label2;
    
    /**
     * Contains the text: "VERY LOW risk".
     */
    private static JLabel label3;
    
    /**
     * Contains the text: "LOW risk".
     */
    private static JLabel label4;
    
    /**
     * Contains the text: "MEDIUM risk".
     */
    private static JLabel label5;
    
    /**
     * Contains the text: "HIGH risk".
     */
    private static JLabel label6;
    
    /**
     * Contains the text: "VERY HIGH risk".
     */
    private static JLabel label7;
    
    /**
     * Contains the text: "Car specifications:".
     */
    private static JLabel label8;
    
    /**
     * Contains the text: "Price ($)".
     */
    private static JLabel label9;
    
    /**
     * Contains the text: "Mileage (KMS)".
     */
    private static JLabel label10;
    
    /**
     * Contains the text: "0" by default. It is changed according to the graph
     * values. This is an axis label.
     */
    private static JLabel label11;
    
    /**
     * Contains the text: "1" by default. It is changed according to the graph
     * values. This is an axis label.
     */
    private static JLabel label12;
    
    /**
     * Contains the text: "2" by default. It is changed according to the graph
     * values. This is an axis label.
     */
    private static JLabel label13;
    
    /**
     * Contains the text: "3" by default. It is changed according to the graph
     * values. This is an axis label.
     */
    private static JLabel label14;
    
    /**
     * Contains the text: "4" by default. It is changed according to the graph
     * values. This is an axis label.
     */
    private static JLabel label15;
    
    /**
     * Contains the text: "5" by default. It is changed according to the graph
     * values. This is an axis label.
     */
    private static JLabel label16;
    
    /**
     * Contains the text: "6" by default. It is changed according to the graph
     * values. This is an axis label.
     */
    private static JLabel label17;
    
    /**
     * Contains the text: "7" by default. It is changed according to the graph
     * values. This is an axis label.
     */
    private static JLabel label18;
    
    /**
     * Contains the text: "8" by default. It is changed according to the graph
     * values. This is an axis label.
     */
    private static JLabel label19;
    
    /**
     * Contains the text: "9" by default. It is changed according to the graph
     * values. This is an axis label.
     */
    private static JLabel label20;
    
    /**
     * Contains the text: "10" by default. It is changed according to the graph
     * values. This is an axis label.
     */
    private static JLabel label21;
    
    /**
     * Contains the text: "Enter dummy car title:".
     */
    private static JLabel dummyLabel1;
    
    /**
     * Contains the text: "Price:".
     */
    private static JLabel dummyLabel2;
    
    /**
     * Contains the text: "Mileage:".
     */
    private static JLabel dummyLabel3;
    
    /**
     * Contains the text: "Has phone # ?".
     */
    private static JLabel dummyLabel4;
    
    /**
     * Contains the text: "Listing Type?".
     */
    private static JLabel dummyLabel5;
    
    /**
     * The list of very low advertisements.
     */
    private static JList listVeryLow;
    
    /**
     * The list of low advertisements.
     */
    private static JList listLow;
    
    /**
     * The list of medium advertisements.
     */
    private static JList listMedium;
    
    /**
     * The list of very high advertisements.
     */
    private static JList listHigh;
    
    /**
     * The list of very high advertisements.
     */
    private static JList listVeryHigh;
    
    /**
     * The list of specifications of an advertisement.
     */
    private static JList listSpecs;
    
    /**
     * The data required for <code>listVeryLow</code>.
     */
    private static DefaultListModel listModelVeryLow;
    
    /**
     * The data required for <code>listLow</code>.
     */
    private static DefaultListModel listModelLow;
    
    /**
     * The data required for <code>listMedium</code>.
     */
    private static DefaultListModel listModelMedium;
    
    /**
     * The data required for <code>listHigh</code>.
     */
    private static DefaultListModel listModelHigh;
    
    /**
     * The data required for <code>listVeryHigh</code>.
     */
    private static DefaultListModel listModelVeryHigh;
    
    /**
     * The data required for <code>listSpecs</code>.
     */
    private static DefaultListModel listModelSpecs;
    
    /**
     * Button which has text: "GET CAR MODELS". Retrieves the list of car
     * models.
     */
    private static JButton buttonCarModels;
    
    /**
     * Button which has text: "GET CAR LISTINGS". Retrieves the car
     * advertisements.
     */
    private static JButton buttonCarListings;
    
    /**
     * Button which has text: "RESET". Resets all the panels.
     */
    private static JButton buttonReset;
    
    /**
     * Button which has text: "GET DETAILS". This button is located under the
     * very low list.
     */
    private static JButton buttonVeryLow;
    
    /**
     * Button which has text: "GET DETAILS". This button is located under the
     * low list.
     */
    private static JButton buttonLow;
    
    /**
     * Button which has text: "GET DETAILS". This button is located under the
     * medium list.
     */
    private static JButton buttonMedium;
    
    /**
     * Button which has text: "GET DETAILS". This button is located under the
     * high list.
     */
    private static JButton buttonHigh;
    
    /**
     * Button which has text: "GET DETAILS". This button is located under the
     * very high list.
     */
    private static JButton buttonVeryHigh;
    
    /**
     * Button which has text: "VIEW LISTING". Takes you to the advertisement's
     * URL.
     */
    private static JButton buttonWebBrowser;
    
    /**
     * Button which has text: "EXPLAIN RISK?". Shows a dialog that explains the
     * risk calculation process.
     */
    private static JButton buttonRiskExplain;
    
    /**
     * Button which has text: "ADD". Adds the dummy listing to the application.
     */
    private static JButton buttonDummyAdd;
    
    /**
     * Drop-down list that holds the car makes.
     */
    private static JComboBox comboCarMakes;
    
    /**
     * Drop-down list that holds the car models.
     */
    private static JComboBox comboCarModels;
    
    /**
     * Drop-down list that holds the options of Has phone #. Default value is
     * "YES", other value is "NO".
     */
    private static JComboBox dummyPhone;
    
    /**
     * Drop-down list that holds the options of Listing Type. Default value is
     * "Dealership", other value is "Private Listing".
     */
    private static JComboBox dummyListingType;
    
    /**
     * The progress bar.
     */
    private static JProgressBar progressBar;
    
    /**
     * Contains the car make.
     */
    private static StringBuilder carMake;
    
    /**
     * Contains the car model.
     */
    private static StringBuilder carModel;
    
    /**
     * Contains the selected listing.
     */
    private static StringBuilder selected;
    
    /**
     * The current directory on the local system.
     */
    private static String CURRENT_DIRECTORY = System.getProperty("user.dir");
    
    /**
     * The current system's file separator.
     */
    private static String FILE_SEPARATOR = System.getProperty("file.separator");
    
    /**
     * Constant used for action commands.
     */
    private static final String GET_CAR_MODELS = "getCarModels";
    
    /**
     * Constant used for action commands.
     */
    private static final String GET_CAR_LISTINGS = "getCarListings";
    
    /**
     * Constant used for action commands.
     */
    private static final String GET_VERY_LOW_LISTINGS = "veryLow";
    
    /**
     * Constant used for action commands.
     */
    private static final String GET_LOW_LISTINGS = "low";
    
    /**
     * Constant used for action commands.
     */
    private static final String GET_MEDIUM_LISTINGS = "medium";
    
    /**
     * Constant used for action commands.
     */
    private static final String GET_HIGH_LISTINGS = "high";
    
    /**
     * Constant used for action commands.
     */
    private static final String GET_VERY_HIGH_LISTINGS = "veryHigh";
    
    /**
     * Constant used for action commands.
     */
    private static final String RESET = "reset";
    
    /**
     * Constant used for action commands.
     */
    private static final String VIEW_LINK = "viewLink";
    
    /**
     * Constant used for action commands.
     */
    private static final String RISK_EXPLAIN = "riskExplain";
    
    /**
     * Constant used for action commands.
     */
    private static final String DUMMY_ADD = "dummyAdd";
    
    /**
     * True if an XML file is being read, false otherwise.
     */
    private static boolean fromHardDisk;
    
    /**
     * The counter of how many dummy cars were added.
     */
    private static int dummyUrlCounter = 0;
    
    /**
     * Counter for how many very low listings there are.
     */
    private static int veryLow;
    
    /**
     * Counter for how many low listings there are.
     */
    private static int low;
    
    /**
     * Counter for how many medium listings there are.
     */
    private static int medium;
    
    /**
     * Counter for how many high listings there are.
     */
    private static int high;
    
    /**
     * Counter for how many very high listings there are.
     */
    private static int veryHigh;
    
    /**
     * A task update object used for multi-threading in swing. This is used
     * for the progress bar in the background.
     */
    private static TaskUpdate taskUpdate;

    /**
     * Constructs the GUI interface. Calls many other "constructors" which
     * initialize other GUI components.
     */
    public GraphicalUserInterface() {
        //initialize the entire program.
        setupInitializeObjects();
        setupButtonCarModels();
        setupComboBoxCarMakes();
        buttonCarModels.addActionListener(this);
        setupComboBoxCarModels();
        setupButtonCarListings();
        buttonCarListings.addActionListener(this);
        setupButtonReset();
        buttonReset.addActionListener(this);
        setupProgressBar();
        setupDummyPanelObjects();
        buttonDummyAdd.addActionListener(this);
        setupVehicleListSpecifications();
        setupLinkToWebButton();
        buttonWebBrowser.addActionListener(this);
        setupRiskExplanationButton();
        buttonRiskExplain.addActionListener(this);
        setupRiskLists();
        setupRiskButtons();
        buttonVeryLow.addActionListener(this);
        buttonLow.addActionListener(this);
        buttonMedium.addActionListener(this);
        buttonHigh.addActionListener(this);
        buttonVeryHigh.addActionListener(this);
        setupGraphUtility();
        setLabels();
        frame.setVisible(true);
        frame.validate();
    }

    /**
     * Initializes the objects that are used globally.
     */
    public static void setupInitializeObjects() {
        //initialize the string objects.
        carMake = new StringBuilder();
        carModel = new StringBuilder();
        selected = new StringBuilder();
        
        //initialize the autoTrader object
        autoTrader = new AutoTraderFraudCalculator();
        
        //initialize the analysis object
        analyzer = new Analysis();

        //initialize the list of prices and mileages for quick graph reference
        priceAndMileage = new ArrayList<Integer>();
    }
    
    /**
     * Constructs the car makes drop down list.
     */
    public static void setupComboBoxCarMakes() {
        int attempts = 0;
        boolean done = false;
        
        while (attempts < 5 && !done) {
            //displays the car makes combobox
            try {
                attempts++;
                comboCarMakes = new JComboBox
                        (autoTrader.getCarMakes().toArray());
                //getCarMakes() is returning an array of 0 length
                System.out.println((autoTrader.getCarMakes().toArray()).length);
                comboCarMakes.setBounds(10, 10, 150, 20);
                comboCarMakes.setMaximumRowCount(6);
                comboCarMakes.setSelectedIndex(0);
                frame.add(comboCarMakes);
                buttonCarModels.setEnabled(true);
                done = true;
            } catch (IllegalStateException ie) {
                System.err.println("CAR MAKES: The website refused to allow "
                        + "further data to be retrieved. Trying again. " 
                        + ie.getMessage());

                JOptionPane.showMessageDialog(frame,
                    "The website refused to allow further data to be retrieved."
                        + " Will attempt again.",
                    "AutoTrader Fraud Calculator",
                    JOptionPane.INFORMATION_MESSAGE);
            } catch (NullPointerException ne) {
                System.err.println("CAR MAKES: The website refused to allow "
                        + "further data to be retrieved. Trying again. " 
                        + ne.getMessage());

                JOptionPane.showMessageDialog(frame,
                    "The website refused to allow data to be retrieved."
                        + " Will attempt again.",
                    "AutoTrader Fraud Calculator",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        }
        
        if (attempts == 5) {
            JOptionPane.showMessageDialog(frame,
                "The website refused to allow data to be retrieved."
                    + " Please try again later.",
                "AutoTrader Fraud Calculator",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
     * Constructs the car models button.
     */
    public static void setupButtonCarModels() {
        //displays the car models button activator
        buttonCarModels = new JButton("GET CAR MODELS");
        buttonCarModels.setBounds(10, 33,
                buttonCarModels.getMaximumSize().width + 15,
                buttonCarModels.getMaximumSize().height);

        buttonCarModels.setVerticalTextPosition(AbstractButton.CENTER);
        buttonCarModels.setHorizontalTextPosition(AbstractButton.CENTER);
        buttonCarModels.setMnemonic(KeyEvent.VK_M);
        buttonCarModels.setActionCommand(GET_CAR_MODELS);
        buttonCarModels.setEnabled(false);
        frame.add(buttonCarModels);
    }
    
    /**
     * Constructs the car models drop down list.
     */
    public static void setupComboBoxCarModels() {
        //car models combo box
        comboCarModels = new JComboBox();
        comboCarModels.setBounds(10, 75, 150, 20);
        comboCarModels.setMaximumRowCount(6);
        comboCarModels.setEnabled(false);
        frame.add(comboCarModels);
    }
    
    /**
     * Constructs the "GET CAR LISTINGS" button.
     */
    public static void setupButtonCarListings() {
        //displays the car listings button activator
        buttonCarListings = new JButton("GET CAR LISTINGS");
        buttonCarListings.setBounds(10, 98,
                buttonCarListings.getMaximumSize().width + 12,
                buttonCarListings.getMaximumSize().height);

        buttonCarListings.setVerticalTextPosition(AbstractButton.CENTER);
        buttonCarListings.setHorizontalTextPosition(AbstractButton.CENTER);
        buttonCarListings.setMnemonic(KeyEvent.VK_L);
        buttonCarListings.setActionCommand(GET_CAR_LISTINGS);
        buttonCarListings.setEnabled(false);
        frame.add(buttonCarListings);
    }
    
    /**
     * Constructs the "RESET" button.
     */
    public static void setupButtonReset() {
        //reset button
        buttonReset = new JButton("RESET");
        buttonReset.setBounds(10, 126,
                buttonReset.getMaximumSize().width + 79,
                buttonReset.getMaximumSize().height);

        buttonReset.setVerticalTextPosition(AbstractButton.CENTER);
        buttonReset.setHorizontalTextPosition(AbstractButton.CENTER);
        buttonReset.setMnemonic(KeyEvent.VK_R);
        buttonReset.setActionCommand(RESET);
        buttonReset.setEnabled(false);
        frame.add(buttonReset);
    }
    
    /**
     * Constructs the progress bar.
     */
    public static void setupProgressBar() {
        //progress bar
        progressBar = new JProgressBar(0, 100);
        progressBar.setBounds(10, 155, 150, 20);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        progressBar.setVisible(false);
        frame.add(progressBar);
    }
    
    /**
     * Constructs the panel that contains the options for adding a dummy
     * listing.
     */
    public static void setupDummyPanelObjects() {
        //dummy car listing information panel
        dummyPanel = new JPanel();
        dummyPanel.setLayout(null);
        dummyPanel.setBounds(10, 178, 150, 155);
        dummyPanel.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("Dummy Car Listing"),
                        BorderFactory.createEmptyBorder(1, 1, 1, 1)));
        
        dummyPanel.setBackground(Color.CYAN);
        frame.add(dummyPanel);
        
        //dummy title field
        dummyTitle = new JTextField();
        dummyTitle.setBounds(7, 30, 135, 20);
        dummyTitle.setEnabled(false);
        dummyPanel.add(dummyTitle);
        
        //dummy price field
        dummyPrice = new JTextField();
        dummyPrice.setBounds(7, 63, 55, 20);
        dummyPrice.setEnabled(false);
        dummyPanel.add(dummyPrice);
        
        //dummy mileage field
        dummyMileage = new JTextField();
        dummyMileage.setBounds(77, 63, 55, 20);
        dummyMileage.setEnabled(false);
        dummyPanel.add(dummyMileage);
        
        //dummy phone number combo box
        dummyPhone = new JComboBox();
        dummyPhone.setBounds(7, 98, 50, 20);
        dummyPhone.setMaximumRowCount(2);
        dummyPhone.addItem("YES");
        dummyPhone.addItem("NO");
        dummyPhone.setSelectedIndex(0);
        dummyPhone.setEnabled(false);
        dummyPanel.add(dummyPhone);
        
        //dummy listing type combo box
        dummyListingType = new JComboBox();
        dummyListingType.setBounds(7, 132, 130, 20);
        dummyListingType.setMaximumRowCount(2);
        dummyListingType.addItem("Dealership");
        dummyListingType.addItem("Private Listing");
        dummyListingType.setSelectedIndex(0);
        dummyListingType.setEnabled(false);
        dummyPanel.add(dummyListingType);
        
        //dummy add button
        buttonDummyAdd = new JButton("ADD");
        buttonDummyAdd.setBounds(85, 100,
                buttonDummyAdd.getMaximumSize().width,
                buttonDummyAdd.getMaximumSize().height);
        
        buttonDummyAdd.setVerticalTextPosition(AbstractButton.CENTER);
        buttonDummyAdd.setHorizontalTextPosition(AbstractButton.CENTER);
        buttonDummyAdd.setMnemonic(KeyEvent.VK_A);
        buttonDummyAdd.setActionCommand(DUMMY_ADD);
        buttonDummyAdd.setEnabled(false);
        dummyPanel.add(buttonDummyAdd);
    }
    
    /**
     * Constructs the panel that contains the specifications list.
     */
    public static void setupVehicleListSpecifications() {
        //displays the specification details of a vehicle
        listModelSpecs = new DefaultListModel();
        listSpecs = new JList(listModelSpecs);
        listSpecs.setBounds(10, 300, 150, 300);
        listSpecs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSpecs.setSelectedIndex(0);
        listSpecs.setLayoutOrientation(JList.VERTICAL);
        JScrollPane listScrollPaneSpecs = new JScrollPane(listSpecs);
        listScrollPaneSpecs.setBounds(10, 350, 150, 300);
        listScrollPaneSpecs.createHorizontalScrollBar();
        listScrollPaneSpecs.createVerticalScrollBar();
        frame.add(listScrollPaneSpecs);
    }
    
    /**
     * Constructs the "VIEW LISTING" button that launches the default web
     * browser with the URL of the selected listing.
     */
    public static void setupLinkToWebButton() {
        //web browser button
        buttonWebBrowser = new JButton("VIEW LISTING");
        buttonWebBrowser.setBounds(33, 655,
                buttonWebBrowser.getMaximumSize().width,
                buttonWebBrowser.getMaximumSize().height);
        
        buttonWebBrowser.setVerticalTextPosition(AbstractButton.CENTER);
        buttonWebBrowser.setHorizontalTextPosition(AbstractButton.CENTER);
        buttonWebBrowser.setMnemonic(KeyEvent.VK_V);
        buttonWebBrowser.setActionCommand(VIEW_LINK);
        buttonWebBrowser.setEnabled(false);
        frame.add(buttonWebBrowser);
    }
    
    /**
     * Constructs the "EXPLAIN RISK?" button that shows a dialog which has an
     * explanation of the risk calculation algorithm.
     */
    public static void setupRiskExplanationButton() {
        //risk explanation button
        buttonRiskExplain = new JButton("EXPLAIN RISK?");
        buttonRiskExplain.setBounds(28, 685,
                buttonRiskExplain.getMaximumSize().width,
                buttonRiskExplain.getMaximumSize().height);
        
        buttonRiskExplain.setVerticalTextPosition(AbstractButton.CENTER);
        buttonRiskExplain.setHorizontalTextPosition(AbstractButton.CENTER);
        buttonRiskExplain.setMnemonic(KeyEvent.VK_E);
        buttonRiskExplain.setActionCommand(RISK_EXPLAIN);
        frame.add(buttonRiskExplain);
    }
    
    /**
     * Constructs the five lists that contain listings with different risk
     * tier ranking.
     */
    public static void setupRiskLists() {
        //displays the vehicles with very low risk
        listModelVeryLow = new DefaultListModel();
        listVeryLow = new JList(listModelVeryLow);
        listVeryLow.setBounds(190, 26, 150, 200);
        listVeryLow.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listVeryLow.setSelectedIndex(0);
        listVeryLow.setLayoutOrientation(JList.VERTICAL);
        JScrollPane listScrollPaneVeryLow = new JScrollPane(listVeryLow);
        listScrollPaneVeryLow.setBounds(190, 26, 150, 200);
        listScrollPaneVeryLow.createHorizontalScrollBar();
        listScrollPaneVeryLow.createVerticalScrollBar();
        frame.add(listScrollPaneVeryLow);

        //displays the vehicles with low risk
        listModelLow = new DefaultListModel();
        listLow = new JList(listModelLow);
        listLow.setBounds(340, 26, 150, 200);
        listLow.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listLow.setSelectedIndex(0);
        listLow.setLayoutOrientation(JList.VERTICAL);
        JScrollPane listScrollPaneLow = new JScrollPane(listLow);
        listScrollPaneLow.setBounds(340, 26, 150, 200);
        listScrollPaneLow.createHorizontalScrollBar();
        listScrollPaneLow.createVerticalScrollBar();
        frame.add(listScrollPaneLow);

        //displays the vehicles with medium risk
        listModelMedium = new DefaultListModel();
        listMedium = new JList(listModelMedium);
        listMedium.setBounds(490, 26, 150, 200);
        listMedium.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listMedium.setSelectedIndex(0);
        listMedium.setLayoutOrientation(JList.VERTICAL);
        JScrollPane listScrollPaneMedium = new JScrollPane(listMedium);
        listScrollPaneMedium.setBounds(490, 26, 150, 200);
        listScrollPaneMedium.createHorizontalScrollBar();
        listScrollPaneMedium.createVerticalScrollBar();
        frame.add(listScrollPaneMedium);

        //displays the vehicles with high risk
        listModelHigh = new DefaultListModel();
        listHigh = new JList(listModelHigh);
        listHigh.setBounds(640, 26, 150, 200);
        listHigh.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listHigh.setSelectedIndex(0);
        listHigh.setLayoutOrientation(JList.VERTICAL);
        JScrollPane listScrollPaneHigh = new JScrollPane(listHigh);
        listScrollPaneHigh.setBounds(640, 26, 150, 200);
        listScrollPaneHigh.createHorizontalScrollBar();
        listScrollPaneHigh.createVerticalScrollBar();
        frame.add(listScrollPaneHigh);

        //displays the vehicles with very high risk
        listModelVeryHigh = new DefaultListModel();
        listVeryHigh = new JList(listModelVeryHigh);
        listVeryHigh.setBounds(790, 26, 150, 200);
        listVeryHigh.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listVeryHigh.setSelectedIndex(0);
        listVeryHigh.setLayoutOrientation(JList.VERTICAL);
        JScrollPane listScrollPaneVeryHigh = new JScrollPane(listVeryHigh);
        listScrollPaneVeryHigh.setBounds(790, 26, 150, 200);
        listScrollPaneVeryHigh.createHorizontalScrollBar();
        listScrollPaneVeryHigh.createVerticalScrollBar();
        frame.add(listScrollPaneVeryHigh);
    }
    
    /**
     * Constructs the "GET DETAILS" buttons under the five lists, which allow
     * for more specific details of the selected listing.
     */
    public static void setupRiskButtons() {
        //button for very low risk listings
        buttonVeryLow = new JButton("GET DETAILS");
        buttonVeryLow.setBounds(210, 235,
                buttonVeryLow.getMaximumSize().width,
                buttonVeryLow.getMaximumSize().height);

        buttonVeryLow.setVerticalTextPosition(AbstractButton.CENTER);
        buttonVeryLow.setHorizontalTextPosition(AbstractButton.CENTER);
        buttonVeryLow.setActionCommand(GET_VERY_LOW_LISTINGS);
        buttonVeryLow.setEnabled(false);
        frame.add(buttonVeryLow);
        
        //button for low risk listings
        buttonLow = new JButton("GET DETAILS");
        buttonLow.setBounds(365, 235,
                buttonLow.getMaximumSize().width,
                buttonLow.getMaximumSize().height);

        buttonLow.setVerticalTextPosition(AbstractButton.CENTER);
        buttonLow.setHorizontalTextPosition(AbstractButton.CENTER);
        buttonLow.setActionCommand(GET_LOW_LISTINGS);
        buttonLow.setEnabled(false);
        frame.add(buttonLow);
        
        //button for medium risk listings
        buttonMedium = new JButton("GET DETAILS");
        buttonMedium.setBounds(515, 235,
                buttonMedium.getMaximumSize().width,
                buttonMedium.getMaximumSize().height);

        buttonMedium.setVerticalTextPosition(AbstractButton.CENTER);
        buttonMedium.setHorizontalTextPosition(AbstractButton.CENTER);
        buttonMedium.setActionCommand(GET_MEDIUM_LISTINGS);
        buttonMedium.setEnabled(false);
        frame.add(buttonMedium);
        
        //button for high risk listings
        buttonHigh = new JButton("GET DETAILS");
        buttonHigh.setBounds(665, 235,
                buttonHigh.getMaximumSize().width,
                buttonHigh.getMaximumSize().height);

        buttonHigh.setVerticalTextPosition(AbstractButton.CENTER);
        buttonHigh.setHorizontalTextPosition(AbstractButton.CENTER);
        buttonHigh.setActionCommand(GET_HIGH_LISTINGS);
        buttonHigh.setEnabled(false);
        frame.add(buttonHigh);
        
        //button for very high risk listings
        buttonVeryHigh = new JButton("GET DETAILS");
        buttonVeryHigh.setBounds(815, 235,
                buttonVeryHigh.getMaximumSize().width,
                buttonVeryHigh.getMaximumSize().height);

        buttonVeryHigh.setVerticalTextPosition(AbstractButton.CENTER);
        buttonVeryHigh.setHorizontalTextPosition(AbstractButton.CENTER);
        buttonVeryHigh.setActionCommand(GET_VERY_HIGH_LISTINGS);
        buttonVeryHigh.setEnabled(false);
        frame.add(buttonVeryHigh);
    }
    
    /**
     * Constructs the graph positioning and size.
     */
    public static void setupGraphUtility() {
        //display the graph window
        graph = new Graphing();
        graph.setBounds(230, 280, 750, 350);
        graph.setVisible(false);
        frame.add(graph);
    }
    
    /**
     * Constructs the various labels seen in the GUI.
     */
    public static void setLabels() {
        label1 = new JLabel("Please select a car make:");
        label1.setBounds(10, -4, label1.getMaximumSize().width,
                label1.getMaximumSize().height);

        label1.setForeground(Color.GREEN);
        label1.setVisible(true);
        frame.getContentPane().add(label1);

        label2 = new JLabel("Please select a car model:");
        label2.setBounds(10, 60, label2.getMaximumSize().width,
                label2.getMaximumSize().height);

        label2.setForeground(Color.GREEN);
        label2.setVisible(true);
        frame.getContentPane().add(label2);

        label3 = new JLabel("VERY LOW risk");
        label3.setBounds(200, 6, label3.getMaximumSize().width,
                label3.getMaximumSize().height);

        label3.setForeground(Color.GREEN);
        label3.setVisible(true);
        frame.getContentPane().add(label3);

        label4 = new JLabel("LOW risk");
        label4.setBounds(350, 6, label4.getMaximumSize().width,
                label4.getMaximumSize().height);

        label4.setForeground(Color.GREEN);
        label4.setVisible(true);
        frame.getContentPane().add(label4);

        label5 = new JLabel("MEDIUM risk");
        label5.setBounds(500, 6, label5.getMaximumSize().width,
                label5.getMaximumSize().height);

        label5.setForeground(Color.GREEN);
        label5.setVisible(true);
        frame.getContentPane().add(label5);

        label6 = new JLabel("HIGH risk");
        label6.setBounds(650, 6, label6.getMaximumSize().width,
                label6.getMaximumSize().height);

        label6.setForeground(Color.GREEN);
        label6.setVisible(true);
        frame.getContentPane().add(label6);

        label7 = new JLabel("VERY HIGH risk");
        label7.setBounds(800, 6, label7.getMaximumSize().width,
                label7.getMaximumSize().height);

        label7.setForeground(Color.GREEN);
        label7.setVisible(true);
        frame.getContentPane().add(label7);

        label8 = new JLabel("Car specifications:");
        label8.setBounds(10, 333, label8.getMaximumSize().width,
                label8.getMaximumSize().height);

        label8.setForeground(Color.GREEN);
        label8.setVisible(true);
        frame.getContentPane().add(label8);

        label9 = new JLabel("Price ($)");
        label9.setForeground(Color.GREEN);
        label9.setVisible(false);
        frame.getContentPane().add(label9);

        label10 = new JLabel("Mileage (KMS)");
        label10.setForeground(Color.GREEN);
        label10.setVisible(false);
        frame.getContentPane().add(label10);

        label11 = new JLabel("0");
        label11.setForeground(Color.GREEN);
        label11.setVisible(false);
        frame.getContentPane().add(label11);

        label12 = new JLabel("1");
        label12.setForeground(Color.GREEN);
        label12.setVisible(false);
        frame.getContentPane().add(label12);

        label13 = new JLabel("2");
        label13.setForeground(Color.GREEN);
        label13.setVisible(false);
        frame.getContentPane().add(label13);

        label14 = new JLabel("3");
        label14.setForeground(Color.GREEN);
        label14.setVisible(false);
        frame.getContentPane().add(label14);

        label15 = new JLabel("4");
        label15.setForeground(Color.GREEN);
        label15.setVisible(false);
        frame.getContentPane().add(label15);

        label16 = new JLabel("5");
        label16.setForeground(Color.GREEN);
        label16.setVisible(false);
        frame.getContentPane().add(label16);

        label17 = new JLabel("6");
        label17.setForeground(Color.GREEN);
        label17.setVisible(false);
        frame.getContentPane().add(label17);

        label18 = new JLabel("7");
        label18.setForeground(Color.GREEN);
        label18.setVisible(false);
        frame.getContentPane().add(label18);

        label19 = new JLabel("8");
        label19.setForeground(Color.GREEN);
        label19.setVisible(false);
        frame.getContentPane().add(label19);

        label20 = new JLabel("9");
        label20.setForeground(Color.GREEN);
        label20.setVisible(false);
        frame.getContentPane().add(label20);
        
        label21 = new JLabel("10");
        label21.setForeground(Color.WHITE);
        label21.setVisible(false);
        frame.getContentPane().add(label21);
        
        dummyLabel1 = new JLabel("Enter dummy car title:");
        dummyLabel1.setForeground(Color.BLACK);
        dummyLabel1.setBounds(7, 13, dummyLabel1.getMaximumSize().width, 
                dummyLabel1.getMaximumSize().height);
        
        dummyPanel.add(dummyLabel1);
        
        dummyLabel2 = new JLabel("Price:");
        dummyLabel2.setForeground(Color.BLACK);
        dummyLabel2.setBounds(7, 48, dummyLabel2.getMaximumSize().width, 
                dummyLabel2.getMaximumSize().height);
        
        dummyPanel.add(dummyLabel2);
        
        dummyLabel3 = new JLabel("Mileage:");
        dummyLabel3.setForeground(Color.BLACK);
        dummyLabel3.setBounds(77, 48, dummyLabel3.getMaximumSize().width, 
                dummyLabel3.getMaximumSize().height);
        
        dummyPanel.add(dummyLabel3);
        
        dummyLabel4 = new JLabel("Has phone # ?");
        dummyLabel4.setForeground(Color.BLACK);
        dummyLabel4.setBounds(7, 81, dummyLabel4.getMaximumSize().width, 
                dummyLabel4.getMaximumSize().height);
        
        dummyPanel.add(dummyLabel4);
        
        dummyLabel5 = new JLabel("Listing Type?");
        dummyLabel5.setForeground(Color.BLACK);
        dummyLabel5.setBounds(7, 116, dummyLabel5.getMaximumSize().width, 
                dummyLabel5.getMaximumSize().height);
        
        dummyPanel.add(dummyLabel5);
    }

    /**
     * Updates and enables labels and their values/content.
     */
    public static void makeVisibleAndComputeValues() {
        label9.setVisible(true);
        label9.setBounds(230 - label9.getMaximumSize().width - 5,
                280 - label9.getMaximumSize().height,
                label9.getMaximumSize().width,
                label9.getMaximumSize().height);

        label10.setVisible(true);
        label10.setBounds(550, 650, label10.getMaximumSize().width,
                label10.getMaximumSize().height);

        label11.setVisible(true);
        label11.setText("0");
        label11.setBounds(230 - label11.getMaximumSize().width - 3,
                280 + 350 - (int) (label11.getMaximumSize().height / 2) - 5,
                label11.getMaximumSize().width,
                label11.getMaximumSize().height);

        label12.setVisible(true);
        label12.setText(String.valueOf(autoTrader.getMaxPrice() / 4));
        label12.setBounds(230 - label12.getMaximumSize().width - 3,
                280 + 350 - (int) (label12.getMaximumSize().height / 2)
                - (int) (325 / 4) - 5,
                label12.getMaximumSize().width,
                label12.getMaximumSize().height);

        label13.setVisible(true);
        label13.setText(String.valueOf(autoTrader.getMaxPrice() / 4 * 2));
        label13.setBounds(230 - label13.getMaximumSize().width - 3,
                280 + 350 - (int) (label13.getMaximumSize().height / 2)
                - (int) (325 / 4 * 2) - 5,
                label13.getMaximumSize().width,
                label13.getMaximumSize().height);

        label14.setVisible(true);
        label14.setText(String.valueOf(autoTrader.getMaxPrice() / 4 * 3));
        label14.setBounds(230 - label14.getMaximumSize().width - 3,
                280 + 350 - (int) (label14.getMaximumSize().height / 2)
                - (int) (325 / 4 * 3) - 5,
                label14.getMaximumSize().width,
                label14.getMaximumSize().height);

        label15.setVisible(true);
        label15.setText(String.valueOf(autoTrader.getMaxPrice()));
        label15.setBounds(230 - label15.getMaximumSize().width - 3,
                280 + 350 - (int) (label15.getMaximumSize().height / 2)
                - 325 - 5,
                label15.getMaximumSize().width,
                label15.getMaximumSize().height);

        label16.setVisible(true);
        label16.setText("0");
        label16.setBounds(230, 630, label16.getMaximumSize().width,
                label16.getMaximumSize().height);


        label17.setVisible(true);
        label17.setText(String.valueOf(autoTrader.getMaxMileage() / 4));
        label17.setBounds((int) (230 + (725 / 4)
                - (label17.getMaximumSize().width / 2)), 630,
                label17.getMaximumSize().width,
                label17.getMaximumSize().height);

        label18.setVisible(true);
        label18.setText(String.valueOf(autoTrader.getMaxMileage() / 4 * 2));
        label18.setBounds((int) (230 + (725 / 4 * 2)
                - (label18.getMaximumSize().width / 2)), 630,
                label18.getMaximumSize().width,
                label18.getMaximumSize().height);

        label19.setVisible(true);
        label19.setText(String.valueOf(autoTrader.getMaxMileage() / 4 * 3));
        label19.setBounds((int) (230 + (725 / 4 * 3)
                - (label19.getMaximumSize().width / 2)), 630,
                label19.getMaximumSize().width,
                label19.getMaximumSize().height);

        label20.setVisible(true);
        label20.setText(String.valueOf(autoTrader.getMaxMileage()));
        label20.setBounds((int) (230 + 725
                - (label20.getMaximumSize().width / 2)), 630,
                label20.getMaximumSize().width,
                label20.getMaximumSize().height);
        
        label21.setVisible(true);
        label21.setText("SILVER BOX <===> AVERAGE PRICE: $" + 
                autoTrader.getAvgPrice() + "   AVERAGE MILEAGE: " + 
                autoTrader.getAvgMileage() + " KMS");
        
        label21.setBounds(200, 680,
                label21.getMaximumSize().width,
                label21.getMaximumSize().height);

        buttonWebBrowser.setEnabled(true);
    }

    /**
     * Sets the visibility of the different GUI components to invisible.
     */
    public static void makeInvisible() {
        label9.setVisible(false);
        label10.setVisible(false);
        label11.setVisible(false);
        label12.setVisible(false);
        label13.setVisible(false);
        label14.setVisible(false);
        label15.setVisible(false);
        label16.setVisible(false);
        label17.setVisible(false);
        label18.setVisible(false);
        label19.setVisible(false);
        label20.setVisible(false);
        label21.setVisible(false);
        buttonWebBrowser.setEnabled(false);
    }

    /**
     * Retrieves the car models from the <code>autotrader.ca</code> web-site. 
     * This function is called when the car make is chosen and the "GET CAR
     * MODELS" button is pressed.
     */
    public void getCarModels() {
        carMake.delete(0, carMake.length());
        carMake.append(comboCarMakes.getSelectedItem().toString());
        comboCarModels.removeAllItems();
        
        try {
            Iterator it = autoTrader.getCarModels
                    (carMake.toString()).keySet().iterator();
            
            while (it.hasNext()) {
                comboCarModels.addItem(it.next());
            }
            
            comboCarMakes.setEnabled(false);
            buttonCarModels.setEnabled(false);
            buttonReset.setEnabled(true);
            comboCarModels.setEnabled(true);
            comboCarModels.setSelectedIndex(0);
            buttonCarListings.setEnabled(true);
        } catch (IllegalStateException e) {
            System.err.println("GET CAR MODELS: The website refused to allow "
                    + "further data to be retrieved. " + e.getMessage());
            
            JOptionPane.showMessageDialog(frame,
                "The website refused to allow further data to be retrieved."
                    + " Please try again shortly.",
                "AutoTrader Fraud Calculator",
                JOptionPane.INFORMATION_MESSAGE);
        } catch (NullPointerException ne) {
            System.err.println("GET CAR MODELS: The website refused to allow "
                    + "further data to be retrieved. " + ne.getMessage());
            
            JOptionPane.showMessageDialog(frame,
                "The website refused to allow further data to be retrieved."
                    + " Please try again shortly.",
                "AutoTrader Fraud Calculator",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Retrieves the car listings from the <code>autotrader.ca</code> website. 
     * If a local XML file corresponding to the car make and model is found, a
     * prompt dialog asks if the user wishes to use cached data or retrieve new
     * information.
     */
    public void getCarListings() {
        Scanner scanning = null;
        
        frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        carModel.delete(0, carModel.length());
        carModel.append(comboCarModels.getSelectedItem().toString());
        comboCarModels.setEnabled(false);
        buttonCarListings.setEnabled(false);
        buttonReset.setEnabled(false);
        priceAndMileage.clear();

        try {
            File file = new File(CURRENT_DIRECTORY
                    + FILE_SEPARATOR + carMake + carModel + ".xml");

            if (!file.exists()) {
                throw new IOException();
            }

            int option = JOptionPane.showConfirmDialog(frame, 
                    "Would you like to re-download the updated "
                    + "listings from the AutoTrader website?\n\n"
                    + "If YES -- Please note that this may take a few "
                    + "minutes.\n\n"
                    + "If NO -- Information on the harddisk will be used "
                    + "instead.",
                    "AutoTrader Fraud Calculator",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if (option == JOptionPane.YES_OPTION) {
                downloadInformation();
            } else if (option == JOptionPane.NO_OPTION) {
                fromHardDisk = true;
                
                try {
                    if (!file.exists()) {
                        throw new IOException();
                    }

                    displayListings(CURRENT_DIRECTORY
                            + FILE_SEPARATOR + carMake + carModel + ".xml");
                    
                } catch (IOException e) {
                    System.err.println("GET CAR LISTINGS: "
                            + "An input/output issue was caught. " 
                            + e.getMessage());
                    
                    JOptionPane.showMessageDialog(frame,
                        "There was a problem trying to read data from a file.",
                        "AutoTrader Fraud Calculator",
                        JOptionPane.INFORMATION_MESSAGE);
                    
                    getReset();
                }
            }
        } catch (IOException m) {
            downloadInformation();
        }
    }
    
    /**
     * Downloads the listing information from the <code>autotrader.ca</code>
     * website. Once listings are found, outputs them to an XML file which can
     * then be read by {@link #displayListings(java.util.Scanner)}.
     */
    public void downloadInformation() {
        fromHardDisk = false;
        
        new Thread() {                    
            @Override
            public void run() {
                try {
                    autoTrader.downloadCarListings
                            (carMake.toString(), carModel.toString());
                    
                } catch (IOException e) {
                    System.err.println("DOWNLOAD INFORMATION IO: "
                            + "An input/output issue was caught. " 
                            + e.getMessage());
                    
                    JOptionPane.showMessageDialog(frame,
                        "There was a problem trying to read data from a file.",
                        "AutoTrader Fraud Calculator",
                        JOptionPane.INFORMATION_MESSAGE);
                    
                    taskUpdate.cancel(true);
                    getReset();
                    
                } catch (IllegalStateException ie) {
                    System.err.println("DOWNLOAD INFORMATION ILLEGAL STATE: "
                            + "An issue was caught while trying to "
                            + "read data from the website. " + ie.getMessage());
                    
                    JOptionPane.showMessageDialog(frame,
                        "The website refused to allow further data to be "
                        + "retrieved. Please try again shortly.",
                        "AutoTrader Fraud Calculator",
                        JOptionPane.INFORMATION_MESSAGE);
                    
                    taskUpdate.cancel(true);
                    getReset();
                    
                } catch (NullPointerException ne) {
                    System.err.println("DOWNLOAD INFORMATION NULL: "
                            + "An issue was caught while trying to "
                            + "read data from the website. " + ne.getMessage());
                    
                    JOptionPane.showMessageDialog(frame,
                        "The website refused to allow further data to be "
                        + "retrieved. Please try again shortly.",
                        "AutoTrader Fraud Calculator",
                        JOptionPane.INFORMATION_MESSAGE);
                    
                    taskUpdate.cancel(true);
                    getReset();
                } catch (ArrayIndexOutOfBoundsException ae) {
                    System.err.println("DOWNLOAD INFORMATION ARRAY BOUNDS: "
                            + "An issue was caught while trying to "
                            + "read data from the website. " + ae.getMessage());
                    
                    JOptionPane.showMessageDialog(frame,
                        "The website refused to allow data to be "
                        + "properly retrieved. Please try again shortly.",
                        "AutoTrader Fraud Calculator",
                        JOptionPane.INFORMATION_MESSAGE);
                    
                    taskUpdate.cancel(true);
                    getReset();
                }
            }
        }.start();
        
        try {   
            Thread.sleep(5000);
        } catch (InterruptedException ex) {}
        
        taskUpdate = new TaskUpdate();
        taskUpdate.addPropertyChangeListener(this);
        taskUpdate.execute();
    }
    
    /**
     * Obtains the listings and the values needed to call upon 
     * <code>populateListings</code> and <code>updatePriceAndMileageStats
     * </code>.
     * 
     * @param fileName The XML file that contains the listings.
     */
    public static void displayListings(String fileName){
        StringBuilder title = new StringBuilder();
        boolean hasPrice = false;
        boolean hasMileage = false;
        int price = 0;
        int mileage = 0;
        int risk = 0;
        
        veryLow = 0;
        low = 0;
        medium = 0;
        high = 0;
        veryHigh = 0;
        dummyUrlCounter = 0;
        listModelVeryLow.removeAllElements();
        listModelLow.removeAllElements();
        listModelMedium.removeAllElements();
        listModelHigh.removeAllElements();
        listModelVeryHigh.removeAllElements();
        listModelSpecs.removeAllElements();

        //initialize the url mapping to spec types/values
        urlMap = new TreeMap<String, ArrayList<String>>
                (analyzer.urlToSpecs(fileName, fromHardDisk));
       
        for(int x = 0; x < urlMap.keySet().toArray().length; x++) {
            String url = String.valueOf(urlMap.keySet().toArray()[x]);
            
            for(int y = 0; y < urlMap.get(url).size(); y += 2) {
                String specType = urlMap.get(url).get(y);
                String specValue = urlMap.get(url).get(y + 1);
                
                if (specType.contentEquals("Title")) {
                    title.delete(0, title.length());
                    title.append(specValue);
                } else if (specType.contentEquals("Mileage") && 
                        !specValue.contentEquals("N/A")) {
                    
                    mileage = Integer.parseInt(specValue.split(" ")[0]);
                    hasMileage = true;
                } else if (specType.contentEquals("Price") && 
                        !specValue.contentEquals("N/A")) {
                    
                    price = Integer.parseInt(specValue);
                    hasPrice = true;
                } else if (specType.contentEquals("Risk")) {
                    risk = Integer.parseInt(specValue);
                }
            }
            
            if (hasPrice && hasMileage) {
                priceAndMileage.add(price);
                priceAndMileage.add(mileage);
                hasPrice = false;
                hasMileage = false;
            }
            
            populateListings(risk, title.toString(), url);
        }

        updatePriceAndMileageStats();

        listsReset();
        buttonReset.setEnabled(true);
        progressBar.setValue(100);
        dummyTitle.setEnabled(true);
        dummyPrice.setEnabled(true);
        dummyMileage.setEnabled(true);
        dummyPhone.setEnabled(true);
        dummyListingType.setEnabled(true);
        buttonDummyAdd.setEnabled(true);
        frame.setCursor(null);
        frame.validate();
    }
    
    /**
     * Adds and organizes a listing into one of the five risk tiers and displays
     * it on the GUI.
     * 
     * @param risk The risk of the listing.
     * @param title The title of the listing.
     * @param url The URL of the listing.
     */
    public static void populateListings(int risk, String title, String url) {
        if (risk >= 0 && risk <= 19) {
            veryLow++;
            listModelVeryLow.addElement(veryLow + " - "
                    + title + " - " + url);
        } else if (risk >= 20 && risk <= 39) {
            low++;
            listModelLow.addElement(low + " - "
                    + title + " - " + url);
        } else if (risk >= 40 && risk <= 59) {
            medium++;
            listModelMedium.addElement(medium + " - "
                    + title + " - " + url);
        } else if (risk >= 60 && risk <= 79) {
            high++;
            listModelHigh.addElement(high + " - "
                    + title + " - " + url);
        } else if (risk >= 80 && risk <= 100) {
            veryHigh++;
            listModelVeryHigh.addElement(veryHigh + " - "
                    + title + " - " + url);
        }
    }
    
    /**
     * Updates the price and mileage statistics required for the graphing.
     */
    public static void updatePriceAndMileageStats() {
        if (fromHardDisk) {
            autoTrader.setMinPrice(analyzer.getMinPrice());
            autoTrader.setAvgPrice(analyzer.getAvgPrice());
            autoTrader.setMaxPrice(analyzer.getMaxPrice());
            autoTrader.setMinMileage(analyzer.getMinMileage());
            autoTrader.setAvgMileage(analyzer.getAvgMileage());
            autoTrader.setMaxMileage(analyzer.getMaxMileage());
        }
    }
    
    /**
     * Update the price and mileage statistics required for the graphing.
     * 
     * @param price The price to add to the <code>priceAndMileage</code>
     *              variable.
     * @param mileage The mileage to add to the <code>priceAndMileage</code>
     *                variable.
     */
    public static void updatePriceAndMileageStats
            (String price, String mileage) {
        
        if (!price.contentEquals("N/A") && !mileage.contentEquals("N/A")) {
            priceAndMileage.add(Integer.parseInt(price));
            priceAndMileage.add(Integer.parseInt(mileage));

            autoTrader.setAvgPrice
                ((int)(((double)autoTrader.getAvgPrice() * 
                (double)(veryLow + low + medium + high + veryHigh) 
                + Integer.parseInt(price)) / 
                (double)(veryLow + low + medium + high + veryHigh + 1)));

            analyzer.setAvgPrice(autoTrader.getAvgPrice());

            autoTrader.setAvgMileage
                ((int)(((double)autoTrader.getAvgMileage() * 
                (double)(veryLow + low + medium + high + veryHigh) 
                + Integer.parseInt(mileage)) / 
                (double)(veryLow + low + medium + high + veryHigh + 1)));

            analyzer.setMaxMileage(autoTrader.getMaxMileage());

            if (Integer.parseInt(price) < autoTrader.getMinPrice()) {
                autoTrader.setMinPrice(Integer.parseInt(price));
            } else if (Integer.parseInt(price) > autoTrader.getMaxPrice()) {
                autoTrader.setMaxPrice(Integer.parseInt(price));
            }

            if (Integer.parseInt(mileage) < autoTrader.getMinMileage()) {
                autoTrader.setMinMileage(Integer.parseInt(mileage));
            } else if (Integer.parseInt(mileage) > 
                    autoTrader.getMaxMileage()) {

                autoTrader.setMaxMileage(Integer.parseInt(mileage));
            }

            analyzer.setMinPrice(autoTrader.getMinPrice());
            analyzer.setMaxPrice(autoTrader.getMaxPrice());
            analyzer.setMinMileage(autoTrader.getMinMileage());
            analyzer.setAvgMileage(autoTrader.getAvgMileage());
        }
    }
    
    /**
     * Resets the selection to the first item in every list.
     */
    public static void listsReset() {
        if (!listModelVeryLow.isEmpty()) {
            listVeryLow.setSelectedIndex(0);
            buttonVeryLow.setEnabled(true);
        }

        if (!listModelLow.isEmpty()) {
            listLow.setSelectedIndex(0);
            buttonLow.setEnabled(true);
        }

        if (!listModelMedium.isEmpty()) {
            listMedium.setSelectedIndex(0);
            buttonMedium.setEnabled(true);
        }

        if (!listModelHigh.isEmpty()) {
            listHigh.setSelectedIndex(0);
            buttonHigh.setEnabled(true);
        }

        if (!listModelVeryHigh.isEmpty()) {
            listVeryHigh.setSelectedIndex(0);
            buttonVeryHigh.setEnabled(true);
        }
    }

    /**
     * Opens the currently selected listing in the default web browser.
     */
    public static void linkToCarListing() {
        try {
            URL linkToAutoTraderListing = new URL(selected.toString());
            Desktop.getDesktop().browse(linkToAutoTraderListing.toURI());
        } catch (MalformedURLException malEx) {
            System.err.println("This page could not be located. Please try "
                    + "updating the car listings. " + malEx.getMessage());

            JOptionPane.showMessageDialog(frame,
                    "There was a technical issue trying to open the "
                    + "default web browser using the appropriate URL.",
                    "AutoTrader Fraud Calculator",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (URISyntaxException uriEx) {
            System.err.println("A URI object could not be created using this "
                    + "URL. " + uriEx.getMessage());

            JOptionPane.showMessageDialog(frame,
                    "There was a technical issue trying to open the "
                    + "default web browser using the appropriate URI.",
                    "AutoTrader Fraud Calculator",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException ioEx) {
            System.err.println("There was an input/output related issue with "
                    + "the URL. " + ioEx.getMessage());

            JOptionPane.showMessageDialog(frame,
                    "There was a technical issue trying to open the "
                    + "default web browser that resulted in "
                    + "an input/output related problem.",
                    "AutoTrader Fraud Calculator",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Gets the details of the car advertisement and displays it on the
     * <code>listModelSpecs</code> panel.
     */
    public void getVeryLow() {
        selected.delete(0, selected.length());
        selected.append
                (listVeryLow.getSelectedValue().toString().split(" - ")[2]);
        
        listModelSpecs.clear();

        for (int i = 0; i < urlMap.get(selected.toString()).size(); i += 2) {
            listModelSpecs.addElement
                    (urlMap.get(selected.toString()).get(i) + ": " 
                    + urlMap.get(selected.toString()).get(i + 1));
        }

        makeVisibleAndComputeValues();
        graph.setVisible(true);
        graph.updateUI();
    }

    /**
     * Gets the details of the car advertisement and displays it on the
     * <code>listModelSpecs</code> panel.
     */
    public void getLow() {
        selected.delete(0, selected.length());
        selected.append(listLow.getSelectedValue().toString().split(" - ")[2]);
        listModelSpecs.clear();

        for (int i = 0; i < urlMap.get(selected.toString()).size(); i += 2) {
            listModelSpecs.addElement(urlMap.get(selected.toString()).get(i) 
                    + ": " + urlMap.get(selected.toString()).get(i + 1));
        }

        makeVisibleAndComputeValues();
        graph.setVisible(true);
        graph.updateUI();
    }

    /**
     * Gets the details of the car advertisement and displays it on the
     * <code>listModelSpecs</code> panel.
     */
    public void getMedium() {
        selected.delete(0, selected.length());
        selected.append
                (listMedium.getSelectedValue().toString().split(" - ")[2]);
        
        listModelSpecs.clear();

        for (int i = 0; i < urlMap.get(selected.toString()).size(); i += 2) {
            listModelSpecs.addElement(urlMap.get(selected.toString()).get(i) 
                    + ": " + urlMap.get(selected.toString()).get(i + 1));
        }

        makeVisibleAndComputeValues();
        graph.setVisible(true);
        graph.updateUI();
    }

    /**
     * Gets the details of the car advertisement and displays it on the
     * <code>listModelSpecs</code> panel.
     */
    public void getHigh() {
        selected.delete(0, selected.length());
        selected.append(listHigh.getSelectedValue().toString().split(" - ")[2]);
        listModelSpecs.clear();

        for (int i = 0; i < urlMap.get(selected.toString()).size(); i += 2) {
            listModelSpecs.addElement(urlMap.get(selected.toString()).get(i) 
                    + ": " + urlMap.get(selected.toString()).get(i + 1));
        }

        makeVisibleAndComputeValues();
        graph.setVisible(true);
        graph.updateUI();
    }

    /**
     * Gets the details of the car advertisement and displays it on the
     * <code>listModelSpecs</code> panel.
     */
    public void getVeryHigh() {
        selected.delete(0, selected.length());
        selected.append
                (listVeryHigh.getSelectedValue().toString().split(" - ")[2]);
        
        listModelSpecs.clear();

        for (int i = 0; i < urlMap.get(selected.toString()).size(); i += 2) {
            listModelSpecs.addElement(urlMap.get(selected.toString()).get(i) 
                    + ": " + urlMap.get(selected.toString()).get(i + 1));
        }

        makeVisibleAndComputeValues();
        graph.setVisible(true);
        graph.updateUI();
    }

    /**
     * Clears all information and sets the selection of the car make to the
     * first index.
     */
    public static void getReset() {
        comboCarMakes.setEnabled(true);
        comboCarMakes.setSelectedIndex(0);
        buttonCarModels.setEnabled(true);
        comboCarModels.setEnabled(false);
        comboCarModels.removeAllItems();
        buttonCarListings.setEnabled(false);
        listModelVeryLow.removeAllElements();
        listModelLow.removeAllElements();
        listModelMedium.removeAllElements();
        listModelHigh.removeAllElements();
        listModelVeryHigh.removeAllElements();
        listModelSpecs.removeAllElements();
        buttonVeryLow.setEnabled(false);
        buttonLow.setEnabled(false);
        buttonMedium.setEnabled(false);
        buttonHigh.setEnabled(false);
        buttonVeryHigh.setEnabled(false);
        dummyTitle.setEnabled(false);
        dummyPrice.setEnabled(false);
        dummyMileage.setEnabled(false);
        dummyPhone.setEnabled(false);
        dummyListingType.setEnabled(false);
        buttonDummyAdd.setEnabled(false);
        urlMap.clear();
        makeInvisible();
        progressBar.setVisible(false);
        progressBar.setValue(0);
        graph.setVisible(false);
        frame.setCursor(null);
    }

    /**
     * Adds a dummy listing to the application.
     */
    public static void dummyAdd() {                
        if (dummyTitle.getText().length() > 30 ||
            dummyTitle.getText().length() == 0 ||
            dummyPrice.getText().length() > 7 ||
            !dummyPrice.getText().matches("\\d+|") ||
            dummyMileage.getText().length() > 7 ||
            !dummyMileage.getText().matches("\\d+|")) {

            JOptionPane.showMessageDialog(frame,
                "Please make sure the following is satisfied:\n\n"
                + "1 ==> The title of the vehicle must be greater than "
                + "0 and less than 31 characters.\n\n"
                + "2 ==> The price must not exceed 7 digits and must "
                + "be expressed as an integer (without comma "
                + "separators). Use digits only.\n\n"
                + "3 ==> The mileage must not exceed 7 digits. Use "
                + "digits only.\n\n",
                "AutoTrader Fraud Calculator",
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            dummyUrlCounter++;
            int risk = 0;
            int numListings = listModelVeryLow.size() + 
                                listModelLow.size() + 
                                listModelMedium.size() +
                                listModelHigh.size() + 
                                listModelVeryHigh.size() + 1;
            
            String[] components = setupDummyListing();
            
            updatePriceAndMileageStats(components[0], components[1]);
            
            risk = analyzer.dummyRiskCalculate
                    (components[0], 
                    components[1], 
                    components[2], 
                    components[3], 
                    numListings);
            
            populateDummyListing(risk);            
            listsReset();
            dummyTitle.setText("");
            dummyPrice.setText("");
            dummyMileage.setText("");
            dummyPhone.setSelectedIndex(0);
            dummyListingType.setSelectedIndex(0);
        }
    }
    
    /**
     * Constructs the values of the dummy listing to be added with <code>
     * dummyAdd</code>
     * @return The price, mileage, phone availability, and listing type.
     */
    public static String[] setupDummyListing() {
        String price;
        String mileage;
        String phone;
        String listingType;
        String[] components = new String[4];
        
        urlMap.put("DUMMY" + dummyUrlCounter, new ArrayList<String>());
        urlMap.get("DUMMY" + dummyUrlCounter).add("URL");
        urlMap.get("DUMMY" + dummyUrlCounter).add
                ("DUMMY" + dummyUrlCounter);

        urlMap.get("DUMMY" + dummyUrlCounter).add("Title");
        urlMap.get("DUMMY" + dummyUrlCounter).add(dummyTitle.getText());
        urlMap.get("DUMMY" + dummyUrlCounter).add("Contact Number");

        if (dummyPhone.getSelectedIndex() == 0)
        {
            urlMap.get("DUMMY" + dummyUrlCounter).add
                    ("DUMMY" + dummyUrlCounter);

            phone = "YES";
        } else {
            urlMap.get("DUMMY" + dummyUrlCounter).add
                    ("N/A");

            phone = "N/A";
        }

        urlMap.get("DUMMY" + dummyUrlCounter).add("Mileage");

        if (dummyMileage.getText().contentEquals(""))
        {
            urlMap.get("DUMMY" + dummyUrlCounter).add
                    ("N/A");

            mileage = "N/A";
        } else {
            urlMap.get("DUMMY" + dummyUrlCounter).add
                    (dummyMileage.getText());

            mileage = dummyMileage.getText();
        }

        urlMap.get("DUMMY" + dummyUrlCounter).add("Price");

        if (dummyPrice.getText().contentEquals(""))
        {
            urlMap.get("DUMMY" + dummyUrlCounter).add
                    ("N/A");

            price = "N/A";
        } else {
            urlMap.get("DUMMY" + dummyUrlCounter).add
                    (dummyPrice.getText());

            price = dummyPrice.getText();
        }

        urlMap.get("DUMMY" + dummyUrlCounter).add("Type of Listing");

        if (dummyListingType.getSelectedIndex() == 0)
        {
            urlMap.get("DUMMY" + dummyUrlCounter).add
                    ("Dealership");

            listingType = "Dealership";
        } else {
            urlMap.get("DUMMY" + dummyUrlCounter).add
                    ("Private Listing");

            listingType = "Private Listing";
        }
        
        components[0] = price;
        components[1] = mileage;
        components[2] = phone;
        components[3] = listingType;
        return components;
    }
    
    /**
     * Adds the dummy listing to one of the five lists.
     * @param risk 
     */
    public static void populateDummyListing(int risk) {
        if (risk >= 0 && risk <= 19) {
            veryLow++;
            listModelVeryLow.addElement(veryLow + " - " + 
                dummyTitle.getText() + " - " + "DUMMY" + dummyUrlCounter);

            JOptionPane.showMessageDialog(frame,
                "Added to VERY LOW risk list.",
                "AutoTrader Fraud Calculator",
                JOptionPane.INFORMATION_MESSAGE);

        } else if (risk >= 20 && risk <= 39) {
            low++;
            listModelLow.addElement(low + " - " + 
                dummyTitle.getText() + " - " + "DUMMY" + dummyUrlCounter);

            JOptionPane.showMessageDialog(frame,
                "Added to LOW risk list.",
                "AutoTrader Fraud Calculator",
                JOptionPane.INFORMATION_MESSAGE);

        } else if (risk >= 40 && risk <= 59) {
            medium++;
            listModelMedium.addElement(medium + " - " + 
                dummyTitle.getText() + " - " + "DUMMY" + dummyUrlCounter);

            JOptionPane.showMessageDialog(frame,
                "Added to MEDIUM risk list.",
                "AutoTrader Fraud Calculator",
                JOptionPane.INFORMATION_MESSAGE);

        } else if (risk >= 60 && risk <= 79) {
            high++;
            listModelHigh.addElement(high + " - " + 
                dummyTitle.getText() + " - " + "DUMMY" + dummyUrlCounter);

            JOptionPane.showMessageDialog(frame,
                "Added to HIGH risk list.",
                "AutoTrader Fraud Calculator",
                JOptionPane.INFORMATION_MESSAGE);

        } else if (risk >= 80 && risk <= 100) {
            veryHigh++;
            listModelVeryHigh.addElement(veryHigh + " - " + 
                dummyTitle.getText() + " - " + "DUMMY" + dummyUrlCounter);

            JOptionPane.showMessageDialog(frame,
                "Added to VERY HIGH risk list.",
                "AutoTrader Fraud Calculator",
                JOptionPane.INFORMATION_MESSAGE);
        }
        
        urlMap.get("DUMMY" + dummyUrlCounter).add("Risk");
        urlMap.get("DUMMY" + dummyUrlCounter).add(String.valueOf(risk));

        if (graph.isVisible()) {
            graph.updateUI();
            makeVisibleAndComputeValues();
        }
    }
    
    /**
     * Controls the events in the GUI.
     * 
     * @param e An event that happened.
     * @throws NullPointerException
     */
    @Override
    public void actionPerformed(ActionEvent e) throws NullPointerException {
        String command = e.getActionCommand();

        if (command.equals(GET_CAR_MODELS)) {
            getCarModels();
        } else if (command.equals(GET_CAR_LISTINGS)) {
            getCarListings();
        } else if (command.equals(GET_VERY_LOW_LISTINGS)) {
            getVeryLow();
        } else if (command.equals(GET_LOW_LISTINGS)) {
            getLow();
        } else if (command.equals(GET_MEDIUM_LISTINGS)) {
            getMedium();
        } else if (command.equals(GET_HIGH_LISTINGS)) {
            getHigh();
        } else if (command.equals(GET_VERY_HIGH_LISTINGS)) {
            getVeryHigh();
        } else if (command.equals(VIEW_LINK)) {
            linkToCarListing();
        } else if (command.equals(RESET)) {
            getReset();
        } else if (command.equals(RISK_EXPLAIN)) {
            JOptionPane.showMessageDialog(frame,
                    "Risk is determined by the following factors:\n\n"
                    + "1 ==> Popularity of the vehicle model.\n\n"
                    + "2 ==> Phone number availability.\n\n"
                    + "3 ==> Listing type is a 'private listing' or "
                    + "'dealership'. In the case of a dealership, there is\n"
                    + "no risk for this metric. In the case of a private "
                    + "listing, there is risk when either no price or mileage "
                    + "is present for\n the vehicle specification.\n\n"
                    + "4 ==> How far away the price and mileage are from the "
                    + "average of all listings of that car model.",
                    "AutoTrader Fraud Calculator",
                    JOptionPane.INFORMATION_MESSAGE);
        } else if (command.equals(DUMMY_ADD)) {
            dummyAdd();
        }
    }
    
    /**
     * Sets the progress on the progress bar when this detects a change in
     * a bound property.
     * @param evt A <code>PropertyChangeEvent</code> object describing the event
     *            source and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().contentEquals("progress")) {
            int progress = (Integer) evt.getNewValue();
            progressBar.setValue(progress);
        }
    }
    
    /**
     * Draws the graph representation of all the car listings of a particular
     * make and model.
     * 
     * The blue dot represents the selected listing and the grey dot represents
     * the average price&milage.
     */
    public static class Graphing extends JLabel {
        
        /**
         * Paints the graph in which all dots represent a single advertisement.
         * @param g The <code>Graphics</code> object that is used to represent
         *          the painting.
         */
        @Override
        public void paint(Graphics g) {
            int listingPrice = -1;
            int listingMileage = -1;

            try {
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, 750, 350);
                g.setColor(Color.MAGENTA);
                g.fillRect(0, 0, 3, 400);
                g.fillRect(0, 347, 750, 3);
                
                g.setColor(Color.RED);
                for (int k = 0; k < priceAndMileage.size(); k += 2) {
                    g.fillRect((int) ((double)priceAndMileage.get(k + 1)
                        / ((double)autoTrader.getMaxMileage() / 725)),
                        (int) (350 - ((double)priceAndMileage.get(k)
                        / ((double)autoTrader.getMaxPrice() / 325)) - 5),
                        5, 5);
                }

                g.setColor(Color.GRAY);
                g.fillRect((int) ((double)autoTrader.getAvgMileage()
                    / ((double)autoTrader.getMaxMileage() / 725)),
                    (int) (350 - ((double)autoTrader.getAvgPrice()
                    / ((double)autoTrader.getMaxPrice() / 325)) - 5), 
                    5, 5);

                g.setColor(Color.BLUE);
                for (int i = 0; i < urlMap.get(selected.toString()).size(); 
                        i += 2) {
                    
                    if ((urlMap.get(selected.toString()).get(i).contentEquals
                        ("Price")) && !(urlMap.get(selected.toString()).
                        get(i + 1).contentEquals("N/A"))) {
                        
                        listingPrice = Integer.parseInt
                                (urlMap.get(selected.toString()).get(i + 1));
                    }

                    if ((urlMap.get(selected.toString()).get(i).contentEquals
                        ("Mileage")) && !(urlMap.get(selected.toString()).
                        get(i + 1).contentEquals("N/A"))) {
                        
                        listingMileage = Integer.parseInt
                                (urlMap.get(selected.toString()).
                                get(i + 1).split(" ")[0]);
                    }
                }

                if (listingMileage != -1 && listingPrice != -1) {
                    g.fillRect((int) ((double)listingMileage
                        / ((double)autoTrader.getMaxMileage() / 725)),
                        (int) (350 - ((double)listingPrice
                        / ((double)autoTrader.getMaxPrice() / 325)) - 5),
                        5, 5);
                }
            } catch (NullPointerException e) {
                //Do nothing here. No further action is necessary.
            }
        }
    }
    
    /**
     * Allows a background process to be run (for the progress bar) in
     * synchronization with the <code>getCarListings</code> function.
     */
    public static class TaskUpdate extends SwingWorker<Void, Void> {
        /**
         * Executes as a separate task in the background. This is the
         * progress bar updating every second.
         */
        @Override
        public Void doInBackground() {            
            int currentProgress = 0;
            int oldProgress = 0;
            setProgress(0);
            progressBar.setVisible(true);
            Thread.currentThread().setPriority(Thread.NORM_PRIORITY);
            
            while (currentProgress < 100) {
                currentProgress = (int)(autoTrader.getCurrentLoadingValue() 
                        / autoTrader.getNumListings(carModel.toString()) * 100);
                
                if (currentProgress > oldProgress) {
                    oldProgress = currentProgress;
                    setProgress(currentProgress);
                }
            }
            return null;
        }

        /**
         * Executes in event dispatching thread. This happens after the progress
         * bar is complete (at 100%). Calls the function <code>displayListings
         * </code> to read the XML file that was outputting when <code>
         * downloadInformation</code> was used.
         */
        @Override
        public void done() {
            try {
                File file = new File(CURRENT_DIRECTORY 
                           + FILE_SEPARATOR + carMake + carModel + ".xml");
                
                if (!file.exists()) {
                    throw new IOException();
                }
                
                displayListings(CURRENT_DIRECTORY 
                                + FILE_SEPARATOR + carMake + carModel + ".xml");
            } catch (IOException e) {
                System.err.println("TASK UPDATE: "
                        + "There was a problem trying to read data "
                        + "from a file. " + e.getMessage());
                
                JOptionPane.showMessageDialog(frame,
                    "A problem occured while trying to obtain data. The request"
                        + " was cancelled.",
                    "AutoTrader Fraud Calculator",
                    JOptionPane.INFORMATION_MESSAGE);
            }
            
            buttonReset.setEnabled(true);
            progressBar.setValue(100);
            frame.setCursor(null);
            Toolkit.getDefaultToolkit().beep();
        }
    }

    public static void main(String[] args) {
        frame = new JFrame("AutoTrader Fraud Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        GraphicalUserInterface contentPane = new GraphicalUserInterface();
        frame.setVisible(true);
    }
}