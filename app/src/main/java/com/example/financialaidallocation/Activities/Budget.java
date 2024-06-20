package com.example.financialaidallocation.Activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.app.AlertDialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import java.util.ArrayList;
import java.util.List;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.financialaidallocation.Adapters.BudgetAdapter;
import com.example.financialaidallocation.Classes.BudgetModel;
import com.example.financialaidallocation.R;

import Api.ApiService;
import Api.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Budget extends AppCompatActivity {
    private RecyclerView recyclerView;
    private BudgetAdapter adapter;
    private UserService userService;
    private ImageView addIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_budget);

        addIcon = findViewById(R.id.add_icon);
        recyclerView = findViewById(R.id.recycler_view);
        // Initialize RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.meritrecycler_item_margin);
        recyclerView.addItemDecoration(new ItemDecoration(spacingInPixels));
        userService = new UserService();
//        // Handle add icon click
        addIcon.setOnClickListener(v -> showAddBudgetDialog());
        fetchBudgetData();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void showAddBudgetDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_budget, null);
        dialogBuilder.setView(dialogView);

        EditText editTextAmount = dialogView.findViewById(R.id.edit_text_amount);
        Button buttonAdd = dialogView.findViewById(R.id.button_add);

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        buttonAdd.setOnClickListener(v ->  {

                String amountStr = editTextAmount.getText().toString();
                if (!amountStr.isEmpty()) {
                    int amount = Integer.parseInt(amountStr);
                    addBudget(amount);
                    alertDialog.dismiss();
                } else {
                    Toast.makeText(Budget.this, "Please enter an amount", Toast.LENGTH_SHORT).show();
                }

        });
    }
    private void addBudget(int amount) {
        userService.addBudget(amount, new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Integer budgetId = response.body();
                    BudgetModel newBudget = new BudgetModel();
                    newBudget.setBudgetId(budgetId);
                    newBudget.setBudgetAmount(amount);
                    newBudget.setBudget_session("Fall-2024");
                    // Add the new budget to the adapter and update the RecyclerView
                    adapter.addBudget(newBudget);
                    // Handle success, maybe update UI or show a success message
                    fetchBudgetData(); // Refresh data after adding a new budget
                } else {
                    Log.e("API_ERROR", "Response was not successful"+response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.e("API_ERROR", "Failed to add budget: " + t.getMessage());
            }
        });
    }


    private void fetchBudgetData() {
        userService.getAllBudgets(new Callback<List<BudgetModel>>() {
            @Override
            public void onResponse(Call<List<BudgetModel>> call, Response<List<BudgetModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<BudgetModel> budgetList = response.body();
                    // Set the adapter with the fetched data
                    adapter = new BudgetAdapter(budgetList);
                    recyclerView.setAdapter(adapter);
                } else {
                    Log.e("API_ERROR", "Response was not successful");
                }
            }

            @Override
            public void onFailure(Call<List<BudgetModel>> call, Throwable t) {
                Log.e("API_ERROR", "Failed to fetch data: " + t.getMessage());
            }
        });
    }

}
