package com.example.financialaidallocation.Activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.financialaidallocation.Adapters.ApplicationAdapter;
import com.example.financialaidallocation.Classes.ApplicationModel;
import com.example.financialaidallocation.Classes.CommitteeMemberModel;
import com.example.financialaidallocation.Classes.Document;
import com.example.financialaidallocation.R;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import Api.ApiService;
import Api.RetrofitClient;
import Api.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommitteeMemberDashboard extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ImageButton btndrawerToogle;
    NavigationView navigationView;
    private RecyclerView recyclerView;
    private ApplicationAdapter adapter;
    private List<ApplicationModel> list = new ArrayList<>();
    private EditText searchEditText;
    private ImageView searchIcon;
    private UserService userService;
    private Button balanceButton;
    private TextView remainingApplicationsTextView;
    private ImageView headerImageView;
    private TextView headerNameTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_committee_member_dashboard);
        // Reference the TextView for remaining applications
//        remainingApplicationsTextView = findViewById(R.id.RemainingApplication);

        balanceButton = findViewById(R.id.nav_balance);
        userService = new UserService();

        drawerLayout = findViewById(R.id.drawerlayout);

        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem Item) {
                int itemId = Item.getItemId();
                if (itemId == R.id.nav_logout) {
                    // Handle the logout action
                    Intent intent = new Intent(CommitteeMemberDashboard.this, MainActivity.class);
                    startActivity(intent);
                    // Optional: close the current activity
                    return true;
                } else if (itemId == R.id.SwitchtoFaculty) {
                    // Handle the switch to faculty action
                    Intent intent = new Intent(CommitteeMemberDashboard.this, FacultyMemberDashboard.class);
                    startActivity(intent);
                    // Optional: close the current activity
                    return true;
                }else if (itemId == R.id.nav_balance) {
                    // Handle the balance check action
                    showBalanceDialog();
                    return true;
                }
                return false;
            }
        });

        btndrawerToogle = findViewById(R.id.buttondrawerToogle);

        btndrawerToogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.open();
            }
        });


        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Add margin between items
        int space = getResources().getDimensionPixelSize(R.dimen.recycler_item_margin);
        recyclerView.addItemDecoration(new ItemDecoration(space));
        adapter = new ApplicationAdapter(list,this);
        recyclerView.setAdapter(adapter);
        // Initialize profile views from drawer header
        View headerView = navigationView.getHeaderView(0);
//        headerImageView = headerView.findViewById(R.id.headerimg);
        headerNameTextView = headerView.findViewById(R.id.headerN);

        // Call API to fetch committee member details
        fetchCommitteeMembers();

        // Call the API with a sample ID, for example 1
        new GetApplicationTask().execute();
        searchEditText = findViewById(R.id.search_edittext);

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.drawerlayout), (v, insets) -> {
                    Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                    return insets;
                });
    }

    private void fetchCommitteeMembers() {
        ApiService apiService = RetrofitClient.getinstance().create(ApiService.class);
        int committeeId = 17; // Replace this with the actual committee ID

        Call<CommitteeMemberModel> call = apiService.getCommitteeMembers(committeeId);
        call.enqueue(new Callback<CommitteeMemberModel>() {
            @Override
            public void onResponse(Call<CommitteeMemberModel> call, Response<CommitteeMemberModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    CommitteeMemberModel committeeMemberResponse = response.body();
                    String profilePicUrl = committeeMemberResponse.getProfilePic();
                    String name = committeeMemberResponse.getName();




                    if (headerNameTextView != null) {
                        headerNameTextView.setText(name);
                    } else {
                        Log.e("CommitteeMemberDashboard", "Header Name TextView is null");
                    }
                } else {
                    Toast.makeText(CommitteeMemberDashboard.this, "Failed to fetch committee members", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CommitteeMemberModel> call, Throwable t) {
                Log.e("CommitteeMemberDashboard", "Error fetching committee members", t);
                Toast.makeText(CommitteeMemberDashboard.this, "Error fetching committee members", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void showBalanceDialog() {
        userService.getBalance(new Callback<Double>() {
            @Override
            public void onResponse(Call<Double> call, Response<Double> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Inflate the dialog layout
                    LayoutInflater inflater = LayoutInflater.from(CommitteeMemberDashboard.this);
                    View dialogView = inflater.inflate(R.layout.dialog_balance, null);

                    // Create the dialog
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(CommitteeMemberDashboard.this);
                    dialogBuilder.setView(dialogView);

                    // Set the balance value
                    TextView balanceTextView = dialogView.findViewById(R.id.balance_text_view);
                    balanceTextView.setText(String.valueOf(response.body()));

                    // Set up the close button
                    Button closeButton = dialogView.findViewById(R.id.close_button);

                    // Create the dialog before setting the click listener
                    AlertDialog dialog = dialogBuilder.create();

                    closeButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    // Show the dialog
                    dialog.show();

                } else {
                    Toast.makeText(CommitteeMemberDashboard.this, "Failed to get balance", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Double> call, Throwable t) {
                Log.e("MainActivity", "Error fetching balance", t);
                Toast.makeText(CommitteeMemberDashboard.this, "Error fetching balance", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private class GetApplicationTask extends AsyncTask<Void, Void, List<ApplicationModel>> {

        @Override
        protected List<ApplicationModel> doInBackground(Void... voids) {
            int committeeId = 1; // Replace this with the actual committee ID
            ApiService apiService = RetrofitClient.getinstance().create(ApiService.class);

            try {
                Call<List<ApplicationModel>> call = apiService.getApplications(committeeId);
                Response<List<ApplicationModel>> response = call.execute();
                if (response.isSuccessful()) {
                    return response.body();
                } else {
                    return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<ApplicationModel> result) {
            if (result != null) {
                // Update your RecyclerView adapter with the list of applications
                list.clear();
                list.addAll(result);
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(CommitteeMemberDashboard.this, "Error retrieving data", Toast.LENGTH_LONG).show();
            }
        }
    }
}
/*

    private class GetApplicationTask extends AsyncTask<Integer, Void, String> {

        @Override
        protected String doInBackground(Integer... params) {
            int id = params[0];
            ApiService apiService = RetrofitClient.getinstance().create(ApiService.class);

            try {
                Call<List<ApplicationModel>> call = apiService.getApplications(id);
                Response<List<ApplicationModel>> response = call.execute();
                if (response.isSuccessful()) {
                    List<ApplicationModel> applications = response.body();
                    Gson gson = new Gson();
                    return gson.toJson(applications);
                } else {
                    return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                try {
                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<ApplicationModel>>() {}.getType();
                    List<ApplicationModel> applications = gson.fromJson(result, listType);

                    applicationList.clear();
                    applicationList.addAll(applications);
                    adapter.notifyDataSetChanged();

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(CommitteeMemberDashboard.this, "Error parsing data", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(CommitteeMemberDashboard.this, "Error retrieving data", Toast.LENGTH_LONG).show();
            }
        }
    }
}*/
