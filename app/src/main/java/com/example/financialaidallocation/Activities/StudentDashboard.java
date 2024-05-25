package com.example.financialaidallocation.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.financialaidallocation.Classes.ApplicationStatusResponse;
import com.example.financialaidallocation.Classes.StudentResponse;
import com.example.financialaidallocation.R;
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

public class StudentDashboard extends AppCompatActivity implements View.OnClickListener {
    private TextView studentNameTextView;
    private TextView studentIdTextView;
    private ApiService apiService;
    private TextView applicationStatusTextView;
    UserService userService;
    public CardView c1, c2, c3, c4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_student_dashboard);
        studentNameTextView = findViewById(R.id.std_name);
        studentIdTextView = findViewById(R.id.std_aridno);
        apiService = RetrofitClient.getinstance().create(ApiService.class);

        // Assume student ID is passed via Intent extras
        int studentId = getIntent().getIntExtra("studentId", 0);
        if (studentId != 0) {
            fetchStudentInfo(studentId);
        } else {
            Toast.makeText(this, "Invalid student ID", Toast.LENGTH_SHORT).show();
        }
    }

    private void fetchStudentInfo(int studentId) {
        Call<StudentResponse> call = apiService.getStudentInfo(studentId);
        call.enqueue(new Callback<StudentResponse>() {   @Override
        public void onResponse(Call<StudentResponse> call, Response<StudentResponse> response) {
            if (response.isSuccessful() && response.body() != null) {
                StudentResponse student = response.body();
                studentNameTextView.setText(student.getName());
                studentIdTextView.setText(String.valueOf(student.getStudentId()));
            } else {
                Toast.makeText(StudentDashboard.this, "Failed to fetch student info", Toast.LENGTH_SHORT).show();
            }
        }

            @Override
            public void onFailure(Call<StudentResponse> call, Throwable t) {
                Toast.makeText(StudentDashboard.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        applicationStatusTextView = findViewById(R.id.application_status);
        userService=new UserService();

        // Assume studentId is passed via Intent
//        int studentId = getIntent().getIntExtra("studentId", 0);

        if (studentId != 0) {
            fetchApplicationStatus(studentId);
        }
    }

    private void fetchApplicationStatus(int studentId) {
        userService.getStudentApplicationStatus(studentId, new Callback<ApplicationStatusResponse>() {
            @Override
            public void onResponse(Call<ApplicationStatusResponse> call, Response<ApplicationStatusResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String status = response.body().getApplicationStatus();
                    applicationStatusTextView.setText(status);
                } else {
                    applicationStatusTextView.setText("No status found");
                }
            }


            @Override
            public void onFailure(Call<ApplicationStatusResponse> call, Throwable throwable) {
                Toast.makeText(StudentDashboard.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
//


        // Get the data from the Intent
        String accountName = getIntent().getStringExtra("accountName");
        String aridNumber = getIntent().getStringExtra("aridNumber");

        // Set the data to the TextViews
        studentNameTextView.setText(accountName);
        studentIdTextView.setText(aridNumber);

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

    @Override
    public void onClick(View v) {
        Intent i;
        int id = v.getId();

        if (id == R.id.apply_scholarship) {
            i = new Intent(this, applyforschloarship.class);
            startActivity(i);
        } else if (id == R.id.needbase_criteria) {
            i = new Intent(this, applyforschloarship.class);
            startActivity(i);
        } else if (id == R.id.meritbase_criteria) {
            i = new Intent(this, applyforschloarship.class);
            startActivity(i);
        } else if (id == R.id.need_help) {
            i = new Intent(this, applyforschloarship.class);
            startActivity(i);

        }
    }
}
