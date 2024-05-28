package utils;

import java.util.HashMap;
import java.util.List;

import namedEntities.*;

public class Printable {
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

    public static void printHelp(List<FeedsData> feedsDataArray) {
        System.out.println("Usage: make run ARGS=\"[OPTION]\"");
        System.out.println("Options:");
        System.out.println("  -h, --help: Show this help message and exit");
        System.out.println("  -f, --feed <feedKey>:                Fetch and process the feed with");
        System.out.println("                                       the specified key");
        System.out.println("                                       Available feed keys are: ");
        for (FeedsData feedData : feedsDataArray) {
            System.out.println("                                       " + feedData.getLabel());
        }
        System.out.println("  -ne, --named-entity <heuristicName>: Use the specified heuristic to extract");
        System.out.println("                                       named entities");
        System.out.println("                                       Available heuristic names are: ");
        System.out.println("                                       Cap: Capitalized Words");
        System.out.println("                                       Prep: Capitalized words that have a preposition right before");
        System.out.println("                                       IA: Capitalized words chosen statiscally by an IA");
        // TODO: Print the available heuristics with the following format
        System.out.println("  -pf, --print-feed:                   Print the fetched feed");
        System.out.println("  -sf, --stats-format <format>:        Print the stats in the specified format");
        System.out.println("                                       Available formats are: ");
        System.out.println("                                       Cat: Category-wise stats");
        System.out.println("                                       Topic: Topic-wise stats");
    }

}
