package com.example.financialaidallocation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BudgetAdapter extends RecyclerView.Adapter<BudgetAdapter.ViewHolder> implements Filterable {

    private List<BudgetItemModel> budgetItemList;
    private List<BudgetItemModel> budgetItemListFull;

    public BudgetAdapter(List<BudgetItemModel> budgetItemList) {
        this.budgetItemList = budgetItemList;
        this.budgetItemListFull = new ArrayList<>(budgetItemList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itembudget, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BudgetItemModel budgetItem = budgetItemList.get(position);
        holder.nameTextView.setText(budgetItem.getName());
        holder.amountTextView.setText(budgetItem.getAmount());
    }

    @Override
    public int getItemCount() {
        return budgetItemList.size();
    }

    @Override
    public Filter getFilter() {
        return budgetFilter;
    }

    private Filter budgetFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<BudgetItemModel> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(budgetItemListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (BudgetItemModel item : budgetItemListFull) {
                    if (item.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            budgetItemList.clear();
            budgetItemList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView amountTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.item_name);
            amountTextView = itemView.findViewById(R.id.item_amount);
        }
    }
}