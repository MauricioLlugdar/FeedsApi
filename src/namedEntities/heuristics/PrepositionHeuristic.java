package namedEntities.heuristics;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class PrepositionHeuristic {
    public List<String> extractCandidates(String text) {
        //Si hay preposicion a, ante, bajo, cabe, ... . Y con esto al siguiente nombre propio lo detectamos como una palabra de interes
        List<String> candidates = new ArrayList<>();

        text = text.replaceAll("[-+.^:,\"]", "");
        text = Normalizer.normalize(text, Normalizer.Form.NFD);
        text = text.replaceAll("\\p{M}", "");

        //Pattern pattern = Pattern.compile("[A-Z][a-z]+(?:\\s[A-Z][a-z]+)*");
        /*
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            candidates.add(matcher.group());
        }
        */
        return candidates;
    }
}
