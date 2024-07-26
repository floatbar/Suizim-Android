package wafoot.becoming.wafoot;

public class ApiUtils {

    private static final String FIRST_BASE_URL = "https://0.0.0.0:6666/"; // Replace with your actual server URL
    private static final String SECOND_BASE_URL = "https://0.0.0.0:6667"; // Replace with your actual server URL.

    public static StudentsInterface getStudentsInterface() {
        return RetrofitClient.getClient(FIRST_BASE_URL).create(StudentsInterface.class);
    }

    public static StudentsInterface getNodejsStudentsInterface() {
        return RetrofitClient.getClient(SECOND_BASE_URL).create(StudentsInterface.class); // I will not share this app's backend codes :-)
    }
}
