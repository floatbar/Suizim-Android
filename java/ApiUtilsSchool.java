package wafoot.becoming.wafoot;

public class ApiUtilsSchool {

    private static final String BASE_URL = "https://0.0.0.0:6666"; // Replace with your actual server URL.

    public static SchoolInterface getSchoolInterface() {
        return RetrofitClient.getClient(BASE_URL).create(SchoolInterface.class);
    }
}
