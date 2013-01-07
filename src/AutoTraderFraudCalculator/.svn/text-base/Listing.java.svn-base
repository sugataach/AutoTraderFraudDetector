package AutoTraderFraudCalculator;

import java.util.*;

/**
 * Stores a car advertisement as an object. In addition, provides methods to
 * set/get the title, URL, and specifications of the listing.
 * 
 */
public class Listing {

    /**
     * Stores the title of the listing. To be specific, the title of the ad
     * which usually includes name of make and model.
     */
    private StringBuilder title;
    /**
     * Stores the URL of the listing.
     */
    private StringBuilder url;
    /**
     * Stores all the specifications of the listing.
     */
    private Map<String, String> specifications;

    /**
     * Constructs a <code>TreeMap<String, String></code> to hold the
     * specifications.
     */
    public Listing() {
        specifications = new TreeMap<String, String>();
        title = new StringBuilder();
        url = new StringBuilder();
    }

    /**
     * Sets the title of the listing. This is the title of the advertisement
     * as shown on the <code>autotrader.ca</code> website.
     * 
     * @param adTitle The title of the listing.
     */
    public void setTitle(String adTitle) {
        title.delete(0, title.length());
        title.append(adTitle);
    }

    /**
     * Sets the URL that represents the listing on the <code>autotrader.ca
     * </code> website.
     * 
     * @param adUrl The URL of the listing.
     */
    public void setUrl(String adUrl) {
        url.delete(0, url.length());
        url.append(adUrl);
    }

    /**
     * Adds/replaces a specification. A specification stores necessary
     * information about the car.
     * 
     * @param adSpecType The name of the specification.
     * @param adSpecValue The specification value.
     */
    public void setSpecification(String adSpecType, String adSpecValue) {
        specifications.put(adSpecType, adSpecValue);
    }

    /**
     * Retrieves the title of the car advertisement. The title usually includes
     * the name of the car's make and model.
     * 
     * @return The title of the car advertisement.
     */
    public String getTitle() {
        return title.toString();
    }

    /**
     * Retrieves the URL that represents the listing on the <code>autotrader.ca
     * </code> website.
     * 
     * @return The URL of the listing.
     */
    public String getUrl() {
        return url.toString();
    }

    /**
     * Retrieves the map that contains the specifications of this listing.
     * 
     * @return The <code>Map<String, String></code> with all the
     *         specifications that were put into this listing.
     * @see #setSpecification(java.lang.String, java.lang.String)
     */
    public Map<String, String> getSpecifications() {
        return specifications;
    }

    /**
     * Returns a specification value associated with a key.
     * 
     * @param adSpecType The type of specification to search for.
     * @return The specification value if found, else return null.
     */
    public String getSpecificationValue(String adSpecType) {
        if (containsSpecificationType(adSpecType)) {
            return specifications.get(adSpecType);
        } else {
            return null;
        }
    }

    /**
     * Returns <code>true</code> if the specification type exists and <code>
     * false</code> if it doesn't exist. Also, return <code>false</code> if
     * the value is either <code>null</code> or blank (length is 0).
     * 
     * @param adSpecType The specification type to search for.
     * @return <code>true</code> if it exists, <code>false</code> otherwise.
     */
    public boolean containsSpecificationType(String adSpecType) {
        if (specifications.containsKey(adSpecType)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the amount of entered specifications. This includes
     * specifications with an empty value with a normal key or vice versa.
     * 
     * @return The amount of specifications.
     */
    public int getSpecificationSize() {
        return specifications.size();
    }

    /**
     * Returns an array representation of the set of specifications.
     * 
     * @return An array of specifications.
     */
    public Object[] toArray() {
        return specifications.entrySet().toArray();
    }
}