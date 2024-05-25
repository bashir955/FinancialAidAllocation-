package com.example.financialaidallocation.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.financialaidallocation.Activities.Student;
import com.example.financialaidallocation.R;

import java.util.List;

public class StudentRecordAdapter extends RecyclerView.Adapter<StudentRecordAdapter.ViewHolder> {
private List<Student> studentList;
private Context context;

public StudentRecordAdapter(List<Student> studentList, Context context) {
    this.studentList = studentList;
    this.context = context;
}

@NonNull
@Override
public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student_record, parent, false);
    return new ViewHolder(view);
}

@Override
public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Student student = studentList.get(position);
    holder.studentNameTextView.setText(student.getName());
    holder.aridNumberTextView.setText(student.getAridNumber());
    // Set profile image here if you have a mechanism to load images
}

@Override
public int getItemCount() {
    return studentList.size();
}

public static class ViewHolder extends RecyclerView.ViewHolder {
    public TextView studentNameTextView;
    public TextView aridNumberTextView;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        studentNameTextView = itemView.findViewById(R.id.student_name);
        aridNumberTextView = itemView.findViewById(R.id.arid_number);
    }
}
}