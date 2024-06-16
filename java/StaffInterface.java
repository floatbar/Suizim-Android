package wafoot.becoming.wafoot;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface StaffInterface {
    @FormUrlEncoded
    @POST("/insertStaffGender")
    Call<StaffResponse> insertStaffGender(@Field("staff_name") String staff_name, @Field("staff_surname") String staff_surname,
                                          @Field("password") String password, @Field("gender") String gender);
    @FormUrlEncoded
    @POST("/insertStaffAge")
    Call<StaffResponse> insertStaffAge(@Field("staff_name") String staff_name, @Field("staff_surname") String staff_surname,
                                       @Field("password") String password, @Field("age") String age);
    @FormUrlEncoded
    @POST("/showStaffPrint")
    Call<StaffResponse> showStaffPrint(@Field("staff_name") String staff_name,
                                       @Field("staff_surname") String staff_surname,
                                       @Field("password") String password);
    @GET("showAllStaff")
    Call<StaffResponse> showAllStaff();
    @FormUrlEncoded
    @POST("/insertStaffFootPrint")
    Call<StaffResponse> insertStaffFootPrint(@Field("staff_name") String staff_name, @Field("staff_surname") String staff_surname,
                                             @Field("password") String password, @Field("water_foot_print") String water_foot_print);
    @FormUrlEncoded
    @POST("/addStaff")
    Call<StaffResponse> addStaff(@Field("staff_name") String staff_name, @Field("staff_surname") String staff_surname,
                                 @Field("password") String password);
    @FormUrlEncoded
    @POST("/loginStaff")
    Call<StaffResponse> loginStaff(@Field("staff_name") String staff_name,
                                   @Field("staff_surname") String staff_surname,
                                   @Field("password") String password);
    @FormUrlEncoded
    @POST("/deleteStaffAccount")
    Call<StaffResponse> deleteStaffAccount(@Field("staff_name") String staff_name,
                                           @Field("staff_surname") String staff_surname,
                                           @Field("password") String password);

    @FormUrlEncoded
    @POST("/verifyStaffToken")
    Call<StaffResponse> verifyStaffToken(@Field("staff_name") String staff_name,
                                         @Field("staff_surname") String staff_surname,
                                         @Field("password") String password);

    @FormUrlEncoded
    @POST("/generate2FAStaffPassword")
    Call<StaffResponse> generate2FAStaffPassword(@Field("staff_name") String staff_name,
                                                 @Field("staff_surname") String staff_surname,
                                                 @Field("password") String password,
                                                 @Field("two_factor_password") String two_factor_password);

    @FormUrlEncoded
    @POST("/showIsStaffWith2FAPassword")
    Call<StaffResponse> showIsStaffWith2FAPassword(@Field("staff_name") String staff_name,
                                                   @Field("staff_surname") String staff_surname,
                                                   @Field("password") String password);

    @FormUrlEncoded
    @POST("/change2FAStaffPassword")
    Call<StaffResponse> change2FAStaffPassword(@Field("staff_name") String staff_name,
                                               @Field("staff_surname") String staff_surname,
                                               @Field("password") String password,
                                               @Field("two_factor_password") String two_factor_password);

    @FormUrlEncoded
    @POST("/verify2FAStaffPassword")
    Call<StaffResponse> verify2FAStaffPassword(@Field("staff_name") String staff_name,
                                               @Field("staff_surname") String staff_surname,
                                               @Field("password") String password,
                                               @Field("two_factor_password") String two_factor_password);
    @FormUrlEncoded
    @POST("/logoutStaff")
    Call<StaffResponse> logoutStaff(@Field("staff_name") String staff_name,
                                    @Field("staff_surname") String staff_surname,
                                    @Field("password") String password);

}