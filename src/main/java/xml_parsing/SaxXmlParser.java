package xml_parsing;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import places.ParsingException;
import places.PlacesData;

public class SaxXmlParser implements XmlParser{
    PlacesHandler placesHandler;

    public SaxXmlParser(){
        placesHandler = new PlacesHandler();
    }

    public PlacesData parseFromXml(String fileName) throws ParsingException {
        try {
            tryParsing(fileName);
            PlacesData placesData = placesHandler.getPlacesData();
            return placesData;
        } catch(Exception e ) {
            e.printStackTrace();
            throw new ParsingException("Problem occured during parsing xml");

        }
    }

    private void tryParsing(String fileName) throws ParserConfigurationException, SAXException, IOException {
        File inputFile = new File(fileName);

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();

        saxParser.parse(inputFile, placesHandler);

    }

}