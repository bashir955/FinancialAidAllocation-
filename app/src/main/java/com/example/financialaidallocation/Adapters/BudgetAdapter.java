package com.example.financialaidallocation.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.financialaidallocation.Classes.BudgetModel;
import com.example.financialaidallocation.R;

import java.util.List;

public class BudgetAdapter extends RecyclerView.Adapter<BudgetAdapter.ViewHolder> {

    private List<BudgetModel> budgets;

    public BudgetAdapter(List<BudgetModel> budgets) {
        this.budgets = budgets;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itembudget, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BudgetModel budget = budgets.get(position);
        holder.session.setText(budget.getBudget_session());
        holder.amount.setText(String.valueOf(budget.getBudgetAmount()));
    }

    @Override
    public int getItemCount() {
        return budgets.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView session;
        public TextView amount;

        public ViewHolder(View itemView) {
            super(itemView);
            session = itemView.findViewById(R.id.Session);
            amount = itemView.findViewById(R.id.b_amount);
        }
    }
}
