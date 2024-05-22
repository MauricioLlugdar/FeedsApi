package namedEntities.heuristics;

import java.text.Normalizer;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PreviousWordHeuristic implements FatherHeuristic {
    //Usando articulos conseguimos las palabras"
    @Override
    public List<String> extractCandidates(String text) {
        List<String> candidates = new ArrayList<>();

        text = text.replaceAll("[-+.^:,\"]", "");
        text = Normalizer.normalize(text, Normalizer.Form.NFD);
        text = text.replaceAll("\\p{M}", "");

        // (a +  b*) --> "b" si existe,  "abb" no existe
        // (ab*) "a" "ab" "abbbb" si existen
        // AZ
        //"(?<=\\b(?:a|ante|bajo|cabe|con|contra|de|desde|durante|en|entre|hacia|hasta|mediante|para|por|según|sin|so|sobre|tras|versus|vía))\\s+[A-Z][a-z]*\\b"
        Pattern pattern = Pattern.compile("(?<=el\\b|la\\b|los\\b|las\\b|un\\b|una\\b|unos\\b|unas\\b|Sr\\b|Sra\\b|Dr\\b|Dra\\b|Prof\\b)\\s[A-Z][a-z]+(?:\\s[A-Z][a-z]+)*");
        //https://docs.oracle.com/javase/6/docs/api/java/util/regex/Pattern.html ?: significa que no captura ese grupo, ya q no lo necesitamos realmente
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            candidates.add(matcher.group());
        }

        return candidates;
    }
}
