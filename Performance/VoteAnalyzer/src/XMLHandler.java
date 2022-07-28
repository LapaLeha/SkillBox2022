import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;


public class XMLHandler extends DefaultHandler {

    int limit = 50;
    int number = 0;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try {
            if (qName.equals("voter") && number < limit)
            {
                String birthDay = attributes.getValue("birthDay").replace('.', '-');
                String name = attributes.getValue("name");
                DBConnection.countVoter(name,birthDay,number);
                number++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
