package com.example.financialaidallocation.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.financialaidallocation.Classes.LoginResponse;
import com.example.financialaidallocation.Classes.SharedPrefManager;
import com.example.financialaidallocation.R;

import Api.ApiService;
import Api.RetrofitClient;
import Api.UserService;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button login;
    UserService userService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CircleImageView imageView = findViewById(R.id.biitlogo);

        login = findViewById(R.id.login_btn);
        ApiService apiService = RetrofitClient.getinstance().create(ApiService.class);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the entered username and ARID number from EditText fields
                EditText usernameEditText = findViewById(R.id.username_input);
                EditText Password = findViewById(R.id.password_input);
                String userName = usernameEditText.getText().toString().trim();
                String password = Password.getText().toString().trim();
                
                userService=new UserService();

                userService.login(userName, password, new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {


                        if(response.isSuccessful()){

                            Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                            handleLoginSuccess(response.body());

                        }

                        else {
                            Toast.makeText(MainActivity.this, response.message(), Toast.LENGTH_SHORT).show();

                        }

                    }


                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable throwable) {

                        Toast.makeText(MainActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });



            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->
        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void handleLoginSuccess(LoginResponse loginResponse) {
        int role = loginResponse.getRole();
        Intent intent = null;

        switch (role) {
            case 1:
                intent = new Intent(MainActivity.this, AdminDashboard.class);
                break;
            case 2:
                intent = new Intent(MainActivity.this, CommitteeMemberDashboard.class);
                break;
            case 3:
                intent = new Intent(MainActivity.this, FacultyMemberDashboard.class);
                break;
            case 4:
                intent = new Intent(MainActivity.this, StudentDashboard.class);
//                sharedPreferencesManager.getStudentUserObject(loginResponse);
                // Pass the account name and ARID number to StudentActivity
                intent.putExtra("accountname",loginResponse.getPassword());
                intent.putExtra("aridNumber", loginResponse.getUserName());

                break;
            default:
                // Handle unknown role, maybe go to a default activity
                Toast.makeText(this, "not found", Toast.LENGTH_SHORT).show();
                break;
        }
// Save user information to SharedPreferences

        intent.putExtra("userId", loginResponse.getId());
        intent.putExtra("userName", loginResponse.getUserName());
        intent.putExtra("password",loginResponse.getPassword());
        intent.putExtra("role",loginResponse.getRole());
        intent.putExtra("profileId",loginResponse.getProfileId());

        // Save user information to SharedPreferences
        SharedPrefManager.getInstance(MainActivity.this).saveUser(loginResponse);
        startActivity(intent);
    }
}