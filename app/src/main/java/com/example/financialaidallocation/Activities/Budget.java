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
//        addIcon.setOnClickListener(v -> showAddAmountDialog());
        fetchBudgetData();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
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

//    private void showAddAmountDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        LayoutInflater inflater = getLayoutInflater();
//        View dialogView = inflater.inflate(R.layout.dialogaddamount, null);
//        builder.setView(dialogView);
//
//        EditText editTextAmount = dialogView.findViewById(R.id.edit_text_amount);
//        Button buttonAddAmount = dialogView.findViewById(R.id.button_add_amount);
//
//        AlertDialog dialog = builder.create();
//
//        buttonAddAmount.setOnClickListener(v -> {
//            String amount = editTextAmount.getText().toString();
//            if (!amount.isEmpty()) {
//                budgetItemList.add(new BudgetItemModel("Amount:", amount));
//                budgetAdapter.notifyItemInserted(budgetItemList.size() - 1);
//                dialog.dismiss();
//            } else {
//                editTextAmount.setError("Please enter an amount");
//            }
//        });
//
//        dialog.show();
//    }}