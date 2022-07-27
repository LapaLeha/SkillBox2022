import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class Loader {

    public static void main(String[] args) throws Exception {
        String fileName = "res/data-18M.xml";

        long usage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLHandler handler = new XMLHandler();
        parser.parse(new File(fileName), handler);
        handler.printDuplicatedVoters();
        usage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() - usage;
        System.out.println(usage);
    }
}