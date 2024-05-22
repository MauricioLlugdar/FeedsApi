package utils;

import namedEntities.NamedEntity;

import java.util.HashMap;
import java.util.List;

public class FillForStats {
    //Creando la lista de categorias para luego imprimirlas
    public static HashMap<String, HashMap<NamedEntity, Integer>> categoryForStats(List<NamedEntity> allEntities){
        HashMap<String, HashMap<NamedEntity, Integer>> orderByCat = new HashMap<>();
        for (NamedEntity entity : allEntities) {
            boolean existCat = orderByCat.containsKey(entity.getCategory());
            if(existCat){
                HashMap<NamedEntity, Integer> actValue = orderByCat.get(entity.getCategory());
                //String actLabel = entity.getLabel();
                if(actValue.containsKey(entity)){
                    actValue.replace(entity, actValue.get(entity) + 1);
                    orderByCat.replace(entity.getCategory(), actValue);
                } else {
                    actValue.put(entity, 1);
                    orderByCat.put(entity.getCategory(), actValue);
                }
            } else {
                HashMap<NamedEntity, Integer> newEntityCount = new HashMap<NamedEntity, Integer>();
                newEntityCount.put(entity, 1);
                orderByCat.putIfAbsent(entity.getCategory(), newEntityCount);
            }
        }

        return orderByCat;
    }

    public static void topicsForStats(List<String> candidates, List<NamedEntity> dictionaryEnt, HashMap<String, Integer> politicsTopic,
                                      HashMap<String, Integer> sportsTopic, HashMap<String, Integer> economyTopic, HashMap<String, Integer> healthTopic,
                                      HashMap<String, Integer> technoTopic, HashMap<String, Integer> cultureTopic, HashMap<String, Integer> otherTopic){
        for (int i = 0; i < candidates.size(); i++) {

            for (int j = 0; j < dictionaryEnt.size(); j++) {
                NamedEntity actDictEnt = dictionaryEnt.get(j);
                String actKey = actDictEnt.getLabel();
                Integer actValue;
                //System.out.println(actDictEnt.getLabel());
                if(actDictEnt.containsKeyword(candidates.get(i))){
                    String[] actTopics = actDictEnt.getTopics();
                    for (int k = 0; k < actTopics.length; k++) {
                        switch (actTopics[k]) {
                            case "POLITICS":
                                if (politicsTopic.containsKey(actDictEnt.getLabel())) {
                                    actValue = politicsTopic.get(actKey);
                                    politicsTopic.replace(actKey, actValue, actValue + 1);
                                } else {
                                    politicsTopic.put(actKey, 1);
                                }
                                break;
                            case "SPORTS":
                                if (sportsTopic.containsKey(actDictEnt.getLabel())) {
                                    actValue = sportsTopic.get(actKey);
                                    sportsTopic.replace(actKey, actValue, actValue + 1);
                                } else {
                                    sportsTopic.put(actKey, 1);
                                }
                                break;
                            case "ECONOMY":
                                if (economyTopic.containsKey(actDictEnt.getLabel())) {
                                    actValue = economyTopic.get(actKey);
                                    economyTopic.replace(actKey, actValue, actValue + 1);
                                } else {
                                    economyTopic.put(actKey, 1);
                                }
                                break;
                            case "HEALTH":
                                if (healthTopic.containsKey(actDictEnt.getLabel())) {
                                    actValue = healthTopic.get(actKey);
                                    healthTopic.replace(actKey, actValue, actValue + 1);
                                } else {
                                    healthTopic.put(actKey, 1);
                                }
                                break;
                            case "TECHNOLOGY":
                                if (technoTopic.containsKey(actDictEnt.getLabel())) {
                                    actValue = technoTopic.get(actKey);
                                    technoTopic.replace(actKey, actValue, actValue + 1);
                                } else {
                                    technoTopic.put(actKey, 1);
                                }
                                break;
                            case "CULTURE":
                                if (cultureTopic.containsKey(actDictEnt.getLabel())) {
                                    actValue = cultureTopic.get(actKey);
                                    cultureTopic.replace(actKey, actValue, actValue + 1);
                                } else {
                                    cultureTopic.put(actKey, 1);
                                }
                                break;
                            case "OTHER":
                                if (otherTopic.containsKey(actDictEnt.getLabel())) {
                                    actValue = otherTopic.get(actKey);
                                    otherTopic.replace(actKey, actValue, actValue + 1);
                                } else {
                                    otherTopic.put(actKey, 1);
                                }

                        }
                    }
                }
            }

        }
    }

}
