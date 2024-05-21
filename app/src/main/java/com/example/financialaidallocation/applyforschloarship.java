package com.example.financialaidallocation;

import static com.example.financialaidallocation.R.id.housetvFilePicker;
import static com.example.financialaidallocation.R.id.salarytvFilePicker;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class applyforschloarship extends AppCompatActivity {
    private RadioGroup rgParentsDetail;
    private LinearLayout layoutParentsDetail;
    private LinearLayout layoutGuardianInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_applyforschloarship);
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

        Spinner semesterSpinner = findViewById(R.id.semesterSpinner);
        LinearLayout cgpaLayout = findViewById(R.id.textViewCGPA);

        semesterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedSemester = (String) parentView.getItemAtPosition(position);
                if (selectedSemester.equals("1st")) {
                    cgpaLayout.setVisibility(View.GONE);
                } else {
                    cgpaLayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing
            }
        });


        // Initialize views
        rgParentsDetail = findViewById(R.id.rgParentsDetail);
        layoutParentsDetail = findViewById(R.id.layoutParentsDetail);
        layoutGuardianInfo = findViewById(R.id.layoutGuardianInfo);

        rgParentsDetail.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbFatherAlive) {
                    // Show Parents Detail section
                    layoutParentsDetail.setVisibility(View.VISIBLE);
                    layoutGuardianInfo.setVisibility(View.GONE);
                } else if (checkedId == R.id.rbFatherDeceased) {
                    // Show Guardian Info section
                    layoutParentsDetail.setVisibility(View.GONE);
                    layoutGuardianInfo.setVisibility(View.VISIBLE);
                }
            }
        });

        // Create an array of course names (you can replace this with your own data)
        String[] courseNames = {"1st", "2nd", "3rd", "4th", "5th", "6th", "7th", "8th"};

        // Create an ArrayAdapter to bind data to the spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, courseNames);

        // Set the dropdown layout style
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Attach the adapter to the spinner
        semesterSpinner.setAdapter(adapter);

        TextView salarytvFilePicker = findViewById(R.id.salarytvFilePicker);
        salarytvFilePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle file picker click here
                openFilePicker();
            }
        }
    );
}
        private void openFilePicker() {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("*/*"); // Set the MIME type of files you want to allow
            startActivityForResult(intent, 123); // Use a unique request code
    }
            // Handle the result after selecting a file (optional)
            @Override
            protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                super.onActivityResult(requestCode, resultCode, data);
                if (requestCode == 123 && resultCode == RESULT_OK) {
                    Uri selectedFileUri = data.getData();
                    // Handle the selected file URI (e.g., display the file name)
                    String fileName = selectedFileUri.getLastPathSegment();
                    // Update your TextView with the selected file name
                    TextView salarytvFilePicker = findViewById(R.id.salarytvFilePicker);
                    salarytvFilePicker.setText("Selected file: " + fileName);
                }
   ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
   });
    }}


