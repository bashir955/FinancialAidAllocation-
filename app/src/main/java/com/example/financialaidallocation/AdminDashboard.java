package com.example.financialaidallocation;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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