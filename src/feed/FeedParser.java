package feed;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
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
            InputStream is = new ByteArrayInputStream(xmlData.getBytes("UTF-8")); // Obtenemos los bytes de xmlData para luego parsearlo
            Document doc = dBuilder.parse(is); // Parseo del InputStream creado en un Document
            NodeList nList = doc.getElementsByTagName("item"); // Obtenemos "nodos" a partir del documento, se crea un nodo por cada instancia de "item" que aparezca
            articles = new ArrayList<>();

            // Cada Nodo representa un artículo, se creará una instancia de Article en cada iteracion del for y se cargarán los datos correspondientes
            for (int nArt = 0; nArt < nList.getLength(); nArt++) {
                Article actArt = new Article();
                Element element = (Element) nList.item(nArt);
                //System.out.println("Este es el link = " + element.getElementsByTagName("description").item(0).getTextContent());

                //Seteamos el link: a cada nodo le buscamos instancias de "link" y nos fijamos la primer instancia. En caso de no existir, queda un espacio en blanco
                actArt.setLink((element.getElementsByTagName("link").item(0) !=null) ? element.getElementsByTagName("link").item(0).getTextContent() : "");
                //Seteamos el title: a cada nodo le buscamos instancias de "title" y nos fijamos la primer instancia. En caso de no existir, queda un espacio en blanco
                actArt.setTitle((element.getElementsByTagName("title").item(0) !=null) ? element.getElementsByTagName("title").item(0).getTextContent() : "");
                //Seteamos el Descripcion: a cada nodo le buscamos instancias de "Descripcion" y nos fijamos la primer instancia. En caso de no existir, queda un espacio en blanco
                actArt.setDescription((element.getElementsByTagName("description").item(0) !=null) ? element.getElementsByTagName("description").item(0).getTextContent() : "");
                //Seteamos el PubDate: a cada nodo le buscamos instancias de "PubDate" y nos fijamos la primer instancia. En caso de no existir, queda un espacio en blanco
                actArt.setPubDate((element.getElementsByTagName("pubDate").item(0) !=null) ? element.getElementsByTagName("pubDate").item(0).getTextContent() : "");
                //Agregagos el artículo a la lista
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
        connection.setRequestProperty("User-agent", "lab_paradigmas");
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
