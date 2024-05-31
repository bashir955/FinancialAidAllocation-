package com.example.financialaidallocation.Activities;

import static android.app.Activity.RESULT_OK;
import static android.widget.Toast.LENGTH_SHORT;

import static androidx.core.app.ActivityCompat.startActivityForResult;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.financialaidallocation.Classes.LoginResponse;
import com.example.financialaidallocation.Classes.SharedPrefManager;
import com.example.financialaidallocation.Classes.StudentResponse;
import com.example.financialaidallocation.R;

import Api.ApiService;
import Api.RetrofitClient;
import Api.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class applyforschloarship extends AppCompatActivity {
    private RadioGroup rgParentsDetail;
    private TextView stdNameTextView;
    private static final int PICK_IMAGE_REQUEST = 1;
    private TextView stdAridNoTextView;
    private ApiService apiService;
    UserService userService;
    private LinearLayout layoutParentsDetail;
    private LinearLayout layoutGuardianInfo;
    private LinearLayout layoutDeathCertificate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_applyforschloarship);

        stdNameTextView=findViewById(R.id.std_name);
        stdAridNoTextView=findViewById(R.id.std_aridno);
        userService = new UserService();
        apiService = RetrofitClient.getinstance().create(ApiService.class);

        // Retrieve user information from SharedPreferences
        LoginResponse user = SharedPrefManager.getInstance(this).getUser();

        if (user != null) {
            int profileId = user.getProfileId();
            fetchStudentInfo(profileId);
        } else {
            Toast.makeText(this, "User data not found", LENGTH_SHORT).show();
        }



        // Find the button by its ID
        Button nextButton = findViewById(R.id.nextButton);
        // Set an OnClickListener for the button
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the SecondActivity
                Intent intent = new Intent(applyforschloarship.this, personalInfo.class);
                startActivity(intent);
            }
        });

        LinearLayout cgpaLayout = findViewById(R.id.textViewCGPA);


        // Initialize views
        rgParentsDetail = findViewById(R.id.rgParentsDetail);
        layoutParentsDetail = findViewById(R.id.layoutParentsDetail);
        layoutGuardianInfo = findViewById(R.id.layoutGuardianInfo);
        layoutDeathCertificate = findViewById(R.id.layoutdeathcertificate);


        rgParentsDetail.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId == R.id.rbFatherAlive) {
                    // Show Parents Detail section
                    layoutParentsDetail.setVisibility(View.VISIBLE);
                    layoutGuardianInfo.setVisibility(View.GONE);
                } else if (checkedId == R.id.rbFatherDeceased) {
                    // Show Guardian Info section
                    layoutParentsDetail.setVisibility(View.GONE);
                    layoutGuardianInfo.setVisibility(View.VISIBLE);
                    layoutDeathCertificate.setVisibility(View.VISIBLE);
                }
            }
        });



        TextView tvUpload = findViewById(R.id.tvUpload);
        tvUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFilePicker();
            }
        });
        TextView salarytvFilePicker = findViewById(R.id.salarytvFilePicker);
        salarytvFilePicker.setOnClickListener(new View.OnClickListener() {
                                                  @Override
                                                  public void onClick(View v) {
                                                      // Handle file picker click here
                                                      openGallery();
                                                  }
                                              }
        );

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void fetchStudentInfo(int profileId) {

        Call<StudentResponse> call = apiService.getStudentInfo(profileId);
        call.enqueue(new Callback<StudentResponse>() {

            @Override
            public void onResponse(Call<StudentResponse> call, Response<StudentResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    StudentResponse student = response.body();
                    stdNameTextView.setText(student.getName());
                    stdAridNoTextView.setText(student.getAridNo());
                } else {
                    Toast.makeText(applyforschloarship.this, "Failed to fetch student info", LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<StudentResponse> call, Throwable t) {
                Toast.makeText(applyforschloarship.this, "Error: " + t.getMessage(), LENGTH_SHORT).show();
            }
        });

    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1234);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1234 && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            // Handle the selected image (e.g., display it in an ImageView or upload it to a server)
            TextView tvUpload = findViewById(R.id.tvUpload);
            tvUpload.setText("Selected image: " + selectedImage.getLastPathSegment());
        } else if (requestCode == 123 && resultCode == RESULT_OK) {
            {
                Uri selectedFileUri = data.getData();
                // Handle the selected file URI (e.g., display the file name)
                String fileName = selectedFileUri.getLastPathSegment();
                // Update your TextView with the selected file name
                TextView salarytvFilePicker = findViewById(R.id.salarytvFilePicker);
                salarytvFilePicker.setText("Selected file: " + fileName);
            }
        }
    }

    private void openFilePicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*"); // Set the MIME type of files you want to allow
        startActivityForResult(intent, 123); // Use a unique request code
    }
    // Handle the result after selecting a file (optional)
}


