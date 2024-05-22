package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import namedEntities.*;
import org.json.JSONArray;
import org.json.JSONObject;


public class JSONtoEntity {

    static public List<NamedEntity> parseJsonEntity(String jsonFilePath) throws IOException {
        String jsonData = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
        List<NamedEntity> entities = new ArrayList<>();

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
            String[] keywordsArray = new String[keywordsArrayJson.length()];
            for (int j = 0; j < keywordsArrayJson.length(); j++) {
                keywordsArray[j] = keywordsArrayJson.get(j).toString();
            }

            NamedEntity dictEntity = switch (category){
                case "PERSON" -> new PERSON(label, topicsArray, keywordsArray, "", "", "");
                case "LOCATION" -> new LOCATION(label, topicsArray, keywordsArray, "", "", "");
                case "ORGANIZATION" -> new ORGANIZATION(label, topicsArray, keywordsArray, "", "");
                case "EVENT" -> new EVENT(label, topicsArray, keywordsArray, "", "");
                case "OTHER" -> new OTHER(label, topicsArray, keywordsArray, "");
                    default -> null;
            };
            if(dictEntity != null){
                entities.add(dictEntity);
            }

        }
        return entities;
    }

    static public List<NamedEntity> validCandidates(List<String> candidates, List<NamedEntity> dict){
        List<NamedEntity> validCandidates = new ArrayList<>();
        for (String actCand : candidates){
            for(NamedEntity actDict : dict){
                if(actDict.containsKeyword(actCand)){

                    validCandidates.add(actDict);
                }
            }
        }
        return validCandidates;
    }
}

