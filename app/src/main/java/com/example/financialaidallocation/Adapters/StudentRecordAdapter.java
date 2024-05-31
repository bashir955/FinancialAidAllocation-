package com.example.financialaidallocation.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.financialaidallocation.Classes.StudentModel;
import com.example.financialaidallocation.R;

import java.util.ArrayList;
import java.util.List;
import android.widget.Filter;
import android.widget.Filterable;

public class StudentRecordAdapter extends RecyclerView.Adapter<StudentRecordAdapter.ViewHolder> implements Filterable {

    private List<StudentModel> students;
    private List<StudentModel> filteredStudents;

    public StudentRecordAdapter(List<StudentModel> students) {
        this.students = students;
        this.filteredStudents = new ArrayList<>(students); // Copy of the original list
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student_record, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StudentModel student = filteredStudents.get(position);
        holder.name.setText(student.getName());
        holder.arid_no.setText(student.getArid_no());
    }

    @Override
    public int getItemCount() {
        return filteredStudents.size();
    }

    @Override
    public Filter getFilter() {
        return studentFilter;
    }

    private Filter studentFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<StudentModel> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(students);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (StudentModel student : students) {
                    if (student.getName().toLowerCase().contains(filterPattern) ||
                            student.getArid_no().toLowerCase().contains(filterPattern)) {
                        filteredList.add(student);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredStudents.clear();
            if (results.values != null) {
                filteredStudents.addAll((List<StudentModel>) results.values);
            }
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView arid_no;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.student_name);
            arid_no = itemView.findViewById(R.id.arid_number);
        }
    }
}
