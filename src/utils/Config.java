package utils;

public class Config {
    private boolean printHelp = false;
    private boolean printFeed = false;
    private boolean computeNamedEntities = false;
    private String feedKey;
    private String heuristic;
    private String statFormat;

    public Config(boolean printHelp, boolean printFeed, boolean computeNamedEntities, String feedKey, String heuristic, String statFormat) {
        this.printHelp = printHelp;
        this.printFeed = printFeed;
        this.computeNamedEntities = computeNamedEntities;
        this.feedKey = feedKey;
        this.heuristic = heuristic;
        this.statFormat = statFormat;
    }

    public boolean getPrintHelp() {
        return printHelp;
    }

    public boolean getPrintFeed() {
        return printFeed;
    }

    public boolean getComputeNamedEntities() {
        return computeNamedEntities;
    }

    public String getFeedKey() {
        return feedKey;
    }

    public String getHeuristic() {
        return heuristic;
    }

    public String getStatFormat() {
        return statFormat;
    }
}
