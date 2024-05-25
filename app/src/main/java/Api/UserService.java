package Api;
import com.example.financialaidallocation.Classes.ApplicationStatusResponse;
import com.example.financialaidallocation.Classes.LoginResponse;

import java.util.List;
import retrofit2.Callback;
import retrofit2.Call;


public class UserService {
   private static ApiService apiService = RetrofitClient.getClient("http://192.168.176.157/FInancialAid/api/").create(ApiService.class);

    public void login(String username,String password,retrofit2.Callback<LoginResponse> callback) {
        Call<LoginResponse> call = apiService.login(username,password);
        call.enqueue(callback);
    }
    public void getStudentApplicationStatus(int id, Callback<ApplicationStatusResponse> callback) {
        Call<ApplicationStatusResponse> call = apiService.getStudentApplicationStatus(id);
        call.enqueue(callback);
    }
}
