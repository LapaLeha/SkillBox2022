import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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
    private static final String fileWrite = "data/file1.json";
    private static MetroMsk metroMsk;

    public static void main(String[] args) {
        String webSiteURL = "https://skillbox-java.github.io/";
        Document doc = null;
        try {
            doc = Jsoup.connect(webSiteURL).maxBodySize(0).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements elementsLines = doc.select("span.js-metro-line");
        ArrayList<Line> lines = new ArrayList<>();
        for (Element e : elementsLines) {
            lines.add(new Line(e.attr("data-line"), e.text()));
        }

        Elements elementsStations = doc.select("div.js-metro-stations");
        ArrayList<String> stationsOnLine = new ArrayList<>();
        elementsStations.forEach(element -> stationsOnLine.add(element.text()));

        ArrayList<Station> stations = new ArrayList<>();

        for (int i = 0; i < stationsOnLine.size(); i++) {
            String[] fragments = stationsOnLine.get(i).split("[ ]?[0-9]{1,2}[.]{1}[ ]");
            for (int j = 1; j < fragments.length; j++) {
                stations.add(new Station(fragments[j], lines.get(i)));
                lines.get(i).addStation(new Station(fragments[j], lines.get(i)));
            }
        }

        try {
            FileWriter file = new FileWriter(fileWrite);
            file.write("{\n\"" + "lines\" : " + "[ " + "\n");
            for (int i = 0; i < lines.size(); i++) {
                JSONObject object1 = new JSONObject();
                object1.put("name", lines.get(i).getName());
                object1.put("number", lines.get(i).getNumber());
                if (i != lines.size() - 1) {
                    file.write(object1.toJSONString() + "," + "\n");
                } else {
                    file.write(object1.toJSONString() + "],\n" + "  \"stations\" : {\n");
                }
            }

            String stationsAll = "";
            String stationsIter = "";
            String stationsIterStart = "";
            String stationsIterFinish = "";

            for (int j = 0; j < lines.size(); j++) {
                stationsIter = "";
                stationsIterStart = "";
                stationsIterFinish = "";
                for (int k = 0; k < lines.get(j).getStations().size(); k++) {
                    String[] arrayStations = new String[lines.get(j).getStations().size()];
                    if (k != lines.get(j).getStations().size() - 1) {
                        arrayStations[k] = String.valueOf(lines.get(j).getStations().get(k));
                        stationsIter += "\"" + arrayStations[k] + "\", " + "\n";
                    } else {
                        stationsIterStart = "\"" + lines.get(j).getNumber() + "\": " + "[ " + "\n";
                        arrayStations[k] = String.valueOf(lines.get(j).getStations().get(k));
                        stationsIterFinish = stationsIterStart + stationsIter + "\"" + arrayStations[k] + "\"" + "\n" + "], " + "\n";
                    }
                }
                stationsAll += stationsIterFinish;

            }
            int count = stationsAll.length();
            String json1 = stationsAll.substring(0, count - 3);
            String json = json1 +"\n"+ "}\n" + "}";
            file.write(json);
            file.flush();
            file.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        createMetroMsk();
        ArrayList<Line> linesR = (ArrayList<Line>) metroMsk.getLines();
        for (int i = 0; i < linesR.size(); i++) {
            int countStation = linesR.get(i).getStations().size();
            System.out.println("Количество станций на " + linesR.get(i).getName() + " - " + countStation);
        }
    }

    private static void createMetroMsk() {
        metroMsk = new MetroMsk();
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonData = (JSONObject) parser.parse(getJsonFile());


            JSONArray linesArray = (JSONArray) jsonData.get("lines");
            parseLines(linesArray);

            JSONObject stationsObject = (JSONObject) jsonData.get("stations");
            parseStations(stationsObject);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void parseStations(JSONObject stationsObject) {
        stationsObject.keySet().forEach(lineNumberObject ->
        {
            Line line = metroMsk.getLine((String) lineNumberObject);
            JSONArray stationsArray = (JSONArray) stationsObject.get(lineNumberObject);
            stationsArray.forEach(stationObject ->
            {
                Station station = new Station((String) stationObject, line);
                metroMsk.addStation(station);
                line.addStation(station);
            });
        });
    }

    private static void parseLines(JSONArray linesArray) {
        linesArray.forEach(lineObject -> {
            JSONObject lineJsonObject = (JSONObject) lineObject;
            Line line = new Line(
                    ((String) lineJsonObject.get("number")),
                    (String) lineJsonObject.get("name")
            );
            metroMsk.addLine(line);
        });
    }

    private static String getJsonFile() {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileWrite));
            lines.forEach(line -> builder.append(line));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return builder.toString();
    }
}
