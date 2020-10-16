package dev.notcacha.inferius.json;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonFileCreator {

    private File file;
    private final File folder;
    private final String fileName;

    public JsonFileCreator(File folder, String fileName)  {
        this.folder = folder;
        this.fileName = fileName;

        try {
            create();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void create() throws IOException {
        file = new File(folder,fileName + ".json");

        if(!file.exists()){
            file.createNewFile();
        }

    }

    public void writeJson(JSONObject jsonObject) {
        FileWriter writer;
        try {
            writer = new FileWriter(file);
            writer.write(jsonObject.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void writeJson(String jsonObject){
        FileWriter writer;
        try {
            writer = new FileWriter(file);
            writer.write(jsonObject);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getJsonString(){
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(fileReader);

        String line;

        StringBuilder builder = new StringBuilder();

        try {
            while((line = reader.readLine()) != null){
                builder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public JSONObject toJsonObject() {
        FileReader fileReader;
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Error in reader file", e);
        }

        BufferedReader reader = new BufferedReader(fileReader);

        String line;

        StringBuilder builder = new StringBuilder();

        try {
            if ((line = reader.readLine()) == null){
                builder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) new JSONParser().parse(builder.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    public static JsonFileCreator create(File folder, String name) {
        return new JsonFileCreator(folder, (name.endsWith(".json") ? name : name + ".json"));
    }
}
