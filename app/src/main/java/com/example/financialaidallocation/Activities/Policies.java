package com.example.financialaidallocation.Activities;

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

import com.example.financialaidallocation.Adapters.PolicyAdapter;
import com.example.financialaidallocation.Classes.PolicyModel;
import com.example.financialaidallocation.R;

import java.util.ArrayList;
import java.util.List;

public class Policies extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PolicyAdapter policyAdapter;
    private List<PolicyModel> policyList;
    private ImageView addIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_policies);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Add spacing between items
        int space = getResources().getDimensionPixelSize(R.dimen.budget_item_spacing);
        recyclerView.addItemDecoration(new ItemDecoration(space));
        addIcon = findViewById(R.id.add_icon);
        addIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Policies.this, AddPolicies.class);
                startActivity(intent);
            }
        });


        policyList = new ArrayList<>();
        // Add some sample data
        policyList.add(new PolicyModel("Needbase", "RequireCgpa: 3.5", "CGPA description for needbase policy"));
        policyList.add(new PolicyModel("Meritbase", "RequireCgpa: 3.8", "CGPA description for meritbase policy"));

        policyAdapter = new PolicyAdapter(policyList, position -> {
            // Handle the edit action
            PolicyModel policy = policyList.get(position);
            Toast.makeText(this, "Edit: " + policy.getPolicyType(), Toast.LENGTH_SHORT).show();
            // Implement your edit functionality here
        });

        recyclerView.setAdapter(policyAdapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    }