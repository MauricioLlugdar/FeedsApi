package namedEntities.heuristics;

import java.util.ArrayList;
import java.util.List;

public interface FatherHeuristic{
        
        abstract public List<String> extractCandidates(String text);
}