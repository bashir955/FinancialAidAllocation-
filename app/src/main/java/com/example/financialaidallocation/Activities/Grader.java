package com.example.financialaidallocation.Activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.net.Uri;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.financialaidallocation.Adapters.GraderAdapter;
import com.example.financialaidallocation.Classes.GraderModel;
import com.example.financialaidallocation.R;

import java.util.ArrayList;
import java.util.List;

public class Grader extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GraderAdapter graderAdapter;
    private List<GraderModel> graderList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_grader);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Add space between items (16dp)
        int space = getResources().getDimensionPixelSize(R.dimen.grader_item_spacing);
        recyclerView.addItemDecoration(new ItemDecoration(space));

        graderList = new ArrayList<>();
        graderList.add(new GraderModel("John Doe", "ARID12345", null));
        graderList.add(new GraderModel("Jane Smith", "ARID54321", null));
        // Add more items as needed

        graderAdapter = new GraderAdapter(this, graderList);
        recyclerView.setAdapter(graderAdapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            int position = requestCode - GraderAdapter.PICK_IMAGE; // Get the original position

            if (position >= 0 && position < graderList.size()) {
                graderAdapter.updateItemImage(position, imageUri.toString());
            }
        }
    }
}