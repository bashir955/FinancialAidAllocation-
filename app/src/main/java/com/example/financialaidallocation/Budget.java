package com.example.financialaidallocation;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

public class Budget extends AppCompatActivity {
    private RecyclerView recyclerView;
    private BudgetAdapter budgetAdapter;
    private List<BudgetItemModel> budgetItemList;
    private SearchView searchView;
    private ImageView addIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_budget);

        recyclerView = findViewById(R.id.recycler_view);
        searchView = findViewById(R.id.search_bar);
        addIcon = findViewById(R.id.add_icon);

        // Initialize RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.meritrecycler_item_margin);
        recyclerView.addItemDecoration(new ItemDecoration(spacingInPixels));
        budgetItemList = new ArrayList<>();
        budgetAdapter = new BudgetAdapter(budgetItemList);
        recyclerView.setAdapter(budgetAdapter);

        // Handle search
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                budgetAdapter.getFilter().filter(newText);
                return true;
            }
        });

        // Handle add icon click
        addIcon.setOnClickListener(v -> showAddAmountDialog());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void showAddAmountDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialogaddamount, null);
        builder.setView(dialogView);

        EditText editTextAmount = dialogView.findViewById(R.id.edit_text_amount);
        Button buttonAddAmount = dialogView.findViewById(R.id.button_add_amount);

        AlertDialog dialog = builder.create();

        buttonAddAmount.setOnClickListener(v -> {
            String amount = editTextAmount.getText().toString();
            if (!amount.isEmpty()) {
                budgetItemList.add(new BudgetItemModel("Amount:", amount));
                budgetAdapter.notifyItemInserted(budgetItemList.size() - 1);
                dialog.dismiss();
            } else {
                editTextAmount.setError("Please enter an amount");
            }
        });

        dialog.show();
    }}