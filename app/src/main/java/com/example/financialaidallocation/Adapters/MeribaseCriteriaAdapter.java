package com.example.financialaidallocation.Adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.financialaidallocation.Classes.PolicyModel;
import com.example.financialaidallocation.Classes.ScholorShipPolicy;
import com.example.financialaidallocation.R;

import java.util.List;

public class MeribaseCriteriaAdapter extends RecyclerView.Adapter<MeribaseCriteriaAdapter.PolicyViewHolder> {
    private List<ScholorShipPolicy> policies;

    public MeribaseCriteriaAdapter(List<ScholorShipPolicy> policies) {
        this.policies = policies;
    }

    @NonNull
    @Override
    public PolicyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_policy, parent, false);
        return new PolicyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PolicyViewHolder holder, int position) {
        ScholorShipPolicy policy = policies.get(position);


        // Logging to check if data is being set
        Log.d("PolicyAdapter", "Binding data at position: " + position + " with data: " + policy.toString());

        if (policy != null) {
            holder.policyType.setText(policy.getP().getPolicyfor());
            holder.session.setText(policy.getP().getSession());
            holder.description.setText(policy.getC().getDescription());
            holder.policy.setText(policy.getP().getPolicy1());
            holder.requiredCgpa.setText(String.valueOf(policy.getC().getVal1()));
            holder.strength.setText(String.valueOf(policy.getC().getStrength()));
        } else {
            Log.e("PolicyAdapter", "Policy data is null at position: " + position);
        }
    }

    @Override
    public int getItemCount() {
        return policies != null ? policies.size() : 0;
    }

    public static class PolicyViewHolder extends RecyclerView.ViewHolder {
        TextView policyType, session, description, policy, requiredCgpa, strength;

        public PolicyViewHolder(@NonNull View itemView) {
            super(itemView);
            policyType = itemView.findViewById(R.id.policy_type);
            session = itemView.findViewById(R.id.session);
            description = itemView.findViewById(R.id.Description);
            policy = itemView.findViewById(R.id.policy);
            requiredCgpa = itemView.findViewById(R.id.required_cgpa);
            strength = itemView.findViewById(R.id.str);
        }
    }
}