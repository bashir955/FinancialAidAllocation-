package com.example.financialaidallocation.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import com.example.financialaidallocation.Classes.CommitteeMemberModel;
import com.example.financialaidallocation.R;

import java.util.ArrayList;
import java.util.List;

public class CommitteeMemberAdapter extends RecyclerView.Adapter<CommitteeMemberAdapter.ViewHolder> {

    private List<CommitteeMemberModel> committeeMembers;
    private List<CommitteeMemberModel> filteredCommitteeMembers;

    public CommitteeMemberAdapter(List<CommitteeMemberModel> committeeMembers) {
//        this.context = context;
        this.committeeMembers = committeeMembers;
        this.filteredCommitteeMembers = new ArrayList<>(committeeMembers);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_commitee_member, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       // CommitteeMemberModel member = committeeMembers.get(position);
        CommitteeMemberModel member = filteredCommitteeMembers.get(position);

        holder.name.setText(member.getName());
        Picasso.get().load(member.getProfilePic())
                .placeholder(R.drawable.person)  // Placeholder image
                .into(holder.profilePic);
    }
    @Override
    public int getItemCount() {
        return filteredCommitteeMembers.size();
    }

    public void filter(String text) {
        filteredCommitteeMembers.clear();
        if (text.isEmpty()) {
            filteredCommitteeMembers.addAll(committeeMembers);
        } else {
            text = text.toLowerCase();
            for (CommitteeMemberModel member : committeeMembers) {
                if (member.getName().toLowerCase().contains(text)) {
                    filteredCommitteeMembers.add(member);
                }
            }
        }
        notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView profilePic;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.member_name);

            profilePic = itemView.findViewById(R.id.profile_image);
        }
    }
}