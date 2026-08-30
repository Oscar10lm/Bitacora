package dosw.semana_4.taller.ejercicio6;

import java.util.List;

public class SuggestedListComponent implements PreferenceObserver {
    @Override
    public void onPreferenceChanged(User user) {
        System.out.println(" ↳ [SuggestedListComponent] Refrescando la barra lateral de sugerencias...");
        List<Content> recommendations = user.getRecommendations();
        System.out.println("    Lista actualizada: " + recommendations);
    }
}
