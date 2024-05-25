package com.example.financialaidallocation;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class AdminDashboard extends AppCompatActivity {
    DrawerLayout draweradminLayout;
    ImageButton btnadmindrawerToogle;
    NavigationView adminnavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_dashboard);
        draweradminLayout = findViewById(R.id.admindrawerlayout);

        CardView needbaseapplication = findViewById(R.id.needbase_application);

        // Set an OnClickListener on the CardView
        needbaseapplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the MeritBaseShortlistingActivity
                Intent intent = new Intent(AdminDashboard.this, NeedbaseApplication.class);
                startActivity(intent);
            }
        });
        CardView acceptedapplication = findViewById(R.id.accepted_application);

        // Set an OnClickListener on the CardView
        acceptedapplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the MeritBaseShortlistingActivity
                Intent intent = new Intent(AdminDashboard.this, AcceptedApplication.class);
                startActivity(intent);
            }
        });

        CardView rejectedapplication = findViewById(R.id.rejected_application);

        // Set an OnClickListener on the CardView
        rejectedapplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the MeritBaseShortlistingActivity
                Intent intent = new Intent(AdminDashboard.this, RejectedApplication.class);
                startActivity(intent);
            }
        });

        CardView commiteemember = findViewById(R.id.Commitee_Member);

        // Set an OnClickListener on the CardView
        commiteemember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the MeritBaseShortlistingActivity
                Intent intent = new Intent(AdminDashboard.this, CommiteeMember.class);
                startActivity(intent);
            }
        });

        CardView grader = findViewById(R.id.assigngrader);

        // Set an OnClickListener on the CardView
        grader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the MeritBaseShortlistingActivity
                Intent intent = new Intent(AdminDashboard.this, Grader.class);
                startActivity(intent);
            }
        });


        // Find the CardView by its ID
        CardView meritBaseShort = findViewById(R.id.meritbase_short);

        // Set an OnClickListener on the CardView
        meritBaseShort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the MeritBaseShortlistingActivity
                Intent intent = new Intent(AdminDashboard.this, Meritbaseshortlisting.class);
                startActivity(intent);
            }
        });

        adminnavigationView = findViewById(R.id.admin_navigationView);
        adminnavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem Item) {
                int itemId = Item.getItemId();
                if (itemId == R.id.anav_logout) {
                    // Handle the logout action
                    Intent intent = new Intent(AdminDashboard.this, MainActivity.class);
                    startActivity(intent);
                    finish(); // Optional: close the current activity
                    return true;
                } else if (itemId == R.id.anav_budget) {
                    Intent intent = new Intent(AdminDashboard.this, Budget.class);
                    startActivity(intent);
                    finish(); // Optional: close the current activity
                    return true;
                }else if (itemId == R.id.anav_student) {
                    Intent intent = new Intent(AdminDashboard.this, StudentRecord.class);
                    startActivity(intent);
                    finish(); // Optional: close the current activity
                    return true;
                }else if (itemId == R.id.anav_policies) {
                    Intent intent = new Intent(AdminDashboard.this, Policies.class);
                    startActivity(intent);
                    finish(); // Optional: close the current activity
                    return true;
                }
                return false;
            }
        });
        btnadmindrawerToogle = findViewById(R.id.adminbuttondrawerToogle);

        btnadmindrawerToogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                draweradminLayout.open();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.admindrawerlayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}