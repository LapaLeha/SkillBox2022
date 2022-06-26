import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {

        String url = "https://skillbox.ru";


        System.out.println("Scanning the site...");
        long start = System.currentTimeMillis();
        WebAll linkExecutor = new WebAll(url);
        String webMap = new ForkJoinPool().invoke(linkExecutor);


        System.out.println("Scanning is finished.");
        System.out
                .println("Time of scanning " + ((System.currentTimeMillis() - start) / 1000) + " seconds.");
        writeFiles(webMap);
    }

    private static void writeFiles(String map) {
        System.out.println("Write the file...");
        String filePath = "webMap.txt";

        File file = new File(filePath);
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.write(map);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Site Map is completed!");
    }
}