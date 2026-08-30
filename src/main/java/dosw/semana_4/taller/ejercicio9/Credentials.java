package dosw.semana_4.taller.ejercicio9;

public class Credentials {
    private final String userId;
    private final String payload;

    public Credentials(String userId, String payload) {
        this.userId = userId;
        this.payload = payload;
    }

    public String getUserId() {
        return userId;
    }

    public String getPayload() {
        return payload;
    }
}
