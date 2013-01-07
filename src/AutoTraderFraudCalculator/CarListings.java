package AutoTraderFraudCalculator;

import java.util.ArrayList;
import java.util.List;

/**
 * Acts as a container for holding an array of car advertisements. Provides
 * methods to add/get listings.
 * 
 * <p> Pushing an advertisement into a <code>CarListings</code> object adds it
 * to the end of the list. Retrieving an advertisement requires the index of
 * the particular advertisement.
 */
public class CarListings {

    /**
     * The container of all the advertisements.
     */
    private List<Listing> carAds;

    /**
     * Allows a method of copying and creating a new instance of this class
     * with the exact same listings in the same order.
     * 
     * @param cl A <code>CarListings</code> object that needs to be cloned.
     */
    public CarListings(CarListings cl) {

        carAds = new ArrayList<Listing>();
        int size = cl.getSize();

        for (int i = 0; i < size; i++) {
            carAds.add(cl.getAdvertisement(i));
        }
    }

    /**
     * Constructs an empty list of car advertisements.
     */
    public CarListings() {
        carAds = new ArrayList<Listing>();
    }

    /**
     * Pushes an advertisement into the object. This adds it to the ending of
     * the current list of advertisements.
     * 
     * @param carAdvertisement A car advertisement.
     */
    public void pushAdvertisement(Listing carAdvertisement) {
        carAds.add(carAdvertisement);
    }

    /**
     * Retrieves the advertisement at a specified index. The index must be
     * between 0 and <code>this.getSize()</code>.
     * 
     * @param index The index of the advertisement.
     * @return The advertisement at specified index.
     * @throws ArrayIndexOutOfBoundsException
     */
    public Listing getAdvertisement(int index) {
        if ((index < 0) || (index > getSize())) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            return carAds.get(index);
        }
    }

    /**
     * Returns the amount of advertisements stored.
     * 
     * @return The amount of advertisements.
     */
    public int getSize() {
        return carAds.size();
    }
}
