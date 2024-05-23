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
            for (NamedEntity entity : categoryStats.get(category).keySet()) {
                System.out.println("    " + entity.getLabel() + " (" + categoryStats.get(category).get(entity) + ")");
            }
            System.out.println();
        }
    }

    public static void printTopicsStats(HashMap<String, HashMap<NamedEntity, Integer>> topicStats) {

        //Ya creamos las listas con los nombres de cada candidato que existe en el dict separados por Topico

        //Ahora printeamos cada una de los topicos con la cantidad de apariciones de cada candidato

        for (String topic : topicStats.keySet()) {
            System.out.println("Topico: " + topic);
            for (NamedEntity entity : topicStats.get(topic).keySet()) {
                System.out.println("    " + entity.getLabel() + "  (" + topicStats.get(topic).get(entity) + ")");
            }
            System.out.println();
        }
    }

}
