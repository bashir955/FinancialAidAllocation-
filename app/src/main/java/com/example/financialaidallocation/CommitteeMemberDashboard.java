package com.example.financialaidallocation;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Spinner;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class CommitteeMemberDashboard extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ImageButton btndrawerToogle;
    NavigationView navigationView;
    private RecyclerView recyclerView;
    private Spinner degreeSpinner;
    private DocumentAdapter adapter;
    private List<Document> documentList;
    private EditText searchEditText;
    private ImageView searchIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_committee_member_dashboard);
//        Spinner degreeSpinner = findViewById(R.id.DegreeSpinner);
//
//        // Create a list of dummy data for the spinner
//        List<String> Degree = new ArrayList<>();
//        Degree.add("BSC");
//        Degree.add("MSC");
//        // Create an ArrayAdapter using the dummy data and a default spinner layout
//        ArrayAdapter<String> adapterr = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Degree);
//
//        // Specify the layout to use when the list of choices appears
//        adapterr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        // Apply the adapter to the spinner
//        degreeSpinner.setAdapter(adapterr);
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
                    finish(); // Optional: close the current activity
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

        // Sample data for demonstration
        documentList = getSampleDocuments();

        // Create and set adapter
        adapter = new DocumentAdapter(documentList);
        recyclerView.setAdapter(adapter);

        // Initialize SearchEditText
        searchEditText = findViewById(R.id.search_edittext);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Do nothing
            }
        });

        // Initialize SearchIcon
        searchIcon = findViewById(R.id.search_icon);
        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = searchEditText.getText().toString().trim();
                adapter.getFilter().filter(query);
            }
        });

        // Initialize DegreeSpinner
        Spinner degreeSpinner = findViewById(R.id.DegreeSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.degree_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        degreeSpinner.setAdapter(adapter);

        degreeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedDegree = parent.getItemAtPosition(position).toString();
                filterByDegree(selectedDegree);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(

                findViewById(R.id.drawerlayout), (v, insets) ->
                {
                    Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                    return insets;
                });
    }

    private void filterByDegree(String degreeType) {
        List<Document> filteredDocuments = new ArrayList<>();
        for (Document document : documentList) {
            if (document.getDegreeType().equalsIgnoreCase(degreeType)) {
                filteredDocuments.add(document);
            }
        }
        adapter.updateList(filteredDocuments);
    }

    // Method to generate sample documents
    private List<Document> getSampleDocuments() {
        List<Document> documents = new ArrayList<>();
        // Add your documents here or fetch from a data source
        // For example:
        documents.add(new Document("Muhammad Bashir", "2020-Arid-3699","Msc"));
        documents.add(new Document("Muhammad Ali", "2021-Arid-3534","Bsc"));
        documents.add(new Document("M Amir Shehzad", "2022-Arid-2983","Msc"));
        documents.add(new Document("Arsalan Raja", "2023-Arid-4322","Bsc"));
        documents.add(new Document("Maria Bukhari", "2019-Arid-4656","Msc"));
        documents.add(new Document("Umair Malik", "2020-Arid-2516","Bsc"));

        documents.add(new Document("Ali Rehman", "2020-Arid-3699","Msc"));
        documents.add(new Document("Kashif Rizvi", "2021-Arid-3534","Msc"));
        documents.add(new Document("Shehzad Khan", "2022-Arid-2983","Bsc"));
        documents.add(new Document("Bilal Khan", "2023-Arid-4322","Msc"));
        documents.add(new Document("Nadir Ali", "2019-Arid-4656","Msc"));
        documents.add(new Document("Nimra Baloch", "2020-Arid-2516","Bsc"));
        return documents;
    }
}