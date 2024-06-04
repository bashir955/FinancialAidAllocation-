package com.example.financialaidallocation.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.financialaidallocation.Classes.BalanceModel;
import com.example.financialaidallocation.R;

import java.util.List;

public class BalanceAdapter extends RecyclerView.Adapter<BalanceAdapter.BalanceViewHolder> {

    private List<BalanceModel> balanceList;

    public BalanceAdapter(List<BalanceModel> balanceList) {
        this.balanceList = balanceList;
    }

    @NonNull
    @Override
    public BalanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dialog_balance, parent, false);
        return new BalanceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BalanceViewHolder holder, int position) {
        BalanceModel balance = balanceList.get(position);
        holder.balanceTextView.setText(String.valueOf(balance.getRemainingAmount()));
    }

    @Override
    public int getItemCount() {
        return balanceList.size();
    }

    static class BalanceViewHolder extends RecyclerView.ViewHolder {
        TextView balanceTextView;

        public BalanceViewHolder(@NonNull View itemView) {
            super(itemView);
            balanceTextView = itemView.findViewById(R.id.balance_text_view);
        }
    }
}