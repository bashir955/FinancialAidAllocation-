package com.example.financialaidallocation.Activities;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.financialaidallocation.Adapters.aCommitteeMemberAdapter;
import com.example.financialaidallocation.Classes.CommitteeMemberModel;
import com.example.financialaidallocation.R;

import java.util.List;

import Api.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommiteeMember extends AppCompatActivity {

    private RecyclerView recyclerView;
    private aCommitteeMemberAdapter adapter;
  private UserService userService;

    private EditText searchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_commitee_member);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchEditText = findViewById(R.id.search_edittext);

        // Add space between items (16dp)
        int space = getResources().getDimensionPixelSize(R.dimen.Comm_item_spacing);
        recyclerView.addItemDecoration(new ItemDecoration(space));

        userService = new UserService();
        loadCommitteeMembers();

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (adapter != null) {
                    adapter.filter(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Do nothing
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void loadCommitteeMembers() {
        userService.getCommitteeMembers(new Callback<List<CommitteeMemberModel>>() {
            @Override
            public void onResponse(Call<List<CommitteeMemberModel>> call, Response<List<CommitteeMemberModel>> response) {
                if (response.isSuccessful()) {
                    List<CommitteeMemberModel> members = response.body();
                    adapter = new aCommitteeMemberAdapter(members);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(CommiteeMember.this, "Failed to load committee members", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<CommitteeMemberModel>> call, Throwable t) {
                Toast.makeText(CommiteeMember.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

