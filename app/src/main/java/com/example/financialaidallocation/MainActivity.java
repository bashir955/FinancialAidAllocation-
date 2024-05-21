package com.example.financialaidallocation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
private Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        CircleImageView imageView = findViewById(R.id.biitlogo);


        login=findViewById(R.id.login_btn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the entered username and ARID number from EditText fields
                EditText usernameEditText = findViewById(R.id.username_input);
                EditText aridEditText = findViewById(R.id.password_input);
                String username = usernameEditText.getText().toString().trim();
                String arid = aridEditText.getText().toString().trim();

                // Check if username and ARID match the conditions
                if (username.equals("M Bashir") && arid.equals("2020-Arid-3699")) {
                    // Navigate to StudentDashboardActivity
                    Intent intent = new Intent(MainActivity.this, StudentDashboard.class);
                    startActivity(intent);
                } else if (username.equals("Abdul Islam") && arid.equals("2020-Arid-3677")) {
                    // Navigate to CommitteeMemberDashboardActivity
                    Intent intent = new Intent(MainActivity.this, CommitteeMemberDashboard.class);
                    startActivity(intent);

                }
                else if (username.equals("Hamid") && arid.equals("2020-Arid-3111")) {
                    // Navigate to AdminDashboardActivity
                    Intent intent = new Intent(MainActivity.this, AdminDashboard.class);
                    Toast.makeText(MainActivity.this, "sai", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                } else {
                    // Show error message or handle invalid login
                    Toast.makeText(MainActivity.this, "Invalid username or ARID number", Toast.LENGTH_SHORT).show();
                }
            }});
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v,insets)->
            {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }}