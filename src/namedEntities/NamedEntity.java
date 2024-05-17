package namedEntities;

public class NamedEntity{
    private String label, Category, keywords;
    private String[] Topics;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String[] getTopics() {
        return Topics;
    }

    public void setTopics(String[] topics) {
        Topics = topics;
    }
}