package AutoTraderFraudCalculator;

import java.util.*;
import org.jdom.Document;
import org.jdom.Element;

/**
 * Provides methods of analysis for calculating the risk of a listing on
 * <code>autotrader.ca</code> of being a scam or not. Calculations are based on
 * a sales clerk's experience and expertise toward the manner.
 * 
 * <p> The required object needed for analysis is a <code>CarListings</code> 
 * object {@link CarListings}. There is no constructor classes for <code>
 * Analysis</code> but in order to do calculations, <code>initCalculations
 * </code> must be called.
 * 
 * <p> @TODO Exceptions
 */
public class Analysis {

    /**
     * Stores the average price of all the listings that <code>initCalculations
     * </code> was called with.
     */
    private static int avgPrice;
    /**
     * Stores the maximum price of all the listings that <code>initCalculations
     * </code> was called with.
     */
    private static int maxPrice;
    /**
     * Stores the minimum price of all the listings that <code>initCalculations
     * </code> was called with.
     */
    private static int minPrice;
    /**
     * Stores the average mileage of all the listings that <code>
     * initCalculations</code> was called with.
     */
    private static int avgMileage;
    /**
     * Stores the maximum mileage of all the listings that <code>
     * initCalculations</code> was called with.
     */
    private static int maxMileage;
    /**
     * Stores the minimum mileage of all the listings that <code>
     * initCalculations</code> was called with.
     */
    private static int minMileage;
    /**
     * Stores the weight associated with having a phone number or not. The
     * values that are possible are <code>0</code> and <code>25</code>.
     */
    private static int phoneNumRisk; // maxweight: 25
    /**
     * Stores the weight associated with the price. Takes into account the
     * mileage and price information and calculates according to a metric. The
     * values can range between <code>0</code> and <code>55</code>.
     */
    private static int priceRisk; // maxweight: 55
    /**
     * Stores the weight associated with popularity risk. To be specific, the
     * less listings there are, the less accurate the total risk will be. The
     * values can range between <code>0</code and <code>10</code>.
     */
    private static int popularityRisk; // maxweight: 10
    /**
     * Stores the risk associated with the privacy of the listing. The values
     * can range between <code>0</code> and <code>10</code>.
     */
    private static int listingTypeRisk; // maxweight: 10
    /**
     * Stores the total risk of a particular listing.
     */
    private static int totalRisk;

    /**
     * Performs initial calculations which involves setting the average price,
     * average milage and the popularity risk.
     * 
     * @param carAds A list that contains all the listing to be analyzed.
     * @return The same list with an additional risk factor specification.
     */
    public CarListings initCalculations(CarListings carAds) {
        long sumPrices = 0;
        long sumMileage = 0;
        int price = 0;
        int minPriced = 0;
        int maxPriced = 0;
        int mileage = 0;
        int minMileaged = 0;
        int maxMileaged = 0;
        int entries = 0;
        int numListings = carAds.getSize();
        boolean hasPrice = false;
        boolean hasMileage = false;
        phoneNumRisk = 0;
        priceRisk = 0;
        popularityRisk = 0;
        listingTypeRisk = 0;
        totalRisk = 0;

        // the more listings, the more accurate the calculations = more chance
        // of being risky
        if (numListings >= 0 && numListings <= 99) {
            popularityRisk = 0;
        } else if (numListings >= 100 && numListings <= 199) {
            popularityRisk = 1;
        } else if (numListings >= 200 && numListings <= 299) {
            popularityRisk = 2;
        } else if (numListings >= 300 && numListings <= 399) {
            popularityRisk = 3;
        } else if (numListings >= 400 && numListings <= 499) {
            popularityRisk = 4;
        } else if (numListings >= 500 && numListings <= 599) {
            popularityRisk = 5;
        } else if (numListings >= 600 && numListings <= 699) {
            popularityRisk = 6;
        } else if (numListings >= 700 && numListings <= 799) {
            popularityRisk = 7;
        } else if (numListings >= 800 && numListings <= 899) {
            popularityRisk = 8;
        } else if (numListings >= 900 && numListings <= 999) {
            popularityRisk = 9;
        } else if (numListings >= 1000) {
            popularityRisk = 10;
        }

        for (int i = 0; i < carAds.getSize(); i++) {
            if (!carAds.getAdvertisement(i).getSpecifications().
                    get("Mileage:").contentEquals("N/A")) {
                
                mileage = Integer.parseInt(carAds.getAdvertisement(i)
                        .getSpecifications().get("Mileage:").split(" ")[0]);

                hasMileage = true;
            }

            if (!carAds.getAdvertisement(i).getSpecifications().get
                    ("Price:").contentEquals("N/A")) {
                
                price = Integer.parseInt(carAds.getAdvertisement(i)
                        .getSpecifications().get("Price:"));

                hasPrice = true;
            }

            if (hasMileage && hasPrice) {
                sumPrices += price;
                sumMileage += mileage;
                entries++;

                if (maxPriced == 0) {
                    maxPriced = price;
                    minPriced = price;
                } else if (price > maxPriced) {
                    maxPriced = price;
                } else if (price < minPriced) {
                    minPriced = price;
                }
                    
                if (maxMileaged == 0) {
                    maxMileaged = mileage;
                    minMileaged = mileage;
                } else if (mileage > maxMileaged) {
                    maxMileaged = mileage;
                } else if (mileage < minMileaged) {
                    minMileaged = mileage;
                }
            }

            hasPrice = false;
            hasMileage = false;
            price = 0;
            mileage = 0;
        }

        // boundary case
        if (entries == 0) {
            entries++;
        }

        minPrice = minPriced;
        avgPrice = (int)(sumPrices / entries);
        maxPrice = maxPriced;
        minMileage = minMileaged;
        avgMileage = (int)(sumMileage / entries);
        maxMileage = maxMileaged;

        carAds = getTotalRisk(carAds);
        return (new CarListings(carAds));
    }

    /**
     * Calculates the total risk each listing in the list of car ads and adds
     * a risk factor specification to each listing. 
     * 
     * @param carAds A list with every car ad to be analyzed.
     * @return A list with the same car ads with an additional specification 
     *         that shows the risk factor. Can be obtained by <code>Risk:</code>
     *         as the type when using <code>getSpecifications()</code>.
     * @see Listing#getSpecifications() 
     */
    public CarListings getTotalRisk(CarListings carAds) {
        double priceMargin = 0;
        double pivotMileage = 0;
        double mileageMargin = 0;
        int price = 0;
        int mileage = 0;
        boolean hasPrice = false;
        boolean hasMileage = false;
        boolean hasPhoneNum = true;
        boolean isPrivateListing = false;

        for (int i = 0; i < carAds.getSize(); i++) {
            hasPrice = false;
            hasMileage = false;
            hasPhoneNum = true;
            isPrivateListing = false;
            price = 0;
            mileage = 0;
            phoneNumRisk = 0;
            priceRisk = 0;
            listingTypeRisk = 0;
            totalRisk = 0;

            // if no contact number, more chance of scam
            if (carAds.getAdvertisement(i).getSpecifications().get
                    ("Contact Number:").contentEquals("N/A")) {
                
                phoneNumRisk = 25;
                hasPhoneNum = false;
            }

            if (!carAds.getAdvertisement(i).getSpecifications().get
                    ("Mileage:").contentEquals("N/A")) {
                
                mileage = Integer.parseInt(carAds.getAdvertisement(i)
                            .getSpecifications().get("Mileage:").split(" ")[0]);

                hasMileage = true;
            }

            if (!carAds.getAdvertisement(i).getSpecifications().get
                    ("Price:").contentEquals("N/A")) {
                
                price = Integer.parseInt(carAds.getAdvertisement(i)
                        .getSpecifications().get("Price:"));

                hasPrice = true;
            }

            // if a private listing, adds to the risk factor
            if (carAds.getAdvertisement(i).getSpecifications().get
                    ("Type of Listing:").contentEquals("Private Listing")) {
                
                listingTypeRisk = 10;
                isPrivateListing = true;
            }

            // different risk parameters
            if (!hasMileage && !hasPrice && isPrivateListing && !hasPhoneNum) {
                priceRisk = 55;
            } else if (!hasMileage && !hasPrice && isPrivateListing) {
                priceRisk = 44;
            } else if (!hasMileage && hasPrice && isPrivateListing) {
                priceRisk = 28;
            } else if (hasMileage && !hasPrice && isPrivateListing) {
                priceRisk = 28;
            } else if ((!hasMileage && !isPrivateListing)
                    || (!hasPrice && !isPrivateListing)) {
                
                priceRisk = 0;
            } else {
                // first figure out the "pivot" milage point which is dependent
                // on the price margin (how far the price is from the average
                // price) then depending on how far UNDER the mileage is from
                // the pivot mileage, the priceRisk is calculated with a weight
                // of 55, rounded up
                priceMargin = ((price / avgPrice) - 1);

                if (priceMargin != 0) {
                    pivotMileage = (avgMileage - (avgMileage * priceMargin));
                } else if (priceMargin == 0) {
                    pivotMileage = avgMileage;
                }

                if (mileage >= pivotMileage) {
                    priceRisk = 0;
                } else if (mileage < pivotMileage) {
                    mileageMargin = (1 - (mileage / pivotMileage));
                    priceRisk = (int) ((mileageMargin * 55) + 0.5);
                }

                if (!isPrivateListing) {
                    priceRisk = (int) (priceRisk * 0.5);
                }
            }

            totalRisk = phoneNumRisk + priceRisk
                    + popularityRisk + listingTypeRisk;

            //No risk calculation can be 100% because knowing if a listing
            //is a scam for certainty is impossible.
            if (totalRisk == 100) {
                totalRisk = 99;
            }

            carAds.getAdvertisement(i).setSpecification
                    ("Risk:", String.valueOf(totalRisk));
        }
        return (new CarListings(carAds));
    }
    
    public int dummyRiskCalculate(String price, String mileage, 
            String phone, String listingType, int numListings) {
        
        double priceMargin = 0;
        double pivotMileage = 0;
        double mileageMargin = 0;
        boolean hasMileage = false;
        boolean hasPrice = false;
        boolean isPrivateListing = false;
        boolean hasPhoneNum = false;
        
        // the more listings, the more accurate the calculations = more chance
        // of being risky
        if (numListings >= 0 && numListings <= 99) {
            popularityRisk = 0;
        } else if (numListings >= 100 && numListings <= 199) {
            popularityRisk = 1;
        } else if (numListings >= 200 && numListings <= 299) {
            popularityRisk = 2;
        } else if (numListings >= 300 && numListings <= 399) {
            popularityRisk = 3;
        } else if (numListings >= 400 && numListings <= 499) {
            popularityRisk = 4;
        } else if (numListings >= 500 && numListings <= 599) {
            popularityRisk = 5;
        } else if (numListings >= 600 && numListings <= 699) {
            popularityRisk = 6;
        } else if (numListings >= 700 && numListings <= 799) {
            popularityRisk = 7;
        } else if (numListings >= 800 && numListings <= 899) {
            popularityRisk = 8;
        } else if (numListings >= 900 && numListings <= 999) {
            popularityRisk = 9;
        } else if (numListings >= 1000) {
            popularityRisk = 10;
        }
        
        if (phone.contentEquals("N/A")) {
            phoneNumRisk = 25;
            hasPhoneNum = false;
        } else {
            phoneNumRisk = 0;
            hasPhoneNum = true;
        }
        
        if (listingType.contentEquals("Private Listing")) {
            listingTypeRisk = 10;
            isPrivateListing = true;
        } else {
            listingTypeRisk = 0;
            isPrivateListing = false;
        }
        
        if (mileage.contentEquals("N/A")) {
            hasMileage = false;
        } else {
            hasMileage = true;
        }
        
        if (price.contentEquals("N/A")) {
            hasPrice = false;
        } else {
            hasPrice = true;
        }
        
        // different risk parameters
        if (!hasMileage && !hasPrice && isPrivateListing && !hasPhoneNum) {
            priceRisk = 55;
        } else if (!hasMileage && !hasPrice && isPrivateListing) {
            priceRisk = 44;
        } else if (!hasMileage && hasPrice && isPrivateListing) {
            priceRisk = 28;
        } else if (hasMileage && !hasPrice && isPrivateListing) {
            priceRisk = 28;
        } else if ((!hasMileage && !isPrivateListing)
                || (!hasPrice && !isPrivateListing)) {

            priceRisk = 0;
        } else {
            // first figure out the "pivot" milage point which is dependent
            // on the price margin (how far the price is from the average
            // price) then depending on how far UNDER the mileage is from
            // the pivot mileage, the priceRisk is calculated with a weight
            // of 55, rounded up
            priceMargin = ((Double.parseDouble(price) / (double)avgPrice) - 1);

            if (priceMargin != 0) {
                pivotMileage = ((double)avgMileage - 
                                ((double)avgMileage * priceMargin));
                
            } else if (priceMargin == 0) {
                pivotMileage = avgMileage;
            }

            if (Double.parseDouble(mileage) >= pivotMileage) {
                priceRisk = 0;
            } else if (Double.parseDouble(mileage) < pivotMileage) {
                mileageMargin = 
                        (1 - (Double.parseDouble(mileage) / pivotMileage));
                
                priceRisk = (int) ((mileageMargin * 55) + 0.5);
            }

            if (!isPrivateListing) {
                priceRisk = (int) (priceRisk * 0.5);
            }
        }
        
        totalRisk = phoneNumRisk + priceRisk + popularityRisk + listingTypeRisk;
        
        //No risk calculation can be 100% because knowing if a listing
        //is a scam for certainty is impossible.
        if (totalRisk == 100) {
            totalRisk = 99;
        }
        
        return totalRisk;
    }

    /**
     * Returns a map that maps a URL string of an advertisement to the
     * advertisements' specifications; read from an XML file. Requires a boolean
     * that specifies if the XML file is from the hard disk.
     * 
     * @param fileName The filename of the XML file.
     * @param fromHardDisk The boolean that specifies if the XML file is from
     *                     the hard disk.
     * @return A map that maps URLs to it's advertisement's specifications.
     */
    public TreeMap<String, ArrayList<String>> urlToSpecs
            (String fileName, boolean fromHardDisk) {
        
        Map<String, ArrayList<String>> urlMap = 
                new TreeMap<String, ArrayList<String>>();
        
        StringBuilder title = new StringBuilder();
        int risk = -1;
        int price = 0;
        int maxPriced = 0;
        int minPriced = 0;
        int mileage = 0;
        int maxMileaged = 0;
        int minMileaged = 0;
        int entries = 0;
        long sumPrices = 0;
        long sumMileage = 0;
        boolean hasPrice = false;
        boolean hasMileage = false;
        
        XMLHandler xmlh = new XMLHandler();
        Document doc = xmlh.readXMLDocument(fileName);
        
        Element root = doc.getRootElement();
        Iterator it = root.getChildren("Car").iterator();
        
        while (it.hasNext()) {
            String url;
            Element currentElement = (Element) it.next();
            
            risk = Integer.parseInt(currentElement.getChildText("Risk"));
            title.append(currentElement.getChildText("Title"));
            url = currentElement.getChildText("URL");
            
            List<Element> list = currentElement.getChildren();
            List<String> list2 = new ArrayList<String>();
            
            for (Element e : list) {
                String name = e.getName();
                String text = e.getText();
                
                list2.add(name);
                list2.add(text);
                
                if (name.equals("Price")) {
                    try {
                        price = Integer.parseInt(text);
                        hasPrice = true;
                    } catch (NumberFormatException nfe) {
                    }
                } else if (name.equals("Mileage")) {
                    try {
                        text = text.split(" ")[0];
                        mileage = Integer.parseInt(text);
                        hasMileage = true;
                    } catch (NumberFormatException nfe) {
                    }
                }
            }
            
            urlMap.put(url, new ArrayList<String>(list2));
            
            if (hasPrice && hasMileage) {
                if (fromHardDisk) {
                    sumPrices += price;
                    sumMileage += mileage;
                    entries++;

                    if (maxPriced == 0) {
                        maxPriced = price;
                        minPriced = price;
                    } else if (price > maxPriced) {
                        maxPriced = price;
                    } else if (price < minPriced) {
                        minPriced = price;
                    }

                    if (maxMileaged == 0) {
                        maxMileaged = mileage;
                        minMileaged = mileage;
                    } else if (mileage > maxMileaged) {
                        maxMileaged = mileage;
                    } else if (mileage < minMileaged) {
                        minMileaged = mileage;
                    }
                }
            }

            hasPrice = false;
            hasMileage = false;
            price = 0;
            mileage = 0;
            risk = -1;
            title.delete(0, title.length());
        }

        if (entries == 0) {
            entries++;
        }
        
        setMinPrice(minPriced);
        setAvgPrice((int) ((sumPrices / entries) + 0.5));
        setMaxPrice(maxPriced);
        setMinMileage(minMileaged);
        setAvgMileage((int) ((sumMileage / entries) + 0.5));
        setMaxMileage(maxMileaged);
        
        return (new TreeMap<String, ArrayList<String>>(urlMap));
    }
    
    /**
     * Retrieves the minimum price of every listing inside of <code>CarListings
     * carAds</code>. In particlar, the car advertisements that are being
     * analyzed.
     * 
     * @return The minimum price.
     */
    public int getMinPrice() {
        return minPrice;
    }

    /**
     * Retrieves the average price of every listing inside of <code>CarListings
     * carAds</code>. In particlar, the car advertisements that are being
     * analyzed.
     * 
     * @return The average price.
     */
    public int getAvgPrice() {
        return avgPrice;
    }

    /**
     * Retrieves the maximum price of every listing inside of <code>CarListings
     * carAds</code>. In particlar, the car advertisements that are being
     * analyzed.
     * 
     * @return The maximum price.
     */
    public int getMaxPrice() {
        return maxPrice;
    }

    /**
     * Retrieves the minimum mileage of every listing inside of 
     * <code>CarListings carAds</code>. In particlar, the car 
     * advertisements that are being analyzed.
     * 
     * @return The minimum mileage.
     */
    public int getMinMileage() {
        return minMileage;
    }

    /**
     * Retrieves the average mileage of every listing inside of 
     * <code>CarListings carAds</code>. In particlar, the car advertisements 
     * that are being analyzed.
     * 
     * @return The average mileage.
     */
    public int getAvgMileage() {
        return avgMileage;
    }

    /**
     * Retrieves the maximum mileage of every listing inside of 
     * <code>CarListings carAds</code>. In particlar, the car advertisements 
     * that are being analyzed.
     * 
     * @return The maximum mileage.
     */
    public int getMaxMileage() {
        return maxMileage;
    }
    
    /**
     * Sets the minimum price. This is usually the minimum price of every 
     * listing inside of <code>CarListings carAds</code>. In particlar, the car 
     * advertisements that are being analyzed.
     * 
     * @param minPriced The minimum price to set the <code>minPrice</code>
     *                  variable.
     */
    public void setMinPrice(int minPriced) {
        minPrice = minPriced;
    }
    
    /**
     * Sets the average price. This is usually the average price of every 
     * listing inside of <code>CarListings carAds</code>. In particlar, the car 
     * advertisements that are being analyzed.
     * 
     * @param avgPriced The average price to set the <code>avgPrice</code>
     *                  variable.
     */
    public void setAvgPrice(int avgPriced) {
        avgPrice = avgPriced;
    }
    
    /**
     * Sets the maximum price. This is usually the maximum price of every 
     * listing inside of <code>CarListings carAds</code>. In particlar, the car 
     * advertisements that are being analyzed.
     * 
     * @param maxPriced The maximum price to set the <code>maxPrice</code>
     *                  variable.
     */
    public void setMaxPrice(int maxPriced) {
        maxPrice = maxPriced;
    }
    
    
    /**
     * Sets the minimum mileage. This is usually the minimum mileage of every 
     * listing inside of <code>CarListings carAds</code>. In particlar, the car 
     * advertisements that are being analyzed.
     * 
     * @param minMileaged The minimum mileage to set the <code>minMileage</code>
     *                  variable.
     */
    public void setMinMileage(int minMileaged) {
        minMileage = minMileaged;
    }
    
    /**
     * Sets the average mileage. This is usually the average mileage of every 
     * listing inside of <code>CarListings carAds</code>. In particlar, the car 
     * advertisements that are being analyzed.
     * 
     * @param avgMileaged The average mileage to set the <code>avgMileage</code>
     *                  variable.
     */
    public void setAvgMileage(int avgMileaged) {
        avgMileage = avgMileaged;
    }
    
    /**
     * Sets the maximum mileage. This is usually the maximum mileage of every 
     * listing inside of <code>CarListings carAds</code>. In particlar, the car 
     * advertisements that are being analyzed.
     * 
     * @param maxMileaged The maximum mileage to set the 
     * <code>maxMileaged</code> variable.
     */
    public void setMaxMileage(int maxMileaged) {
        maxMileage = maxMileaged;
    }
}