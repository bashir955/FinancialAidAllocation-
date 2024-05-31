package com.example.financialaidallocation.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.financialaidallocation.Adapters.StudentRecordAdapter;
import com.example.financialaidallocation.Classes.StudentModel;
import com.example.financialaidallocation.R;

import java.util.ArrayList;
import java.util.List;

import Api.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentRecord extends AppCompatActivity {
    private UserService userService;
    private RecyclerView recyclerView;
    private StudentRecordAdapter adapter;
    private EditText searchEditText;
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
        searchEditText = findViewById(R.id.search_edittext);
        userService = new UserService();
        // Add margin between items
        int space = getResources().getDimensionPixelSize(R.dimen.budget_item_spacing);
        recyclerView.addItemDecoration(new ItemDecoration(space));

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);

            }

            @Override
            public void afterTextChanged(Editable s) {
                // Do nothing
            }
        });
        loadStudents();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void loadStudents() {
        userService.getAllStudents(new Callback<List<StudentModel>>() {
            @Override
            public void onResponse(Call<List<StudentModel>> call, Response<List<StudentModel>> response) {
                if (response.isSuccessful()) {
                    List<StudentModel> students = response.body();
                    adapter = new StudentRecordAdapter(students);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(StudentRecord.this, "Failed to load students", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<StudentModel>> call, Throwable t) {
                Toast.makeText(StudentRecord.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
