import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class Loader {

    public static void main(String[] args) throws Exception {
        String fileName = "res/data-1572M.xml";

        long start = System.currentTimeMillis();

        try {
            parseFileSax(fileName);
            System.out.println(System.currentTimeMillis() - start + " ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Find Duplicates: ");
        DBConnection.printVoterCounts();

        System.out.println("Let's Find Person by Name: ");
        DBConnection.customSelect("Борин Назим");

        DBConnection.connectionClose();

    }

    private static void parseFileSax(String fileName) throws Exception {
        DBConnection.getConnection();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLHandler handler = new XMLHandler();
        parser.parse(new File(fileName), handler);
        System.out.println("Parsing completed");
        DBConnection.executeBatch();
    }
}
