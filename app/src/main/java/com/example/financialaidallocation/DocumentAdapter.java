package com.example.financialaidallocation;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class DocumentAdapter extends RecyclerView.Adapter<DocumentAdapter.DocumentViewHolder>{

    private List<Document> documentList;
//    private List<Document> filteredList;
    private Context context;
    public DocumentAdapter( Context context,List<Document> documentList) {
        this.context = context;
        this.documentList = documentList;
    }

    @NonNull
    @Override
    public DocumentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_document, parent, false);
        return new DocumentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DocumentViewHolder holder, int position) {
        Document document = documentList.get(position);
        holder.documentImage.setImageResource(document.getImageResId());
        holder.documentName.setText(document.getName());
        holder.documentAridNo.setText(document.getAridNo());
    }

    @Override
    public int getItemCount() {
        return documentList.size();
    }

    public static class DocumentViewHolder extends RecyclerView.ViewHolder {
        ImageView documentImage;
        TextView documentName;
        TextView documentAridNo;

        public DocumentViewHolder(@NonNull View itemView) {
            super(itemView);
            documentImage = itemView.findViewById(R.id.document_image);
            documentName = itemView.findViewById(R.id.document_name);
            documentAridNo = itemView.findViewById(R.id.document_aridno);
        }
    }
}


//
//    @Override
//    public Filter getFilter() {
//        return new Filter() {
//            @Override
//            protected FilterResults performFiltering(CharSequence constraint) {
//                String query = constraint.toString().toLowerCase().trim();
//                List<Document> filtered = new ArrayList<>();
//                if (query.isEmpty()) {
//                    filtered.addAll(documentList);
//                } else {
//                    for (Document document : documentList) {
//                        if (document.getName().toLowerCase().contains(query)|| document.getInfo().toLowerCase().contains(query))  {
//                            filtered.add(document);
//                        }
//                    }
//                }
//                FilterResults results = new FilterResults();
//                results.values = filtered;
//                return results;
//            }
//
//            @Override
//            protected void publishResults(CharSequence constraint, FilterResults results) {
//                filteredList.clear();
//                filteredList.addAll((List<Document>) results.values);
//                notifyDataSetChanged();
//            }
//        };
//    }
//    public void updateList(List<Document> newList) {
//        filteredList.clear();
//        filteredList.addAll(newList);
//        notifyDataSetChanged();
//    }
