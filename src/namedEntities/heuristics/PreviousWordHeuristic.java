package namedEntities.heuristics;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public class PreviousWordHeuristic {
    //Usando texto previo como " en la (Primera letra mayuscula)" podemos darnos cuenta que la palabra con mayuscula es un lugar"
    public static List<String> extractCandidates(String text) {
        List<String> candidates = new ArrayList<>();

        text = text.replaceAll("[-+.^:,\"]", "");
        text = Normalizer.normalize(text, Normalizer.Form.NFD);
        text = text.replaceAll("\\p{M}", "");

        Pattern pattern = Pattern.compile("(?<=\\b(?:el|la|los|las|un|una|unos|unas|Sr|Sra|Dr|Dra|Prof))\\s+[A-Z][a-z]*\\b");
        //https://docs.oracle.com/javase/6/docs/api/java/util/regex/Pattern.html ?: significa que no captura ese grupo, ya q no lo necesitamos realmente
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            candidates.add(matcher.group());
        }

        return candidates;
    }
}
