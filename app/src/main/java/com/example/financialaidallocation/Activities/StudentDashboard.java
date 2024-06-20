package com.example.financialaidallocation.Activities;

import static android.widget.Toast.LENGTH_SHORT;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.financialaidallocation.Classes.ApplicationStatusResponse;
import com.example.financialaidallocation.Classes.LoginResponse;
import com.example.financialaidallocation.Classes.StudentResponse;
import com.example.financialaidallocation.R;
import com.google.android.material.navigation.NavigationView;

import Api.ApiService;
import Api.RetrofitClient;

import Api.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import androidx.activity.EdgeToEdge;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import java.util.List;

public class StudentDashboard extends AppCompatActivity implements View.OnClickListener {
    DrawerLayout drawerLayout;
    ImageButton btndrawerToogle;
    NavigationView navigationView;
    private TextView studentNameTextView;
    private TextView studentAridTextView;
    private ApiService apiService;
    private TextView applicationStatusTextView;
    UserService userService;
    public CardView c1, c2, c3, c4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_student_dashboard);
        applicationStatusTextView = findViewById(R.id.application_status); // Added for UI element
        studentNameTextView = findViewById(R.id.std_name);
        studentAridTextView = findViewById(R.id.std_aridno);
        userService = new UserService();
        apiService = RetrofitClient.getinstance().create(ApiService.class);
// Retrieve the profileId from the Intent
        Intent intent = getIntent();
        int profileId = intent.getIntExtra("profileId", 0); // Get profileId with a default of 0

//
        // Fetch student information using profileID
        if (profileId > 0) {

            fetchStudentInfo(profileId);
            fetchApplicationStatus(profileId);
        } else {
            Toast.makeText(this, "Profile ID not found", LENGTH_SHORT).show();
        }

        drawerLayout = findViewById(R.id.drawerlayout);

        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem Item) {
                int itemId = Item.getItemId();
                if (itemId == R.id.std_nav_logout) {
                    // Handle the logout action
                    Intent intent = new Intent(StudentDashboard.this, MainActivity.class);
                    startActivity(intent);
                    // Optional: close the current activity
                    return true;
                }
                return false;
            }
        });
        btndrawerToogle = findViewById(R.id.buttondrawerToogle);

        btndrawerToogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.open();
            }
        });


        c1 = (CardView) findViewById(R.id.apply_scholarship);
        c2 = (CardView) findViewById(R.id.needbase_criteria);
        c3 = (CardView) findViewById(R.id.meritbase_criteria);
        c4 = (CardView) findViewById(R.id.need_help);
        c1.setOnClickListener(this);
        c2.setOnClickListener(this);
        c3.setOnClickListener(this);
        c4.setOnClickListener(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void fetchApplicationStatus(int profileId) {


//         Call to retrieve application status
        Call<List<ApplicationStatusResponse>> applicationStatusCall = apiService.getStudentApplicationStatus(profileId);
        applicationStatusCall.enqueue(new Callback<List<ApplicationStatusResponse>>() {
            @Override
            public void onResponse(Call<List<ApplicationStatusResponse>> call, Response<List<ApplicationStatusResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ApplicationStatusResponse> statusResponse = response.body();
                    if (!statusResponse.isEmpty()) {
                        String applicationStatus = statusResponse.get(0).getApplicationStatus().toString();
                        applicationStatusTextView.setText(applicationStatus); // Update UI with application status
                    }else {
                        applicationStatusTextView.setText("Not Submitted");
                    }
                } else {
                    Toast.makeText(StudentDashboard.this, "Failed to fetch application status", LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure( Call<List<ApplicationStatusResponse>> call, Throwable t) {
                Log.e("Error", "wrong ", t);
                Toast.makeText(StudentDashboard.this,   t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }

    private void fetchStudentInfo(int profileId) {

        Call<StudentResponse> call = apiService.getStudentInfo(profileId);
        call.enqueue(new Callback<StudentResponse>() {

            @Override
            public void onResponse(Call<StudentResponse> call, Response<StudentResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    StudentResponse student = response.body();
                    studentNameTextView.setText(student.getName());
                    studentAridTextView.setText(student.getAridNo());
                } else {
                    Toast.makeText(StudentDashboard.this, "Failed to fetch student info", LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<StudentResponse> call, Throwable t) {
                Toast.makeText(StudentDashboard.this, "Error: " + t.getMessage(), LENGTH_SHORT).show();
            }
        });

}
    @Override
    public void onClick(View v) {
        Intent i;
        int id = v.getId();

        if (id == R.id.apply_scholarship) {
            i  = new Intent(this, applyforschloarship.class);
            startActivity(i);
        } else if (id == R.id.needbase_criteria) {
            i = new Intent(this, NeedBaseCriteria.class);
            startActivity(i);
        } else if (id == R.id.meritbase_criteria) {
            i = new Intent(this, MeritbaseCriteria.class);
            startActivity(i);
        } else if (id == R.id.need_help) {
            i = new Intent(this, NeedHelp.class);

            startActivity(i);
        }
    }}
