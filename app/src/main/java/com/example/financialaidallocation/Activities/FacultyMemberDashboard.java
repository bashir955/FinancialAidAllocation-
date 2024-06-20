package com.example.financialaidallocation.Activities;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.financialaidallocation.Adapters.GraderAdapter;
import com.example.financialaidallocation.Adapters.StudentRecordAdapter;
import com.example.financialaidallocation.Classes.GraderModel;
import com.example.financialaidallocation.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class FacultyMemberDashboard extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ImageButton btndrawerToogle;
    private StudentRecordAdapter adapter;
    NavigationView navigationView;
    private RecyclerView recyclerView;
    private GraderAdapter graderAdapter;
    private List<GraderModel> graderList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_faculty_member_dashboard);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        List<Student> studentList = createDummyData();
//        List<Student> studentList = new ArrayList<>();
//        // Populate studentList with data
//
//        adapter = new StudentRecordAdapter(studentList, this);
//        recyclerView.setAdapter(adapter);
        // Add margin between items
        int space = getResources().getDimensionPixelSize(R.dimen.budget_item_spacing);
        recyclerView.addItemDecoration(new ItemDecoration(space));
        drawerLayout = findViewById(R.id.facultydrawer);
        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem Item) {
                int itemId = Item.getItemId();
                if (itemId == R.id.flogout) {
                    // Handle the logout action
                    Intent intent = new Intent(FacultyMemberDashboard.this, MainActivity.class);
                    startActivity(intent);
                    // Optional: close the current activity
                    return true;
                } else if (itemId==R.id.fSwitchtoCommittee) {
                    Intent intent=new Intent(FacultyMemberDashboard.this,CommitteeMemberDashboard.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
        btndrawerToogle = findViewById(R.id.fbtndrawerToogle);

        btndrawerToogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.open();
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.facultydrawer), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });}}
//        private List<Student> createDummyData() {
//            List<Student> dummyData = new ArrayList<>();
//            // Add dummy data for 3 students
//            dummyData.add(new Student("Abdullah Ashraf", " 2023-Arid-3977"));
//            dummyData.add(new Student("Alishba Rashid", "2022-Arid-3975"));
//
//            return dummyData;
//    }
//}