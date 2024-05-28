package namedEntities.heuristics;

import java.text.Normalizer;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IAPrevWordHeuristic implements FatherHeuristic {
    @Override
    public List<String> extractCandidates(String text) {

        List<String> candidates = new ArrayList<>();

        text = text.replaceAll("[-+.^:,\"]", "");
        text = Normalizer.normalize(text, Normalizer.Form.NFD);
        text = text.replaceAll("\\p{M}", "");

        String articles = "el|la|los|las|un|una|unos|unas";
        String preposition = "de|en|para|con|por|sobre|entre|sin|hasta";
        String adjetive = "mi|tu|su|nuestro|nuestra|nuestros|nuestras|vuestro|vuestra|vuestros|vuestras|grande|famoso|famosa|querido|querida|pequeño|pequeña";
        String titlesOrPosition = "Señor|Señora|Doctor|Doctora|Profesor|Profesora|Ingeniero|Ingeniera|Presidente|Presidenta|Rey|Reina";
        String pronouns = "este|esta|estos|estas|aquel|aquella|aquellos|aquellas";
        String numerals = "uno|una|dos|tres|primero|primera|segundo|segunda|tercero|tercera";
        String etcetera = "a través de|junto a|al lado de|cerca de|lejos de|en medio de|enfrente de|que|aunque|pero|sin embargo|mientras|además|aquí|allí|ahora|entonces|anteriormente|posteriormente";

        String allPrevWords = articles+preposition+adjetive+titlesOrPosition+pronouns+numerals+etcetera;

        Pattern pattern = Pattern.compile("(?<=\\b "+ allPrevWords +")\\s+[A-Z][a-z]+(?:\\s[A-Z][a-z]+)?\\b");
        //https://docs.oracle.com/javase/6/docs/api/java/util/regex/Pattern.html ?: significa que no captura ese grupo, ya q no lo necesitamos realmente

        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            candidates.add(matcher.group());
        }
        return candidates;
    }
}

