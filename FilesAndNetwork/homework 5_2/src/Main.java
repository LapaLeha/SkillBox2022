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
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {
    private static final String fileWrite = "data/file1.json";
    private static MetroMsk metroMsk;
    private static Map<Station, TreeSet<Station>> connections;

    public static void main(String[] args) throws IOException {


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


        for (int i = 0; i < stationsOnLine.size(); i++) {
            String[] fragments = stationsOnLine.get(i).split("[ ]?[0-9]{1,2}[.]{1}[ ]");
            for (int j = 0; j < fragments.length; j++) {
                lines.get(i).addStation(new Station(fragments[j], lines.get(i)));
            }
        }


        Elements connectionsElements = doc.select("span.t-icon-metroln,span.name");
        String numberLineNow = "";
        String stationNow = "";
        TreeSet<String> linesWithConnectionSmall = new TreeSet<>();
        TreeSet<String> linesWithConnectionBig = new TreeSet<>();
        int flag = 0;
        String regex = "ln-.*";
        String regex2 = "[«].*[»]";

        for (Element e : connectionsElements) {


            if (e.select("span.js-metro-line").hasAttr("data-line")) {

                if (flag == 0) {
                    numberLineNow = e.attr("data-line");
                    continue;
                } else {
                    linesWithConnectionSmall.add("\"" + numberLineNow + "\"" + "." + "\"" + stationNow + "\"");
                    String s = "";
                    for (String s1 : linesWithConnectionSmall) {
                        s += s1;
                    }

                    linesWithConnectionBig.add(s);
                    linesWithConnectionSmall.clear();
                    flag = 0;
                    numberLineNow = e.attr("data-line");
                    continue;
                }
            }

            if (e.select("span.name").hasAttr("class")) {

                if (flag == 0) {
                    stationNow = e.text();
                    continue;
                } else {
                    linesWithConnectionSmall.add("\"" + numberLineNow + "\"" + "." + "\"" + stationNow + "\"");
                    String s = "";
                    for (String s1 : linesWithConnectionSmall) {
                        s += s1;
                    }

                    linesWithConnectionBig.add(s);
                    linesWithConnectionSmall.clear();
                    flag = 0;
                    stationNow = e.text();
                    continue;
                }
            }

            String numberLineConnection = "";

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(e.attr("class"));
            while (matcher.find()) {
                int start = matcher.start() + 3;
                int end = matcher.end();
                numberLineConnection = e.attr("class").substring(start, end);
            }

            String stationLineConnection = "";
            Pattern pattern2 = Pattern.compile(regex2);
            Matcher matcher2 = pattern2.matcher(e.attr("title"));
            while (matcher2.find()) {
                int start = matcher2.start() + 1;
                int end = matcher2.end() - 1;
                stationLineConnection = e.attr("title").substring(start, end);
            }

            linesWithConnectionSmall.add("\"" + numberLineConnection + "\"" + "." + "\"" + stationLineConnection + "\"");
            flag = 1;
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
            String stationsIter;
            String stationsIterStart;
            String stationsIterFinish;

            for (int j = 0; j < lines.size(); j++) {
                stationsIter = "";
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
            String json = json1 + "\n" + "}" + ",\n" + "  \"connections\" : [\n";
            file.write(json);

            String allConB = "";
            for (String e : linesWithConnectionBig) {
                String allConS = "";
                String[] fragmentsStation = e.split("[\\\"][0-9a-zA-Z]{1,2}[\\\"][.]{1}");
                String[] fragmentsLine = e.split("[.][\\\"][А-я,Ё,ё,\\-, ,]{1,}[\\\"]");

                for (int i = 0; i < fragmentsLine.length; i++) {
                    String con;
                    if (i != fragmentsLine.length - 1) {
                        con = "{" + "\n"
                                + "  \"line\" : " + fragmentsLine[i] + "," + "\n"
                                + "  \"station\" : " + fragmentsStation[i + 1] + "\n"
                                + "}" + "," + "\n";
                        allConS += con;
                    } else {
                        con = "{" + "\n"
                                + "  \"line\" : " + fragmentsLine[i] + "," + "\n"
                                + "  \"station\" : " + fragmentsStation[fragmentsStation.length - 1] + "\n"
                                + "}" + "\n";
                        allConS += con;
                        allConS = "[" + "\n" + allConS + "]" + "," + "\n";
                    }
                }
                allConB += allConS;
            }

            int count2 = allConB.length();
            String json2 = allConB.substring(0, count2 - 3);
            String jsonC = json2 + "\n" + "]" + "\n" + "]" + "\n" + "}";
            file.write(jsonC);

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
        System.out.println("Количество переходов в метро - " + metroMsk.getSumConnections());
    }

    private static void createMetroMsk() {
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonData = (JSONObject) parser.parse(getJsonFile());

            metroMsk = new MetroMsk(((JSONArray) jsonData.get("connections")).size());

            JSONArray linesArray = (JSONArray) jsonData.get("lines");
            parseLines(linesArray);

            JSONObject stationsObject = (JSONObject) jsonData.get("stations");
            parseStations(stationsObject);

            JSONArray connectionsArray = (JSONArray) jsonData.get("connections");
            parseConnections(connectionsArray);

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

    private static void parseConnections(JSONArray connectionsArray) {
        connectionsArray.forEach(connectionObject ->
        {
            JSONArray connection = (JSONArray) connectionObject;
            List<Station> connectionStations = new ArrayList<>();
            connection.forEach(item ->
            {
                JSONObject itemObject = (JSONObject) item;
                String lineNumber = (String) itemObject.get("line");
                String stationName = (String) itemObject.get("station");


                Station station = metroMsk.getStation(stationName, lineNumber);
                if (station == null) {
                    throw new IllegalArgumentException("core.Station " +
                            stationName + " on line " + lineNumber + " not found");
                }
                connectionStations.add(station);
            });
            metroMsk.addConnection(connectionStations);
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

