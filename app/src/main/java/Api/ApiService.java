package Api;
import com.example.financialaidallocation.Classes.ApplicationModel;
import com.example.financialaidallocation.Classes.ApplicationStatusResponse;
import com.example.financialaidallocation.Classes.ApplicationSuggestionModel;
import com.example.financialaidallocation.Classes.BudgetModel;
import com.example.financialaidallocation.Classes.CommitteeMemberModel;
import com.example.financialaidallocation.Classes.DefaultResponse;
import com.example.financialaidallocation.Classes.LoginResponse;
import com.example.financialaidallocation.Classes.MeritbaseStudentModel;
import com.example.financialaidallocation.Classes.PolicyModel;
import com.example.financialaidallocation.Classes.ScholorShipPolicy;
import com.example.financialaidallocation.Classes.StudentModel;
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
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("User/Login")
    Call<LoginResponse> login(@Query("username")String username, @Query("password") String password);

    @GET("Student/getStudentInfo")
    Call<StudentResponse> getStudentInfo(@Query("id") int id);

    @GET("Admin/MeritBaseShortListing")
    Call<List<MeritbaseStudentModel>> getMeritBaseShortListing();

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
    Call<List<ApplicationStatusResponse>> getStudentApplicationStatus(@Query("id") int id);


    //Admin
    @GET("Admin/GetMeritBaseShortListedStudent")
    Call<List<MeritbaseStudentModel>> getMeritBaseShortListedStudent();
    @GET("Admin/ApplicationSuggestions")
    Call<List<ApplicationSuggestionModel>> getApplicationSuggestions();

    @GET("Admin/CommitteeMembers")
    Call<List<CommitteeMemberModel>> getCommitteeMembers();

    @GET("Admin/getAllStudent")
    Call<List<StudentModel>> getAllStudents();

    @GET("Admin/getAllBudget")
    Call<List<BudgetModel>> getAllBudgets();

    @POST("Admin/AddBudget")
    Call<Integer> addBudget(@Body int amount);

    @GET("Admin/getPolicies")
    Call<List<ScholorShipPolicy>> getPolicies();

    //Committee Member Screnn
    @GET("Committee/GetApplication/{id}")
    Call<List<ApplicationModel>> getApplications(@Path("id") int committeeId);

    @GET("Committee/GetBalance")
    Call<Double> getBalance();

    @GET("Committee/CommitteeMembers/{id}")
    Call<CommitteeMemberModel> getCommitteeMembers(@Path("id") int committeeId);

    @POST("Committee/GiveSuggestion")
    Call<Void> giveSuggestion(
            @Query("committeeId") int committeeId,
            @Query("status") String status,
            @Query("applicationId") int applicationId,
            @Query("comment") String comment
    );

}
