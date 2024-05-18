package namedEntities.heuristics;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PreviousWordHeuristic {
    //Usando texto previo como " en la (Primera letra mayuscula)" podemos darnos cuenta que la palabra con mayuscula es un lugar"
    public List<String> extractCandidates(String text) {
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
