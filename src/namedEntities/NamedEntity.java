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

    public String getCategory() {
        return Category;
    }

    public void printKeywords(String[] keywords){
        for (int i = 0; i < keywords.length; i++) {
            System.out.println(keywords[i]);
        }
    }

    public String[] getTopics() {
        return Topics;
    }

    public boolean containsKeyword(String actKey){
        for (int i = 0; i < this.keywords.length; i++) {
            if(this.keywords[i].trim().equalsIgnoreCase(actKey.trim())){
                return true;
            }
        }
        return false;
    }

}