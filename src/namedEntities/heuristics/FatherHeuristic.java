package namedEntities.heuristics;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public interface FatherHeuristic{
        
        abstract public List<String> extractCandidates(String text);
}