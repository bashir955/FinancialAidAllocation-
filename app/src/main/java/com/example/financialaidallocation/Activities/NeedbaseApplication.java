// NeedbaseApplication.java

package com.example.financialaidallocation.Activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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

import com.example.financialaidallocation.Adapters.ApplicationSuggestionAdapter;
import com.example.financialaidallocation.Classes.ApplicationSuggestionModel;
import com.example.financialaidallocation.R;

import java.util.ArrayList;
import java.util.List;

import Api.ApiService;
import Api.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NeedbaseApplication extends AppCompatActivity {


    private RecyclerView recyclerView;
    private List<ApplicationSuggestionModel> applicationList;
    private ApplicationSuggestionAdapter adapter;
    private EditText searchEditText;
    private ImageView searchIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_needbase_application);


        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        searchEditText = findViewById(R.id.search_edittext);
        searchIcon = findViewById(R.id.search_icon);

        // Add margin between items
        int space = getResources().getDimensionPixelSize(R.dimen.recycler_item_margin);
        recyclerView.addItemDecoration(new ItemDecoration(space));

        // Initialize applicationList and adapter
//        applicationList = new ArrayList<>();
//        adapter = new ApplicationSuggestionAdapter(this, applicationList);
//        recyclerView.setAdapter(adapter);
        applicationList = new ArrayList<>();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        fetchApplications();
        setupSearch();
    }

    private void fetchApplications() {
        ApiService apiService = RetrofitClient.getinstance().create(ApiService.class);
        Call<List<ApplicationSuggestionModel>> call = apiService.getApplicationSuggestions();

        call.enqueue(new Callback<List<ApplicationSuggestionModel>>() {
            @Override
            public void onResponse(Call<List<ApplicationSuggestionModel>> call, Response<List<ApplicationSuggestionModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    applicationList = response.body();
                    adapter = new ApplicationSuggestionAdapter(applicationList);
                    recyclerView.setAdapter(adapter);
                    Log.d("API_SUCCESS", "Application data fetched successfully");
                } else {
                    Log.e("API_ERROR", "Failed to fetch data");
                }
            }

            @Override
            public void onFailure(Call<List<ApplicationSuggestionModel>> call, Throwable t) {
                Toast.makeText(NeedbaseApplication.this, "An error occurred", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void setupSearch() {
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // No filter logic here
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // No filter logic here
            }
        });
    }
}
