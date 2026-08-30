package dosw.semana_4.taller.ejercicio6;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Motor de Recomendaciones ---");

        // 1. Crear el usuario con una estrategia inicial
        User user = new User("Alice", new PopularityStrategy());

        // 2. Registrar los componentes de la interfaz (Observers)
        user.addObserver(new HomePageComponent());
        user.addObserver(new SuggestedListComponent());

        // 3. El usuario obtiene sus recomendaciones iniciales (sin notificar, solo lectura)
        System.out.println("\nAl iniciar sesión, Alice ve recomendaciones populares:");
        System.out.println(user.getRecommendations());

        // 4. El usuario cambia sus preferencias a 'Por Género'
        user.changePreference(new GenreStrategy());

        // 5. El usuario cambia sus preferencias a 'Por Historial'
        user.changePreference(new HistoryStrategy());
    }
}
