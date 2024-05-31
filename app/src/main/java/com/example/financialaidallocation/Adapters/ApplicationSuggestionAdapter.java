
package com.example.financialaidallocation.Adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.financialaidallocation.Activities.NeedbaseApplication;
import com.example.financialaidallocation.Classes.ApplicationSuggestionModel;
import com.example.financialaidallocation.R;

import java.util.List;

public class ApplicationSuggestionAdapter extends RecyclerView.Adapter<ApplicationSuggestionAdapter.ApplicationViewHolder> {

    private List<ApplicationSuggestionModel> applicationList;
//    private Context context;

    public ApplicationSuggestionAdapter( List<ApplicationSuggestionModel> applicationList) {
//        this.context = context;
        this.applicationList=applicationList;
    }
    @NonNull
    @Override
    public ApplicationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_document, parent, false);
        return new ApplicationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ApplicationViewHolder holder, int position) {
        ApplicationSuggestionModel application = applicationList.get(position);
        holder.TextViewName.setText(application.getName());
        holder.TextViewArid.setText(application.getArid_no());
    }

    @Override
    public int getItemCount() {
        return applicationList.size();
    }

    public static class ApplicationViewHolder extends RecyclerView.ViewHolder {
        TextView TextViewName;
        TextView TextViewArid;

        public ApplicationViewHolder(@NonNull View itemView) {
            super(itemView);
            TextViewName = itemView.findViewById(R.id.Name);
            TextViewArid = itemView.findViewById(R.id.aridno);
        }
    }
}
