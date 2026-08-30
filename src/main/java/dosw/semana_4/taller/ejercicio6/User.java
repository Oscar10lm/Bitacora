package dosw.semana_4.taller.ejercicio6;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String name;
    private RecommendationAlgorithm strategy;
    private final List<PreferenceObserver> observers = new ArrayList<>();

    public User(String name, RecommendationAlgorithm strategy) {
        this.name = name;
        this.strategy = strategy;
    }

    public void addObserver(PreferenceObserver observer) {
        observers.add(observer);
    }

    public void changePreference(RecommendationAlgorithm newStrategy) {
        System.out.println("\n[User] " + name + " ha cambiado sus preferencias de recomendación.");
        this.strategy = newStrategy;
        notifyObservers();
    }

    private void notifyObservers() {
        for (PreferenceObserver observer : observers) {
            observer.onPreferenceChanged(this);
        }
    }

    public List<Content> getRecommendations() {
        return strategy.recommend(this);
    }
}
