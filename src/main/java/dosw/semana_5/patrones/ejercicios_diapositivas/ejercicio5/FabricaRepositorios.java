package dosw.semana_5.patrones.extra.ejercicio5;

public class FabricaRepositorios {
    public static Repositorio crear(String tipo) {
        switch (tipo.toUpperCase()) {
            case "USUARIOS":
                return new UsuarioRepository();
            case "FACTURAS":
                return new FacturaRepository();
            default:
                throw new IllegalArgumentException("Tipo de repositorio desconocido: " + tipo);
        }
    }
}
