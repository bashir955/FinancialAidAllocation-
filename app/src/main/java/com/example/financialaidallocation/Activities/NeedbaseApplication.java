package com.example.financialaidallocation.Activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.financialaidallocation.Adapters.DocumentAdapter;
import com.example.financialaidallocation.R;

import java.util.ArrayList;
import java.util.List;

public class NeedbaseApplication extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Document> documentList;
    private DocumentAdapter documentAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_needbase_application);
        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Add margin between items
        int space = getResources().getDimensionPixelSize(R.dimen.recycler_item_margin);
        recyclerView.addItemDecoration(new ItemDecoration(space));

        documentList = new ArrayList<>();
        documentList.add(new Document(R.drawable.document, "Muhammad Ali", "2019-ARID-12345"));
        documentList.add(new Document(R.drawable.document, "M Amir Shehzad", "2018-ARID-67890"));
        documentList.add(new Document(R.drawable.document, "Muhammad Bashir","2020-Arid-3699"));
        documentList.add(new Document(R.drawable.document, "Arsalan Raja","2023-Arid-4322"));
        documentList.add(new Document(R.drawable.document, "Maria Bukhari","2021-Arid-2319"));
        documentList.add(new Document(R.drawable.document, "Umair Malik","2020-Arid-2516"));
         documentAdapter = new DocumentAdapter(this, documentList);
        recyclerView.setAdapter(documentAdapter);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}