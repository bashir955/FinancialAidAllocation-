package Api;
import com.example.financialaidallocation.Classes.ApplicationStatusResponse;
import com.example.financialaidallocation.Classes.DefaultResponse;
import com.example.financialaidallocation.Classes.LoginResponse;
import com.example.financialaidallocation.Classes.StudentResponse;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiService {
    @GET("RunMe")
    Call<String> runMe();

    @GET("User/Login")
    Call<LoginResponse> login(@Query("username")String username, @Query("password") String password);
    @GET("Student/getStudentInfo")
    Call<StudentResponse> getStudentInfo(@Query("id") int id);

    @Multipart
    @POST("Student/BuildProfile")
    Call<DefaultResponse> buildProfile(
            @Part("id") RequestBody id,
            @Part("name") RequestBody name,
            @Part("aridno") RequestBody aridno,
            @Part("semester") RequestBody semester,
            @Part("gender") RequestBody gender,
            @Part("fname") RequestBody fname,
            @Part("fstatus") RequestBody fstatus,
            @Part("jobtitle") RequestBody jobtitle,
            @Part("contact") RequestBody contact,
            @Part("salary") RequestBody salary,
            @Part("cgpa") RequestBody cgpa,
            @Part("degree") RequestBody degree,
            @Part MultipartBody.Part salaryslip,
            @Part MultipartBody.Part certificate
    );

    @Multipart
    @POST("Student/updateProfilePicture")
    Call<DefaultResponse> updateProfilePicture(
            @Part("id") RequestBody id,
            @Part MultipartBody.Part image
    );

    @Multipart
    @POST("Student/sendApplication")
    Call<DefaultResponse> sendApplication(
            @Part("status") RequestBody status,
            @Part("occupation") RequestBody occupation,
            @Part("contactNo") RequestBody contactNo,
            @Part("salary") RequestBody salary,
            @Part("gName") RequestBody gName,
            @Part("gContact") RequestBody gContact,
            @Part("gRelation") RequestBody gRelation,
            @Part("house") RequestBody house,
            @Part("reason") RequestBody reason,
            @Part("amount") RequestBody amount,
            @Part("isPicked") RequestBody isPicked,
            @Part("studentId") RequestBody studentId,
            @Part MultipartBody.Part docs,
            @Part MultipartBody.Part agreement
    );

    @GET("Student/NeedBasePolicies")
    Call<DefaultResponse> getNeedBasePolicies();

    @GET("Student/MeritBasePolicies")
    Call<DefaultResponse> getMeritBasePolicies();

    @GET("Student/getStudentApplicationStatus")
    Call<ApplicationStatusResponse> getStudentApplicationStatus(@Query("id") int id);
}
