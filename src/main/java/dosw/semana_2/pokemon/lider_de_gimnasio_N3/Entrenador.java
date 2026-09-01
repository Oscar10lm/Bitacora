package dosw.semana_2.pokemon.lider_de_gimnasio_N3;

import java.util.List;

public class Entrenador {
    private Long id;
    private String nombre;
    private int medallas;
    private List<Pokemon> equipo;

    public Entrenador(Long id, String nombre, int medallas, List<Pokemon> equipo) {
        this.id = id;
        this.nombre = nombre;
        this.medallas = medallas;
        this.equipo = equipo;
    }

    // Getters
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public int getMedallas() { return medallas; }
    public List<Pokemon> getEquipo() { return equipo; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setMedallas(int medallas) { this.medallas = medallas; }
    public void setEquipo(List<Pokemon> equipo) { this.equipo = equipo; }
}
