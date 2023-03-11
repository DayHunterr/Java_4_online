package ua.com.alevel;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Main {
    private static final File INPUT_PATH = new File("../SourcePath/input.json");
    private static final File OUTPUT_PATH = new File("../SourcePath/output.json");

    private static Graph getGraph(JsonArray citiesJson) {
        Graph graph = new Graph();
        for (int i = 0; i < citiesJson.size(); i++) {
            JsonObject city = citiesJson.get(i).getAsJsonObject();
            String name = city.get("name").getAsString();
            graph.addName(name);
            JsonArray neighbours = city.getAsJsonArray("neighbors");
            for (int j = 0; j < neighbours.size(); j++) {
                JsonObject neighbour = neighbours.get(j).getAsJsonObject();
                int to = neighbour.get("to").getAsInt() - 1;
                int cost = neighbour.get("cost").getAsInt();
                graph.addEdge(name, citiesJson.get(to).getAsJsonObject().get("name").getAsString(), cost);
            }
        }
        return graph;
    }

    private static JsonArray getShortestPath(JsonArray pathsJson, Graph graph) {
        JsonArray output = new JsonArray();
        for (int i = 0; i < pathsJson.size(); i++) {
            JsonObject path = pathsJson.get(i).getAsJsonObject();
            String start = path.get("start").getAsString();
            String end = path.get("end").getAsString();
            int cost = graph.DPQ(start, end);
            output.add(cost);
        }
        return output;
    }

    private static void writeToJson(Gson gson, JsonArray output) {
        JsonObject result = new JsonObject();
        result.add("costs", output);
        try (FileWriter fileWriter = new FileWriter(OUTPUT_PATH)) {
            gson.toJson(result, fileWriter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            Gson gson = new Gson();
            JsonArray jsonArray = gson.fromJson(new FileReader(INPUT_PATH), JsonArray.class);
            JsonObject jsonObject = jsonArray.get(0).getAsJsonObject();
            JsonArray citiesJson = jsonObject.getAsJsonArray("cities");
            JsonArray pathsJson = jsonObject.getAsJsonArray("paths");
            Graph graph = getGraph(citiesJson);
            JsonArray output = getShortestPath(pathsJson, graph);
            writeToJson(gson, output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
