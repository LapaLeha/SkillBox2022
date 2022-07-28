import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class Loader {
    //public static long timer = System.currentTimeMillis();
    //public static long total_timer = System.currentTimeMillis();

    public static void main(String[] args) throws Exception {
        String fileName = "res/data-18M.xml";
        long start = System.currentTimeMillis();

        try {
            parseFileSax(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Печать используемой памяти
        long usage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("################## < Find Duplicates: > ##################");
        DBConnection.printVoterCounts();
        usage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() - usage;
        System.out.println("Memory: " + usage);
        System.out.println("################## < Let's Find Person by Name: > ##################");
        DBConnection.customSelect("Гогель Геллий");
        usage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() - usage;
        System.out.println("Memory: " + usage);
        DBConnection.connectionClose();
        usage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() - usage;
        System.out.println("Memory: " + usage);

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