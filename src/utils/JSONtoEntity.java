package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import namedEntities.NamedEntity;
import org.json.JSONArray;
import org.json.JSONObject;


public class JSONtoEntity {

    static public List<NamedEntity> parseJsonEntity(String jsonFilePath) throws IOException {
        String jsonData = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
        List<NamedEntity> entity = new ArrayList<>();

        JSONArray jsonArray = new JSONArray(jsonData);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            String label = jsonObject.getString("label");

            String category = jsonObject.getString("Category");

            JSONArray TopicsArrayJson = jsonObject.getJSONArray("Topics");
            String[] topicsArray = new String[TopicsArrayJson.length()];
            for (int j = 0; j < TopicsArrayJson.length(); j++) {
                topicsArray[j] = TopicsArrayJson.get(j).toString();
            }

            JSONArray keywordsArrayJson = jsonObject.getJSONArray("keywords");
            String[] keywordsArray = new String[TopicsArrayJson.length()];
            for (int j = 0; j < TopicsArrayJson.length(); j++) {
                keywordsArray[j] = keywordsArrayJson.get(j).toString();
            }

            entity.add(new NamedEntity(label, category, topicsArray, keywordsArray));
        }
        return entity;
    }

}

