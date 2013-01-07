package AutoTraderFraudCalculator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.filter.ElementFilter;
import org.jdom.filter.Filter;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 * The XMLHandler is able to read, write, and build XML documents using JDOM.
 * 
 */
public class XMLHandler {
    
    /**
     * Builds a JDOM Document that represents all the car advertisements that
     * are stored in carAds.
     * @param carAds Car advertisements that are originated from <code>
     *               autotrader.ca</code>
     * @return A JDOM document that can write to an XML file or be read and
     *         searched.
     */
    public Document buildXMLDocument(CarListings carAds) {
        Element root = new Element("Listings");
        Document doc = new Document(root);
        
        for (int i = 0; i < carAds.getSize(); i++) {
            Element newCar = new Element("Car");
            
            newCar.addContent(new Element("Title").setText
                    (carAds.getAdvertisement(i).getTitle()));
            
            newCar.addContent(new Element("URL").setText
                    (carAds.getAdvertisement(i).getUrl()));
            
            for (int j = 0; j < carAds.getAdvertisement(i).
                    getSpecificationSize(); j++) {
                
                String spec = carAds.getAdvertisement(i).
                                            toArray()[j].toString();
                
                String[] parts = spec.split(":=");
                
                if (parts.length == 2) {
                    parts[0] = parts[0].replaceAll("(\\W)", "");
                    newCar.addContent(new Element(parts[0]).setText(parts[1]));
                } else if (parts.length == 1) {
                    parts[0] = parts[0].replaceAll("(\\W)", "");
                    String part2 = "N/A";
                    newCar.addContent(new Element(parts[0]).setText(part2));
                }
            }
            
            root.addContent(newCar);
        }
        
        return doc;
    }
    
    /**
     * Writes a JDOM Document as an XML formatted file.
     * 
     * @param doc A JDOM document to be written onto a file.
     * @param fileName The filename of the XML file.
     */
    public void writeXMLDocument(Document doc, String fileName) {
        
        try {
            
            Writer writer = new BufferedWriter(new FileWriter(fileName));  
            XMLOutputter xo = new XMLOutputter(Format.getPrettyFormat());
            xo.output(doc, writer);
            writer.close();
        }
        catch(IOException io){
            System.exit(1);
        }
    }
    
    /**
     * Reads an XML document and stores it as a JDOM document.
     * 
     * @param fileName The filename of the XML formatted file.
     */
    public Document readXMLDocument(String fileName) {
        
        SAXBuilder builder = new SAXBuilder();
        
        try {
            Document doc = builder.build(fileName);
            return doc;
        } 
        catch (JDOMException jex){
            System.out.print(jex.getMessage());
            System.exit(1);
        } 
        catch(IOException ioe){
            System.exit(1);
        }
        
        return null;
    }
    
    /**
     * Returns the element that contains the URL specified.
     * @param doc The document to search through.
     * @param uniqueElementURL The unique URL, if multiple, returns first one.
     * @return The parent of the JDOM element where the URL was found.
     */
    public Element findElement(Document doc, String uniqueElementURL) {
        Filter f = new ElementFilter("URL");
        Iterator it = doc.getDescendants(f);
        String url = uniqueElementURL.trim();
        
        while (it.hasNext()) {
            Element elt = (Element) it.next();
            if (elt.getText().equals(url))
                return elt.getParentElement();
        }
        
        return null;
    }
    
    /**
     * Returns an string array of URLs in a document.
     * @param doc The document to search through.
     * @return All the URLs.
     */
    public String[] getURLs(Document doc) {
        String[] urls = null;
        List<String> urlList = new ArrayList<String>();
        
        Filter f = new ElementFilter("URL");
        Iterator it = doc.getDescendants(f);
        
        while (it.hasNext()) {
            Element elt = (Element) it.next();
            urlList.add(elt.getText());
        }
        
        return urlList.toArray(new String[urlList.size()]);
    }
}