package utils;

import namedEntities.NamedEntity;

import java.util.HashMap;
import java.util.List;

/*
(Persona (Milei, 2))
         (Messi, 1))
         
 Lugar   (Madrid, 2))
         (Cordoba, 1))

*/

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
                    orderByCat.replace(entity.getCategory(), actValue);
                }
            } else {
                HashMap<NamedEntity, Integer> newEntityCount = new HashMap<NamedEntity, Integer>();
                newEntityCount.put(entity, 1);
                orderByCat.putIfAbsent(entity.getCategory(), newEntityCount);
            }
        }

        return orderByCat;
    }

    public static HashMap<String, HashMap<NamedEntity, Integer>> topicForStats(List<NamedEntity> allEntities){
        HashMap<String, HashMap<NamedEntity, Integer>> orderByTop = new HashMap<>();
        for (NamedEntity entity : allEntities) {
            String[] topics = entity.getTopics();
            for (Integer i = 0; i < topics.length; i++){
                boolean existTop = orderByTop.containsKey(topics[i]);
                if (existTop){
                    HashMap<NamedEntity, Integer> actValue = orderByTop.get(topics[i]);
                    if (actValue.containsKey(entity)){
                        actValue.replace(entity, actValue.get(entity) + 1);
                        orderByTop.replace(topics[i], actValue);
                    } else {
                        actValue.put(entity, 1);
                        orderByTop.replace(topics[i], actValue);
                    }
                } else {
                    HashMap<NamedEntity, Integer> newEntityCount = new HashMap<NamedEntity, Integer>();
                    newEntityCount.put(entity, 1);
                    orderByTop.putIfAbsent(topics[i], newEntityCount);
                }
            }
        }
        return orderByTop;
    }

}
