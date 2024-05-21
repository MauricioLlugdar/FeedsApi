import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import feed.*;
import namedEntities.NamedEntity;
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
            //System.out.println("Number of candidates of all Articles : \n" + candidates.size());

            // TODO: Print stats
            System.out.println("\nStats: ");
            System.out.println("-".repeat(80));

            List<NamedEntity> dictionaryEnt = JSONtoEntity.parseJsonEntity("src/data/dictionary.json");

            //Divido por categorias
            HashMap<String, Integer> locationCategory = new HashMap<String, Integer>();
            HashMap<String, Integer> personCategory = new HashMap<String, Integer>();
            HashMap<String, Integer> organizationCategory = new HashMap<String, Integer>();
            HashMap<String, Integer> otherCategory = new HashMap<String, Integer>();
            HashMap<String, Integer> eventCategory = new HashMap<String, Integer>();
            //Divido por topicos
            HashMap<String, Integer> politicsTopic = new HashMap<String, Integer>();
            HashMap<String, Integer> sportsTopic = new HashMap<String, Integer>();
            HashMap<String, Integer> economyTopic = new HashMap<String, Integer>();
            HashMap<String, Integer> healthTopic = new HashMap<String, Integer>();
            HashMap<String, Integer> technoTopic = new HashMap<String, Integer>();
            HashMap<String, Integer> cultureTopic = new HashMap<String, Integer>();
            HashMap<String, Integer> otherTopic = new HashMap<String, Integer>();

            String orderOfStats = config.getStatFormat();

            switch (orderOfStats){
                case "topic":
                    FillForStats.topicsForStats(candidates, dictionaryEnt, politicsTopic, sportsTopic, economyTopic, healthTopic, technoTopic, cultureTopic, otherTopic);

                    //Printeamos las estadisticas por Topico
                    PrintStats.printTopicsStats(politicsTopic, sportsTopic, economyTopic, healthTopic, technoTopic, cultureTopic, otherTopic);
                    break;
                default:
                    //Filling category for Stats
                    FillForStats.categoryForStats(candidates,dictionaryEnt,locationCategory,personCategory,organizationCategory,otherCategory,eventCategory);

                    //Printeamos las estadisticas por Categoria
                    PrintStats.printCategoryStats(locationCategory,personCategory,organizationCategory,otherCategory,eventCategory);
                    break;
            }


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
