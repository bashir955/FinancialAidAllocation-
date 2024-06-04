package com.example.financialaidallocation.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.financialaidallocation.Classes.ApplicationModel;
import com.example.financialaidallocation.R;

import java.util.List;

public class ApplicationAdapter extends RecyclerView.Adapter<ApplicationAdapter.ApplicationViewHolder> {

    private List<ApplicationModel> applications;
    private TextView remainingApplicationsTextView;

    public ApplicationAdapter(List<ApplicationModel> applications, TextView remainingApplicationsTextView) {
        this.applications = applications;
        this.remainingApplicationsTextView = remainingApplicationsTextView;
        updateRemainingApplications();
    }

    @NonNull
    @Override
    public ApplicationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_application, parent, false);
        return new ApplicationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ApplicationViewHolder holder, int position) {
        ApplicationModel application = applications.get(position);
        holder.nameTextView.setText(application.getName());
        holder.aridNoTextView.setText(application.getAridNo());
    }


    private void updateRemainingApplications() {
        remainingApplicationsTextView.setText(String.valueOf(applications.size()));
    }

    @Override
    public int getItemCount() {
        return applications.size();
    }

    public static class ApplicationViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView aridNoTextView;

        public ApplicationViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.Naame);
            aridNoTextView = itemView.findViewById(R.id.Aridno);
        }
    }
}
