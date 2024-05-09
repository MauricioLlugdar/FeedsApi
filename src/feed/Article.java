package feed;

public class Article {
    private String title, description, pubDate, link;

    public String print(){
        return "Title: " + title + "\n" +
                "Description: " + description + "\n" +
                "Publication Date: " + pubDate + "\n" +
                "Link: " + "\n" + link + "\n" + "*".repeat(80);
    }
}

