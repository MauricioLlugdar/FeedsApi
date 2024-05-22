package utils;

import java.util.HashMap;
import java.util.Map;
import namedEntities.*;

public class PrintStats {
    public static void printCategoryStats(HashMap<String, HashMap<NamedEntity, Integer>> categoryStats){
        //Ya creamos las listas con los nombres de cada candidato que existe en el dict separados por categoria

        //Ahora printeamos cada una de los categorias con la cantidad de apariciones de cada candidato

        for (String category : categoryStats.keySet()){
            System.out.println("Category: " + category);
                for (NamedEntity elemEachCat : categoryStats.get(category).keySet()) {
                    System.out.println("    " + elemEachCat.getLabel() + " (" + categoryStats.get(category).get(elemEachCat) + ")");
                }

            System.out.println();
        }
    }

    public static void printTopicsStats(HashMap<String, Integer> politicsTopic,
                                        HashMap<String, Integer> sportsTopic, HashMap<String, Integer> economyTopic, HashMap<String, Integer> healthTopic,
                                        HashMap<String, Integer> technoTopic, HashMap<String, Integer> cultureTopic, HashMap<String, Integer> otherTopic){

        //Ya creamos las listas con los nombres de cada candidato que existe en el dict separados por Topico

        //Ahora printeamos cada una de los categorias con la cantidad de apariciones de cada candidato
        System.out.println("Topic: POLITICS");
        for (Map.Entry<String, Integer> polElem : politicsTopic.entrySet()) {
            System.out.println("    " + polElem.getKey() + " (" + polElem.getValue() + ")");
        }
        System.out.println();

        System.out.println("Topic: SPORTS");
        for (Map.Entry<String, Integer> sportElem : sportsTopic.entrySet()) {
            System.out.println("    " + sportElem.getKey() + " (" + sportElem.getValue() + ")");
        }
        System.out.println();

        System.out.println("Topic: ECONOMY");
        for (Map.Entry<String, Integer> ecoElem : economyTopic.entrySet()) {
            System.out.println("    " + ecoElem.getKey() + " (" + ecoElem.getValue() + ")");
        }
        System.out.println();

        System.out.println("Topic: HEALTH");
        for (Map.Entry<String, Integer> healthElem : healthTopic.entrySet()) {
            System.out.println("    " + healthElem.getKey() + " (" + healthElem.getValue() + ")");
        }
        System.out.println();

        System.out.println("Topic: TECHNOLOGY");
        for (Map.Entry<String, Integer> technoElem : technoTopic.entrySet()) {
            System.out.println("    " + technoElem.getKey() + " (" + technoElem.getValue() + ")");
        }
        System.out.println();

        System.out.println("Topic: CULTURE");
        for (Map.Entry<String, Integer> cultElem : cultureTopic.entrySet()) {
            System.out.println("    " + cultElem.getKey() + " (" + cultElem.getValue() + ")");
        }

        System.out.println("Topic: OTHER");
        for (Map.Entry<String, Integer> otherElem : otherTopic.entrySet()) {
            System.out.println("    " + otherElem.getKey() + " (" + otherElem.getValue() + ")");
        }
        System.out.println();
    }
}
