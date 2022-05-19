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
import java.util.Map;

public class Main {
    private static final String fileWrite = "data/file1.json";
    private static final String fileWrite2 = "data/spbMetro222.json";
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
        // ArrayList<String> lineNumbers = new ArrayList<>();
        for (Element e : elementsLines) {
            lines.add(new Line(e.attr("data-line"), e.text()));
            //System.out.println(e.text());
        }
/*        for (Element e : elementsLines) {
            lineNumbers.add(e.attr("data-line"));
            System.out.println(e.attr("data-line"));
        }*/


/*        for (int i = 0; i < lines.size(); i++) {
            System.out.println(lines.get(i).getNumber() + " - " + lines.get(i).getName());
        }*/

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

        for (int i = 0; i < lines.size(); i++) {
            System.out.println(lines.get(i).getNumber() + " - " + lines.get(i).getName() + ":"
                    + "\n" + lines.get(i).getStations());
        }


        try {
            FileWriter file = new FileWriter(fileWrite);
            file.write("{\n\"" + "Lines\" : " + "[ " + "\n");
            for (int i = 0; i < lines.size(); i++) {
                JSONObject object1 = new JSONObject();
                object1.put("name", lines.get(i).getName());
                object1.put("number", lines.get(i).getNumber());
                if (i != lines.size() - 1) {
                    file.write(object1.toJSONString() + "," + "\n");
                } else {
                    file.write(object1.toJSONString() + "],\n" + "  \"Stations\" : [\n");
                }
            }
            for (int j = 0; j < lines.size(); j++) {
                JSONObject object2 = new JSONObject();
                String stationsLineK = "";
                for (int k = 0; k < lines.get(j).getStations().size(); k++) {
                    if (k != lines.get(j).getStations().size() - 1) {
                        stationsLineK += lines.get(j).getStations().get(k) + ", ";
                    } else {
                        stationsLineK += lines.get(j).getStations().get(k);
                    }
                }

                object2.put(lines.get(j).getNumber(), stationsLineK.trim());
                if (j != lines.size() - 1) {
                    file.write(object2.toJSONString() + "," + "\n");
                } else file.write(object2.toJSONString() + "\n]\n}");
            }
            file.flush();
            file.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        createMetroMsk();

/*        ArrayList<Line> linesR = (ArrayList<Line>) metroMsk.getLines();
        for (int i = 0; i < linesR.size(); i++) {
            int countStation = linesR.get(i).getStations().size();
            System.out.println("Количество станций на " + linesR.get(i).getName() + " - " + countStation);
        }*/
/*        for (Map.Entry<String, String> entry : lineMskMetro.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + " = " + value);
        }*/
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

/*            JSONArray connectionsArray = (JSONArray) jsonData.get("connections");
            parseConnections(connectionsArray);*/
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void parseStations(JSONObject stationsObject) {
        stationsObject.keySet().forEach(lineNumberObject ->
        {
            //String lineNumber = String.valueOf(Integer.parseInt((String) lineNumberObject));
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
