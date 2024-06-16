package wafoot.becoming.wafoot;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface StudentsInterface {
    @GET("showAllStudents")
    Call<StudentsResponse> showAllStudents();

    @FormUrlEncoded
    @POST("showAritmeticClassPrint")
    Call<StudentsResponse> showAritmeticClassPrint(@Field("student_class") String student_class);


    /* @POST("createStudentMessage")
    Call<StudentsResponse> createStudentMessage(@Field("student_name") String student_name, @Field("student_surname")
                                             String student_surname, @Field("password") String password, @Field("message")
                                             String message); */
    @FormUrlEncoded
    @POST("/insertStudentGender")
    Call<StudentsResponse> insertStudentGender(@Field("student_name") String student_name, @Field("student_surname") String student_surname,
                                               @Field("password") String password, @Field("gender") String gender);
    @FormUrlEncoded
    @POST("/showStudentPrint")
    Call<StudentsResponse> showStudentPrint(@Field("student_name") String student_name,
                                            @Field("student_surname") String student_surname,
                                            @Field("password") String password);
    @FormUrlEncoded
    @POST("/insertStudentAge")
    Call<StudentsResponse> insertStudentAge(@Field("student_name") String student_name, @Field("student_surname") String student_surname,
                                            @Field("password") String password, @Field("age") String age);
    @FormUrlEncoded
    @POST("/insertFootPrint")
    Call<StudentsResponse> insertFootPrint(@Field("student_name") String student_name, @Field("student_surname")
                                        String student_surname, @Field("password") String password,
                                           @Field("water_foot_print") String water_foot_print);

    @FormUrlEncoded
    @POST("/saveStudent")
    Call<StudentsResponse> saveStudent(@Field("student_class") String student_class, @Field("student_name") String student_name,
                                       @Field("student_surname") String student_surname, @Field("password") String password);
    @FormUrlEncoded
    @POST("/login")
    Call<StudentsResponse> loginStudent(@Field("student_name") String student_name, @Field("student_surname") String student_surname,
                                        @Field("password") String password);

    /* @POST("joinChannel")
    Call<StudentsResponse> joinChannel(); */ // Suizim public messaging group

    @FormUrlEncoded
    @POST("/showStudentClass")
    Call<StudentsResponse> showStudentClass(@Field("student_name") String student_name,
                                            @Field("student_surname") String student_surname,
                                            @Field("password") String password);
    @FormUrlEncoded
    @POST("/deleteStudentAccount")
    Call<StudentsResponse> deleteStudentAccount(@Field("student_name") String student_name,
                                                @Field("student_surname") String student_surname,
                                                @Field("password") String password);

    @FormUrlEncoded
    @POST("/verifyStudentToken")
    Call<StudentsResponse> verifyStudentToken(@Field("student_name") String student_name,
                                              @Field("student_surname") String student_surname,
                                              @Field("password") String password);

    @FormUrlEncoded
    @POST("/generate2FAStudentPassword")
    Call<StudentsResponse> generate2FAStudentPassword(@Field("student_name") String student_name,
                                                      @Field("student_surname") String student_surname,
                                                      @Field("password") String password,
                                                      @Field("two_factor_password") String two_factor_password);

    @FormUrlEncoded
    @POST("/showIsStudentWith2FAPassword")
    Call<StudentsResponse> showIsStudentWith2FAPassword(@Field("student_name") String student_name,
                                                        @Field("student_surname") String student_surname,
                                                        @Field("password") String password);

    @FormUrlEncoded
    @POST("/change2FAStudentPassword")
    Call<StudentsResponse> change2FAStudentPassword(@Field("student_name") String student_name,
                                                    @Field("student_surname") String student_surname,
                                                    @Field("password") String password,
                                                    @Field("two_factor_password") String two_factor_password);

    @FormUrlEncoded
    @POST("/verify2FAStudentPassword")
    Call<StudentsResponse> verify2FAStudentPassword(@Field("student_name") String student_name,
                                                    @Field("student_surname") String student_surname,
                                                    @Field("password") String password,
                                                    @Field("two_factor_password") String two_factor_password);

    @FormUrlEncoded
    @POST("/logoutStudent")
    Call<StudentsResponse> logoutStudent(@Field("student_name") String student_name,
                                         @Field("student_surname") String student_surname,
                                         @Field("password") String password);
}