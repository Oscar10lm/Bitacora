package dosw.semana_4.taller.ejercicio9;

public class AuthResult {
    private final boolean success;
    private final String userId;

    public AuthResult(boolean success, String userId) {
        this.success = success;
        this.userId = userId;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getUserId() {
        return userId;
    }
}
