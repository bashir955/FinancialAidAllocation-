package com.example.financialaidallocation.Activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.financialaidallocation.Adapters.MeribaseCriteriaAdapter;
import com.example.financialaidallocation.Adapters.PolicyAdapter;
import com.example.financialaidallocation.Classes.PolicyModel;
import com.example.financialaidallocation.Classes.ScholorShipPolicy;
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

public class MeritbaseCriteria extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MeribaseCriteriaAdapter adapter;
    private UserService userService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_meritbase_criteria);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        int space = getResources().getDimensionPixelSize(R.dimen.budget_item_spacing);
        recyclerView.addItemDecoration(new ItemDecoration(space));



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getPolicies();
    }
    private void getPolicies() {
        ApiService apiService = RetrofitClient.getinstance().create(ApiService.class);
        Call<List<ScholorShipPolicy>> call = apiService.getPolicies();

        call.enqueue(new Callback<List<ScholorShipPolicy>>() {
            @Override
            public void onResponse(Call<List<ScholorShipPolicy>> call, Response<List<ScholorShipPolicy>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ScholorShipPolicy> policies = response.body();

                    Log.d("Policies", "Received policies: " + policies);
                    // Filter policies to include only those with policyfor = "MeritBase"
                    List<ScholorShipPolicy> meritBasePolicies = new ArrayList<>();
                    for (ScholorShipPolicy policy : policies) {
                        if ("MeritBase".equalsIgnoreCase(policy.getP().getPolicyfor())) {
                            meritBasePolicies.add(policy);
                        }
                    }
                    adapter = new MeribaseCriteriaAdapter(meritBasePolicies);
                    recyclerView.setAdapter(adapter);

                    // Log for debugging
                    Log.d("Policies", "Filtered policies: " + meritBasePolicies);
                    Toast.makeText(MeritbaseCriteria.this, "Fetched policies", Toast.LENGTH_SHORT).show();
                } else {
                    // Log the response for debugging
                    Log.e("Policies", "Response Code: " + response.code());
                    try {
                        Log.e("Policies", "Response Body: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(MeritbaseCriteria.this, "Failed to fetch policies", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ScholorShipPolicy>> call, Throwable t) {
                Log.e("Policies", "onFailure: ", t);
                Toast.makeText(MeritbaseCriteria.this, "An error occurred", Toast.LENGTH_SHORT).show();
            }
        });
}}