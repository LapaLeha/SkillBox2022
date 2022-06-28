import org.apache.commons.lang3.StringUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.concurrent.ForkJoinPool;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static char padChar = '\t';
    public static void main(String[] args) {

        String url = "https://skillbox.ru/media";

        System.out.println("Scanning the site...");
        long start = System.currentTimeMillis();
        WebAll webAll = new WebAll(url);
        String webMap = new ForkJoinPool().invoke(webAll);
        String webMapEnd = "";
        String[] fragmentsString = webMap.split("\n");

        for (int i =0;i< fragmentsString.length;i++){
            fragmentsString[i] = StringUtils.leftPad(fragmentsString[i], getCountTab(fragmentsString[i])
                    +fragmentsString[i].length(), padChar);
            webMapEnd=webMapEnd+fragmentsString[i]+"\n";
        }

        System.out.println("Scanning is finished.");
        System.out
                .println("Time of scanning " + ((System.currentTimeMillis() - start) / 1000) + " seconds.");
        writeFiles(webMapEnd);
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

    private static int getCountTab(String attr){
        Pattern pattern = Pattern.compile("/");
        Matcher matcher = pattern.matcher(attr);
        int countTab=0;
        while (matcher.find()){
            countTab++;
        }
        if (attr.substring(attr.length()-1).equals("/")){
            return countTab-3;
        }
        return countTab-2;
    }
}