package com.example.onlinedirectoryprovider.retrofit;

import com.example.onlinedirectoryprovider.model.adddomain.AddDomainResponse;
import com.example.onlinedirectoryprovider.model.contactus.ContactUsResponse;
import com.example.onlinedirectoryprovider.model.faqs.FaqsResponse;
import com.example.onlinedirectoryprovider.model.forgotpassword.ForgotPasswordResponse;
import com.example.onlinedirectoryprovider.model.getprofile.GetProfileResponse;
import com.example.onlinedirectoryprovider.model.login.LoginResponse;
import com.example.onlinedirectoryprovider.model.manageinfo.ManageInfoResponse;
import com.example.onlinedirectoryprovider.model.newpassword.ChangePasswordResponse;
import com.example.onlinedirectoryprovider.model.register.RegisterResponse;
import com.example.onlinedirectoryprovider.model.resendotp.ResendOtpResponse;
import com.example.onlinedirectoryprovider.model.resetpassword.ResetPasswordResponse;
import com.example.onlinedirectoryprovider.model.selecttheme.SelectThemeResponse;
import com.example.onlinedirectoryprovider.model.tandc.TermsAndConditionsResponse;
import com.example.onlinedirectoryprovider.model.updateprofile.UpdateProfileResponse;
import com.example.onlinedirectoryprovider.model.verifyotp.VerifyOtpResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface ApiInterface {

    @FormUrlEncoded
    @POST("register")
    Call<RegisterResponse> getRegister(@Field("first_name") String first_name,
                                       @Field("last_name") String last_name,
                                       @Field("email") String email,
                                       @Field("password") String password,
                                       @Field("confirm_password") String confirm_password,
                                       @Field("mobile") String mobile,
                                       @Field("business_name") String business_name,
                                       @Field("role") String role,
                                       @Field("category_id") String category_id,
                                       @Field("location") String location,
                                       @Field("zip_code") String zip_code,
                                       @Field("latitude") String latitude,
                                       @Field("longitude") String longitude,
                                       @Field("device_type") int device_type,
                                       @Field("device_token") String device_token) ;


    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> getLogin(@Field("role") String role,
                                 @Field("email") String email,
                                 @Field("password") String password);
    @FormUrlEncoded
    @POST("forgotpassword")
    Call<ForgotPasswordResponse> forgotPassword(@Field("email") String email,
                                                @Field("role") String role);


    @FormUrlEncoded
    @POST("verifyotp")
    Call<VerifyOtpResponse> getVerifyOtp(@Field("email") String email,
                                         @Field("otp") String otp,
                                         @Field("type") String type);

    @FormUrlEncoded
    @POST("resend-otp")
    Call<ResendOtpResponse> resendOTP(@Field("email") String email,
                                      @Field("type") String type);


    @FormUrlEncoded
    @POST("change-password")
    Call<ChangePasswordResponse> ChangePassword(@Header("Authorization") String token,
                                                @Field("current_password") String current_password,
                                                @Field("new_password") String new_password,
                                                @Field("confirm_password") String confirm_password);



    @FormUrlEncoded
    @POST("reset-password")
    Call<ResetPasswordResponse> ResetPassword(@Header("Authorization") String token,
                                              @Field("email") String email,
                                              @Field("new_password") String new_password,
                                              @Field("confirm_password") String confirm_password);


    @GET("getprofile")
    Call<GetProfileResponse> getProfile(@Header("Authorization")String token);

    @FormUrlEncoded
    @POST("updateprofile")
    Call<UpdateProfileResponse> updateProfile(@Header("Authorization")String token,
                                              @Field("firstname") String firstname,
                                              @Field("lastname") String lastname,
                                              @Field("email") String email,
                                              @Field("mobile") String mobile,
                                              @Field("role") String role,
                                              @Field("location") String location,
                                              @Field("zip_code")String zip_code);


    @FormUrlEncoded
    @POST("contact-us")
    Call<ContactUsResponse> ContactUs(@Header("Authorization")String token,
                                      @Field("name") String name,
                                      @Field("email") String email,
                                      @Field("subject") String subject,
                                      @Field("message") String message);

    @GET("faqs")
    Call<FaqsResponse> faqs(@Header("Authorization") String token);

    @GET("termsconditions")
    Call<TermsAndConditionsResponse>tandc(@Header("Authorization")String token);


    @GET("getalltemplate")
    Call<SelectThemeResponse> selecttheme(@Header("Authorization")String token);

    @FormUrlEncoded
    @POST("addDomain")
    Call<AddDomainResponse> addDomain(@Header("Authorization")String token,
                                      @Field("domain_name")String domain_name);

    @FormUrlEncoded
    @POST("manageinfo")
    Call<ManageInfoResponse> manageInfo(@Header("Authorization")String token,
                                       @Field("website_url")String website_url,
                                       @Field("details")String details,
                                       @Field("opening_time")String opening_time,
                                       @Field("closing_time")String closing_time,                                       @Field("average_charges")String average_charges,
                                       @Field("website_logo")String website_logo);
















}


