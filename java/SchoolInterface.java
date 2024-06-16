package wafoot.becoming.wafoot;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SchoolInterface {
    @GET("/showAllSchoolPrint")
    Call<SchoolResponse> showAllSchoolPrint();

    @GET("/showWholeSchoolPrint")
    Call<SchoolResponse> showWholeSchoolPrint();

    @GET("/showGender")
    Call<SchoolResponse> showGender();

    @GET("/showAge")
    Call<SchoolResponse> showAge();
}