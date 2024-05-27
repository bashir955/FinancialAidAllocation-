package com.example.financialaidallocation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.security.Policy;
import java.util.List;

public class PolicyAdapter extends RecyclerView.Adapter<PolicyAdapter.PolicyViewHolder> {

    private List<PolicyModel> policyList;
    private OnEditClickListener onEditClickListener;

    public interface OnEditClickListener {
        void onEditClick(int position);
    }

    public PolicyAdapter(List<PolicyModel> policyList, OnEditClickListener onEditClickListener) {
        this.policyList = policyList;
        this.onEditClickListener = onEditClickListener;
    }

    @NonNull
    @Override
    public PolicyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_policy, parent, false);
        return new PolicyViewHolder(view, onEditClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PolicyViewHolder holder, int position) {
        PolicyModel policy = policyList.get(position);
        holder.policyType.setText(policy.getPolicyType());
        holder.requiredCgpa.setText(policy.getRequiredCgpa());
        holder.cgpaDescription.setText(policy.getCgpaDescription());
    }

    @Override
    public int getItemCount() {
        return policyList.size();
    }

    public static class PolicyViewHolder extends RecyclerView.ViewHolder {
        TextView policyType, requiredCgpa, cgpaDescription;
        ImageView editIcon;

        public PolicyViewHolder(@NonNull View itemView, OnEditClickListener onEditClickListener) {
            super(itemView);
            policyType = itemView.findViewById(R.id.policy_type);
            requiredCgpa = itemView.findViewById(R.id.required_cgpa);
            cgpaDescription = itemView.findViewById(R.id.cgpa_description);
            editIcon = itemView.findViewById(R.id.edit_icon);

            editIcon.setOnClickListener(v -> {
                if (onEditClickListener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        onEditClickListener.onEditClick(position);
                    }
                }
            });
        }}}
