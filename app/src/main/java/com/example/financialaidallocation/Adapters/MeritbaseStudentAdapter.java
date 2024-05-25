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

import com.example.financialaidallocation.Classes.MeritbaseStudentModel;
import com.example.financialaidallocation.R;

import java.util.List;

public class MeritbaseStudentAdapter extends RecyclerView.Adapter<MeritbaseStudentAdapter.StudentViewHolder> {

    private List<MeritbaseStudentModel> studentList;
    private Context context;

    public MeritbaseStudentAdapter(Context context, List<MeritbaseStudentModel> studentList) {
        this.context = context;
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_meritbasestudent, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        MeritbaseStudentModel student = studentList.get(position);
        holder.studentName.setText(student.getName());
        holder.studentAridNo.setText(student.getAridNo());

        holder.buttonAccept.setOnClickListener(v -> {
            // Handle accept button click
        });

        holder.buttonReject.setOnClickListener(v -> {
            // Handle reject button click
        });

        // Set button colors programmatically
        holder.buttonAccept.setBackgroundTintList(ContextCompat.getColorStateList(context, R.color.accept_button_color));
        holder.buttonReject.setBackgroundTintList(ContextCompat.getColorStateList(context, R.color.reject_button_color));
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView studentName;
        TextView studentAridNo;
        Button buttonAccept;
        Button buttonReject;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            studentName = itemView.findViewById(R.id.student_name);
            studentAridNo = itemView.findViewById(R.id.student_aridno);
            buttonAccept = itemView.findViewById(R.id.button_accept);
            buttonReject = itemView.findViewById(R.id.button_reject);
        }
    }}