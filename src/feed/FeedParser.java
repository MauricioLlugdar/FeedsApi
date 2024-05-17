package feed;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FeedParser {

    public static List<Article> parseXML(String xmlData) {
        List<Article> articles = null;
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlData);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("item");
            articles = new ArrayList<Article>();

            for (int nArt = 0; nArt < nList.getLength(); nArt++) {
                Article actArt = new Article();
                Element item = (Element) nList.item(nArt);
                actArt.setLink(item.getAttribute("link"));
                actArt.setTitle(item.getAttribute("title"));
                actArt.setDescription(item.getAttribute("description"));
                actArt.setPubDate(item.getAttribute("pubDate"));
                articles.add(actArt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articles;
    }

    public static String fetchFeed(String feedURL) throws MalformedURLException, IOException, Exception {

        URL url = new URL(feedURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");

        // Si todos los grupos usan el mismo user-agent, el servidor puede bloquear las solicitudes.
        connection.setRequestProperty("grupo1", "lab_paradigmas");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);

        int status = connection.getResponseCode();
        if (status != 200) {
            throw new Exception("HTTP error code: " + status);
        } else {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            connection.disconnect();
            return content.toString();
        }
    }
}
