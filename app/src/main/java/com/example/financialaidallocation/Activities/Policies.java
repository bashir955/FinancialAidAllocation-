package com.example.financialaidallocation.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.financialaidallocation.Adapters.PolicyAdapter;
import com.example.financialaidallocation.Classes.MeritbaseStudentModel;
import com.example.financialaidallocation.Classes.PolicyModel;
import com.example.financialaidallocation.Classes.StudentModel;
import com.example.financialaidallocation.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Api.ApiService;
import Api.RetrofitClient;
import Api.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Policies extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PolicyAdapter policyAdapter;
    private UserService userService;
    private List<PolicyModel> List;
    private ImageView addIcon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policies);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        userService = new UserService();
        fetchPolicies();
    }


    private void fetchPolicies() {
        ApiService apiService = RetrofitClient.getinstance().create(ApiService.class);
        Call<List<PolicyModel>> call = apiService.getPolicies();

        call.enqueue(new Callback<List<PolicyModel>>() {
            @Override
            public void onResponse(Call<List<PolicyModel>> call, Response<List<PolicyModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<PolicyModel> policies = response.body();
                    policyAdapter = new PolicyAdapter(policies);
                    recyclerView.setAdapter(policyAdapter);

                    // Log for debugging
                    Log.d("Policies", "Fetched policies: " + policies);
                    Toast.makeText(Policies.this, "Fetched policies", Toast.LENGTH_SHORT).show();
                } else {
                    // Log the response for debugging
                    Log.e("Policies", "Response Code: " + response.code());
                    try {
                        Log.e("Policies", "Response Body: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(Policies.this, "Failed to fetch policies", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<PolicyModel>> call, Throwable t) {
                Log.e("Policies", "onFailure: ", t);
                Toast.makeText(Policies.this, "An error occurred", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
