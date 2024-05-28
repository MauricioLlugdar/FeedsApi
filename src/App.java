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

        if (config.getPrintHelp()){
            Printable.printHelp(feedsDataArray);
            System.exit(0);
        }

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
                case "IA":
                    actHeuristic = new IAPrevWordHeuristic();
                    break;
                case "Prep":
                    actHeuristic = new PrepositionHeuristic();
                    break;
                default:
                    actHeuristic = new CapitalizedWordHeuristic();
                    System.out.println("NO ES HEURISTICA VALIDA");
                    Printable.printHelp(feedsDataArray);
                    System.exit(0);
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
            
            String orderOfStats = config.getStatFormat();
            System.out.println("Computing stats using: " + orderOfStats);
            System.out.println("-".repeat(80));

            switch (orderOfStats){
                case "Topic":
                    HashMap<String, HashMap<NamedEntity, Integer>> statsTopic = new HashMap<String, HashMap<NamedEntity, Integer> >();
                    statsTopic = FillForStats.topicForStats(filterCandidates);
                    Printable.printTopicsStats(statsTopic);
                    break;
                case "Cat":
                    HashMap<String, HashMap<NamedEntity, Integer>> statsCategory = new HashMap<String, HashMap<NamedEntity, Integer> >();
                    statsCategory = FillForStats.categoryForStats(filterCandidates);
                    Printable.printCategoryStats(statsCategory);
                    break;
                default:
                    System.out.println("NO ES ESTADÍSTICA VÁLIDO");
                    System.exit(1);
            }
        }
    }
}
