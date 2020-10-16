package dev.notcacha.inferius.json;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonFileCreator {

    private File file;
    private final File folder;
    private final String fileName;

    public JsonFileCreator(File folder, String fileName) {
        this.folder = folder;
        this.fileName = fileName;

        try {
            create();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void create() throws IOException {
        file = new File(folder, fileName + ".json");

        if (!file.exists()) {
            file.createNewFile();
        }

    }

    public void writeJson(JSONObject jsonObject) throws IOException {
        FileWriter writer = new FileWriter(file);
        writer.write(jsonObject.toString());
        writer.flush();
        writer.close();

    }

    public void writeJson(String jsonObject) throws IOException {
        FileWriter writer = new FileWriter(file);
        writer.write(jsonObject);
        writer.flush();
        writer.close();

    }

    public String getJsonString() throws IOException {
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);

        String line;

        StringBuilder builder = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }

        return builder.toString();
    }

    public JSONObject toJsonObject() throws IOException, ParseException {
        FileReader fileReader = new FileReader(file);

        BufferedReader reader = new BufferedReader(fileReader);

        String line;

        StringBuilder builder = new StringBuilder();

        if ((line = reader.readLine()) == null) {
            builder.append(line);
        }


        return (JSONObject) new JSONParser().parse(builder.toString());
    }

    public static JsonFileCreator create(File folder, String name) {
        return new JsonFileCreator(folder, (name.endsWith(".json") ? name : name + ".json"));
    }
}
