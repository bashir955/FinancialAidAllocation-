package com.example.financialaidallocation.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.financialaidallocation.Classes.ApplicationSuggestionModel;
import com.example.financialaidallocation.R;
import java.util.List;

public class ApplicationSuggestionAdapter extends RecyclerView.Adapter<ApplicationSuggestionAdapter.ApplicationViewHolder> {

    private List<ApplicationSuggestionModel> list;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(ApplicationSuggestionModel application);
    }

    public ApplicationSuggestionAdapter(List<ApplicationSuggestionModel> applicationList, OnItemClickListener onItemClickListener) {
        this.list = applicationList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ApplicationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_document, parent, false);
        return new ApplicationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ApplicationViewHolder holder, int position) {
        ApplicationSuggestionModel application = list.get(position);
        holder.TextViewName.setText(application.getRe().getName());
        holder.TextViewArid.setText(application.getRe().getArid_no());

        holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(application));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ApplicationViewHolder extends RecyclerView.ViewHolder {
        TextView TextViewName;
        TextView TextViewArid;

        public ApplicationViewHolder(@NonNull View itemView) {
            super(itemView);
            TextViewName = itemView.findViewById(R.id.name);
            TextViewArid = itemView.findViewById(R.id.arid_no);
        }
    }
}
