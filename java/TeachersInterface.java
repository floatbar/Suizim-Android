package wafoot.becoming.wafoot;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
public interface TeachersInterface {
    @FormUrlEncoded
    @POST("/insertTeacherGender")
    Call<TeachersResponse> insertTeacherGender(@Field("teacher_name") String teacher_name, @Field("teacher_surname") String teacher_surname,
                                               @Field("password") String password, @Field("gender") String gender);

    @FormUrlEncoded
    @POST("/insertTeacherAge")
    Call<TeachersResponse> insertTeacherAge(@Field("teacher_name") String teacher_name, @Field("teacher_surname") String teacher_surname,
                                            @Field("password") String password, @Field("age") String age);

    @GET("showAllTeachers")
    Call<TeachersResponse> showAllTeachers();

    @FormUrlEncoded
    @POST("/insertTeacherFootPrint")
    Call<TeachersResponse> insertTeacherFootPrint(@Field("teacher_name") String teacher_name, @Field("teacher_surname")
    String teacher_surname, @Field("password") String password, @Field("water_foot_print") String water_foot_print);

    @FormUrlEncoded
    @POST("/addTeacher")
    Call<TeachersResponse> addTeacher(@Field("teacher_branch") String teacher_branch, @Field("teacher_name") String teacher_name,
                                      @Field("teacher_surname") String teacher_surname, @Field("password") String
                                   password);

    @FormUrlEncoded
    @POST("/loginTeacher")
    Call<TeachersResponse> loginTeacher(@Field("teacher_name") String teacher_name,
                                        @Field("teacher_surname") String teacher_surname,
                                        @Field("password") String password);

    @FormUrlEncoded
    @POST("/deleteTeacherAccount")
    Call<TeachersResponse> deleteTeacherAccount(@Field("teacher_name") String teacher_name,
                                                @Field("teacher_surname") String teacher_surname,
                                                @Field("password") String password);

    @FormUrlEncoded
    @POST("/verifyTeacherToken")
    Call<TeachersResponse> verifyTeacherToken(@Field("teacher_name") String teacher_name,
                                              @Field("teacher_surname") String teacher_surname,
                                              @Field("password") String password);

    @FormUrlEncoded
    @POST("/generate2FATeacherPassword")
    Call<TeachersResponse> generate2FATeacherPassword(@Field("teacher_name") String teacher_name,
                                                      @Field("teacher_surname") String teacher_surname,
                                                      @Field("password") String password,
                                                      @Field("two_factor_password") String two_factor_password);

    @FormUrlEncoded
    @POST("/showIsTeacherWith2FAPassword")
    Call<TeachersResponse> showIsTeacherWith2FAPassword(@Field("teacher_name") String teacher_name,
                                                        @Field("teacher_surname") String teacher_surname,
                                                        @Field("password") String password);

    @FormUrlEncoded
    @POST("/change2FATeacherPassword")
    Call<TeachersResponse> change2FATeacherPassword(@Field("teacher_name") String teacher_name,
                                                    @Field("teacher_surname") String teacher_surname,
                                                    @Field("password") String password,
                                                    @Field("two_factor_password") String two_factor_password);

    @FormUrlEncoded
    @POST("/verify2FATeacherPassword")
    Call<TeachersResponse> verify2FATeacherPassword(@Field("teacher_name") String teacher_name,
                                                    @Field("teacher_surname") String teacher_surname,
                                                    @Field("password") String password,
                                                    @Field("two_factor_password") String two_factor_password);

    @FormUrlEncoded
    @POST("/logoutTeacher")
    Call<TeachersResponse> logoutTeacher(@Field("teacher_name") String teacher_name,
                                         @Field("teacher_surname") String teacher_surname,
                                         @Field("password") String password);
}