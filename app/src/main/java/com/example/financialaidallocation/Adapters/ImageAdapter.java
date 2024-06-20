package com.example.financialaidallocation.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.financialaidallocation.Classes.Config;
import com.example.financialaidallocation.Classes.EvidenceDocumentModel;
import com.example.financialaidallocation.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private Context context;
    private List<EvidenceDocumentModel> evidenceDocuments;

    public ImageAdapter(Context context, List<EvidenceDocumentModel> evidenceDocuments) {
        this.context = context;
        this.evidenceDocuments = evidenceDocuments;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EvidenceDocumentModel document = evidenceDocuments.get(position);
        String imageUrl = Config.IMAGE_BASE_URL + document.getImage();
        // Use Picasso to load the image
        Picasso.get()
                .load(imageUrl)
               .placeholder(R.drawable.person) // Placeholder image while loading
//                .error(R.drawable.error_image)             // Error image if loading fails
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return evidenceDocuments.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
