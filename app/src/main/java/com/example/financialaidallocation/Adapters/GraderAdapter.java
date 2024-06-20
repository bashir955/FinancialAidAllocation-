package com.example.financialaidallocation.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.financialaidallocation.Classes.GraderModel;
import com.example.financialaidallocation.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class GraderAdapter extends RecyclerView.Adapter<GraderAdapter.ViewHolder> {
    public static final int PICK_IMAGE = 1;
    private List<GraderModel> graderList;
    private Context context;

    private String[] graderNames = {"Sir Zahid", "Sir Umer", "Sir Zeeshan"};

    public GraderAdapter(Context context, List<GraderModel> graderList) {
        this.context = context;
        this.graderList = graderList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemgrader, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GraderModel grader = graderList.get(position);

        holder.memberName.setText(grader.getName());
        holder.memberAridNo.setText(grader.getAridNo());

        if (grader.getProfileImagePath() != null) {
            Glide.with(context)
                    .load(grader.getProfileImagePath())
                    .apply(new RequestOptions().placeholder(R.drawable.person).circleCrop())
                    .into(holder.profileImage);
        } else {
            holder.profileImage.setImageResource(R.drawable.person);
        }

        holder.profileImage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            ((Activity) context).startActivityForResult(intent, PICK_IMAGE + position);
        });

        // Check if a grader is assigned and update the button state
        if (grader.getAssignedGrader() != null) {
            holder.assignButton.setText("Assigned");
            holder.assignButton.setBackgroundColor(Color.GRAY);
            holder.assignButton.setEnabled(false);
        } else {
            holder.assignButton.setText("Assign");
            holder.assignButton.setBackgroundColor(context.getResources().getColor(R.color.accept_button_color));
            holder.assignButton.setEnabled(true);
        }

        holder.assignButton.setOnClickListener(v -> showGraderDialog(position, holder));
    }

    @Override
    public int getItemCount() {
        return graderList.size();
    }

    private void showGraderDialog(int position, ViewHolder holder) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Assign Grader")
                .setItems(graderNames, (dialog, which) -> {
                    String selectedGrader = graderNames[which];
                    graderList.get(position).setAssignedGrader(selectedGrader);
                    Toast.makeText(context, "Assigned " + selectedGrader + " to " + graderList.get(position).getName(), Toast.LENGTH_SHORT).show();
                    // Update the button state
                    holder.assignButton.setText("Assigned");
                    holder.assignButton.setBackgroundColor(Color.GRAY);
                    holder.assignButton.setEnabled(false);
                });
        builder.create().show();
    }

    public void updateItemImage(int position, String imagePath) {
        graderList.get(position).setProfileImagePath(imagePath);
        notifyItemChanged(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView profileImage;
        TextView memberName;
        TextView memberAridNo;
        Button assignButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profileImage = itemView.findViewById(R.id.profile_image);
            memberName = itemView.findViewById(R.id.member_name);
            memberAridNo = itemView.findViewById(R.id.member_arid_no);
            assignButton = itemView.findViewById(R.id.button_assign);
        }
    }
}
