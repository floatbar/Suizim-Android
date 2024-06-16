package wafoot.becoming.wafoot;

public class SessionManager {

    private static SessionManager instance;

    private boolean isLogged = false;

    private SessionManager() {
    }

    public static synchronized SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }

        return instance;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }

    public boolean isLogged() {
        return isLogged;
    }
}