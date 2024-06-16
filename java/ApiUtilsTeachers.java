package wafoot.becoming.wafoot;

public class ApiUtilsTeachers {

    private static final String FIRST_BASE_URL = "https://0.0.0.0:6666/"; // Replace with your actual server URL.
    private static final String SECOND_BASE_URL = "https://0.0.0.0:6667"; // Replace with your actual server URL.

    public static TeachersInterface getTeachersInterface() {
        return RetrofitClient.getClient(FIRST_BASE_URL).create(TeachersInterface.class);
    }

    public static TeachersInterface getNodejsTeachersInterface() {
        return RetrofitClient.getClient(SECOND_BASE_URL).create(TeachersInterface.class);
    }
}
