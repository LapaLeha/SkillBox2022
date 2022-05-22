import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

/*        String htmlFile = parseFile("data/code.html");
        String filetoWrite = "data/file.json";
        Document doc = Jsoup.parse(htmlFile);*/


        //для парсинга самого сайта будет такой вариант:
        String webSiteURL = "https://skillbox-java.github.io/";
        Document doc = null;
        try {
            doc = Jsoup.connect(webSiteURL).maxBodySize(0).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements elementsLines = doc.select("span.js-metro-line");
        ArrayList<String> lines = new ArrayList<>();
        ArrayList<String> lineNumbers = new ArrayList<>();
        for (Element e : elementsLines) {
            lines.add(e.text());
            System.out.println(e.text());
        }
        for (Element e : elementsLines) {
            lineNumbers.add(e.attr("data-line"));
            System.out.println(e.attr("data-line"));
        }
//************************************************************************************
// вар 2 запись линий и номера линий в МАП
/*        Map<String, String> lineMskMetro = new HashMap<>();
        //Elements elementsLines = doc.select("span.js-metro-line");
        for (Element e : elementsLines) {
            lineMskMetro.put(e.attr("data-line"), e.text());
        }

        for (Map.Entry<String, String> entry : lineMskMetro.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + " = " + value);
        }*/
        //*******************************************************************************
        Elements elementsStations = doc.select("div.js-metro-stations");
        ArrayList<String> stations = new ArrayList<>();
        elementsStations.forEach(element -> stations.add(element.text()));

        //String[] fragments = lines.get(i).split("[0-9]{1,2}[.]{1}");



        ArrayList<String> lineCount = new ArrayList<>();
        for (int i = 0; i < stations.size(); i++) {
            long stationsCount = stations.get(i).chars().filter(ch -> ch == '.').count();
            lineCount.add(String.valueOf(stationsCount));
           System.out.println(lines.get(i) + " (Количество станций на линии: " + stationsCount + ")");
        }


        String fileWrite = "data/file.json";
        try {
            FileWriter file = new FileWriter(fileWrite);
            file.write("{\n\"" + "Lines\" : " + "[ " + "\n");
            for (int i = 0; i < lines.size(); i++) {
                JSONObject object1 = new JSONObject();
                object1.put("name", lines.get(i));
                object1.put("number", lineNumbers.get(i));
                if (i != lines.size() - 1) {
                    file.write(object1.toJSONString() + "," + "\n");
                } else {
                    file.write(object1.toJSONString() + "],\n" + "  \"Stations\" : [\n");
                }
            }
            for (int j = 0; j < lines.size(); j++) {
                JSONObject object2 = new JSONObject();
                object2.put(lineNumbers.get(j), stations.get(j));
                if (j != lines.size() - 1) {
                    file.write(object2.toJSONString() + "," + "\n");
                } else file.write(object2.toJSONString() + "\n]\n}");
            }
            file.flush();
            file.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

/*        try {

            Reader reader = Files.newBufferedReader(Paths.get(fileWrite));
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode parser = objectMapper.readTree(reader);


            for (JsonNode project : parser.path("name")) {
                System.out.println(project.path("number").asText());
                System.out.println(parser.get(String.valueOf(lineNumbers)).asText());

            }
            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }*/
    }

    public static String parseFile(String path) {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            lines.forEach(line -> builder.append(line + "\n"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return builder.toString();
    }
}