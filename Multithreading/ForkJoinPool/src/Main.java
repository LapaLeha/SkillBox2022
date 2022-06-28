import org.apache.commons.lang3.StringUtils;
import java.io.*;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final String fileWrite = "data/file1.txt";
    private static char padChar = '\t';

    public static void main(String[] args) {

        String url = "https://skillbox.ru/media";
        WebAll webAll = new WebAll(url);
        List<String> a = new ForkJoinPool().invoke(webAll);

        try {
            FileWriter file = new FileWriter(fileWrite);
            for (String s : a) {
                s = StringUtils.leftPad(s, getCountTab(s)+s.length()-1, padChar);
                file.write(s+"\n");
            }
            file.flush();
            file.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static int getCountTab(String attr) {
        Pattern pattern = Pattern.compile("/");
        Matcher matcher = pattern.matcher(attr);
        int countTab = 0;
        while (matcher.find()) {
            countTab++;
        }
        if (attr.substring(attr.length() - 1).equals("/")) {
            return countTab - 3;
        }
        return countTab - 2;
    }
}