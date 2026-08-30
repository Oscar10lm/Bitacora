package dosw.semana_4.taller.ejercicio6;

import java.util.Arrays;
import java.util.List;

public class HistoryStrategy implements RecommendationAlgorithm {
    @Override
    public List<Content> recommend(User user) {
        return Arrays.asList(new Content("The Matrix 2"), new Content("John Wick"));
    }
}
