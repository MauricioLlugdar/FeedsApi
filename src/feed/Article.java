package feed;

public class Article {
    private String title, description, pubDate, link;

    public String print(){
        return "Title: " + title + "\n" +
                "Description: " + description + "\n" +
                "Publication Date: " + pubDate + "\n" +
                "Link: " + "\n" + link + "\n" + "*".repeat(80);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public void setLink(String link) {
        this.link = link;
    }
}

