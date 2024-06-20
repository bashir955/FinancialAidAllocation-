package com.example.financialaidallocation.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.financialaidallocation.Activities.ApplicationDetailActivity;
import com.example.financialaidallocation.Classes.ApplicationModel;
import com.example.financialaidallocation.Classes.SharedPrefManager;
import com.example.financialaidallocation.R;

import java.util.List;

public class ApplicationAdapter extends RecyclerView.Adapter<ApplicationAdapter.ApplicationViewHolder> {

    private List<ApplicationModel> applications;
    private Context context;
    private TextView remainingApplicationsTextView;

    public ApplicationAdapter(List<ApplicationModel> applications ,Context context) {
        this.applications = applications;
        this.context = context;

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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Save selected application to SharedPreferences
                SharedPrefManager.getInstance(context).saveSelectedApplication(application);

                // Open the detail activity
                Intent intent = new Intent(context, ApplicationDetailActivity.class);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return applications.size();
    }
    public void setApplications(List<ApplicationModel> applications) {
        this.applications = applications;
//        updateRemainingApplicationsCount();
        notifyDataSetChanged(); // Notify RecyclerView about the dataset change
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
