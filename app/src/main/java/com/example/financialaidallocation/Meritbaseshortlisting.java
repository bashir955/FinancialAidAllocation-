package com.example.financialaidallocation;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Meritbaseshortlisting extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MeritbaseStudentAdapter studentAdapter;
    private List<MeritbaseStudentModel> studentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_meritbaseshortlisting);


        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.meritrecycler_item_margin);
        recyclerView.addItemDecoration(new ItemDecoration(spacingInPixels));

        studentList = new ArrayList<>();
        studentList.add(new MeritbaseStudentModel("John Doe", "ARID 12345"));
        studentList.add(new MeritbaseStudentModel("Jane Smith", "ARID 67890"));
        // Add more students as needed

        studentAdapter = new MeritbaseStudentAdapter(this, studentList);
        recyclerView.setAdapter(studentAdapter);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}