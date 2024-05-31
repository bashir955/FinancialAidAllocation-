package com.example.financialaidallocation.Activities;

import static com.example.financialaidallocation.R.id.housetvFilePicker;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.financialaidallocation.R;

public class personalInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_personal_info);
        // Find the TextView by its ID
        TextView textViewFilePicker = findViewById(housetvFilePicker);


        // Set an OnClickListener for the TextView
        textViewFilePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the method to open the file picker
                openFilePicker();
            }
        });}
//        evidenceTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openevidanceFiePicker();
//            }
//        });
//    }
    // Method to open the file picker
    public void openFilePicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*"); // Set the file type you want to allow (e.g., images, PDFs, etc.)
        startActivityForResult(intent, 1);
    }
//    // Method to open the evidence file picker
//    public void openevidanceFiePicker() {
//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        intent.setType("*/*"); // Set the file type you want to allow
//        startActivityForResult(intent, 2);
//    }

    // Handle the result after selecting a file (optional)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Uri selectedFileUri = data.getData();
            // Handle the selected file URI (e.g., display the file name)
            String fileName = selectedFileUri.getLastPathSegment();
            // Update your TextView with the selected file name
            TextView textViewFilePicker = findViewById(housetvFilePicker);
            textViewFilePicker.setText("Selected file: " + fileName);
        }
//        else if (requestCode == 2 && resultCode == RESULT_OK) {
//            Uri selectedFileUri = data.getData();
//            // Handle the selected file URI (e.g., display the file name)
//            String fileName = selectedFileUri.getLastPathSegment();
//            // Update your TextView with the selected file name
//            TextView textViewFilePicker = findViewById(evidancetvFilePicker);
//            textViewFilePicker.setText("Selected file: " + fileName);
//        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }}