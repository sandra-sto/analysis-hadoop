package xml_parsing;


import places.PlacesData;

public interface XmlParser {
    public PlacesData parseFromXml(String fileName) throws ParsingException;
}