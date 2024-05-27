package com.example.financialaidallocation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommitteeMemberAdapter extends RecyclerView.Adapter<CommitteeMemberAdapter.ViewHolder> {

    private List<CommitteeMemberModel> memberList;
    private Context context;
    public static final int PICK_IMAGE = 1;

    public CommitteeMemberAdapter(Context context, List<CommitteeMemberModel> memberList) {
        this.context = context;
        this.memberList = memberList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_commitee_member, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CommitteeMemberModel member = memberList.get(position);

        holder.memberName.setText(member.getName());

        if (member.getProfileImagePath() != null) {
            Glide.with(context)
                    .load(member.getProfileImagePath())
                    .apply(new RequestOptions().placeholder(R.drawable.person).circleCrop())
                    .into(holder.profileImage);
        } else {
            holder.profileImage.setImageResource(R.drawable.person);
        }

        holder.profileImage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            ((Activity) context).startActivityForResult(intent, PICK_IMAGE);
        });

        holder.addButton.setOnClickListener(v -> {
            String name = holder.memberName.getText().toString().trim();
            if (!name.isEmpty()) {
                member.setName(name);
                Toast.makeText(context, "Added: " + name, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Name cannot be empty", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return memberList.size();
    }

    public void updateItemImage(int position, String imagePath) {
        memberList.get(position).setProfileImagePath(imagePath);
        notifyItemChanged(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView profileImage;
        TextView memberName;
        Button addButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profileImage = itemView.findViewById(R.id.profile_image);
            memberName = itemView.findViewById(R.id.member_name);
            addButton = itemView.findViewById(R.id.button_add);
        }
    }
}