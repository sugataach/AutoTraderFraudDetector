package AutoTraderFraudCalculator;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Links the classes <code>Analysis</code> and <code>Listing</code> together in 
 * order to become a object that is able to access the <code>autotrader.ca
 * </code> website and retrieves all the listings of a particular make and 
 * model, and then analyzes the data in order to output a risk factor. This 
 * risk factor calculates the percentage that a certain car ad is a scam or not.
 * In addition, this class is able to output the data used in an XML file for
 * further analysis.
 * 
 */
public class AutoTraderFraudCalculator {

    /**
     * Reads URL pages that is setup with <code>setupPageReader</code>.
     */
    private static Scanner pageReader;
    
    /**
     * Base website constant that this class uses.
     */
    private static final String AUTO_TRADER = "http://www.autotrader.ca";
    
    /**
     * Currently loaded number of listings.
     */
    private static int currentLoadingValue = 0;
    
    /**
     * Number of listing advertisements per model.
     */
    private static Map<String, Integer> modelsToQuantity;
    
    /**
     * Minimum price of all the listings of a make and model.
     */
    private static int minPrice;
    
    /**
     * Average price of all the listings of a make and model.
     */
    private static int avgPrice;
    
    /**
     * Maximum price of all the listings of a make and model.
     */
    private static int maxPrice;
    
    /**
     * Minimum mileage of all the listings of a make and model.
     */
    private static int minMileage;
    
    /**
     * Average mileage of all the listings of a make and model.
     */
    private static int avgMileage;
    
    /**
     * Maximum mileage of all the listings of a make and model.
     */
    private static int maxMileage;

    /**
     * Initializes the <code>Scanner pageReader</code> to read the URL
     * specified.
     * 
     * @param url A <code>String</code> representation of the URL to read.
     */
    public void setupPageReader(String url) {
        try {
            
            //Open the given URL and open in the Scanner "pageReader".
            URL linkToAutoTrader = new URL(url);
            pageReader = new Scanner(linkToAutoTrader.openStream());
        }
                
        //If no legal protocol could be found in a specification string, 
        //or the string could not be parsed, throw exception.
        catch (MalformedURLException e) {
            System.err.println(e.getMessage());
        }
                
        //Throw an exception for general I/O error.
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Retrieves a list of makes from the <code>AUTO_TRADER</code> site.
     * 
     * @return An <code>ArrayList<String></code> of makes.
     */
    public ArrayList<String> getCarMakes() {
        
        //Declare variables to be used in method.
        boolean makesComing = false;
        boolean doneExtracting = false;
        List<String> makes = new ArrayList<String>();
        StringBuilder lineText = new StringBuilder();
        StringBuilder make = new StringBuilder();

        //Scan the Auto Trader site.
        setupPageReader(AUTO_TRADER);
        
        //While there are more lines, and boolean "doneExtracting" is False:
        while (pageReader.hasNext() && !doneExtracting) {
            
            //"lineText" is the next read line.
            lineText.delete(0, lineText.length());
            lineText.append(pageReader.nextLine());

            //If the line contains this phrase, change boolean "makesComing".
            if (lineText.toString().contains
                    ("A huge array of makes to choose from in")) {
                
                makesComing = true;
            } 
                    
            //Else if "makesComing" is True, split it according to the regular
            //expression and add it to the List of makes.
            else if (makesComing) {
                if (lineText.toString().contains("&nbsp;")) {
                    make.delete(0, make.length());
                    make.append(lineText.toString().split("&nbsp;|6\">")[1]);
                    makes.add(make.toString());
                }
            }

            //Finally, if the line contains the following phrase, exit the
            //while loop by changing the boolean values.
            if (lineText.toString().contains("What model are you after?")) {
                makesComing = false;
                doneExtracting = true;
            }
        }

        //Close scanner and return List.
        pageReader.close();
        return (new ArrayList<String>(makes));
    }

    /**
     * Retrieve a map of models and the respective URLs for the models.
     * @param carMake The particular car make to search through.
     * @return A <code>TreeMap</code> representation with models as keys and 
     *         URLs as values.
     */
    public TreeMap<String, String> getCarModels(String carMake) {
        
        //Declare variables to be used in method.
        Map<String, String> modelsToUrl = new TreeMap<String, String>();
        modelsToQuantity = new TreeMap<String, Integer>();
        int numListings = 0;
        StringBuilder lineText = new StringBuilder();
        StringBuilder model = new StringBuilder();
        StringBuilder modelUrl = new StringBuilder();
        StringBuilder makeUrl = new StringBuilder();
        
        makeUrl.append(AUTO_TRADER);
        makeUrl.append("/Ajax/Models.aspx?cat2=7%2c11%2c9%2c10&"
                + "prv=Ontario&r=40&rprv=True&prx="
                + "0&sts=Used&make=");
                
        makeUrl.append
                (carMake.replaceAll("\\s+", "+").replaceAll("&#\\d+;", "+"));
        
        //Setup scanner.
        setupPageReader(makeUrl.toString());
        while (pageReader.hasNext()) {
            lineText.delete(0, lineText.length());
            lineText.append(pageReader.nextLine());

            //If the String variable "lineText" contains the selected phrase,
            //split it using the appropriate regular expressions, and store the
            //final values (which are the model names and respective URL) in
            //the Map, "modelsToUrl".
            if (lineText.toString().contains("&nbsp;")) {
                String quantity = lineText.toString().split
                        ("&nbsp;\\(|\\)</a>")[1];
                
                StringBuilder quantityString = new StringBuilder();
                model.delete(0, model.length());
                model.append(lineText.toString().split("&nbsp;|6\">")[1]);
                
                for (int k = 0; k < quantity.length(); k++) {
                    if (!quantity.substring(k, k + 1).contentEquals(",")) {
                        quantityString.append(quantity.substring(k, k + 1));
                    }
                }
                
                numListings = Integer.parseInt(quantityString.toString());
                modelUrl.delete(0, modelUrl.length());
                modelUrl.append(lineText.toString().split("\"")[5]
                        .replaceAll("amp;", "")
                        .replaceAll("\\s+", "+")
                        .replaceAll("&#\\d+;", "+"));
                
                modelsToUrl.put(model.toString(), modelUrl.toString());
                modelsToQuantity.put(model.toString(), numListings);
            }
        }

        //Close scanner and return Map.
        pageReader.close();
        return (new TreeMap<String, String>(modelsToUrl));
    }

    /**
     * Downloads and accesses the car listings for a particular make and model, 
     * then stores them in a list. Passes this list to 
     * <code>getCarSpecifications</code> along with the car made and model.
     * 
     * @param carMake The name of a car make.
     * @param carModel The name of a car model.
     * @throws IOException 
     * @see #getCarSpecifications(java.util.ArrayList, java.lang.String, 
     *                            java.lang.String)
     */
    public void downloadCarListings(String carMake, String carModel)
            throws IOException {
        
        //Declare variables to be used in method.
        CarListings carAds = new CarListings();
        List<String> uniqueUrls = new ArrayList<String>();
        Listing currentListing;
        int pageNum = 1;
        int infoPieces = 0;
        int curLength = 0;
        boolean adTitleComing = false;
        boolean sifted = false;
        boolean listingTypeComing = false;
        StringBuilder lineText = new StringBuilder();
        StringBuilder nextPageUrl = new StringBuilder();
        StringBuilder adTitle = new StringBuilder();
        StringBuilder adUrl = new StringBuilder();
        StringBuilder listingType = new StringBuilder();
        currentLoadingValue = 0;

        //Setup a scanner which accesses the page with the car listings of 
        //a particular make and model.
        setupPageReader(AUTO_TRADER + getCarModels(carMake).get(carModel));
        while (pageReader.hasNext()) {
            lineText.delete(0, lineText.length());
            lineText.append(pageReader.nextLine());
            
            //While the scanned line contains the selected phrases and the
            //boolean "sifted" is True: read the next line.
            while (!lineText.toString().contains("Priority Listings")
                && !lineText.toString().contains("Search Results") && !sifted) {
                
                lineText.delete(0, lineText.length());
                lineText.append(pageReader.nextLine());
            }

            sifted = true;

            //If the boolean "adTitleComing" is True and the List "uniqueUrls"
            //does not conatain the String "adUrl", there is no ad title coming.
            if (adTitleComing && !uniqueUrls.contains(adUrl.toString())) {
                
                //Create a new currentListing and set the Title to "adTitle" and
                //URL to "adUrl".
                adTitleComing = false;
                adTitle.delete(0, adTitle.length());
                adTitle.append(lineText.toString().trim().replaceAll("-+", ""));
                currentListing = new Listing();
                currentListing.setTitle(adTitle.toString());
                currentListing.setUrl(adUrl.toString());

                //By default the listing type is "Dealership".
                if (listingType.toString().contentEquals("")) {
                    listingType.append("Dealership");
                }

                //For the specification of the Listing object, add the
                //key-value pair.
                currentListing.setSpecification
                        ("Type of Listing:", listingType.toString());

                //Add the "adUrl" to the list of "uniqueUrls", and push the
                //"currentListing" to the CarListings object "carAds".
                uniqueUrls.add(adUrl.toString());
                carAds.pushAdvertisement(currentListing);
                listingType.delete(0, listingType.length());
                currentLoadingValue++;
            }
                    
            //Else if the line contains the selected text:
            else if (lineText.toString().contains("class=\"carlink\">")) {
                
                //Change "adTitleComing" and format "adUrl" (to get the URL).
                adTitleComing = true;
                adUrl.delete(0, adUrl.length());
                adUrl.append(AUTO_TRADER);
                adUrl.append(lineText.toString().split("\"")[1]
                        .replaceAll("\\s+", "+")
                        .replaceAll("&#\\d+;", "+"));
            } 
            
            //Else if listingTypeComing is True:
            else if (listingTypeComing) {
                
                //If the trimmed "lineText" equals the selected phrase, set the
                //selected phrase to the listingType and change
                //listingTypeComing to False.
                if (lineText.toString().trim().contentEquals
                        ("Private Listing")) {
                    
                    listingType.delete(0, listingType.length());
                    listingType.append("Private Listing");
                    listingTypeComing = false;
                }
            } 
            
            //Else if "lineText" contians the selected phrase, change 
            //listingTypeComing to True.
            else if (lineText.toString().contains("pnlPrivate\">")) {
                listingTypeComing = true;
            } 
            
            //Else if "lineText" contians the selected phrase, change 
            //listingTypeComing to False.
            else if (listingTypeComing && lineText.toString().
                    contains("</div>")) {
                
                listingTypeComing = false;
            } 
            
            //Else if "lineText" contians the selected phrase:
            else if (lineText.toString().contains("\">Next &rsaquo;")) {
                //Close the scanner, setup a new scanner by retrieving the new
                //URL by formatting the current URL, allowing to continue to 
                //parse the next webpage.
                
                pageReader.close();
                sifted = false;
                pageNum++;
                nextPageUrl.delete(0, nextPageUrl.length());
                nextPageUrl.append(lineText.toString().
                        split("\">Next &rsaquo;")[0]);
                
                infoPieces = nextPageUrl.toString().split("\"").length;
                curLength = nextPageUrl.length();
                nextPageUrl.append
                        (nextPageUrl.toString().split("\"")[infoPieces - 1]);
                
                nextPageUrl.delete(0, curLength);
                nextPageUrl.insert(0, AUTO_TRADER);
                curLength = nextPageUrl.length();
                nextPageUrl.append(nextPageUrl.toString().replaceAll("amp;", "")
                        .replaceAll("\\s+", "+")
                        .replaceAll("&#\\d+;", "+"));
                
                nextPageUrl.delete(0, curLength);
                setupPageReader(nextPageUrl.toString());
            }
        }

        //Close scanner and continue to get the vehicle specifications, using
        //the following method.
        pageReader.close();
        getCarSpecifications(new CarListings(carAds), carMake, carModel);
    }

    /**
     * Accesses each car ad in <code>carAds</code> and retrieves the car
     * specifications from the ad. Stores the specifications in local instance
     * variables and calls <code>writeToXml(carAds, carMake, carModel)</code>
     * with carAds including the retrieved specifications.
     * 
     * @param carAds A list of <code>Listings</code> that need specification
     *               information.
     * @param carMake The name of the car make.
     * @param carModel The name of the car model.
     * @throws IOException 
     * @see #writeToXml(java.util.ArrayList, java.lang.String, java.lang.String) 
     */
    public void getCarSpecifications(CarListings carAds, String carMake,
            String carModel) throws IOException {
        
        //Declare variables to used in the method.
        boolean specsComing = false;
        boolean priceComing = false;
        boolean priceFound = false;
        boolean phoneNumComing = false;
        boolean phoneNumFound = false;
        boolean sifted = false;
        boolean doneExtracting = false;
        StringBuilder specType = new StringBuilder();
        StringBuilder specValue = new StringBuilder();
        StringBuilder lineText = new StringBuilder();
        StringBuilder nextUrl = new StringBuilder();
        StringBuilder price = new StringBuilder();
        StringBuilder mileage = new StringBuilder();
        StringBuilder precPhoneNum = new StringBuilder();
        Analysis analyzation = new Analysis();

        //Iterate through the List "carAds".
        for (int i = 0; i < carAds.getSize(); i++) {
            
            //For each URL in the List "carAds", setup the scanner and declare
            //relevant variables.
            nextUrl.delete(0, nextUrl.length());
            nextUrl.append(carAds.getAdvertisement(i).getUrl());
            setupPageReader(nextUrl.toString());
            specsComing = false;
            priceComing = false;
            priceFound = false;
            phoneNumComing = false;
            phoneNumFound = false;
            sifted = false;
            doneExtracting = false;

            //While the scanner has a next line and the boolean
            //"doneExtracting" is False:
            while (pageReader.hasNext() && !doneExtracting) {
                lineText.delete(0, lineText.length());
                lineText.append(pageReader.nextLine());
                
                //While the line contains the selected phrase and the boolean 
                //"sifted" is False, read the next line.
                while (!lineText.toString().contains("<!-- Detail info -->") 
                        && !sifted && pageReader.hasNext()) {
                    
                    lineText.delete(0, lineText.length());
                    lineText.append(pageReader.nextLine());
                }

                sifted = true;

                //If the boolean "specsComing" is True and the line contains
                //the selected phrase:
                if (specsComing && lineText.toString().contains("<strong>")) {
                    
                    //Extract the "specType" by parsing the line.
                    specType.delete(0, specType.length());
                    specType.append(lineText.toString().split
                        ("<strong>| </strong>|<br/>")[1].replaceAll("-+", ""));

                    //If the "specType" contains the selected phrase:
                    if (specType.toString().contentEquals("Mileage:")) {
                        
                        //Set "specValue" to empty string and extract the
                        //mileage by parsing the line.
                        specValue.delete(0, specValue.length());
                        mileage.delete(0, mileage.length());
                        
                        if (lineText.toString().contains
                                ("</strong>[( kms)(kms)]*<br/>")) {
                            
                            specValue.append("N/A");
                        } else {
                            mileage.append(lineText.toString().split
                                    ("<strong>| </strong>|<br/>")[2].replaceAll
                                    ("^-+", ""));
                            
                            //For each substring of "mileage", if the substring
                            //matches the selected phrase, add it to the 
                            //"specValue".
                            for (int k = 0; k < mileage.length(); k++) {
                                if (mileage.substring(k, k + 1).matches
                                        ("[0-9 kms]")) {

                                    specValue.append
                                            (mileage.substring(k, k + 1));
                                }
                            }
                        }

                        //If the "specValue" substring from 0 to 1 characters
                        //matches the selected phrase set the "specValue" to
                        //"N/A".
                        if (!specValue.substring(0, 1).matches("\\d")) {
                            specValue.delete(0, specValue.length());
                            specValue.append("N/A");
                        }
                    } 
                    
                    //Else, parse the line to get the "specValue".
                    else {
                        specValue.delete(0, specValue.length());
                        
                        if (lineText.toString().contains("</strong><br/>")) {                   
                            specValue.append("N/A");
                        } else {
                            specValue.append(lineText.toString().split
                                    ("<strong>| </strong>|<br/>")[2].replaceAll
                                    ("^-+", ""));
                        }
                    }

                    //For the particular vehicle advertisement set the
                    //specfication type and value according to the values
                    //retrieved.
                    carAds.getAdvertisement(i).setSpecification
                            (specType.toString(), specValue.toString());
                    
                } else if (lineText.toString().contains
                        ("class=\"d_title\">Technical")) {
                    
                    specsComing = true;
                } else if (specsComing && lineText.toString().contains
                        ("</table>")) {
                    
                    specsComing = false;
                } else if (lineText.toString().contains(">Price:")) {
                    priceComing = true;
                } 
                
                //Else if "priceComing" is True and the line contains the
                //selected phrase:
                else if (priceComing && lineText.toString().contains("\">$")) {
                    
                    //Extract the specValue, by parsing the line and set 
                    //specType to "Price"
                    priceComing = false;
                    priceFound = true;
                    specType.delete(0, specType.length());
                    specType.append("Price:");
                    specValue.delete(0, specValue.length());
                    specValue.append(lineText.toString().split
                            ("\\$|</span></td>")[1]);
                    
                    price.delete(0, price.length());

                    for (int k = 0; k < specValue.length(); k++) {
                        if (!specValue.substring(k, k + 1).contains(",")) {
                            price.append(specValue.substring(k, k + 1));
                        }
                    }

                    specValue.delete(0, specValue.length());
                    specValue.append(price.toString());

                    if (specValue.toString().contains(".")) {
                        specValue.delete(specValue.length() - 3, 
                                specValue.length());
                    }

                    price.delete(0, price.length());
                    carAds.getAdvertisement(i).setSpecification
                            (specType.toString(), specValue.toString());
                    
                } else if (lineText.toString().contains
                        ("<div class=\"contact_seller\">")) {
                    
                    phoneNumComing = true;

                    if (!priceFound) {
                        specType.delete(0, specType.length());
                        specType.append("Price:");
                        specValue.delete(0, specValue.length());
                        specValue.append("N/A");
                        carAds.getAdvertisement(i).setSpecification
                                (specType.toString(), specValue.toString());
                    }
                } else if (!phoneNumFound && lineText.toString().matches
                        ("^.*\\d\\d\\d-\\d\\d\\d-\\d\\d\\d\\d.*$")) {
                    
                    phoneNumFound = true;
                    specType.delete(0, specType.length());
                    specType.append("Contact Number:");
                    precPhoneNum.delete(0, precPhoneNum.length());
                    precPhoneNum.append(lineText.toString().split
                            ("\\d\\d\\d-\\d\\d\\d-\\d\\d\\d\\d")[0]);

                    specValue.delete(0, specValue.length());
                    specValue.append(lineText.substring(precPhoneNum.length(),
                            precPhoneNum.length() + 12));

                    carAds.getAdvertisement(i).setSpecification
                            (specType.toString(), specValue.toString());
                    
                } else if (phoneNumComing && lineText.toString().contains
                        ("</div>")) {
                    
                    doneExtracting = true;

                    if (!phoneNumFound) {
                        specType.delete(0, specType.length());
                        specType.append("Contact Number:");
                        specValue.delete(0, specValue.length());
                        specValue.append("N/A");
                        carAds.getAdvertisement(i).setSpecification
                                (specType.toString(), specValue.toString());
                    }

                    if (!carAds.getAdvertisement(i).containsSpecificationType
                            ("Mileage:")) {
                        
                        specType.delete(0, specType.length());
                        specType.append("Mileage:");
                        specValue.delete(0, specValue.length());
                        specValue.append("N/A");
                        carAds.getAdvertisement(i).setSpecification
                                (specType.toString(), specValue.toString());
                    }
                }
            }

            pageReader.close();
            currentLoadingValue++;
            Thread.currentThread().setPriority(Thread.NORM_PRIORITY);
        }

        carAds = analyzation.initCalculations(new CarListings(carAds));
        minPrice = analyzation.getMinPrice();
        avgPrice = analyzation.getAvgPrice();
        maxPrice = analyzation.getMaxPrice();
        minMileage = analyzation.getMinMileage();
        avgMileage = analyzation.getAvgMileage();
        maxMileage = analyzation.getMaxMileage();
        writeToXml(carAds, carMake, carModel);
        currentLoadingValue++;
    }

    /**
     * Temporary method to write a pseudo XML file.
     * 
     * @param carAds The list of car ads with the specifications added.
     * @param carMake The car make that the ads came from.
     * @param carModel The car model that was being accessed
     * @throws IOException 
     */
    public void writeToXml(CarListings carAds, String carMake,
            String carModel) throws IOException {
        
        String currentDirectory = System.getProperty("user.dir");
        String fileSeparator = System.getProperty("file.separator");

        XMLHandler xmlh = new XMLHandler();
        xmlh.writeXMLDocument(xmlh.buildXMLDocument(carAds), currentDirectory 
                              + fileSeparator + carMake + carModel + ".xml");
    }

    /**
     * Retrieves the minimum price of all the listings of a particular car make 
     * and car model (the one that <code>downloadCarListings</code> was called
     * with by default).
     * 
     * @return The minimum price of all the listings of the car make and model.
     */
    public int getMinPrice() {
        return minPrice;
    }

    /**
     * Retrieves the average price of all the listings of a particular car make 
     * and car model (the one that <code>downloadCarListings</code> was called
     * with by default).
     * 
     * @return The average price of all the listings of the car make and model.
     */
    public int getAvgPrice() {
        return avgPrice;
    }

    /**
     * Retrieves the maximum price of all the listings of a particular car make 
     * and car model (the one that <code>downloadCarListings</code> was called
     * with by default).
     * 
     * @return The maximum price of all the listings of the car make and model.
     */
    public int getMaxPrice() {
        return maxPrice;
    }

    /**
     * Retrieves the minimum mileage of all the listings of a particular car 
     * make and car model (the one that <code>downloadCarListings</code> was 
     * called with by default).
     * 
     * @return The minimum mileage of all the listings of the car make and 
     *         model.
     */
    public int getMinMileage() {
        return minMileage;
    }

    /**
     * Retrieves the average mileage of all the listings of a particular car 
     * make and car model (the one that <code>downloadCarListings</code> was 
     * called with by default).
     * 
     * @return The average mileage of all the listings of the car make and 
     *         model.
     */
    public int getAvgMileage() {
        return avgMileage;
    }

    /**
     * Retrieves the maximum mileage of all the listings of a particular car 
     * make and car model (the one that <code>downloadCarListings</code> was
     * called with by default).
     * 
     * @return The maximum mileage of all the listings of the car make and 
     *         model.
     */
    public int getMaxMileage() {
        return maxMileage;
    }

    /**
     * Sets the minimum price of all the listings of a particular car make 
     * and car model.
     * 
     * @param minPriced The minimum price to set it to.
     */
    public void setMinPrice(int minPriced) {
        minPrice = minPriced;
    }

    /**
     * Sets the average price of all the listings of a particular car make 
     * and car model.
     * 
     * @param avgPriced The average price to set it to.
     */
    public void setAvgPrice(int avgPriced) {
        avgPrice = avgPriced;
    }

    /**
     * Sets the maximum price of all the listings of a particular car make 
     * and car model.
     * 
     * @param maxPriced The maximum price to set it to.
     */
    public void setMaxPrice(int maxPriced) {
        maxPrice = maxPriced;
    }

    /**
     * Sets the minimum mileage of all the listings of a particular car make 
     * and car model.
     * 
     * @param minPriced The minimum mileage to set it to.
     */
    public void setMinMileage(int minMileaged) {
        minMileage = minMileaged;
    }

    /**
     * Sets the average mileage of all the listings of a particular car make 
     * and car model.
     * 
     * @param avgPriced The average mileage to set it to.
     */
    public void setAvgMileage(int avgMileaged) {
        avgMileage = avgMileaged;
    }

    /**
     * Sets the maximum mileage of all the listings of a particular car make 
     * and car model.
     * 
     * @param maxPriced The maximum mileage to set it to.
     */
    public void setMaxMileage(int maxMileaged) {
        maxMileage = maxMileaged;
    }
    
    /**
     * Returns the number of listings that were processed for a certain model.
     * 
     * @param model The model to look at.
     * @return The number of listings for this model.
     */
    public double getNumListings(String model) {
        return ((modelsToQuantity.get(model) * 2) + 1);
    }
    
    /**
     * Returns the current amount of listings that were processed.
     * 
     * @return The number of processed listings.
     */
    public double getCurrentLoadingValue() {
        return currentLoadingValue;
    }
}