package xml_parsing;

/**
 * Created by nissatech on 1/27/17.
 */
public class XmlParserFactory {
    public static XmlParser createXmlParser(XmlParserType xmlParserType) {
        switch (xmlParserType) {
            case SAX_PARSER: return  new SaxXmlParser();
            default: return  new SaxXmlParser();
        }
    }
}
