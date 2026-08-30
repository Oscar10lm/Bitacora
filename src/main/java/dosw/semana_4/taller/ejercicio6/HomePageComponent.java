package dosw.semana_4.taller.ejercicio6;

import java.util.List;

public class HomePageComponent implements PreferenceObserver {
    @Override
    public void onPreferenceChanged(User user) {
        System.out.println(" ↳ [HomePageComponent] Actualizando banner principal...");
        List<Content> recommendations = user.getRecommendations();
        System.out.println("    Mostrando en inicio: " + recommendations);
    }
}
