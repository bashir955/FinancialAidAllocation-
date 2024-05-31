package com.example.financialaidallocation.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.financialaidallocation.Activities.Meritbaseshortlisting;
import com.example.financialaidallocation.Classes.MeritbaseStudentModel;
import com.example.financialaidallocation.R;

import java.util.ArrayList;
import java.util.List;

public class MeritBaseStudentAdapter extends RecyclerView.Adapter<MeritBaseStudentAdapter.ViewHolder> {
    private List<MeritbaseStudentModel> students;
    private List<MeritbaseStudentModel> filteredList;

    public MeritBaseStudentAdapter(List<MeritbaseStudentModel> students) {
        this.students = students;
        this.filteredList = new ArrayList<>(students);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meritbasestudent, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MeritbaseStudentModel student = filteredList.get(position);
        holder.textViewName.setText(student.getName());
        holder.textViewAridNo.setText(student.getArid_no());
        // Bind other fields...
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    public void filter(String text) {
        filteredList.clear();
        if (text.isEmpty()) {
            filteredList.addAll(students);
        } else {
            text = text.toLowerCase();
            for (MeritbaseStudentModel item : students) {
                if (item.getName().toLowerCase().contains(text)) {
                    filteredList.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName;
        public TextView textViewAridNo;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.student_Name);
            textViewAridNo = itemView.findViewById(R.id.student_Aridno);
        }
    }
}
