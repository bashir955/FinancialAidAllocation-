package Api;

import com.example.financialaidallocation.Classes.ApplicationModel;
import com.example.financialaidallocation.Classes.ApplicationStatusResponse;
import com.example.financialaidallocation.Classes.ApplicationSuggestionModel;
import com.example.financialaidallocation.Classes.BalanceModel;
import com.example.financialaidallocation.Classes.BudgetModel;
import com.example.financialaidallocation.Classes.CommitteeMemberModel;
import com.example.financialaidallocation.Classes.LoginResponse;
import com.example.financialaidallocation.Classes.MeritbaseStudentModel;
import com.example.financialaidallocation.Classes.PolicyModel;
import com.example.financialaidallocation.Classes.StudentModel;
import com.example.financialaidallocation.Classes.StudentResponse;

import java.util.List;
import retrofit2.Callback;
import retrofit2.Call;


public class UserService {
    private static ApiService apiService = RetrofitClient.getClient("http://192.168.1.4/FInancialAid/api/").create(ApiService.class);

    public void login(String username,String password,retrofit2.Callback<LoginResponse> callback) {
        Call<LoginResponse> call = apiService.login(username,password);
        call.enqueue(callback);
    }
    public void getStudentInfo(int id, retrofit2.Callback<StudentResponse>callback){
        Call<StudentResponse>call=apiService.getStudentInfo(id);
        call.enqueue(callback);
    }
    public void getStudentApplicationStatus(int id, retrofit2.Callback<List<ApplicationStatusResponse>> callback) {
        Call<List<ApplicationStatusResponse>> call = apiService.getStudentApplicationStatus(id);
        call.enqueue(callback);

    }
    public void getMeritBaseShortListedStudent(Callback<List<MeritbaseStudentModel>> callback) {
        Call<List<MeritbaseStudentModel>> call = apiService.getMeritBaseShortListedStudent();
        call.enqueue(callback);

    }
    public void getApplicationSuggestions(Callback<List<ApplicationSuggestionModel>> callback) {
        Call<List<ApplicationSuggestionModel>> call = apiService.getApplicationSuggestions();
        call.enqueue(callback);
}
    public void getCommitteeMembers(retrofit2.Callback<List<CommitteeMemberModel>> callback) {
        Call<List<CommitteeMemberModel>> call = apiService.getCommitteeMembers();
        call.enqueue(callback);

    }
    public void getAllStudents(retrofit2.Callback<List<StudentModel>> callback) {
        Call<List<StudentModel>> call = apiService.getAllStudents();
        call.enqueue(callback);
}
    public void getAllBudgets(Callback<List<BudgetModel>> callback) {
        Call<List<BudgetModel>> call = apiService.getAllBudgets();
        call.enqueue(callback);
}
    public void getPolicies(Callback<List<PolicyModel>> callback) {
        Call<List<PolicyModel>> call = apiService.getPolicies();
        call.enqueue(callback);

}

//Commitee Members

    public void getApplications(int committeeId, Callback<List<ApplicationModel>> callback) {
        Call<List<ApplicationModel>> call = apiService.getApplications(committeeId);
        call.enqueue(callback);
    }
    public void getBalance(Callback<Double> callback) {
        Call<Double> call = apiService.getBalance();
        call.enqueue(callback);
    }


}