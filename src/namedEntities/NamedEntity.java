package namedEntities;

public class NamedEntity{
    private String label, Category;
    private String[] Topics, keywords;

    public NamedEntity(){};

    public NamedEntity(String label, String Category, String[] Topics, String[] keywords){
        this.label =label;
        this.Category = Category;
        this.Topics = Topics;
        this.keywords = keywords;
    };

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

    public String[] getKeywords() {
        return keywords;
    }

    public void setKeywords(String[] keywords) {
        this.keywords = keywords;
    }

    public String[] getTopics() {
        return Topics;
    }

    public void setTopics(String[] topics) {
        Topics = topics;
    }

    public boolean containsKeyword(String actKey){
        for (int i = 0; i < keywords.length; i++) {
            if(keywords[i] == actKey){
                return true;
            }
        }
        return false;
    }

}