package com.example.financialaidallocation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class DocumentAdapter extends RecyclerView.Adapter<DocumentAdapter.DocumentViewHolder> implements Filterable {

    private List<Document> documentList;
    private List<Document> filteredList;
    public DocumentAdapter(List<Document> documentList) {
        this.documentList = documentList;
        this.filteredList = new ArrayList<>(documentList);
    }
    @NonNull
    @Override
    public DocumentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_document, parent, false);
        return new DocumentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DocumentViewHolder holder, int position) {
        Document document = filteredList.get(position);
        holder.bind(document);
    }
    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String query = constraint.toString().toLowerCase().trim();
                List<Document> filtered = new ArrayList<>();
                if (query.isEmpty()) {
                    filtered.addAll(documentList);
                } else {
                    for (Document document : documentList) {
                        if (document.getName().toLowerCase().contains(query)|| document.getInfo().toLowerCase().contains(query))  {
                            filtered.add(document);
                        }
                    }
                }
                FilterResults results = new FilterResults();
                results.values = filtered;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredList.clear();
                filteredList.addAll((List<Document>) results.values);
                notifyDataSetChanged();
            }
        };
    }
    public void updateList(List<Document> newList) {
        filteredList.clear();
        filteredList.addAll(newList);
        notifyDataSetChanged();
    }

    static class DocumentViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private TextView infoTextView;

        DocumentViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name_text_view);
            infoTextView = itemView.findViewById(R.id.info_text_view);
        }

        void bind(Document document) {

            nameTextView.setText(document.getName());
            infoTextView.setText(document.getInfo());
        }
    }
}
