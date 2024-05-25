package com.example.financialaidallocation;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CommiteeMember extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CommitteeMemberAdapter memberAdapter;
    private List<CommitteeMemberModel> memberList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_commitee_member);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        // Add space between items (10dp)
        int space = getResources().getDimensionPixelSize(R.dimen.Comm_item_spacing);
        recyclerView.addItemDecoration(new ItemDecoration(space));

        memberList = new ArrayList<>();
        memberList.add(new CommitteeMemberModel("John Doe", null));
        memberList.add(new CommitteeMemberModel("Jane Smith", null));
        // Add more items as needed

        memberAdapter = new CommitteeMemberAdapter(this, memberList);
        recyclerView.setAdapter(memberAdapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            int position = requestCode - CommitteeMemberAdapter.PICK_IMAGE;; // Get the original position

            if (position >= 0 && position < memberList.size()) {
                memberAdapter.updateItemImage(position, imageUri.toString());
            }
    }
}}
