package dosw.semana_4.taller.ejercicio6;

import java.util.Arrays;
import java.util.List;

public class PopularityStrategy implements RecommendationAlgorithm {
    @Override
    public List<Content> recommend(User user) {
        return Arrays.asList(new Content("Stranger Things"), new Content("Squid Game"));
    }
}
