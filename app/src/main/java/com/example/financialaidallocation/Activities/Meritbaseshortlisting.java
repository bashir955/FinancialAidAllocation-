package com.example.financialaidallocation.Activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.financialaidallocation.Adapters.MeritBaseStudentAdapter;
import com.example.financialaidallocation.Classes.MeritbaseStudentModel;
import com.example.financialaidallocation.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Api.ApiService;
import Api.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Meritbaseshortlisting extends AppCompatActivity {
    private RecyclerView recyclerView;
    private EditText searchEditText;
    private ImageView searchIcon;
    private List<MeritbaseStudentModel> meritBaseList;
    private MeritBaseStudentAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_meritbaseshortlisting);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchEditText = findViewById(R.id.search_edittext);
        searchIcon = findViewById(R.id.search_icon);

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.meritrecycler_item_margin);
        recyclerView.addItemDecoration(new ItemDecoration(spacingInPixels));


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        fetchMeritBaseStudents();
        setupSearch();
    }

    private void setupSearch() {
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (adapter != null) {
                    adapter.filter(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        searchIcon.setOnClickListener(view -> {
            String query = searchEditText.getText().toString();
            if (adapter != null) {
                adapter.filter(query);
            }
        });
    }


    private void fetchMeritBaseStudents() {
        ApiService apiService = RetrofitClient.getinstance().create(ApiService.class);
        Call<List<MeritbaseStudentModel>> call = apiService.getMeritBaseShortListedStudent();

        call.enqueue(new Callback<List<MeritbaseStudentModel>>() {
            @Override
            public void onResponse(Call<List<MeritbaseStudentModel>> call, Response<List<MeritbaseStudentModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                     meritBaseList = response.body();
                    adapter = new MeritBaseStudentAdapter(meritBaseList);
                    recyclerView.setAdapter(adapter);

                } else {
                    // Log the response for debugging
                    Log.e("Meritbaseshortlisting", "Response Code: " + response.code());
                    try {
                        Log.e("Meritbaseshortlisting", "Response Body: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                }
                    Toast.makeText(Meritbaseshortlisting.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<List<MeritbaseStudentModel>> call, Throwable t) {
                Log.e("Meritbaseshortlisting", "onFailure: ", t);
                Toast.makeText(Meritbaseshortlisting.this, "An error occurred", Toast.LENGTH_SHORT).show();
            }
        });}}
