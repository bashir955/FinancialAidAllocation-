package com.example.financialaidallocation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StudentRecord extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StudentRecordAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_student_record);

        ImageView addIcon = findViewById(R.id.add_icon);

        // Set click listener to the "+" icon
        addIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the AddStudent activity
                Intent intent = new Intent(StudentRecord.this, AddStudent.class);
                startActivity(intent);
                Toast.makeText(StudentRecord.this, "ccc", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Student> studentList = createDummyData();
//        List<Student> studentList = new ArrayList<>();
        // Populate studentList with data

        adapter = new StudentRecordAdapter(studentList, this);
        recyclerView.setAdapter(adapter);
        // Add margin between items
        int space = getResources().getDimensionPixelSize(R.dimen.budget_item_spacing);
        recyclerView.addItemDecoration(new ItemDecoration(space));


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private List<Student> createDummyData() {
        List<Student> dummyData = new ArrayList<>();
        // Add dummy data for 3 students
        dummyData.add(new Student("John Doe", "123456"));
        dummyData.add(new Student("Jane Smith", "789012"));
        dummyData.add(new Student("Alice Johnson", "345678"));
        return dummyData;
}}