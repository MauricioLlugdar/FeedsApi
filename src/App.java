import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import feed.*;
import namedEntities.*;
import namedEntities.heuristics.*;
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

        String feedKey = config.getFeedKey(); //lmnoti p12eco ...
        for (FeedsData feed : feedsDataArray) {
            if (feedKey.equals(feed.getLabel()) || feedKey.equals("all")) {
                try {
                    var xml = FeedParser.fetchFeed(feed.getUrl());
                    allArticles.addAll(FeedParser.parseXML(xml));
                } catch (Exception e) {
                    e.printStackTrace();
                    System.exit(1);
                }
            }
        }

        if (config.getPrintFeed()) {
            System.out.println("Printing feed(s) ");
            for(var i = 0; i < allArticles.size(); ++i){
                System.out.println(allArticles.get(i).print());
            }
        }

        if (config.getComputeNamedEntities()) {
            // TODO: complete the message with the selected heuristic name
            System.out.println("Computing named entities using " + config.getHeuristic());
            // TODO: compute named entities using the selected heuristic
            
            List<String> candidatesFrTitle;
            List<String> candidatesFrDescription;
            List<String> candidates = new ArrayList<>();
            
            FatherHeuristic actHeuristic;

            switch(config.getHeuristic()){
                case "Cap":
                    actHeuristic = new CapitalizedWordHeuristic();
                    break;
                case "Prev":
                    actHeuristic = new PreviousWordHeuristic();
                    break;
                case "Prep":
                    actHeuristic = new PrepositionHeuristic();
                    break;
                default:
                    actHeuristic = new CapitalizedWordHeuristic();
                    System.out.println("NO ES HEURISTICA VALIDA");
                    System.exit(1);
            }
            
            for (int i = 0; i < allArticles.size(); i++) {
                candidatesFrTitle = actHeuristic.extractCandidates(allArticles.get(i).getTitle());
                candidatesFrDescription = actHeuristic.extractCandidates(allArticles.get(i).getDescription());
                candidates.addAll(candidatesFrTitle);
                candidates.addAll(candidatesFrDescription);
            }

            System.out.println("Number of candidates of all Articles : \n" + candidates.size());

            for (String candidate : candidates){
                System.out.println(candidate);
            }

            // TODO: Print stats
            System.out.println("\nStats: ");
            System.out.println("-".repeat(80));

            List<NamedEntity> dictionaryEnt = JSONtoEntity.parseJsonEntity("src/data/dictionary.json");

            List<NamedEntity> filterCandidates = JSONtoEntity.validCandidates(candidates, dictionaryEnt);

            /*
            //Divido por categorias
            HashMap<String, Integer> locationCategory = new HashMap<String, Integer>();
            HashMap<String, Integer> personCategory = new HashMap<String, Integer>();
            HashMap<String, Integer> organizationCategory = new HashMap<String, Integer>();
            HashMap<String, Integer> otherCategory = new HashMap<String, Integer>();
            HashMap<String, Integer> eventCategory = new HashMap<String, Integer>();

            //Divido por topicos
             */
            /*
            HashMap<String, Integer> politicsTopic = new HashMap<String, Integer>();
            HashMap<String, Integer> sportsTopic = new HashMap<String, Integer>();
            HashMap<String, Integer> economyTopic = new HashMap<String, Integer>();
            HashMap<String, Integer> healthTopic = new HashMap<String, Integer>();
            HashMap<String, Integer> technoTopic = new HashMap<String, Integer>();
            HashMap<String, Integer> cultureTopic = new HashMap<String, Integer>();
            HashMap<String, Integer> otherTopic = new HashMap<String, Integer>();
            */
            String orderOfStats = config.getStatFormat();
            System.out.println("Computing stats using: " + orderOfStats);
            System.out.println("-".repeat(80));

            switch (orderOfStats){
                case "Topic":
                    HashMap<String, HashMap<NamedEntity, Integer>> statsTopic = new HashMap<String, HashMap<NamedEntity, Integer> >();
                    statsTopic = FillForStats.topicForStats(filterCandidates);
                    PrintStats.printTopicsStats(statsTopic);
                    break;
                case "Cat":
                    HashMap<String, HashMap<NamedEntity, Integer>> statsCategory = new HashMap<String, HashMap<NamedEntity, Integer> >();
                    statsCategory = FillForStats.categoryForStats(filterCandidates);
                    PrintStats.printCategoryStats(statsCategory);
                    break;
                default:
                    System.out.println("NO ES ESTADÍSTICA VÁLIDO");
                    System.exit(1);

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
