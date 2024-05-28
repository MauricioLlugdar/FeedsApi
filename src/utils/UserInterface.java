package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserInterface {

    private HashMap<String, String> optionDict;//Hashmap<String, String> optionDict.get("Cage") //Me devuelve Nico

    private List<Option> options;

    public UserInterface() {
        options = new ArrayList<Option>();
        options.add(new Option("-h", "--help", 0));
        options.add(new Option("-f", "--feed", 1));
        options.add(new Option("-ne", "--named-entity", 1));
        options.add(new Option("-pf", "--print-feed", 0));
        options.add(new Option("-sf", "--stats-format", 1));

        optionDict = new HashMap<String, String>();
    }

    public Config handleInput(String[] args) {
        for (Integer i = 0; i < args.length; i++) {
            for (Option option : options) {
                if (option.getName().equals(args[i]) || option.getLongName().equals(args[i])) {
                    if (option.getnumValues() == 0) {
                        optionDict.put(option.getName(), null);
                    } else {
                        if (i + 1 < args.length && !args[i + 1].startsWith("-")) {
                            optionDict.put(option.getName(), args[i + 1]);
                            System.out.println("Nombre en el optionDict: " + option.getName());
                            System.out.println(optionDict.get(option.getName()));
                            i++;
                        } else {
                            System.out.println("Invalid inputs");
                            System.exit(1);
                        }
                    }
                }
            }
        }
        Boolean printHelp = optionDict.containsKey("-h");

        Boolean printFeed = optionDict.containsKey("-pf");

        Boolean computeNamedEntities = optionDict.containsKey("-ne");

        Boolean statsFormat = optionDict.containsKey("-sf");
        
        String heuristic = "";
        if (computeNamedEntities){
            heuristic = optionDict.get("-ne");
        } else {
            printFeed = true;
        }
        String feedKey = optionDict.get("-f");
        if(feedKey == null){
           feedKey = "all";
        }
        //Actual stat format
        String sf = "Cat";
        if(statsFormat){
            sf = optionDict.get("-sf"); // cat or topic
        }

        return new Config(printHelp, printFeed, computeNamedEntities, feedKey, heuristic, sf);
    }
}


