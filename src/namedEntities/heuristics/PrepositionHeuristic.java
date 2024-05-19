package namedEntities.heuristics;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public class PrepositionHeuristic {
    public static List<String> extractCandidates(String text) {

        //Si hay preposicion a, ante, bajo, cabe, ... . Y con esto al siguiente nombre propio lo detectamos como una palabra de interes
        List<String> candidates = new ArrayList<>();

        text = text.replaceAll("[-+.^:,\"]", "");
        text = Normalizer.normalize(text, Normalizer.Form.NFD);
        text = text.replaceAll("\\p{M}", "");

        Pattern pattern = Pattern.compile("(?<=\\b(?:a|ante|bajo|cabe|con|contra|de|desde|durante|en|entre|hacia|hasta|mediante|para|por|según|sin|so|sobre|tras|versus|vía))\\s+[A-Z][a-z]*\\b");
        //https://docs.oracle.com/javase/6/docs/api/java/util/regex/Pattern.html ?: significa que no captura ese grupo, ya q no lo necesitamos realmente

        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            candidates.add(matcher.group());
        }
        return candidates;
    }
}
