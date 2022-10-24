package utils;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * Utility that reads from an XML file.
 */
public class ReadXML {
    /**
     * Gets the requested data from the xml file
     * @param keyName which key to look up the value
     * @return the requested value as a String
     * @throws Exception IO exception from the File
     */
    public static String getData (String keyName) throws Exception{

        ClassLoader classLoader = DriverSingleton.class.getClassLoader();
        String xmlFilePath = String.valueOf(new File(classLoader.getResource("data.xml").getFile()));
        File fXmlFile = new File(xmlFilePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();
        return doc.getElementsByTagName(keyName).item(0).getTextContent();

    }
}
