import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import feed.*;
import namedEntities.NamedEntity;
import namedEntities.heuristics.CapitalizedWordHeuristic;
import namedEntities.heuristics.PrepositionHeuristic;
import namedEntities.heuristics.PreviousWordHeuristic;
import utils.*;

public class App {

    public static void main(String[] args) throws IOException {

        List<FeedsData> feedsDataArray = new ArrayList<>();
        try {
            feedsDataArray = JSONParser.parseJsonFeedsData("src/data/feeds.json");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        UserInterface ui = new UserInterface();
        Config config = ui.handleInput(args);

        run(config, feedsDataArray);
    }

    // TODO: Change the signature of this function if needed
    private static void run(Config config, List<FeedsData> feedsDataArray) throws IOException {

        if (feedsDataArray == null || feedsDataArray.size() == 0) {
            System.out.println("No feeds data found");
            return;
        }

        List<Article> allArticles = new ArrayList<>();
        // TODO: Populate allArticles with articles from corresponding feeds
        String feedUrl = "";
        List<Article> articlesFeed;
        Boolean fParam = true;
        System.out.println(config.getFeedKey());
        switch (config.getFeedKey()) {
            case "p12pais":
                try {
                    feedUrl = FeedParser.fetchFeed(feedsDataArray.get(0).getUrl());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "p12eco":
                try {
                    feedUrl = FeedParser.fetchFeed(feedsDataArray.get(1).getUrl());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "lmgral:":
                try {
                    feedUrl = FeedParser.fetchFeed(feedsDataArray.get(2).getUrl());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "lmnoti":
                try {
                    feedUrl = FeedParser.fetchFeed(feedsDataArray.get(3).getUrl());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            case "all": //All feeds
                fParam = false;
                for (var i = 0; i < feedsDataArray.size(); ++i) {
                    try {
                        feedUrl = FeedParser.fetchFeed(feedsDataArray.get(i).getUrl());
                        articlesFeed = FeedParser.parseXML(feedUrl);
                        //Agrego todos los articulos de cada uno de los feeds en allArticles
                        allArticles.addAll(articlesFeed);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                break;
        }

        if(fParam){
            articlesFeed = FeedParser.parseXML(feedUrl);
            //Agrego todos los articulos del feed en particular
            allArticles.addAll(articlesFeed);
        }

        if (config.getPrintFeed()) {
            System.out.println("Printing feed(s) ");
            for(var i = 0; i < allArticles.size(); ++i){
                System.out.println(allArticles.get(i).print());
            }
        }

        if (config.getComputeNamedEntities()) {
            // TODO: complete the message with the selected heuristic name
            System.out.println("Computing named entities using ");

            // TODO: compute named entities using the selected heuristic

            List<String> candidatesFrTitle;
            List<String> candidatesFrDescription;
            List<String> candidates = new ArrayList<>();
            for (int i = 0; i < allArticles.size(); i++) {
                candidatesFrTitle = PreviousWordHeuristic.extractCandidates(allArticles.get(i).getTitle());
                candidatesFrDescription = PreviousWordHeuristic.extractCandidates(allArticles.get(i).getDescription());
                candidates.addAll(candidatesFrTitle);
                candidates.addAll(candidatesFrDescription);
            }
            System.out.println("Number of candidates of all Articles : \n" + candidates.size());

            // TODO: Print stats
            System.out.println("\nStats: ");
            System.out.println("-".repeat(80));

            List<NamedEntity> dictionaryEnt = JSONtoEntity.parseJsonEntity("src/data/dictionary.json");

            //System.out.println(dictionaryEnt.get(0).getLabel());
            //dictionaryEnt.get(0).printKeywords(dictionaryEnt.get(0).getKeywords());

            HashMap<String, Integer> locationEntities = new HashMap<String, Integer>();
            HashMap<String, Integer> personEntities = new HashMap<String, Integer>();
            HashMap<String, Integer> organizationEntities = new HashMap<String, Integer>();
            HashMap<String, Integer> otherEntities = new HashMap<String, Integer>();
            HashMap<String, Integer> eventEntities = new HashMap<String, Integer>();


            for (int i = 0; i < candidates.size(); i++) {

                for (int j = 0; j < dictionaryEnt.size(); j++) {
                    NamedEntity actDictEnt = dictionaryEnt.get(j);
                    String actKey = actDictEnt.getLabel();
                    Integer actValue;
                    //System.out.println(actDictEnt.getLabel());
                    if(actDictEnt.containsKeyword(candidates.get(i))){
                        switch (actDictEnt.getCategory()){
                            case "LOCATION":
                                if(locationEntities.containsKey(actDictEnt.getLabel())){
                                    actValue = locationEntities.get(actKey);
                                    locationEntities.replace(actKey, actValue, actValue+1);
                                }else {
                                    locationEntities.put(actKey, 1);
                                }
                                break;
                            case "ORGANIZATION":
                                if(organizationEntities.containsKey(actDictEnt.getLabel())){
                                    actValue = organizationEntities.get(actKey);
                                    organizationEntities.replace(actKey, actValue, actValue+1);
                                }else {
                                    organizationEntities.put(actKey, 1);
                                }
                                break;
                            case "PERSON":
                                if(personEntities.containsKey(actDictEnt.getLabel())){
                                    actValue = personEntities.get(actKey);
                                    personEntities.replace(actKey, actValue, actValue+1);
                                }else {
                                    personEntities.put(actKey, 1);
                                }
                                break;
                            case "EVENT":
                                if(eventEntities.containsKey(actDictEnt.getLabel())){
                                    actValue = eventEntities.get(actKey);
                                    eventEntities.replace(actKey, actValue, actValue+1);
                                }else {
                                    eventEntities.put(actKey, 1);
                                }
                                break;
                            case "OTHER":
                                if(otherEntities.containsKey(actDictEnt.getLabel())){
                                    actValue = otherEntities.get(actKey);
                                    otherEntities.replace(actKey, actValue, actValue+1);
                                }else {
                                    otherEntities.put(actKey, 1);
                                }

                        }
                    }
                }

            }
            //Ya creamos las listas con los nombres de cada candidato que existe en el dict separados por categoria

            //Ahora printeamos cada una de los categorias con la cantidad de apariciones de cada candidato
            System.out.println("Category: ORGANIZATION");

            for (Map.Entry<String, Integer> orgElem : organizationEntities.entrySet()) {
                System.out.println("    " + orgElem.getKey() + " (" + orgElem.getValue() + ")");
            }
            System.out.println();
            System.out.println("Category: LOCATION");

            for (Map.Entry<String, Integer> locElem : locationEntities.entrySet()) {
                System.out.println("    " + locElem.getKey() + " (" + locElem.getValue() + ")");
            }
            System.out.println();
            System.out.println("Category: OTHER");

            for (Map.Entry<String, Integer> othElem : otherEntities.entrySet()) {
                System.out.println("    " + othElem.getKey() + " (" + othElem.getValue() + ")");
            }
            System.out.println();
            System.out.println("Category: PERSON");

            for (Map.Entry<String, Integer> perElem : personEntities.entrySet()) {
                System.out.println("    " + perElem.getKey() + " (" + perElem.getValue() + ")");
            }
            System.out.println();
            System.out.println("Category: EVENT");

            for (Map.Entry<String, Integer> evElem : eventEntities.entrySet()) {
                System.out.println("    " + evElem.getKey() + " (" + evElem.getValue() + ")");
            }
            System.out.println();
        }
    }

    // TODO: Maybe relocate this function where it makes more sense
    private static void printHelp(List<FeedsData> feedsDataArray) {
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
        // TODO: Print the available heuristics with the following format
        System.out.println("                                       <name>: <description>");
        System.out.println("  -pf, --print-feed:                   Print the fetched feed");
        System.out.println("  -sf, --stats-format <format>:        Print the stats in the specified format");
        System.out.println("                                       Available formats are: ");
        System.out.println("                                       cat: Category-wise stats");
        System.out.println("                                       topic: Topic-wise stats");
    }

}
